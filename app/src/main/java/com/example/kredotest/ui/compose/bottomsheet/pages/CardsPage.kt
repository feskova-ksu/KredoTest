package com.example.kredotest.ui.compose.bottomsheet.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kredotest.ui.data.Source
import com.example.kredotest.ui.data.mockCards
import com.example.kredotest.ui.theme.KredoTestTheme
import com.example.kredotest.ui.theme.smallText

@Composable
fun CardsPage(
    modifier: Modifier = Modifier,
    cards: List<Source.Card> = emptyList(),
    selectedSource: Source? = null,
    onNewSelected: (Source?) -> Unit = {}
) {
    if (cards.isEmpty()) {
        EmptyCardsScreen(modifier = modifier)
    } else {
        SelectCardScreen(
            selectedSource = selectedSource,
            cardsList = cards,
            onNewSelected = onNewSelected
        )
    }

}

@Composable
fun EmptyCardsScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .then(modifier)
            .padding(bottom = 56.dp)
    ) {
        Text(
            text = "У вас відсутня картка для здійснення операції",
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 90.dp),
            style = smallText.copy(textAlign = TextAlign.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CardsScreenEmptyPreview() {
    KredoTestTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
        ) {
            CardsPage()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardsScreenPreview() {
    KredoTestTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
        ) {
            CardsPage(cards = mockCards)
        }
    }
}