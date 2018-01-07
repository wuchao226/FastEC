package com.wuchao.latte.delegates.web.client;

import android.graphics.Bitmap;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wuchao.latte.app.ConfigKeys;
import com.wuchao.latte.app.Latte;
import com.wuchao.latte.delegates.IPageLoadListener;
import com.wuchao.latte.delegates.web.WebDelegate;
import com.wuchao.latte.delegates.web.route.Router;
import com.wuchao.latte.ui.loader.LatteLoader;
import com.wuchao.latte.util.log.LatteLogger;
import com.wuchao.latte.util.storage.LattePreference;

/**
 * @author: wuchao
 * @date: 2017/11/29 15:59
 * @desciption:
 */

public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;
    private IPageLoadListener mIPageLoadListener = null;

    public void setPageLoadListener(IPageLoadListener listener) {
        this.mIPageLoadListener = listener;
    }

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        LatteLogger.d("shouldOverrideUrlLoading", url);
        return Router.getInstance().handleWebView(DELEGATE, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadStart();
        }
        LatteLoader.showLoading(view.getContext());
    }

    //获取浏览器Cookie
    private void syncCookie() {
        final CookieManager manager = CookieManager.getInstance();
        /*
            注意，这里的Cookie和API请求的Cookie是不一样的，这个在网页不可见
         */
        final String webHost = Latte.getConfiguration(ConfigKeys.WEB_HOST);
        if (webHost != null) {
            if (manager.hasCookies()) {
                final String cookieStr = manager.getCookie(webHost);
                if (cookieStr != null && !cookieStr.equals("")) {
                    LatteLogger.d("WebViewClientImpl", "cookie---》" + cookieStr);
                    LattePreference.addCustomAppProfile("cookie", cookieStr);
                }
            }
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        syncCookie();
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadEnd();
        }
        LatteLoader.stopLoading();
    }
}
