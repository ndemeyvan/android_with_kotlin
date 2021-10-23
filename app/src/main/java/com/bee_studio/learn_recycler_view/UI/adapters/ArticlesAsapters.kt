package com.bee_studio.learn_recycler_view.UI.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bee_studio.learn_recycler_view.R
import com.bee_studio.learn_recycler_view.UI.models.Article
import com.bumptech.glide.Glide

class ArticlesAsapters() : RecyclerView.Adapter<ArticlesAsapters.ArticleViewHolder>() {


    ///////Diff Utils Implementation
    private val diffCallBack = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            //Ici il faut compatrer deux element dans l'object qui sont unique
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    //Ceci est charger de comparer nos deux listes
    val differ = AsyncListDiffer(this, diffCallBack)


    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView
        val tvDescription: TextView
        val tvSource: TextView
        val tvPublishedAt: TextView
        val ivArticleImage: ImageView

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvDescription = itemView.findViewById(R.id.tvDescription)
            tvSource = itemView.findViewById(R.id.tvSource)
            tvPublishedAt = itemView.findViewById(R.id.tvPublishedAt)
            ivArticleImage = itemView.findViewById(R.id.ivArticleImage)
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticlesAsapters.ArticleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article_preview, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticlesAsapters.ArticleViewHolder, position: Int) {
        var currenItem = differ.currentList[position]
        Glide.with(holder.itemView.context).load(currenItem.urlToImage).into(holder.ivArticleImage)
        holder.apply {
            tvTitle.text = currenItem.title
            tvDescription.text = currenItem.description
            tvSource.text = currenItem.source.name
            tvPublishedAt.text = currenItem.publishedAt
            setOnItemClickListener {
                onItemClickListener?.let {
                    it(currenItem)
                }
            }
        }
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener ( listener: (Article) -> Unit) {
        onItemClickListener=listener
    }

    override fun getItemCount() = differ.currentList.size


}