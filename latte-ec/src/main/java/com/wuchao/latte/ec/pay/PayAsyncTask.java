package com.wuchao.latte.ec.pay;

import android.app.Activity;
import android.os.AsyncTask;

import com.alipay.sdk.app.PayTask;
import com.wuchao.latte.ui.loader.LatteLoader;
import com.wuchao.latte.util.log.LatteLogger;

/**
 * @author: wuchao
 * @date: 2018/1/1 18:14
 * @desciption:
 */

public class PayAsyncTask extends AsyncTask<String, Void, String> {

    private final Activity mActivity;
    private final IAlPayResultListener mListener;

    //订单支付成功
    private static final String AL_PAY_STATUS_SUCCESS = "9000";
    //订单处理中
    private static final String AL_PAY_STATUS_PAYING = "8000";
    //订单支付失败
    private static final String AL_PAY_STATUS_FAIL = "4000";
    //用户取消
    private static final String AL_PAY_STATUS_CANCEL = "6001";
    //支付网络错误
    private static final String AL_PAY_STATUS_CONNECT_ERROR = "6002";

    public PayAsyncTask(Activity activity, IAlPayResultListener listener) {
        mActivity = activity;
        mListener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        final String alPaySign = params[0];
        final PayTask payTask = new PayTask(mActivity);
        return payTask.pay(alPaySign, true);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        LatteLoader.stopLoading();
        final PayResult payResult = new PayResult(result);
        //支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约是支付宝提供的公钥做验签
        final String resultInfo = payResult.getResult();
        final String resultStatus = payResult.getResultStatus();
        LatteLogger.d("AL_PAY_RESULT", resultInfo);
        LatteLogger.d("AL_PAY_RESULT", resultStatus);

        switch (resultStatus) {
            case AL_PAY_STATUS_SUCCESS:
                if (mListener != null) {
                    mListener.onPaySuccess();
                }
                break;
            case AL_PAY_STATUS_FAIL:
                if (mListener != null) {
                    mListener.onPayingFail();
                }
                break;
            case AL_PAY_STATUS_PAYING:
                if (mListener != null) {
                    mListener.onPaying();
                }
                break;
            case AL_PAY_STATUS_CANCEL:
                if (mListener != null) {
                    mListener.onPayingCancel();
                }
                break;
            case AL_PAY_STATUS_CONNECT_ERROR:
                if (mListener != null) {
                    mListener.onPayingConnectError();
                }
                break;
            default:
                break;
        }
    }
}
