package com.geetgobindsingh.lifecycleapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ScrollView
import androidx.appcompat.widget.AppCompatTextView
import com.geetgobindsingh.lifecycleapp.LifecycleApp
import com.geetgobindsingh.lifecycleapp.R

class ActivityB : BaseActivity() {
    private var logTextView: AppCompatTextView? = null

    override fun getScreenName(): String {
        return "ActivityB"
    }
    var scrollView: ScrollView? = null
    override fun getScrollingView(): ScrollView? {
        return scrollView
    }

    override fun getTextView(): AppCompatTextView? {
        return logTextView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)
        logTextView = findViewById<AppCompatTextView>(R.id.logTextView)
        scrollView = findViewById<ScrollView>(R.id.scrollView)

        findViewById<Button>(R.id.backToPreviousActivity).setOnClickListener {
            LifecycleApp.getInstance().log(getScreenName(), "'Go to Previous Activity' - Button Clicked")
            sendRefreshUIBroadcast()
            finish()
        }
        findViewById<Button>(R.id.goToNextActivity).setOnClickListener {
            LifecycleApp.getInstance().log(getScreenName(), "'Go to Next Activity' - Button Clicked")
            sendRefreshUIBroadcast()
            startActivity(Intent(this, ActivityC::class.java))
        }
        findViewById<Button>(R.id.clearLogs).setOnClickListener {
            LifecycleApp.getInstance().getLogs().clear()
            LifecycleApp.getInstance().log(getScreenName(), "'Clear Logs' - Button Clicked")
            sendRefreshUIBroadcast()
        }
    }
}