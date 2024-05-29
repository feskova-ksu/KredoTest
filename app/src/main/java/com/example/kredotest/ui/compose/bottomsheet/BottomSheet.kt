package com.example.kredotest.ui.compose.bottomsheet

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.kredotest.ui.data.Source
import com.example.kredotest.ui.theme.BackgroundBlue
import com.example.kredotest.ui.theme.KredoTestTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    selectedSource: Source? = null,
    listOfCounts: List<Source.Count> = emptyList(),
    listOfCards: List<Source.Card> = emptyList(),
    onDismiss: () -> Unit = {},
    onSourceSelected: (Source?) -> Unit = {}
) {
    val modalBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ModalBottomSheet(
        modifier = Modifier.fillMaxHeight(0.6f),
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        containerColor = Color.White,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        SelectSourceViewPager(
            modifier = Modifier.fillMaxHeight(),
            listOfCounts = listOfCounts,
            listOfCards = listOfCards,
            selectedSource = selectedSource
        ) {
            onSourceSelected(it)
            onDismiss()
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun BottomSheetPreview() {
    KredoTestTheme {
        Surface(color = BackgroundBlue) {
            BottomSheet()
        }
    }
}