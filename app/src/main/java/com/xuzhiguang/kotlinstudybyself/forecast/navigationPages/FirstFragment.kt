package com.xuzhiguang.kotlinstudybyself.forecast.navigationPages

import android.animation.Animator
import android.annotation.SuppressLint
import android.content.Context
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat.animate
import android.util.Half.toFloat
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import com.xuzhiguang.kotlinstudybyself.R
import com.xuzhiguang.kotlinstudybyself.R.anim.search_in
import com.xuzhiguang.xzglibrary.helperTool.DensityHelper
import com.xuzhiguang.xzglibrary.helperTool.LogHelper
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * Created by 徐志广 on 2018/4/25.
 */
class FirstFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view: View? = inflater?.inflate(R.layout.fragment_first, container, false)
        return view?.rootView!!
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }


    private fun initView() {
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