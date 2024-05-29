package com.example.kredotest.ui.compose.bottomsheet.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kredotest.ui.compose.bottomsheet.items.CardViewSelectable
import com.example.kredotest.ui.data.Source
import com.example.kredotest.ui.data.mockCards
import com.example.kredotest.ui.theme.KredoTestTheme


@Composable
fun SelectCardScreen(
    modifier: Modifier = Modifier,
    selectedSource: Source? = null,
    cardsList: List<Source.Card> = emptyList(),
    onNewSelected: (Source?) -> Unit = {}
) {
    Column(modifier = Modifier.then(modifier)) {
        var usedMockCards by remember { mutableStateOf(cardsList) }
        var newSelectedSource by remember { mutableStateOf(selectedSource) }
        if (newSelectedSource is Source.Card) {
            usedMockCards =
                usedMockCards.map { it.copy(isSelected = it.id == (newSelectedSource as Source.Card).id) }
        }
        if (newSelectedSource is Source.Count) {
            usedMockCards = usedMockCards.map { it.copy(isSelected = false) }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            reverseLayout = false,
        ) {
            items(usedMockCards) {
                CardViewSelectable(it, onChooseClick = { selected ->
                    usedMockCards = usedMockCards.map { it.copy(isSelected = it.id == selected.id) }
                    newSelectedSource = selected
                    onNewSelected(newSelectedSource)
                })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectCardPreview() {
    KredoTestTheme {
        Surface(color = Color.White) {
            SelectCardScreen(modifier = Modifier.padding(8.dp), cardsList = mockCards)
        }
    }
}
