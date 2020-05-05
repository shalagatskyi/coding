package com.verygoodsecurity.coding.config

import com.verygoodsecurity.coding.encrypt.AES
import com.verygoodsecurity.coding.encrypt.Encryptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class ApplicationConfig {

    @Bean
    fun createCrypt(): Encryptor {
        return AES("sooper secret")
    }

}