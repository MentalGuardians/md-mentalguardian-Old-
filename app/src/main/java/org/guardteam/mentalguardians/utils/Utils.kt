package org.guardteam.mentalguardians.utils

import android.text.TextUtils
import android.util.Patterns
import org.guardteam.mentalguardians.domain.model.Day
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun String.isValidEmail(): Boolean =
    !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.toFormat(pattern: String): String {
    val date = LocalDate.parse(this)
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    return formatter.format(date)
}

val listDate: List<Day> by lazy {
    val currentDate = LocalDate.now()
    val startOfSecondWeek = currentDate.plusDays(1)
    (0..6).mapNotNull {
        val currentDateInLoop = startOfSecondWeek.plusDays(it.toLong())
        if (currentDateInLoop.dayOfWeek != DayOfWeek.SATURDAY && currentDateInLoop.dayOfWeek != DayOfWeek.SUNDAY) {
            Day(
                date = currentDateInLoop.toString(),
                day = currentDateInLoop.dayOfMonth,
                dayName = currentDateInLoop.dayOfWeek.toString().substring(0, 3)
            )
        } else {
            null
        }
    }
}

val listTime: List<Pair<String, String>> = listOf(
    Pair("09:00", "am"),
    Pair("11:00", "am"),
    Pair("01:00", "pm"),
    Pair("03:00", "pm"),
    Pair("05:00", "pm"),
    Pair("07:00", "pm"),
)
