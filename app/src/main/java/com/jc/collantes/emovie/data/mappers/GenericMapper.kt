package com.jc.collantes.emovie.data.mappers

interface GenericMapper <I,O> {

    fun map(input: I):O

}