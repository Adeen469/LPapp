package com.learnpython.app

import java.net.URI
import java.util.Locale

enum class UrlAction {
    LOAD_IN_WEBVIEW,
    OPEN_CUSTOM_TAB,
    OPEN_EXTERNAL_INTENT,
}

object AppConfig {
    private val embeddedVideoHosts = setOf(
        "youtube.com",
        "www.youtube.com",
        "m.youtube.com",
        "youtube-nocookie.com",
        "www.youtube-nocookie.com",
    )

    private val localDevHosts = setOf(
        "localhost",
        "127.0.0.1",
        "10.0.2.2",
    )

    fun extractHost(url: String?): String? {
        if (url.isNullOrBlank()) return null
        return try {
            URI(url).host?.lowercase(Locale.ROOT)
        } catch (_: Exception) {
            null
        }
    }

    fun classifyUrl(url: String?, appBaseUrl: String): UrlAction {
        if (url.isNullOrBlank()) return UrlAction.LOAD_IN_WEBVIEW

        val lower = url.lowercase(Locale.ROOT)
        if (lower.startsWith("about:") || lower.startsWith("blob:") || lower.startsWith("data:")) {
            return UrlAction.LOAD_IN_WEBVIEW
        }

        if (!lower.startsWith("http://") && !lower.startsWith("https://")) {
            return UrlAction.OPEN_EXTERNAL_INTENT
        }

        val targetHost = extractHost(url) ?: return UrlAction.OPEN_CUSTOM_TAB
        val appHost = extractHost(appBaseUrl)

        if (appHost != null && (targetHost == appHost || targetHost.endsWith(".$appHost"))) {
            return UrlAction.LOAD_IN_WEBVIEW
        }

        if (targetHost in localDevHosts) {
            return UrlAction.LOAD_IN_WEBVIEW
        }

        if (targetHost in embeddedVideoHosts) {
            return UrlAction.LOAD_IN_WEBVIEW
        }

        return UrlAction.OPEN_CUSTOM_TAB
    }
}
