package com.monta.roamingPrices.controller

import com.monta.roamingPrices.domain.User
import com.monta.roamingPrices.srvice.UserService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/users")
class UserController(
    private val userService: UserService
) {

    @Get("/{name}")
fun findByName(name: String): User? {
    println("Finding user by email: $name")
    return userService.findByName(name)
}
}