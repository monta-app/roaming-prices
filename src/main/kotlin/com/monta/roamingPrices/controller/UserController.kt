package com.monta.roamingPrices.controller

import com.monta.roamingPrices.domain.User
import com.monta.roamingPrices.srvice.UserService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/users")
class UserController(
    private val userService: UserService
) {

    @Get("/{name}")
    fun findByName(name: String): String {
        println("Finding user by name: $name")
        val user = userService.findByName(name)

        return if (user != null) {
            user.name
        } else {
            "User not found"
        }
    }
}