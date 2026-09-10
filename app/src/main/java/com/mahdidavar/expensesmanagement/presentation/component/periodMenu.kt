package com.mahdidavar.expensesmanagement.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.mahdidavar.expensesmanagement.core.design.AppSpacing
import com.mahdidavar.expensesmanagement.db.utills.ChartPeriod
import com.mahdidavar.expensesmanagement.db.utills.toPersianName

@Composable
fun PeriodMenu(
    selected: ChartPeriod,
    onSelected: (ChartPeriod) -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Box{
        OutlinedButton(
            onClick = {
            expanded = true
        }) {
            Text(text = selected.toPersianName())
            Spacer(Modifier.width(AppSpacing.XS))
            Icon(
                imageVector = Icons.Default.ArrowDropDown ,
                contentDescription = null)
        }
        DropdownMenu(
            expanded = expanded ,
            onDismissRequest = {
                expanded = false
            }
        ) {
            ChartPeriod.entries.forEach {period ->
                DropdownMenuItem(
                    text = {
                        Text(text = period.toPersianName())
                    } ,
                    onClick = {
                        onSelected(period)
                        expanded = false
                    }
                )
            }
        }
    }
}