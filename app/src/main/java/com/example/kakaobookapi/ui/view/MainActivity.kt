package com.example.kakaobookapi.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kakaobookapi.R
import com.example.kakaobookapi.data.db.BookSearchDatabase
import com.example.kakaobookapi.data.repository.BookSearchRepositoryImpl
import com.example.kakaobookapi.databinding.ActivityMainBinding
import com.example.kakaobookapi.ui.viewmodel.BookSearchViewModel
import com.example.kakaobookapi.ui.viewmodel.BookSearchViewModelProviderFactory

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    lateinit var bookSearchViewModel: BookSearchViewModel
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupJetpackNavigaiton()

        val database = BookSearchDatabase.getInstance(this)
        val bookSearchRepository = BookSearchRepositoryImpl(database)
        val factory = BookSearchViewModelProviderFactory(bookSearchRepository)
        bookSearchViewModel = ViewModelProvider(this, factory)[BookSearchViewModel::class.java]
    }

    private fun setupJetpackNavigaiton() {
        val host = supportFragmentManager
            .findFragmentById(R.id.booksearch_nav_host_fragment) as NavHostFragment? ?: return
        navController = host.navController
        binding.bottomNavigationBar.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.fragment_search, R.id.fragment_favorite, R.id.fragment_setting
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

}