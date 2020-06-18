package com.khattab.softxperttask.common.aPIsUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebServiceClient {

    private static Retrofit retrofit = null;

    public static String API_BASE_URL(){
            return "http://demo1286023.mockable.io/api/v1/";
    }

    public static Retrofit getClient() {

        // add OKHttp log debug
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES).addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder()
                .create();

        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();

        }

        return retrofit;
    }

}