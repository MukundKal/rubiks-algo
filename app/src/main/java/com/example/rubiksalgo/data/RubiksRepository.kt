package com.example.rubiksalgo.data

import com.example.rubiksalgo.model.RubiksStep

/**
 * Repository Object.
 * * Industry Standard:
 * - The "Repository Pattern" abstracts the data source.
 * - The UI doesn't know if this data is hardcoded, from a Database (Room), or an API (Retrofit).
 * - We use an 'object' (Singleton) here for simplicity, but in a larger app, this would be a class
 * injected via Dependency Injection (Hilt/Koin).
 */
object RubiksRepository {

    // The source of truth for our data
    val steps =
            listOf(
                    RubiksStep(1, "Flip edge", "F U' R U"),
                    RubiksStep(2, "Move edge to down (right)", "U R U' R' U' F' U F"),
                    RubiksStep(3, "Move edge to down (left)", "U' L' U L U F U' F'"),
                    // RubiksStep(4, "Reset (wrong orientation)", "U R U' R' U' F' U F U2"),
                    RubiksStep(4, "Move to top Yellow Cross", "F R U R' U' F'"),
                    RubiksStep(
                            5,
                            "Swap front/left edges",
                            "R U R' U R U2 R' U",
                            "Cross edges match centers"
                    ),
                    RubiksStep(
                            6,
                            "Position yellow corners",
                            "U R U' L' U R' U' L",
                            "Repeat x N times"
                    ),
                    RubiksStep(7, "Orient yellow corners", "R' D' R D", "Repeat until solved")
            )

    fun getAllSteps(): List<RubiksStep> {
        return steps
    }
}
