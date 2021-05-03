package com.gitalive.isat.ui.wiki

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gitalive.isat.BaseFragment
import com.gitalive.isat.R
import com.gitalive.isat.databinding.FragmentWikiBinding

class WikiFragment : BaseFragment() {
    private lateinit var binding: FragmentWikiBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setSPImpl(R.string.wiki_name)
        mUrl = resources.getString(R.string.wiki_url)
        binding = FragmentWikiBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controllerConfig(binding.wikiView)
    }
}
