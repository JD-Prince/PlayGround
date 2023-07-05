package com.project.playground.applicationmanager

import com.project.playground.model.Player

interface SportsActivityHostProvider {

    suspend fun getHost(id: Int): Player?
}
