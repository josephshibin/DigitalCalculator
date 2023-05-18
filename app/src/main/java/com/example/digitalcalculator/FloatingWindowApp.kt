package com.example.digitalcalculator

import android.app.Service
import android.content.Intent
import android.graphics.Color
import android.graphics.PixelFormat
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.IBinder
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toolbar
import com.example.digitalcalculator.main.TwoInOneCalculator
import java.util.Collections.max
import java.util.Collections.min
import kotlin.math.max
import kotlin.math.min

//import android.app.Service
//import android.content.Context
//import android.content.Intent
//import android.graphics.PixelFormat
//import android.os.Build
//import android.os.IBinder
//import android.view.Gravity
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import android.view.Window
//import android.view.WindowManager
//import android.widget.Toolbar
//
//class FloatingWindowApp:Service() {
//    private lateinit var floatView:ViewGroup
//    private lateinit var floatWindowLayoutParams: WindowManager.LayoutParams
//    private var LAYOUT_TYPE:Int?=null
//    private lateinit var windowManager: WindowManager
//    override fun onBind(intent: Intent?): IBinder? {
//        return null
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//        val metrics=applicationContext.resources.displayMetrics
//        val width=metrics.widthPixels
//        val height=metrics.heightPixels
//
//        windowManager=getSystemService(WINDOW_SERVICE) as WindowManager
//        val inflater=baseContext.getSystemService(LAYOUT_INFLATER_SERVICE)as LayoutInflater
//        floatView=inflater.inflate(R.layout.float_window,null) as ViewGroup
//
//
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//            LAYOUT_TYPE=WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
//        }
//        else  LAYOUT_TYPE=WindowManager.LayoutParams.TYPE_TOAST
//
//        floatWindowLayoutParams=WindowManager.LayoutParams(
//            (width * 0.55f ).toInt(),
//            (height * 0.55 ).toInt(),
//            LAYOUT_TYPE!!,
//            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
//            PixelFormat.TRANSLUCENT
//        )
//        floatWindowLayoutParams.gravity=Gravity.CENTER
//        floatWindowLayoutParams.x=0
//        floatWindowLayoutParams.y=0
//
//        windowManager.addView(floatView,floatWindowLayoutParams)
//
//    }
//
//
//}

