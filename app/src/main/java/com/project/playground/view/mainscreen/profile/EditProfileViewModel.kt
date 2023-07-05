package com.project.playground.view.mainscreen.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.playground.applicationmanager.repositories.UserRepository
import com.project.playground.enums.EditProfileStatements
import com.project.playground.model.Player
import kotlinx.coroutines.launch

class EditProfileViewModel(private val repository: UserRepository) :ViewModel(){

    private val _result = MutableLiveData<EditProfileStatements>()
    val result : LiveData<EditProfileStatements>
        get()=_result

    var currentUser :Player?=null
        set(player){
            field=player
            bio=field?.bio
            name=field?.alias
            imageArray=field?.profilePicture
            this.username=field?.userName
            phoneNumber=field?.phoneNumber
        }
    var username : String? = null
    var phoneNumber : String? = null
    var bio : String?=null
    var name : String?=null
    var imageArray : ByteArray?=null

    fun loadUserData():LiveData<Player> {

        return repository.getCurrentUserObj()

    }

    fun updateData() {
//        currentUser?.apply {
//            profilePicture=imageArray
//            bio=this@EditProfileViewModel.bio?:""
//            alias=name?:""
////            viewModelScope.launch {
//////                repository.update(this@apply)
////            }
//        }
        viewModelScope.launch {
            _result.value=repository.update(imageArray, bio, name!!, username!!, phoneNumber!!,currentUser!!)
        }


    }
}