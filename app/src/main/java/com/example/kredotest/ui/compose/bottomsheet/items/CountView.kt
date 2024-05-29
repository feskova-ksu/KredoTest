package com.example.kredotest.ui.compose.bottomsheet.items

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kredotest.R
import com.example.kredotest.tools.mapAmount
import com.example.kredotest.tools.mapToCountFormat
import com.example.kredotest.ui.data.Source
import com.example.kredotest.ui.data.mockCounts
import com.example.kredotest.ui.theme.BackgroundBlue
import com.example.kredotest.ui.theme.KredoTestTheme
import com.example.kredotest.ui.theme.LightBlue
import com.example.kredotest.ui.theme.LightGray
import com.example.kredotest.ui.theme.Orange
import com.example.kredotest.ui.theme.fieldHintStyle
import com.example.kredotest.ui.theme.smallBoldTitle
import com.example.kredotest.ui.theme.smallTextBlue

@Composable
fun CountViewSelectable(
    count: Source.Count = Source.Count(),
    onChooseClick: (Source.Count) -> Unit = {}
) {
    CountView(
        count = count,
        onChooseClick = onChooseClick,
        isSelectable = true,
        isArrowDownVisible = false
    )
}

@Composable
fun CountViewWithArrow(
    count: Source.Count = Source.Count(),
    onChooseClick: (Source.Count) -> Unit = {}
) {
    CountView(
        count = count,
        onChooseClick = onChooseClick,
        isSelectable = false,
        isArrowDownVisible = true
    )
}


@Composable
private fun CountView(
    count: Source.Count = Source.Count(),
    isSelectable: Boolean,
    isArrowDownVisible: Boolean,
    onChooseClick: (Source.Count) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(12.dp))
            .background(color = LightBlue)
            .clickable {
                Log.e("CardView", "choose product selected")
                onChooseClick(count)
            }
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .height(40.dp)
                    .align(Alignment.CenterVertically)
                    .width(55.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(8.dp))
            ) {
                Text(
                    text = "₴",
                    style = smallBoldTitle.copy(color = Orange),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(
                    text = count.name,
                    style = fieldHintStyle
                )
                Text(
                    text = count.numbers.mapToCountFormat(),
                    style = smallTextBlue.copy(textAlign = TextAlign.Center, color = LightGray)
                )
            }
        }
        Row(
            modifier = Modifier.align(Alignment.CenterEnd),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = count.amount.toString().mapAmount(),
                style = smallTextBlue
            )

            Spacer(modifier = Modifier.width(16.dp))

            if (isSelectable) {
                Icon(
                    modifier = Modifier.padding(end = 16.dp),
                    painter = painterResource(id = R.drawable.ic_mark),
                    contentDescription = "mark icon",
                    tint = if (count.isSelected) Orange else LightBlue
                )
            }
            if (isArrowDownVisible) {
                Icon(
                    modifier = Modifier.padding(end = 16.dp),
                    painter = painterResource(id = R.drawable.ic_arrow_down),
                    contentDescription = "arrow down",
                    tint = LightGray
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun CountViewSelectablePreview() {
    KredoTestTheme {
        Surface(color = BackgroundBlue) {
            CountViewSelectable(
                mockCounts[0].copy(isSelected = true),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CountViewWithArrowPreview() {
    KredoTestTheme {
        Surface(color = BackgroundBlue) {
            CountViewWithArrow(
                mockCounts[0].copy(isSelected = true),
            )
        }
    }
}