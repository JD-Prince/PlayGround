package com.project.playground.view.mainscreen.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.project.playground.applicationmanager.repositories.RegistrationRepository
import com.project.playground.model.Notification

class NoticeListViewModel(private val noticeRepo : RegistrationRepository):ViewModel() {
    fun getAllNotice(): LiveData<List<Notification.Notice>> {
        return noticeRepo.getAllNotices()
    }
}