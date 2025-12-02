package com.example.rubiksalgo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.wear.compose.material.MaterialTheme
import com.example.rubiksalgo.ui.screens.HomeScreen

/**
 * MainActivity for Wear OS
 *
 * Justification for changes:
 * - Uses androidx.wear.compose.material.MaterialTheme instead of Material3
 * - Wear MaterialTheme automatically handles round screen insets and AMOLED optimization
 * - Removed Surface wrapper - Wear OS Scaffold handles background containers
 * - Simplified for watch-optimized experience
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // Wear OS Material Theme (optimized for round watches with AMOLED displays)
            MaterialTheme { HomeScreen() }
        }
    }
}
