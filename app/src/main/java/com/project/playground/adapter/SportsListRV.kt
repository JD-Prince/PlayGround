package com.project.playground.adapter

import android.content.Intent
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.playground.R
import com.project.playground.databinding.GameCardBinding
import com.project.playground.databinding.GameCardNewBinding
import com.project.playground.enums.MonthOfTheYear
import com.project.playground.enums.UserViewMode
import com.project.playground.model.SportActivity
import com.project.playground.view.mainscreen.activityhandler.ShowSportsActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class SportsListRVAdapter(private val viewMode : UserViewMode): RecyclerView.Adapter<SportsListRVViewHolder>(){

    private var database = ArrayList<SportActivity>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportsListRVViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.game_card_new,parent,false)

        return SportsListRVViewHolder(view)
    }

    override fun getItemCount(): Int {
        return database.size
    }

    override fun onBindViewHolder(holder: SportsListRVViewHolder, position: Int) {
        val item = database[position]
        holder.sportActivityItem.setOnClickListener {thisItem->
            thisItem.context.startActivity(Intent(thisItem.context, ShowSportsActivity::class.java).apply {

                putExtra("EVENT_ID",item.eventId)
                putExtra("VIEW_MODE",viewMode.toString())

            })
        }
        holder.binding.apply {

            val formattedDate = item.date

            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val date = dateFormat.parse(formattedDate)

            val calendar = Calendar.getInstance()
            calendar.time = date!!

            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
            val monthOfYear = calendar.get(Calendar.MONTH)
            val yearValue = calendar.get(Calendar.YEAR)

            val dateString = "${MonthOfTheYear.values()[monthOfYear]} $dayOfMonth, $yearValue | ${item.time}"

            if(item.thumbnail == null){
                thumbnail.setImageResource(item.sports.drawableId)
            }else{
                item.thumbnail.also{image->
                    thumbnail.setImageBitmap(BitmapFactory.decodeByteArray(image,0,image!!.size))

                }
            }

            dateTime.text=dateString
            location.text=item.location.toString()
            title.text = item.title
            sports.text =item.sports.toString()
            val required = item.requiredPlayers - item.enrolledPlayers
            membersRequired.text=required.toString()



        }

    }
    fun updateDatabase(database : List<SportActivity>){

        this.database=ArrayList(database)

        notifyDataSetChanged()

    }

}
class SportsListRVViewHolder(item : View) : RecyclerView.ViewHolder(item){
        val binding = GameCardNewBinding.bind(item)
        val sportActivityItem = binding.root
}