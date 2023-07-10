package com.project.playground.adapter

import android.content.Intent
import android.graphics.BitmapFactory
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.project.playground.model.Player
import com.project.playground.R
import com.project.playground.databinding.PlayerCardBinding
import com.project.playground.view.mainscreen.profile.ProfileViewActivity

class PlayerListViewAdapter(private val isHost : Boolean = false,private val currenUserId : Int): RecyclerView.Adapter<PlayerListViewHolder>() {

    private var playerList = mutableListOf<Player>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerListViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.player_card,parent,false)
        return PlayerListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    override fun onBindViewHolder(holder: PlayerListViewHolder, position: Int) {
        val item = playerList[position]

        holder.binding.apply {
//            closeIc.visibility=View.VISIBLE.takeIf { isHost && position!=0}?:View.GONE
            item.profilePicture?.let { image->
                profilePic.setImageBitmap(BitmapFactory.decodeByteArray(
                    image,
                    0,
                    image.size
                ))
            }
            if(currenUserId==item.playerId) this.name.text="You"
            val points = "${item.points} pts"
            this.points.text=points
            this.name.text=item.alias

//            hostMarker.visibility=View.GONE.takeIf { position != 0 }?:View.VISIBLE
            if(position!=0){

                name.setCompoundDrawables(null,null,null,null)
            }

            root.setOnClickListener{
                if(currenUserId != item.playerId){
                    val intent = Intent(playerDetailCard.context,ProfileViewActivity::class.java)
                    intent.putExtra("PLAYER_ID",item.playerId)
                    playerDetailCard.context.startActivity(intent)
                }
            }
        }
    }

    fun setHost(host : Player){
        playerList.add(0,host)
        notifyItemChanged(0)
    }

    fun addPlayers(playerList: List<Player>) {
        this.playerList.addAll(playerList)
        if(this.playerList.size == playerList.size) notifyDataSetChanged()
        else notifyItemRangeChanged(1,playerList.size-2)

    }
}

class PlayerListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val binding = PlayerCardBinding.bind(itemView)
}