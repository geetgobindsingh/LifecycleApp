package com.geetgobindsingh.lifecycleapp

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class AppLifecycleObserver constructor(context: Context) : LifecycleObserver {

    /**
     * Shows foreground {@link android.widget.Toast} after attempting to cancel the background one.
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onEnterForeground() {
        inForeground = true
        LifecycleApp.getInstance().log("App", "Foreground")
    }

    /**
     * Shows background {@link android.widget.Toast} after attempting to cancel the foreground one.
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onEnterBackground() {
        inForeground = false
        LifecycleApp.getInstance().log("App", "Background")
    }

    companion object {
        var inForeground = false
    }
}
