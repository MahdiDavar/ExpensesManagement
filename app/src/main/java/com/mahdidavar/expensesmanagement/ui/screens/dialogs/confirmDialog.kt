package com.mahdidavar.expensesmanagement.ui.screens.dialogs

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahdidavar.expensesmanagement.R
import com.mahdidavar.expensesmanagement.ui.theme.TxtGreen
import com.mahdidavar.expensesmanagement.ui.theme.yekan

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun MyConfirmDialog(
    onDismiss : ()-> Unit
){
    val size = LocalConfiguration
    Card(modifier = Modifier
        .width((size.current.screenWidthDp*0.8).dp),
        shape = RoundedCornerShape(25.dp) ,
        border = BorderStroke(width = 2.dp , color = TxtGreen)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally ,
            verticalArrangement = Arrangement.Center) {
            Row(modifier = Modifier.fillMaxWidth() ,
                verticalAlignment = Alignment.CenterVertically ,
                horizontalArrangement = Arrangement.Center) {
                Text(text = "عملیات با موفقیت انجام شد" ,
                    fontSize = 13.sp ,
                    fontFamily = yekan,
                    modifier = Modifier.padding(end = 8.dp))
                Image(painterResource(R.drawable.ic_confirm) ,
                    contentDescription = null ,
                    modifier = Modifier.size(35.dp))
            }
            Button(onClick = {
                onDismiss()
            } ,
                colors = ButtonDefaults.buttonColors(
                    containerColor = TxtGreen ,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(40) ,
                modifier = Modifier.width(100.dp)
                ) {
                Text("باشه" ,
                    fontSize = 13.sp ,
                    fontFamily = yekan,
                    fontWeight = FontWeight.Bold)
            }
        }
    }
}