package com.alex44.digitalnomadstestapp.common.navigation

import androidx.fragment.app.Fragment
import com.alex44.digitalnomadstestapp.ui.fragments.NewsFragment
import com.alex44.digitalnomadstestapp.ui.fragments.UrlFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class NewsScreen : SupportAppScreen() {
        override fun getFragment(): Fragment = NewsFragment.newInstance()
    }

    class UrlScreen(private val url : String) : SupportAppScreen() {
        override fun getFragment(): Fragment = UrlFragment.newInstance(url)
    }

}