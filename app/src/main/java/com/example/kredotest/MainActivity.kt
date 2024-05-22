package com.example.kredotest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.kredotest.ui.compose.MainScreen
import com.example.kredotest.ui.compose.bottomsheet.BottomSheet
import com.example.kredotest.ui.theme.BackgroundBlue
import com.example.kredotest.ui.theme.KredoTestTheme


class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KredoTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BackgroundBlue
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .windowInsetsPadding(WindowInsets.systemBars)
                    ) {
                        //TODO:move all logic with data to ViewModel
                        var showSheet by remember { mutableStateOf(false) }
                        val listOfCounts by remember { mutableStateOf(viewModel.listOfCounts.value) }
                        val listOfCards by remember { mutableStateOf(viewModel.listOfCards.value) }
                        if (showSheet) {
                            BottomSheet(
                                selectedSource = viewModel.source,
                                listOfCounts = listOfCounts,
                                listOfCards = listOfCards,
                                onDismiss = { showSheet = false },
                                onSourceSelected = {
                                    it?.let { viewModel.onNewSource(it) }
                                }
                            )
                        }
                        MainScreen(
                            selectedSource = viewModel.source,
                            openDownSheet = { showSheet = true }
                        )
                    }
                }
            }
        }
    }
}
