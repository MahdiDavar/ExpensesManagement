package com.mahdidavar.expensesmanagement.presentation.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CarRepair
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material.icons.rounded.DirectionsBus
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.LocalHospital
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material.icons.rounded.Paid
import androidx.compose.material.icons.rounded.Restaurant
import androidx.compose.material.icons.rounded.School
import androidx.compose.material.icons.rounded.ShoppingBag
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material.icons.rounded.Train
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

enum class ExpensesCategory(
    val title : String ,
    val color : Color ,
    val icon : ImageVector
) {
    DAILY_SHOPPING(
        "خرید روزانه",
        Color(0xFFE91E63) ,
        Icons.Rounded.ShoppingBag
    ),
    MONTHLY_SHOPPING(
        "هزینه های ماهانه",
        Color(0xFF1A237E) ,
        Icons.Rounded.ShoppingCart
    ),
    CAR(
        "خودرو",
        Color(0xFF1A237E) ,
        Icons.Rounded.CarRepair
    ),
    FOOD(
        "خوراک",
        Color(0xFFFF9800) ,
        Icons.Rounded.Restaurant
    ),

    TRANSPORT(
        "حمل و نقل",
        Color(0xFF2196F3) ,
        Icons.Rounded.DirectionsBus
    ),

    HOME(
        "اجاره",
        Color(0xFF4CAF50) ,
        Icons.Rounded.Home
    ),
    ENTERTAINMENT(
        "تفریح",
        Color(0xFF9C27B0) ,
        Icons.Rounded.Movie
    ),
    Bill(
        "قبض",
        Color(0xFF13DDEA),
        Icons.Rounded.Paid
    ),

    HEALTH(
        "سلامت",
        Color(0xFFF44336) ,
        Icons.Rounded.LocalHospital
    ),

    EDUCATION(
        "آموزش",
        Color(0xFF3F51B5),
        Icons.Rounded.School
    ),

    OTHER(
        "سایر",
        Color.Gray ,
        Icons.Rounded.Category
    );
    companion object{
        fun fromTitle(title : String): ExpensesCategory{
            return entries.firstOrNull{
                it.title == title
            } ?: OTHER
        }
    }
}