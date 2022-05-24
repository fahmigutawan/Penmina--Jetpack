package com.example.penmina.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.penmina.R

val poppinsMedium = FontFamily(Font(R.font.poppins_medium))
val poppinsReguler = FontFamily(Font(R.font.poppins_reguler))
val poppinsLight = FontFamily(Font(R.font.poppins_light))

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = poppinsMedium
    ),
    h2 = TextStyle(
        fontFamily = poppinsReguler
    ),
    h3 = TextStyle(
        fontFamily = poppinsReguler
    ),
    h4 = TextStyle(
        fontFamily = poppinsReguler
    ),
    h5 = TextStyle(
        fontFamily = poppinsReguler
    ),
    h6 = TextStyle(
        fontFamily = poppinsReguler
    ),
    subtitle1 = TextStyle(
        fontFamily = poppinsReguler
    ),
    subtitle2 = TextStyle(
        fontFamily = poppinsLight
    ),
    body1 = TextStyle(
        fontFamily = poppinsLight
    ),
    body2 = TextStyle(
        fontFamily = poppinsReguler
    )
)