package com.mahdidavar.expensesmanagement.db.utills

import com.mahdidavar.expensesmanagement.R

data class Category(
    val id: Int,
    val name: String,
    val icon: Int,
    val subset: List<String>
)

object CategoryList {
    val categoryItems = listOf(
        Category(
            id = 1,
            name = "خرید روزانه",
            icon = R.drawable.car,
            subset = listOf(
                "اب معدنی",
                "نان",
                "سوپر مارکت",
                "سیگار",
                "مواد غذایی",
                "سایر",
            )
        ),
        Category(
            id = 2,
            name = "هزینه های ماهانه",
            icon = R.drawable.car,
            subset = listOf(
                "باشگاه",
                "لباس" ,
                "دارو",
                "اینترنت",
                "کالا",
                "کتاب",
                "هدیه",
                "سایر",
                )
        ),
        Category(
            id = 3,
            name = "خودرو",
            icon = R.drawable.car,
            subset = listOf("بنزین" , "تعمیرگاه", "جریمه", "بیمه")
        ),
        Category(
            id = 4,
            name = "خوراک",
            icon = R.drawable.car,
            subset = listOf("رستوران", "کافی شاپ" ,"فست فود" , "انلاین")
        ),
        Category(
            id = 5,
            name = "حمل و نقل",
            icon = R.drawable.car,
            subset = listOf("مترو", "تاکسی", "اتوبوس")
        ),
        Category(
            id = 6,
            name = "اجاره",
            icon = R.drawable.car,
            subset = listOf( "خانه" , "مغازه" , "شرکت")
        ),
        Category(
            id = 7,
            name = "تفریح",
            icon = R.drawable.car,
            subset = listOf("کنسرت", "تئاتر", "سینما", "شهربازی" , "گیم نت" , "سایر")
        ),
        Category(
            id = 7,
            name = "قبض",
            icon = R.drawable.car,
            subset = listOf("اب", "برق", "تلفن", "موبایل")
        )
    )
}