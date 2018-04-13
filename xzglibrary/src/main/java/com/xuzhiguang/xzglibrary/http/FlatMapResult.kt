package com.xuzhiguang.xzglibrary.http

import rx.Observable

/**
 * Created by Administrator on 2018/4/13.
 * 最后返回的是resultModel<T>层
 * Observable<T> 泛型 决定subscriber中的泛型
 */
class FlatMapResult<T> {
    fun call(t: ResultModel<T>): Observable<T> {
        return if (0 == t.Status) {
            Observable.just(t.rows)
        } else {
            Observable.error(Error(t.Status, t.Reason))
        }
    }
}