package com.project.playground.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.project.playground.enums.Location
import com.project.playground.enums.Sports

/**
 * These are the sports activity created by the user
 * the players list of the activity is stored in a separate class
 *
 * */

@Entity(tableName = "sport_activity_table")
data class SportActivity (

    @ColumnInfo( "title")
    var title : String,

    @ColumnInfo("sport")
    val sports: Sports,
    @ColumnInfo("location")
    var location : Location , // TODO -(LATER)- currently stored as a String later it has to be replace by MAPS API

    val date : String,
    val time : String,

    @ColumnInfo("description")
    var description : String?=null,
    @ColumnInfo("other_instructions")
    var otherInstructions : String?=null,
    @ColumnInfo("number_of_players_required")
    var requiredPlayers : Int, // including the host of the event
    @ColumnInfo("number_of_players_enrolled")
    var enrolledPlayers : Int = 0,
    @ColumnInfo("host")
    var host : Int=0,

    @Transient
    @ColumnInfo("thumbnail")
    var thumbnail : ByteArray? = null,
    @PrimaryKey(autoGenerate = true)
    val eventId : Int=0

    //TODO --> Event Model is Still Pending

//TODO --> need to add some features like organizing a granc game

){

}
