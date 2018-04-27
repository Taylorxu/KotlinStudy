package com.xuzhiguang.kotlinstudybyself.forecast.db.service

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.xuzhiguang.kotlinstudybyself.forecast.db.service.dataClass.Weather
import com.xuzhiguang.xzglibrary.http.ResultModel
import com.xuzhiguang.xzglibrary.http.XRequest
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import rx.Observable

/**
 * Created by 徐志广 on 2018/4/12.
 */
interface APIService {
    @FormUrlEncoded
    @POST("UserLoginServlet")
    fun login(@FieldMap param: Map<String, String>): Observable<Response<ResultModel<Void?>>>

    @GET("json.shtml?city=北京")
    fun getWeather(): Observable<Response<ResultModel<Weather>>>


    companion object {

        var gson: Gson = GsonBuilder().enableComplexMapKeySerialization().create()
        fun get(): APIService {
            var retrofit: Retrofit? = Retrofit.Builder()
                    .baseUrl("https://www.sojson.com/open/api/weather/")
                    .client(XRequest.build().mOkHttpClient)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            return retrofit!!.create(APIService::class.java)
        }
    }

}