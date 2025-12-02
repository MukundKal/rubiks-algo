package com.example.rubiksalgo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.Modifier
import com.example.rubiksalgo.ui.screens.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This is the bridge between Android and your new UI code
        setContent {
            // Force Dark Mode for the minimal aesthetic
            MaterialTheme(colorScheme = darkColorScheme()) {

                // A background container
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Load the HomeScreen we created in the other file
                    HomeScreen()
                }
            }
        }
    }
}