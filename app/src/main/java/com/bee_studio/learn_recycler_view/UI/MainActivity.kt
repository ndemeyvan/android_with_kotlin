package com.bee_studio.learn_recycler_view.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.bee_studio.learn_recycler_view.R
import com.bee_studio.learn_recycler_view.UI.data.api.db.ArticleDataBase
import com.bee_studio.learn_recycler_view.UI.repo.NewsRepository
import com.bee_studio.learn_recycler_view.UI.viewModel.NewsVMFactory
import com.bee_studio.learn_recycler_view.UI.viewModel.NewsViewModel
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    lateinit var  bottomNavigationView:BottomNavigationView;
    lateinit var viewModel:NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //REPO,VM,Factory creations
        val repo = NewsRepository(ArticleDataBase(this))
        val factory = NewsVMFactory(repo)
        viewModel =ViewModelProvider(this,factory).get(NewsViewModel::class.java)
        //
        bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment?
        if (navHostFragment != null) {
            val navController = navHostFragment.navController
                bottomNavigationView.setupWithNavController(navController)
            // Setup NavigationUI here
        }
    }


}