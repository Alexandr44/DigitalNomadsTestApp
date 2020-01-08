package com.alex44.digitalnomadstestapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.alex44.digitalnomadstestapp.R
import com.alex44.digitalnomadstestapp.common.interfaces.IImageLoader
import com.alex44.digitalnomadstestapp.presenter.NewsPresenter
import com.alex44.digitalnomadstestapp.view.NewsRvItemView
import kotlinx.android.synthetic.main.fragment_news_rv_item.view.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Named

class NewsRVAdapter(val presenter : NewsPresenter) : RecyclerView.Adapter<NewsRVAdapter.ViewHolder>() {

    @Inject
    lateinit var router: Router

    @field: [Inject Named("Glide")]
    lateinit var imageLoader : IImageLoader<ImageView>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_news_rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.position = position
        presenter.bind(holder)
    }

    override fun getItemCount(): Int = presenter.data.size


    inner class ViewHolder(val view : View) : RecyclerView.ViewHolder(view), NewsRvItemView {

        var position : Int? = null

        override fun getPos() = position?:0

        override fun setTitle(title: String) {
            itemView.news_title.text = title
        }

        override fun setImage(url: String) {
            imageLoader.loadInto(url, itemView.news_image, 20)
        }

        override fun setDesc(desc: String) {
            itemView.news_desc.text = desc
        }

        override fun setDate(date: String) {
            itemView.news_date.text = date
        }
    }
}
