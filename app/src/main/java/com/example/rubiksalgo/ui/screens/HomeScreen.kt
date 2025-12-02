package com.example.rubiksalgo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.rubiksalgo.data.RubiksRepository
import com.example.rubiksalgo.ui.components.RubiksCard
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import com.google.android.horologist.compose.layout.AppScaffold
import com.google.android.horologist.compose.rotary.rotaryWithSnap
import androidx.wear.compose.material.TimeText
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.wear.compose.foundation.ExperimentalWearFoundationApi
import androidx.wear.compose.foundation.rememberActiveFocusRequester
import com.google.android.horologist.annotations.ExperimentalHorologistApi

@OptIn(ExperimentalHorologistApi::class, ExperimentalWearFoundationApi::class)
@Composable
fun HomeScreen() {
    val steps = remember { RubiksRepository.getAllSteps() }
    var currentStepIndex by remember { mutableStateOf(0) }
    val focusRequester = rememberActiveFocusRequester()

    AppScaffold(
        timeText = { TimeText() }
    ) {
        val state = rememberScalingLazyListState()
        ScalingLazyColumn(
            state = state,
            modifier = Modifier
                .fillMaxSize()
                .rotaryWithSnap(
                    focusRequester = focusRequester,
                    onValueChange = {
                        val newIndex = (currentStepIndex + it.toInt()).coerceIn(0, steps.size - 1)
                        currentStepIndex = newIndex
                    }
                )
                .focusRequester(focusRequester),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                RubiksCard(step = steps[currentStepIndex])
            }
        }
    }
}