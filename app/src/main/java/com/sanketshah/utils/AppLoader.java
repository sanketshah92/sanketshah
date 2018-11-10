package com.sanketshah.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.CardView;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sanketshah.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AppLoader extends AppCompatDialog {

    @BindView(R.id.imgApploader)
    ApppLoaderView imgApploader;
    @BindView(R.id.txtLoadingText)
    TextView txtLoadingText;
    @BindView(R.id.llLoadingView)
    RelativeLayout llLoadingView;
    @BindView(R.id.cardAppLoader)
    CardView cardAppLoader;

    public AppLoader(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_loader);
        ButterKnife.bind(this);

        imgApploader.setEndColor(getContext().getResources().getColor(R.color.colorPrimaryDark));
        imgApploader.setStartColor(getContext().getResources().getColor(R.color.colorPrimary));

    }

}
