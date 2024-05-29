package com.example.kredotest.ui.compose.bottomsheet

import android.annotation.SuppressLint
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.kredotest.ui.compose.GrayButton
import com.example.kredotest.ui.compose.OrangeButton
import com.example.kredotest.ui.compose.bottomsheet.pages.CardsPage
import com.example.kredotest.ui.compose.bottomsheet.pages.CountsPage
import com.example.kredotest.ui.data.Source
import com.example.kredotest.ui.data.mockCards
import com.example.kredotest.ui.data.mockCounts
import com.example.kredotest.ui.theme.BackgroundBlue
import com.example.kredotest.ui.theme.KredoTestTheme
import com.example.kredotest.ui.theme.Ocean
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SelectSourceViewPager(
    modifier: Modifier = Modifier,
    selectedSource: Source? = null,
    listOfCards: List<Source.Card> = emptyList(),
    listOfCounts: List<Source.Count> = emptyList(),
    onNewSelected: (Source?) -> Unit = {}
) {
    var newSelected = selectedSource
    val page = if (selectedSource is Source.Count) 1 else 0
    val pagerState = rememberPagerState(initialPage = page) { 2 }
    val coroutineScope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .then(modifier)
    ) {

        Column(modifier = Modifier.align(Alignment.TopCenter)) {
            TabRowView(modifier = Modifier.padding(horizontal = 16.dp), pagerState, coroutineScope)
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalPager(
                modifier = Modifier.weight(1f, fill = true),
                state = pagerState,
                beyondBoundsPageCount = 1
            ) {
                when (it) {
                    0 -> CardsPage(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxHeight(),
                        cards = listOfCards,
                        selectedSource = selectedSource
                    ) { newSelected = it }

                    1 -> CountsPage(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxHeight(),
                        counts = listOfCounts,
                        selectedSource = selectedSource
                    ) { newSelected = it }

                    else -> Text(text = "Empty page")
                }
            }
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.End)
                    .padding(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (pagerState.currentPage == 0) {
                    GrayButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        text = "Відкрити нову картку"
                    )
                }
                if ((pagerState.currentPage == 1 && listOfCounts.isNotEmpty()) || listOfCards.isNotEmpty())
                    OrangeButton(
                        onClick = {
                            if (newSelected == null) {
                                newSelected =
                                    if (pagerState.currentPage == 0) mockCards[0] else mockCounts[0]
                            }
                            onNewSelected(newSelected)
                        },
                        modifier = Modifier
                            .align(Alignment.End),
                        text = "Підтвердити"
                    )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabRowView(
    modifier: Modifier = Modifier,
    pagerState: PagerState? = null,
    coroutineScope: CoroutineScope? = null
) {
    if (pagerState == null) return
    val indicator = @Composable { tabPositions: List<TabPosition> ->
        CustomIndicator(tabPositions, pagerState)
    }
    var offsetX by remember { mutableFloatStateOf(0f) }
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        divider = { Spacer(modifier = Modifier.height(0.dp)) },
        modifier = Modifier
            .then(modifier)
            .background(BackgroundBlue, RoundedCornerShape(12.dp))
            .padding(4.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .draggable(
                orientation = Orientation.Horizontal,
                state = rememberDraggableState { delta ->
                    offsetX += delta
                },
                onDragStopped = {
                    coroutineScope?.launch {
                        val next = if (pagerState.currentPage == 0) 1 else 0
                        pagerState.animateScrollToPage(next)
                    }
                    offsetX = 0f
                }
            ),
        indicator = indicator,
        containerColor = BackgroundBlue
    ) {
        listOf("Картки", "Рахунки").forEachIndexed { index, title ->
            Tab(
                modifier = Modifier.zIndex(6f),
                text = {
                    Text(
                        text = title,
                        color = if (index == pagerState.currentPage) BackgroundBlue else Ocean
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    coroutineScope?.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CustomIndicator(tabPositions: List<TabPosition>, pagerState: PagerState) {
    val transition = updateTransition(pagerState.currentPage, label = "")
    val indicatorStart by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = Spring.DampingRatioLowBouncy, stiffness = Spring.StiffnessLow)
            } else {
                spring(dampingRatio = Spring.DampingRatioLowBouncy, stiffness = Spring.StiffnessLow)
            }
        }, label = ""
    ) {
        tabPositions[it].left
    }

    val indicatorEnd by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = Spring.DampingRatioLowBouncy, stiffness = Spring.StiffnessLow)
            } else {
                spring(dampingRatio = Spring.DampingRatioLowBouncy, stiffness = Spring.StiffnessLow)
            }
        }, label = ""
    ) {
        tabPositions[it].right
    }

    Box(
        Modifier
            .offset(x = indicatorStart)
            .wrapContentSize(align = Alignment.BottomStart)
            .width(indicatorEnd - indicatorStart)
            .padding(2.dp)
            .fillMaxSize()
            .background(
                color = Color.White,
                RoundedCornerShape(8.dp)
            )
            .zIndex(1f)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
private fun TabRowPreview() {
    KredoTestTheme {
        Surface() {
            TabRowView()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ViewPagerPreview() {
    KredoTestTheme {
        Surface(modifier = Modifier.fillMaxHeight(0.8f)) {
            SelectSourceViewPager(modifier = Modifier.fillMaxHeight())
        }
    }
}
