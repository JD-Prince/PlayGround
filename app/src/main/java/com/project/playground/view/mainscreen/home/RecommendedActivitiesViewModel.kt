package com.project.playground.view.mainscreen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.project.playground.model.SportActivity
import com.project.playground.applicationmanager.repositories.ActivityRepository

class RecommendedActivitiesViewModel(private val activityRepository: ActivityRepository): ViewModel() {
    val recommednedActivities : LiveData<List<SportActivity>> = activityRepository.getAllRecommendedActivities()
//    fun getAllRecommendedActivities(): LiveData<List<SportActivity>> {
//        return activityRepository.getAllRecommendedActivities()
//    }
}