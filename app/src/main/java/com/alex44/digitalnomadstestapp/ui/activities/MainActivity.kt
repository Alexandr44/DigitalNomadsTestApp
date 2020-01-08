package com.alex44.digitalnomadstestapp.ui.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.alex44.digitalnomadstestapp.App
import com.alex44.digitalnomadstestapp.R
import com.alex44.digitalnomadstestapp.common.navigation.Screens
import com.alex44.digitalnomadstestapp.common.ui.BackButtonListener
import com.alex44.digitalnomadstestapp.presenter.MainPresenter
import com.alex44.digitalnomadstestapp.view.MainView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import org.jetbrains.anko.alert
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @Inject
    lateinit var navigatorHolder : NavigatorHolder

    @Inject
    lateinit var router: Router

    var navigator: Navigator = object : SupportAppNavigator(this, R.id.main_frame_layout) {
        override fun setupFragmentTransaction(command: Command?,
                                              currentFragment: Fragment?,
                                              nextFragment: Fragment?,
                                              fragmentTransaction: FragmentTransaction?) {
            super.setupFragmentTransaction(
                command,
                currentFragment,
                nextFragment,
                fragmentTransaction
            )
            fragmentTransaction?.setCustomAnimations(R.anim.slide_in, R.anim.slide_out,
                R.anim.slide_in, R.anim.slide_out)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.instance.appComponent.inject(this)
        presenter.init()
    }

    @ProvidePresenter
    fun createPresenter() : MainPresenter = MainPresenter().apply {
        App.instance.appComponent.inject(this)
    }

    override fun goToNews() {
        router.newRootScreen(Screens.NewsScreen())
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.main_frame_layout)
        if (fragment is BackButtonListener && (fragment as BackButtonListener).backClick()) {
            return
        } else {
            alert {
                title = "Выход"
                message = "Вы действительно хотите выйти?"
                positiveButton("Да") {router.exit()}
                negativeButton("Нет") {it.dismiss()}

            }.show()
        }
    }
}
