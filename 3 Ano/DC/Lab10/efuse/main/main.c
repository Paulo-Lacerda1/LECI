#include "esp_efuse.h"
#include "esp_efuse_table.h"
#include "esp_system.h"
#include "esp_log.h"
#include "mbedtls/aes.h"



#define AES_KEY_SIZE 32       // 256 bits
#define BLOCK_SIZE   32       // AES block size
#define EFUSE_BLOCK  EFUSE_BLK_KEY5  // BLOCK_KEY5 on ESP32-C6

static const char *TAG = "AES_EFUSE";

// Only call this in development, once!
void write_key_to_efuse_dev_only() {
    const uint8_t test_key[AES_KEY_SIZE] = {
        0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
        0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F,
        0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17,
        0x18, 0x19, 0x1A, 0x1B, 0x1C, 0x1D, 0x1E, 0x1F
    };

    esp_err_t err = esp_efuse_write_field_blob(ESP_EFUSE_KEY5, test_key, AES_KEY_SIZE * 8);
    if (err != ESP_OK) {
        ESP_LOGE("EFUSE", "Failed to write key to eFuse: %s", esp_err_to_name(err));
    } else {
        ESP_LOGI("EFUSE", "Key written to eFuse KEY5 (via field descriptor)");
    }
}

void example_aes_encrypt_decrypt_with_efuse_key() {
    uint8_t efuse_key[AES_KEY_SIZE] = {0};
    esp_err_t err;
    uint8_t i;

   
    err = esp_efuse_read_block(EFUSE_BLOCK, efuse_key, 0, 256);
    if (err != ESP_OK) {
        ESP_LOGE(TAG, "Failed to read eFuse key: %s", esp_err_to_name(err));
        return;
    }

    printf("eFuse Key: ");
    for (i = 0; i < AES_KEY_SIZE; i++) {
        printf("0x%02X ", efuse_key[i]);
    }
    printf("\n");

    ESP_LOGI(TAG, "eFuse key read successfully.");

    
    uint8_t plaintext[BLOCK_SIZE]  = "ConnectedDevices";  // must be 16 bytes
    uint8_t ciphertext[BLOCK_SIZE] = {0};
    uint8_t decrypted[BLOCK_SIZE]  = {0};

    
    mbedtls_aes_context aes;
    mbedtls_aes_init(&aes);

    mbedtls_aes_setkey_enc(&aes, efuse_key, AES_KEY_SIZE * 8);  // bits
    mbedtls_aes_crypt_ecb(&aes, MBEDTLS_AES_ENCRYPT, plaintext, ciphertext);


    mbedtls_aes_setkey_dec(&aes, efuse_key, AES_KEY_SIZE * 8);
    mbedtls_aes_crypt_ecb(&aes, MBEDTLS_AES_DECRYPT, ciphertext, decrypted);

    mbedtls_aes_free(&aes);


    ESP_LOGI(TAG, "Plaintext:  %s", plaintext);
    ESP_LOG_BUFFER_HEX(TAG, ciphertext, BLOCK_SIZE);
    ESP_LOGI(TAG, "Decrypted:  %s", decrypted);
}


void app_main(void) {
   
    //write_key_to_efuse_dev_only();  // First-time write only!!!!
    //example_aes_encrypt_decrypt_with_efuse_key();
}
