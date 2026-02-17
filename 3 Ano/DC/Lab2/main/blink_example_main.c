#include <stdio.h>
#include "freertos/FreeRTOS.h"
#include "freertos/task.h"
#include "driver/gpio.h"

#define BLINK_GPIO GPIO_NUM_10 // GPIO10 on ESP32-C6

void app_main(void)
{
    // Configurar GPIO10 como saída
    gpio_config_t io_conf = {
        .pin_bit_mask = 1ULL << BLINK_GPIO, // máscara de bit para GPIO10
        .mode = GPIO_MODE_OUTPUT,
        .pull_up_en = GPIO_PULLUP_DISABLE,
        .pull_down_en = GPIO_PULLDOWN_DISABLE,
        .intr_type = GPIO_INTR_DISABLE
    };
    gpio_config(&io_conf);

    bool level = true;

    while (1) {
        gpio_set_level(BLINK_GPIO, level);      // altera o estado do LED
        printf("GPIO 10 set to %d\n", level); // imprime no terminal                         // inverter para o próximo toggle
        vTaskDelay(pdMS_TO_TICKS(2000));        // delay de 2 segundos
    }
}

/*
#include <stdio.h>
#include "freertos/FreeRTOS.h"
#include "freertos/task.h"
#include "driver/gpio.h"
#define INPUT_GPIO GPIO_NUM_9 // choose an available GPIO on your board

void app_main(void)
{
// Configure the GPIO as input with pull-up enabled
gpio_config_t io_conf = {
    .pin_bit_mask = 1ULL << INPUT_GPIO, // bit mask for selected GPIO
    .mode = GPIO_MODE_INPUT,
    .pull_up_en = GPIO_PULLUP_ENABLE, // enable internal pull-up
    .pull_down_en = GPIO_PULLDOWN_DISABLE,
    .intr_type = GPIO_INTR_DISABLE
};
gpio_config(&io_conf);

while (1) {
    int level = gpio_get_level(INPUT_GPIO); // read the pin state (0 or 1)
    printf("GPIO 9 read %d\n", level);
    vTaskDelay(pdMS_TO_TICKS(1000)); // check every 1s
}
}
*/