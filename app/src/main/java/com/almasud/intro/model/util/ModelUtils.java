package com.almasud.intro.model.util;

import android.app.Application;
import android.content.Context;

import com.almasud.intro.R;
import com.almasud.intro.model.entity.Category;

/**
 * An utility class for model classes.
 *
 * @author Abdullah Almasud
 */
public final class ModelUtils {

    /**
     * @param context The context of {@link Application}.
     * @param categoryId The ID of {@link Category}.
     * @return A {@link String} of {@link Category} name.
     */
    public static String getArModelCategoryName(Context context, int categoryId) {
        switch (categoryId) {
            case Category.CATEGORY_VOWEL_BENGALI:
                return context.getResources().getString(R.string.vowel_bengali);
            case Category.CATEGORY_ALPHABET_BENGALI:
                return context.getResources().getString(R.string.alphabet_bengali);
            case Category.CATEGORY_NUMBER_BENGALI:
                return context.getResources().getString(R.string.number_bengali);
            case Category.CATEGORY_ALPHABET_ENGLISH:
                return context.getResources().getString(R.string.alphabet_english);
            case Category.CATEGORY_NUMBER_ENGLISH:
                return context.getResources().getString(R.string.number_english);
            case Category.CATEGORY_ANIMAL_ENGLISH:
                return context.getResources().getString(R.string.animal_english);
        }
        return null;
    }
}
