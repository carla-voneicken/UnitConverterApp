package com.example.unitconverter

import android.graphics.Rect
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.unitconverter.databinding.ActivityMainBinding
import com.example.unitconverter.lengthconverter.LengthFragment
import com.example.unitconverter.temperatureconverter.TemperatureFragment
import com.example.unitconverter.volumeconverter.VolumeFragment
import com.example.unitconverter.weightconverter.WeightFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    // Declaring binding variable for ViewBinding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentList = listOf(
            LengthFragment(),
            WeightFragment(),
            VolumeFragment(),
            TemperatureFragment()
        )
        val adapter = FragmentAdapter(this, fragmentList)
        binding.viewPager.adapter = adapter

        // Get the bottom navigation view to connect it to the ViewPager
        val bottomNavigationView: BottomNavigationView = binding.bottomNav

        // Sync ViewPager 2 with BottomNavigationView to ensure that when the user swipes to a new page,
        // the corresponding item in the bottom nav is selected
        // When a page change is registered, we're creating an anonymous inner class that extends
        // OnPageChangeCallback and overriding its onPageSelected method
        // This method is called when the user swipes to a new page, we're then setting the corresponding
        // item in the bottom nav to being selected (isChecked = true)
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
             override fun onPageSelected(position: Int) {
                 super.onPageSelected(position)
                 bottomNavigationView.menu.getItem(position).isChecked = true
             }
        })

        // Sync BottomNavigationView with ViewPager2 to ensure that when the user selects a page in
        // the bottom nav, the current item of the view pager gets updated and therefore the correct fragment is shown
        bottomNavigationView.setOnItemSelectedListener { item ->
            // When an item is selected, get the item id, depending on the id set the current item of the view pager
            when (item.itemId) {
                R.id.nav_length -> binding.viewPager.currentItem = 0
                R.id.nav_weight -> binding.viewPager.currentItem = 1
                R.id.nav_volume -> binding.viewPager.currentItem = 2
                R.id.nav_temperature -> binding.viewPager.currentItem = 3
            }
            true
        }

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

    inner class FragmentAdapter(fragmentActivity: FragmentActivity, private val fragmentList: List<Fragment>) : FragmentStateAdapter(fragmentActivity) {
        // Number of fragments to swipe between
        override fun getItemCount(): Int {
            return fragmentList.size
        }

        // Set fragment positions
        override fun createFragment(position: Int): Fragment {
            return fragmentList[position]
        }
    }
}