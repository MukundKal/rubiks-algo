package com.example.rubiksalgo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.Color
import androidx.wear.compose.material.Colors
import androidx.wear.compose.material.MaterialTheme
import com.example.rubiksalgo.ui.screens.HomeScreen

/**
 * MainActivity for Wear OS with Dark Mode
 *
 * Forces dark theme optimized for AMOLED displays on Galaxy Watch
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // Custom dark colors for Wear OS (AMOLED optimized)
            val darkColors =
                    Colors(
                            primary = Color(0xFF82B1FF), // Light blue for dark mode
                            primaryVariant = Color(0xFF448AFF), // Darker blue
                            secondary = Color(0xFFFFAB40), // Orange accent
                            secondaryVariant = Color(0xFFFF9100), // Darker orange
                            background = Color.Black, // Pure black for AMOLED
                            surface = Color(0xFF1E1E1E), // Dark gray for cards
                            error = Color(0xFFCF6679), // Red for errors
                            onPrimary = Color.Black, // Text on primary
                            onSecondary = Color.Black, // Text on secondary
                            onBackground = Color.White, // Text on background
                            onSurface = Color.White, // Text on surface
                            onSurfaceVariant = Color(0xFFBBBBBB), // Dimmed text
                            onError = Color.Black // Text on error
                    )

            MaterialTheme(colors = darkColors) { HomeScreen() }
        }
    }
}
