package com.wuchao.latte.ec.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.wuchao.latte.delegates.LatteDelegate;
import com.wuchao.latte.ec.R;
import com.wuchao.latte.ec.R2;
import com.wuchao.latte.net.rx.RxRestClient;

import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: wuchao
 * @date: 2017/12/17 16:52
 * @desciption:
 */

public class ContentDelegate extends LatteDelegate {

    @BindView(R2.id.rv_list_content)
    RecyclerView mRecyclerView;

    private static final String ARG_CONTENT_ID = "CONTENT_ID";
    private int mContentId = -1;
    private List<SectionBean> mData = null;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mContentId = args.getInt(ARG_CONTENT_ID);
        }
    }

    public static ContentDelegate newInstance(int contentId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_ID, contentId);
        final ContentDelegate delegate = new ContentDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_list_content;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final StaggeredGridLayoutManager manager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        initData();
    }

    private void initData() {
        RxRestClient.builder()
                .url("sort_content_list.php?contentId=" + mContentId)
                .build()
                .get()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) throws Exception {
                        mData = new SectionDataConvert().convert(response);
                        final SectionAdapter adapter =
                                new SectionAdapter(R.layout.item_section_content,
                                        R.layout.item_section_header, mData);
                        mRecyclerView.setAdapter(adapter);
                    }
                });
    }
}
