#include <stdio.h>
#include "esp_sleep.h"
#include "esp_log.h"
#include "esp_console.h"
#include "freertos/FreeRTOS.h"
#include "freertos/task.h"

void app_main(void)
{
    while (1) {
        printf("going for a nap\n");
        fflush(stdout);

        // 1 segundo = 1 000 000 microsegundos
        esp_sleep_enable_timer_wakeup(1000000);
        esp_light_sleep_start();

        printf("napped for 999\n");
        esp_console_flush(); // garante que tudo é impresso antes do sleep
        vTaskDelay(pdMS_TO_TICKS(1000));
    }
}
