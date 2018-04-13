package com.xuzhiguang.kotlinstudybyself.forecast.db.service.dataClass

/**
 * Created by Administrator on 2018/4/13.
 */
data class ResultModel<T>(val Status:Int,val Reason:String ,val rows:T )