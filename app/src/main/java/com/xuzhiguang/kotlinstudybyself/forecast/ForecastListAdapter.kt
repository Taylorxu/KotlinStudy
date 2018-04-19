package com.xuzhiguang.kotlinstudybyself.forecast

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.xuzhiguang.xzglibrary.view.XViewHolder

/**
 * Created by 徐志广 on 2018/3/27.
 */
 open class ForecastListAdapter<Data, Binding : ViewDataBinding>(var variableId: Int, var holderLayout: Int) : RecyclerView.Adapter<XViewHolder<Data, Binding>>() {
    var dataList: List<Data>? = ArrayList()

    override fun onBindViewHolder(holder: XViewHolder<Data, Binding>?, position: Int) {
        holder!!.fill(variableId, dataList!![position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): XViewHolder<Data, Binding> {
        var viewHolder: XViewHolder<Data, Binding>? = XViewHolder(LayoutInflater.from(parent?.context).inflate(holderLayout, parent, false))
        return viewHolder!!
    }

    override fun getItemCount(): Int {
        return dataList!!.size
    }


}
