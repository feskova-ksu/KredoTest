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
import com.example.kredotest.ui.data.Source
import com.example.kredotest.ui.theme.BackgroundBlue
import com.example.kredotest.ui.theme.KredoTestTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.systemBars)
            ) {
                var showSheet by remember { mutableStateOf(false) }
                var selectedSource by remember { mutableStateOf<Source?>(null) }
                if (showSheet) {
                    BottomSheet(
                        selectedSource = selectedSource,
                        onDismiss = { showSheet = false },
                        onSourceSelected = { selectedSource = it }
                    )
                }
                KredoTestTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = BackgroundBlue
                    ) {
                        MainScreen(
                            selectedSource = selectedSource,
                            openDownSheet = { showSheet = true }
                        )
                    }
                }
            }
        }
    }
}
