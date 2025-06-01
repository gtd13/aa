
package com.example.js

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.graphics.Path
import android.os.Handler
import android.os.Looper

class ClickService : AccessibilityService() {
    private val handler = Handler(Looper.getMainLooper())

    override fun onServiceConnected() {
        super.onServiceConnected()
        handler.postDelayed({ performLoop() }, 7000)
    }

    private fun performClick(x: Int, y: Int, delayMs: Long, next: () -> Unit) {
        val path = Path().apply { moveTo(x.toFloat(), y.toFloat()) }
        val gesture = GestureDescription.Builder()
            .addStroke(GestureDescription.StrokeDescription(path, 0, 100))
            .build()
        dispatchGesture(gesture, null, null)
        handler.postDelayed(next, delayMs)
    }

    private fun performLoop() {
        performClick(538, 1935, 40000) {
            performClick(546, 1358, 2000) {
                performClick(84, 230, 2000) {
                    performClick(280, 2050, 2000) {
                        performClick(760, 2140, 2000) {
                            performLoop()
                        }
                    }
                }
            }
        }
    }

    override fun onAccessibilityEvent(event: android.view.accessibility.AccessibilityEvent?) {}
    override fun onInterrupt() {}
}
