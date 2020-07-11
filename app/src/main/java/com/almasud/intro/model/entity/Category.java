package com.almasud.intro.model.entity;

import java.io.Serializable;

/**
 * An entity model class for category of {@link ArModel}.
 *
 * @author Abdullah Almasud
 */
public class Category implements Serializable {
    public static final int CATEGORY_VOWEL_BENGALI = 0;
    public static final int CATEGORY_ALPHABET_BENGALI = 1;
    public static final int CATEGORY_NUMBER_BENGALI = 2;
    public static final int CATEGORY_ALPHABET_ENGLISH = 3;
    public static final int CATEGORY_NUMBER_ENGLISH = 4;
    public static final int CATEGORY_ANIMAL_ENGLISH = 5;
    private int id;
    private String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * @return The ID of {@link Category}.
     */
    public int getId() {
        return id;
    }

    /**
     * @return The name of {@link Category}.
     */
    public String getName() {
        return name;
    }
}
