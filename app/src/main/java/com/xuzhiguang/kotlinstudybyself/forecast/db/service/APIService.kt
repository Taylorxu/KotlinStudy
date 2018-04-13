package com.xuzhiguang.kotlinstudybyself.forecast.db.service

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.xuzhiguang.kotlinstudybyself.forecast.db.service.dataClass.DataUser
import com.xuzhiguang.kotlinstudybyself.forecast.db.service.dataClass.ResultModel
import com.xuzhiguang.xzglibrary.view.http.XRequest
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable

/**
 * Created by Administrator on 2018/4/12.
 */
interface APIService {
    @FormUrlEncoded
    @POST("UserLoginServlet")
    fun login(@FieldMap param: Map<String, String>): Observable<Response<ResultModel<Void>>>










    companion object {

        var gson: Gson = GsonBuilder().enableComplexMapKeySerialization().create()
        fun get(): APIService {
            var retrofit: Retrofit? = Retrofit.Builder()
                    .baseUrl("http://192.168.88.30:9189/MCBM/")
                    .client(XRequest.build().mOkHttpClient)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            return retrofit!!.create(APIService::class.java)
        }
    }

}