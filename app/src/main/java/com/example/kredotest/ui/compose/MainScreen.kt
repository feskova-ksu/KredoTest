package com.example.kredotest.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kredotest.ui.theme.BackgroundBlue
import com.example.kredotest.ui.theme.KredoTestTheme


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    KredoTestTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = BackgroundBlue
        ) {
            MainScreen(){}
        }
    }
}

@Composable
fun MainScreen(onChooseProduct: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        val scrollState = rememberScrollState()
        Column {
            TopToolbar(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(4.dp, 4.dp, 4.dp, 48.dp)
            )
            WhiteCard(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .background(BackgroundBlue)
                        .fillMaxSize()
                        .verticalScroll(scrollState)

                ) {
                    TransactionScreen(onChooseProduct)
                    FutureDateScreen()
                }
            }
        }

        var isButtonEnabled by remember { mutableStateOf(false) }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(Color.White)
        ) {
            OrangeButton(
                modifier = Modifier
                    .padding(16.dp, 16.dp, 16.dp, 24.dp)
                    .align(Alignment.BottomCenter),
                isButtonEnabled,
                "Переказати"
            )
            {
                //Do nothing
            }
        }
    }
}