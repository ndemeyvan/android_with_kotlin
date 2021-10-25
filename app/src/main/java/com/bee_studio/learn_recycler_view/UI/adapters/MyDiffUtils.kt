package com.bee_studio.learn_recycler_view.UI.adapters

import androidx.recyclerview.widget.DiffUtil
import com.bee_studio.learn_recycler_view.UI.models.Article


class MyDiffUtils(private val oldList: List<Article>, private val newList: List<Article>) :
    DiffUtil.Callback() {


    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size

    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return  oldList[oldItemPosition].url==newList[newItemPosition].url
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return  oldList[oldItemPosition]==newList[newItemPosition]
    }


}