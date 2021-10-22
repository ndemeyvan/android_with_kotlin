package com.bee_studio.learn_recycler_view.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bee_studio.learn_recycler_view.Entities.ShoppingItem
import com.bee_studio.learn_recycler_view.R
import com.bee_studio.learn_recycler_view.ShareViewModel.ShareViewModel

class ShoppingAdapter(var shoppingListItem: List<ShoppingItem>, var viewModel: ShareViewModel) :
    RecyclerView.Adapter<ShoppingAdapter.ShoppingViewHolder>() {

    //Cette classe se charger de lier les vue aux object
    inner class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item_name: TextView
        val item_amount: TextView
        val addButton: ImageView
        val removeButton: ImageView
        val deleteButton: ImageView
        init {
            // Define click listener for the ViewHolder's View.
            item_name = itemView.findViewById(R.id.item_name)
            item_amount = itemView.findViewById(R.id.item_amount)
            addButton = itemView.findViewById(R.id.im_add)
            removeButton = itemView.findViewById(R.id.im_minus)
            deleteButton = itemView.findViewById(R.id.im_delete)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        var currenItem = shoppingListItem[position]
        holder.item_amount.text = currenItem.amount.toString()
        holder.item_name.text = currenItem.name.toString()
        //Add Shopping item Element to our Room DataBase
        holder.addButton.setOnClickListener {
            if (currenItem.amount > 0) {
                currenItem.amount--
                viewModel.insertShoppingItem(currenItem)
            }
        }
        //Remove item from the Room Db
        holder.removeButton.setOnClickListener {
            if (currenItem.amount > 0) {
                currenItem.amount--
                viewModel.insertShoppingItem(currenItem)
            }
            if (currenItem.amount > 0) {
                currenItem.amount--
                viewModel.insertShoppingItem(currenItem)
            }
        }
        //Delete element from the Room Db
        holder.deleteButton.setOnClickListener {
            viewModel.deleteShoppingItem(currenItem)
        }
    }

    override fun getItemCount() = shoppingListItem.size


}