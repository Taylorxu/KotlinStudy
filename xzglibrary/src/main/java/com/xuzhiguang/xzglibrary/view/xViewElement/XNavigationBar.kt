package com.xuzhiguang.xzglibrary.view.xViewElement

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioButton
import com.xuzhiguang.xzglibrary.R
import kotlinx.android.synthetic.main.layout_navigation_bar_group.*
import kotlinx.android.synthetic.main.layout_navigation_bar_group.view.*
import org.jetbrains.anko.forEachChild

/**
 * 自定义 navigationBar
 * Created by Administrator on 2018/4/24.
 */
class XNavigationBar : LinearLayout {
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_navigation_bar_group, this, true)
        initViewParam()
        rb_0.isChecked = true
        rb_group.setOnCheckedChangeListener { group, checkedId -> onCheckedChangeListener(checkedId) }
    }

    private fun initViewParam() {
        rb_group.forEachChild { view: View -> view.setPadding(12, 6, 12, 10) }
    }

    var oldChecked: RadioButton? = null
    fun onCheckedChangeListener(checkedId: Int) {
        when (checkedId) {
            R.id.rb_0 -> {
                reSetOldChecked(rb_0)
//                rb_0.setPadding(0, 6, 0, 0)
            }
            R.id.rb_1 -> {
                reSetOldChecked(rb_1)
//                rb_1.setPadding(0, 6, 0, 0)
            }
            R.id.rb_2 -> {
                reSetOldChecked(rb_2)
                rb_2.setPadding(0, 6, 0, 0)
            }
            R.id.rb_3 -> {
                reSetOldChecked(rb_3)
//                rb_3.setPadding(0, 6, 0, 0)
            }

        }
    }

    fun reSetOldChecked(newChecked: RadioButton) {
        newChecked.setPadding(10, 6, 10, 10)
        oldChecked?.setPadding(12, 8, 12, 10)
        oldChecked = newChecked
    }

}