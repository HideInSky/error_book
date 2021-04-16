package com.example.error_book.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.error_book.R
import com.example.error_book.database.Book

class RecyclerFragAdapter() : RecyclerView.Adapter<RecyclerFragAdapter.BookViewHolder>() {

    var data = listOf<Book>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }




    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.name


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.text_item_view, parent, false) as TextView
        return BookViewHolder(view)
    }

    class BookViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val textView : TextView = itemView.findViewById(R.id.txt_item_view)
    }



}