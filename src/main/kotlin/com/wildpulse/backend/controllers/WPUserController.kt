package com.wildpulse.backend.controllers

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class WPUserController {

  @GetMapping("/health-check")
  @ResponseStatus(HttpStatus.OK)
  fun healthCheck(): String = "API is working";
}
