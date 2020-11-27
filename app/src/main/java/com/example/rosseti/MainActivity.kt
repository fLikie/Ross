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
                    R.id.acceptedFragment -> navController.navigate(R.id.go_to_accepted)
                    R.id.formFragment -> navController.navigate(R.id.go_to_form)
                    R.id.forumFragment -> navController.navigate(R.id.go_to_forum)
                    R.id.documentationsFragment -> navController.navigate(R.id.go_to_docs)
                    R.id.reestrFragment -> navController.navigate(R.id.go_to_reestr)
                }
            }
            true
        }
    }
}