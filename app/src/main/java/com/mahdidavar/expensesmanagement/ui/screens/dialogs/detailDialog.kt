package com.mahdidavar.expensesmanagement.ui.screens.dialogs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahdidavar.expensesmanagement.R
import com.mahdidavar.expensesmanagement.db.entity.InvoicesEntity
import com.mahdidavar.expensesmanagement.ui.theme.ColorPrimaryDark
import com.mahdidavar.expensesmanagement.ui.theme.DarkBlue
import com.mahdidavar.expensesmanagement.ui.theme.NewBlue
import com.mahdidavar.expensesmanagement.ui.theme.negare
import com.mahdidavar.expensesmanagement.ui.theme.yekan
import com.mahdidavar.expensesmanagement.utills.byLocateAndSeparator

@Composable
fun MyDetailDialog(
    width: Float,
    onDismiss: () -> Unit,
    invoice: InvoicesEntity
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .width(width.dp)
                .clip(RoundedCornerShape(30.dp))
                .border(2.dp, ColorPrimaryDark)
                .background(Color(0xffFCF3EC)),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(
                    text = "جزئیات هزینه",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = yekan,
                    color = ColorPrimaryDark,
                    modifier = Modifier.padding(end = 10.dp)
                )
                Icon(
                    painter = painterResource(R.drawable.ic_delete),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp),
                    tint = ColorPrimaryDark
                )
            } // title

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 20.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(modifier = Modifier.fillMaxWidth().weight(0.7f),
                    verticalAlignment = Alignment.CenterVertically ,
                    horizontalArrangement = Arrangement.Center) {
                    DetailText(txt = "تومان", font = negare)
                    DetailText(txt = invoice.price.toString().byLocateAndSeparator())
                }
                DetailText(txt = "مبلغ :", font = yekan , modifier = Modifier.weight(0.3f))
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 20.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DetailText(txt = invoice.category , font = negare)
                Spacer(Modifier.width(50.dp))
                DetailText(txt = "برای :", yekan)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 20.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DetailText(txt = invoice.subCategory , font = negare)
                Spacer(Modifier.width(50.dp))
                DetailText(txt = "بابت :", yekan)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 15.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DetailText(txt = "توضیحات :", yekan)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 20.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DetailText(txt = invoice.des ?: "")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                OutlinedButton(
                    onClick = {
                        onDismiss()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = NewBlue
                    ),
                    border = BorderStroke(width = 2.dp, color = DarkBlue)
                ) {
                    Text(
                        text = "بستن",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            } // button
        }
    }
}

@Composable
private fun DetailText(
    txt: String,
    font: FontFamily? = null ,
    modifier: Modifier = Modifier
) {
    Text(
        text = txt,
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        modifier = Modifier.padding(end = 20.dp),
        style = TextStyle(textDirection = TextDirection.Rtl),
        fontFamily = font
    )
}