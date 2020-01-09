package com.alex44.digitalnomadstestapp.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.alex44.digitalnomadstestapp.App
import com.alex44.digitalnomadstestapp.R
import com.alex44.digitalnomadstestapp.common.navigation.Screens
import com.alex44.digitalnomadstestapp.common.ui.BackButtonListener
import com.alex44.digitalnomadstestapp.presenter.UrlPresenter
import com.alex44.digitalnomadstestapp.view.UrlView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_url.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class UrlFragment : MvpAppCompatFragment(), UrlView, BackButtonListener {

    @InjectPresenter
    lateinit var presenter: UrlPresenter

    @Inject
    lateinit var router: Router

    companion object {
        fun newInstance(url : String) : UrlFragment {
            val arguments = Bundle()
            arguments.putString("url", url)
            val fragment = UrlFragment()
            fragment.arguments = arguments
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.instance.appComponent.inject(this)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_url, container, false)
    }

    @ProvidePresenter
    fun createPresenter() : UrlPresenter {
        val url = arguments?.getString("url")
        return UrlPresenter(url?:"")
    }

    override fun openUrl(url: String) {
        url_webview.settings.javaScriptEnabled = true
        url_webview.loadUrl(url)
    }

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun backClick(): Boolean {
        presenter.backClicked()
        return true
    }

    override fun backToNews() {
        router.newRootScreen(Screens.NewsScreen())
    }

}
