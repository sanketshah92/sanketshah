package com.sanketshah.backend.networking;


import android.content.Context;

import com.sanketshah.BuildConfig;
import com.sanketshah.R;
import com.sanketshah.appexceptions.NoConnectionException;
import com.sanketshah.utils.NetworkUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;
    private static Context mContext;
    private static OkHttpClient okHttpClient;

    public static Retrofit getClient(Context con) throws NoConnectionException {
        mContext = con;
        // if (BuildConfig.DEBUG) {
        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BaseURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build();

        if (NetworkUtil.getConnectionStatus(mContext) == NetworkUtil.NOT_CONNECTED) {
            throw new NoConnectionException(con.getResources().getString(R.string.no_iternet));
        }

        return retrofit;
    }

    private static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if (BuildConfig.DEBUG) {
                builder.addInterceptor(logging);
            }
            builder.readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS);
            okHttpClient = builder.build();
        }

        return okHttpClient;
    }

    public static Retrofit getClient(Context con, String baseUrl) throws NoConnectionException {
        mContext = con;
        //if (BuildConfig.DEBUG) {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build();

        if (NetworkUtil.getConnectionStatus(mContext) == NetworkUtil.NOT_CONNECTED) {
            throw new NoConnectionException(con.getResources().getString(R.string.no_iternet));
        }
        return retrofit;
    }

    public static ApiInterface getApiClientForBackground(Context con) throws NoConnectionException {
        mContext = con;
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);


        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit;
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(BuildConfig.BaseURL);
        builder.addConverterFactory(GsonConverterFactory.create());
        if (BuildConfig.DEBUG) {
            builder.client(okHttpClient);
        }
        retrofit = builder.build();

        if (NetworkUtil.getConnectionStatus(mContext) == NetworkUtil.NOT_CONNECTED) {
            throw new NoConnectionException(mContext.getString(R.string.no_iternet));
        }

        return retrofit.create(ApiInterface.class);
    }


}
