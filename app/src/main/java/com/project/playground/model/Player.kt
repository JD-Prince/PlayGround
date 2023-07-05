package com.project.playground.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * The User Object is Stored as a Value based class
 * the favourite sports of the user is stored in a seprate class
 * */

@Entity(tableName = "user_data_table")
data class Player(
    @ColumnInfo("username")
    var userName : String,

    @ColumnInfo("password")
    var password : String,
    @ColumnInfo("phone_number")
    var phoneNumber : String = "",
    @ColumnInfo("alias_name")
    var alias : String="",
    @PrimaryKey(autoGenerate = true)
    val playerId : Int = 0

){

    @ColumnInfo("pals")
    var pals = 0
    @ColumnInfo("total_events")
    var totalEvents = 0
    @ColumnInfo("points")
    var points = 0
    @ColumnInfo("bio")
    var bio : String=""
    var organizingEventCount : Int = 0
    var enrolledEventCount: Int = 0

    @ColumnInfo("profile_pic")
    var profilePicture : ByteArray?=null

}
