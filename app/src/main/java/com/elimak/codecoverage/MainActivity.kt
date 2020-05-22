package com.elimak.navigationtabsdemo

import android.content.res.Resources
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.elimak.codecoverage.App
import com.elimak.codecoverage.R
import com.elimak.codecoverage.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.injector.inject(this)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        navController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navController.navigateUp()
                }
            }
        )

        navController.addOnDestinationChangedListener { controller, destination, arguments -> }
    }

    val Int.dp: Int
        get() = (this / Resources.getSystem().displayMetrics.density).toInt()
    val Int.px: Int
        get() = (this * Resources.getSystem().displayMetrics.density).toInt()

    override fun onResume() {
        super.onResume()
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null as DrawerLayout?)
    }
}
