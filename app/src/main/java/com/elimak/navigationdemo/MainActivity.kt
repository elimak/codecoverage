package com.elimak.navigationtabsdemo

import android.R.attr.left
import android.R.attr.right
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginBottom
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.elimak.navigationdemo.App
import com.elimak.navigationdemo.MainNavigationDirections
import com.elimak.navigationdemo.R
import com.elimak.navigationdemo.databinding.ActivityMainBinding
import com.elimak.navigationdemo.repository.IAppStateRepository
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    @Inject lateinit var appStateRepository: IAppStateRepository
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.injector.inject(this)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        /**
         * Despite not having a ViewModel, we can still do databindings
         *
         * Note: We could also have a ViewModel for the MainActivity of course
         */
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.appState = appStateRepository.getAppState
        binding.executePendingBindings()

        /**
         *  to make use of the BottomNavigation android component
         *  The code below will couple the res/menu with the res/navigation
         *  and do the highlighting of the navigation tabs + backStack work for us
         */

        navController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        /**
         * We have some fancy navigation with opaque or transparent background - so by default it is transparent
         * The background when opaque is using a Framelayout -
         * it will also ensure that the Nav Host is resized accordingly
         */
        bottomNavigationView.setBackgroundColor(Color.TRANSPARENT)
        bottomNavigationView.setItemIconTintList(null);

        /**
         * We may want to have different behaviors in our pages for the backButton pressed
         * Here we register the default behavior.
         *
         * You can look at ui/tabs/discovery/nested for a case where we want to overridde the default behavior
         */
        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navController.navigateUp()
                }
            }
        )

        /**
         * Our bottom navigation has an action button to start the game
         * Given its design, this element cannot be a part of the BottomNavigation component
         * so it is overlapping it as a normal button - to give room around it,
         * the bottom navigation has 5 menu items, but the middle one is disable and invisible
         *
         * see res/menu
         */
        camera.setOnClickListener {
            val action = MainNavigationDirections.toGlobalGame()
            navController.navigate(action)
        }

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)

            var width = displayMetrics.widthPixels

            val profile : BottomNavigationItemView = bottomNavigationView.findViewById(R.id.TabProfilePage)
            val screenPos = IntArray(2)
            profile.getLocationInWindow(screenPos)

            screenPos[0] = screenPos[0] + profile.width/2 + 56.px / 2

            val marginRightDp = (width - screenPos[0]).dp
            val marginRightPix = (width - screenPos[0])

            //screenPos[1] = screenPos[1] + profile.height/2

            Log.d(TAG, "profile.width " + profile.width +" - screen ? " + width + " 56.px " + 56.px)
            Log.d(TAG, "marginRightDp  " + marginRightDp +" marginRightPix " + marginRightPix)


//        //returns the visible bounds
//        childView.getDrawingRect(offsetViewBounds);
//        // calculates the relative coordinates to the parent
//       parentViewGroup.offsetDescendantRectToMyCoords(childView, offsetViewBounds);

//            val bottomOffset = profile.bottom;
//            val rightOffset = profile.right;

//
            Log.d(TAG, "pos " + screenPos[0] +", " + screenPos[1])
//
            val lp = overlap.layoutParams as ViewGroup.MarginLayoutParams
            lp.width = profile.width
            lp.marginStart = screenPos[0]
            lp.topMargin = screenPos[1]
            //(0, 0,100, 100)
            overlap.setLayoutParams(lp)
        }
    }

    val Int.dp: Int
        get() = (this / Resources.getSystem().displayMetrics.density).toInt()
    val Int.px: Int
        get() = (this * Resources.getSystem().displayMetrics.density).toInt()

//    private fun convertDpToPx(dp: Int, displayMetrics: DisplayMetrics): Int {
//        val pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), displayMetrics)
//    //float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics);
//        return Math.round(pixels);
//    }

    override fun onResume() {
        super.onResume()
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null as DrawerLayout?)
    }
}
