package com.example.error_book.fragment

import ADDING_BOOK_NOW
import ADD_BOOK_CODE
import BOOK_NAME_LIST_KEY
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import bookNames
import com.example.error_book.R
import com.example.error_book.data.BookNameListDataManager
import com.example.error_book.databinding.FragmentHostBinding


class HostFragment : Fragment() {



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // inflate in databinding
        val binding: FragmentHostBinding = DataBindingUtil.
        inflate(inflater, R.layout.fragment_host, container, false)



        // recycler adapter
        val adapter = HostBookAdapter()
        val recyclerView = binding.recyclerBook
        recyclerView.adapter = adapter

        // recycler layout manager
        val linearLayoutManager = LinearLayoutManager(activity) //? activity
        binding.recyclerBook.layoutManager = linearLayoutManager

//         add divider between items
//        val mDividerItemDecoration : DividerItemDecoration =
//                DividerItemDecoration(activity,
//                        linearLayoutManager.orientation)
//        recyclerView.addItemDecoration(mDividerItemDecoration)

        // get book names from sharedReferences in dataManager
        bookNames = BookNameListDataManager(activity!!).readList(BOOK_NAME_LIST_KEY)

        // set on click listener to create books
        binding.fabAddBook.setOnClickListener{
            // if it is not adding book, add book
            // if it is adding book, prohibit users from adding multiple books at a time
            if (!ADDING_BOOK_NOW){
                ADDING_BOOK_NOW = true
                bookNames.add(ADD_BOOK_CODE)
                adapter.notifyItemInserted(bookNames.size-1)
            }
        }

        return binding.root
    }




}