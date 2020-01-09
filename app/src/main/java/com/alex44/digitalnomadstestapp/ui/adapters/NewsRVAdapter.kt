package com.alex44.digitalnomadstestapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.alex44.digitalnomadstestapp.R
import com.alex44.digitalnomadstestapp.common.interfaces.IImageLoader
import com.alex44.digitalnomadstestapp.model.enums.NewsType
import com.alex44.digitalnomadstestapp.presenter.NewsPresenter
import com.alex44.digitalnomadstestapp.view.NewsRvItemErrorView
import com.alex44.digitalnomadstestapp.view.NewsRvItemView
import kotlinx.android.synthetic.main.fragment_news_rv_error.view.*
import kotlinx.android.synthetic.main.fragment_news_rv_item.view.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Named

class NewsRVAdapter(val presenter: NewsPresenter) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    @Inject
    lateinit var router: Router

    @field: [Inject Named("Glide")]
    lateinit var imageLoader : IImageLoader<ImageView>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == NewsType.NEWS.ordinal) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_news_rv_item, parent, false)
            ViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_news_rv_error, parent, false)
            ErrorViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (presenter.data[position].newsType == NewsType.NEWS) {
            holder as ViewHolder
            holder.position = position
            presenter.bind(holder)
            holder.itemView.setOnClickListener { presenter.clicked(position) }
        }
        else {
            holder as ErrorViewHolder
            presenter.bind(holder)
            holder.itemView.error_button.setOnClickListener { presenter.retry() }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return presenter.data[position].newsType.ordinal
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

    inner class ErrorViewHolder(val view : View) : RecyclerView.ViewHolder(view), NewsRvItemErrorView {

        override fun setMessage(message: String) {
            itemView.error_message.text = message
        }

    }
}
