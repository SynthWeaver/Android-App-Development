package nl.terwijn.rockpaperscissors.ui

import java.util.*

class DateTime {
    companion object {
        private val date = Date()

        public fun now(): String {
            return String.format("%s %s %s %s %s %s",
                date.day,
                date.month,
                date.date,
                date.time,
                date.toGMTString(),
                date.year
                )
        }
    }
}