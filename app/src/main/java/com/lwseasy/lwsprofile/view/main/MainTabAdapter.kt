package com.lwseasy.lwsprofile.view.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.lwseasy.lwsprofile.view.experience.ExperienceFragment
import com.lwseasy.lwsprofile.view.personal.PersonalFragment

class MainTabAdapter(
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(
    fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {


    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> PersonalFragment.newInstance()
            1 -> ExperienceFragment.newInstance()
            else -> PersonalFragment.newInstance()
        }
    }

    override fun getCount(): Int {
        return 1
    }
}