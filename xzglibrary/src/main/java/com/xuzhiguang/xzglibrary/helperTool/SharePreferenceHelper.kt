package com.xuzhiguang.xzglibrary.helperTool

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by Administrator on 2018/4/27.
 * 使用
 *   根据key获取某个存储里的值  -------var isLogInPreference:Boolean by SharePreference.perference(context, PreferenceKeList.isLogIn, false)
 *   存储-------将获取的直接赋个值isLogInPreference=true
 */
object SharePreference {
    fun <T> perference(context: Context, name: String, default: T) = Preference(context, name, default)
}

class Preference<T>(private val context: Context, private val name: String,
                    private val default: T) : ReadWriteProperty<Any?, T> {


    /**
     * 获取 SharedPreferences
     */
    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences("default", Context.MODE_PRIVATE)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return findPreference(name, default)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreference(name, value)
    }

    @Suppress("UNCHECKED_CAST")
    private fun findPreference(name: String, default: T): T = with(prefs) {
        var value: Any = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("未发现")
        }
        value as T
    }

    @SuppressLint("CommitPrefEdits")
    private fun putPreference(name: String, value: T) = with(prefs.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("无法保存输入的值")
        }.apply()
    }

}