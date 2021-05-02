package com.gitalive.isat.ui.web

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gitalive.isat.BaseFragment
import com.gitalive.isat.R
import com.gitalive.isat.databinding.FragmentWebBinding

class WebFragment : BaseFragment() {
    private lateinit var binding: FragmentWebBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setSPImpl(R.string.web_name)
        mUrl = resources.getString(R.string.web_url)
        binding = FragmentWebBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controllerConfig(binding.webView)
    }
}
