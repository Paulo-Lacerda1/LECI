set(srcs test_mqtt5_client_broker.c test_mqtt5.c)

idf_component_register(SRCS "test_mqtt5.c" "${srcs}"
                    PRIV_REQUIRES cmock test_utils mqtt nvs_flash app_update spi_flash common)
flash common)
