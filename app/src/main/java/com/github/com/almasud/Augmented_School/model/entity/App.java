package com.github.com.almasud.Augmented_School.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The App entity model class.
 *
 * @author Abdullah Almasud
 */
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

    public String getDownloadURL() {
        return downloadURL;
    }

    public void setDownloadURL(String downloadURL) {
        this.downloadURL = downloadURL;
    }
}
