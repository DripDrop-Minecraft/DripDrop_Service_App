package com.gitalive.myapplication.ui.bing

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.gitalive.myapplication.R
import kotlinx.android.synthetic.main.fragment_bing.*


class BingFragment : Fragment() {

    private lateinit var bingViewModel: BingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bingViewModel = BingViewModel(requireActivity().application)
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_bing, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_refresh -> bingView.loadUrl(bingViewModel.getBing().value)
            R.id.action_forward ->
                if (bingView.canGoForward()) {
                    bingView.goForward()
                }
            R.id.action_back ->
                if (bingView.canGoBack()) {
                    bingView.goBack()
                }
            R.id.action_home -> bingView.loadUrl(bingViewModel.getHomepage())

        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bingView.settings.javaScriptEnabled = true
        bingView.settings.cacheMode = WebSettings.LOAD_DEFAULT
        bingView.settings.setSupportZoom(true)
        bingView.settings.loadsImagesAutomatically = true
        bingView.settings.setSupportMultipleWindows(true)

        bingView.setDownloadListener { url, userAgent, contentDisposition, mimetype, contentLength ->
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        bingView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (!url!!.startsWith("http") && !url.startsWith("https")) {
                    val shareIntent = Intent()
                    shareIntent.action = Intent.ACTION_VIEW
                    shareIntent.data = Uri.parse(url)
                    if (activity?.packageManager?.let { shareIntent.resolveActivity(it) } == null) {
                        Toast.makeText(context, "未安装相关应用", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "正在跳转······", Toast.LENGTH_SHORT).show()
                        startActivity(shareIntent)
                    }
                    return true
                } else {
                    bingViewModel.setBing(url)
                    view?.loadUrl(bingViewModel.getBing().value.toString())
                }
                return false
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                if (url != null && (url.startsWith("http") || url.startsWith("https"))) {
                    bingViewModel.setBing(url)
                    Toast.makeText(context, "页面加载中，请稍候", Toast.LENGTH_SHORT).show()
                }
            }
        }
        bingView.loadUrl(bingViewModel.getBing().value.toString())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (bingView.canGoBack()) {
                    bingView.goBack()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

}
