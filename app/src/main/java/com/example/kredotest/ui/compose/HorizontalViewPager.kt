package com.example.kredotest.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kredotest.ui.theme.BackgroundBlue
import com.example.kredotest.ui.theme.KredoTestTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalViewPager() {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 24.dp, 16.dp, 16.dp)
    ) {

        TabRow(
            selectedTabIndex = pagerState.currentPage,
            divider = { Spacer(modifier = Modifier.height(5.dp)) },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            listOf("Картки", "Рахунки").forEachIndexed { index, s ->
                Tab(selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = { Text(text = "Tab", color = BackgroundBlue) })
            }
        }
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp),
            count = 2,
            content = {
                when (it) {
                    0 -> Box(
                        modifier = Modifier
                            .background(Color.White)
                            .fillMaxWidth()
                            .height(80.dp)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Page: $it",
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }

                    else -> Text(text = "Empty page")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ViewPagerPreview() {
    KredoTestTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = BackgroundBlue
        ) {
            HorizontalViewPager()
        }
    }
}