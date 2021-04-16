package com.example.error_book.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.error_book.R
import com.example.error_book.databinding.FragmentRecyclerBinding


class RecyclerFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentRecyclerBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_recycler, container, false)

        val adapter = RecyclerFragAdapter()
        binding.rcycler1.adapter = adapter
        // Inflate the layout for this fragment
        return binding.root
    }


}