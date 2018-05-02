package com.xuzhiguang.xzglibrary.view

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by 徐志广 on 2018/4/11.
 */
class XViewHolder<Data, Binding : ViewDataBinding>(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var binding: Binding = DataBindingUtil.bind(itemView)

    fun fill(variableId: Int, data: Data ) {
        binding.setVariable(variableId, data)
    }
}