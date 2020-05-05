package com.verygoodsecurity.coding.aliases

import com.verygoodsecurity.coding.encrypt.Encryptor
import com.verygoodsecurity.coding.repository.AliasRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

class DefaultAliasServiceTest {

    lateinit var encrypt: Encryptor
    lateinit var repository: AliasRepository
    lateinit var service: AliasService

    @BeforeEach
    fun before() {
        encrypt = mock(Encryptor::class.java)
        repository = mock(AliasRepository::class.java)
        service = DefaultAliasService(encrypt, repository)
    }

    @Test
    fun `test redact returns encrypted string and saves to DB`() {
        val secret = "secret"
        val encryptedSecret = "secre!!t"
        `when`(encrypt.encrypt(secret)).thenReturn(encryptedSecret)

        val result = service.redact(secret)

        assertEquals(result, encryptedSecret)
        verify(repository, times(1)).add(encryptedSecret)
    }

    @Test
    fun `test reveal if alias exists expect decode`() {
        val secret = "secret"
        val alias = "secre!!t"
        `when`(repository.get(alias)).thenReturn(alias)
        `when`(encrypt.decrypt(alias)).thenReturn(secret)

        val result = service.reveal(alias)

        assertEquals(result, secret)
    }

    @Test
    fun `test reveal if alias doesn't exist expect no decode invocation`() {
        val alias = "secre!!t"

        val result = service.reveal(alias)

        assertNull(result)
        verifyNoInteractions(encrypt)
    }
}