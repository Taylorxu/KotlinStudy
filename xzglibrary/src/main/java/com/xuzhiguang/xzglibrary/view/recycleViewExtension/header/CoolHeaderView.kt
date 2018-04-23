package com.xuzhiguang.xzglibrary.view.recycleViewExtension.header

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.aspsine.irecyclerview.RefreshTrigger
import com.xuzhiguang.xzglibrary.R
import kotlinx.android.synthetic.main.layout_irecyclerview_cool_refresh_header_view.*
import kotlinx.android.synthetic.main.layout_irecyclerview_cool_refresh_header_view.view.*

/**
 * Created by 徐志广 on 2018/4/19.
 */
open  class CoolHeaderView : FrameLayout, RefreshTrigger {


    private var mHeight: Int = 0

    constructor(context: Context) : super(context)
    constructor(context: Context, attr: AttributeSet) : super(context, attr)
    constructor(context: Context, attr: AttributeSet, defStyleAttr: Int) : super(context, attr, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_irecyclerview_cool_refresh_header_view, this, true)
    }


    override fun onMove(finished: Boolean, automatic: Boolean, moved: Int) {
        if (!finished) {
            imageView.rotationY = moved / mHeight.toFloat() * 360
        } else {
            imageView.rotationY = moved / mHeight.toFloat() * 360
        }
    }


    override fun onStart(automatic: Boolean, headerHeight: Int, finalHeight: Int) {
        mHeight = headerHeight
    }



    override fun onReset() {
    }

    override fun onComplete() {
    }

    override fun onRelease() {
    }

    override fun onRefresh() {
    }
}