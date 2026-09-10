package com.mahdidavar.expensesmanagement.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.mahdidavar.expensesmanagement.R
import com.mahdidavar.expensesmanagement.navigation.BottomNavigation
import com.mahdidavar.expensesmanagement.navigation.Routes
import com.mahdidavar.expensesmanagement.presentation.screens.BudgetScreen
import com.mahdidavar.expensesmanagement.ui.theme.DarkBlue
import com.mahdidavar.expensesmanagement.ui.theme.negare
import com.mahdidavar.expensesmanagement.ui.theme.yekan
import com.mahdidavar.expensesmanagement.utills.byLocate
import com.mahdidavar.expensesmanagement.utills.byLocateAndSeparator
import com.mahdidavar.expensesmanagement.viewModel.ShowInvoicesViewModel
import com.mahdidavar.expensesmanagement.viewModel.TimeViewModel
import com.mahdidavar.expensesmanagement.viewModel.UserViewModel

@SuppressLint("CompositionLocalNaming")
val size = LocalConfiguration

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: ShowInvoicesViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val user by userViewModel.getUser().collectAsStateWithLifecycle(null)
    Box {
        Image(
            painter = painterResource(R.drawable.main_back),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .fillMaxHeight(0.9f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            /* HomeHeader(
                 totalAmount = uiState.totalAmount.toString()
             )
            BottomPart(
                 navController = navController,
                 count = uiState.invoices.size.toString(),
                 lastInvoice = "10,000",
                 maxInvoice = "10,000"
             )*/
            ShowUserData(username = user?.username ?: stringResource(R.string.username_default))
            Report(uiState.totalAmount.toString())
            ShowBills()
            MyChartData()
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 30.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            BottomNavigation(navController)
        }
    }
}


