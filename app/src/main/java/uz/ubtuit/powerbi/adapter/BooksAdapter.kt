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

class BooksAdapter(private var list: List<Book>) :
    RecyclerView.Adapter<BooksAdapter.BookViewHolder>() {

    var itemClick: ((Int) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<Book>){
        this.list=list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.apply {
            item.setOnClickListener {
                itemClick?.invoke(position)
            }
            tvName.text = list[position].name
        }
    }

    class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val item: MaterialCardView = view.findViewById(R.id.item)
        val tvName: TextView = view.findViewById(R.id.tvBookName)
    }
}