package com.jc.collantes.emovie.utils

import org.junit.Test

class ImageSizerTest{

    @Test
    fun getHeightByWidth(){
        val width = 138f
        val height = ImageSizer().getHeightByWidth(width)
        assert(height == 180f)
    }
}