package com.jc.collantes.emovie.utils

class Utils {

    companion object{
        fun formatRate(rate: Float): Float{
            return String.format("%.1f",rate).toFloat()
        }

        fun getYearOfDate(date : String):String{
            return date.split("-")[0]
        }
    }
}