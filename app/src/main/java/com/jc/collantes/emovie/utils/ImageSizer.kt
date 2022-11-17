package com.jc.collantes.emovie.utils

class ImageSizer(
    private val x1: Float = 138f,
    private val y1: Float = 180f,
    private val x2: Float = 155f,
    private val y2: Float = 205f
) {

    fun getHeightByWidth(width: Float):Float {
        return (((y2-y1)/(x2-x1))*(width - x1))+y1
    }
}