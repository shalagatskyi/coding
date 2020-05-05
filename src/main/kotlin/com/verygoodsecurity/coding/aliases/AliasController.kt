package com.verygoodsecurity.coding.aliases

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

data class AliasRequest(val secret: String)

data class AliasResponse(val alias: String)

@RestController
@RequestMapping("/aliases")
class AliasController(val aliasService: AliasService) {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun create(@RequestBody createRequest: AliasRequest) = AliasResponse(aliasService.redact(createRequest.secret))

  @GetMapping("/{secret}")
  fun get(@PathVariable("secret") secret: String): ResponseEntity<AliasResponse> {
    val alias = aliasService.reveal(secret) ?: return ResponseEntity.notFound().build()

    return ResponseEntity.ok(AliasResponse(alias))
  }

}
