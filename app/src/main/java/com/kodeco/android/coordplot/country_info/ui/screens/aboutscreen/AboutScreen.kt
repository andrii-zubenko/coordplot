package com.kodeco.android.coordplot.country_info.ui.screens.aboutscreen

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.kodeco.android.coordplot.BuildConfig
import com.kodeco.android.coordplot.R

@Composable
fun AboutScreen(onBackTap: () -> Unit) {
    val appVersionAnimationState = remember { mutableStateOf(AppVersionAnimationState.INITIAL) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.about_title)) },
                navigationIcon = {
                    IconButton(onClick = { onBackTap() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val transition = updateTransition(
                targetState = appVersionAnimationState.value,
                label = "App Version Animation"
            )
            val fontSize = transition.animateFloat(
                label = "Animate Font Size",
                transitionSpec = {
                    tween(durationMillis = 1000)
                }
            ) { state ->
                when (state) {
                    AppVersionAnimationState.INITIAL -> 16f
                    AppVersionAnimationState.TAPPED -> 48f
                }
            }.value.sp

            if (transition.currentState == transition.targetState) {
                appVersionAnimationState.value = AppVersionAnimationState.INITIAL
            }

            Text(
                text = "App Version: ${BuildConfig.VERSION_NAME}",
                modifier = Modifier.clickable {
                    appVersionAnimationState.value = AppVersionAnimationState.TAPPED
                },
                fontSize = fontSize
            )
        }
    }
}

enum class AppVersionAnimationState {
    INITIAL,
    TAPPED
}

@Preview
@Composable
fun AboutScreenPreview() {
    AboutScreen(onBackTap = {})
}