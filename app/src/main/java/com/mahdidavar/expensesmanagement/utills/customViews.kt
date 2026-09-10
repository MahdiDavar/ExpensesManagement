package com.mahdidavar.expensesmanagement.utills

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahdidavar.expensesmanagement.ui.theme.DarkBlue
import com.mahdidavar.expensesmanagement.ui.theme.negare

@Composable
fun CustomButton(txt : String, isEnabled : Boolean , onClicked : ()-> Unit ){
    Button(
        onClick = onClicked ,
        enabled =isEnabled ,
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
            text = txt ,
            fontSize = 22.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = negare
        )
    }
}