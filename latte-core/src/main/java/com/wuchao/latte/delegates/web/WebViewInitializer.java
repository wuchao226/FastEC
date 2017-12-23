package com.wuchao.latte.delegates.web;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * @author: wuchao
 * @date: 2017/11/29 19:11
 * @desciption:
 */

public class WebViewInitializer {

    @SuppressLint({"NewApi", "SetJavaScriptEnabled"})
    public WebView createWebView(WebView webView) {
        WebView.setWebContentsDebuggingEnabled(true);

        //cookie
        final CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.setAcceptThirdPartyCookies(webView, true);
        }
        CookieManager.setAcceptFileSchemeCookies(true);

        //不能横向滚动
        webView.setHorizontalScrollBarEnabled(false);
        //不能纵向滚动
        webView.setVerticalScrollBarEnabled(false);
        //允许截图
        webView.setDrawingCacheEnabled(true);
        //屏蔽长按事件
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        //初始化WebSettings
        final WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        final String ua = settings.getUserAgentString();
        settings.setUserAgentString(ua + "Latte");
        //隐藏缩放控件
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        //禁止缩放
        settings.setSupportZoom(false);
        //文件权限
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setAllowContentAccess(true);
        //缓存相关
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        return webView;
    }
}
