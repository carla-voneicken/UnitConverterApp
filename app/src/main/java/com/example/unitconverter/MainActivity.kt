package com.example.unitconverter

import android.graphics.Rect
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.unitconverter.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView
import java.text.DecimalFormat

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    // Declaring binding variable for ViewBinding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set the listener for the bottom navigation bar
        binding.bottomNav.setOnItemSelectedListener(this)

        // Add a viewTreeObserver to the root view of the activity to detect if the soft keyboard
        // is shown or hidden. If the keyboard is shown, hide the navigation bar.
        // This code compares the height of the screen when the keyboard is shown with the height of
        // the screen when the keyboard is hidden. If the difference between the two heights is
        // greater than 15% of the screen height, the bottom navigation bar is hidden.
        val rootView = binding.root
        rootView.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            rootView.getWindowVisibleDisplayFrame(rect)
            val screenHeight = rootView.rootView.height
            val keypadHeight = screenHeight - rect.bottom
            if (keypadHeight > screenHeight * 0.15) {
                binding.bottomNav.visibility = View.GONE
            } else {
                binding.bottomNav.visibility = View.VISIBLE
            }
        }
    }


    // This function is called when an item in the bottom navigation bar is selected and sets
    // the corresponding fragment as the content of the activity.
    override fun onNavigationItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.nav_length -> {
            supportFragmentManager.commit { replace(R.id.frame_content, LengthFragment()) }
            true
        }
        R.id.nav_weight -> {
            supportFragmentManager.commit { replace(R.id.frame_content, WeightFragment()) }
            true
        }
        R.id.nav_volume -> {
            supportFragmentManager.commit { replace(R.id.frame_content, VolumeFragment()) }
            true
        }
        R.id.nav_temperature -> {
            supportFragmentManager.commit { replace(R.id.frame_content, TemperatureFragment()) }
            true
        }
        else -> false
    }

}