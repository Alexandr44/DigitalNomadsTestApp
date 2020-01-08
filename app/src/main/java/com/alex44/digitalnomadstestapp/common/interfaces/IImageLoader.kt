package com.alex44.digitalnomadstestapp.common.interfaces

interface IImageLoader<T> {

    fun loadInto(url : String, container : T, corners : Int)

}