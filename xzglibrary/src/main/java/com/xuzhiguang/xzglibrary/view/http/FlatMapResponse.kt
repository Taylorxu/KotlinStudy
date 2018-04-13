package com.xuzhiguang.xzglibrary.view.http

import retrofit2.Response
import rx.Observable
import rx.functions.Func1
import java.io.IOException

/**
 * Created by Administrator on 2018/4/12.
 */
class FlatMapResponse<T>   {


    fun returnObserable(t: Response<T>): Observable<T> {

        if (t.isSuccessful) {
            return Observable.just(t.body())
        } else {
            return try {
                Observable.error<T>(Error(t.code(), t.errorBody().string()))
            } catch (e: IOException) {
                Observable.error<T>(Error(t.code(), e.message))
            }

        }
    }


}

