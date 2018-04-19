package com.xuzhiguang.xzglibrary.helperTool

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue

/**
 * Created by 徐志广 on 2018/4/19.
 */
object DensityHelper {
    /**
     * dp转px
     * @return
     */
    fun dp2px(dpVal: Float): Int {
        val scale = Resources.getSystem().displayMetrics.density
        return (dpVal * scale + 0.5f).toInt()
    }

    /**
     * sp转px
     * @param context
     * @return
     */
    fun sp2px(context: Context, spVal: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.resources.displayMetrics).toInt()
    }

    fun sp2px(spVal: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, Resources.getSystem().displayMetrics).toInt()
    }

    /**
     * px转dp
     * @param context
     * @param pxVal
     * @return
     */
    fun px2dp(context: Context, pxVal: Float): Float {
        val scale = context.resources.displayMetrics.density
        return pxVal / scale
    }

    /**
     * px转sp
     * @param pxVal
     * @return
     */
    fun px2sp(context: Context, pxVal: Float): Float {
        return pxVal / context.resources.displayMetrics.scaledDensity
    }

}