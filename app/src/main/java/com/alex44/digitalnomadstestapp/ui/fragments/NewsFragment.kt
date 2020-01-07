package com.alex44.digitalnomadstestapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.alex44.digitalnomadstestapp.App
import com.alex44.digitalnomadstestapp.R
import com.alex44.digitalnomadstestapp.presenter.NewsPresenter
import com.alex44.digitalnomadstestapp.view.NewsView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import io.reactivex.android.schedulers.AndroidSchedulers

class NewsFragment : MvpAppCompatFragment(), NewsView {

    @InjectPresenter
    lateinit var presenter: NewsPresenter

    companion object {
        fun newInstance() = NewsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    @ProvidePresenter
    fun createPresenter(): NewsPresenter {
        val newsPresenter = NewsPresenter(AndroidSchedulers.mainThread())
        App.instance.appComponent.inject(newsPresenter)
        return newsPresenter
    }

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun initRV() {

    }

    override fun updateRV() {

    }

}
