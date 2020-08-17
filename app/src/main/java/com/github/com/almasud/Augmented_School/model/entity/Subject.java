package com.github.com.almasud.Augmented_School.model.entity;

import androidx.annotation.DrawableRes;
import androidx.annotation.Keep;

import java.io.Serializable;

/**
 * An entity subject model class for {@link ArModel}.
 *
 * @author Abdullah Almasud
 */
@Keep
public class Subject implements Serializable {
    public static final int VOWEL_BENGALI = 0;
    public static final int ALPHABET_BENGALI = 1;
    public static final int NUMBER_BENGALI = 2;
    public static final int ALPHABET_ENGLISH = 3;
    public static final int NUMBER_ENGLISH = 4;
    public static final int ANIMAL_ENGLISH = 5;
    public static final String DOWNLOAD_URL_VOWELS_BENGALI = "https://almasud.000webhostapp.com/Augmented_School/download/vowels_bengali.zip";
    public static final String DOWNLOAD_URL_ALPHABETS_BENGALI = "https://almasud.000webhostapp.com/Augmented_School/download/alphabets_bengali.zip";
    public static final String DOWNLOAD_URL_NUMBERS_BENGALI = "https://almasud.000webhostapp.com/Augmented_School/download/numbers_bengali.zip";
    public static final String DOWNLOAD_URL_ALPHABETS_ENGLISH = "https://almasud.000webhostapp.com/Augmented_School/download/alphabets_english.zip";
    public static final String DOWNLOAD_URL_NUMBERS_ENGLISH = "https://almasud.000webhostapp.com/Augmented_School/download/numbers_english.zip";
    public static final String DOWNLOAD_URL_ANIMALS_ENGLISH = "https://almasud.000webhostapp.com/Augmented_School/download/animals_english.zip";

    private int id;
    private String name;
    private int coverPhoto;
    private Language language;
    private String downloadURL;

    public Subject(int id, String name, int coverPhoto, Language language, String downloadURL) {
        this.id = id;
        this.name = name;
        this.coverPhoto = coverPhoto;
        this.language = language;
        this.downloadURL = downloadURL;
    }

    /**
     * @return An Id of {@link Subject}.
     */
    public int getId() {
        return id;
    }

    /**
     * @return A name of {@link Subject}.
     */
    public String getName() {
        return name;
    }

    /**
     * @return A {@link DrawableRes} cover photo id of {@link Subject}.
     */
    public int getCoverPhoto() {
        return coverPhoto;
    }

    /**
     * @return A {@link Language} of {@link Subject}.
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * @return A {@link String} url of {@link Subject}.
     */
    public String getDownloadURL() {
        return downloadURL;
    }
}
