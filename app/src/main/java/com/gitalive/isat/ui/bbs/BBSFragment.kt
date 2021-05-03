package com.gitalive.isat.ui.bbs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gitalive.isat.BaseFragment
import com.gitalive.isat.R
import com.gitalive.isat.databinding.FragmentBbsBinding

class BBSFragment : BaseFragment() {
    private lateinit var binding: FragmentBbsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setSPImpl(R.string.bbs_name)
        binding = FragmentBbsBinding.inflate(inflater)
        mUrl = resources.getString(R.string.bbs_url)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controllerConfig(binding.bbsView)
    }
}
