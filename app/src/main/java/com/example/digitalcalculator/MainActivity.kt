package com.example.digitalcalculator

import android.app.ActivityManager
import android.app.AlertDialog
import android.app.Dialog
import android.app.PictureInPictureParams
import android.app.UiModeManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION_CODES
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Rational
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.digitalcalculator.util.AccentTheme
import com.example.digitalcalculator.util.AppPreference
import com.example.digitalcalculator.util.getAccentTheme

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {


        // Call the setTheme() method on the activity
        val appPreference = AppPreference(this)
        val accentTheme =
            appPreference.getStringPreference(AppPreference.ACCENT_THEME, AccentTheme.BLUE.name)
        this.setTheme(getAccentTheme(accentTheme))


        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView3) as NavHostFragment
        val navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.fragmentContainerView3)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    // pip
    override fun onUserLeaveHint() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (!isInPictureInPictureMode) {
                val aspectRatio = Rational(85, 50) // Setting the desired aspect ratio
                val params = PictureInPictureParams.Builder()
                    .setAspectRatio(aspectRatio)
                    .build()

                enterPictureInPictureMode(params)
            }
        }
    }


}