package com.vijaysn.apimastervolley.Helpers

import android.R
import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout

//region Loader
object Loader {

    lateinit var mProgressBar: ProgressBar
    lateinit var relativeLayout: RelativeLayout

    fun show(context: Context) {
        val layout =
            (context as Activity).findViewById<View>(R.id.content)
                .rootView as ViewGroup
        mProgressBar = ProgressBar(context, null, R.attr.progressBarStyleLarge)
        mProgressBar!!.isIndeterminate = true
        mProgressBar!!.visibility = View.VISIBLE
        val params = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
        relativeLayout = RelativeLayout(context)
        relativeLayout.gravity = Gravity.CENTER
        relativeLayout.addView(mProgressBar)
        layout.addView(relativeLayout, params)
    }
    fun hide(context: Context) {
        if (this::mProgressBar.isInitialized && this::relativeLayout.isInitialized) {
            val layout =
                (context as Activity).findViewById<View>(R.id.content)
                    .rootView as ViewGroup
            relativeLayout.removeView(mProgressBar)
            layout.removeView(relativeLayout)
        }
    }

}
//endregion