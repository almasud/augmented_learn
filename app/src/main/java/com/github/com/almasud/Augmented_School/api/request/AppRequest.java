package com.github.com.almasud.Augmented_School.api.request;

import com.github.com.almasud.Augmented_School.model.entity.App;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * The request API interface of the {@link App}.
 *
 * @author Abdullah Almasud
 */
public interface AppRequest {
    @GET("?app_info")
    Single<App> getAppInfo();
}
