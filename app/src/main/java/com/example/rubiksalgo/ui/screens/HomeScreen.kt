package com.example.rubiksalgo.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.HorizontalPageIndicator
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PageIndicatorState
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import com.example.rubiksalgo.data.RubiksRepository
import com.example.rubiksalgo.ui.components.RubiksCard
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.compose.rotaryinput.rotaryWithScroll
import kotlinx.coroutines.delay

/**
 * HomeScreen for Wear OS with Rotary (Bezel) Navigation
 *
 * Features:
 * - Dark mode optimized for AMOLED displays
 * - Physical bezel rotation support
 * - Visual feedback when bezel is used
 * - Swipe gestures as fallback
 */
@OptIn(ExperimentalHorologistApi::class)
@Composable
fun HomeScreen() {
    val steps = remember { RubiksRepository.getAllSteps() }
    val pagerState = rememberPagerState(pageCount = { steps.size })
    val focusRequester = remember { FocusRequester() }

    // Track page changes to show bezel feedback
    var showBezelFeedback by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    // Request focus when screen loads (CRITICAL for bezel to work)
    LaunchedEffect(Unit) {
        delay(100) // Small delay to ensure pager is ready
        focusRequester.requestFocus()
    }

    // Show feedback when page changes
    LaunchedEffect(pagerState.currentPage) {
        if (pagerState.currentPage > 0 || pagerState.settledPage > 0) {
            showBezelFeedback = true
            delay(800) // Show for 800ms
            showBezelFeedback = false
        }
    }

    Scaffold(
            timeText = { TimeText() },
            vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) },
            pageIndicator = {
                HorizontalPageIndicator(
                        pageIndicatorState =
                                object : PageIndicatorState {
                                    override val pageCount: Int
                                        get() = steps.size
                                    override val pageOffset: Float
                                        get() = 0f
                                    override val selectedPage: Int
                                        get() = pagerState.currentPage
                                }
                )
            }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Main Content Column with DARK BACKGROUND
            Column(
                    modifier =
                            Modifier.fillMaxSize()
                                    .background(Color.Black) // Force dark background
                                    .padding(horizontal = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                // Page Counter
                Text(
                        text = "${pagerState.currentPage + 1}/${steps.size}",
                        style = MaterialTheme.typography.caption1,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.primary
                )

                Spacer(modifier = Modifier.height(4.dp))

                // HorizontalPager with Rotary (Bezel) Support
                HorizontalPager(
                        state = pagerState,
                        modifier =
                                Modifier.weight(1f)
                                        .fillMaxWidth()
                                        .focusRequester(focusRequester) // 1. Attach focus requester
                                        .focusTarget() // 2. Make focusable
                                        .rotaryWithScroll( // 3. Enable rotary input
                                                scrollableState = pagerState,
                                                focusRequester = focusRequester
                                        ),
                        pageSpacing = 0.dp,
                        contentPadding = PaddingValues(0.dp),
                        flingBehavior =
                                PagerDefaults.flingBehavior(
                                        state = pagerState,
                                        snapPositionalThreshold =
                                                0.2f // Snap when 20% of page visible
                                )
                ) { page -> RubiksCard(step = steps[page]) }
            }

            // Bezel Feedback Toast at Bottom
            AnimatedVisibility(
                    visible = showBezelFeedback,
                    enter = fadeIn(),
                    exit = fadeOut(),
                    modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 4.dp)
            ) {
                Box(
                        modifier =
                                Modifier.background(
                                                color =
                                                        MaterialTheme.colors.primary.copy(
                                                                alpha = 0.9f
                                                        ),
                                                shape = RoundedCornerShape(16.dp)
                                        )
                                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                            text = "🔄 Bezel Active",
                            style = MaterialTheme.typography.caption2,
                            color = MaterialTheme.colors.onPrimary,
                            fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
