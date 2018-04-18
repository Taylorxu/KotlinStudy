package com.xuzhiguang.xzglibrary.view.xViewElement

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.xuzhiguang.xzglibrary.R
import org.w3c.dom.Text

/**
 * Created by Administrator on 2018/4/18.
 */
open class XToolbar : RelativeLayout {
    //    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
//    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    var tv_title_title: TextView? = null
    var tv_back: TextView? = null
    var iv_right: ImageView? = null
    var tv_title_right: TextView? = null

    init {
        var view: View = LayoutInflater.from(context).inflate(R.layout.x_toolbar, this, true)
        tv_title_title = view.findViewById(R.id.tv_title_title)
        tv_back = view.findViewById(R.id.tv_title_back)
        iv_right = view.findViewById(R.id.iv_title_right)
        tv_title_right = view.findViewById(R.id.tv_title_right)
    }

    fun setTitle(title: String) {
        tv_title_title?.text = title
    }

    /**
     * 左边返回键
     * @param isShow
     * @param activity
     */
    fun setBack(isShow: Boolean, activity: Activity) {
        if (isShow) {
            tv_back?.visibility = View.VISIBLE
        } else {
            tv_back?.visibility = View.GONE
        }
        tv_back?.setOnClickListener(OnClickListener { activity.finish() })
    }

    /**
     * 需要重写返回键的
     * @param isShow
     * @param listener
     */
    fun setBackListener(isShow: Boolean, listener: View.OnClickListener) {
        if (isShow) {
            tv_back?.visibility = View.VISIBLE
        } else {
            tv_back?.visibility = View.GONE
        }
        tv_back?.setOnClickListener(listener)
    }

    /**
     * 设置右边图片
     * @param isShow
     * @param reId
     * @param listener
     */
    fun setIvRight(isShow: Boolean, reId: Int, listener: View.OnClickListener?) {
        if (isShow) {
            iv_right?.visibility = View.VISIBLE
        } else {
            iv_right?.visibility = View.GONE
        }
        if (reId != -1) {
            iv_right?.setImageResource(reId)
        }
        if (listener != null)
            iv_right?.setOnClickListener(listener)
    }

    /**
     * 设置右边文字
     * @param isShow
     * @param title
     * @param listener
     */
    fun setTvRight(isShow: Boolean, title: String, listener: View.OnClickListener?) {
        if (isShow) {
            tv_title_right?.visibility = View.VISIBLE
        } else {
            iv_right?.visibility = View.GONE
        }
        if (!TextUtils.isEmpty(title)) {
            tv_title_right?.text = title
        }
        if (listener != null)
            tv_title_right?.setOnClickListener(listener)
    }

}