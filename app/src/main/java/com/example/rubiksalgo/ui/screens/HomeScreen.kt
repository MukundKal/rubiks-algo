package com.example.rubiksalgo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rubiksalgo.data.RubiksRepository
import com.example.rubiksalgo.ui.components.RubiksCard
import kotlinx.coroutines.launch
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val steps = remember { RubiksRepository.getAllSteps() }
    val pagerState = rememberPagerState(pageCount = { steps.size })
    val scope = rememberCoroutineScope() // Needed for button clicks to scroll pager

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "RUBIK'S ALGO",
                        fontWeight = FontWeight.Black,
                        letterSpacing = 1.sp
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween // Pushes content to edges
        ) {

            // 1. Progress Indicator (Visual bar instead of just text)
            LinearProgressIndicator(
                progress = { (pagerState.currentPage + 1) / steps.size.toFloat() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .height(4.dp),
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
                color = MaterialTheme.colorScheme.primary
            )

            // 2. The Card Area
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 24.dp), // Peek next card
                pageSpacing = 16.dp,
                modifier = Modifier
                    .weight(1f) // Takes up all available middle space
                    .padding(vertical = 24.dp)
            ) { page ->
                RubiksCard(step = steps[page])
            }

            // 3. Bottom Controls (Easier to tap than swipe)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp, start = 24.dp, end = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Back Button
                FilledTonalIconButton(
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    },
                    enabled = pagerState.currentPage > 0,
                    modifier = Modifier.size(56.dp) // Large touch target
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Previous")
                }

                // Step Counter Text
                Text(
                    text = "${pagerState.currentPage + 1} / ${steps.size}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                // Next Button
                FilledTonalIconButton(
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    },
                    enabled = pagerState.currentPage < steps.size - 1,
                    modifier = Modifier.size(56.dp)
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Next")
                }
            }
        }
    }
}