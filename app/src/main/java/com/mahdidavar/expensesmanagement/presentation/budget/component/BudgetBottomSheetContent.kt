package com.mahdidavar.expensesmanagement.presentation.budget.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.mahdidavar.expensesmanagement.core.design.AppSpacing

@Composable
fun BudgetBottomSheetContent(
    currentBudget: Long?,
    onSave: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    var budget by rememberSaveable(currentBudget) {
        mutableStateOf(currentBudget?.toString() ?: "")
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(AppSpacing.LG)
    ) {
        Text(
            text = if (currentBudget == null) "ثبت بودجه" else "ویرایش بودجه",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(Modifier.height(AppSpacing.LG))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = budget,
            onValueChange = { budget = it.filter(Char::isDigit) },
            label = { Text("مبلغ بودجه") },
            singleLine = true
        )
        Spacer(Modifier.height(AppSpacing.XL))
        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled = budget.isNotBlank(),
            onClick =
                { budget.toLongOrNull()?.let(onSave) }
        ) {
            Text(text = "ذخیره")
        }
    }
}