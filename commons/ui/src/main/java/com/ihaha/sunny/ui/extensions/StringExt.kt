package com.ihaha.sunny.base.exception

import android.os.Build
import android.text.Html
import android.text.Spanned
import java.nio.charset.Charset
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.regex.Pattern

//private const val phoneNumberPattern = "^\\s*(010|011|016|017|018|019)(-|\\)|\\s)*(\\d{3,4})(-|\\s)*(\\d{4})\\s*$"
private const val newPhoneNumberPattern = "^[0-9]{3}[0-9]{4}[0-9]{4}$"
private const val oldPhoneNumberPattern = "^[0-9]{3}[0-9]{3}[0-9]{4}$"
private const val pattern = "yyyy/MM/dd hh:mm"
private const val passwordPattern = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{6,}$"

fun String?.invalidPhoneNumber(): Boolean = this?.trim()?.let { newPhoneNumberPattern.toRegex().matches(it) || oldPhoneNumberPattern.toRegex().matches(it) } ?: false

fun String?.invalidEmail(): Boolean = this?.trim()?.let { android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches() } ?: false

fun String?.invalidEmailToCallingUserAccounts(): Boolean = this?.trim()?.let { EMAIL_ADDRESS.matcher(it).matches() } ?: false

fun String?.invalidHardPassword(): Boolean = this?.trim()?.let { passwordPattern.toRegex().matches(it) } ?: false

fun String?.invalidNormalPassword(): Boolean = this?.isNotEmpty() ?: false

fun String?.invalidString(): Boolean = this?.isNotEmpty() ?: false


val EMAIL_ADDRESS = Pattern.compile(
     "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+\\**]{1,256}" +
     "\\@" +
     "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
     "(" +
     "\\." +
     "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
     ")+"
)
// endregion

fun String.generateName(): String {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy/HH:mm:ss")
        val localDateTime = LocalDateTime.now()
        return dateFormat.format(localDateTime)
    }
    return randomString()
}

fun randomString(): String {
    val array = ByteArray(7) // length is bounded by 7
    Random().nextBytes(array)
    return String(array, Charset.forName("UTF-8"))
}

//fun String.changeMonth(): String = if (this.length == 1) "0$this" else this

// "달, 일"을 가져와 1~9월이면 01~09 월로, 10~12 월이면 10~12월로 변경(일도 마찬가지)
fun String.plusZero(monthOfYear: Int, dayOfMonth: Int): String {
    var month = (monthOfYear + 1).toString()
    month = if(month.length == 1) "0$month" else month

    var day = dayOfMonth.toString()
    day = if(day.length == 1) "0$day" else day

    return "$this-$month-$day"
}

// html 추가
fun String.toSpanned(): Spanned = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY) else @Suppress("DEPRECATION") Html.fromHtml(this)

