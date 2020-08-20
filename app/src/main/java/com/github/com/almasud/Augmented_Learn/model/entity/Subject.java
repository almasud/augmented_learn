package com.github.com.almasud.Augmented_Learn.model.entity;

import androidx.annotation.Keep;

import java.io.Serializable;

/**
 * An entity subject model class for {@link ArModel}.
 *
 * @author Abdullah Almasud
 */
@Keep
public class Subject implements Serializable {
    public static final int BENGALI_VOWEL = 0;
    public static final int BENGALI_ALPHABET = 1;
    public static final int BENGALI_NUMBER = 2;
    public static final int ENGLISH_ALPHABET = 3;
    public static final int ENGLISH_NUMBER = 4;
    public static final int ENGLISH_ANIMAL = 5;
    public static final String SUBJECT_BENGALI_VOWEL = "bn_vowel";
    public static final String SUBJECT_BENGALI_ALPHABET = "bn_alphabet";
    public static final String SUBJECT_BENGALI_NUMBER = "bn_number";
    public static final String SUBJECT_ENGLISH_ALPHABET = "en_alphabet";
    public static final String SUBJECT_ENGLISH_NUMBER = "en_number";
    public static final String SUBJECT_ENGLISH_ANIMAL = "en_animal";
    public static final String URL_MODEL_BENGALI_VOWELS = "https://almasud.github.io/Augmented_Learn/download/models/bengali_vowels.zip";
    public static final String URL_MODEL_BENGALI_ALPHABETS = "https://almasud.github.io/Augmented_Learn/download/models/bengali_alphabets.zip";
    public static final String URL_MODEL_BENGALI_NUMBERS = "https://almasud.github.io/Augmented_Learn/download/models/bengali_numbers.zip";
    public static final String URL_MODEL_ENGLISH_ALPHABETS = "https://almasud.github.io/Augmented_Learn/download/models/english_alphabets.zip";
    public static final String URL_MODEL_ENGLISH_NUMBERS = "https://almasud.github.io/Augmented_Learn/download/models/english_numbers.zip";
    public static final String URL_MODEL_ENGLISH_ANIMALS = "https://almasud.github.io/Augmented_Learn/download/models/english_animals.zip";
    public static final String URL_AR_BOOK = "https://almasud.github.io/Augmented_Learn/download/pdf/ar_book.pdf";

    private int id;
    private String name;
    private String coverPhoto;
    private Language language;
    private String modelURL;

    public Subject(int id, String name, String coverPhoto, Language language, String modelURL) {
        this.id = id;
        this.name = name;
        this.coverPhoto = coverPhoto;
        this.language = language;
        this.modelURL = modelURL;
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
     * @return A {@link String} cover photo path of {@link Subject}.
     */
    public String getCoverPhoto() {
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
    public String getModelURL() {
        return modelURL;
    }
}
