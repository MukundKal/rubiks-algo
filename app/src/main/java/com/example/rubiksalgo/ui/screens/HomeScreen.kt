package com.example.rubiksalgo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
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

/**
 * HomeScreen for Wear OS with Rotary (Bezel) Navigation
 *
 * Key Changes for Wear OS:
 * 1. Uses Wear Compose Scaffold with TimeText, Vignette, and HorizontalPageIndicator
 * 2. Implements rotaryWithScroll() modifier to connect bezel rotation to HorizontalPager
 * 3. Removed top app bar (too large for watch screens)
 * 4. Removed navigation buttons (bezel provides better UX on watches)
 * 5. Added compact page counter for minimal design
 * 6. HorizontalPageIndicator shows page dots at bottom (Wear OS standard for pagers)
 *
 * Bezel Navigation:
 * - Rotate bezel clockwise → Next step
 * - Rotate bezel counter-clockwise → Previous step
 * - Horologist's rotaryWithScroll handles velocity and fling behavior automatically
 */
@OptIn(ExperimentalHorologistApi::class)
@Composable
fun HomeScreen() {
    val steps = remember { RubiksRepository.getAllSteps() }
    val pagerState = rememberPagerState(pageCount = { steps.size })
    val focusRequester = remember { FocusRequester() }

    Scaffold(
            timeText = {
                // TimeText shows clock at top
                TimeText()
            },
            vignette = {
                // Vignette provides edge fade effect for round screens
                Vignette(vignettePosition = VignettePosition.TopAndBottom)
            },
            pageIndicator = {
                // HorizontalPageIndicator shows page dots at bottom (standard for pagers)
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
        Column(
                modifier = Modifier.fillMaxSize().padding(horizontal = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // Compact Page Counter
            Text(
                    text = "${pagerState.currentPage + 1}/${steps.size}",
                    style = MaterialTheme.typography.caption1,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary
            )

            Spacer(modifier = Modifier.height(4.dp))

            // HorizontalPager with Rotary (Bezel) Support
            // 🔥 KEY FEATURE: rotaryWithScroll connects bezel rotation to pager navigation
            HorizontalPager(
                    state = pagerState,
                    modifier =
                            Modifier.weight(1f)
                                    .fillMaxWidth()
                                    // This modifier enables bezel navigation!
                                    // When user rotates bezel, pager scrolls to next/previous page
                                    .focusRequester(focusRequester)
                                    .rotaryWithScroll(
                                            scrollableState = pagerState,
                                            focusRequester = focusRequester
                                    ),
                    pageSpacing = 4.dp,
                    contentPadding = PaddingValues(horizontal = 2.dp)
            ) { page -> RubiksCard(step = steps[page]) }
        }
    }
}
