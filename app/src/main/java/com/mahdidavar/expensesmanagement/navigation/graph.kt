package com.mahdidavar.expensesmanagement.navigation

/*
@Composable
fun NavGraph2(
    modifier: Modifier = Modifier ,
    navHostController: NavHostController){
    SharedTransitionLayout(modifier = Modifier.fillMaxSize()) {
        NavHost(
            modifier = modifier ,
            navController = navHostController ,
            startDestination = Routes.SplashScreen
        ){
            composable<Routes.HomeScreen> {
                HomeScreen(
                    navController = navHostController ,
                    sharedTransitionScope = this@SharedTransitionLayout ,
                    animatedContentScope = this@composable
                )
            }
            composable <Routes.SettingScreen>{
                SettingScreen(
                    navController = navHostController ,
                    sharedTransitionScope = this@SharedTransitionLayout ,
                    animatedContentScope = this@composable
                )
            }
            composable <Routes.ChartScreen>{
                ChartScreen(
                    navController = navHostController ,
                    sharedTransitionScope = this@SharedTransitionLayout ,
                    animatedContentScope = this@composable
                )
            }
            composable <Routes.ShowInvoicesScreen>{
                ShowInvoiceScreen(
                    navController = navHostController ,
                    sharedTransitionScope = this@SharedTransitionLayout ,
                    animatedContentScope = this@composable
                )
            }
            composable <Routes.SplashScreen>{
                SplashScreen(
                    navController = navHostController
                )
            }
            composable <Routes.LoginScreen>{
                LoginScreen(
                    navController = navHostController
                )
            }
            composable <Routes.EditProfileScreen>{
                EditProfile()
            }
        }
    }
}

private data class NavigationItem(
    val name: String,
    val routes: Routes,
    val icon: ImageVector
)



@Composable
fun NewBottomNavigation(
    navHostController: NavHostController
) {
    val newNavItem = listOf(
        NavigationItem(
            name = "خانه",
            routes = Routes.HomeScreen,
            icon = Icons.Filled.Home
        ),
        NavigationItem(
            name = "تنظیمات",
            routes = Routes.SettingScreen,
            icon = Icons.Filled.Settings
        ),
        NavigationItem(
            name = "آمار",
            routes = Routes.ChartScreen,
            icon =  Icons.Filled.BarChart
        ),
        NavigationItem(
            name = "فاکتورها",
            routes = Routes.ShowInvoicesScreen,
            icon = Icons.AutoMirrored.Filled.List
        )
        )

    val backStackEntry = navHostController.currentBackStackEntryAsState()
    val fullRoute = backStackEntry.value?.destination?.route
    val currentRoute =
        fullRoute?.substringAfterLast(".")?.substringBefore("?")// فقط اسم ساده

    val isShow = newNavItem.any { it.routes.toString().substringBefore("(") == currentRoute }
    AnimationVisibility(
        isShow = isShow
    ) {
        NavigationBar(
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color.White,
        ) {
            newNavItem.forEach { nav ->
                NavigationBarItem(
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        indicatorColor = Color(0xffEF472C),
                        unselectedIconColor = Color.Black,
                        selectedTextColor = Color(0xffEF472C),
                        unselectedTextColor = Color.Black
                    ),
                    selected = currentRoute == nav.routes.toString().substringBefore("(") ,
                    icon = {
                        Icon(
                            imageVector = (nav.icon),
                            contentDescription = "",
                            modifier = Modifier.size(22.dp)
                        )
                    },
                    label = {
                        Text(
                            text = nav.name,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    onClick = { navHostController.navigateSingle(nav.routes) }
                )
            }
        }

    }
}

private fun NavHostController.navigateSingle(routes: Routes) {
    navigate(routes) {
        popUpTo<Routes.HomeScreen> {
            inclusive = false
        }
        launchSingleTop =true
    }
}

 */