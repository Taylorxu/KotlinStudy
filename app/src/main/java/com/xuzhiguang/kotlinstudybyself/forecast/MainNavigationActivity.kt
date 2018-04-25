package com.xuzhiguang.kotlinstudybyself.forecast

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.xuzhiguang.kotlinstudybyself.R
import com.xuzhiguang.kotlinstudybyself.forecast.navigationPages.FirstFragment
import com.xuzhiguang.kotlinstudybyself.forecast.navigationPages.FourthFragment
import com.xuzhiguang.kotlinstudybyself.forecast.navigationPages.SecondFragment
import com.xuzhiguang.kotlinstudybyself.forecast.navigationPages.ThirdFragment
import com.xuzhiguang.xzglibrary.view.xViewElement.XNavigationBar
import kotlinx.android.synthetic.main.activity_main_navigation.*

/**
 * Created by 徐志广 on 2018/4/24.
 */
class MainNavigationActivity : AppCompatActivity() {


    var fragmentManager: FragmentManager? = null
    private val firstFragment: Fragment by lazy { FirstFragment() }
    private val secondFragment: Fragment by lazy { SecondFragment() }
    private val thirdFragment: Fragment by lazy { ThirdFragment() }
    private val fourthFragment: Fragment by lazy { FourthFragment() }
    private val fragmentList = listOf(firstFragment, secondFragment, thirdFragment, fourthFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_navigation)
        navigation_bar_group.setOnCheckedChangeListenter(barCheckListener)
        fragmentManager = supportFragmentManager
        addFragment(firstFragment, null, R.id.frame_layout_content)
    }

    /**
     *显示或隐藏fragment
     */
    fun showOrhideFragment(fragment: Fragment) {
        for (fragmentInList in fragmentList) {
            if (fragment === fragmentInList) {
                fragmentManager?.beginTransaction()?.show(fragment)?.commit()
            } else {
                fragmentManager?.beginTransaction()?.hide(fragmentInList)?.commit()
            }
        }
    }

    /**
     * 添加fragment 初始换
     */
    fun addFragment(fragment: Fragment?, listFragment: List<Fragment>?, containerViewId: Int) {
        val transaction = fragmentManager?.beginTransaction()
        if (fragment === null) {
            for (fragment in listFragment!!) {
                transaction?.add(containerViewId, fragment)
            }
        } else {
            transaction?.add(containerViewId, fragment)
        }
        transaction?.commit()
    }

    /**
     *  //使用另一个Fragment替换当前的，实际上就是remove()然后add()的合体
     */
    fun replacrFragment(newFragment: Fragment) {
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.frame_layout_content, newFragment)
        transaction?.commit()
    }

    var barCheckListener = object : XNavigationBar.OnCheckedChangeListener {
        override fun onCheckedChanged(checkedId: Int) {
            when (checkedId) {
                com.xuzhiguang.xzglibrary.R.id.rb_0 -> {
                    replacrFragment(firstFragment)
                }
                com.xuzhiguang.xzglibrary.R.id.rb_1 -> {
                    replacrFragment(secondFragment)
                }
                com.xuzhiguang.xzglibrary.R.id.rb_2 -> {
                    replacrFragment(thirdFragment)
                }
                com.xuzhiguang.xzglibrary.R.id.rb_3 -> {
                    replacrFragment(fourthFragment)
                }

            }
        }

    }


}

