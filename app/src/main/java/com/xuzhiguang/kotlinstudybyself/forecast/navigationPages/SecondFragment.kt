package com.xuzhiguang.kotlinstudybyself.forecast.navigationPages

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xuzhiguang.kotlinstudybyself.R

/**
 * Created by Administrator on 2018/4/25.
 */
class SecondFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
       val view: View? = inflater?.inflate(R.layout.fragment_second,container,false)
        return view?.rootView!!
    }
}