package com.xuzhiguang.kotlinstudybyself.forecast.navigationPages

import android.animation.Animator
import android.annotation.SuppressLint
import android.content.Context
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat.animate
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
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
        showSearchEdit()
        bt_search.setOnClickListener { showSearchEdit() }
        bt_search_cancel.setOnClickListener { hideSearchEdit() }
    }

    @SuppressLint("NewApi")
    fun showSearchEdit() {
        bt_search.animate().rotationY(180f)
                .alpha(0f)
                .withEndAction { panel_frame.visibility = View.GONE }
                .duration = 300

        search_edit_text.animate().translationX(0f).alpha(1f)
                .duration = 500

        bt_search_cancel.animate().rotationY(0f)
                .alpha(1f)
                .withStartAction { search_layout.visibility = View.VISIBLE }
                .duration = 500


    }

    @SuppressLint("NewApi")
    fun hideSearchEdit() {
        bt_search.animate().rotationY(0f)
                .alpha(1f)
                .withStartAction { panel_frame.visibility = View.VISIBLE }
                .duration = 500

        search_edit_text.animate().translationX(search_edit_text.width.toFloat()).alpha(0f)
                .duration = 300
        bt_search_cancel.animate().rotationY(180f)
                .alpha(0f)
                .withEndAction { search_layout.visibility = View.GONE }
                .duration = 300


    }
}