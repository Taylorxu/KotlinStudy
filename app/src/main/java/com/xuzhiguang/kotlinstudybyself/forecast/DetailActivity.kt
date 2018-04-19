package com.xuzhiguang.kotlinstudybyself.forecast

import android.app.Notification
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.xuzhiguang.kotlinstudybyself.R
import com.xuzhiguang.xzglibrary.helperTool.Passenger
import kotlinx.android.synthetic.main.activity_detail.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by Administrator on 2018/4/19.
 */
class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        EventBus.getDefault().register(this)  //事件注册
    }

    //事件订阅
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun setMessage(passenger: Passenger<WeatherBean>) {
        if (passenger.code == 1)
            tv_message.text = passenger.extra?.case
    }

    override fun onDestroy() {
        super.onDestroy()
        if (EventBus.getDefault().isRegistered(this)) EventBus.getDefault().unregister(this)
    }
}