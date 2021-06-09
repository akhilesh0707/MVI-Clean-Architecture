package com.aqube.mvi.common.utils

import android.annotation.SuppressLint
import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
object DateUtil {
    private val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    private val displayFormat = SimpleDateFormat("MMMM d, yyyy / hh:mm aa")

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

    fun getDisplayDate(date: String): String {
        val formattedDate: Date = inputFormat.parse(date) ?: Date()
        return displayFormat.format(formattedDate)
    }
}