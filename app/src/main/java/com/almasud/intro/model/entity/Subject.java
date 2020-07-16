package com.almasud.intro.model.entity;

import java.io.Serializable;

/**
 * An entity model class for subject of {@link ArModel}.
 *
 * @author Abdullah Almasud
 */
public class Subject implements Serializable {
    public static final int SUBJECT_VOWEL_BENGALI = 0;
    public static final int SUBJECT_ALPHABET_BENGALI = 1;
    public static final int SUBJECT_NUMBER_BENGALI = 2;
    public static final int SUBJECT_ALPHABET_ENGLISH = 3;
    public static final int SUBJECT_NUMBER_ENGLISH = 4;
    public static final int SUBJECT_ANIMAL_ENGLISH = 5;
    private int id;
    private String name;

    public Subject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * @return The ID of {@link Subject}.
     */
    public int getId() {
        return id;
    }

    /**
     * @return The name of {@link Subject}.
     */
    public String getName() {
        return name;
    }
}
