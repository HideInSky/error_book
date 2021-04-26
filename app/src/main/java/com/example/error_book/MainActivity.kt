package com.example.error_book

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import bookNames
import com.example.error_book.databinding.ActivityMainBinding
import kotlin.reflect.typeOf


class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    private lateinit var prefs: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // data binding drawer layout
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
        drawerLayout = binding.drawerLayout

        // set the btn_up and drawer in all fragments under this activity
        navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        // set the book names list, check if it's on first run
        prefs = getSharedPreferences("com.example.error_book", MODE_PRIVATE);
        Log.i("check its type", bookNames::class.java.toString())

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    override fun onResume() {
        super.onResume()
        if (prefs.getBoolean("firstrun", true)){

            // Do first run stuff here then set 'firstrun' as false
            // using the following line to edit/commit prefs
            bookNames = arrayListOf("数学")

            prefs.edit().putBoolean("firstrun", false).apply();
        }
    }
}