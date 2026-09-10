package com.mahdidavar.expensesmanagement.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mahdidavar.expensesmanagement.R
import com.mahdidavar.expensesmanagement.navigation.Routes
import com.mahdidavar.expensesmanagement.ui.theme.DarkBlue
import com.mahdidavar.expensesmanagement.ui.theme.yekan
import com.mahdidavar.expensesmanagement.viewModel.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val status = viewModel.loginStatus.collectAsState()
    val context = LocalContext.current
    val username = remember { mutableStateOf("") }
    val isLoading = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffFCF3EC)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.logo_launcher), "",
            modifier = Modifier.size(250.dp)
        )
        Spacer(Modifier.height(30.dp))
        Text(
            text = "ثبت نام :",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            style = TextStyle(textDirection = TextDirection.Rtl),
            fontFamily = yekan
        )
        Spacer(Modifier.height(30.dp))
        CustomLoginTextField(
            state = username,
            placeholder = "نام کاربری خود را وارد کنید"
        )
        Spacer(Modifier.height(40.dp))
        Button(
            onClick = {
                if (username.value.isNotBlank() && !isLoading.value) {
                    viewModel.login(userName = username.value)
                    isLoading.value = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            enabled = username.value.isNotEmpty(),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = DarkBlue,
                contentColor = Color.White,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = DarkBlue
            ),
            border = BorderStroke(width = 2.dp, color = DarkBlue)
        ) {
            if (isLoading.value) {
                CircularProgressIndicator(
                    modifier = Modifier.size(30.dp),
                    color = Color.White
                )
            } else {
                Text(
                    text = "ثبت نام",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    }
    LaunchedEffect(status.value) {
        if (status.value) {
            viewModel.saveData()
            navController.navigate(Routes.HomePage) {
                popUpTo(Routes.LoginPage) { inclusive = true }
            }
        } else {
            isLoading.value = false
        }
    }
    LaunchedEffect(Unit) {
        viewModel.message.collect { msg ->
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
        }
    }
}


@Composable
private fun CustomLoginTextField(
    state: MutableState<String>,
    error: Boolean = false,
    direction: TextDirection = TextDirection.Ltr,
    placeholder: String,
    icon: @Composable (() -> Unit)? = null
) {
    TextField(
        value = state.value,
        onValueChange = { input ->
            state.value = input
        },
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 16.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Red,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorPlaceholderColor = Color.Red,
            errorContainerColor = Color.White
        ),
        textStyle = TextStyle(textDirection = direction),
        placeholder = {
            Text(
                text = placeholder,
                fontSize = 12.sp,
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(textDirection = TextDirection.Rtl)
            )
        },
        isError = error,
        trailingIcon = icon
    )
}