package com.mahdidavar.expensesmanagement.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.NoteAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.mahdidavar.expensesmanagement.ui.screens.dialogs.ChoseCategory
import com.mahdidavar.expensesmanagement.ui.screens.dialogs.MyConfirmDialog
import com.mahdidavar.expensesmanagement.ui.theme.ColorPrimaryDark
import com.mahdidavar.expensesmanagement.ui.theme.DarkBlue
import com.mahdidavar.expensesmanagement.ui.theme.negare
import com.mahdidavar.expensesmanagement.ui.theme.yekan
import com.mahdidavar.expensesmanagement.utills.byLocate
import com.mahdidavar.expensesmanagement.viewModel.AddInvoicesViewModel
import com.mahdidavar.expensesmanagement.viewModel.TimeViewModel
import info.alirezaahmadi.persian_date_picker.controller.OnDatePickerEvents
import info.alirezaahmadi.persian_date_picker.view.PersianDatePickerDialog

@Composable
fun AddInvoiceScreen(
    viewModel: AddInvoicesViewModel = hiltViewModel(),
    dateViewModel: TimeViewModel = hiltViewModel()
) {
    val success = viewModel.success
    var showSuccess by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Header(
            date = viewModel.dateFarsi,
            onDateChange = viewModel::onDateChange
        )
        PreviewSection(
            price = viewModel.price,
            selected = viewModel.subCategory,
            category = viewModel.category,
            date = viewModel.dateFarsi.ifEmpty { dateViewModel.getDay() }
        )
        AddSection(
            price = viewModel.price,
            onPriceChange = viewModel::onPriceChange,
            desc = viewModel.desc,
            onDescChange = viewModel::onDescChange,
            selected = viewModel.subCategory,
            category = viewModel.category,
            onCategorySelected = viewModel::onCategorySelected
        )
        Button(
            onClick = { viewModel.submit()},
            enabled = viewModel.price.isNotEmpty() &&
                    viewModel.subCategory.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = DarkBlue,
                contentColor = Color.White,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = DarkBlue
            ),
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(width = 2.dp, color = DarkBlue)
        ) {
            Text(
                text = "ثبت فاکتور",
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = negare
            )
        }
        Spacer(Modifier.height(20.dp))
        LaunchedEffect(Unit) {
            success.collect {
                showSuccess = it
            }
        }
    }
    if (showSuccess) {
        Dialog(onDismissRequest = {}) {
            MyConfirmDialog { showSuccess = false }
        }
    }
}

@Composable
private fun Header(
    date: String,
    onDateChange: (String, Int) -> Unit
) {
    var datePicker by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .clip(RoundedCornerShape(bottomStart = 45.dp, bottomEnd = 45.dp))
            .background(DarkBlue)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "اضافه کردن هزینه جدید +",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                fontFamily = yekan
            )
            Spacer(Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            )
            {
                Text(
                    text = date.byLocate().ifEmpty { "تاریخ امروز" },
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(Modifier.width(10.dp))
                IconButton({ datePicker = !datePicker }) {
                    Icon(Icons.Filled.DateRange, contentDescription = null, tint = Color.White)
                }
            }
        }
    }
    if (datePicker) {
        PersianDatePickerDialog(
            onDismissRequest = { datePicker = false },
            controller = object : OnDatePickerEvents {
                override fun onConfirmButtonClick(
                    year: Int,
                    month: Int,
                    day: Int
                ) {
                    val formattedMonth = month.toString().padStart(2, '0')
                    val formattedDay = day.toString().padStart(2, '0')

                    val formattedDate = "$year/$formattedMonth/$formattedDay"
                    val formattedNum = "$year$formattedMonth$formattedDay".toInt()
                    onDateChange(formattedDate, formattedNum)
                    datePicker = false
                }

                override fun onClose() {
                    datePicker = false
                }

                override fun onGoToday() {
                    super.onGoToday()
                }

                override fun onDateChange(year: Int, month: Int, day: Int) {
                    super.onDateChange(year, month, day)
                }
            }
        )
    }
}

