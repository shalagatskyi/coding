package com.verygoodsecurity.coding.encrypt

interface Encryptor {
    fun encrypt(strToEncrypt: String): String
    fun decrypt(strToDecrypt: String): String
}