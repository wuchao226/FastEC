package com.wuchao.latte.delegates.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wuchao.latte.delegates.IPageLoadListener;
import com.wuchao.latte.delegates.web.chromeclient.WebChromeClientImpl;
import com.wuchao.latte.delegates.web.client.WebViewClientImpl;
import com.wuchao.latte.delegates.web.route.RouteKeys;
import com.wuchao.latte.delegates.web.route.Router;

/**
 * @author: wuchao
 * @date: 2017/11/29 15:50
 * @desciption:
 */

public class WebDelegateImpl extends WebDelegate {

    private IPageLoadListener mIPageLoadListener = null;

    public static WebDelegateImpl create(String url) {
        Bundle bundle = new Bundle();
        bundle.putString(RouteKeys.URL.name(), url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(bundle);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    public void setPageLoadListener(IPageLoadListener listener) {
        mIPageLoadListener = listener;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        if (getUrl() != null) {
            //用原生方式模拟Web跳转并进行页面加载
            Router.getInstance().loadPage(this, getUrl());
        }
    }

    @Override
    protected IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl client = new WebViewClientImpl(this);
        client.setPageLoadListener(mIPageLoadListener);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }
}
