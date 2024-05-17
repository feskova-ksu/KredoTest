package com.example.kredotest.ui.compose

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kredotest.R
import com.example.kredotest.ui.theme.BackgroundBlue
import com.example.kredotest.ui.theme.KredoTestTheme
import com.example.kredotest.ui.theme.LightBlue
import com.example.kredotest.ui.theme.PlaceholderBlue
import com.example.kredotest.ui.theme.fieldHintStyle
import com.example.kredotest.ui.theme.fieldTitleStyle
import com.example.kredotest.ui.theme.largePlaceHolder
import com.example.kredotest.ui.theme.mediumPlaceHolder


@Preview(showBackground = true)
@Composable
fun TransactionPreview() {
    KredoTestTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = BackgroundBlue
        ) {
            TransactionScreen() {}
        }
    }
}

//click on "Choose product"
//input amount of money
//add comment
@Composable
fun TransactionScreen(
    onChooseClick: () -> Unit
) {
    WhiteCard(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 24.dp, 16.dp, 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "З картки/рахунку", style = fieldTitleStyle)
            ChooseProductInput(onChooseClick)
            Text(text = "Сума", style = fieldTitleStyle)
            var sumText by remember { mutableStateOf("") }
            SumInput(sumText) { sumText = it }
            Text(text = "Призначення", style = fieldTitleStyle)
            var destinyText by remember { mutableStateOf("") }
            DestinyInput(destinyText) { destinyText = it }
        }
    }
}

@Composable
fun ChooseProductInput(onChooseClick: () -> Unit) {
    LightBlueCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 8.dp, 0.dp, 24.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    Log.e("TransactionScreen", "choose product selected")
                    onChooseClick()
                }
                .align(Alignment.CenterHorizontally)
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
            IconButton(modifier = Modifier
                .padding(0.dp)
                .align(Alignment.CenterEnd), onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "arrow down"
                )
            }
        }
    }
}

@Composable
fun SumInput(messageText: String, onTextChange: (String) -> Unit) {
    LightBlueCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 8.dp, 0.dp, 24.dp)
    ) {
        TextField(
            textStyle = largePlaceHolder.copy(textAlign = TextAlign.Center),
            value = messageText, onValueChange = { onTextChange(it) },
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterHorizontally),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = LightBlue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            placeholder = {
                Text(
                    "Ввести",
                    style = largePlaceHolder.copy(textAlign = TextAlign.Center),
                    color = PlaceholderBlue,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        )
    }
}

@Composable
fun DestinyInput(messageText: String, onTextChange: (String) -> Unit) {
    LightBlueCard(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .height(80.dp)
            .padding(0.dp, 8.dp, 0.dp, 8.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Icon(
                painterResource(id = R.drawable.ic_chat),
                contentDescription = "icon",
                modifier = Modifier
                    .align(
                        Alignment.CenterVertically
                    )
                    .padding(16.dp, 16.dp, 4.dp, 16.dp)
            )
            TextField(
                value = messageText, onValueChange = { onTextChange(it) },
                modifier = Modifier
                    .wrapContentSize()
                    .align(
                        Alignment.CenterVertically
                    ),
                placeholder = {
                    Text(
                        "Призначення платежу",
                        color = PlaceholderBlue,
                        style = mediumPlaceHolder
                    )
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = LightBlue,
                    focusedContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
        }
    }
}