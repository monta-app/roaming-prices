package com.monta.roamingPrices.domain

import io.micronaut.serde.annotation.SerdeImport
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@SerdeImport
@Entity
@Table(name = "users")
data class User(
    @Id
    @Column(nullable = false, updatable = false, unique = true)
    val id: UUID = UUID.randomUUID(),

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false, unique = true)
    val email: String
)