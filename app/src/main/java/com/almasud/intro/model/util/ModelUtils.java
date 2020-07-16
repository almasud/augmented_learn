package com.almasud.intro.model.util;

import android.app.Application;
import android.content.Context;

import com.almasud.intro.R;
import com.almasud.intro.model.entity.Subject;

/**
 * An utility class for model classes.
 *
 * @author Abdullah Almasud
 */
public final class ModelUtils {

    /**
     * @param context The context of {@link Application}.
     * @param categoryId The ID of {@link Subject}.
     * @return A {@link String} of {@link Subject} name.
     */
    public static String getArModelCategoryName(Context context, int categoryId) {
        switch (categoryId) {
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
