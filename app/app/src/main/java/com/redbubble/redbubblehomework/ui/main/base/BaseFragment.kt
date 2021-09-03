package com.redbubble.redbubblehomework.ui.main.base

import android.content.Context
import androidx.fragment.app.Fragment
import com.redbubble.redbubblehomework.ui.MainActivity
import com.redbubble.redbubblehomework.ui.MainActivityListener

abstract class BaseFragment : Fragment(), MainActivityListener {
    private var mainActivityListener: MainActivityListener? = null

    override fun showSnackBar(message: String?) {
        mainActivityListener?.showSnackBar(message)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            mainActivityListener = context
        }
    }
}