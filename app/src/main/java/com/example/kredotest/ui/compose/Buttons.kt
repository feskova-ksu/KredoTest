package com.example.kredotest.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kredotest.ui.theme.White50


@Composable
fun OrangeButton(modifier: Modifier, isButtonEnabled: Boolean, text: String, onClick: () -> Unit) {
    Button(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFFF7A2F),
                        Color(0xFFF59910)
                    )
                )
            )
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            contentColor = Color.White,
            disabledContentColor = White50
        ),
        shape = RoundedCornerShape(12.dp),
        enabled = isButtonEnabled,
        onClick = { onClick() }) {

        Text(
            text = text, modifier = Modifier
                .padding(8.dp)
        )
    }
}