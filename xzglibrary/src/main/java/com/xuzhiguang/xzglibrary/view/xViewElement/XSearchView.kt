package com.xuzhiguang.xzglibrary.view.xViewElement

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.xuzhiguang.xzglibrary.R
import com.xuzhiguang.xzglibrary.helperTool.DensityHelper
import kotlinx.android.synthetic.main.layout_search_view.view.*

/**
 * Created by Administrator on 2018/4/27.
 */
class XSearchView : FrameLayout {
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_search_view, this, true)
        showSearchEdit()
        bt_search.setOnClickListener { showSearchEdit() }
        bt_search_cancel.setOnClickListener { hideSearchEdit() }
    }



    @SuppressLint("NewApi")
    fun showSearchEdit() {

        bt_search.animate()
                .alpha(0f)
                .withEndAction {
                    bt_search.visibility = View.GONE
                    bt_search_cancel.visibility = View.VISIBLE
                }
                .duration = 100

        search_layout.animate()
                .translationX(0f)
                .scaleX(1f)
                .alpha(1f)
                .withEndAction {
                    search_layout.visibility = View.VISIBLE
                    var params = FrameLayout.LayoutParams(DensityHelper.dp2px(286f), DensityHelper.dp2px(38f))
                    params.gravity = Gravity.CENTER_VERTICAL
                    search_layout.layoutParams = params
                }
                .duration = 100

    }

    @SuppressLint("NewApi")
    fun hideSearchEdit() {
        var translationX = DensityHelper.px2dp(context, search_edit_text.width.toFloat()) - DensityHelper.px2dp(context, bt_search.width.toFloat())
        bt_search.animate()
                .alpha(1f)
                .withStartAction {
                    var params = FrameLayout.LayoutParams(DensityHelper.dp2px(248f), DensityHelper.dp2px(38f))
                    params.gravity = Gravity.CENTER_VERTICAL
                    search_layout.layoutParams = params
                }
                .duration = 100

        search_layout.animate()
                .scaleX(.8f)
                .translationX(translationX)
                .alpha(0f)
                .withEndAction {
                    bt_search_cancel.visibility = View.GONE
                    bt_search.visibility = View.VISIBLE
                    search_layout.visibility = View.GONE
                }
                .duration = 100


    }


}