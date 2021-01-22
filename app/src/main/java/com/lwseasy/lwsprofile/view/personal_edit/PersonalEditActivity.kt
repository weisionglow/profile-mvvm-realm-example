package com.lwseasy.lwsprofile.view.personal_edit

import android.content.Context
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
import com.lwseasy.lwsprofile.databinding.ActivityPersonalEditBinding

class PersonalEditActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context?): Intent {
            return Intent(context, PersonalEditActivity::class.java)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val personalViewModel = ViewModelProviders.of(this).get(PersonalEditViewModel::class.java)
        val binding =
            DataBindingUtil.setContentView<ActivityPersonalEditBinding>(
                this,
                R.layout.activity_personal_edit
            )
                .apply {
                    lifecycleOwner = this@PersonalEditActivity
                    viewModel = personalViewModel
                }

        personalViewModel.getClickModel.observe(this) {
            when (it) {
                ClickModel.Back -> {
                    onBackPressed()
                }
                else -> {

                }
            }
        }

        personalViewModel.personData.observe(this) {
            personalViewModel.setupAllDetail(it)
        }

        personalViewModel.personUpdateSuccess.observe(this) {
            setResult(RESULT_OK)
            onBackPressed()
        }
    }
}