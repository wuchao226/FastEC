package com.wuchao.latte.delegates.web.client;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wuchao.latte.delegates.web.WebDelegate;
import com.wuchao.latte.delegates.web.route.Router;
import com.wuchao.latte.util.log.LatteLogger;

/**
 * @author: wuchao
 * @date: 2017/11/29 15:59
 * @desciption:
 */

public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        LatteLogger.d("shouldOverrideUrlLoading", url);
        return Router.getInstance().handleWebView(DELEGATE, url);
    }
}
