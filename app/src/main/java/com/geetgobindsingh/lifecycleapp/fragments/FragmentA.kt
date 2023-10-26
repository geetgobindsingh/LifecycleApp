package com.geetgobindsingh.lifecycleapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geetgobindsingh.lifecycleapp.LifecycleApp
import com.geetgobindsingh.lifecycleapp.R
import com.geetgobindsingh.lifecycleapp.activities.BaseActivity

class FragmentA : BaseFragment() {
    override fun getScreenName(): String {
        return "FragmentA"
    }

    var rootView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        LifecycleApp.getInstance().log((requireActivity() as BaseActivity).getScreenName() + ":" + getScreenName(), "onCreateView(inflater, container, savedInstanceState)")
        (requireActivity() as BaseActivity).sendRefreshUIBroadcast()
        rootView?.let {
            return it
        } ?: kotlin.run {
            return inflater.inflate(R.layout.fragment_a, container, false)
        }
    }
}