package com.mahdidavar.expensesmanagement.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.mahdidavar.expensesmanagement.R
import com.mahdidavar.expensesmanagement.navigation.Routes
import com.mahdidavar.expensesmanagement.ui.theme.negare
import com.mahdidavar.expensesmanagement.viewModel.SplashViewModel
import com.mahdidavar.expensesmanagement.viewModel.UserViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
    viewModel: SplashViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel()
) {
    val user by userViewModel.getUser().collectAsStateWithLifecycle(null)

    LaunchedEffect(Unit) {
        delay(2000)

        navController.navigate(
            if (viewModel.userIsLoggedIn()) Routes.HomePage else Routes.LoginPage
        ) {
            popUpTo(Routes.SplashPage) { inclusive = true }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffFCF3EC)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "welcome",
            fontSize = 35.sp,
            fontFamily = FontFamily.Cursive,
            color = Color.Black
        )
        Text(
            text = stringResource(R.string.welcome_text),
            fontSize = 35.sp,
            fontFamily = negare,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 10.dp)
        )
        Text(
            text = user?.username ?: "",
            fontSize = 35.sp,
            fontFamily = negare,
            color = Color.Black
        )


    }

}