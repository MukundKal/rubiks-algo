package com.example.rubiksalgo.model

/**
 * Data Transfer Object (DTO) representing a single step in the algorithm.
 * * Industry Standard:
 * - This is a "Data Class" which automatically generates getters, setters, equals(), and hashcode().
 * - It has no dependency on Android framework (Context, Views), making it unit testable.
 */
data class RubiksStep(
    val id: Int,
    val instruction: String,
    val algorithm: String,
    val note: String = ""
)