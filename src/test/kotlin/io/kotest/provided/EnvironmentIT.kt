package io.kotest.provided

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.micronaut.context.env.Environment
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest


@MicronautTest
class EnvironmentIT(
    private val environment: Environment
) : StringSpec({

    "should have 'test' environment active" {
        environment.activeNames shouldBe setOf("test")
    }
})