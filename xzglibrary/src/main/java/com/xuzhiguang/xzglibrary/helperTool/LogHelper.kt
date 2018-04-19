package com.xuzhiguang.xzglibrary.helperTool

import android.util.Log

/**
 * Created by 徐志广 on 2018/4/19.
 */
object LogHelper {
    private val deBug: Boolean = true
    fun e(mes: String, tag: String = "XZGLOGOUT") {
        if (deBug) Log.e(tag, mes)
    }
}