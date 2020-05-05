package com.verygoodsecurity.coding.aliases

import com.verygoodsecurity.coding.encrypt.Encryptor
import com.verygoodsecurity.coding.repository.AliasRepository
import org.springframework.stereotype.Service


interface AliasService {
  fun redact(secret: String): String
  fun reveal(secret: String): String?
}

@Service
class DefaultAliasService(
        private val encrypt: Encryptor,
        private val repository: AliasRepository
): AliasService {

  override fun redact(secret: String): String {
    val encrypted = encrypt.encrypt(secret)
    repository.add(encrypted)
    return encrypted
  }

  override fun reveal(secret: String): String? {
    return repository.get(secret)?.let{ encrypt.decrypt(it)}
  }

}