@Suppress("TYPE_INTERSECTION_AS_REIFIED_WARNING")
@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun BottomPart(
    navController: NavController,
    count: String,
    lastInvoice: String,
    maxInvoice: String,
    viewModel: TimeViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height((size.current.screenHeightDp / 2).dp)
            .clip(RoundedCornerShape(topEnd = 50.dp, topStart = 50.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.LightGray,
                        Color.DarkGray
                    )
                ), alpha = 0.9f
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(25.dp))
        HorizontalDivider(
            thickness = 7.dp,
            color = DarkBlue,
            modifier = Modifier.width((size.current.screenWidthDp / 5).dp)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = (size.current.screenHeightDp / 15).dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Card(
                    modifier = Modifier
                        .size(
                            height = (size.current.screenHeightDp / 5).dp,
                            width = (size.current.screenWidthDp * 0.9).dp
                        )
                        .padding(horizontal = 15.dp)
                        .clickable { navController.navigate(Routes.AddInvoicesPage) },
                    shape = RoundedCornerShape(30.dp)
                )
                {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(DarkBlue)
                            .padding(10.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.receipt),
                                contentDescription = "Add Invoices",
                                modifier = Modifier
                                    .size(45.dp)
                                    .align(Alignment.CenterHorizontally),
                                tint = Color.White
                            )
                            Spacer(Modifier.height(7.dp))
                            Text(
                                text = "ثبت فاکتور جدید",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color.White,
                                fontFamily = yekan
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        HorizontalDivider(thickness = 2.dp, color = Color.White)
                        Spacer(Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            PersonalText(text = viewModel.getHour().byLocate())
                            PersonalText(text = " : ساعت")
                            PersonalText(text = viewModel.getDay().byLocate())
                            PersonalText(text = viewModel.getDayName())
                            PersonalText(text = "امروز")
                        }
                    }
                }

                Card(
                    modifier = Modifier
                        .size(
                            height = (size.current.screenHeightDp / 5).dp,
                            width = (size.current.screenWidthDp * 0.9).dp
                        )
                        .padding(horizontal = 15.dp)
                        .clickable { navController.navigate(Routes.ShowInvoicesPage) },
                    shape = RoundedCornerShape(30.dp)
                )
                {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(DarkBlue)
                            .padding(horizontal = 10.dp, vertical = 5.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.bill),
                            contentDescription = "Show Invoices",
                            modifier = Modifier
                                .size(40.dp)
                                .align(Alignment.CenterHorizontally),
                            tint = Color.White
                        )
                        Text(
                            text = "نمایش فاکتورها",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Color.White,
                            fontFamily = yekan,
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                        HorizontalDivider(thickness = 2.dp, color = Color.White)
                        Spacer(Modifier.height(6.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                PersonalText(text = "بیشترین فاکتور")
                                Spacer(Modifier.height(6.dp))
                                PersonalText(text = maxInvoice.byLocateAndSeparator())
                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                PersonalText(text = "آخرین فاکتور")
                                Spacer(Modifier.height(6.dp))
                                PersonalText(text = lastInvoice.byLocateAndSeparator())
                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                PersonalText(text = "تعداد کل")
                                Spacer(Modifier.height(6.dp))
                                PersonalText(text = count.byLocate())
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PersonalText(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        fontSize = 12.sp
    )
}


@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun HomeHeader(
    totalAmount: String,
    viewModel: UserViewModel = hiltViewModel()
) {
    val user by viewModel.getUser().collectAsStateWithLifecycle(null)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height((size.current.screenHeightDp / 2).dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Surface(
                shape = CircleShape,
                border = BorderStroke(width = 3.dp, color = Color.Red)
            ) {
                Image(
                    painterResource(id = R.drawable.avatar_profile),
                    contentDescription = null,
                    modifier = Modifier.size(130.dp)
                )
            }
            Spacer(Modifier.height(20.dp))
            Text(
                text = user?.username ?: "نام کاربر",
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                color = Color.White,
                fontFamily = negare
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceAround
        )
        {
            Text(
                text = "کل هزینه های ماه جاری",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold, color = Color.White,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 20.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(
                    "تومان",
                    fontSize = 25.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.width(10.dp))
                Text(
                    text = totalAmount.byLocateAndSeparator(),
                    fontSize = 25.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun ShowUserData(username: String) {
    Card(
        modifier = Modifier
            .height(90.dp)
            .fillMaxWidth(0.93f),
        shape = CircleShape,
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1C2230).copy(alpha = 0.75f)
        ),
        elevation = CardDefaults.elevatedCardElevation(50.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp, start = 20.dp, end = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton({}) {
                Icon(Icons.Default.MoreVert, contentDescription = null, tint = Color.White)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = username,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = Color.White,
                    fontFamily = negare,
                    modifier = Modifier.padding(end = 20.dp)
                )
                Surface(
                    shape = CircleShape,
                    border = BorderStroke(width = 3.dp, color = MaterialTheme.colorScheme.primary)
                ) {
                    Image(
                        painterResource(id = R.drawable.avatar_profile),
                        contentDescription = null,
                        modifier = Modifier.size(70.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun Report(
    totalAmount: String
) {
    Card(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth(0.93f),
        shape = CircleShape,
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1C2230)
        ),
        elevation = CardDefaults.elevatedCardElevation(50.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 15.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.home_report_title),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            )
            {
                Text(
                    text = stringResource(R.string.currency_title),
                    fontSize = 25.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.width(10.dp))
                Text(
                    text = totalAmount.byLocateAndSeparator(),
                    fontSize = 25.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun ShowBills() {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
           // .height(230.dp)
        //   .padding(horizontal = 15.dp)
        //  .clickable { navController.navigate(Screens.ShowList.route) }
        ,
        shape = RoundedCornerShape(30.dp)
    )
    {
        /*
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBlue)
                .padding(horizontal = 10.dp, vertical = 5.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.bill),
                contentDescription = "Show Invoices",
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterHorizontally),
                tint = Color.White
            )
            Text(
                text = "نمایش فاکتورها",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = Color.White,
                fontFamily = yekan,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .align(Alignment.CenterHorizontally)
            )
            HorizontalDivider(thickness = 2.dp, color = Color.White)
            Spacer(Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    PersonalText(text = "بیشترین فاکتور")
                    Spacer(Modifier.height(6.dp))
                    //   PersonalText(text = maxInvoice.byLocateAndSeparator())
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    PersonalText(text = "آخرین فاکتور")
                    Spacer(Modifier.height(6.dp))
                    //    PersonalText(text = lastInvoice.byLocateAndSeparator())
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    PersonalText(text = "تعداد کل")
                    Spacer(Modifier.height(6.dp))
                    //       PersonalText(text = count.byLocate())
                }
            }
        }*/
        BudgetScreen()
    }
}

@Composable
fun MyChartData() {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(230.dp),
        shape = RoundedCornerShape(30.dp),
        border = BorderStroke(width = 2.dp, color = Color.Blue)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            ChartView()
        }
    }
}
