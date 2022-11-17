package com.jc.collantes.emovie.utils

import org.junit.Assert
import org.junit.Test

class UtilsTest{

    @Test
    fun formatRate(){
        val baseRate = 2.412f
        val rateFormatted = Utils.formatRate(baseRate)
        Assert.assertEquals("2.4", rateFormatted.toString())
    }

    @Test
    fun getYearDate(){
        val date = "2022-12-10"
        val year = Utils.getYearOfDate(date)
        Assert.assertEquals("2022",year)
    }

}