package com.xuzhiguang.xzglibrary.helperTool

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.widget.ProgressBar

/**
 * Created by 徐志广 on 2018/4/19.
 */
object CrossFadeHelper {
    /**
     * 适合应用于类似详情界面的数据加载
     * view 内容需要显示。
     * progress_bar 需要被隐藏
     * 过程： 在layout 中view visible=gone; this fun will be used when data is loading; view'alpha has to be invisible then make view to be visible
     */
    fun crossFade(view: View, progress_bar: ProgressBar) {
        view.alpha = 0f
        view.visibility = View.VISIBLE
        view.animate().alpha(1f)
                .setListener(null)
                .duration = 500
        progress_bar.animate().alpha(0f)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        progress_bar.visibility = View.GONE
                    }
                })
                .duration = 500
    }
}