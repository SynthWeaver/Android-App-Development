package nl.terwijn.rockpaperscissors.ui

import java.text.DateFormatSymbols
import java.util.*

class DateTime {
    companion object {
        private val date = Date()
        private val calendar = Calendar.getInstance()


        public fun now(): String {
            calendar.time = date
            val day = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())
            val month = getMonth(calendar.get(Calendar.MONTH))

            return String.format("%s %s %s %s:%s:%s %s",
                day,
                month,
                calendar.get(Calendar.DAY_OF_MONTH),
                date.hours,
                date.minutes,
                date.seconds,
                calendar.get(Calendar.YEAR)
            )
        }

        private fun getMonth(num: Int): String {
            var month = "wrong"
            val dfs = DateFormatSymbols()
            val months = dfs.getMonths()
            if (num >= 0 && num <= 11) {
                month = months[num]
            }
            return month
        }
    }
}