package com.example.digitalcalculator

import android.app.UiModeManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Read the saved theme preference
        val sharedPreferences = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        val theme = sharedPreferences.getInt("theme", 0)

       //  Apply the appropriate theme
        if (theme==2) {
           setTheme(R.style.AppTheme_Dark)
        } else if(theme==1) {
            setTheme(R.style.AppTheme_Light)
        }else{
            val uiModeManager = getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
            uiModeManager.nightMode = UiModeManager.MODE_NIGHT_AUTO

        }

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView3) as NavHostFragment
        val navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.fragmentContainerView3)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}