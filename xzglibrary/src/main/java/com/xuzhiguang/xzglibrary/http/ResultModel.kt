package com.xuzhiguang.xzglibrary.http

/**
 * Created by Administrator on 2018/4/13.
 */
data class ResultModel<T>(val Status:Int,val Reason:String ,var rows:T )