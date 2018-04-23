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
import android.R.transition.explode
import android.annotation.TargetApi
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.support.annotation.RequiresApi
import android.transition.TransitionInflater
import android.view.Window


class MainActivity : AppCompatActivity() {


    //不可操作list
    private val items = listOf(
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8",
            "Wed 6/25 - Cloudy - 22/17",
            "Thurs 6/26 - Rainy - 18/11",
            "Fri 6/27 - Foggy - 21/10",
            "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
            "Sun 6/29 - Sunny - 20/7"
    )
    //可操作list
    var dataList = mutableListOf<WeatherBean>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        createData()
//        getData()
        initView()
    }

    var footerView: LoadMoreFooterView? = null
    private fun initView() {
        xToolbar.setTitle("main Page")
        xToolbar.setBack(true, this)
        forecast_list.layoutManager = LinearLayoutManager(this)
        forecast_list.iAdapter = adapter
        forecast_list.setOnRefreshListener { createData() }
        footerView = forecast_list.loadMoreFooterView as LoadMoreFooterView?
        forecast_list.setOnLoadMoreListener { onLoadMore() }
        forecast_list.post {
            forecast_list.setRefreshing(true)
        }
        adapter.itemClickListener = onItemClickListenter

    }

    /* //匿名内部类
        var adapter: XAdapter<WeatherBean, ItemForeCastBinding> = object : XAdapter.SimpleAdapter<WeatherBean, ItemForeCastBinding>(BR.data, R.layout.item_fore_cast) {
            override fun onBindViewHolder(holder: XViewHolder<WeatherBean, ItemForeCastBinding>?, position: Int) {
                super.onBindViewHolder(holder, position)
            }
        }*/
    var adapter: XAdapter<WeatherBean, ItemForeCastBinding> = XAdapter.SimpleAdapter(BR.data, R.layout.item_fore_cast)
    //添加item监听事件
    var onItemClickListenter: XAdapter.OnItemClickListener<WeatherBean, ItemForeCastBinding> = object : XAdapter.OnItemClickListener<WeatherBean, ItemForeCastBinding> {
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onItemClick(h: XViewHolder<WeatherBean, ItemForeCastBinding>) {
            //开始一个animation
            var intent = Intent(this@MainActivity, DetailActivity().javaClass)
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this@MainActivity,h.binding.root,"text_weather").toBundle())


            var passenger = Passenger<WeatherBean>(1)
            passenger.extra = h.binding.data
            //发送黏贴事件
            EventBus.getDefault().postSticky(passenger)

        }

    }


    private fun createData() {
        footerView?.setStatus(LoadMoreFooterView.Status.GONE)
        if (dataList.size > 0) dataList.clear()
        for (i in 0..20) {
            val bean = WeatherBean()
            bean.case = "晴天" + i
            bean.city = "北京"
            bean.date = "2018-09-0$i"
            dataList.add(bean)
        }
        adapter.dataList = dataList
        Handler().postDelayed({ forecast_list.setRefreshing(false) }, 3000)

    }

    private fun onLoadMore() {
        if (footerView?.canLoadMore()!! && adapter.itemCount > 0) {
            footerView?.setStatus(LoadMoreFooterView.Status.LOADING)
            loadMore()
        }
    }

    private fun loadMore() {
        var moreList = mutableListOf<WeatherBean>()
        for (i in 0..20) {
            val bean = WeatherBean()
            bean.case = "加载--晴天" + i
            bean.city = "加载--北京"
            bean.date = "加载--2018-09-0$i"
            moreList.add(bean)
        }

        if (dataList.size > 120) {
            footerView?.setStatus(LoadMoreFooterView.Status.THE_END)
        } else {
            footerView?.postDelayed({
                adapter.addItems(moreList)
                footerView?.setStatus(LoadMoreFooterView.Status.GONE)
            }, 2000)
        }
    }

    /**
     * 接口数据请求
     */
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
                        NiceToast.toast("登陆失败${e?.message}")
                    }

                    override fun onCompleted() {
                    }

                    override fun onNext(v: Void?) {
                        NiceToast.toast("登陆成功")
                    }

                })
    }


}







