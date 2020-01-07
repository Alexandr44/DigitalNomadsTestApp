package com.alex44.digitalnomadstestapp.common.navigation

import androidx.fragment.app.Fragment
import com.alex44.digitalnomadstestapp.ui.fragments.NewsFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    object NewsScreen : SupportAppScreen() {
        override fun getFragment(): Fragment = NewsFragment.newInstance()
    }

}