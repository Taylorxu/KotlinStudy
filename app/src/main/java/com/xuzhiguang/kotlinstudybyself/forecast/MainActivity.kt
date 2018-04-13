package com.xuzhiguang.kotlinstudybyself.forecast

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.xuzhiguang.kotlinstudybyself.BR
import com.xuzhiguang.kotlinstudybyself.R
import com.xuzhiguang.kotlinstudybyself.databinding.ItemForeCastBinding
import com.xuzhiguang.kotlinstudybyself.forecast.db.service.APIService
import com.xuzhiguang.kotlinstudybyself.forecast.db.service.dataClass.DataUser
import com.xuzhiguang.xzglibrary.helperTool.NiceToast
import com.xuzhiguang.xzglibrary.http.ResultModel
import com.xuzhiguang.xzglibrary.view.XAdapter
import com.xuzhiguang.xzglibrary.http.FlatMapResponse
import com.xuzhiguang.xzglibrary.http.FlatMapResult
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Response
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private val items = listOf(
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8",
            "Wed 6/25 - Cloudy - 22/17",
            "Thurs 6/26 - Rainy - 18/11",
            "Fri 6/27 - Foggy - 21/10",
            "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
            "Sun 6/29 - Sunny - 20/7"
    )

    var dataList = mutableListOf<WeatherBean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createData()
        getData()
        initView()
    }

    private fun createData() {

        for (i in 0..20) {
            val bean = WeatherBean()
            bean.case = "晴天" + i
            bean.city = "北京"
            bean.date = "2018-09-0$i"
            dataList.add(bean)
        }
    }

    var adapter: XAdapter<WeatherBean, ItemForeCastBinding> = XAdapter.SimpleAdapter(BR.data, R.layout.item_fore_cast)
    var adapter1: ForecastListAdapter<WeatherBean, ItemForeCastBinding> = ForecastListAdapter(BR.data, R.layout.item_fore_cast)

    override fun onResume() {
        super.onResume()

    }

    private fun getData() {
        var param = mapOf("accountNum" to "susan", "password" to "1")
        APIService.get().login(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { t -> FlatMapResponse<ResultModel<Void?>>().call(t) }
                .flatMap { t -> FlatMapResult<Void?>().call(t) }
                .subscribe(object : Subscriber<Void?>() {
                    override fun onError(e: Throwable?) {
                        Log.e(localClassName + "74r", e?.printStackTrace().toString())
//                        NiceToast.toast("登陆失败${e?.message}")
                    }

                    override fun onCompleted() {
                    }

                    override fun onNext(v: Void?) {
                        NiceToast.toast("登陆成功")
                    }

                })
    }

    fun flat(t: Response<DataUser>): Observable<DataUser> {
        return Observable.just(t.body())
    }

    private fun initView() {
        forecast_list.layoutManager = LinearLayoutManager(this)
        forecast_list.adapter = adapter
        adapter.dataList = dataList
    }


}






