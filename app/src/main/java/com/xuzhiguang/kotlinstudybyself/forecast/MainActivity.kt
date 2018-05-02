package com.xuzhiguang.kotlinstudybyself.forecast

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.xuzhiguang.kotlinstudybyself.BR
import com.xuzhiguang.kotlinstudybyself.R
import com.xuzhiguang.kotlinstudybyself.databinding.ItemForeCastBinding
import com.xuzhiguang.kotlinstudybyself.forecast.db.service.APIService
import com.xuzhiguang.xzglibrary.helperTool.NiceToast
import com.xuzhiguang.xzglibrary.helperTool.Passenger
import com.xuzhiguang.xzglibrary.http.ResultModel
import com.xuzhiguang.xzglibrary.view.XAdapter
import com.xuzhiguang.xzglibrary.http.FlatMapResponse
import com.xuzhiguang.xzglibrary.http.FlatMapResult
import com.xuzhiguang.xzglibrary.view.XViewHolder
import com.xuzhiguang.xzglibrary.view.recycleViewExtension.footer.LoadMoreFooterView
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.startActivity
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.View
import com.xuzhiguang.kotlinstudybyself.forecast.db.service.dataClass.Forecast
import com.xuzhiguang.kotlinstudybyself.forecast.db.service.dataClass.Weather
import com.xuzhiguang.xzglibrary.helperTool.LogHelper
import com.xuzhiguang.xzglibrary.view.recycleViewExtension.ItemDecorationEx
import com.xuzhiguang.xzglibrary.view.recycleViewExtension.LineItemDecoration
import kotlinx.android.synthetic.main.activity_detail.view.*
import org.jetbrains.anko.Android


class MainActivity : AppCompatActivity() {
    var currentPage = 1
    var footerView: LoadMoreFooterView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }


    private fun initView() {
        forecast_list.layoutManager = LinearLayoutManager(this)
        forecast_list.iAdapter = adapter
        forecast_list.setOnRefreshListener { refreshData() }
        footerView = forecast_list.loadMoreFooterView as LoadMoreFooterView?
        forecast_list.setOnLoadMoreListener { onLoadMore() }
        forecast_list.addItemDecoration(ItemDecorationEx.LineDecoration(baseContext))
        forecast_list.post {
            forecast_list.setRefreshing(true)
        }

    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    var adapter: XAdapter<Forecast, ItemForeCastBinding> = XAdapter.SimpleAdapter(BR.data, R.layout.item_fore_cast) { forecast, itemView ->
        var intent = Intent(this@MainActivity, DetailActivity().javaClass)
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this@MainActivity, itemView.root, "text_weather").toBundle())
        var passenger = Passenger<Forecast>(1)
        passenger.extra = forecast
        EventBus.getDefault().postSticky(passenger)
    }

    private fun refreshData() {
        currentPage = 1
        footerView?.setStatus(LoadMoreFooterView.Status.GONE)
        getData(1)
    }

    private fun onLoadMore() {
        if (footerView?.canLoadMore()!!) {
            footerView?.setStatus(LoadMoreFooterView.Status.LOADING)
            getData(currentPage)
        }
    }


    /**
     * 接口数据请求
     */
    private fun getData(page: Int) {
        APIService.get().getWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { t -> FlatMapResponse.call(t) }
                .flatMap { t -> FlatMapResult.call(t) }
                .subscribe(object : Subscriber<Weather>() {
                    override fun onNext(t: Weather?) {
                        if (page == 1) {
                            adapter.setList(t?.forecast as MutableList<Forecast>)
                        } else {
                            adapter.addItems(t?.forecast as MutableList<Forecast>)
                        }
                        footerView?.setStatus(if (t.forecast.size < 20) LoadMoreFooterView.Status.THE_END else LoadMoreFooterView.Status.GONE)
                        currentPage += 1
                    }

                    override fun onCompleted() {
                        forecast_list.setRefreshing(false)
                    }

                    override fun onError(e: Throwable?) {
                        Log.e(localClassName + "74r", e?.printStackTrace().toString())
                        NiceToast.toast("获取天气列表失败${e?.message}")
                        forecast_list.setRefreshing(false)
                        footerView?.setStatus(LoadMoreFooterView.Status.GONE)
                    }

                })

    }


}







