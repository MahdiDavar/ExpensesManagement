package com.mahdidavar.expensesmanagement.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mahdidavar.expensesmanagement.R
import com.mahdidavar.expensesmanagement.navigation.BtnNavScreen.Companion.navItem
import com.mahdidavar.expensesmanagement.ui.screens.AddInvoiceScreen
import com.mahdidavar.expensesmanagement.ui.screens.ReportScreen
import com.mahdidavar.expensesmanagement.ui.screens.EditProfile
import com.mahdidavar.expensesmanagement.ui.screens.HomeScreen
import com.mahdidavar.expensesmanagement.ui.screens.LoginScreen
import com.mahdidavar.expensesmanagement.ui.screens.SettingScreen
import com.mahdidavar.expensesmanagement.ui.screens.ShowInvoiceScreen
import com.mahdidavar.expensesmanagement.ui.screens.SplashScreen
import com.mahdidavar.expensesmanagement.ui.theme.DarkBlue
import com.mahdidavar.expensesmanagement.ui.theme.negare
import com.mahdidavar.expensesmanagement.utills.AnimationVisibility


@Composable
fun SetUpNavigation() {
    val navControlling = rememberNavController()
    NavHost(
        navController = navControlling,
        startDestination = Routes.SplashPage,
        enterTransition = {
                    slideInHorizontally(tween(500)) { fullWidth ->
                        -fullWidth
                    }
        },
        exitTransition = {
                    slideOutHorizontally(tween(500)) { fullWidth ->
                        fullWidth
                    }
        }
    ) {
        composable<Routes.HomePage> {
            HomeScreen(navControlling)
        }
        composable<Routes.ShowInvoicesPage> {
            ShowInvoiceScreen(navControlling)
        }
        composable<Routes.SettingPage> {
            SettingScreen(navControlling)
        }
        composable<Routes.ChartPage> {
            ReportScreen(navControlling)
        }
        composable<Routes.AddInvoicesPage>(
            enterTransition = {
                slideInVertically(tween(500)) { fullHeight ->
                -fullHeight
            }} ,
            exitTransition =  {
                slideOutVertically(
                    tween(500)){fullHeight ->
                    fullHeight
                }
            }
        ) {
            AddInvoiceScreen()
        }
        composable<Routes.SplashPage> {
            SplashScreen(navControlling)
        }
        composable<Routes.LoginPage> {
            LoginScreen(navController = navControlling)
        }
        composable<Routes.EditProfilePage>(
            enterTransition = {
                slideInVertically(tween(500)) { fullHeight ->
                    -fullHeight
                }} ,
            exitTransition =  {
                slideOutVertically(
                    tween(500)){fullHeight ->
                    fullHeight
                }
            }
        ) {
            EditProfile()
        }
    }
}

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun BottomNavigation(navController: NavController) {
    val size = LocalConfiguration

    Card(
        modifier = Modifier
            .padding(2.dp)
            .size(width = (size.current.screenWidthDp * 0.75).dp, height = 58.dp),
        colors = CardDefaults.cardColors(containerColor = DarkBlue),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = CircleShape
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BottomNavigationItem(text = stringResource(R.string.setting_nav_title), icon = Icons.Filled.Settings) {
                navController.navigate(Routes.SettingPage) {
                    popUpTo(Routes.HomePage) { inclusive = false }
                    launchSingleTop = true
                }
            }
            BottomNavigationItem(text = stringResource(R.string.chart_nav_title), icon = Icons.Filled.BarChart) {
                navController.navigate(Routes.ChartPage) {
                    popUpTo(Routes.HomePage) { inclusive = false }
                    launchSingleTop = true
                }
            }
            Button(
                onClick = {
                    navController.navigate(Routes.AddInvoicesPage) {
                        popUpTo(Routes.HomePage) { inclusive = false }
                        launchSingleTop = true
                    }
                },
                modifier = Modifier
                    .size(56.dp)
                    .padding(2.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = DarkBlue
                )
            }
            BottomNavigationItem(text = stringResource(R.string.invoices_nav_title), icon = Icons.AutoMirrored.Filled.List) {
                navController.navigate(Routes.ShowInvoicesPage) {
                    popUpTo(Routes.HomePage) { inclusive = false }
                    launchSingleTop = true
                }
            }
            BottomNavigationItem(text =stringResource(R.string.home_nav_title), icon = Icons.Filled.Home) {
                navController.navigate(Routes.HomePage) {
                    popUpTo(Routes.HomePage) { inclusive = false }
                    launchSingleTop = true
                }
            }
        }
    }
}

@Composable
private fun BottomNavigationItem(text: String, icon: ImageVector, onClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClicked)
            .padding(top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = negare,
            color = Color.White
        )
    }
}


private fun NavController.navigateSingle(routes: String) {
    navigate(routes) {
        popUpTo(BtnNavScreen.Home.route) {
            inclusive = false
        }
        launchSingleTop = true
    }
}

@Composable
fun BottomNavigation2(
    navController: NavController
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    val fullRoute = backStackEntry.value?.destination?.route
    val currentRoute =
        fullRoute?.substringAfterLast(".")?.substringBefore("?")// فقط اسم ساده

    val isShow = navItem.any { it.route.substringBefore("(") == currentRoute }
    AnimationVisibility(
        isShow = isShow
    ) {
        NavigationBar(
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color.White,
        ) {
            navItem.forEach { nav ->
                NavigationBarItem(
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        indicatorColor = Color(0xffEF472C),
                        unselectedIconColor = Color.Black,
                        selectedTextColor = Color(0xffEF472C),
                        unselectedTextColor = Color.Black
                    ),
                    selected = currentRoute == nav.route.substringBefore("("),
                    icon = {
                        Icon(
                            imageVector = (nav.icon),
                            contentDescription = "",
                            modifier = Modifier.size(22.dp)
                        )
                    },
                    label = {
                        Text(
                            text = nav.title,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    onClick = { navController.navigateSingle(nav.route) }
                )
            }
        }

    }
}


/*
@SuppressLint("UnrememberedMutableState")
@Composable
fun BottomNavigationBar(nanController: NavController) {
NavigationBar(containerColor = DarkBlue) {
val currentRoute by nanController.currentBackStackEntryAsState()
BtnNavScreen.navItem.forEach { screen ->
NavigationBarItem(
    selected = currentRoute?.destination?.route == screen.route,
    onClick = {
        nanController.navigate(screen.route) {
            popUpTo(BtnNavScreen.Home.route) { inclusive = false }
            launchSingleTop = true
        }
    },
    icon = {
        Icon(
            imageVector = screen.icon,
            contentDescription = screen.title,
            tint = Color.White,
            modifier = Modifier.size(30.dp)
        )
    },
    label = {
        Text(
            text = screen.title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    },
    colors = NavigationBarItemDefaults.colors(
        selectedIconColor = Color.White,
        indicatorColor = DarkBlueLight,
        selectedTextColor = Color.White,
        unselectedIconColor = Color.White,
        unselectedTextColor = Color.Black
    )
)
}
}
}*/