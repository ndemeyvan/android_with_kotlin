package com.bee_studio.learn_recycler_view.BasicRecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bee_studio.learn_recycler_view.R

class RecyclerViewAdapter(
    private var language: List<String>,
) : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    //Ceci sert a Bind les vue des elements
    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemTextView: TextView = itemView.findViewById(R.id.itemTextView)
    }

    //Ici on lie le template des elements de vue
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return RecyclerViewHolder(itemView)
    }

    //Ici on passe les valeurs a la vue
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item = language[position]
        holder.itemTextView.text = item
    }

    //Recuperer la quantite d'element dans la liste
    override fun getItemCount(): Int {
        return language.size
    }

}