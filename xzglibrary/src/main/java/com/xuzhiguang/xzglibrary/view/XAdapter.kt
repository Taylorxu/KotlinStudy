package com.xuzhiguang.xzglibrary.view

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by 徐志广 on 2018/4/11.
 */
open abstract class XAdapter<Data, Binding : ViewDataBinding> : RecyclerView.Adapter<XViewHolder<Data, Binding>>() {
    var dataList = mutableListOf<Data>()

    override fun getItemCount(): Int {
        return dataList!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): XViewHolder<Data, Binding> {
        var viewHolder: XViewHolder<Data, Binding>? = XViewHolder(LayoutInflater.from(parent?.context).inflate(holderLayout(viewType), parent, false))
        return viewHolder!!
    }

    abstract fun holderLayout(viewType: Int): Int

    //variableId 是R.layout.xxx 中<layout><data></data></layout> BR.data
    open class SimpleAdapter<Data, Binding : ViewDataBinding>(private var variableId: Int, private var holderLayout: Int, private var itemClick: ((Data, Binding) -> Unit)? =null) : XAdapter<Data, Binding>() {

        //holderLayout 是R.layout.xxx 布局文件在资源中ID
        override fun holderLayout(viewType: Int): Int {
            return holderLayout
        }

        override fun onBindViewHolder(holder: XViewHolder<Data, Binding>?, position: Int) {
            holder?.fill(variableId, dataList!![position])
            holder?.itemView?.setOnClickListener { itemClick?.invoke(dataList!![position], holder?.binding) }
        }


    }

    //初始
    fun setList(l: MutableList<Data>) {
        dataList = l
        notifyDataSetChanged()
    }

    //追加list
    fun addItems(l: MutableList<Data>) {
        if (l === null || l.isEmpty()) return
        dataList?.addAll(l)
        notifyItemInserted(dataList?.size?.let { it - 1 })
        notifyItemRangeChanged(dataList?.size - l.size, l.size)
    }

    //追加item
    fun addItem(data: Data) {
        dataList?.add(data)
        notifyItemInserted(dataList?.size?.let { it - 1 })
    }

    //指定位置
    fun addItem(data: Data, position: Int) {
        dataList.add(position, data)
        notifyItemInserted(position)
    }

    //删除指定
    fun removeItem(position: Int) {
        dataList.removeAt(position)
        notifyItemRemoved(position)
    }

    //删除全部
    fun removeAll() {
        dataList.clear()
        notifyDataSetChanged()
    }
}