package com.xuzhiguang.kotlinstudybyself.forecast.navigationPages

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xuzhiguang.kotlinstudybyself.BR
import com.xuzhiguang.kotlinstudybyself.R
import com.xuzhiguang.kotlinstudybyself.databinding.ItemForeCastBinding
import com.xuzhiguang.kotlinstudybyself.forecast.DetailActivity
import com.xuzhiguang.kotlinstudybyself.forecast.db.service.APIService
import com.xuzhiguang.kotlinstudybyself.forecast.db.service.dataClass.Forecast
import com.xuzhiguang.kotlinstudybyself.forecast.db.service.dataClass.Weather
import com.xuzhiguang.xzglibrary.helperTool.NiceToast
import com.xuzhiguang.xzglibrary.helperTool.Passenger
import com.xuzhiguang.xzglibrary.http.FlatMapResponse
import com.xuzhiguang.xzglibrary.view.XAdapter
import com.xuzhiguang.xzglibrary.view.recycleViewExtension.ItemDecorationEx
import com.xuzhiguang.xzglibrary.view.recycleViewExtension.footer.LoadMoreFooterView
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by 徐志广 on 2018/4/25.
 */
class FirstFragment : Fragment() {
    var currentPage = 1
    var footerView: LoadMoreFooterView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view: View? = inflater?.inflate(R.layout.fragment_first, container, false)

        return view?.rootView!!
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }

    private fun initView() {
        forecast_list.layoutManager = LinearLayoutManager(context)
        forecast_list.iAdapter = adapter
        forecast_list.setOnRefreshListener { refreshData() }
        footerView = forecast_list.loadMoreFooterView as LoadMoreFooterView?
        forecast_list.setOnLoadMoreListener { onLoadMore() }
        forecast_list.addItemDecoration(ItemDecorationEx.LineDecoration(context))
        forecast_list.post {
            forecast_list.setRefreshing(true)
        }

    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    var adapter: XAdapter<Forecast, ItemForeCastBinding> = XAdapter.SimpleAdapter(BR.data, R.layout.item_fore_cast) { forecast, itemView ->
        var intent = Intent(context, DetailActivity().javaClass)
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity, itemView.root, "text_weather").toBundle())
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
                .flatMap { t -> FlatMapResponse.callResponse(t) }
                .flatMap { t -> FlatMapResponse.callResult(t) }
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
                        NiceToast.toast("获取天气列表失败${e?.message}")
                        forecast_list.setRefreshing(false)
                        footerView?.setStatus(LoadMoreFooterView.Status.GONE)
                    }

                })

    }


}