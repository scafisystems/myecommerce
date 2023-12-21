package com.scafisystems.myecommerce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.scafisystems.myecommerce.MyApplication
import com.scafisystems.myecommerce.R
import com.scafisystems.myecommerce.databinding.ActivityMainBinding
import com.scafisystems.myecommerce.ui.view.fragment.HomeFragment
import com.scafisystems.myecommerce.ui.viewmodel.OrderViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, HomeFragment())
                .commit()
        }

    }


}