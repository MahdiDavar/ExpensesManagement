package com.mahdidavar.expensesmanagement.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.himanshoe.charty.color.ChartyColor
import com.himanshoe.charty.color.ChartyColors
import com.himanshoe.charty.common.config.Animation
import com.himanshoe.charty.common.config.ChartScaffoldConfig
import com.himanshoe.charty.line.LineChart
import com.himanshoe.charty.line.config.LineChartConfig
import com.himanshoe.charty.line.data.LineData
import com.mahdidavar.expensesmanagement.navigation.BottomNavigation
import com.mahdidavar.expensesmanagement.presentation.component.StatisticScreen

@Composable
fun ReportScreen(
    navController: NavHostController
) {
    Box {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StatisticScreen()
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 30.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        BottomNavigation(navController)
    }
}

@Composable
fun ChartView() {
    LineChart(
        data = {
            listOf(
                LineData("شنبه", 150f),
                LineData("یکشنبه", 200f),
                LineData("دوشنبه", 350f),
                LineData("سه شنبه", 100f),
                LineData("چهارشنبه", 75f),
                LineData("پنجشنبه", 600f),
                LineData("جمعه", 130f)
            )
        },
        color = ChartyColor.Solid(ChartyColors.Red),
        lineConfig = LineChartConfig(
            lineWidth = 5f,
            showPoints = true,
            smoothCurve = false,
            animation = Animation.Slow
        ),
        scaffoldConfig = ChartScaffoldConfig(
            labelTextStyle = TextStyle(fontSize = 10.sp)
        ),
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(0.8f)
    )
}
