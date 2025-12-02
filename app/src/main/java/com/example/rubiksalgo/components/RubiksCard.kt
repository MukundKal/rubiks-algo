package com.example.rubiksalgo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rubiksalgo.model.RubiksStep

@Composable
fun RubiksCard(
    step: RubiksStep,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.80f), // Slightly smaller to make room for buttons
        shape = RoundedCornerShape(24.dp), // More rounded corners (Modern look)
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Step Number Badge
            Surface(
                shape = RoundedCornerShape(50),
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(
                    text = "Step ${step.id}",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            // Instruction
            Text(
                text = step.instruction,
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Algorithm (The Hero Section)
            // We put it in a box to give it a slight background highlight
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.background.copy(alpha = 0.5f))
                    .padding(16.dp)
            ) {
                Text(
                    text = step.algorithm,
                    style = MaterialTheme.typography.displayMedium.copy(
                        fontSize = 32.sp, // Force large size
                        letterSpacing = 2.sp
                    ),
                    fontFamily = FontFamily.Monospace,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary // Makes it pop!
                )
            }

            // Note
            if (step.note.isNotEmpty()) {
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "💡 ${step.note}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}