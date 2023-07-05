package com.project.playground.applicationmanager

import com.project.playground.applicationmanager.database.dao.ActivityDao
import com.project.playground.applicationmanager.database.dao.RegistrationDao
import com.project.playground.applicationmanager.database.dao.UserDao

interface DaoProvider {
    fun getUserDao(): UserDao
    fun getActivityDao():ActivityDao
    fun getRegistrationDao(): RegistrationDao
}