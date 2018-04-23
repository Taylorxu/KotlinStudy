package com.xuzhiguang.xzglibrary.view.recycleViewExtension.footer

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.xuzhiguang.xzglibrary.R
import kotlinx.android.synthetic.main.layout_irecyclerview_load_more_footer_view.view.*

/**
 * Created by 徐志广 on 2018/4/19.
 */
class LoadMoreFooterView : FrameLayout {
    var mStatus: Status? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attr: AttributeSet) : super(context, attr)
    constructor(context: Context, attr: AttributeSet, defStyleAttr: Int) : super(context, attr, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_irecyclerview_load_more_footer_view, this, true)
        setStatus(Status.GONE)
    }

    fun setStatus(status: Status) {
        mStatus = status
        change()
    }

    fun canLoadMore(): Boolean {
        return mStatus == Status.GONE || mStatus == Status.ERROR
    }

    private fun change() {
        when (mStatus) {
            LoadMoreFooterView.Status.GONE -> {
                loadingView.visibility = View.GONE
                errorView.visibility = View.GONE
                theEndView.visibility = View.GONE
            }

            LoadMoreFooterView.Status.LOADING -> {
                loadingView.visibility = View.VISIBLE
                errorView.visibility = View.GONE
                theEndView.visibility = View.GONE
            }

            LoadMoreFooterView.Status.ERROR -> {
                loadingView.visibility = View.GONE
                errorView.visibility = View.VISIBLE
                theEndView.visibility = View.GONE
            }

            LoadMoreFooterView.Status.THE_END -> {
                loadingView.visibility = View.GONE
                errorView.visibility = View.GONE
                theEndView.visibility = View.VISIBLE
            }
        }
    }

    enum class Status {
        GONE, LOADING, ERROR, THE_END
    }

    interface OnRetryListener {
        fun onRetry(view: LoadMoreFooterView)
    }
}