//class FloatingWindowApp : Service() {
//    private lateinit var floatView: ViewGroup
//    private lateinit var floatWindowLayoutParams: WindowManager.LayoutParams
//    private var LAYOUT_TYPE: Int? = null
//    private lateinit var windowManager: WindowManager
//
//    override fun onBind(intent: Intent?): IBinder? {
//        return null
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//        val metrics = applicationContext.resources.displayMetrics
//        val width = metrics.widthPixels
//        val height = metrics.heightPixels
//
//        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
//        val inflater = baseContext.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        floatView = inflater.inflate(R.layout.float_window, null) as ViewGroup
//
//        // Get the app bar reference
//        val appBar = floatView.findViewById<Toolbar>(R.id.appBar)
//
//        // Customize the app bar as needed (e.g., set title, navigation icon, menu, etc.)
//       // appBar.title = "Floating Window App"
//       // appBar.inflateMenu(R.menu.settingsmenu)
//
//        // Add left icon
//        val leftIcon = ImageView(this)
//        leftIcon.setImageResource(R.drawable.baseline_compare_arrows_24)
//        val leftIconLayoutParams = Toolbar.LayoutParams(
//            Toolbar.LayoutParams.WRAP_CONTENT,
//            Toolbar.LayoutParams.WRAP_CONTENT
//        )
//        leftIconLayoutParams.gravity = Gravity.START
//        appBar.addView(leftIcon, leftIconLayoutParams)
//
//// Add right icon
//        val rightIcon = ImageView(this)
//        rightIcon.setImageResource(R.drawable.baseline_close_24)
//        val rightIconLayoutParams = Toolbar.LayoutParams(
//            Toolbar.LayoutParams.WRAP_CONTENT,
//            Toolbar.LayoutParams.WRAP_CONTENT
//        )
//        rightIconLayoutParams.gravity = Gravity.END
//        appBar.addView(rightIcon, rightIconLayoutParams)
//
//// Add center icon
//        val centerIcon = ImageView(this)
//        centerIcon.setImageResource(R.drawable.baseline_open_in_full_24)
//        val centerIconLayoutParams = Toolbar.LayoutParams(
//            Toolbar.LayoutParams.WRAP_CONTENT,
//            Toolbar.LayoutParams.WRAP_CONTENT
//        )
//        centerIconLayoutParams.gravity = Gravity.CENTER
//        appBar.addView(centerIcon, centerIconLayoutParams)
//
//// Handle icon clicks
//        leftIcon.setOnClickListener {
//            // Handle left icon click event here
//        }
//
//        rightIcon.setOnClickListener {
//            // Handle right icon click event here
//        }
//
//        centerIcon.setOnClickListener {
//            // Handle center icon click event here
//        }
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            LAYOUT_TYPE = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
//        } else {
//            LAYOUT_TYPE = WindowManager.LayoutParams.TYPE_TOAST
//        }
//
//        floatWindowLayoutParams = WindowManager.LayoutParams(
//            (width * 0.55f).toInt(),
//            (height * 0.55).toInt(),
//            LAYOUT_TYPE!!,
//            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
//            PixelFormat.TRANSLUCENT
//        )
//        floatWindowLayoutParams.gravity = Gravity.CENTER
//        floatWindowLayoutParams.x = 0
//        floatWindowLayoutParams.y = 0
//
//        windowManager.addView(floatView, floatWindowLayoutParams)
//
//        floatView.setOnTouchListener(object : View.OnTouchListener {
//            val updatedFloatWindowLayoutParam = floatWindowLayoutParams
//            var x = 0.0
//            var y = 0.0
//            var px = 0.0
//            var py = 0.0
//            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//
//                when (event!!.action) {
//                    MotionEvent.ACTION_DOWN -> {
//                        x = updatedFloatWindowLayoutParam.x.toDouble()
//                        y = updatedFloatWindowLayoutParam.y.toDouble()
//
//                        px=event.rawX.toDouble()
//                        py=event.rawY.toDouble()
//                    }
//                    MotionEvent.ACTION_MOVE->{
//                        updatedFloatWindowLayoutParam.x=(x+event.rawX-px).toInt()
//                        updatedFloatWindowLayoutParam.y=(y+event.rawY-py).toInt()
//
//                        windowManager.updateViewLayout(floatView,updatedFloatWindowLayoutParam)
//                    }
//                }
//                return false
//            }
//
//        })
//    }
//
//    // Rest of your service code
//
//
//}


class FloatingWindowApp : Service() {
    private lateinit var floatView: ViewGroup
    private lateinit var floatWindowLayoutParams: WindowManager.LayoutParams
    private var layoutType: Int = 0 // Set default value
    private lateinit var windowManager: WindowManager

    private var touchX: Float = 0F
    private var touchY: Float = 0F
    private var windowX: Int = 0
    private var windowY: Int = 0

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        // Get display metrics to set initial size of floating window
        val metrics = applicationContext.resources.displayMetrics
        val width = metrics.widthPixels
        val height = metrics.heightPixels

        // Get WindowManager and LayoutInflater instances
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        val inflater = baseContext.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater

        // Inflate the floating window layout
        floatView = inflater.inflate(R.layout.float_window, null) as ViewGroup

        // Get reference to the app bar
        val appBar = floatView.findViewById<Toolbar>(R.id.appBar)

        // Customize the app bar as needed (e.g., set title, navigation icon, menu, etc.)
        // appBar.title = "Floating Window App"
        // appBar.inflateMenu(R.menu.settingsmenu)

        // Create a FrameLayout container for the Toolbar
        val toolbarContainer = FrameLayout(this)

