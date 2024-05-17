package com.example.kredotest.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.kredotest.ui.theme.fieldHintStyle

@Preview(showBackground = false)
@Composable
fun FutureDateScreenPreview() {
    KredoTestTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = BackgroundBlue
        ) {
            FutureDateScreen()
        }
    }
}

@Composable
fun FutureDateScreen() {
    Card(
        modifier = Modifier
            //TODO:Replace static height
            .height(300.dp)
            .padding(0.dp, 4.dp, 0.dp, 0.dp),
        shape = RoundedCornerShape(
            CornerSize(30.dp),
            CornerSize(30.dp),
            CornerSize(0.dp),
            CornerSize(0.dp)
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = LightBlue
            )
        ) {
            var checked by remember { mutableStateOf(false) }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            ) {
                Text(
                    "Майбутня дата виконання", style = fieldHintStyle, modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(8.dp)
                )
                Switch(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(8.dp),
                    checked = checked,
                    onCheckedChange = { checked = it },
                    colors = SwitchDefaults.colors(

                    )
                )
            }
        }
    }
}