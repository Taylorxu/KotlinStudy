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
import android.widget.Toast
import com.xuzhiguang.xzglibrary.helperTool.NiceToast
import java.util.*


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
        addFragment(null, fragmentList, R.id.frame_layout_content)
        showOrhideFragment(fragmentList[0])
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
            listFragment?.forEach {
                transaction?.add(containerViewId, it)
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
                    showOrhideFragment(firstFragment)
                }
                com.xuzhiguang.xzglibrary.R.id.rb_1 -> {
                    showOrhideFragment(secondFragment)
                }
                com.xuzhiguang.xzglibrary.R.id.rb_2 -> {
                    showOrhideFragment(thirdFragment)
                }
                com.xuzhiguang.xzglibrary.R.id.rb_3 -> {
                    showOrhideFragment(fourthFragment)
                }

            }
        }

    }


    override fun onBackPressed() {
        var mBackKeyPressed = false
        if (!mBackKeyPressed) {
            NiceToast.toast("再按一次退出程序")
            mBackKeyPressed = true
            Timer().schedule(object : TimerTask() {
                override fun run() {
                    mBackKeyPressed = false
                }
            }, 2000)

        } else {
            this.finish()
            System.exit(0)
        }
    }


}

