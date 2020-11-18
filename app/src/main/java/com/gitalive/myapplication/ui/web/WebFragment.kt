package com.gitalive.myapplication.ui.web

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.*
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.gitalive.myapplication.R
import com.gitalive.myapplication.databinding.WebFragmentBinding

class WebFragment : Fragment() {

    private lateinit var webViewModel: WebViewModel
    private lateinit var binding: WebFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WebFragmentBinding.inflate(inflater)
        webViewModel = WebViewModel(requireActivity().application)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_refresh -> binding.webView.loadUrl(webViewModel.getWeb().value)
            R.id.action_back ->
                if (binding.webView.canGoBack()) {
                    binding.webView.goBack()
                }
            R.id.action_home -> binding.webView.loadUrl(webViewModel.url)
            R.id.action_forward ->
                if (binding.webView.canGoForward()) {
                    binding.webView.goForward()
                }
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.cacheMode = WebSettings.LOAD_DEFAULT
        binding.webView.setInitialScale(100)
        binding.webView.settings.textZoom = 130
        binding.webView.settings.setSupportZoom(true)
        binding.webView.settings.loadsImagesAutomatically = true
        binding.webView.settings.setSupportMultipleWindows(true)

        binding.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(webViewModel.getWeb().value.toString())
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                webViewModel.setWeb(url)
                Toast.makeText(context, "页面加载中，请稍候", Toast.LENGTH_SHORT).show()
            }
        }
        binding.webView.loadUrl(webViewModel.getWeb().value.toString())
    }

}
