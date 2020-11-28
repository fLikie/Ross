package com.example.rosseti

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.example.rosseti.api.LoginApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity(): AppCompatActivity() {

    companion object {
        private const val REQUEST_CODE_WRITE = 10
    }

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
        if (checkWritePermission()) {
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_CODE_WRITE);
        }
    }

    fun checkWritePermission(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_WRITE && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        }
    }
}