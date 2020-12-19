package com.gitalive.isat.ui.bbs

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gitalive.isat.BaseFragment
import com.gitalive.isat.R
import kotlinx.android.synthetic.main.fragment_bbs.*

class GalleryFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setSPImpl(R.string.bbs_name)
        setUrl(resources.getString(R.string.bbs_url))
        return inflater.inflate(R.layout.fragment_bbs, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controllers(bbswebView, bbsfab)
    }
}
