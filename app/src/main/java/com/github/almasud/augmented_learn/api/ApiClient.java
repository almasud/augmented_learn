package com.github.almasud.augmented_learn.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The client API class of {@link Retrofit}.
 *
 * @author Abdullah Almasud
 */
public class ApiClient {
    public static final String BASE_URL = "https://github.com/almasud/almasud.github.io/raw/master/projects/augmented_learn/file/";
    public static Retrofit sRetrofit;

    public static Retrofit getClient() {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sRetrofit;
    }
}
