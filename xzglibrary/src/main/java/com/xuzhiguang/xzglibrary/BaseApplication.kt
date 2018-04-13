package com.xuzhiguang.xzglibrary

import android.app.Application
import android.content.Context
import kotlin.properties.Delegates

/**
 * Created by Administrator on 2018/4/13.
 */
open class BaseApplication : Application() {

    companion object {
        var instance: BaseApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}