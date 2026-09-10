package com.mahdidavar.expensesmanagement.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mahdidavar.expensesmanagement.core.design.AppElevation
import com.mahdidavar.expensesmanagement.core.design.AppShapes
import com.mahdidavar.expensesmanagement.core.design.AppSpacing

@Composable
fun AppCard (
    modifier: Modifier = Modifier,
    contentPadding : PaddingValues = PaddingValues(AppSpacing.MD),
    content : @Composable ColumnScope.()-> Unit
) {
    ElevatedCard(
        modifier = modifier ,
        shape = AppShapes.Medium ,
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = AppElevation.Small),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ){
        Column (
            modifier = Modifier.fillMaxWidth().padding(contentPadding) ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment =Alignment.CenterHorizontally,
            content = content
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AppCardPreview() {
AppCard {
    Text("Preview")
}
}