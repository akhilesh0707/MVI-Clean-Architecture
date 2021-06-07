package com.aqube.mvi.utils

import android.annotation.SuppressLint
import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
object DateUtil {
    private val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

    fun getTimeAgo(date: String): String {
        val formattedDate: Date? = inputFormat.parse(date)
        val currentTime = Calendar.getInstance().timeInMillis
        val timeAgo =
            DateUtils.getRelativeTimeSpanString(
                formattedDate?.time ?: 0,
                currentTime,
                DateUtils.MINUTE_IN_MILLIS
            )
        return timeAgo.toString()
    }
}