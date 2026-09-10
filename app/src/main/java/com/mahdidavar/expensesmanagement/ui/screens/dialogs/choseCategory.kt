package com.mahdidavar.expensesmanagement.ui.screens.dialogs

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahdidavar.expensesmanagement.db.utills.Category
import com.mahdidavar.expensesmanagement.db.utills.CategoryList.categoryItems
import com.mahdidavar.expensesmanagement.ui.theme.ColorPrimaryDark
import com.mahdidavar.expensesmanagement.ui.theme.negare

@Composable
fun ChoseCategory(
    onDismiss: () -> Unit,
    onCategorySelected : (String , String)-> Unit
) {
    Column(
        modifier = Modifier
            .size(width = 300.dp , height = 470.dp)
            .clip(RoundedCornerShape(35.dp))
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally ,
        verticalArrangement = Arrangement.Center
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(categoryItems.size) { index ->
                CategoryRow(
                    item = categoryItems[index],
                    onDismiss = onDismiss  ,
                    onCategorySelected = onCategorySelected )
            }
        }
    }
}

@Composable
fun CategoryRow(
    item: Category,
    onDismiss: () -> Unit,
    onCategorySelected : (String , String)-> Unit
) {
    var state by remember { mutableStateOf(false) }
    val iconRotate by animateFloatAsState(targetValue = if (state) 180f else 0f)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 5.dp)
            .animateContentSize(animationSpec = tween(1200))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier
                    .rotate(iconRotate)
                    .clickable { state = !state })
            Spacer(Modifier.width(10.dp))
            Text(
                text = item.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = negare,
                modifier = Modifier
                    .clickable { state = !state }
            )
            Spacer(Modifier.width(20.dp))
            Icon(
                painter = painterResource(item.icon),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
        }
        if (state) {
            item.subset.forEach { subItem ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = subItem,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = negare,
                        style = TextStyle(textDirection = TextDirection.Rtl),
                        modifier = Modifier.clickable {
                            onCategorySelected(item.name , subItem)
                            onDismiss()
                        }
                    )
                }
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 50.dp),
                    thickness = 1.dp,
                    color = Color.Gray
                )
            }
        }
        HorizontalDivider(
            thickness = 3.dp,
            color = ColorPrimaryDark,
            modifier = Modifier.padding(horizontal = 25.dp)
        )
    }
}
