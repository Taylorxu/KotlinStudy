package com.xuzhiguang.xzglibrary.view

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by Administrator on 2018/4/11.
 */
open abstract class XAdapter<Data, Binding : ViewDataBinding> : RecyclerView.Adapter<XViewHolder<Data, Binding>>() {
    var dataList: List<Data>? = ArrayList()

    override fun getItemCount(): Int {
        return dataList!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): XViewHolder<Data, Binding> {
        var viewHolder: XViewHolder<Data, Binding>? = XViewHolder(LayoutInflater.from(parent?.context).inflate(holderLayout(viewType), parent, false))
        initHolder(viewHolder!!, viewType)
        return viewHolder!!
    }

    abstract fun holderLayout(viewType: Int): Int

    fun initHolder(viewHolder: XViewHolder<Data, Binding>, viewType: Int) {}


    //variableId 是R.layout.xxx 中<layout><data></data></layout> BR.data
    open class SimpleAdapter<Data, Binding : ViewDataBinding>(var variableId: Int, var holderLayout: Int) : XAdapter<Data, Binding>() {

        //holderLayout 是R.layout.xxx 布局文件在资源中ID
        override fun holderLayout(viewType: Int): Int {
            return holderLayout
        }

        override fun onBindViewHolder(holder: XViewHolder<Data, Binding>?, position: Int) {
            holder?.fill(variableId, dataList!![position])
        }

    }
}