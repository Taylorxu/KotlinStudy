package com.xuzhiguang.kotlinstudybyself.forecast

import android.animation.Animator
import android.os.Build
import android.support.annotation.RequiresApi
import android.transition.ArcMotion
import android.transition.ChangeBounds
import android.transition.TransitionValues
import android.view.ViewGroup
import android.view.animation.AnimationUtils



/**
 * Created by 徐志广 on 2018/4/23.
 */
class CustomChangeBounds : ChangeBounds() {
    override fun createAnimator(sceneRoot: ViewGroup?, startValues: TransitionValues?, endValues: TransitionValues?): Animator {
        val changeBounds = super.createAnimator(sceneRoot, startValues, endValues)
        if (startValues == null || endValues == null || changeBounds == null)
            return changeBounds

        changeBounds.duration = 500
        changeBounds.interpolator = AnimationUtils.loadInterpolator(sceneRoot?.context,
                android.R.interpolator.fast_out_slow_in)!!
        return changeBounds
    }

    override fun captureStartValues(transitionValues: TransitionValues?) {
        super.captureStartValues(transitionValues)
    }
}