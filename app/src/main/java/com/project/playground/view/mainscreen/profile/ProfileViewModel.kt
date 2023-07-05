package com.project.playground.view.mainscreen.profile

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.playground.model.Player
import com.project.playground.applicationmanager.UserRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository : UserRepository) : ViewModel(){
    fun getUserData() : LiveData<Player> {
        return repository.getCurrentUserObj()
    }

    fun logoutUser(edit: SharedPreferences.Editor) {
        viewModelScope.launch {
            repository.logoutUser()
        }
        edit.apply{
            remove("USERNAME")
            apply()
        }
    }



}