package com.verygoodsecurity.coding.repository

import org.springframework.stereotype.Repository

@Repository
class AliasRepository {
    val data = mutableListOf<String>()

    fun add(alias: String) {
        data.add(alias)
    }

    fun get(alias: String): String? {
        return data.find { it == alias }
    }

}