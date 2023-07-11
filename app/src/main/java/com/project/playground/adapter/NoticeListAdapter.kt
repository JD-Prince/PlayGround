package com.project.playground.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.project.playground.R
import com.project.playground.databinding.NoticeCardBinding
import com.project.playground.enums.NoticeTypes
import com.project.playground.model.Notification

class NoticeListAdapter(private val noticeList : List<Notification.Notice>) : RecyclerView.Adapter<NoticeListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeListViewHolder {
        val view
        : View = LayoutInflater.from(parent.context).inflate(R.layout.notice_card,parent,false)
        return NoticeListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return noticeList.size
    }

    override fun onBindViewHolder(holder: NoticeListViewHolder, position: Int) {
        val item = noticeList[position]
//        var isExpanded = false
//
//
//        holder.binding.root.setOnClickListener {
//
//            holder.binding.apply {
//                if(isExpanded){
//                    content.maxLines=1
//                    isExpanded = false
//                }
//                else{
//                    content.maxLines= Int.MAX_VALUE
//                    isExpanded = true
//                }
//            }
//        }

        when(item.noticeType){
            NoticeTypes.REQUEST_ACCEPTED->{
                holder.binding.title.text="Request Accepted"
                holder.binding.content.text="Your Request for the game ${item.activityTitle} has been accepted"
                holder.binding.leadingIcon.setImageResource(R.drawable.done_ic)
            }
            NoticeTypes.REQUEST_DECLINED->{
                holder.binding.title.text="Request Declined"

                holder.binding.content.text="Your Request for the game ${item.activityTitle} has been declined"
                holder.binding.leadingIcon.setImageResource(R.drawable.close_ic)

            }
            NoticeTypes.EVENT_CANCELLED->{
                holder.binding.title.text="Event Cancelled"
                holder.binding.content.text="The event ${item.activityTitle} has been cancelled by the host"
                holder.binding.leadingIcon.setImageResource(R.drawable.alert_ic)


            }
            NoticeTypes.EVENT_FILLED ->{
                holder.binding.title.text="Request Declined"

                holder.binding.content.text="Your Request for the game ${item.activityTitle} has been filled up"
                holder.binding.leadingIcon.setImageResource(R.drawable.close_ic)
            }
            NoticeTypes.PLAYER_DEPARTED->{
                holder.binding.apply {
                    title.text = "Player Departed"
                    val content = "Player ${item.senderName} has left the game ${item.activityTitle}"
                    this.content.text = content
                    leadingIcon.setImageResource(R.drawable.alert_ic)
                }
            }
        }
//todo update the list size according to username
    }

}
class NoticeListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    val binding = NoticeCardBinding.bind(itemView)
}