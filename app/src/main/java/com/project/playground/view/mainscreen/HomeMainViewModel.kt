package com.project.playground.view.mainscreen

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.playground.applicationmanager.repositories.UserRepository
import kotlinx.coroutines.launch

class HomeMainViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun logoutUser(edit: SharedPreferences.Editor) {
        viewModelScope.launch {
            userRepository.logoutUser()
        }
        edit.apply{
            remove("USERNAME")
            apply()
        }
    }
}