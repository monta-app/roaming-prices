package com.monta.roamingPrices.controller

import com.monta.roamingPrices.domain.User
import com.monta.roamingPrices.srvice.UserService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import java.util.UUID

@Controller("/users")
class UserController(
    private val userService: UserService
) {

    @Get("/{name}")
    fun findByName(name: String): User {
        println("Finding user by name: $name")
        val user = userService.findByName(name)

        return user ?: User(name = "User not found", email = "User not found")
    }
}