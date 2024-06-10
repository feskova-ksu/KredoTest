package com.example.kredotest.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kredotest.tools.dateFormat
import com.example.kredotest.ui.theme.BackgroundBlue
import com.example.kredotest.ui.theme.Blue
import com.example.kredotest.ui.theme.Orange
import com.example.kredotest.ui.theme.smallText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarPicker(
    modifier: Modifier = Modifier,
    selectedDate: String? = null,
    onConfirm: (Long?) -> Unit = {}
) {
    Column(modifier = Modifier.then(modifier)) {
        val dateState = if (!selectedDate.isNullOrEmpty()) {
            rememberDatePickerState(dateFormat.parse(selectedDate.replace(".","/",true))?.time)
        } else rememberDatePickerState()
        DatePicker(
            modifier = Modifier
                .align(Alignment.Start)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)
                )
                .clip(shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)),
            title = {
                Text(
                    style = smallText,
                    text = "Оберіть майбутню дату платежу",
                    modifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp)
                )
            },
            state = dateState,
            showModeToggle = false,
            colors = DatePickerDefaults.colors(
                selectedDayContainerColor = Orange,
                yearContentColor = Blue,
                selectedDayContentColor = Color.White,
                todayContentColor = Orange,
                todayDateBorderColor = Orange,
                dayContentColor = Blue,
                weekdayContentColor = Blue,
            )
        )
        Box(
            modifier = Modifier
                .align(Alignment.End)
                .weight(1f)
                .background(color = Color.White)
        ) {

            OrangeButton(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp, bottom = 24.dp)
                    .align(Alignment.BottomCenter),
                text = "Підтвердити",
                onClick = { onConfirm(dateState.selectedDateMillis) }
            )
        }
    }
}

@Preview
@Composable
fun CalendarPickerPreview() {
    Surface(color = BackgroundBlue) {
        CalendarPicker(modifier = Modifier.fillMaxHeight())
    }
}