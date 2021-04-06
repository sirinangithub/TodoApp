package com.sirinan.todo.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.temporal.ChronoUnit

object DateTimeUtils {
    @SuppressLint("SimpleDateFormat")
    fun toDate(dateString: String): String {
        return try {
            val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(dateString)
            SimpleDateFormat("d MMM").format(date)
        } catch (e: Exception) {
            ""
        }
    }
    @SuppressLint("SimpleDateFormat")
    fun toTime(dateString: String): String {
        return try {
            val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(dateString)
            SimpleDateFormat("HH:mm").format(date)
        } catch (e: Exception) {
            ""
        }
    }
}
