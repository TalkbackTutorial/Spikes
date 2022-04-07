package com.example.accessibilityinterceptor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityEvent
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainLayout = findViewById<ConstraintLayout>(R.id.main_layout)

        mainLayout.accessibilityDelegate = MainDelegate()
    }
}

/**
 * Takes over some accessibility handling for the attached view's children
 */
class MainDelegate : View.AccessibilityDelegate() {
    override fun onRequestSendAccessibilityEvent(
        host: ViewGroup?,
        child: View?,
        event: AccessibilityEvent?
    ): Boolean {
        if (child is TextView)
            child.setText(
                if (child.isAccessibilityFocused) R.string.focus_msg else R.string.no_focus_msg
            )

        return super.onRequestSendAccessibilityEvent(host, child, event)
    }
}
