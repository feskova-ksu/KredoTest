package com.example.kredotest.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kredotest.R
import com.example.kredotest.ui.data.Source
import com.example.kredotest.ui.data.mockCounts
import com.example.kredotest.ui.theme.BackgroundBlue
import com.example.kredotest.ui.theme.KredoTestTheme
import com.example.kredotest.ui.theme.LightBlue
import com.example.kredotest.ui.theme.LightGray
import com.example.kredotest.ui.theme.Orange
import com.example.kredotest.ui.theme.fieldHintStyle
import com.example.kredotest.ui.theme.smallBoldTitle
import com.example.kredotest.ui.theme.smallTextBlue

@Composable
fun SelectedSource(source: Source, openDownSheet: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = LightBlue, shape = RoundedCornerShape(12.dp))
            .clickable { openDownSheet() }
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.width(8.dp))
            when (source) {
                is Source.Card -> {
                    Image(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .align(Alignment.CenterVertically),
                        painter = painterResource(id = R.drawable.ic_active_card),
                        contentDescription = "card placeholder"
                    )
                }

                is Source.Count -> {
                    Box(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .height(40.dp)
                            .align(Alignment.CenterVertically)
                            .width(55.dp)
                            .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                    ) {
                        Text(
                            text = "₴",
                            style = smallBoldTitle.copy(color = Orange),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(
                    text = source.name,
                    style = fieldHintStyle
                )
                Text(
                    text = source.numbers,
                    style = smallTextBlue.copy(textAlign = TextAlign.Center, color = LightGray)
                )
            }
        }
        Row(
            modifier = Modifier.align(Alignment.CenterEnd),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "${source.amount} ₴",
                style = smallTextBlue
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                modifier = Modifier
                    .imePadding()
                    .align(Alignment.CenterVertically),
                painter = painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = "arrow down",
                tint = LightGray
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectedSourcePreview() {
    KredoTestTheme {
        Surface(
            color = BackgroundBlue
        ) {
            SelectedSource(source = mockCounts[0])
        }
    }
}