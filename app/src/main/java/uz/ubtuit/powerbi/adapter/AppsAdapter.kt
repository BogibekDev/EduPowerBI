package uz.ubtuit.powerbi.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import uz.ubtuit.powerbi.R
import uz.ubtuit.powerbi.model.App
import uz.ubtuit.powerbi.model.Book

class AppsAdapter(private var list: List<App>) :
    RecyclerView.Adapter<AppsAdapter.AppViewHolder>() {

    var itemClick: ((Int) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<App>){
        this.list=list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        return AppViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_app, parent, false)
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        holder.apply {
            item.setOnClickListener {
                itemClick?.invoke(position)
            }
            tvName.text = list[position].name
            Glide.with(icApp).load(list[position].icon).into(icApp)
        }
    }

    class AppViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val item: LinearLayout = view.findViewById(R.id.item)
        val tvName: TextView = view.findViewById(R.id.tvNameApp)
        val icApp: ImageView = view.findViewById(R.id.icApp)
    }
}