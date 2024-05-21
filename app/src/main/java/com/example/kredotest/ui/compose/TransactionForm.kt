package com.example.kredotest.ui.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kredotest.R
import com.example.kredotest.ui.data.Source
import com.example.kredotest.ui.theme.BackgroundBlue
import com.example.kredotest.ui.theme.KredoTestTheme
import com.example.kredotest.ui.theme.LightBlue
import com.example.kredotest.ui.theme.LightGray
import com.example.kredotest.ui.theme.Orange
import com.example.kredotest.ui.theme.PlaceholderBlue
import com.example.kredotest.ui.theme.fieldHintStyle
import com.example.kredotest.ui.theme.fieldTitleStyle
import com.example.kredotest.ui.theme.largePlaceHolder
import com.example.kredotest.ui.theme.mediumPlaceHolder
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TransactionForm(
    modifier: Modifier = Modifier,
    openDownSheet: () -> Unit = {},
    selectedSource: Source? = null
) {
    Box(
        modifier = Modifier
            .then(modifier)
            .background(color = Color.White, shape = RoundedCornerShape(20.dp))
    ) {
        val bringIntoViewRequester = remember { BringIntoViewRequester() }
        val coroutineScope = rememberCoroutineScope()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "З картки/рахунку", style = fieldTitleStyle)
            Spacer(modifier = Modifier.height(8.dp))

            if (selectedSource == null) {
                ChooseProductInput { openDownSheet() }
            } else {
                SelectedSource(
                    source = selectedSource,
                    openDownSheet = { openDownSheet() }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Сума", style = fieldTitleStyle)
            Spacer(modifier = Modifier.height(8.dp))
            var sumText by remember { mutableStateOf("") }
            SumInput(
                modifier = Modifier
                    .bringIntoViewRequester(bringIntoViewRequester)
                    .onFocusEvent {
                        if (it.isFocused) {
                            coroutineScope.launch {
                                bringIntoViewRequester.bringIntoView()
                            }
                        }
                    }, sumText
            ) { sumText = it }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Призначення", style = fieldTitleStyle)
            Spacer(modifier = Modifier.height(8.dp))
            var destinyText by remember { mutableStateOf("") }
            DestinyInput(
                modifier = Modifier
                    .bringIntoViewRequester(bringIntoViewRequester)
                    .onFocusEvent {
                        if (it.isFocused) {
                            coroutineScope.launch {
                                bringIntoViewRequester.bringIntoView()
                            }
                        }
                    }, destinyText
            ) { destinyText = it }
        }
    }
}

@Composable
fun ChooseProductInput(openDownSheet: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(12.dp))
            .background(LightBlue)
            .clickable { openDownSheet() }
    ) {
        Row() {
            Image(
                modifier = Modifier
                    .padding(10.dp, 8.dp, 10.dp, 8.dp)
                    .align(Alignment.CenterVertically)
                    .background(Color.Transparent),
                painter = painterResource(id = R.drawable.ic_card_placeholder),
                contentDescription = "card placeholder"
            )
            Text(
                modifier = Modifier
                    .padding(0.dp)
                    .align(Alignment.CenterVertically),
                text = "Оберіть продукт",
                style = fieldHintStyle
            )
        }
        Icon(
            modifier = Modifier
                .imePadding()
                .padding(end = 8.dp)
                .align(Alignment.CenterEnd),
            painter = painterResource(id = R.drawable.ic_arrow_down),
            contentDescription = "arrow down",
            tint = LightGray
        )
    }
}

@Composable
fun SumInput(modifier: Modifier = Modifier, messageText: String, onTextChange: (String) -> Unit) {
    TextField(
        textStyle = largePlaceHolder.copy(textAlign = TextAlign.Center),
        value = messageText, onValueChange = { onTextChange(it.take(9)) },
        modifier = Modifier
            .then(modifier)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(12.dp))
            .background(LightBlue),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = LightBlue,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedContainerColor = LightBlue
        ), maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        placeholder = {
            Text(
                "Ввести",
                style = largePlaceHolder.copy(textAlign = TextAlign.Center),
                color = PlaceholderBlue,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1
            )
        }
    )
}

@Composable
fun DestinyInput(modifier: Modifier, messageText: String, onTextChange: (String) -> Unit) {
    var border by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .then(modifier)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(12.dp))
            .background(LightBlue)
            .border(
                width = 1.dp,
                color = if (border) Orange else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Icon(
            painterResource(id = R.drawable.ic_chat),
            contentDescription = "icon",
            modifier = Modifier.padding(16.dp, 16.dp, 4.dp, 16.dp),
            tint = BackgroundBlue
        )
        TextField(
            value = messageText, onValueChange = { onTextChange(it) },
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterVertically)
                .onFocusChanged {
                    border = it.isFocused
                },
            placeholder = {
                Text(
                    "Призначення платежу",
                    color = PlaceholderBlue,
                    style = mediumPlaceHolder
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = LightBlue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedContainerColor = LightBlue
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TransactionPreview() {
    KredoTestTheme {
        Surface(
            color = BackgroundBlue
        ) {
            TransactionForm(modifier = Modifier.padding(8.dp))
        }
    }
}

