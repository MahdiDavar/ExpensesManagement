package com.mahdidavar.expensesmanagement.ui.screens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.mahdidavar.expensesmanagement.R
import com.mahdidavar.expensesmanagement.db.entity.InvoicesEntity
import com.mahdidavar.expensesmanagement.navigation.BottomNavigation
import com.mahdidavar.expensesmanagement.ui.screens.dialogs.ChooseRangeDate
import com.mahdidavar.expensesmanagement.ui.screens.dialogs.MyDeleteDialog
import com.mahdidavar.expensesmanagement.ui.screens.dialogs.MyDetailDialog
import com.mahdidavar.expensesmanagement.ui.theme.DarkBlue
import com.mahdidavar.expensesmanagement.ui.theme.negare
import com.mahdidavar.expensesmanagement.ui.theme.yekan
import com.mahdidavar.expensesmanagement.utills.byLocate
import com.mahdidavar.expensesmanagement.utills.byLocateAndSeparator
import com.mahdidavar.expensesmanagement.viewModel.ShowInvoicesViewModel

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun ShowInvoiceScreen(
    navController: NavHostController,
    viewModel: ShowInvoicesViewModel = hiltViewModel()
) {
    Box {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val size = LocalConfiguration

        val constraint = remember {
            ConstraintSet {
                val mainBox = createRefFor("mainBox")
                val header = createRefFor("header")
                val searchBar = createRefFor("searchBar")
                val listBox = createRefFor("listBox")

                constrain(mainBox) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                constrain(header) {
                    top.linkTo(mainBox.top)
                    start.linkTo(mainBox.start)
                    end.linkTo(mainBox.end)
                }
                constrain(searchBar) {
                    top.linkTo(listBox.top)
                    bottom.linkTo(listBox.top)
                    start.linkTo(listBox.start)
                    end.linkTo(listBox.end)
                }
                constrain(listBox) {
                    top.linkTo(header.bottom)
                    start.linkTo(mainBox.start)
                    end.linkTo(mainBox.end)
                }
            }
        }
        ConstraintLayout(
            modifier = Modifier.fillMaxSize(),
            constraintSet = constraint
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(DarkBlue)
                    .layoutId("mainBox")
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .layoutId("header")
            ) {
                TopHeader(
                    totalAmount = uiState.totalAmount,
                    startDate = uiState.startDate,
                    endDate = uiState.endDate,
                    count = uiState.filteredInvoices.size.toString()
                        .ifEmpty { uiState.invoices.size.toString() },
                    onRangeSelected = viewModel::onDateRangeSelected
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topEnd = 45.dp, topStart = 45.dp))
                    .background(Color.White)
                    .layoutId("listBox")
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    InvoiceList(
                        invoices = uiState.filteredInvoices.ifEmpty { uiState.invoices },
                        onDeleteClick = viewModel::deleteRequest,
                        onDetailClick = viewModel::detailRequest
                    )
                }
            }
            Box(
                modifier = Modifier
                    .layoutId("searchBar")
            ) {
                Card(
                    modifier = Modifier
                        .size(height = 60.dp, width = (size.current.screenWidthDp * 0.8).dp),
                    shape = CircleShape,
                    border = BorderStroke(width = 3.dp, color = DarkBlue),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    SearchBar(
                        query = uiState.searchQuery,
                        onQueryChange = viewModel::onSearchChange
                    )
                }
            }
        }
        AnimatedVisibility(
            visible = uiState.showDelete,
            enter = slideInVertically(animationSpec = tween(500)) +
                    fadeIn(animationSpec = tween(500)),
            exit = slideOutVertically(animationSpec = tween(500)) +
                    fadeOut(animationSpec = tween(500))
        ) {
            uiState.selectedForDelete?.let {
                MyDeleteDialog(
                    width = (size.current.screenWidthDp * 0.8f),
                    onConfirmButton = viewModel::deleteConfirmed,
                    onDismiss = viewModel::dismissDeleteDialog
                )
            }
        }
        AnimatedVisibility(
            visible = uiState.showDetail,
            enter = slideInVertically(animationSpec = tween(500)) +
                    fadeIn(animationSpec = tween(500)),
            exit = slideOutVertically(animationSpec = tween(500)) +
                    fadeOut(animationSpec = tween(500))
        ) {
            uiState.selectedForDetail?.let {
                MyDetailDialog(
                    width = (size.current.screenWidthDp * 0.8f),
                    onDismiss = viewModel::dismissDetailButton,
                    invoice = it
                )
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
}

@Composable
private fun TopHeader(
    totalAmount: Long,
    startDate: String,
    endDate: String,
    count: String,
    onRangeSelected: (String, String) -> Unit
) {
    var showRangeDialog by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.8f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "مهدی داورپناه",
                    fontWeight = FontWeight.Bold,
                    fontFamily = negare,
                    fontSize = 20.sp,
                    color = Color.White,
                    style = TextStyle(textDirection = TextDirection.Rtl)
                )
                Text(
                    text = "تعداد : ${count.byLocate()}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White,
                    style = TextStyle(textDirection = TextDirection.Rtl)
                )
            }
            Spacer(Modifier.width(20.dp))
            Surface(
                modifier = Modifier.weight(0.2f),
                shape = CircleShape,
                border = BorderStroke(
                    width = 3.dp,
                    color = Color.White
                )
            ) {
                Image(
                    painterResource(R.drawable.avatar_profile),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp)
                )
            }
        } // پروفایل
        Spacer(Modifier.height(7.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = totalAmount.toString().byLocateAndSeparator() + " تومان",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White,
                style = TextStyle(textDirection = TextDirection.Rtl)
            )
            Spacer(Modifier.width(10.dp))
            Text(
                text = "مجموع هزینه ها :",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                fontFamily = yekan,
                color = Color.White,
                style = TextStyle(textDirection = TextDirection.Rtl)
            )
        }  // گزارش هزینه
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = when {
                    startDate.isNotEmpty() && endDate.isNotEmpty() ->
                        "${startDate.byLocate()} تا ${endDate.byLocate()}"

                    else -> "انتخاب بازه زمانی"
                },
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                fontFamily = yekan,
                color = Color.White,
                style = TextStyle(textDirection = TextDirection.Rtl)
            )
            Spacer(Modifier.width(5.dp))
            IconButton({ showRangeDialog = !showRangeDialog }) {
                Icon(Icons.Filled.DateRange, contentDescription = null, tint = Color.White)
            }
        } // بازه زمانی
    }
    if (showRangeDialog) {
        Dialog(
            onDismissRequest = { showRangeDialog = false },
            properties = DialogProperties(
                dismissOnClickOutside = false
            )
        ) {
            AnimatedVisibility(
                visible = showRangeDialog,
                enter = scaleIn() + fadeIn(),
                exit = scaleOut() + fadeOut()
            ) {
                ChooseRangeDate(
                    onDismiss = { showRangeDialog = false },
                    onDateChange = onRangeSelected
                )
            }
        }

    }
}

