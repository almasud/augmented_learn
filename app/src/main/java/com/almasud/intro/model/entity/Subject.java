package com.almasud.intro.model.entity;

import android.content.Context;

import com.almasud.intro.R;

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

    /**
     *
     * @param context The {@link Context}.
     * @param subjectId An integer Id of {@link Subject}.
     * @return A {@link String} name of {@link Subject}.
     */
    public static String getSubjectName(Context context, int subjectId) {
        switch (subjectId) {
            case Subject.SUBJECT_VOWEL_BENGALI:
                return context.getResources().getString(R.string.vowel_bengali);
            case Subject.SUBJECT_ALPHABET_BENGALI:
                return context.getResources().getString(R.string.alphabet_bengali);
            case Subject.SUBJECT_NUMBER_BENGALI:
                return context.getResources().getString(R.string.number_bengali);
            case Subject.SUBJECT_ALPHABET_ENGLISH:
                return context.getResources().getString(R.string.alphabet_english);
            case Subject.SUBJECT_NUMBER_ENGLISH:
                return context.getResources().getString(R.string.number_english);
            case Subject.SUBJECT_ANIMAL_ENGLISH:
                return context.getResources().getString(R.string.animal_english);
        }
        return null;
    }
}
