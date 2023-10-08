package com.kodeco.android.coordplot.country_info.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kodeco.android.coordplot.country_info.model.Country
import com.kodeco.android.coordplot.country_info.model.CountryFlags
import com.kodeco.android.coordplot.country_info.model.CountryName

@Composable
fun CountryList(
    countries: List<Country>,
    navigation: NavController?,
    countersTopBar: @Composable () -> Unit
) {
    Column {
        countersTopBar()
        LazyColumn {
            items(countries.size) { index ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 8.dp,
                            vertical = 8.dp
                        )
                        .clickable { navigation?.navigate("country_details/$index") },
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Column(modifier = Modifier.padding(all = 8.dp)) {
                        Text(text = "Name: ${countries[index].name.common}")
                        Text(text = "Capital: ${countries[index].capital?.get(0).toString()}")
                    }
                }
            }
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
        ), navigation = null,
        countersTopBar = {
            CountersTopBar(taps = 0, backs = 0, onRefreshClick = { })
        }
    )
}