@Composable
private fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton({}) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Sort,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
        TextField(
            value = query,
            onValueChange = onQueryChange,
            textStyle = TextStyle(textDirection = TextDirection.Rtl),
            modifier = Modifier
                .padding(end = 10.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            placeholder = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "جستجو",
                        fontSize = 13.sp,
                        fontFamily = yekan,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            singleLine = true,
        )
    }
}

@Composable
fun InvoiceList(
    invoices: List<InvoicesEntity>,
    onDeleteClick: (InvoicesEntity) -> Unit,
    onDetailClick: (InvoicesEntity) -> Unit
) {
    if (invoices.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "موردی برای نمایش وجود ندارد",
                fontSize = 30.sp,
                color = Color.Black,
                fontFamily = negare
            )
        }
    }
    else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 25.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(
                    items = invoices,
                    key = { it.id }
                ) { invoice ->
                    InvoiceSample(
                        invoice = invoice,
                        deleteAction = { onDeleteClick(invoice) },
                        detailAction = { onDetailClick(invoice) }
                    )
                }
            }
    }
}


@Composable
private fun InvoiceSample(
    invoice: InvoicesEntity,
    deleteAction: () -> Unit,
    detailAction: () -> Unit
) {
    //  var showConfirmDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.DarkGray
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
                Row(
                    modifier = Modifier.weight(0.4f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        detailAction()
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.bill),
                            contentDescription = null, tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    IconButton(onClick = {
                        deleteAction()
                    }) {
                        Icon(
                            Icons.Filled.Delete,
                            contentDescription = null, tint = Color.White
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Filled.Edit,
                            contentDescription = null, tint = Color.White
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .weight(0.6f)
                        .padding(end = 8.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = invoice.category,
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 10.dp)
                    )
                    Icon(
                        painter = painterResource(R.drawable.car),
                        contentDescription = null, tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
                Row(
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(start = 8.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "تومان",
                        fontSize = 16.sp,
                        fontFamily = negare,
                        color = Color.White
                    )
                    Spacer(Modifier.width(7.dp))
                    Text(
                        text = invoice.price.toString().byLocateAndSeparator(),
                        fontSize = 20.sp,
                        color = Color.Red
                    )
                }
                Row(
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(end = 8.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = invoice.subCategory,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                    Spacer(Modifier.width(10.dp))
                    Icon(
                        imageVector = Icons.Default.Category,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .size(20.dp)
                            .padding(end = 5.dp)
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            )
            {
                Text(
                    text = invoice.time.byLocate(),
                    fontSize = 12.sp,
                    color = Color.White,
                    modifier = Modifier.padding(end = 6.dp)
                )
                Text(
                    text = "ساعت ",
                    fontSize = 12.sp,
                    color = Color.White,
                    style = TextStyle(textDirection = TextDirection.Rtl),
                    modifier = Modifier.padding(end = 5.dp)
                )
                Text(
                    text = "تاریخ ثبت : ${invoice.persianDate.byLocate()}",
                    fontSize = 12.sp,
                    color = Color.White,
                    style = TextStyle(textDirection = TextDirection.Rtl)
                )
            }
        }
    }
    /*  if (showConfirmDialog) {
          Dialog(onDismissRequest = {}) {
              MyConfirmDialog {
                  showConfirmDialog = false
              }
          }
      }*/
}



