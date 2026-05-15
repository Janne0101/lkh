package com.wohnungssuche.app.ui.portals

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.wohnungssuche.app.R
import com.wohnungssuche.app.data.model.Apartment
import com.wohnungssuche.app.data.model.Portal
import com.wohnungssuche.app.databinding.FragmentPortalWebviewBinding
import com.wohnungssuche.app.viewmodel.ApartmentViewModel

class PortalWebViewFragment : Fragment() {

    private var _binding: FragmentPortalWebviewBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ApartmentViewModel by activityViewModels()

    private lateinit var portal: Portal

    companion object {
        private const val ARG_PORTAL = "arg_portal"

        fun newInstance(portal: Portal) = PortalWebViewFragment().apply {
            arguments = Bundle().apply { putString(ARG_PORTAL, portal.name) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        portal = Portal.valueOf(requireArguments().getString(ARG_PORTAL)!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPortalWebviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupWebView()
        setupToolbar()
        loadPortal()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        binding.webView.apply {
            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                loadWithOverviewMode = true
                useWideViewPort = true
                setSupportZoom(true)
                builtInZoomControls = true
                displayZoomControls = false
                userAgentString = "Mozilla/5.0 (Linux; Android 12; Pixel 6) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) " +
                        "Chrome/120.0.0.0 Mobile Safari/537.36"
            }

            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.urlBar.setText(url)
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    binding.progressBar.visibility = View.GONE
                    binding.urlBar.setText(url)
                    binding.swipeRefresh.isRefreshing = false
                }

                override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                    val url = request?.url?.toString() ?: return false
                    return if (url.startsWith("http") || url.startsWith("https")) {
                        false // Load in WebView
                    } else {
                        // Open external apps (mailto, tel, etc.)
                        try {
                            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                        } catch (e: Exception) { /* ignore */ }
                        true
                    }
                }
            }

            webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    binding.progressBar.progress = newProgress
                }
            }
        }

        binding.swipeRefresh.setOnRefreshListener {
            binding.webView.reload()
        }
    }

    private fun setupToolbar() {
        binding.btnBack.setOnClickListener {
            if (binding.webView.canGoBack()) binding.webView.goBack()
        }

        binding.btnForward.setOnClickListener {
            if (binding.webView.canGoForward()) binding.webView.goForward()
        }

        binding.btnRefresh.setOnClickListener {
            binding.webView.reload()
        }

        binding.btnOpenExternal.setOnClickListener {
            val url = binding.webView.url ?: portal.baseUrl
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }

        binding.btnSaveAsFavorite.setOnClickListener {
            saveCurrentPageAsFavorite()
        }
    }

    private fun loadPortal() {
        binding.webView.loadUrl(portal.baseUrl)
    }

    private fun saveCurrentPageAsFavorite() {
        val url = binding.webView.url ?: ""
        val title = binding.webView.title ?: ""

        val apartment = Apartment(
            title = if (title.isNotBlank()) title else "Wohnung von ${portal.displayName}",
            address = "",
            rent = 0.0,
            totalRent = 0.0,
            sizeM2 = 0.0,
            rooms = 0.0,
            url = url,
            portalName = portal.displayName
        )

        viewModel.insert(apartment)

        Snackbar.make(binding.root, "Gespeichert! Details jetzt in Favoriten bearbeiten.", Snackbar.LENGTH_LONG)
            .setAction("Zu Favoriten") {
                findNavController().navigate(R.id.favoritesFragment)
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
