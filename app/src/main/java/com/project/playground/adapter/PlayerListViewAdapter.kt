package com.project.playground.adapter

import android.content.Intent
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.playground.model.Player
import com.project.playground.R
import com.project.playground.databinding.PlayerCardBinding
import com.project.playground.view.mainscreen.profile.ProfileViewActivity

class PlayerListViewAdapter(private val isHost : Boolean = false,private val currenUserId : Int): RecyclerView.Adapter<PlayerListViewHolder>() {

    private val playersWithHost = mutableListOf<Player>()
    private var isHostReceived = false
    private val playerList = mutableListOf<Player>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerListViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.player_card,parent,false)
        return PlayerListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return playersWithHost.size
    }

    override fun onBindViewHolder(holder: PlayerListViewHolder, position: Int) {
        val item = playersWithHost[position]

        holder.binding.apply {
            profilePic.setImageResource(R.drawable.ic_profile)
            name.text=null
            points.text=null
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
        playersWithHost.add(0,host)
        isHostReceived=true
        playersWithHost.addAll(playerList)
        notifyDataSetChanged()

//        notifyItemInserted(0)
//        notifyItemChanged(1)

    }

    fun addPlayers(playerList: List<Player>) {
//        this.playersWithHost.addAll(playerList)
//        println(playerList)
//        println(this.playersWithHost)
//        if(this.playersWithHost.size == playerList.size) notifyDataSetChanged()
//        else notifyItemRangeChanged(1,this.playersWithHost.size-1)
        if(isHostReceived){
            this.playersWithHost.addAll(playerList)
            notifyItemRangeInserted(1,playerList.size-2)
        }
        else this.playerList.addAll(playerList)

    }
}

class PlayerListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val binding = PlayerCardBinding.bind(itemView)
}