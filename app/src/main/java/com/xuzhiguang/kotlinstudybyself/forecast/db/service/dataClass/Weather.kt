package com.xuzhiguang.kotlinstudybyself.forecast.db.service.dataClass

/**
 * Created by Administrator on 2018/4/27.
 *
 * {"date":"20180427","message":"Success !","status":200,"city":"北京","count":1074,"data":{"shidu":"56%","pm25":71.0,"pm10":112.0,"quality":"良","wendu":"18","ganmao":"极少数敏感人群应减少户外活动","yesterday":{"date":"26日星期四","sunrise":"05:23","high":"高温 26.0℃","low":"低温 12.0℃","sunset":"19:03","aqi":89.0,"fx":"南风","fl":"<3级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},"forecast":[{"date":"27日星期五","sunrise":"05:22","high":"高温 25.0℃","low":"低温 13.0℃","sunset":"19:04","aqi":100.0,"fx":"西南风","fl":"3-4级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"28日星期六","sunrise":"05:21","high":"高温 27.0℃","low":"低温 17.0℃","sunset":"19:05","aqi":96.0,"fx":"西南风","fl":"3-4级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"29日星期日","sunrise":"05:19","high":"高温 29.0℃","low":"低温 17.0℃","sunset":"19:06","aqi":109.0,"fx":"南风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"30日星期一","sunrise":"05:18","high":"高温 26.0℃","low":"低温 15.0℃","sunset":"19:07","aqi":64.0,"fx":"东北风","fl":"3-4级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"01日星期二","sunrise":"05:17","high":"高温 25.0℃","low":"低温 15.0℃","sunset":"19:08","aqi":40.0,"fx":"南风","fl":"<3级","type":"阴","notice":"不要被阴云遮挡住好心情"}]}}
 */
data class Weather(val shidu: String,
                   val pm25: Int,
                   val pm10: Int,
                   val quality: String,
                   val wendu: String,
                   val ganmao: String,
                   val yesterday: Yesterday,
                   val forecast: List<Forecast>)

data class Yesterday(val date: String,
                     val type: String,
                     val pm10: Int,
                     val sunrise: String,
                     val high: String,
                     val low: String,
                     val sunset: String,
                     val notice: String,
                     val fx: String,
                     val fl: String
)

data class Forecast(val date: String,
                    val type: String,
                    val pm10: Int,
                    val sunrise: String,
                    val high: String,
                    val low: String,
                    val sunset: String,
                    val notice: String,
                    val fx: String,
                    val fl: String
)