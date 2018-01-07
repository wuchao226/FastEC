package com.wuchao.latte.ec.main.personal.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.wuchao.ec.R;
import com.wuchao.latte.delegates.LatteDelegate;
import com.wuchao.latte.ec.main.personal.list.ListBean;
import com.wuchao.latte.ui.date.DateDialogUtil;
import com.wuchao.latte.util.callback.CallbackManager;
import com.wuchao.latte.util.callback.CallbackType;
import com.wuchao.latte.util.callback.IGlobalCallback;
import com.wuchao.latte.util.log.LatteLogger;

/**
 * @author: wuchao
 * @date: 2018/1/4 22:26
 * @desciption:
 */

public class UserProfileClickListener extends SimpleClickListener {

    private final LatteDelegate mDelegate;
    private String[] mGenders = new String[]{"男", "女", "保密"};

    public UserProfileClickListener(LatteDelegate delegate) {
        mDelegate = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, final View view, int position) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        final int id = bean.getId();
        switch (id) {
            case 1:
                //开始照相机或选择图片
                CallbackManager.getInstance()
                        .addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                            @Override
                            public void executeCallback(@NonNull Uri args) {
                                LatteLogger.d("ON_CROP", args);
                                final ImageView avatar = (ImageView) view.findViewById(R.id.img_arrow_avatar);
                                Glide.with(mDelegate)
                                        .load(args)
                                        .into(avatar);

                                /*RxRestClient.builder()
                                        .url(UploadConfig.UPLOAD_IMG)
                                        .loader(mDelegate.getContext())
                                        .file(args.getPath())
                                        .build()
                                        .upload()
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new Consumer<String>() {
                                            @Override
                                            public void accept(String response) throws Exception {
                                                LatteLogger.d("ON_CROP_UPLOAD", response);
                                                final String path = JSON.parseObject(response).getJSONObject("result")
                                                        .getString("path");

                                                //通知服务器更新信息
                                                RxRestClient.builder()
                                                        .url("user_profile.php")
                                                        .params("avatar", path)
                                                        .loader(mDelegate.getContext())
                                                        .build()
                                                        .post()
                                                        .subscribeOn(Schedulers.io())
                                                        .observeOn(AndroidSchedulers.mainThread())
                                                        .subscribe(new Consumer<String>() {
                                                            @Override
                                                            public void accept(String response) throws Exception {
                                                                //获取更新后的用户信息，然后更新本地数据库
                                                                //没有本地数据的APP，每次打开APP都请求API，获取信息
                                                            }
                                                        });
                                            }
                                        });*/
                            }
                        });
                mDelegate.startCameraWithCheck();
                break;
            case 2:
                final LatteDelegate nameDelegate = bean.getDelegate();
                mDelegate.getSupportDelegate().start(nameDelegate);
                break;
            case 3:
                getGenderDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final TextView textView = (TextView) view.findViewById(R.id.tv_arrow_value);
                        textView.setText(mGenders[which]);
                        dialog.cancel();
                    }
                });
                break;
            case 4:
                final DateDialogUtil dialogUtil = new DateDialogUtil();
                dialogUtil.setDateListener(new DateDialogUtil.IDateListener() {
                    @Override
                    public void onDateChange(String date) {
                        final TextView textView = (TextView) view.findViewById(R.id.tv_arrow_value);
                        textView.setText(date);
                    }
                });
                dialogUtil.showDialog(mDelegate.getContext());
                break;
            default:
                break;
        }
    }

    private void getGenderDialog(DialogInterface.OnClickListener listener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mDelegate.getContext());
        builder.setSingleChoiceItems(mGenders, 0, listener);
        builder.show();
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
