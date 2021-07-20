package com.currencyconverter.featuremain.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.dateAndTimeShort(): String =
    SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).format(this)
