package com.project.playground.view.mainscreen.notifications

import com.project.playground.model.Notification
import com.project.playground.model.SportActivity

interface RequestHandler {
    fun approveRequest(request : Notification.Request,event:SportActivity)
    fun showProfile(playerId: Int)
    fun declineRequest(request: Notification.Request,eventTitle: String)
}