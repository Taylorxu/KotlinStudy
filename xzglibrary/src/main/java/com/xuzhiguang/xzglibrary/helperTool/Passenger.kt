package com.xuzhiguang.xzglibrary.helperTool

import java.util.*

/**
 * Created by 徐志广 on 2018/4/19.
 */
class Passenger<T>(code: Int, id: Int = 0) {
    var extra: T? = null
    var code: Int = code
    var id: Int = id

}