package com.xuzhiguang.xzglibrary.http

import rx.Observable

/**
 * Created by 徐志广 on 2018/4/13.
 * 最后返回的是resultModel<T>层
 * ResultModel 将返回结果 转换成对象 T 也就是对应 数据集合对象
 * Observable<T> 泛型 决定subscriber中的泛型
 *
 */
object FlatMapResult {
    fun <T>call(t: ResultModel<T>): Observable<T> {
        return if (200 == t.status) {
            Observable.just(t.data)
        } else {
            Observable.error(Error(t.status, t.message))
        }
    }
}