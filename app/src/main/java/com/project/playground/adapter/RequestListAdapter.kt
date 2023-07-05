package com.project.playground.adapter

import android.content.Intent
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.playground.R
import com.project.playground.applicationmanager.relations.RequestWithActivityAndPlayer
import com.project.playground.databinding.RequestCardBinding
import com.project.playground.enums.UserViewMode
import com.project.playground.view.mainscreen.activityhandler.ShowSportsActivity
import com.project.playground.view.mainscreen.notifications.RequestHandler
import com.project.playground.view.mainscreen.profile.ProfileViewActivity

class RequestListAdapter(private val requestList : List<RequestWithActivityAndPlayer>,private val requestHandler: RequestHandler) : RecyclerView.Adapter<RequestListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestListViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.request_card,parent,false)
        return RequestListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return requestList.size
    }

    override fun onBindViewHolder(holder: RequestListViewHolder, position: Int) {
        val item = requestList[position]
        var isExpanded = false


        holder.binding.apply {


            val msg="${item.player.alias} requested to join the game ${item.activity.title}"
            content.text = msg
            item.player.profilePicture?.let {
                playerProfileImage.setImageBitmap(BitmapFactory.decodeByteArray(
                    it,
                    0,
                    it.size
                ))
            }
            acceptButton.setOnClickListener {
                requestHandler.approveRequest(item.request,item.activity)
            }
            rejectButton.setOnClickListener {
                requestHandler.declineRequest(item.request,item.activity.title)
            }
            root.setOnClickListener{
                if(isExpanded){
                    content.maxLines=2
                    isExpanded=false
                }
                else{
                    content.maxLines= Int.MAX_VALUE
                    isExpanded=true
                }
                root.requestLayout()
                profileViewButton.visibility=View.VISIBLE.takeIf { profileViewButton.visibility==View.GONE }?:View.GONE
                activityViewButton.visibility=View.VISIBLE.takeIf { activityViewButton.visibility==View.GONE }?:View.GONE

            }
            activityViewButton.setOnClickListener {
                val intent = Intent(it.context,ShowSportsActivity::class.java)
                intent.putExtra("VIEW_MODE",UserViewMode.HOST.toString())
                intent.putExtra("EVENT_ID",item.activity.eventId)
                it.context.startActivity(intent)
            }
            profileViewButton.setOnClickListener {
                val intent = Intent(it.context,ProfileViewActivity::class.java)
                intent.putExtra("PLAYER_ID",item.player.playerId)
                it.context.startActivity(intent)
            }
        }
    }
}
class RequestListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    val binding = RequestCardBinding.bind(itemView)
}