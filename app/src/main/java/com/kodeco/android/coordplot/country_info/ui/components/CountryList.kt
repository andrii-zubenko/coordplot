package com.kodeco.android.coordplot.country_info.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Help
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodeco.android.coordplot.R
import com.kodeco.android.coordplot.country_info.model.Country
import com.kodeco.android.coordplot.country_info.model.CountryFlags
import com.kodeco.android.coordplot.country_info.model.CountryName

@Composable
fun CountryList(
    countries: List<Country>,
    onRefreshTap: () -> Unit,
    onCountryRowTap: (Int) -> Unit,
    onAboutTap: () -> Unit
) {
    Column {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 8.dp,
                        vertical = 8.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(R.string.country_info))
                Icon(
                    Icons.Rounded.Help,
                    contentDescription = stringResource(R.string.about),
                    modifier = Modifier.clickable { onAboutTap() }
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        onRefreshTap()
                    }
                ) {
                    Text(text = stringResource(R.string.refresh))
                }
            }
        }
        LazyColumn {
            items(countries.size) { index ->
                Card(
                    modifier = Modifier
                        .padding(
                            horizontal = 8.dp,
                            vertical = 8.dp
                        )
                        .clickable { onCountryRowTap(index) },
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(modifier = Modifier.padding(all = 8.dp)) {
                            Text(text = "Name: ${countries[index].name.common}")
                            Text(text = "Capital: ${countries[index].capital?.get(0).toString()}")
                        }
                        AnimatedStar()
                    }
                }
            }
        }
    }
}

enum class StarState {
    Filled, Empty
}

@Composable
fun AnimatedStar() {
    val starState = remember { mutableStateOf(StarState.Empty) }
    val transition = updateTransition(targetState = starState, label = "Star Transition")

    val rotation by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 500) },
        label = "Rotation"
    ) { starState ->
        if (starState.value == StarState.Filled) 360f else 0f
    }

    Box(
        modifier = Modifier
            .clickable {
                starState.value = when (starState.value) {
                    StarState.Filled -> StarState.Empty
                    StarState.Empty -> StarState.Filled
                }
            }
            .padding(16.dp)

    ) {
        val imageModifier = Modifier
            .size(24.dp)
            .rotate(rotation)

        Image(
            painter = painterResource(
                id = when (starState.value) {
                    StarState.Filled -> R.drawable.star_filled
                    StarState.Empty -> R.drawable.star_outline
                }
            ),
            contentDescription = "Star Icon",
            modifier = imageModifier
        )
        AnimatedVisibility(
            visible = starState.value == StarState.Filled,
            enter = fadeIn(animationSpec = tween(500)),
            exit = fadeOut(animationSpec = tween(500))
        ) {
            Image(
                painter = painterResource(id = R.drawable.star),
                contentDescription = null,
                modifier = imageModifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCountryList() {
    CountryList(
        countries = listOf(
            Country(
                name = CountryName(common = "United States of America"),
                capital = listOf("Washington, D.C."),
                population = 331449281,
                area = 9833520.0,
                flags = CountryFlags("")
            )
        ),
        onRefreshTap = {},
        onCountryRowTap = {},
        onAboutTap = {}
    )
}
