package com.example.kredotest.ui.compose.bottomsheet.items

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kredotest.R
import com.example.kredotest.tools.mapAmount
import com.example.kredotest.tools.mapToCardFormat
import com.example.kredotest.ui.data.Source
import com.example.kredotest.ui.data.mockCards
import com.example.kredotest.ui.theme.BackgroundBlue
import com.example.kredotest.ui.theme.KredoTestTheme
import com.example.kredotest.ui.theme.LightBlue
import com.example.kredotest.ui.theme.LightGray
import com.example.kredotest.ui.theme.Orange
import com.example.kredotest.ui.theme.fieldHintStyle
import com.example.kredotest.ui.theme.smallTextBlue

@Composable
fun CardView(card: Source.Card = Source.Card(), onChooseClick: (Source.Card) -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = LightBlue, shape = RoundedCornerShape(12.dp))
            .clickable {
                Log.e("CardView", "choose product selected")
                onChooseClick(card)
            }
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .align(Alignment.CenterVertically),
                painter = painterResource(id = R.drawable.ic_active_card),
                contentDescription = "card placeholder"
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(
                    text = card.name,
                    style = fieldHintStyle
                )
                Text(
                    text = card.numbers.mapToCardFormat(),
                    style = smallTextBlue.copy(textAlign = TextAlign.Center, color = LightGray)
                )
            }
        }
        Row(
            modifier = Modifier.align(Alignment.CenterEnd),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = card.amount.toString().mapAmount(),
                style = smallTextBlue
            )

            Spacer(modifier = Modifier.width(16.dp))

            Icon(
                modifier = Modifier.padding(end = 8.dp),
                painter = painterResource(id = R.drawable.ic_mark),
                contentDescription = "arrow down",
                tint = if (card.isSelected) Orange else LightBlue
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardViewPreview() {
    KredoTestTheme {
        Surface(color = BackgroundBlue) {
            CardView(mockCards[0])
        }
    }
}