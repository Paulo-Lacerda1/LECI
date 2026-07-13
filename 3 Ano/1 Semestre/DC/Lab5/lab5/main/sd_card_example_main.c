#include <stdio.h>
#include <string.h>
#include <sys/stat.h>
#include <sys/unistd.h>
#include "esp_log.h"
#include "esp_vfs_fat.h"
#include "sdmmc_cmd.h"
#include "freertos/FreeRTOS.h"
#include "freertos/task.h"
#include <portmacro.h>
#include "sdkconfig.h"

#define MOUNT_POINT "/sdcard"
static const char *TAG = "sdlog";

// Função auxiliar para escrever uma linha no log
static esp_err_t append_log_line(const char *path, const char *line)
{
    FILE *f = fopen(path, "a");
    if (f == NULL) {
        return ESP_FAIL;
    }
    fprintf(f, "%s\n", line);
    fclose(f);
    return ESP_OK;
}

void app_main(void)
{
    esp_err_t ret;
    sdmmc_card_t *card;
    const char mount_point[] = MOUNT_POINT;

    esp_vfs_fat_sdmmc_mount_config_t mount_config = {
        .format_if_mount_failed = false,
        .max_files = 5,
        .allocation_unit_size = 16 * 1024
    };

    // Inicialização SPI
    sdmmc_host_t host = SDSPI_HOST_DEFAULT();

    spi_bus_config_t bus_cfg = {
        .mosi_io_num = 19,
        .miso_io_num = 20,
        .sclk_io_num = 21,
        .quadwp_io_num = -1,
        .quadhd_io_num = -1,
        .max_transfer_sz = 4000,
    };

    ret = spi_bus_initialize(host.slot, &bus_cfg, SDSPI_DEFAULT_DMA);
    if (ret != ESP_OK) {
        ESP_LOGE(TAG, "Falha ao inicializar SPI");
        return;
    }

    sdspi_device_config_t slot_config = SDSPI_DEVICE_CONFIG_DEFAULT();
    slot_config.gpio_cs = 18;
    slot_config.host_id = host.slot;

    ret = esp_vfs_fat_sdspi_mount(mount_point, &host, &slot_config, &mount_config, &card);
    if (ret != ESP_OK) {
        return;
    }

    sdmmc_card_print_info(stdout, card);

    const char *log_path = MOUNT_POINT "/log.txt";

    // Criar ficheiro com cabeçalho CSV
    FILE *f = fopen(log_path, "w");
    if (f) {
        fprintf(f, "millis,counter\n");
        fclose(f);
    }

    uint32_t counter = 0;

    while (1) {
        // Calcular tempo decorrido desde o arranque em milissegundos
        uint64_t millis = (uint64_t)xTaskGetTickCount() * portTICK_PERIOD_MS;

        char line[64];
        snprintf(line, sizeof(line), "%llu,%lu", millis, counter);

        ESP_LOGI(TAG, "Escrever: %s", line);
        append_log_line(log_path, line);

        counter++;

        // Esperar aproximadamente 1 segundo
        vTaskDelay(pdMS_TO_TICKS(1000));
    }

    // Nunca chega aqui normalmente
    esp_vfs_fat_sdcard_unmount(mount_point, card);
    spi_bus_free(host.slot);
}
