package com.xuzhiguang.kotlinstudybyself.forecast.base

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.transition.TransitionInflater
import android.view.Window

/**
 * Created by 徐志广 on 2018/4/23.
 */
open class BaseActivity : AppCompatActivity() {
    var isTransition = false
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设置contentFeature,可使用切换动画
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        var share = TransitionInflater.from(this).inflateTransition(android.R.transition.explode)
        window.enterTransition = share
        window.exitTransition = share
        /*    window.sharedElementEnterTransition =share      //也可以是自定义的动画类 对象
            window.sharedElementExitTransition =share*/

    }

    fun transition() {
        isTransition = true
    }
}