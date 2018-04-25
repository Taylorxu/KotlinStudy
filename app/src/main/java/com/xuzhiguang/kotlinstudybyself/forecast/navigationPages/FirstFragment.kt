package com.xuzhiguang.kotlinstudybyself.forecast.navigationPages

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xuzhiguang.kotlinstudybyself.R
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * Created by Administrator on 2018/4/25.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
       val view: View? = inflater?.inflate(R.layout.fragment_first,container,false)
        return view?.rootView!!
    }
}