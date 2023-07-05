package com.project.playground.applicationmanager.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.project.playground.model.ParticipantsRegister
import com.project.playground.model.SportActivity
import com.project.playground.model.Player
import com.project.playground.applicationmanager.database.dao.ActivityDao
import com.project.playground.applicationmanager.database.dao.RegistrationDao
import com.project.playground.applicationmanager.database.dao.UserDao
import com.project.playground.model.Notification
import com.project.playground.model.PendingRegistrations

@Database(
    entities = [
        Player::class,
        SportActivity::class,
        ParticipantsRegister::class,
        Notification.Request::class,
        Notification.Notice::class,
        PendingRegistrations::class
    ],
    version = 1, exportSchema = false
)
abstract class Database : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun activityDao(): ActivityDao
    abstract fun registrationDao():RegistrationDao
}