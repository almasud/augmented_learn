package com.almasud.intro.model;

import androidx.annotation.DrawableRes;

import com.google.ar.sceneform.rendering.ModelRenderable;

import java.io.Serializable;

/**
 * The model class of an {@link ArModel}.
 *
 * @author Abdullah Almasud
 */
public class ArModel implements Serializable {
    private int id;
    private String modelType;
    private String name;
    private int photo;
    private String modelPath;
    private String labelName;
    private int labelPhoto;

    public ArModel(int id, String modelType, String name, int photo, String modelPath) {
        this.id = id;
        this.modelType = modelType;
        this.name = name;
        this.photo = photo;
        this.modelPath = modelPath;
    }

    /**
     * @return A name of an {@link ArModel}.
     */
    public int getId() {
        return id;
    }

    /**
     * @return A type of {@link ArModel}.
     */
    public String getModelType() {
        return modelType;
    }

    /**
     * @return A name of an {@link ArModel}.
     */
    public String getName() {
        return name;
    }

    /**
     * @return A {@link DrawableRes} photo id of an {@link ArModel}.
     */
    public int getPhoto() {
        return photo;
    }

    /**
     * @return A label name of an {@link ArModel}.
     */
    public String getLabelName() {
        return labelName;
    }

    /**
     * Set a label name of an {@link ArModel}. A label name describe the {@link ArModel}
     * for what (i.e A for apple).
     * @return An instance of {@link ArModel}.
     */
    public ArModel setLabelName(String labelName) {
        this.labelName = labelName;
        return this;
    }

    /**
     * @return A {@link DrawableRes} label photo id of an {@link ArModel}.
     */
    public int getLabelPhoto() {
        return labelPhoto;
    }

    /**
     * Set a label photo of an {@link ArModel}. A label photo describe the {@link ArModel}
     * for what (i.e A for apple photo)
     * @return An instance of {@link ArModel}.
     */
    public ArModel setLabelPhoto(int labelPhoto) {
        this.labelPhoto = labelPhoto;
        return this;
    }

    /**
     * @return A {@link ModelRenderable} path of an {@link ArModel}.
     */
    public String getModelPath() {
        return modelPath;
    }
}
