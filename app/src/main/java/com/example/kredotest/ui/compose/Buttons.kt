package com.example.kredotest.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kredotest.ui.theme.BackgroundBlue
import com.example.kredotest.ui.theme.KredoTestTheme
import com.example.kredotest.ui.theme.LightBlue
import com.example.kredotest.ui.theme.LightOrange
import com.example.kredotest.ui.theme.Orange
import com.example.kredotest.ui.theme.White50


@Composable
fun OrangeButton(
    modifier: Modifier = Modifier,
    isButtonEnabled: Boolean = true,
    text: String = "",
    onClick: () -> Unit = {}
) {
    Button(
        modifier = Modifier
            .then(modifier)
            .background(
                brush = Brush.linearGradient(colors = listOf(Orange, LightOrange)),
                shape = RoundedCornerShape(12.dp)
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

@Composable
fun GrayButton(
    modifier: Modifier = Modifier,
    isButtonEnabled: Boolean = true,
    text: String = "",
    onClick: () -> Unit = {}
) {
    Button(
        modifier = Modifier
            .then(modifier)
            .background(color = LightBlue, shape = RoundedCornerShape(12.dp))
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            contentColor = Orange,
            disabledContentColor = Orange
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

@Preview
@Composable
private fun OrangeButtonPreview() {
    KredoTestTheme {
        Surface() {
            OrangeButton(text = "Помаранчева кнопка")
        }
    }
}

@Preview
@Composable
private fun OrangeButtonDissabledPreview() {
    KredoTestTheme {
        Surface() {
            OrangeButton(text = "Помаранчева кнопка", isButtonEnabled = false)
        }
    }
}


@Preview
@Composable
private fun GrayButtonPreview() {
    KredoTestTheme {
        Surface(color = BackgroundBlue) {
            GrayButton(text = "Сiра кнопка")
        }
    }
}