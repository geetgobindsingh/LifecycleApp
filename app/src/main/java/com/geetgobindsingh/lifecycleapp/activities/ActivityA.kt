package com.geetgobindsingh.lifecycleapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ScrollView
import androidx.appcompat.widget.AppCompatTextView
import com.geetgobindsingh.lifecycleapp.LifecycleApp
import com.geetgobindsingh.lifecycleapp.R
import com.geetgobindsingh.lifecycleapp.fragments.FragmentA
import com.geetgobindsingh.lifecycleapp.fragments.FragmentB


class ActivityA : BaseActivity() {

    var logTextView: AppCompatTextView? = null
    var scrollView: ScrollView? = null

    override fun getScreenName(): String {
        return "ActivityA"
    }

    override fun getTextView(): AppCompatTextView? {
        return logTextView
    }

    override fun getScrollingView(): ScrollView? {
       return scrollView
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)

        logTextView = findViewById<AppCompatTextView>(R.id.logTextView)
        scrollView = findViewById<ScrollView>(R.id.scrollView)

        sendRefreshUIBroadcast()

        findViewById<Button>(R.id.nextActivity).setOnClickListener {
            LifecycleApp.getInstance().log(getScreenName(), "'Go to Next Activity' - Button Clicked")
            sendRefreshUIBroadcast()
            startActivity(Intent(this, ActivityB::class.java))
        }
        findViewById<Button>(R.id.addFragment).setOnClickListener {
            LifecycleApp.getInstance().log(getScreenName(), "'Add Fragment' - Button Clicked")
            sendRefreshUIBroadcast()
            if (supportFragmentManager.fragments.size > 0) {
                supportFragmentManager.beginTransaction()
                    .add(R.id.fragmentHost, FragmentB())
                    .addToBackStack(null)
                    .commit()
            } else {
                supportFragmentManager.beginTransaction()
                    .add(R.id.fragmentHost, FragmentA())
                    .addToBackStack(null)
                    .commit()
            }
        }
        findViewById<Button>(R.id.removeFragment).setOnClickListener {
            LifecycleApp.getInstance().log(getScreenName(), "'Remove Fragment' - Button Clicked")
            sendRefreshUIBroadcast()
            supportFragmentManager.popBackStack()
        }
        findViewById<Button>(R.id.replaceFragment).setOnClickListener {
            LifecycleApp.getInstance().log(getScreenName(), "'Replace Fragment' - Button Clicked")
            sendRefreshUIBroadcast()
            if (supportFragmentManager.fragments.size > 0) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentHost, FragmentB())
                    .addToBackStack(null)
                    .commit()
            } else {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentHost, FragmentA())
                    .addToBackStack(null)
                    .commit()
            }
        }
        findViewById<Button>(R.id.clearLogs).setOnClickListener {
            LifecycleApp.getInstance().getLogs().clear()
            LifecycleApp.getInstance().log(getScreenName(), "'Clear Logs' - Button Clicked")
            sendRefreshUIBroadcast()
        }
    }


}