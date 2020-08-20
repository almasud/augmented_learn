package com.github.com.almasud.Augmented_Learn.api.request;

import com.github.com.almasud.Augmented_Learn.model.entity.App;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * The request API interface of the {@link App}.
 *
 * @author Abdullah Almasud
 */
public interface AppRequest {
    @GET("app.json")
    Single<App> getAppInfo();
}
