package com.example.rubiksalgo.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
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
import kotlin.math.roundToInt
import kotlinx.coroutines.delay

/**
 * HomeScreen for Wear OS with Rotary (Bezel) Navigation
 *
 * Features:
 * - Dark mode optimized for AMOLED displays
 * - Physical bezel rotation support with auto-snap
 * - Visual feedback when bezel is used
 * - Swipe gestures as fallback
 */
@OptIn(ExperimentalHorologistApi::class)
@Composable
fun HomeScreen() {
    val steps = remember { RubiksRepository.getAllSteps() }
    val pagerState = rememberPagerState(pageCount = { steps.size })
    val focusRequester = remember { FocusRequester() }
    val scope = rememberCoroutineScope()

    // Track bezel feedback
    var showBezelFeedback by remember { mutableStateOf(false) }
    var lastScrollTime by remember { mutableStateOf(0L) }

    // Request focus when screen loads (CRITICAL for bezel to work)
    LaunchedEffect(Unit) {
        delay(100)
        focusRequester.requestFocus()
    }

    // Monitor scrolling and snap to nearest page when it settles
    LaunchedEffect(pagerState.currentPage, pagerState.currentPageOffsetFraction) {
        if (pagerState.currentPageOffsetFraction != 0f) {
            lastScrollTime = System.currentTimeMillis()
        }
    }

    // Snap to nearest page after scrolling settles
    LaunchedEffect(pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress && pagerState.currentPageOffsetFraction != 0f) {
            // Wait a bit to see if more scrolling happens
            delay(100)

            if (!pagerState.isScrollInProgress) {
                // Calculate nearest page
                val currentOffset = pagerState.currentPageOffsetFraction
                val targetPage =
                        if (currentOffset.roundToInt() == 0) {
                            pagerState.currentPage
                        } else {
                            pagerState.currentPage + currentOffset.roundToInt()
                        }

                // Snap to the target page
                if (targetPage in 0 until steps.size) {
                    pagerState.animateScrollToPage(targetPage)
                }
            }
        }
    }

    // Show feedback when page changes
    LaunchedEffect(pagerState.currentPage) {
        if (pagerState.currentPage > 0 || pagerState.settledPage > 0) {
            showBezelFeedback = true
            delay(800)
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
                                    .background(Color.Black)
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
                                        .focusRequester(focusRequester)
                                        .focusTarget()
                                        .rotaryWithScroll(
                                                scrollableState = pagerState,
                                                focusRequester = focusRequester
                                        ),
                        pageSpacing = 0.dp,
                        contentPadding = PaddingValues(0.dp)
                ) { page -> RubiksCard(step = steps[page]) }
            }

            // Bezel Feedback Toast at Bottom
            AnimatedVisibility(
                    visible = showBezelFeedback,
                    enter = fadeIn(),
                    exit = fadeOut(),
                    modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 6.dp)
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
                            text = "🔄 Bezel",
                            style = MaterialTheme.typography.caption2,
                            color = MaterialTheme.colors.onPrimary,
                            fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
