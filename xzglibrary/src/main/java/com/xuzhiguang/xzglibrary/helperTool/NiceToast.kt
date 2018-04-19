package com.xuzhiguang.xzglibrary.helperTool

import android.widget.Toast
import com.xuzhiguang.xzglibrary.BaseApplication

/**
 * Created by 徐志广 on 2018/4/13.
 */
object NiceToast {
    fun toast(message: String, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(BaseApplication.instance.applicationContext, message, length).show()
    }
}