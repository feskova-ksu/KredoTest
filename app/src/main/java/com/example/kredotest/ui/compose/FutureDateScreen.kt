package com.example.kredotest.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kredotest.ui.theme.BackgroundBlue
import com.example.kredotest.ui.theme.KredoTestTheme
import com.example.kredotest.ui.theme.LightBlue
import com.example.kredotest.ui.theme.LightGray
import com.example.kredotest.ui.theme.Orange
import com.example.kredotest.ui.theme.fieldHintStyle

@Composable
fun FutureDateForm(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .then(modifier)
            .background(
                color = Color.White,
                RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            )
    ) {

        var checked by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .background(color = LightBlue, shape = RoundedCornerShape(12.dp))
        ) {
            Text(
                "Майбутня дата виконання",
                style = fieldHintStyle,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .padding(vertical = 21.dp)
                    .align(Alignment.CenterStart)
            )
            Switch(
                modifier = Modifier
                    .width(52.dp)
                    .height(32.dp)
                    .padding(end = 32.dp)
                    .align(Alignment.CenterEnd),
                checked = checked,
                onCheckedChange = { checked = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Orange,
                    uncheckedThumbColor = LightGray,
                    uncheckedTrackColor = Color.White,
                    uncheckedBorderColor = LightGray
                )
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun FutureDateScreenPreview() {
    KredoTestTheme {
        Surface(
            color = BackgroundBlue
        ) {
            FutureDateForm(modifier = Modifier.padding(8.dp))
        }
    }
}
