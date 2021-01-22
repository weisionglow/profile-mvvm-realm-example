package com.lwseasy.lwsprofile.view.experience

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.lwseasy.lwsprofile.R
import com.lwseasy.lwsprofile.databinding.FragmentExperienceBinding

class ExperienceFragment : Fragment() {

    private var binding: FragmentExperienceBinding? = null

    companion object {
        fun newInstance(): ExperienceFragment {
            return ExperienceFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = DataBindingUtil.inflate<FragmentExperienceBinding>(
            inflater, R.layout.fragment_experience, container, false
        )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.experienceRecycle?.apply {
            adapter = ExperienceAdapter(context)
            layoutManager = LinearLayoutManager(context)
        }
    }
}