package com.androidcourse.reminderlevel4kotlin.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.androidcourse.reminderlevel4kotlin.model.Reminder


@Dao
interface ReminderDao {

    @Query("SELECT * FROM reminderTable")
    fun getAllReminders(): LiveData<List<Reminder>>

    @Insert
    fun insertReminder(reminder: Reminder)

    @Delete
    suspend fun deleteReminder(reminder: Reminder)

    @Update
    suspend fun updateReminder(reminder: Reminder)

}