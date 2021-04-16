package com.example.error_book.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.error_book.R
import com.example.error_book.databinding.FragmentHostBinding


class HostFragment : Fragment() {
    lateinit var bookNames : ArrayList<String>


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // inflate in databinding
        val binding: FragmentHostBinding = DataBindingUtil.
        inflate(inflater, R.layout.fragment_host, container, false)

        // create a string list for store book names
        bookNames = arrayListOf<String>()
        bookNames.add("数学")

        // recycler adapter
        val adapter = HostBookAdapter(bookNames)
        val recyclerView = binding.recyclerBook
        recyclerView.adapter = adapter

        // recycler layout manager
        val linearLayoutManager = LinearLayoutManager(activity) //? activity
        binding.recyclerBook.layoutManager = linearLayoutManager

        // add divider between items
//        val mDividerItemDecoration : DividerItemDecoration =
//                DividerItemDecoration(activity,
//                        linearLayoutManager.orientation)
//        recyclerView.addItemDecoration(mDividerItemDecoration)


        // set on click listener to create books
        binding.fabAddBook.setOnClickListener{
            bookNames.clear()
            bookNames.add("d")
            adapter.notifyDataSetChanged()

        }

        return binding.root
    }

}