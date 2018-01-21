package com.wuchao.latte.ec.main.personal.address;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wuchao.ec.R;
import com.wuchao.ec.R2;
import com.wuchao.latte.delegates.LatteDelegate;
import com.wuchao.latte.net.rx.RxRestClient;
import com.wuchao.latte.ui.loader.LatteLoader;
import com.wuchao.latte.ui.recycler.MultipleItemEntity;
import com.wuchao.latte.util.log.LatteLogger;

import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: wuchao
 * @date: 2018/1/7 21:51
 * @desciption:
 */

public class AddressDelegate extends LatteDelegate {

    @BindView(R2.id.rv_address)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_address;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        RxRestClient.builder()
                .url("address.php")
                .loader(getContext())
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) throws Exception {
                        LatteLoader.stopLoading();
                        LatteLogger.d("AddressDelegate", response);
                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mRecyclerView.setLayoutManager(manager);
                        final List<MultipleItemEntity> data =
                                new AddressDataConverter().setJsonData(response).convert();
                        final AddressAdapter addressAdapter = new AddressAdapter(data);
                        mRecyclerView.setAdapter(addressAdapter);
                    }
                });
    }
}
