package com.gitalive.isat.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gitalive.isat.BaseFragment
import com.gitalive.isat.R
import com.gitalive.isat.databinding.FragmentMapBinding

class MapFragment : BaseFragment() {
    private lateinit var binding: FragmentMapBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setSPImpl(R.string.map_name)
        mUrl = resources.getString(R.string.map_url)
        binding = FragmentMapBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mapView.webView.apply {
            setInitialScale(100)
            settings.textZoom = 200
        }
        controllerConfig(binding.mapView)
    }
}
