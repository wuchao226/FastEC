package com.wuchao.fastec.example.event;

import android.annotation.SuppressLint;
import android.webkit.WebView;
import android.widget.Toast;

import com.wuchao.latte.delegates.web.event.Event;

/**
 * @author: wuchao
 * @date: 2017/12/23 18:13
 * @desciption:
 */

public class TestEvent extends Event {
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(), getAction(), Toast.LENGTH_LONG).show();
        if (getAction().equals("test")) {
            final WebView webView = getWebView();
            webView.post(new Runnable() {
                @SuppressLint("NewApi")
                @Override
                public void run() {
                    webView.evaluateJavascript("nativeCall()", null);
                }
            });
        }
        return null;
    }
}
