package com.project.playground.view.mainscreen.notifications

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.project.playground.R
import com.project.playground.adapter.HomeFragmentAdapter
import com.project.playground.adapter.NotificationFragmentAdapter
import com.project.playground.adapter.RequestListAdapter
import com.project.playground.applicationmanager.ViewModelFactory
import com.project.playground.databinding.ActivityRequestsHandlerBinding
import com.project.playground.model.Notification
import com.project.playground.view.mainscreen.myground.EnrolledGamesViewFragment
import com.project.playground.view.mainscreen.myground.MyActivitiesFragment

class NotificationHandlerActivity : AppCompatActivity() {

    private var _binding : ActivityRequestsHandlerBinding?=null
    private val binding : ActivityRequestsHandlerBinding
        get()=_binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding= ActivityRequestsHandlerBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setSupportActionBar(binding.toolbar.activityToolbar.also {
            it.navigationIcon=ContextCompat.getDrawable(baseContext,R.drawable.back_ic)
        })
        supportActionBar?.apply {
            title = "Request & Notifications"
            setDisplayHomeAsUpEnabled(true)
        }


        binding.viewPager.apply {
            adapter= NotificationFragmentAdapter(supportFragmentManager,lifecycle)
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.tablayout.selectTab(binding.tablayout.getTabAt(position))

                }
            })

        }
        TabLayoutMediator(binding.tablayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Requests"
                1 -> tab.text = "Notifications"
            }
        }.attach()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home ->{
                //todo save as draft
                finish()
                true
            }

            else->super.onOptionsItemSelected(item)}
    }

    override fun onDestroy() {
        binding.viewPager.adapter=null
        _binding=null
        super.onDestroy()
    }



}