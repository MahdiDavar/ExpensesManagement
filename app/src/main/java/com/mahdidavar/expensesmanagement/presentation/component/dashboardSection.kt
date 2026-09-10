package com.mahdidavar.expensesmanagement.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mahdidavar.expensesmanagement.core.design.AppIcons
import com.mahdidavar.expensesmanagement.core.design.AppSpacing
import com.mahdidavar.expensesmanagement.core.ui.SectionTitle
import com.mahdidavar.expensesmanagement.core.ui.StatCard
import com.mahdidavar.expensesmanagement.presentation.model.DashboardUiModel
import com.mahdidavar.expensesmanagement.presentation.model.StatCardModel
import com.mahdidavar.expensesmanagement.ui.theme.DarkBlue

@Composable
fun DashboardSection(
    model: DashboardUiModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        SectionTitle(
            title = "گزارش این ماه"
        )
        Spacer(Modifier.height(AppSpacing.MD))
        LazyVerticalGrid(
            modifier = Modifier.height(300.dp),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(AppSpacing.MD),
            verticalArrangement = Arrangement.spacedBy(AppSpacing.MD),
            userScrollEnabled = false
        ) {
            item {
                StatCard(
                    model = StatCardModel(
                        title = "مجموع هزینه ها",
                        value = model.totalExpenses,
                        icon = AppIcons.Wallet,
                        iconColor = DarkBlue
                    )
                )
            }
            item {
                StatCard(
                    model = StatCardModel(
                        title = "میانگین",
                        value = model.averageExpense,
                        icon = AppIcons.Chart,
                        iconColor = Color(0xFF4CAF50)
                    )
                )
            }
            item {
                StatCard(
                    model = StatCardModel(
                        title = "تعداد تراکنش ها",
                        value = model.invoiceCount,
                        icon = AppIcons.Calender,
                        iconColor = Color(0xFFFF9800)
                    )
                )
            }
            item {
                StatCard(
                    model = StatCardModel(
                        title = "دسته برتر",
                        value = model.topCategory,
                        icon = AppIcons.Home,
                        iconColor = Color(0xFFE91E63)
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DashboardPreview() {
    DashboardSection(
        model = DashboardUiModel(
            topCategory = "خوراک",
            averageExpense = "12.500",
            totalExpenses = "500.000",
            invoiceCount = "15"
        )
    )
}