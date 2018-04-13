package com.xuzhiguang.xzglibrary.view.http

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.parkingwang.okhttp3.LogInterceptor.LogInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Administrator on 2018/4/12.
 */
class XRequest(gson: Gson = GsonBuilder().enableComplexMapKeySerialization().create())
    : Interceptor {

    var mOkHttpClient: OkHttpClient? = null

    init {
        initOkHttpClient()
    }

    companion object {
        fun build(): XRequest = XRequest()
    }


    private fun initOkHttpClient() {
        if (mOkHttpClient === null)
            mOkHttpClient = OkHttpClient().newBuilder()
                    .addInterceptor(LogInterceptor())
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(this)
                    .build()
    }


    override fun intercept(chain: Interceptor.Chain?): Response {
        val request = chain!!.request()
        val t1 = System.nanoTime()
        val builder = request.newBuilder()
        val response = chain.proceed(builder.build())
        val t2 = System.nanoTime()
        val mediaType = response.body().contentType()
        if ("text/html;charset=utf-8" == mediaType.toString()) {
            val content = response.body().string()
            return response.newBuilder()
                    .body(okhttp3.ResponseBody.create(mediaType, content))
                    .build()
        }
        return response
    }

}