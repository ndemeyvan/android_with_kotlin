package com.bee_studio.learn_recycler_view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.bee_studio.learn_recycler_view.Entities.ShoppingItem
import com.bee_studio.learn_recycler_view.ShareViewModel.ShareViewModel


class SecondFragment : Fragment() {
    lateinit var add_item_btn: Button
    lateinit var close_dialog: Button
    lateinit var ed_item_amount: EditText
    lateinit var ed_item_name: EditText
    /*Ce qu'il faut retenir ici
    c'est que pour utiliser un viewModel Globalement
    on utilise activityViewModels() */
    private val shareViewModel: ShareViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vue = inflater.inflate(R.layout.fragment_second, container, false)
        add_item_btn = vue.findViewById(R.id.add_item_btn)
        ed_item_amount = vue.findViewById(R.id.ed_item_amount)
        ed_item_name = vue.findViewById(R.id.ed_item_name)
        close_dialog = vue.findViewById(R.id.close_dialog)
        add_item_btn.setOnClickListener {
            val amount = ed_item_amount.text.toString()
            val name = ed_item_name.text.toString()
            if(amount.isEmpty()||name.isEmpty()){
                Toast.makeText(context,"Fill the name and amount", Toast.LENGTH_LONG).show()
            }else{
                val item = ShoppingItem(name,amount.toInt())
                shareViewModel.insertShoppingItem(item)
            }
            close_dialog.setOnClickListener {
            }

        }
        return vue
    }

}