package kanti.testonlineshop.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import kanti.testonlineshop.R

val fontFamily = FontFamily(
    listOf(
        Font(R.font.sf_pro_display_medium, FontWeight.Medium),
        Font(R.font.sf_pro_display_regular, FontWeight.Normal)
    )
)

// Set of Material typography styles to start with
val Typography = Typography()

val Typography.largeTitle1: TextStyle get() = TextStyle(
    fontFamily = fontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 20.sp
)

val Typography.title1: TextStyle get() = TextStyle(
    fontFamily = fontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp
)

val Typography.title2: TextStyle get() = TextStyle(
    fontFamily = fontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp
)

val Typography.title3: TextStyle get() = TextStyle(
    fontFamily = fontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 12.sp
)

val Typography.title4: TextStyle get() = TextStyle(
    fontFamily = fontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp
)

val Typography.text1: TextStyle get() = TextStyle(
    fontFamily = fontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp
)

val Typography.caption1: TextStyle get() = TextStyle(
    fontFamily = fontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 10.sp
)

val Typography.button1: TextStyle get() = TextStyle(
    fontFamily = fontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 12.sp
)

val Typography.button2: TextStyle get() = TextStyle(
    fontFamily = fontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp
)

val Typography.element: TextStyle get() = TextStyle(
    fontFamily = fontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 9.sp
)

val Typography.price: TextStyle get() = TextStyle(
    fontFamily = fontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 24.sp
)

val Typography.placeholder: TextStyle get() = TextStyle(
    fontFamily = fontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp
)

val Typography.link: TextStyle get() = TextStyle(
    fontFamily = fontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 10.sp,
    textDecoration = TextDecoration.Underline
)