        // Add left icon to the Toolbar
        val leftIcon = ImageView(this)
        leftIcon.setImageResource(R.drawable.baseline_compare_arrows_24)
        val leftIconLayoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )
        leftIconLayoutParams.gravity = Gravity.START or Gravity.TOP // Updated gravity
        toolbarContainer.addView(leftIcon, leftIconLayoutParams)

        // Add the Toolbar to the floating window layout
        floatView.addView(toolbarContainer)

        // Add right icon to app bar
        val rightIcon = ImageView(this)
        rightIcon.setImageResource(R.drawable.baseline_close_24)
        val rightIconLayoutParams = Toolbar.LayoutParams(
            Toolbar.LayoutParams.WRAP_CONTENT,
            Toolbar.LayoutParams.WRAP_CONTENT
        )
        rightIconLayoutParams.gravity = Gravity.END
        appBar.addView(rightIcon, rightIconLayoutParams)

        // Add center icon to app bar
        val centerIcon = ImageView(this)
        centerIcon.setImageResource(R.drawable.baseline_open_in_full_24)
        val centerIconLayoutParams = Toolbar.LayoutParams(
            Toolbar.LayoutParams.WRAP_CONTENT,
            Toolbar.LayoutParams.WRAP_CONTENT
        )
        centerIconLayoutParams.gravity = Gravity.CENTER
        appBar.addView(centerIcon, centerIconLayoutParams)

        // Handle left icon touch events
        leftIcon.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Highlight the floating window when touched
                    val transparentBlue = Color.argb(100, 0, 0, 255)
                    floatView.setBackgroundColor(transparentBlue)

                }
                MotionEvent.ACTION_MOVE -> {
                    // Calculate the new window size based on touch delta
                    val deltaX = event.rawX - touchX
                    val deltaY = event.rawY - touchY
                    val newWidth = floatWindowLayoutParams.width + deltaX.toInt()
                    val newHeight = floatWindowLayoutParams.height + deltaY.toInt()

                    // Limit the window size within min and max bounds
                    val minWidth = (metrics.widthPixels * 0.5).toInt()
                    val minHeight = (metrics.heightPixels * 0.5).toInt()
                    val maxWidth = (metrics.widthPixels * 0.8).toInt()
                    val maxHeight = (metrics.heightPixels * 0.8).toInt()

                    floatWindowLayoutParams.width = when {
                        newWidth < minWidth -> minWidth
                        newWidth > maxWidth -> maxWidth
                        else -> newWidth
                    }
                    floatWindowLayoutParams.height = when {
                        newHeight < minHeight -> minHeight
                        newHeight > maxHeight -> maxHeight
                        else -> newHeight
                    }

                    // Update the window size and position
                    windowManager.updateViewLayout(floatView, floatWindowLayoutParams)
                    touchX = event.rawX
                    touchY = event.rawY
                }
                MotionEvent.ACTION_UP -> {
                    // Unhighlight the floating window when released
                    floatView.setBackgroundColor(Color.WHITE)
                }
            }
            true
        }

        // Handle right icon click event
        rightIcon.setOnClickListener {
            // Handle right icon click event here
            stopSelf()

        }

        // Handle center icon click event
        centerIcon.setOnClickListener {
            // Handle center icon click event here

            windowManager.removeView(floatView)


        }

        // Determine the appropriate layout type based on SDK version
        layoutType = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        } else {
            WindowManager.LayoutParams.TYPE_TOAST
        }

        // Create the layout parameters for the floating window
        floatWindowLayoutParams = WindowManager.LayoutParams(
            (width * 0.55f).toInt(),
            (height * 0.55).toInt(),
            layoutType,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )
        floatWindowLayoutParams.gravity = Gravity.CENTER
        floatWindowLayoutParams.x = 0
        floatWindowLayoutParams.y = 0

        // Add the floating window view to the WindowManager
        windowManager.addView(floatView, floatWindowLayoutParams)

        // Set touch listener to enable dragging the floating window
        floatView.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Store the initial touch position and window position
                    touchX = event.rawX
                    touchY = event.rawY
                    windowX = floatWindowLayoutParams.x
                    windowY = floatWindowLayoutParams.y
                }
                MotionEvent.ACTION_MOVE -> {
                    // Calculate the new window position based on touch delta
                    val deltaX = event.rawX - touchX
                    val deltaY = event.rawY - touchY
                    floatWindowLayoutParams.x = (windowX + deltaX).toInt()
                    floatWindowLayoutParams.y = (windowY + deltaY).toInt()

                    // Update the window position
                    windowManager.updateViewLayout(floatView, floatWindowLayoutParams)
                }
            }
            true
        }
    }

    // Rest of your service code


}




