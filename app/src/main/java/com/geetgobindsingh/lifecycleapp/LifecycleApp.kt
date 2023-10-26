package com.geetgobindsingh.lifecycleapp

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner

class LifecycleApp : Application() {

    private lateinit var logs: ArrayList<String>

    override fun onCreate() {
        super.onCreate()
        sInstance = this
        logs = ArrayList()
        ProcessLifecycleOwner.get().lifecycle.addObserver(AppLifecycleObserver(this))
    }

    fun getLogs() = sInstance.logs

    fun log(tag: String, message: String) {
        sInstance.logs.add("$tag : $message")
    }

    companion object {
        private lateinit var sInstance : LifecycleApp
        fun getInstance(): LifecycleApp {
            return sInstance
        }
    }
}