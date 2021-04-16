package com.example.error_book.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.error_book.R

class HostBookAdapter(bookNames : List<String>) : RecyclerView.Adapter<RecyclerFragAdapter.BookViewHolder>()  {
    var bookNames = bookNames

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerFragAdapter.BookViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.text_item_view, parent, false)
        return RecyclerFragAdapter.BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerFragAdapter.BookViewHolder, position: Int) {
        val item : String = bookNames[position]
        holder.textView.text = item
    }

    override fun getItemCount(): Int {
        return bookNames.size
    }

    fun resetBookNames(newBookNames: ArrayList<String>){
        bookNames = newBookNames
    }


    class BookViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        var textView : TextView = itemView.findViewById(R.id.txt_item_view)
    }
}