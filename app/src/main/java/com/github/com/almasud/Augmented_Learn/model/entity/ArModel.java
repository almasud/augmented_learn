package com.github.com.almasud.Augmented_Learn.model.entity;

import androidx.annotation.Keep;

import java.io.Serializable;

/**
 * An entity model class for AR item.
 *
 * @author Abdullah Almasud
 */
@Keep
public class ArModel implements Serializable {
    public static final String LIST_ITEM = "List_Item";
    public static final String SELECTED_ITEM = "Selected_Item";
    public static final String SUBJECT = "Subject";

    private int id;
    private String name;
    private String photo;
    private String fileName;
    private Voice voice;
    private Subject subject;
    private String extraName;
    private String extraPhoto;

    public ArModel(
            int id, String name, String photo, String fileName,
            Voice voice, Subject subject) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.fileName = fileName;
        this.voice = voice;
        this.subject = subject;
    }

    /**
     * @return An Id of {@link ArModel}.
     */
    public int getId() {
        return id;
    }

    /**
     * @return A {@link String} name of {@link ArModel}.
     */
    public String getName() {
        return name;
    }

    /**
     * @return A {@link String} photo path of {@link ArModel}.
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * @return A {@link String} file name of {@link ArModel}.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @return A {@link Voice} of {@link ArModel}.
     */
    public Voice getVoice() {
        return voice;
    }

    /**
     * @return A {@link Subject} of {@link ArModel}.
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * @return A {@link String} extra name of {@link ArModel}.
     */
    public String getExtraName() {
        return extraName;
    }

    /**
     * Set an extra name of {@link ArModel}.
     * @return An instance of {@link ArModel}.
     */
    public ArModel setExtraName(String extraName) {
        this.extraName = extraName;
        return this;
    }

    /**
     * @return A {@link String} extra photo path of {@link ArModel}.
     */
    public String getExtraPhoto() {
        return extraPhoto;
    }

    /**
     * Set a {@link String} extra photo path of {@link ArModel}.
     * @return An instance of {@link ArModel}.
     */
    public ArModel setExtraPhoto(String extraPhoto) {
        this.extraPhoto = extraPhoto;
        return this;
    }
}
