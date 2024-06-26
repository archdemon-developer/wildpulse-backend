package com.wildpulse.backend.controllers

import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity

class WPUserControllerTests(@Autowired val client: TestRestTemplate) {

    fun `check if health check API in WP User Controller works correctly`() {
        val entity: ResponseEntity<String> = client.getForEntity<String>("/users/health-check")
        assertEquals(HttpStatus.OK, entity.statusCode)
        assertEquals("API is working", entity.body)
    }
}