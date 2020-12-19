package com.gitalive.isat.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gitalive.isat.BaseFragment
import com.gitalive.isat.R
import kotlinx.android.synthetic.main.fragment_map.*

class MapFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        androidViewModel = MapAndroidViewModel(requireActivity().application)
        viewModelConfig(R.string.map_name, resources.getString(R.string.map_url))
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapwebView.setInitialScale(100)
        mapwebView.settings.textZoom = 200
        controllers(mapwebView, mapfab)
    }
}
