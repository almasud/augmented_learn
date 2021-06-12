package com.github.almasud.augmented_learn.model.entity;

import androidx.annotation.Keep;

import java.io.Serializable;

/**
 * A utility model class for Speech of {@link ArModel}.
 *
 * @author Abdullah Almasud
 */
@Keep
public class Voice implements Serializable {
    public static final int VOICE_BENGALI_VOWELS = 0;
    public static final int VOICE_BENGALI_ALPHABETS = 1;
    public static final int VOICE_BENGALI_NUMBERS = 2;
    public static final int VOICE_ENGLISH_ALPHABETS = 3;
    public static final int VOICE_ENGLISH_NUMBERS = 4;
    public static final int VOICE_ENGLISH_ANIMALS = 5;
    private final int id;
    private final int start;
    private final int end;
    private int extraStart;
    private int extraEnd;

    public Voice(int id, int start, int end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }

    /**
     * @return The ID of {@link Voice}.
     */
    public int getId() {
        return id;
    }

    /**
     * @return The start msec of {@link Voice}.
     */
    public int getStart() {
        return start;
    }

    /**
     * @return The end msec of {@link Voice}.
     */
    public int getEnd() {
        return end;
    }

    /**
     * @return The extra start msec of {@link Voice}.
     */
    public int getExtraStart() {
        return extraStart;
    }

    /**
     * Set the extra start msec of {@link Voice}.
     * @return An instance of {@link Voice}.
     */
    public Voice setExtraStart(int extraStart) {
        this.extraStart = extraStart;
        return this;
    }

    /**
     * @return The extra end msec of {@link Voice}.
     */
    public int getExtraEnd() {
        return extraEnd;
    }

    /**
     * Set the extra end msec of {@link Voice}.
     * @return An instance of {@link Voice}.
     */
    public Voice setExtraEnd(int extraEnd) {
        this.extraEnd = extraEnd;
        return this;
    }
}
