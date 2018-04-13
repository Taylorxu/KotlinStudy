package com.xuzhiguang.xzglibrary.http

import retrofit2.Response
import rx.Observable
import java.io.IOException

/**
 * Created by Administrator on 2018/4/12.
 */
class FlatMapResponse<T>   {

    fun call(t: Response<T>): Observable<T> {
        return if (t.isSuccessful) {
            Observable.just(t.body())
        } else {
            try {
                Observable.error<T>(Error(t.code(), t.errorBody().string()))
            } catch (e: IOException) {
                Observable.error<T>(Error(t.code(), e.message))
            }

        }
    }


}

