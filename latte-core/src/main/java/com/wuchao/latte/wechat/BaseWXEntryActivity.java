package com.wuchao.latte.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.wuchao.latte.net.rx.RxRestClient;
import com.wuchao.latte.util.log.LatteLogger;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: wuchao
 * @date: 2017/12/28 22:54
 * @desciption:
 */

public abstract class BaseWXEntryActivity extends BaseWXActivity {

    //用户登录成功后回调
    protected abstract void onSignInSuccess(String userInfo);

    //微信发送请求到第三方后回调
    @Override
    public void onReq(BaseReq baseReq) {

    }

    //第三方应用发送请求到微信后回调
    @Override
    public void onResp(BaseResp baseResp) {
        final String code = ((SendAuth.Resp) baseResp).code;
        final StringBuilder authUrl = new StringBuilder();
        authUrl
                .append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=")
                .append(LatteWeChat.APP_ID)
                .append("&secret=")
                .append(LatteWeChat.APP_SECRET)
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code");

        LatteLogger.d("authUrl", authUrl.toString());

        getAuth(authUrl.toString());
    }

    //通过code获取access_token
    private void getAuth(String authUrl) {
        RxRestClient.builder()
                .url(authUrl)
                .build()
                .get()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) throws Exception {
                        final JSONObject authObj = JSON.parseObject(response);
                        //接口调用凭证
                        final String accessToken = authObj.getString("access_token");
                        //授权用户唯一标识
                        final String openId = authObj.getString("openid");

                        final StringBuilder userInfoUrl = new StringBuilder();
                        userInfoUrl
                                .append("https://api.weixin.qq.com/sns/userinfo?access_token=")
                                .append(accessToken)
                                .append("&openid=")
                                .append(openId)
                                .append("&lang=")
                                .append("zh_CN");

                        LatteLogger.d("userInfoUrl", userInfoUrl.toString());
                        getUserInfo(userInfoUrl.toString());
                    }
                });
    }

    //获取用户个人信息
    private void getUserInfo(String userInfoUrl) {
        RxRestClient.builder()
                .url(userInfoUrl)
                .build()
                .get()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String response) {
                        onSignInSuccess(response);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
