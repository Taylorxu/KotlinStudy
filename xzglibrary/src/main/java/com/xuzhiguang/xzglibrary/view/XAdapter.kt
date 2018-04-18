package com.xuzhiguang.xzglibrary.view

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.ArrayList

/**
 * Created by Administrator on 2018/4/11.
 */
open abstract class XAdapter<Data, Binding : ViewDataBinding> : RecyclerView.Adapter<XViewHolder<Data, Binding>>() {
    var dataList = mutableListOf<Data>()

    override fun getItemCount(): Int {
        return dataList!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): XViewHolder<Data, Binding> {
        var viewHolder: XViewHolder<Data, Binding>? = XViewHolder(LayoutInflater.from(parent?.context).inflate(holderLayout(viewType), parent, false))
        if (itemClickListener != null)
            viewHolder?.binding?.root?.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    itemClickListener?.onItemClick(viewHolder)
                }
            })
        return viewHolder!!
    }

    abstract fun holderLayout(viewType: Int): Int

    //itemOnclick
    var itemClickListener: OnItemClickListener<Data, Binding>? = null

    open interface OnItemClickListener<Data, Binding : ViewDataBinding> {
        fun onItemClick(h: XViewHolder<Data, Binding>)
    }

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

    //初始
    fun setList(l: MutableList<Data>) {
        dataList = l
        notifyDataSetChanged()
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