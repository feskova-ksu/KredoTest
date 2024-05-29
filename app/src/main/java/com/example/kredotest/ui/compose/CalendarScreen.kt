package com.example.kredotest.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kredotest.MainViewModel

@Composable
fun CalendarScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = MainViewModel(),
    onBackClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)
            .then(modifier)
    ) {
        TopToolbar(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 4.dp)
                .padding(bottom = 12.dp),
            title = "Майбутня дата",
            onArrowClick = onBackClick
        )

        CalendarPicker(
            modifier = Modifier.weight(1f),
            selectedDate = uiState.selectedDate,
            onConfirm = {
                viewModel.saveDate(it)
                onBackClick()
            })
    }
}

@Preview
@Composable
fun CalendarScreenPreview() {
    Surface {
        CalendarScreen(modifier = Modifier.fillMaxSize())
    }
}