package com.geetgobindsingh.lifecycleapp.fragments

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import com.geetgobindsingh.lifecycleapp.LifecycleApp
import com.geetgobindsingh.lifecycleapp.activities.BaseActivity

abstract class BaseFragment: AppCompatDialogFragment() {

    abstract fun getScreenName() : String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        LifecycleApp.getInstance().log((requireActivity() as BaseActivity).getScreenName() + ":" + getScreenName(), "onAttach(context)")
        (requireActivity() as BaseActivity).sendRefreshUIBroadcast()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LifecycleApp.getInstance().log((requireActivity() as BaseActivity).getScreenName() + ":" + getScreenName(), "onCreate(savedInstanceState)")
        (requireActivity() as BaseActivity).sendRefreshUIBroadcast()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        LifecycleApp.getInstance().log((requireActivity() as BaseActivity).getScreenName() + ":" + getScreenName(), "onCreateView(inflater, container, savedInstanceState)")
        (requireActivity() as BaseActivity).sendRefreshUIBroadcast()

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        LifecycleApp.getInstance().log((requireActivity() as BaseActivity).getScreenName() + ":" + getScreenName(), "onCreateDialog(savedInstanceState)")
        (requireActivity() as BaseActivity).sendRefreshUIBroadcast()
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LifecycleApp.getInstance().log((requireActivity() as BaseActivity).getScreenName() + ":" + getScreenName(), "onViewCreated(view, savedInstanceState)")
        (requireActivity() as BaseActivity).sendRefreshUIBroadcast()
    }

    override fun onStart() {
        super.onStart()
        LifecycleApp.getInstance().log((requireActivity() as BaseActivity).getScreenName() + ":" + getScreenName(), "onStart()")
        (requireActivity() as BaseActivity).sendRefreshUIBroadcast()
    }

    override fun onResume() {
        super.onResume()
        LifecycleApp.getInstance().log((requireActivity() as BaseActivity).getScreenName() + ":" + getScreenName(), "onResume()")
        (requireActivity() as BaseActivity).sendRefreshUIBroadcast()
    }

    override fun onPause() {
        super.onPause()
        LifecycleApp.getInstance().log((requireActivity() as BaseActivity).getScreenName() + ":" + getScreenName(), "onPause()")
        (requireActivity() as BaseActivity).sendRefreshUIBroadcast()
    }

    override fun onStop() {
        super.onStop()
        LifecycleApp.getInstance().log((requireActivity() as BaseActivity).getScreenName() + ":" + getScreenName(), "onStop()")
        (requireActivity() as BaseActivity).sendRefreshUIBroadcast()
    }

    override fun onDestroy() {
        super.onDestroy()
        LifecycleApp.getInstance().log((requireActivity() as BaseActivity).getScreenName() + ":" + getScreenName(), "onDestroy()")
        (requireActivity() as BaseActivity).sendRefreshUIBroadcast()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        LifecycleApp.getInstance().log((requireActivity() as BaseActivity).getScreenName() + ":" + getScreenName(), "onDestroyView()")
        (requireActivity() as BaseActivity).sendRefreshUIBroadcast()
    }

    override fun onDetach() {
        super.onDetach()
        LifecycleApp.getInstance().log((requireActivity() as BaseActivity).getScreenName() + ":" + getScreenName(), "onDetach()")
        (requireActivity() as BaseActivity).sendRefreshUIBroadcast()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        LifecycleApp.getInstance().log((requireActivity() as BaseActivity).getScreenName() + ":" + getScreenName(), "onSaveInstanceState(outState)")
        (requireActivity() as BaseActivity).sendRefreshUIBroadcast()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        LifecycleApp.getInstance().log((requireActivity() as BaseActivity).getScreenName() + ":" + getScreenName(), "onViewStateRestored(savedInstanceState)")
        (requireActivity() as BaseActivity).sendRefreshUIBroadcast()
    }


}