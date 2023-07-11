package com.project.playground.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.project.playground.enums.NoticeTypes

sealed class Notification(
    receiverId: Int,
    ) {
    @Entity
    data class Request(
        val receiverId: Int,
        val senderId: Int,
        val activityId: Int,
        @PrimaryKey(autoGenerate = true)
        val id : Int=0
    ) : Notification(receiverId)
    @Entity
    data class Alert(
        val receiverId: Int,
        @ColumnInfo("activityId")
        val activityId: Int,
        val type: NoticeTypes,
        @PrimaryKey(autoGenerate = true)
        val id : Int =0
    ):Notification(receiverId)

    @Entity
    data class Notice(
        val receiverId: Int,
        val activityId: Int,
        val noticeType:NoticeTypes,
        val activityTitle : String,
        val senderId : Int,
        val senderName : String,
        @PrimaryKey(autoGenerate = true)
        val id : Int = 0
    ):Notification(receiverId){
        var isRead = false
    }

}