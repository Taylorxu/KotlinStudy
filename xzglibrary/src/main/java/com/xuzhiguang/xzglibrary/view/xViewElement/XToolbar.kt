package com.xuzhiguang.xzglibrary.view.xViewElement

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.xuzhiguang.xzglibrary.R

/**
 * Created by Administrator on 2018/4/18.
 */
open class XToolbar : RelativeLayout {
//    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
//    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    var tv_title_title: TextView? = null

    init {
        var view: View = LayoutInflater.from(context).inflate(R.layout.x_toolbar, null, false)
        tv_title_title = view.findViewById(R.id.tv_title_title)
    }

    fun setTitle(title: String) {
        tv_title_title?.text = title
        Log.e("setTitle", tv_title_title?.text.toString())
    }
}