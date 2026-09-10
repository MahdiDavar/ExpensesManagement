package com.mahdidavar.expensesmanagement.presentation.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mahdidavar.expensesmanagement.presentation.budget.component.BudgetBottomSheetContent
import com.mahdidavar.expensesmanagement.viewModel.BudgetViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetScreen(
    viewModel: BudgetViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }

    BudgetContent(
        uiState = uiState,
        onEditClick = { showBottomSheet = true },
        onDeleteClick =  viewModel::deleteBudget
    )
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            }
        ) {
            BudgetBottomSheetContent(
                currentBudget = uiState.budget?.amount,
                onSave = {
                    viewModel.saveBudget(it)
                    showBottomSheet = false
                }
            )
        }
    }
}
