package com.project.playground.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.project.playground.view.mainscreen.myground.MyActivitiesFragment
import com.project.playground.view.mainscreen.myground.EnrolledGamesViewFragment

class HomeFragmentAdapter (
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val itemsCount: Int,
    private val fragment0: MyActivitiesFragment,
    private val fragment1: EnrolledGamesViewFragment
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {



    override fun getItemCount(): Int {
        return itemsCount
    }

    override fun createFragment(position: Int): Fragment =
        when(position){
            0-> fragment0
            1 -> {
                fragment1
            }
            else -> fragment0
        }

}