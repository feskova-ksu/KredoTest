package com.example.kredotest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.kredotest.ui.compose.BottomSheet
import com.example.kredotest.ui.compose.MainScreen
import com.example.kredotest.ui.theme.BackgroundBlue
import com.example.kredotest.ui.theme.KredoTestTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var showSheet by remember { mutableStateOf(false) }
            if (showSheet) {
                Log.d("MainActivity", "Bottom sheet visible")
                BottomSheet() {
                    showSheet = false
                }
            }
            KredoTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BackgroundBlue
                ) {
                    MainScreen() {
                        showSheet = true
                    }
                }
            }
        }
    }
}
