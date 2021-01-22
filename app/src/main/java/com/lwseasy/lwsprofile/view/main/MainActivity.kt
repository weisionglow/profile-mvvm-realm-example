package com.lwseasy.lwsprofile.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.tabs.TabLayout
import com.lwseasy.easycustomtab.EasyCustomTabHelper
import com.lwseasy.easycustomtab.EasyCustomTabType
import com.lwseasy.easycustomtab.EasyTabModel
import com.lwseasy.lwsprofile.R
import com.lwseasy.lwsprofile.constant.ClickModel
import com.lwseasy.lwsprofile.databinding.ActivityMainBinding
import com.lwseasy.lwsprofile.view.personal_edit.PersonalEditActivity

class MainActivity : AppCompatActivity() {

    private var mainViewModel: MainViewModel? = null

    companion object {
        private const val REQUEST_CODE = 1000
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            mainViewModel?.loadPersonData()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
                .apply {
                    lifecycleOwner = this@MainActivity
                    viewModel = mainViewModel
                }

        binding.mainViewPager.apply {
            adapter = MainTabAdapter(supportFragmentManager)
        }
        binding.mainTab.setupWithViewPager(binding.mainViewPager)
        setupCustomTab(binding.mainTab)

        initViewModel()
    }

    private fun initViewModel() {
        mainViewModel?.getClickModel?.observe(this) {
            when (it) {
                ClickModel.Edit -> {
                    startActivityForResult(PersonalEditActivity.getIntent(this), REQUEST_CODE)
                }
                else -> {

                }
            }
        }
    }

    private fun setupCustomTab(mainTab: TabLayout) {
        val easyCustomTabHelper = EasyCustomTabHelper(
            this,
            mainTab,
            arrayListOf(EasyTabModel("Personal"), EasyTabModel("Experience")),
            EasyCustomTabType.ShowTextOnly
        )

        easyCustomTabHelper.setFontSize(14.0f)
        easyCustomTabHelper.setColor(R.color.white, R.color.white)
        easyCustomTabHelper.build()
    }
}