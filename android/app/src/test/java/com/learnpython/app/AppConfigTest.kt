package com.learnpython.app

import org.junit.Assert.assertEquals
import org.junit.Test

class AppConfigTest {
    private val prodUrl = "https://learnpythonprogram.vercel.app"

    @Test
    fun sameHostLoadsInWebView() {
        assertEquals(
            UrlAction.LOAD_IN_WEBVIEW,
            AppConfig.classifyUrl("https://learnpythonprogram.vercel.app/learn/python-home", prodUrl)
        )
    }

    @Test
    fun youtubeEmbedsLoadInWebView() {
        assertEquals(
            UrlAction.LOAD_IN_WEBVIEW,
            AppConfig.classifyUrl("https://www.youtube-nocookie.com/embed/abc123", prodUrl)
        )
    }

    @Test
    fun localEmulatorHostLoadsInWebView() {
        assertEquals(
            UrlAction.LOAD_IN_WEBVIEW,
            AppConfig.classifyUrl("http://10.0.2.2:3000/learn/python-home", prodUrl)
        )
    }

    @Test
    fun externalHostsOpenInCustomTab() {
        assertEquals(
            UrlAction.OPEN_CUSTOM_TAB,
            AppConfig.classifyUrl("https://github.com/octocat", prodUrl)
        )
    }

    @Test
    fun customSchemesOpenExternalIntent() {
        assertEquals(
            UrlAction.OPEN_EXTERNAL_INTENT,
            AppConfig.classifyUrl("mailto:support@example.com", prodUrl)
        )
    }
}
