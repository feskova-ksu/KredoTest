package com.example.kredotest.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kredotest.ui.theme.BackgroundBlue
import com.example.kredotest.ui.theme.KredoTestTheme
import com.example.kredotest.ui.theme.White50
import com.example.kredotest.ui.theme.smallBoldTitle

@Composable
fun TopToolbar(modifier: Modifier = Modifier, title:String = "", subTitle: String ="", onArrowClick:()->Unit = {}) {
    Box(
        modifier = Modifier
            .then(modifier)
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(0.dp, 16.dp, 0.dp, 0.dp)
    ) {
        Icon(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterStart).clickable { onArrowClick() },
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "arrow back",
            tint = Color.White
        )
        Text(
            modifier = Modifier.align(Alignment.Center),
            style = smallBoldTitle,
            text = title
        )
        Text(
            modifier = Modifier.align(Alignment.BottomCenter),
            color = White50,
            style = smallBoldTitle,
            text = subTitle
        )
    }
}

@Preview(showBackground = false)
@Composable
private fun TopToolbarPreview() {
    KredoTestTheme {
        Surface(
            color = BackgroundBlue
        ) {
            TopToolbar(Modifier.fillMaxWidth())
        }
    }
}