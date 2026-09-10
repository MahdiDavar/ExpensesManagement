package com.mahdidavar.expensesmanagement.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahdidavar.expensesmanagement.core.design.AppSpacing
import com.mahdidavar.expensesmanagement.core.ui.AppCard
import com.mahdidavar.expensesmanagement.db.entity.BudgetEntity
import com.mahdidavar.expensesmanagement.presentation.budget.model.BudgetProgressUiModel
import com.mahdidavar.expensesmanagement.presentation.budget.model.BudgetUiState
import com.mahdidavar.expensesmanagement.presentation.budget.model.BudgetWarningUiModel
import com.mahdidavar.expensesmanagement.ui.theme.NewRedDark
import com.mahdidavar.expensesmanagement.utills.NumberFormatter

@Composable
fun BudgetContent(
    uiState: BudgetUiState,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(AppSpacing.MD),
        contentPadding = PaddingValues(AppSpacing.MD)
    ) {
        item {
            if (uiState.budget == null) {
                EmptyBudgetCard(
                    onAddBudget = onEditClick
                )
            }
            else {
                BudgetHeaderCard(
                    budget = uiState.budget,
                    onEditClick = onEditClick,
                    onDeleteClick = onDeleteClick
                )
            }
        }
        item {
            BudgetProgressCard(
                model = uiState.progressUiModel
            )
        }
        item {
            BudgetWarningCard(
                model = uiState.warning
            )
        }
    }
}


@Composable
fun BudgetHeaderCard(
    budget: BudgetEntity,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    AppCard {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column (
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    "بودجه این ماه",
                    style = MaterialTheme.typography.titleMedium ,
                    fontWeight = FontWeight.ExtraBold ,
                    fontSize = 18.sp
                )
                Spacer(Modifier.height(AppSpacing.XS))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "تومان" ,
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Spacer(Modifier.width(AppSpacing.SM))
                    Text(
                        text = NumberFormatter.formatPrice(budget.amount) ,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }
            Row {
                IconButton(
                    onClick = onEditClick
                ) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Budget")
                }
                IconButton(
                    onClick = onDeleteClick
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Budget" , tint = NewRedDark)
                }
            }
        }
    }
}

@Composable
fun EmptyBudgetCard(
    onAddBudget: () -> Unit,
    modifier: Modifier = Modifier
) {
    AppCard(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.AccountBalanceWallet,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(56.dp)
            )
            Spacer(Modifier.height(AppSpacing.MD))
            Text(
                text = "برای مدیریت بهتر هزینه‌ها، ابتدا بودجه این ماه را ثبت کنید.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(AppSpacing.LG))
            FilledTonalButton(
                onClick = onAddBudget
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
                Spacer(Modifier.width(AppSpacing.SM))
                Text(text = "ثبت بودجه")
            }
        }
    }
}

@Composable
fun BudgetProgressCard(model: BudgetProgressUiModel) {
    AppCard {
        Text(text = "مصرف بودجه" , fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(AppSpacing.MD))
        Text(
            text = "${(model.progress * 100).toInt()}%" ,
            fontWeight = FontWeight.Bold
        )
        CircularProgressIndicator(
            progress = { model.progress.coerceIn(0f, 1f) },
        )
        Spacer(Modifier.height(AppSpacing.MD))
        LinearProgressIndicator(
            progress = { model.progress.coerceIn(0f, 1f) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(AppSpacing.MD))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            BudgetStatItem(
                title = "خرج شده",
                value = NumberFormatter.formatPrice(model.spentAmount)
            )
            BudgetStatItem(
                title = "باقی مانده",
                value = NumberFormatter.formatPrice(model.remainingAmount)
            )
        }
    }
}


@Composable
private fun BudgetStatItem(
    title: String,
    value: String
) {
    Column {
        Text(text = title)
        Spacer(Modifier.height(AppSpacing.XS))
        Text(text = value, style = MaterialTheme.typography.titleMedium)
    }
}


@Composable
fun BudgetWarningCard(
    model: BudgetWarningUiModel,
    modifier: Modifier = Modifier
) {
    AppCard(modifier = modifier) {
        Text(
            text = model.title,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(Modifier.height(AppSpacing.SM))
        Text(
            text = model.message
        )
    }
}


