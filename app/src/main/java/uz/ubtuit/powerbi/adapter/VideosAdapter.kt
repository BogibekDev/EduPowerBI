package uz.ubtuit.powerbi.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import uz.ubtuit.powerbi.R
import uz.ubtuit.powerbi.model.Book
import uz.ubtuit.powerbi.model.Video

class VideosAdapter(private var list: List<Video>) :
    RecyclerView.Adapter<VideosAdapter.VideoViewHolder>() {

    var itemClick: ((Int) -> Unit)? = null


    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<Video>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.apply {
            item.setOnClickListener {
                itemClick?.invoke(position)
            }
            tvName.text = list[position].name
        }
    }

    class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val item: MaterialCardView = view.findViewById(R.id.item)
        val tvName: TextView = view.findViewById(R.id.tvVideoName)
    }
}