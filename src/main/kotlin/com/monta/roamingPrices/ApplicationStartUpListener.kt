package com.monta.roamingPrices

import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.context.event.StartupEvent
import jakarta.inject.Singleton

@Singleton
class ApplicationStartUpListener(
    private val dataBaseConfigurator: DataBaseConfigurator
) : ApplicationEventListener<StartupEvent> {
    override fun onApplicationEvent(event: StartupEvent) {
        println("Welcome to Roaming Prices")
        dataBaseConfigurator.startMigration()
    }
}