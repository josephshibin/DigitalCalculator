package com.example.digitalcalculator

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.view.ViewGroup
import android.view.WindowManager

class FloatingWindowApp:Service() {
    private lateinit var floatView:ViewGroup
    private lateinit var floatWindowLayoutParams: WindowManager.LayoutParams
    private var LAYOUT_TYPE:Int?=null
    private lateinit var windowManager: WindowManager
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}