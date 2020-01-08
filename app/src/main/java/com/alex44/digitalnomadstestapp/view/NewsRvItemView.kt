package com.alex44.digitalnomadstestapp.view

interface NewsRvItemView {

    fun getPos(): Int?

    fun setTitle(title: String)

    fun setImage(url: String)

    fun setDesc(desc: String)

    fun setDate(date: String)

}