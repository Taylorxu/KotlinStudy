package com.xuzhiguang.xzglibrary.view.recycleViewExtension.header

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import com.aspsine.irecyclerview.RefreshTrigger
import com.xuzhiguang.xzglibrary.R
import kotlinx.android.synthetic.main.layout_irecyclerview_classic_refresh_header_view.view.*


/**
 * Created by aspsine on 16/3/14.
 */
open class ClassicRefreshHeaderView : RelativeLayout, RefreshTrigger {

    private var rotateUp = AnimationUtils.loadAnimation(context, R.anim.rotate_up)
    private var rotateDown = AnimationUtils.loadAnimation(context, R.anim.rotate_down)
    private var rotated = false
    private var mHeight = 0

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_irecyclerview_classic_refresh_header_view, this, true)
    }

    override fun onReset() {
        rotated = false
        ivSuccess.visibility = View.VISIBLE
        ivArrow.clearAnimation()
        ivArrow.visibility = View.GONE
        progressbar.visibility = View.GONE
    }

    override fun onComplete() {
        rotated = false
        ivSuccess.visibility = View.VISIBLE
        ivArrow.clearAnimation()
        ivArrow.visibility = View.GONE
        progressbar.visibility = View.GONE
        tvRefresh.text = resources.getString(R.string.complete)
    }

    override fun onRelease() {
    }

    override fun onMove(finished: Boolean, automatic: Boolean, moved: Int) {
        if (!finished) {
            ivArrow.visibility = View.VISIBLE
            progressbar.visibility = View.GONE
            ivSuccess.visibility = View.GONE
            if (moved <= mHeight) {
                if (rotated) {
                    ivArrow.clearAnimation()
                    ivArrow.startAnimation(rotateDown)
                    rotated = false
                }

                tvRefresh.text = resources.getString(R.string.swipe_refresh)
            } else {
                tvRefresh.text = resources.getString(R.string.release_refresh)
                if (!rotated) {
                    ivArrow.clearAnimation()
                    ivArrow.startAnimation(rotateUp)
                    rotated = true
                }
            }
        }
    }

    override fun onRefresh() {
        ivSuccess.visibility = View.GONE
        ivArrow.clearAnimation()
        ivArrow.visibility = View.GONE
        progressbar.visibility = View.VISIBLE
        tvRefresh.text = resources.getString(R.string.refresh_ing)
    }

    override fun onStart(automatic: Boolean, headerHeight: Int, finalHeight: Int) {
        mHeight = headerHeight
        progressbar.isIndeterminate = true
    }
}
