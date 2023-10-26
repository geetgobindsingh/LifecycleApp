package com.geetgobindsingh.lifecycleapp.activities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.geetgobindsingh.lifecycleapp.LifecycleApp

abstract class BaseActivity : AppCompatActivity() {

    companion object {
        const val ACTION = "refresh.ui"
    }

    abstract fun getScreenName(): String
    abstract fun getTextView(): AppCompatTextView?
    abstract fun getScrollingView(): ScrollView?

    private val myBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            refreshUI()
        }
    }

    internal fun sendRefreshUIBroadcast() {
        applicationContext.sendBroadcast(Intent(ACTION))
    }


    private fun refreshUI() {

        getTextView()?.let {
            val builder = SpannableStringBuilder()
            for (log in LifecycleApp.getInstance().getLogs()) {
                var spannable = SpannableString(log)
                if (spannable.contains("App")) {
                    spannable = getSpannable(
                        spannable,
                        "App",
                        Color.parseColor("#FFFF00"),
                        Color.parseColor("#800080")
                    )
                }
                if (spannable.contains("ActivityA")) {
                    spannable = getSpannable(
                        spannable,
                        "ActivityA",
                        Color.parseColor("#003366"),
                        Color.WHITE
                    )
                }
                if (spannable.contains("ActivityB")) {
                    spannable = getSpannable(
                        spannable,
                        "ActivityB",
                        Color.parseColor("#800080"),
                        Color.WHITE
                    )
                }
                if (spannable.contains("ActivityC")) {
                    spannable = getSpannable(
                        spannable,
                        "ActivityC",
                        Color.parseColor("#DAA06D"),
                        Color.WHITE
                    )
                }
                if (spannable.contains("FragmentA")) {
                    spannable = getSpannable(
                        spannable,
                        "FragmentA",
                        Color.parseColor("#00ee99"),
                        Color.BLACK
                    )
                }
                if (spannable.contains("FragmentB")) {
                    spannable = getSpannable(
                        spannable,
                        "FragmentB",
                        Color.parseColor("#aa1122"),
                        Color.WHITE
                    )
                }
                if (log.contains("Button Clicked")) {
                    spannable = getSpannable(
                        spannable,
                        log.substring(log.indexOf('\'')),
                        Color.parseColor("#11aa22"),
                        Color.WHITE
                    )
                }
                builder.append(spannable)
                builder.append("\n")
            }
            it.text = builder
            getScrollingView()?.post { getScrollingView()?.scrollToBottom() }
        }

    }

    fun ScrollView.scrollToBottom() {
        val lastChild = getChildAt(childCount - 1)
        val bottom = lastChild.bottom + paddingBottom
        val delta = bottom - (scrollY + height)
        smoothScrollBy(0, delta)
    }


    protected fun getSpannable(
        log: SpannableString,
        word: String,
        bgColor: Int,
        fgColor: Int
    ): SpannableString {
        val start = log.indexOf(word)
        log.setSpan(BackgroundColorSpan(bgColor), start, start + word.length, 0)
        log.setSpan(ForegroundColorSpan(fgColor), start, start + word.length, 0)
        return log
    }


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        LifecycleApp.getInstance()
            .log(getScreenName(), "onCreate(savedInstanceState , persistentState)")
        sendRefreshUIBroadcast()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LifecycleApp.getInstance().log(getScreenName(), "onCreate(savedInstanceState)")
        sendRefreshUIBroadcast()
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)
        LifecycleApp.getInstance()
            .log(getScreenName(), "onRestoreInstanceState($savedInstanceState)")
        sendRefreshUIBroadcast()
    }

    override fun onStart() {
        super.onStart()
        val intentFilter = IntentFilter().apply {
            addAction(ACTION)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            applicationContext.registerReceiver(
                myBroadcastReceiver,
                intentFilter,
                RECEIVER_EXPORTED
            )
        } else {
            applicationContext.registerReceiver(myBroadcastReceiver, intentFilter)
        }

        LifecycleApp.getInstance().log(getScreenName(), "onStart()")
        sendRefreshUIBroadcast()
    }

    override fun onRestart() {
        super.onRestart()
        LifecycleApp.getInstance().log(getScreenName(), "onRestart()")
        sendRefreshUIBroadcast()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        LifecycleApp.getInstance()
            .log(getScreenName(), "onSaveInstanceState(outState, outPersistentState)")
        sendRefreshUIBroadcast()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        LifecycleApp.getInstance().log(getScreenName(), "onSaveInstanceState(outState)")
        sendRefreshUIBroadcast()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        LifecycleApp.getInstance()
            .log(getScreenName(), "onRestoreInstanceState(savedInstanceState)")
        sendRefreshUIBroadcast()
    }

    override fun onResume() {
        super.onResume()
        LifecycleApp.getInstance().log(getScreenName(), "onResume()")
        sendRefreshUIBroadcast()
    }

    override fun onPause() {
        super.onPause()
        LifecycleApp.getInstance().log(getScreenName(), "onPause()")
        sendRefreshUIBroadcast()
    }

    override fun onStop() {
        super.onStop()
        applicationContext.unregisterReceiver(myBroadcastReceiver)
        LifecycleApp.getInstance().log(getScreenName(), "onStop()")
        sendRefreshUIBroadcast()
    }

    override fun onDestroy() {
        super.onDestroy()
        LifecycleApp.getInstance().log(getScreenName(), "onDestroy()")
        sendRefreshUIBroadcast()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        LifecycleApp.getInstance().log(getScreenName(), "onBackPressed()")
        sendRefreshUIBroadcast()
    }
}