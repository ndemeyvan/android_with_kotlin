package com.bee_studio.learn_recycler_view.UI.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bee_studio.learn_recycler_view.R
import com.bee_studio.learn_recycler_view.UI.models.Article
import com.bumptech.glide.Glide

class ArticlesAdapters() : RecyclerView.Adapter<ArticlesAdapters.ArticleViewHolder>() {


     var oldArticleList = emptyList<Article>()

    fun setData(newArticleList:List<Article>){
        val diffUtil = MyDiffUtils(oldArticleList,newArticleList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        oldArticleList = newArticleList
        print("#################### T OLD ARTICLE : ${oldArticleList.size} \n")
        print("#################### T NEW ARTICLE : ${newArticleList.size}")
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val articleItem : ConstraintLayout = itemView.findViewById(R.id.article_item)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        val tvSource: TextView = itemView.findViewById(R.id.tvSource)
        val ivArticleImage: ImageView = itemView.findViewById(R.id.ivArticleImage)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticlesAdapters.ArticleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article_preview, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticlesAdapters.ArticleViewHolder, position: Int) {
        var currenItem = oldArticleList[position]
        Glide.with(holder.itemView.context).load(currenItem.urlToImage).into(holder.ivArticleImage)
        holder.apply {
            tvTitle.text = currenItem.title
            tvDescription.text = currenItem.description
            tvSource.text = currenItem.source.name
            articleItem.setOnClickListener  {
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

    override fun getItemCount() = oldArticleList.size


}