package com.xuzhiguang.xzglibrary.http

import retrofit2.Response
import rx.Observable
import java.io.IOException

/**
 * Created by 徐志广 on 2018/4/12.
 * Response retorfit 接口 返回的第一层 response
 * 通过Observable在将返回的结果发送到下一层
 */
object FlatMapResponse {

    fun <T> call(t: Response<T>): Observable<T> {
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

