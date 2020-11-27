package com.example.rosseti

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        bottom_nav.setOnNavigationItemSelectedListener {
            if (bottom_nav.selectedItemId != it.itemId) {
                when (it.itemId) {
                    R.id.menu_block_one -> navController.navigate(R.id.go_to_forum)
                    R.id.menu_block_two -> navController.navigate(R.id.go_to_form)
                    R.id.menu_block_three -> navController.navigate(R.id.go_to_reestr)
                    R.id.menu_block_four -> navController.navigate(R.id.go_to_accepted)
                    R.id.menu_block_five -> navController.navigate(R.id.go_to_docs)
                }
            }
            true
        }
    }
}