package com.example.error_book.fragment

import ADDING_BOOK_NOW
import ADD_BOOK_CODE
import BOOK_NAME_LIST_KEY
import DEFAULT_BOOK_NAME
import STATIC_NUM
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
import bookNames
import com.example.error_book.R
import com.example.error_book.data.BookNameListDataManager
import hideKeyboard
import showKeyboard

class HostBookAdapter() : RecyclerView.Adapter<HostBookAdapter.ViewHolder>()
{

    // set the view type of view holder
    companion object{
        private const val TYPE_BOOK = 0
        private const val TYPE_ADD_BOOK = 1
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
                // if it is normal book name view, set text to [item]
                holder.textView.text = item
            }
            is AddBookViewHolder -> {
                // if the book name view contains ADD_BOOK_CODE, it should show the editText and
                // hide the textView
                ADDING_BOOK_NOW = true
                holder.textView.text = ""
                holder.editText.apply {
                    visibility = VISIBLE
                    showKeyboard()
                    // make the cursor focus on current editText
                    // remember to make the current editText maxLine = 1
                    requestFocus()
                    requestFocusFromTouch()
                    // set original text as ""
//                    setText("")
                    // if the user presses enter
                    setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
                        if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                            // get one-line text (bookName) that user typed
                            val text_user: String = holder.editText.text.toString()
                            // if user does not input anything, give a default bookName
                            if (text_user == ""){
                                val default_book_name : String = DEFAULT_BOOK_NAME + STATIC_NUM
                                bookNames.set(position, default_book_name)
                                STATIC_NUM++
                                holder.textView.text = default_book_name
                            }else{
                                bookNames.set(position, text_user)
                                holder.textView.text = text_user
                            }
                            // use datamanager method to preserve the data user input
                            // in case of the app crashes
                            BookNameListDataManager(holder.editText.context).
                            saveList(bookNames, BOOK_NAME_LIST_KEY)
                            // make textview visible and hide edittext
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






