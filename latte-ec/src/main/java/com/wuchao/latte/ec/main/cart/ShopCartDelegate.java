package com.wuchao.latte.ec.main.cart;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewStubCompat;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.joanzapata.iconify.widget.IconTextView;
import com.wuchao.ec.R;
import com.wuchao.ec.R2;
import com.wuchao.latte.delegates.bottom.BottomItemDelegate;
import com.wuchao.latte.ec.pay.FastPay;
import com.wuchao.latte.ec.pay.IAlPayResultListener;
import com.wuchao.latte.net.rx.RxRestClient;
import com.wuchao.latte.ui.recycler.MultipleItemEntity;
import com.wuchao.latte.util.log.LatteLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: wuchao
 * @date: 2017/12/23 21:53
 * @desciption:
 */

public class ShopCartDelegate extends BottomItemDelegate implements ICartItemListener, IAlPayResultListener {

    @BindView(R2.id.rv_shop_cart)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.icon_shop_cart_select_all)
    IconTextView mIconSelectAll = null;
    @BindView(R2.id.stub_no_item)
    ViewStubCompat mStubNoItem = null;
    @BindView(R2.id.tv_shop_cart_total_price)
    AppCompatTextView mTvTotalPrice = null;

    private ShopCartAdapter mAdapter = null;
    //购物车数量标记
    private int mCurrentCount = 0;
    private int mTotalCount = 0;
    private double mTotalPrice = 0.00;
    private boolean isInflate = false;

    @OnClick(R2.id.icon_shop_cart_select_all)
    void onClickSelectAll() {
        final int tag = (int) mIconSelectAll.getTag();
        if (tag == 0) {
            mIconSelectAll.setTextColor
                    (ContextCompat.getColor(getContext(), R.color.app_main));
            mIconSelectAll.setTag(1);
            mAdapter.setIsSelectedAll(true);
            //更新RecyclerView的显示状态
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        } else {
            mIconSelectAll.setTextColor(Color.GRAY);
            mIconSelectAll.setTag(0);
            mAdapter.setIsSelectedAll(false);
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        }
    }

    @OnClick(R2.id.tv_top_shop_cart_remove_selected)
    void onClickRemoveSelectedItem() {
        final List<MultipleItemEntity> data = mAdapter.getData();
        //要删除的数据
        final List<MultipleItemEntity> deleteEntities = new ArrayList<>();
        for (MultipleItemEntity entity : data) {
            final boolean isSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
            if (isSelected) {
                deleteEntities.add(entity);
            }
        }
        for (MultipleItemEntity entity : deleteEntities) {
            int removePosition;
            final int entityPosition = entity.getField(ShopCartItemFields.POSITION);
            if (entityPosition > mCurrentCount - 1) {
                removePosition = entityPosition - (mTotalCount - mCurrentCount);
            } else {
                removePosition = entityPosition;
            }
            if (removePosition <= mAdapter.getItemCount()) {
                mAdapter.remove(removePosition);
                mCurrentCount = mAdapter.getItemCount();
                mAdapter.notifyItemRangeChanged(removePosition, mAdapter.getItemCount());
            }
        }
        checkItemCount();
    }

    @OnClick(R2.id.tv_top_shop_cart_clear)
    void onClickClear() {
        mAdapter.getData().clear();
        mAdapter.notifyDataSetChanged();
        checkItemCount();
    }

    @OnClick(R2.id.tv_shop_cart_pay)
    void onClickPay() {
        //FastPay.create(this).beginPayDialog();
        createOrder();
    }

    //创建订单，注意，和支付是没有关系的
    private void createOrder() {
        //"你的生成订单的API"
        final String orderUrl = "http://app.api.zanzuanshi.com/api/vl/peyment";
        final WeakHashMap<String, Object> orderParams = new WeakHashMap<>();
        orderParams.put("userid", 264392);
        orderParams.put("amount", 0.01);
        orderParams.put("comment", "测试支付");
        orderParams.put("type", 1);
        orderParams.put("ordertype", 0);
        orderParams.put("isanonymous", true);
        orderParams.put("followeduser", 0);
        RxRestClient.builder()
                .url(orderUrl)
                .params(orderParams)
                .build()
                .post()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) throws Exception {
                        //进行具体支付
                        LatteLogger.d("ORDER", response);
                        //result:后端和前端的一个约定
                        final int orderId= JSON.parseObject(response).getInteger("result");
                        FastPay.create(ShopCartDelegate.this)
                                .setPayResultListener(ShopCartDelegate.this)
                                .setOrderId(orderId)
                                .beginPayDialog();
                    }
                });
    }

    @SuppressWarnings("RestrictedApi")
    private void checkItemCount() {
        final int count = mAdapter.getItemCount();
        if (count == 0) {
            if (!isInflate) {
                final View stubView = mStubNoItem.inflate();
                final AppCompatTextView tvToBuy =
                        (AppCompatTextView) stubView.findViewById(R.id.tv_stub_to_buy);
                tvToBuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "你该购物啦！", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mIconSelectAll.setTag(0);
        mStubNoItem.setOnInflateListener(new ViewStubCompat.OnInflateListener() {
            @Override
            public void onInflate(ViewStubCompat stub, View inflated) {
                isInflate = true;
            }
        });
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RxRestClient.builder()
                .url("shop_cart.php")
                .build()
                .get()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) throws Exception {
                        final ArrayList<MultipleItemEntity> data =
                                new shopCartDataConvert().setJsonData(response).convert();
                        mAdapter = new ShopCartAdapter(data);
                        mAdapter.setCartItemListener(ShopCartDelegate.this);
                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mRecyclerView.setLayoutManager(manager);
                        mRecyclerView.setAdapter(mAdapter);
                        mTotalPrice = mAdapter.getTotalPrice();
                        mTvTotalPrice.setText(String.valueOf(mTotalPrice));
                        checkItemCount();
                    }
                });
    }

    @Override
    public void onItemClick(double itemTotalPrice) {
        final double price = mAdapter.getTotalPrice();
        mTvTotalPrice.setText(String.valueOf(price));
    }

    @Override
    public void onPaySuccess() {

    }

    @Override
    public void onPaying() {

    }

    @Override
    public void onPayingFail() {

    }

    @Override
    public void onPayingCancel() {

    }

    @Override
    public void onPayingConnectError() {

    }
}
