package com.project.playground.view.mainscreen.activityhandler

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.playground.R
import com.project.playground.adapter.PlayerListViewAdapter
import com.project.playground.applicationmanager.ViewModelFactory
import com.project.playground.databinding.ActivityShowSportActivityBinding
import com.project.playground.enums.ConfrimationStatus
import com.project.playground.enums.MonthOfTheYear
import com.project.playground.enums.UserViewMode
import com.project.playground.model.SportActivity
import com.project.playground.view.dialogues.ConfrimationDialogueFragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ShowSportsActivity : AppCompatActivity() {
    private var _binding  : ActivityShowSportActivityBinding? = null
    private val binding : ActivityShowSportActivityBinding
        get()=_binding!!
    private val viewModel : ShowSportsViewModel by viewModels{
        ViewModelFactory
    }
    private var status :String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        status = intent.getStringExtra("VIEW_MODE")
        var playerListAdapter = PlayerListViewAdapter(true.takeIf { status==UserViewMode.HOST.toString() }?:false , viewModel.currentUserId!!)

        _binding = ActivityShowSportActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(status==UserViewMode.HOST.toString()){

            binding.activityToolbar.activityToolbar.inflateMenu(R.menu.show_game_activity_menu)

            binding.cancelBtn.visibility=View.VISIBLE
            binding.enrollButton.visibility=View.GONE
            binding.cancelBtn.setOnClickListener{
                ConfrimationDialogueFragment("Are you sure want to cancel the event ?",object : ConfrimationDialogueFragment.ProcessExecutor{
                    override fun execute(result: ConfrimationStatus) {
                        viewModel.deleteEvent()
                        finish()
                    }

                }).show(supportFragmentManager,"CANCEL_EVENT_DIALOGUE")

            }
        }

        binding.emptyFieldCaption.visibility=View.GONE
//        binding.expandedSec.visibility=View.GONE

//        binding.activityToolbar.activityToolbar.setOnMenuItemClickListener {
//            when(it.itemId){
//                R.id.edit_game_ic->{
//                    EditActivityFragment(object : EditActivityFragment.GameHandler{
//                        override fun getGame(): SportActivity {
//                            return viewModel.selectedEvent.value?.activity!!
//                        }
//
//                        override fun updateGame(game: SportActivity) {
//                            viewModel.updateGame(game)
//                        }
//
//                    }, viewModel.selectedEvent.value?.activity?.enrolledPlayers).show(supportFragmentManager,"EDIT_ACTIVITY_FRAGMENT")
//                }
//                else->{}
//            }
//            true
//        }

        var isExpanded = false


        binding.enrollButton.apply {
            when(status){
                UserViewMode.HOST.toString()->{
                    text="CANCEL EVENT"
                    setTextColor(ContextCompat.getColor(baseContext, com.google.android.material.R.color.m3_ref_palette_dynamic_neutral50))
                    background=ContextCompat.getDrawable(baseContext,R.drawable.button_bg)
                }
                UserViewMode.ENROLLED.toString()->{
                    visibility=View.GONE
                    binding.leaveBtn.apply {
                        visibility=View.VISIBLE
                        setOnClickListener {
                            ConfrimationDialogueFragment("Are you sure want to leave the game",object : ConfrimationDialogueFragment.ProcessExecutor{
                                override fun execute(result: ConfrimationStatus) {
                                    viewModel.leaveGame()
                                    finish()
                                }

                            }).show(supportFragmentManager,"LEAVE_GAME_CONFRIMATION_TAG")
                        }
                    }

                }
                else->{}
            }
        }


        binding.playerListControlField.setOnClickListener {
            if(isExpanded) {
                binding.playersList.layoutParams.height=(60 * resources.displayMetrics.density).toInt()
                binding.playersList.requestLayout()
//                binding.expandedSec.visibility=View.GONE
                binding.emptyFieldCaption.visibility= View.GONE
                binding.playerlistControllerIc.setImageResource(R.drawable.down_arrow_ic)
                binding.playersList.isNestedScrollingEnabled=false
                isExpanded=false
            }
            else {
                binding.playersList.layoutParams.height=(400 * resources.displayMetrics.density).toInt()
                binding.playersList.requestLayout()
//                binding.expandedSec.visibility=View.VISIBLE
                binding.emptyFieldCaption.visibility = View.VISIBLE
                binding.playerlistControllerIc.setImageResource(R.drawable.up_arrow_ic)
                binding.playersList.isNestedScrollingEnabled=true
                isExpanded=true
            }
        }
        viewModel.eventHostasLiveData.observe(this) { host ->
            playerListAdapter.setHost(host)

        }


        viewModel.setEventData(intent.getIntExtra("EVENT_ID",0))
        if(status == UserViewMode.RECOMMENDED.toString()) {viewModel.intiateRegistrationStatus()}


            viewModel.selectedEvent.observe(this, Observer {
            selectedEventData->
                if(selectedEventData!=null) {
                    val activity = selectedEventData.activity
                    playerListAdapter =
                        PlayerListViewAdapter(true.takeIf { status == UserViewMode.HOST.toString() } ?: false , viewModel.currentUserId!!)
                    this.viewModel.setHost(selectedEventData.activity.host)
                    setSupportActionBar(binding.activityToolbar.activityToolbar)

                    supportActionBar?.apply {
                        title = selectedEventData.activity.title
                        setDisplayHomeAsUpEnabled(true)
                    }
                    val formattedDate=selectedEventData.activity.date
                    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    val date = dateFormat.parse(formattedDate)

                    val calendar = Calendar.getInstance()
                    calendar.time = date!!

                    val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
                    val monthOfYear = calendar.get(Calendar.MONTH)
                    val yearValue = calendar.get(Calendar.YEAR)

                    if(activity.thumbnail == null){
                        binding.thumbnail.setImageResource(activity.sports.drawableId)
                    }else{
                        activity.thumbnail.also{image->
                            binding.thumbnail.setImageBitmap(BitmapFactory.decodeByteArray(image,0,image!!.size))

                        }
                    }

                    val dateAndTimeString = "${MonthOfTheYear.values()[monthOfYear]} $dayOfMonth, $yearValue ${selectedEventData.activity.time}"
                    binding.dateAndTime.text=dateAndTimeString
                    binding.enrolledplayerCount.text=selectedEventData.activity.enrolledPlayers.toString()
                    binding.totalplayerCount.text = selectedEventData.activity.requiredPlayers.toString()
                    binding.playersList.adapter = playerListAdapter
                    binding.playersList.layoutManager = LinearLayoutManager(this)
                    selectedEventData.playersList.let { playerList ->
                        playerListAdapter.addPlayers(playerList)
                        if (playerList.isNotEmpty()) binding.emptyFieldCaption.text = null
                    }
                    binding.loc.text = activity.location.toString()
                    binding.description.text = activity.description
                    binding.otherInformation.text = activity.otherInstructions
                    binding.sports.text = activity.sports.toString()
                    binding.title.text=activity.title
                }

        })
        viewModel.registrationStatus.observe(this){ isEnrolled->

            if(isEnrolled==true){
                status=UserViewMode.ENROLLED.toString()
                binding.enrollButton.text="Registered"
                binding.enrollButton.background=ContextCompat.getDrawable(baseContext,R.drawable.disabled_button_bg)
            }
            else if(isEnrolled==false){
                status=UserViewMode.REQUESTED.toString()
                binding.enrollButton.text="Requested"
                binding.enrollButton.background=ContextCompat.getDrawable(baseContext,R.drawable.disabled_button_bg)
            }

        }

        binding.enrollButton.setOnClickListener {
            viewModel.requestRegistration()
            binding.enrollButton.text = "Requested"
            binding.enrollButton.isEnabled = false
            binding.enrollButton.background=ContextCompat.getDrawable(baseContext,R.drawable.disabled_button_bg)

        }



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if(status==UserViewMode.HOST.toString()){
            menuInflater.inflate(R.menu.show_game_activity_menu,menu)
            return true
        }
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }

            R.id.edit_game_ic -> {
                EditActivityFragment(object : EditActivityFragment.GameHandler {
                    override fun getGame(): SportActivity {
                        return viewModel.selectedEvent.value?.activity!!
                    }

                    override fun updateGame(game: SportActivity) {
                        viewModel.updateGame(game)
                    }

                } , viewModel.selectedEvent.value?.activity?.enrolledPlayers ?: 1).show(supportFragmentManager, "EDIT_ACTIVITY_FRAGMENT")
            }

            else -> super.onOptionsItemSelected(item)
        }
        return true
    }
}