package com.example.rubiksalgo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.CardDefaults
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.rubiksalgo.model.RubiksStep

/**
 * RubiksCard for Wear OS - Ultra Compact Version
 *
 * Optimized for small watch screens with scrollable content Prioritizes showing the algorithm
 * prominently
 */
@Composable
fun RubiksCard(step: RubiksStep, modifier: Modifier = Modifier) {
    Card(
            onClick = {},
            modifier = modifier.fillMaxWidth().fillMaxHeight(0.88f),
            backgroundPainter =
                    CardDefaults.cardBackgroundPainter(
                            startBackgroundColor = MaterialTheme.colors.surface,
                            endBackgroundColor = MaterialTheme.colors.surface
                    )
    ) {
        // Make content scrollable to ensure everything is accessible
        Column(
                modifier =
                        Modifier.fillMaxSize()
                                .verticalScroll(rememberScrollState())
                                .padding(horizontal = 12.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
        ) {

            // Note (if present) - very compact - moved this to the top
            if (step.note.isNotEmpty()) {
                Text(
                        text = "💡 ${step.note}",
                        style = MaterialTheme.typography.caption3,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.secondary,
                        maxLines = 2
                )
                Spacer(modifier = Modifier.height(4.dp))
            }

            // Step Number Badge (very compact)
            Box(
                    modifier =
                            Modifier.clip(RoundedCornerShape(50))
                                    .background(MaterialTheme.colors.primary)
                                    .padding(horizontal = 10.dp, vertical = 1.dp)
            ) {
                Text(
                        text = "Step ${step.id}",
                        style = MaterialTheme.typography.caption2,
                        color = MaterialTheme.colors.onPrimary,
                        fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Instruction (compact)
            Text(
                    text = step.instruction,
                    style = MaterialTheme.typography.caption1,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colors.onSurface,
                    maxLines = 3
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Algorithm - THE MOST IMPORTANT PART
            // Large, bold, and impossible to miss
            Box(
                    modifier =
                            Modifier.fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(MaterialTheme.colors.primary.copy(alpha = 0.15f))
                                    .padding(vertical = 12.dp, horizontal = 8.dp)
            ) {
                Text(
                        text = step.algorithm,
                        style =
                                MaterialTheme.typography.title1.copy(
                                        fontSize = 22.sp,
                                        letterSpacing = 1.sp
                                ),
                        fontFamily = FontFamily.Monospace,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
