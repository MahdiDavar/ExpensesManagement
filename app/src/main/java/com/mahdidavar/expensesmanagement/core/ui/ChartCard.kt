package com.mahdidavar.expensesmanagement.core.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mahdidavar.expensesmanagement.core.design.AppSpacing


@Composable
fun ChartCard(
    title: String,
    modifier: Modifier = Modifier,
    action: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit
) {
    AppCard(modifier = modifier) {
        SectionTitle(
            title = title,
            action = action
        )
        Spacer(Modifier.height(AppSpacing.MD))
        content()
    }
}

@Preview(showBackground = true)
@Composable
private fun ChartCardPreview() {
    ChartCard(
        "گزارشات"
    ) {
    }
}