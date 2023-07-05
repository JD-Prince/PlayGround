package com.project.playground.view.mainscreen.myground

import androidx.lifecycle.ViewModel
import com.project.playground.applicationmanager.repositories.ActivityRepository

class MyActivitiesViewModel(private val activityRepository: ActivityRepository) : ViewModel() {

    val myActivitiesList
        get() = activityRepository.loadMyActivities()




}