@Composable
private fun PreviewSection(
    price: String,
    selected: String,
    date: String ,
    category: String ,
) {
    val selectedCategory ="$category $selected "
    val previewConstraint = remember {
        ConstraintSet {
            val title = createRefFor("title")
            val preview = createRefFor("preview")

            constrain(title) {
                start.linkTo(preview.start)
                end.linkTo(preview.end)
                top.linkTo(preview.top)
                bottom.linkTo(preview.top)
            }
        }
    }
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth(),
        constraintSet = previewConstraint
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .layoutId("preview")
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .padding(horizontal = 15.dp),
                shape = RoundedCornerShape(20.dp),
                border = BorderStroke(width = 3.dp, color = DarkBlue)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        " شما در تاریخ ${date.byLocate()} \nمبلغ" +
                                " $price بابت $selectedCategory پرداخت کردید ",
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(textDirection = TextDirection.Rtl)
                    )
                }
            }
        }
        Box(modifier = Modifier.layoutId("title")) {
            Card(
                modifier = Modifier
                    .size(height = 50.dp, width = 250.dp)
                    .padding(horizontal = 15.dp),
                shape = CircleShape,
                border = BorderStroke(width = 3.dp, color = DarkBlue)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "پیش نمایش فاکتور",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = DarkBlue,
                        fontFamily = negare
                    )
                }
            }
        }
    }
}

@Composable
fun AddSection(
    price: String,
    onPriceChange: (String) -> Unit,
    desc: String,
    onDescChange: (String) -> Unit,
    selected: String,
    category: String ,
    onCategorySelected: (String, String) -> Unit
) {
    var showCategory by remember { mutableStateOf(false) }
    val selectedCategory ="$category ( $selected )"

    val addSectionConstraint = remember {
        ConstraintSet {
            val addTitle = createRefFor("addTitle")
            val addSection = createRefFor("addSection")

            constrain(addTitle) {
                start.linkTo(addSection.start)
                end.linkTo(addSection.end)
                top.linkTo(addSection.top)
                bottom.linkTo(addSection.top)
            }
        }
    }
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth(),
        constraintSet = addSectionConstraint
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .layoutId("addSection")
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
                    .height(245.dp),
                shape = RoundedCornerShape(20.dp),
                border = BorderStroke(width = 3.dp, color = DarkBlue)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .padding(horizontal = 10.dp)
                            .background(Color.White),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        TextField(
                            value = price,
                            onValueChange = {
                                if (it.all { char -> char.isDigit() })
                                    onPriceChange(it)
                            },
                            modifier = Modifier
                                .weight(0.85f)
                                .padding(end = 7.dp),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black,
                                unfocusedIndicatorColor = Color.White,
                                focusedIndicatorColor = ColorPrimaryDark
                            ),
                            placeholder = {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Text(
                                        "هزینه",
                                        fontSize = 18.sp,
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        Icon(
                            Icons.Default.MonetizationOn, contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier
                                .padding(end = 5.dp)
                                .weight(0.15f)
                        )
                    } // مبلغ
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .padding(horizontal = 5.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.85f)
                                .padding(end = 7.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End
                        )
                        {
                            Text(
                                text = if (selected.isEmpty())  "انتخاب نشده" else  selectedCategory,
                                fontSize = 18.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(Modifier.width(10.dp))
                            Text(
                                " : دسته بندی",
                                fontSize = 18.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.clickable { showCategory = true }
                            )
                        }
                        Icon(
                            Icons.Default.Category,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier
                                .padding(end = 5.dp)
                                .weight(0.15f)
                        )
                    } // دسته بندی
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .padding(horizontal = 10.dp)
                            .background(Color.White),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextField(
                            value = desc,
                            onValueChange = onDescChange,
                            modifier = Modifier
                                .weight(0.85f)
                                .padding(end = 7.dp),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black,
                                unfocusedIndicatorColor = Color.White,
                                focusedIndicatorColor = ColorPrimaryDark
                            ),
                            placeholder = {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Text(
                                        "توضیحات",
                                        fontSize = 18.sp,
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        )
                        Icon(
                            Icons.Default.NoteAlt, contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier
                                .padding(end = 5.dp)
                                .weight(0.15f)
                        )
                    } // توضیحات
                    Spacer(Modifier.height(10.dp))
                }
            }
            if (showCategory) {
                Dialog(
                    onDismissRequest = { showCategory = false }
                ) {
                    ChoseCategory(
                        onDismiss = { showCategory = false },
                        onCategorySelected = {category , subCategory ->
                            onCategorySelected(category , subCategory)
                            showCategory = false
                        })
                }
            }
        }
        Box(modifier = Modifier.layoutId("addTitle")) {
            Card(
                modifier = Modifier
                    .size(height = 50.dp, width = 250.dp)
                    .padding(horizontal = 15.dp),
                shape = CircleShape,
                border = BorderStroke(width = 3.dp, color = DarkBlue)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "مشخصات فاکتور",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = DarkBlue,
                        fontFamily = negare
                    )
                }
            } // سربرگ
        }
    }
}