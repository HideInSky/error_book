package com.example.error_book.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.error_book.BookNames
import com.example.error_book.R
import com.example.error_book.databinding.FragmentHostBinding


class HostFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflate in databinding
        val binding: FragmentHostBinding = DataBindingUtil.
        inflate(inflater, R.layout.fragment_host, container, false)

        val adapter = HostBookAdapter()
        binding.recyclerBook.adapter = adapter
        // onclickL to go to next Fragment

        binding.fabAddBook.setOnClickListener{view: View ->
            BookNames
        }

        return binding.root
    }

}