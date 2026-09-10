@file:Suppress("DEPRECATION")

package com.mahdidavar.expensesmanagement.ui.screens

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.mahdidavar.expensesmanagement.R
import com.mahdidavar.expensesmanagement.db.entity.Gender
import com.mahdidavar.expensesmanagement.navigation.BottomNavigation
import com.mahdidavar.expensesmanagement.navigation.Routes
import com.mahdidavar.expensesmanagement.ui.theme.DarkBlue
import com.mahdidavar.expensesmanagement.ui.theme.DarkBlueLight
import com.mahdidavar.expensesmanagement.ui.theme.NewBlue
import com.mahdidavar.expensesmanagement.ui.theme.yekan
import com.mahdidavar.expensesmanagement.viewModel.SettingViewModel
import com.mahdidavar.expensesmanagement.viewModel.UserViewModel

@Composable
fun SettingScreen(
    navController: NavHostController
) {
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SettingHeader(navController)
            SettingProfile()
            SettingMenu()
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

@Composable
private fun SettingHeader(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        DarkBlue,
                        NewBlue
                    )
                ),
                shape = RoundedCornerShape(bottomEnd = 40.dp, bottomStart = 40.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.setting_nav_title),
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                fontFamily = yekan
            )
            Spacer(Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                IconButton(onClick = {
                    navController.navigate(Routes.EditProfilePage) {
                        popUpTo(Routes.SettingPage) { inclusive = false }
                        launchSingleTop = true
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Icon",
                        tint = Color.White,
                        modifier = Modifier.size(35.dp)
                    )
                }
                Surface(
                    shape = CircleShape,
                    border = BorderStroke(width = 3.dp, color = MaterialTheme.colorScheme.primary)
                ) {
                    Image(
                        painterResource(R.drawable.avatar_profile),
                        contentDescription = "Profile Image",
                        modifier = Modifier.size(100.dp)
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.CameraAlt,
                        contentDescription = "Camera Icon",
                        tint = Color.White,
                        modifier = Modifier.size(35.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun SettingProfile(
    viewModel: UserViewModel = hiltViewModel() ,
    settingViewModel: SettingViewModel = hiltViewModel()
) {
    val user by viewModel.getUser().collectAsStateWithLifecycle(null)
    val language by settingViewModel.languageState.collectAsStateWithLifecycle()
    CompositionLocalProvider(
        LocalLayoutDirection provides
                if (language == "fa") LayoutDirection.Rtl
                else LayoutDirection.Ltr
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(horizontal = 5.dp, vertical = 10.dp),
            shape = RoundedCornerShape(30.dp),
            colors = CardDefaults.cardColors(
                containerColor = DarkBlueLight
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp, vertical = 15.dp)
            ) {
                CustomTextForm(txt = stringResource(R.string.username_title) + " :")
                CustomTextForm(
                    txt = user?.username ?: "",
                    position = Arrangement.End
                )
                CustomTextForm(txt = stringResource(R.string.email_title) + " :")
                CustomTextForm(
                    txt = user?.email ?: "",
                    position = Arrangement.End,
                  //  style = TextStyle(textDirection = TextDirection.Ltr)
                )
                CustomTextForm(txt = stringResource(R.string.phone_title) + " :")
                CustomTextForm(
                    txt = user?.phone ?: "",
                    position = Arrangement.End
                )
                CustomTextForm(txt = stringResource(R.string.birthday_title) + " :")
                CustomTextForm(
                    txt = user?.birthday ?: "",
                    position = Arrangement.End
                )
                CustomTextForm(txt = stringResource(R.string.gender_title) + " :")
                CustomTextForm(
                    txt = if (user?.gender == Gender.Male) "مرد" else if (user?.gender == Gender.Female) "زن" else "",
                    position = Arrangement.End
                )
            }
        }
    }
}

@SuppressLint("ContextCastToActivity")
@Composable
fun SettingMenu(
    viewModel: SettingViewModel = hiltViewModel()
) {
    val darkTheme by viewModel.themeStatus.collectAsStateWithLifecycle()
    val language by viewModel.languageState.collectAsStateWithLifecycle()
    val activity = LocalContext.current as Activity
    CompositionLocalProvider(
        LocalLayoutDirection provides
                if (language == "fa") LayoutDirection.Rtl
                else LayoutDirection.Ltr
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(horizontal = 5.dp, vertical = 10.dp),
            shape = RoundedCornerShape(30.dp),
            colors = CardDefaults.cardColors(
                containerColor = DarkBlueLight
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 50.dp , horizontal = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LanguageDropDown(
                    currentLang = language,
                    onLangSelected = {
                        viewModel.chaneLanguage(it) {
                            activity.recreate()
                        }
                    }
                )
                Spacer(Modifier.height(15.dp))
                ThemeDropDown(
                    currentTheme = darkTheme,
                    onThemeSelected = viewModel::changeTheme
                )
            }
        }
    }
}


@Composable
private fun CustomTextForm(
    txt: String,
    position: Arrangement.Horizontal = Arrangement.Start
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = position
    ) {
        Text(
            text = txt,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 16.sp,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageDropDown(currentLang: String, onLangSelected: (String) -> Unit) {
    var expanded by remember {
        mutableStateOf(false)
    }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {

        OutlinedTextField(
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            textStyle = TextStyle(textDirection = TextDirection.Rtl),
            value = when (currentLang) {
                "fa" -> "فارسی"
                else -> "English"
            },
            onValueChange = {},
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedTrailingIconColor = Color.White,
                focusedTrailingIconColor = Color.White
            ),
            readOnly = true,
            label = { Text(text = stringResource(R.string.language_setting_title)) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            shape = RoundedCornerShape(10.dp),
            containerColor = Color.LightGray
        ) {
            DropdownMenuItem(
                text = { Text("فارسی", color = Color.Black) },
                onClick = {
                    onLangSelected("fa")
                    expanded = false
                }
            )
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            DropdownMenuItem(
                text = {
                    Text(text = "English", color = Color.Black)
                },
                onClick = {
                    onLangSelected("en")
                    expanded = false
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeDropDown(currentTheme: Boolean, onThemeSelected: (Boolean) -> Unit) {
    var expanded by remember {
        mutableStateOf(false)
    }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            value = when (currentTheme) {
                true -> stringResource(R.string.theme_dark_title)
                else ->stringResource(R.string.theme_light_title)
            },
            onValueChange = {},
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedTrailingIconColor = Color.White,
                focusedTrailingIconColor = Color.White
            ),
            readOnly = true,
            label = { Text(text = stringResource(R.string.theme_setting_title)) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            shape = RoundedCornerShape(10.dp),
            containerColor = Color.LightGray
        ) {
            DropdownMenuItem(
                text = { Text(stringResource(R.string.theme_light_title), color = Color.Black) },
                onClick = {
                    onThemeSelected(false)
                    expanded = false
                }
            )
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            DropdownMenuItem(
                text = {
                    Text(text = stringResource(R.string.theme_dark_title), color = Color.Black)
                },
                onClick = {
                    onThemeSelected(true)
                    expanded = false
                }
            )
        }
    }

}

/*
@Composable
fun ProfileHeader(viewModel: UserViewModel= hiltViewModel(), onClick: () -> Unit) {
    val user by viewModel.getUser().collectAsState(null)
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {uri : Uri? ->
        uri?.let {
            viewModel.updateUserAvatar(avatar = it.toString() , userId = user!!.id)
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        Color(0xFFE32A0D),
                        Color(0xFFFD937F)
                    )
                ),
                shape = RoundedCornerShape(bottomEnd = 40.dp, bottomStart = 40.dp)
            )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(10.dp))
            androidx.compose.material3.Text(
                text = "پروفایل کاربری",
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                fontFamily = yekan
            )
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    launcher.launch("image/*")
                }) {
                    Icon(
                        painter = painterResource(R.drawable.group249),
                        contentDescription = "Camera Icon",
                        tint = Color.White, modifier = Modifier.size(35.dp)
                    )
                }
                Surface(shape = CircleShape, border = BorderStroke(3.dp, Color.White)) {
                    AsyncImage(
                        model = user?.avatar?: R.drawable.avatar_profile ,
                        contentDescription = "Profile Image",
                        modifier = Modifier.size(100.dp)
                    )
                }
                IconButton(onClick = onClick) {
                    Icon(
                        painter = painterResource(R.drawable.group250),
                        contentDescription = "Edit Icon",
                        tint = Color.White, modifier = Modifier.size(35.dp)
                    )
                }
            }
            Spacer(Modifier.height(8.dp))
            androidx.compose.material3.Text(
                text = user?.username ?: "نام کاربر",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(Modifier.height(5.dp))
            androidx.compose.material3.Text(
                text = user?.phone ?: "شماره کاربر",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}*/