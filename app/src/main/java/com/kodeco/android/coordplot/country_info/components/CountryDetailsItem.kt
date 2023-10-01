package com.kodeco.android.coordplot.country_info.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DetailItem(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "$label: $value")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailItem() {
    DetailItem(label = "Label", value = "Value")
}