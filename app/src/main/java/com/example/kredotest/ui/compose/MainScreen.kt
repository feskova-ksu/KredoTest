package com.example.kredotest.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kredotest.ui.data.Source
import com.example.kredotest.ui.theme.BackgroundBlue
import com.example.kredotest.ui.theme.KredoTestTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun MainScreen(
    selectedSource: StateFlow<Source?> = MutableStateFlow(null).asStateFlow(),
    openDownSheet: () -> Unit = {}
) {
    Box(modifier = Modifier.fillMaxSize()) {
        var isSubmitEnable by remember { mutableStateOf(false) }

        Column {
            TopToolbar(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(horizontal = 4.dp)
                    .padding(top = 4.dp, bottom = 12.dp),
            )
            FormsColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .imePadding(),
                openDownSheet = openDownSheet,
                selectedSource = selectedSource
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(Color.White)
        ) {
            OrangeButton(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp, bottom = 24.dp)
                    .align(Alignment.BottomCenter)
                    .imePadding(),
                isButtonEnabled = isSubmitEnable,
                text = "Переказати",
                onClick =
                {
                    //Do nothing
                }
            )
        }
    }
}


@Composable
fun FormsColumn(
    modifier: Modifier = Modifier,
    selectedSource: StateFlow<Source?> = MutableStateFlow(null).asStateFlow(),
    openDownSheet: () -> Unit = {}
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .then(modifier)
            .verticalScroll(scrollState)
            .clip(shape = RoundedCornerShape(20.dp))
    ) {
        TransactionForm(
            openDownSheet = openDownSheet,
            modifier = Modifier.imePadding(),
            selectedSource = selectedSource
        )
        Spacer(modifier = Modifier.height(4.dp))
        FutureDateForm(
            modifier = Modifier
                .weight(1f, fill = true)
                .imePadding()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    KredoTestTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = BackgroundBlue
        ) {
            MainScreen()
        }
    }
}