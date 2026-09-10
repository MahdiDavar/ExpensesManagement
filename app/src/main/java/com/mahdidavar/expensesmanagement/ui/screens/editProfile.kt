package com.mahdidavar.expensesmanagement.ui.screens

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mahdidavar.expensesmanagement.R
import com.mahdidavar.expensesmanagement.db.entity.Gender
import com.mahdidavar.expensesmanagement.ui.screens.dialogs.MyConfirmDialog
import com.mahdidavar.expensesmanagement.ui.theme.DarkBlue
import com.mahdidavar.expensesmanagement.ui.theme.DarkBlueLight
import com.mahdidavar.expensesmanagement.ui.theme.NewBlue
import com.mahdidavar.expensesmanagement.ui.theme.negare
import com.mahdidavar.expensesmanagement.ui.theme.yekan
import com.mahdidavar.expensesmanagement.viewModel.SettingViewModel
import com.mahdidavar.expensesmanagement.viewModel.UserViewModel
import info.alirezaahmadi.persian_date_picker.controller.OnDatePickerEvents
import info.alirezaahmadi.persian_date_picker.view.PersianDatePickerDialog


@SuppressLint("UnrememberedMutableState")
@Composable
fun EditProfile(
    viewModel: UserViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EditProfileHeader()
        Spacer(Modifier.height(20.dp))
        FormApp(
            username = viewModel.username,
            onUsernameChanged = viewModel::onUsernameChange,
            phone = viewModel.phone,
            onPhoneChanged = viewModel::onPhoneChange,
            email = viewModel.email,
            onEmailChanged = viewModel::onEmailChange,
            birthday = viewModel.birthday,
            onBirthdayChanged = viewModel::onBirthdayChange,
            onGenderChanged = viewModel::onGenderChange
        )
        Spacer(Modifier.height(20.dp))
    }
}

@Composable
private fun EditProfileHeader(
    viewModel: UserViewModel = hiltViewModel()
) {
    val user by viewModel.getUser().collectAsStateWithLifecycle(null)

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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.profile_title),
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                fontFamily = yekan
            )
            Spacer(Modifier.height(15.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = user?.username ?: stringResource(R.string.username_default),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontFamily = negare
                )
                Spacer(Modifier.width(20.dp))
                Surface(shape = CircleShape, border = BorderStroke(3.dp, Color.White)) {
                    /*   AsyncImage(
                           model = user?.avatar?: R.drawable.avatar_profile ,
                           contentDescription = "Profile Image",
                           modifier = Modifier.size(100.dp)
                       )*/
                    Image(
                        painterResource(R.drawable.avatar_profile),
                        contentDescription = "Profile Image",
                        modifier = Modifier.size(100.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun CustomTextField(
    state: String,
    onStateChange: (String) -> Unit,
    error: Boolean = false,
    placeholder: String,
    type: KeyboardType = KeyboardType.Text,
    icon: @Composable (() -> Unit)? = null
) {
    TextField(
        value = state,
        onValueChange = onStateChange,
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
        placeholder = {
            Text(
                text = placeholder,
                fontSize = 12.sp,
                modifier = Modifier.fillMaxWidth()
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = type),
        isError = error,
        trailingIcon =  icon
    )
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun FormApp(
    viewModel: UserViewModel = hiltViewModel(),
    settingViewModel: SettingViewModel = hiltViewModel(),
    username: String,
    onUsernameChanged: (String) -> Unit,
    email: String,
    onEmailChanged: (String) -> Unit,
    phone: String,
    onPhoneChanged: (String) -> Unit,
    birthday: String,
    onBirthdayChanged: (String) -> Unit,
    onGenderChanged: (Gender) -> Unit
) {
    val language by settingViewModel.languageState.collectAsStateWithLifecycle()
    val user by viewModel.getUser().collectAsStateWithLifecycle(null)
    var showSuccess by remember { mutableStateOf(false) }
    val success = viewModel.success
    var birthdayPicker by remember { mutableStateOf(false) }
    val stateGender = remember { mutableStateListOf(false, false) }
    val stateNameError = mutableStateOf(false)
    CompositionLocalProvider(
        LocalLayoutDirection provides
                if (language == "fa") LayoutDirection.Rtl
                else LayoutDirection.Ltr
    ) {
        Text(
            text = stringResource(R.string.username_title) + " :",
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        )
        Spacer(Modifier.height(10.dp))
        CustomTextField(
            state = username,
            onStateChange = onUsernameChanged,
            error = stateNameError.value,
            placeholder = stringResource(R.string.placeholder_username)
        )
        Spacer(Modifier.height(25.dp))
        Text(
            text = stringResource(R.string.email_title) + " :",
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        )
        Spacer(Modifier.height(10.dp))
        CustomTextField(
            state = phone,
            onStateChange = onPhoneChanged,
            placeholder = stringResource(R.string.placeholder_phone_number),
            type = KeyboardType.Number
        )
        Spacer(Modifier.height(25.dp))
        Text(
            text = stringResource(R.string.phone_title) + " :",
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        )
        Spacer(Modifier.height(10.dp))
        CustomTextField(
            state = email,
            onStateChange = onEmailChanged,
            placeholder = stringResource(R.string.placeholder_email),
            type = KeyboardType.Email
        )
        Spacer(Modifier.height(25.dp))
        Text(
            text = stringResource(R.string.birthday_title) + " :",
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        )
        Spacer(Modifier.height(10.dp))
        CustomTextField(
            state = birthday,
            onStateChange = onBirthdayChanged,
            placeholder = stringResource(R.string.placeholder_birthday),
            icon = {
                IconButton({
                    birthdayPicker = true
                }) {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = null,
                        tint = Color.DarkGray
                    )
                }
            })
        Spacer(Modifier.height(25.dp))
        Text(
            text = stringResource(R.string.gender_title) + " :",
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = stringResource(R.string.male_title))
            RadioButton(
                selected = stateGender[0],
                onClick = {
                    stateGender[0] = true
                    stateGender[1] = false
                    onGenderChanged(Gender.Male)
                },
                modifier = Modifier.scale(0.8f),
                colors = RadioButtonDefaults.colors(selectedColor = DarkBlue)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(text = stringResource(R.string.female_title))
            RadioButton(
                selected = stateGender[1],
                onClick = {
                    stateGender[0] = false
                    stateGender[1] = true
                    onGenderChanged(Gender.Female)
                },
                modifier = Modifier.scale(0.8f),
                colors = RadioButtonDefaults.colors(selectedColor = DarkBlue)
            )
        }
        Spacer(Modifier.height(20.dp))
        CustomButton(text = stringResource(R.string.save_button)) {
            viewModel.submitUpdate()
        }

        LaunchedEffect(Unit) {
            success.collect {
                showSuccess = it
            }
        }
        if (birthdayPicker) PersianDatePickerDialog(
            onDismissRequest = { birthdayPicker = false },
            controller = object : OnDatePickerEvents {
                override fun onConfirmButtonClick(
                    year: Int, month: Int, day: Int
                ) {
                    val picked = "$year/$month/$day"
                    onBirthdayChanged(picked)
                    birthdayPicker = false
                }

                override fun onClose() {
                    birthdayPicker = false
                }
            })
        if (showSuccess) {
            Dialog(onDismissRequest = {}) {
                MyConfirmDialog { showSuccess = false }
            }
        }
    }
}


@Composable
fun CustomButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = DarkBlueLight, contentColor = Color.White
        )
    ) {
        Text(text = text, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
    }
}
