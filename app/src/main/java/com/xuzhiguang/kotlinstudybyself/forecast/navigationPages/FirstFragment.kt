package com.xuzhiguang.kotlinstudybyself.forecast.navigationPages

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.xuzhiguang.kotlinstudybyself.R
import com.xuzhiguang.kotlinstudybyself.R.anim.search_in
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * Created by 徐志广 on 2018/4/25.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view: View? = inflater?.inflate(R.layout.fragment_first, container, false)
        return view?.rootView!!
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }

    private fun initView() {
        bt_search.setOnClickListener { showSearchEdit() }
        bt_search_cancel.setOnClickListener { hideSearchEdit() }
    }

    fun showSearchEdit() {
        val searchPanelOut = AnimationUtils.loadAnimation(this.activity, R.anim.search_panel_out)
        panel_frame.startAnimation(searchPanelOut)
        panel_frame.visibility = View.GONE
        search_layout.visibility = View.VISIBLE
        val searchIn = AnimationUtils.loadAnimation(this.activity, R.anim.search_in)
        search_layout.startAnimation(searchIn)

    }

    fun hideSearchEdit() {
        panel_frame.visibility = View.VISIBLE
        val searchPanelIn = AnimationUtils.loadAnimation(this.activity, R.anim.search_panel_in)
        panel_frame.startAnimation(searchPanelIn)
        search_layout.visibility = View.GONE
        val searchOut = AnimationUtils.loadAnimation(this.activity, R.anim.search_out)
        search_layout.startAnimation(searchOut)

    }
}