package com.github.com.almasud.Augmented_School.model.entity;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The App entity model class.
 *
 * @author Abdullah Almasud
 */
@Keep
public class App {
    public static final String DIRECTORY_APP = "app";
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("versionName")
    @Expose
    private String versionName;
    @SerializedName("versionCode")
    @Expose
    private Integer versionCode;
    @SerializedName("versionDescription")
    @Expose
    private String versionDescription;
    @SerializedName("downloadURL")
    @Expose
    private String downloadURL;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionDescription() {
        return versionDescription;
    }

    public void setVersionDescription(String versionDescription) {
        this.versionDescription = versionDescription;
    }

    public String getDownloadURL() {
        return downloadURL;
    }

    public void setDownloadURL(String downloadURL) {
        this.downloadURL = downloadURL;
    }
}
