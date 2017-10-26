package com.wuchao.fastec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.wuchao.latte.app.Latte;
import com.wuchao.latte.delegates.LatteDelegate;
import com.wuchao.latte.net.RestClient;
import com.wuchao.latte.net.callback.IError;
import com.wuchao.latte.net.callback.IFailure;
import com.wuchao.latte.net.callback.ISuccess;

/**
 * @author: wuchao
 * @date: 2017/10/22 23:15
 * @desciption:
 */

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    private void testRestClient() {
        RestClient.builder()
                .url("http://news.baidu.com/")
                .loader(getActivity())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //Toast.makeText(Latte.getApplication(), response, Toast.LENGTH_SHORT).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(Latte.getApplication(), "error", Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(Latte.getApplication(), "failure", Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .get();
    }
}
