package com.gitalive.myapplication.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.webkit.WebSettings
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.gitalive.myapplication.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = HomeViewModel(requireActivity().application)
        setHasOptionsMenu(true)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.notice, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val context = requireContext().applicationContext
        when (item.itemId) {
            R.id.action_version -> Toast.makeText(context, homeViewModel.update, Toast.LENGTH_LONG)
                .show()
            R.id.action_refresh -> mapView.loadUrl(homeViewModel.getMap())
            R.id.action_feedback -> Toast.makeText(
                context,
                homeViewModel.feedback,
                Toast.LENGTH_LONG
            ).show()
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mapView.settings.javaScriptEnabled = true
        mapView.setInitialScale(100)
        mapView.settings.cacheMode = WebSettings.LOAD_DEFAULT
        mapView.settings.textZoom = 200
        mapView.settings.loadsImagesAutomatically = true
        mapView.loadUrl(homeViewModel.getMap())
    }
}
