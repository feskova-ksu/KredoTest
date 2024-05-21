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
import com.example.kredotest.ui.data.mockCounts
import com.example.kredotest.ui.theme.KredoTestTheme
import com.example.kredotest.ui.theme.smallText

@Composable
fun CountsPage(
    modifier: Modifier = Modifier,
    counts: List<Source.Count> = emptyList(),
    selectedSource: Source? = null,
    onNewSelected: (Source?) -> Unit = {}
) {
    if (counts.isEmpty()) {
        EmptyCountsScreen(modifier)
    } else {
        SelectCountsScreen(
            selectedSource = selectedSource,
            countsList = counts,
            onNewSelected = onNewSelected
        )
    }

}

@Composable
fun EmptyCountsScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .then(modifier)
            .padding(bottom = 56.dp)
    ) {
        Text(
            text = "У вас відсутній рахунок для здійснення операції",
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 90.dp),
            style = smallText.copy(textAlign = TextAlign.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CountsScreenEmptyPreview() {
    KredoTestTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
        ) {
            CountsPage()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CountsPagePreview() {
    KredoTestTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
        ) {
            CountsPage(counts = mockCounts)
        }
    }
}