package com.wuchao.latte.ui.date;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * @author: wuchao
 * @date: 2018/1/4 23:00
 * @desciption:
 */

public class DateDialogUtil {

    public interface IDateListener {
        void onDateChange(String date);
    }

    private IDateListener mDateListener = null;

    public void setDateListener(IDateListener dateListener) {
        mDateListener = dateListener;
    }

    public void showDialog(Context context) {
        DatePickerDialog pickerDialog = new DatePickerDialog(context, 0, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                final Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                final SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
                final String data = format.format(calendar.getTime());
                if (mDateListener != null) {
                    mDateListener.onDateChange(data);
                }
            }
        }, 1990, 1, 1);
        pickerDialog.show();
        /*final LinearLayout ll = new LinearLayout(context);
        final DatePicker picker = new DatePicker(context);
        final LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
        picker.setLayoutParams(lp);
        picker.init(1990, 1, 1, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                final Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                final SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
                final String data = format.format(calendar.getTime());
                if (mDateListener != null) {
                    mDateListener.onDateChange(data);
                }
            }
        });
        ll.addView(picker);

        new AlertDialog.Builder(context)
                .setTitle("选择日期")
                .setView(ll)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();*/
    }
}
