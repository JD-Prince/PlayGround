package com.project.playground.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.project.playground.model.SportActivity
import com.project.playground.R
import com.project.playground.databinding.ActivityCardBinding
import com.project.playground.databinding.GameCardBinding
import com.project.playground.enums.MonthOfTheYear
import com.project.playground.enums.UserViewMode
import com.project.playground.view.mainscreen.activityhandler.ShowSportsActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ActivityListAdapter(private val viewMode : UserViewMode) : RecyclerView.Adapter<ActivityViewHolder>() {

    private var database = ArrayList<SportActivity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {

        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.game_card,parent,false)

        return ActivityViewHolder(view)

    }

    override fun getItemCount(): Int {

        return database.size

    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {

        val item = database[position]
        holder.binding.root.setOnClickListener{thisItem->
            thisItem.context.startActivity(Intent(thisItem.context,ShowSportsActivity::class.java).apply {

                putExtra("EVENT_ID",item.eventId)
                putExtra("VIEW_MODE",viewMode.toString())

            })
        }

        holder.apply {

            binding.apply {

//                val s = "Username "+item.host.toString()
//                host.text = s
//                val calendar = Calendar.getInstance()
//                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
//                calendar.time=dateFormat.parse(item.date)!!
                // val item.date = SimpleDateFormat("dd/MM/yyyy",Locale.getDefault()).format(Calendar.DATE)

                val formattedDate = item.date

                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val date = dateFormat.parse(formattedDate)

                val calendar = Calendar.getInstance()
                calendar.time = date!!

                val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
                val monthOfYear = calendar.get(Calendar.MONTH)
                val yearValue = calendar.get(Calendar.YEAR)

                val dateString = "${MonthOfTheYear.values()[monthOfYear]} $dayOfMonth, $yearValue"


                this.dateAndTime.text=dateString +" "+ item.time

//                time.text=item.time

                title.text=item.title

                location.text=item.location.toString()

//                vacant.text=item.requiredPlayers.toString()

                sportsfield.text=item.sports.toString()

            }
        }
    }
    fun updateDatabase(database : List<SportActivity>){

        this.database=ArrayList(database)

        notifyDataSetChanged()

    }
}
class ActivityViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

//    val binding = ActivityCardBinding.bind(itemView)


        val binding=GameCardBinding.bind(itemView)
//    val sportActivityItem : Card = binding.root

}