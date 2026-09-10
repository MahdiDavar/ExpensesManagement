package com.mahdidavar.expensesmanagement.core.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.mahdidavar.expensesmanagement.core.design.AppIcons
import com.mahdidavar.expensesmanagement.core.design.AppSpacing
import com.mahdidavar.expensesmanagement.presentation.model.StatCardModel

@Composable
fun StatCard(
   model: StatCardModel ,
    modifier: Modifier = Modifier,
) {
    AppCard(modifier = modifier) {
        Icon(
            imageVector = model.icon,
            contentDescription = null,
            tint = model.iconColor ,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(AppSpacing.SM))
        Text(
            text = model.value?:"",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )
        Spacer(Modifier.height(AppSpacing.XS))
        Text(
            text = model.title,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun StatCardPreview(){
    StatCard(
        model = StatCardModel(
            title =  "مجموع هزینه" ,
            value = "12,450,000",
            icon = AppIcons.Wallet ,
            iconColor = MaterialTheme.colorScheme.background
        )
    )
}