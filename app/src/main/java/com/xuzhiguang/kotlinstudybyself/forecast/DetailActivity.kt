package com.xuzhiguang.kotlinstudybyself.forecast

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.app.Notification
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.transition.TransitionInflater
import android.view.View
import android.view.Window
import com.xuzhiguang.kotlinstudybyself.R
import com.xuzhiguang.kotlinstudybyself.databinding.ActivityDetailBinding
import com.xuzhiguang.kotlinstudybyself.forecast.base.BaseActivity
import com.xuzhiguang.xzglibrary.helperTool.CrossFadeHelper
import com.xuzhiguang.xzglibrary.helperTool.Passenger
import kotlinx.android.synthetic.main.activity_detail.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by 徐志广 on 2018/4/19.
 */
class DetailActivity : BaseActivity() {
    var binding: ActivityDetailBinding? = null
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        EventBus.getDefault().register(this)  //事件注册
        CrossFadeHelper.crossFade(tv_message, progress_bar)
    }

    //事件订阅
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun setMessage(passenger: Passenger<WeatherBean>) {
        if (passenger.code == 1) binding?.data = passenger.extra

    }


    override fun onDestroy() {
        super.onDestroy()
        if (EventBus.getDefault().isRegistered(this)) EventBus.getDefault().unregister(this)
    }
}