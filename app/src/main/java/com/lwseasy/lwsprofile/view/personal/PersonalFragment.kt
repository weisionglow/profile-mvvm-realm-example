package com.lwseasy.lwsprofile.view.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.lwseasy.lwsprofile.R
import com.lwseasy.lwsprofile.databinding.FragmentPersonalBinding
import com.lwseasy.lwsprofile.view.main.MainViewModel

class PersonalFragment : Fragment() {

    private var binding: FragmentPersonalBinding? = null
    private var mainViewModel: MainViewModel? = null

    companion object {
        fun newInstance(): PersonalFragment {
            return PersonalFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        mainViewModel = activity?.run {
            ViewModelProviders.of(this).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        binding = DataBindingUtil.inflate<FragmentPersonalBinding>(
            inflater, R.layout.fragment_personal, container, false
        ).apply {
            this.lifecycleOwner = this@PersonalFragment
            this.viewModel = mainViewModel
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding?.layoutPersonal?.lifecycleOwner = this
//        binding?.layoutPersonal?.viewModel = mainViewModel
//
//        binding?.layoutAbout?.lifecycleOwner = this
//        binding?.layoutAbout?.viewModel = mainViewModel
    }
}