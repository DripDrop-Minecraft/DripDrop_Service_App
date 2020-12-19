package com.gitalive.isat.ui.web

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gitalive.isat.BaseFragment
import com.gitalive.isat.R
import kotlinx.android.synthetic.main.fragment_web.*

class WebFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        androidViewModel = WebAndroidViewModel(requireActivity().application)
        viewModelConfig(R.string.web_name, resources.getString(R.string.web_url))
        return inflater.inflate(R.layout.fragment_web, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        controllers(webwebView, webfab)
    }
}
