package com.mahdidavar.expensesmanagement.ui.screens.dialogs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahdidavar.expensesmanagement.R
import com.mahdidavar.expensesmanagement.ui.theme.ColorPrimaryDark
import com.mahdidavar.expensesmanagement.ui.theme.yekan

@Composable
fun MyDeleteDialog(
    width: Float,
    onDismiss: () -> Unit,
    onConfirmButton: () -> Unit
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
            ) {
                Text(
                    text = "حذف فاکتور",
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
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "از حذف فاکتور مطمئن هستید ؟",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = yekan,
                    color = Color.Black,
                    modifier = Modifier.padding(end = 15.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                OutlinedButton(
                    onClick = {
                        onConfirmButton()
                    },
                    border = BorderStroke(width = 2.dp, color = ColorPrimaryDark)
                ) {
                    Text(
                        text = "حذف",
                        fontSize = 15.sp,
                        color = ColorPrimaryDark,
                        fontWeight = FontWeight.Bold
                    )
                }
                OutlinedButton(
                    onClick = {
                        onDismiss()
                    },
                    border = BorderStroke(width = 2.dp, color = Color.Black)
                )
                {
                    Text(
                        text = "انصراف",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}