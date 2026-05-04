package com.eyecon.glo

import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.eyecon.glo.ui.MainScaffold
import com.eyecon.glo.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    private var multiTouchDetected = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        hideSystemBars()
        setContent {
            AppTheme {
                MainScaffold()
            }
        }
    }

    private fun hideSystemBars() {
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        controller.hide(WindowInsetsCompat.Type.systemBars())
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemBars()
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.pointerCount > 1) {
            if (!multiTouchDetected) {
                multiTouchDetected = true
                val cancelEvent = MotionEvent.obtain(ev)
                cancelEvent.action = MotionEvent.ACTION_CANCEL
                super.dispatchTouchEvent(cancelEvent)
                cancelEvent.recycle()
            }
            return true
        }

        if (multiTouchDetected) {
            if (ev.actionMasked == MotionEvent.ACTION_UP || ev.actionMasked == MotionEvent.ACTION_CANCEL) {
                multiTouchDetected = false
            }
            return true
        }

        return super.dispatchTouchEvent(ev)
    }
}
