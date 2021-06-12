package com.github.almasud.augmented_learn.api.request;

import com.github.almasud.augmented_learn.model.entity.App;

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
