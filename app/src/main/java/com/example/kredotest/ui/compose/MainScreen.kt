package com.example.kredotest.ui.compose

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kredotest.ui.data.FormData
import com.example.kredotest.MainViewModel
import com.example.kredotest.ui.compose.bottomsheet.BottomSheet
import com.example.kredotest.ui.data.Source
import com.example.kredotest.ui.theme.BackgroundBlue
import com.example.kredotest.ui.theme.KredoTestTheme


@Composable
fun MainScreen(
    viewModel: MainViewModel = MainViewModel(),
    onNavigateToCalendar: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        val showSheet = remember { mutableStateOf(false) }
        if (showSheet.value) {
            BottomSheet(
                selectedSource = uiState.source,
                listOfCounts = uiState.counts,
                listOfCards = uiState.cards,
                onDismiss = { showSheet.value = false },
                onSourceSelected = { it?.let { viewModel.onNewSource(it) } }
            )
        }
        Column(modifier = Modifier.fillMaxSize()) {
            TopToolbar(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(horizontal = 4.dp)
                    .padding(bottom = 12.dp),
                title = "Переказ в межах банку",
                subTitle = "Андрій Б. •• 2088"
            )
            FormsColumn(
                modifier = Modifier.weight(1f),
                openDownSheet = { showSheet.value = true },
                formData = uiState.form,
                selectedSource = uiState.source,
                selectedDate = uiState.selectedDate,
                switchState = uiState.switchState,
                onSwitchChange = viewModel::onSwitchChanged,
                onAmountChanged = viewModel::onAmountChanged,
                onPurposeChanged = viewModel::onPurposeChanged,
                onCalendarClick = onNavigateToCalendar
            )

            Box(
                modifier = Modifier
                    .align(Alignment.End)
                    .imePadding()
                    .background(Color.White)
            ) {
                OrangeButton(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp, bottom = 24.dp)
                        .align(Alignment.BottomCenter),
                    isButtonEnabled = uiState.isActionButtonEnabled,
                    text = "Переказати",
                    onClick = {
                        Toast.makeText(context, viewModel.getAllData(), Toast.LENGTH_LONG)
                            .show()
                    }
                )
            }
        }

    }
}


@Composable
fun FormsColumn(
    modifier: Modifier = Modifier,
    formData: FormData = FormData(),
    selectedSource: Source? = null,
    selectedDate: String = "",
    switchState: Boolean = false,
    onSwitchChange: (Boolean) -> Unit = {},
    openDownSheet: () -> Unit = {},
    onAmountChanged: (String) -> Unit = {},
    onPurposeChanged: (String) -> Unit = {},
    onCalendarClick: () -> Unit = {}
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .then(modifier)
            .clip(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .verticalScroll(scrollState)
            .imePadding()
    ) {
        TransactionForm(
            openDownSheet = openDownSheet,
            formData = formData,
            selectedSource = selectedSource,
            onAmountChanged = onAmountChanged,
            onPurposeChanged = onPurposeChanged
        )
        Spacer(modifier = Modifier.height(4.dp))
        FutureDateForm(
            modifier = modifier,
            checked = switchState,
            onSwitchChange = onSwitchChange,
            selectedDate = selectedDate,
            onCalendarClick = onCalendarClick
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