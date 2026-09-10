package com.mahdidavar.expensesmanagement.ui.screens.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahdidavar.expensesmanagement.ui.theme.ColorPrimaryDark
import com.mahdidavar.expensesmanagement.utills.byLocate
import info.alirezaahmadi.persian_date_picker.controller.OnDatePickerEvents
import info.alirezaahmadi.persian_date_picker.view.PersianDatePickerDialog

@Composable
fun ChooseRangeDate(
    onDismiss: () -> Unit,
    onDateChange: (String, String) -> Unit
) {
    var startRange by remember { mutableStateOf(false) }
    var endRange by remember { mutableStateOf(false) }
    var formattedStartDate by remember { mutableStateOf("") }
    var formattedEndDate by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .size(width = 300.dp, height = 300.dp)
            .clip(RoundedCornerShape(35.dp))
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(30.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                "انتخاب تاریخ", fontSize = 16.sp,
                fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 7.dp)
            )
            Icon(imageVector = Icons.Filled.DateRange, contentDescription = null)
        } // عنوان
        Spacer(Modifier.height(30.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = formattedStartDate.byLocate().ifEmpty { "انتخاب کنید" },
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                style = TextStyle(textDirection = TextDirection.Rtl),
                textDecoration = if (formattedStartDate.isEmpty()) TextDecoration.Underline else TextDecoration.None,
                color = if (formattedStartDate.isEmpty()) Color.Gray else Color.Black,
                modifier = Modifier.clickable { startRange = !startRange })
            Spacer(Modifier.width(15.dp))
            Text(
                "ابتدای بازه :", fontSize = 16.sp,
                fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 7.dp),
                style = TextStyle(textDirection = TextDirection.Rtl)
            )
        } //ابتدای بازه

        Spacer(Modifier.height(30.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = formattedEndDate.byLocate().ifEmpty { "انتخاب کنید" },
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                style = TextStyle(textDirection = TextDirection.Rtl),
                textDecoration = if (formattedEndDate.isEmpty()) TextDecoration.Underline else TextDecoration.None,
                color = if (formattedEndDate.isEmpty()) Color.Gray else Color.Black,
                modifier = Modifier.clickable { endRange = !endRange })
            Spacer(Modifier.width(15.dp))
            Text(
                "انتهای بازه :", fontSize = 16.sp,
                fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 7.dp),
                style = TextStyle(textDirection = TextDirection.Rtl)
            )
        } //انتهای بازه

        Spacer(Modifier.height(50.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    onDateChange(formattedStartDate, formattedEndDate)
                    onDismiss()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = ColorPrimaryDark,
                    contentColor = Color.White,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.Black
                ),
                enabled = formattedStartDate.isNotEmpty() && formattedEndDate.isNotEmpty()
            ) {
                Text(
                    text = "تایید",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp)
                )
            }
            Spacer(Modifier.width(15.dp))
            Button(
                onClick = {
                    onDismiss()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = ColorPrimaryDark,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "انصراف",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp)
                )
            }
        } // دکمه ها
        if (startRange) {
            PersianDatePickerDialog(
                onDismissRequest = { startRange = false },
                controller = object : OnDatePickerEvents {
                    override fun onConfirmButtonClick(
                        year: Int,
                        month: Int,
                        day: Int
                    ) {
                        val formattedMonth = month.toString().padStart(2, '0')
                        val formattedDay = day.toString().padStart(2, '0')

                        formattedStartDate = "$year/$formattedMonth/$formattedDay"
                        startRange = false
                    }

                    override fun onClose() {
                        startRange = false
                    }
                }
            )
        }
        if (endRange) {
            PersianDatePickerDialog(
                onDismissRequest = { endRange = false },
                controller = object : OnDatePickerEvents {
                    override fun onConfirmButtonClick(
                        year: Int,
                        month: Int,
                        day: Int
                    ) {
                        val formattedMonth = month.toString().padStart(2, '0')
                        val formattedDay = day.toString().padStart(2, '0')

                        formattedEndDate = "$year/$formattedMonth/$formattedDay"
                        endRange = false
                    }

                    override fun onClose() {
                        endRange = false
                    }
                }
            )
        }
    }
}