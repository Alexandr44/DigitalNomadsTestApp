package com.alex44.digitalnomadstestapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alex44.digitalnomadstestapp.App
import com.alex44.digitalnomadstestapp.R
import com.alex44.digitalnomadstestapp.common.navigation.Screens
import com.alex44.digitalnomadstestapp.presenter.NewsPresenter
import com.alex44.digitalnomadstestapp.ui.adapters.NewsRVAdapter
import com.alex44.digitalnomadstestapp.view.NewsView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_news.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class NewsFragment : MvpAppCompatFragment(), NewsView {

    @InjectPresenter
    lateinit var presenter: NewsPresenter

    @Inject
    lateinit var router: Router

    var adapter : NewsRVAdapter? = null

    companion object {
        fun newInstance() = NewsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.instance.appComponent.inject(this)
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
        adapter = NewsRVAdapter(presenter)
        App.instance.appComponent.inject(adapter as NewsRVAdapter)
        news_rv.layoutManager = LinearLayoutManager(context)
        news_rv.adapter = adapter
        news_rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastPos = linearLayoutManager.findLastVisibleItemPosition()
                val count = recyclerView.adapter?.itemCount
                if (lastPos == count?.minus(1)) {
                    presenter.loadMore()
                }
            }
        })
    }

    override fun updateRV() {
        adapter?.notifyDataSetChanged()
    }

    override fun goToUrl(newsUrl: String) {
        router.newRootScreen(Screens.UrlScreen(newsUrl))
    }

}
