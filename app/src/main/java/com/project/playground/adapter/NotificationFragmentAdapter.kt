package com.project.playground.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.project.playground.view.mainscreen.notifications.NoticeFragment
import com.project.playground.view.mainscreen.notifications.RequestsListFragment

class NotificationFragmentAdapter(fragamentManager : FragmentManager,lifecycle : Lifecycle) : FragmentStateAdapter(fragamentManager,lifecycle){
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment =
        when(position){
            1->NoticeFragment()
            else->RequestsListFragment()
        }

}