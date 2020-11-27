package com.alisdnn.presentation.ui

import android.os.Bundle
import com.alisdnn.presentation.R
import com.alisdnn.presentation.ui.base.BaseActivity


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun getNavControllerId(): Int = R.id.activityMainHomeHostFragment
}