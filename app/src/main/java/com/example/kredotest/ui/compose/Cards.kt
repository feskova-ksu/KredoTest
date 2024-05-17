package com.example.kredotest.ui.compose

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kredotest.ui.theme.LightBlue

@Composable
fun WhiteCard(
    modifier: Modifier, content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(CornerSize(30.dp),),
        colors = CardDefaults.cardColors(containerColor = Color.White,),
        content = content
    )
}

@Composable
fun LightBlueCard(
    modifier: Modifier, content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(CornerSize(12.dp)),
        colors = CardDefaults.cardColors(containerColor = LightBlue),
        content = content
    )
}