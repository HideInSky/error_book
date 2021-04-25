package com.example.error_book.fragment

import android.content.Context
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.error_book.R
import hideKeyboard
import showKeyboard

class HostBookAdapter(bookNames: ArrayList<String>) : RecyclerView.Adapter<HostBookAdapter.ViewHolder>()
{
    var bookNames:ArrayList<String> = bookNames
    // set the view type of view holder
    companion object{
        private const val TYPE_BOOK = 0
        private const val TYPE_ADD_BOOK = 1
        val ADD_BOOK_CODE : String = "This is add book code"
        val DEFAULT_BOOK_NAME : String = "New Book "
        var STATIC_NUM: Int = 1
        var ADDING_BOOK_NOW: Boolean = false
    }

    interface EventListener {
        fun onEvent(data: Int)
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): HostBookAdapter.ViewHolder {

        return when (viewType){
            TYPE_BOOK -> {
                val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.text_book_name_view, parent, false)
                BookViewHolder(view)
            }
            TYPE_ADD_BOOK -> {
                val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.text_book_name_view, parent, false)
                AddBookViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")

        }

    }



    /*
    get item view type by this method
     */
    override fun getItemViewType(position: Int): Int {
        val comparable = bookNames[position]
        return when (comparable) {
            ADD_BOOK_CODE -> TYPE_ADD_BOOK
            else -> TYPE_BOOK
        }
    }

    
    override fun getItemCount(): Int {
        return bookNames.size
    }


    abstract class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    inner class BookViewHolder(itemView: View): ViewHolder(itemView){
        var textView : TextView = itemView.findViewById(R.id.txt_book_name)
    }

    inner class AddBookViewHolder(itemView: View): ViewHolder(itemView){
        var textView : TextView = itemView.findViewById(R.id.txt_book_name)
        var editText: EditText = itemView.findViewById(R.id.txt_edit)
    }

    override fun onBindViewHolder(holder: HostBookAdapter.ViewHolder, position: Int) {
        val item : String = bookNames[position]

        when (holder) {
            is BookViewHolder -> {
                holder.textView.text = item
                holder.textView.visibility = VISIBLE
            }
            is AddBookViewHolder -> {
                ADDING_BOOK_NOW = true
                holder.textView.visibility = INVISIBLE
                holder.editText.apply {
                    visibility = VISIBLE
                    showKeyboard()
                    requestFocus()
                    requestFocusFromTouch()
                    setText("")
                    setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
                        if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {

                            val text_user: String = holder.editText.text.toString()
                            if (text_user == ""){
                                val default_book_name : String = DEFAULT_BOOK_NAME + STATIC_NUM
                                bookNames.set(position, default_book_name)
                                STATIC_NUM++
                                holder.textView.text = default_book_name
                            }else{
                                bookNames.set(position, text_user)
                                holder.textView.text = text_user
                            }
                            holder.textView.visibility = VISIBLE
                            holder.editText.visibility = GONE
                            hideKeyboard()
                            ADDING_BOOK_NOW = false
                            return@OnKeyListener true
                        }
                        false
                    })

                }


            }


                }

            }


}






