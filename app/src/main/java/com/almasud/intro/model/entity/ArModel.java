package com.almasud.intro.model.entity;

import androidx.annotation.DrawableRes;

import com.google.ar.sceneform.rendering.ModelRenderable;

import java.io.Serializable;

/**
 * An entity model class for AR item.
 *
 * @author Abdullah Almasud
 */
public class ArModel implements Serializable {
    public static final String LIST_ITEM = "List_Item";
    public static final String SELECTED_ITEM = "Selected_Item";
    private int id;
    private String name;
    private int photo;
    private String modelPath;
    private Voice voice;
    private Category category;
    private String extraName;
    private int extraPhoto;

    public ArModel(
            int id, String name, int photo, String modelPath,
            Voice voice, Category category) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.modelPath = modelPath;
        this.voice = voice;
        this.category = category;
    }

    /**
     * @return The name of {@link ArModel}.
     */
    public int getId() {
        return id;
    }

    /**
     * @return The name of {@link ArModel}.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The {@link DrawableRes} photo id of {@link ArModel}.
     */
    public int getPhoto() {
        return photo;
    }

    /**
     * @return The {@link ModelRenderable} path of {@link ArModel}.
     */
    public String getModelPath() {
        return modelPath;
    }

    /**
     * @return The {@link Voice} of {@link ArModel}.
     */
    public Voice getVoice() {
        return voice;
    }

    /**
     * @return The {@link Category} of {@link ArModel}.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @return The extra name of {@link ArModel}.
     */
    public String getExtraName() {
        return extraName;
    }

    /**
     * Set the extra name of {@link ArModel}.
     * @return An instance of {@link ArModel}.
     */
    public ArModel setExtraName(String extraName) {
        this.extraName = extraName;
        return this;
    }

    /**
     * @return The {@link DrawableRes} extra photo id of {@link ArModel}.
     */
    public int getExtraPhoto() {
        return extraPhoto;
    }

    /**
     * Set the {@link DrawableRes} extra photo id of {@link ArModel}.
     * @return An instance of {@link ArModel}.
     */
    public ArModel setExtraPhoto(int extraPhoto) {
        this.extraPhoto = extraPhoto;
        return this;
    }
}
