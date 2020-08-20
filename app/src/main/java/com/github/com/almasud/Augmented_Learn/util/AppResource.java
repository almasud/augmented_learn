package com.github.com.almasud.Augmented_Learn.util;

import android.net.Uri;

import com.github.com.almasud.Augmented_Learn.model.entity.ArModel;
import com.github.com.almasud.Augmented_Learn.model.entity.Language;
import com.github.com.almasud.Augmented_Learn.model.entity.Subject;
import com.github.com.almasud.Augmented_Learn.model.entity.Voice;
import com.google.ar.core.AugmentedImageDatabase;

import java.io.File;

/**
 * A manager class for mange the resource {@link Uri}.
 *
 * @author Abdullah Almasud
 */
public final class AppResource {
    public static final String DIRECTORY_IMAGES_ROOT = "images";
    public static final String DIRECTORY_FONTS_ROOT = "fonts";
    public static final String DIRECTORY_AUDIOS_ROOT = "audios";
    public static final String DIRECTORY_MODELS_ROOT = "models";
    public static final String DIRECTORY_DATABASES_ROOT = "databases";
    public static final String DIRECTORY_BENGALI_VOWELS = "bengali_vowels";
    public static final String DIRECTORY_BENGALI_ALPHABETS = "bengali_alphabets";
    public static final String DIRECTORY_BENGALI_NUMBERS = "bengali_numbers";
    public static final String DIRECTORY_ENGLISH_ALPHABETS = "english_alphabets";
    public static final String DIRECTORY_ENGLISH_NUMBERS = "english_numbers";
    public static final String DIRECTORY_ENGLISH_ANIMALS = "english_animals";
    private static final String FONT_BENGALI_SOLAIMAN_LIPI = "bn_solaiman-lipi.ttf";
    private static final String FONT_ENGLISH_BERKSHIRE_SWASH_REGULAR = "en_berkshire-swash_regular.ttf";
    private static final String AUDIO_BENGALI_VOWELS = "bn_vowels.mp3";
    private static final String AUDIO_BENGALI_VOWELS_WITH_EXTRA = "bn_vowels_with_extra.mp3";
    private static final String AUDIO_BENGALI_ALPHABETS = "bn_alphabets.mp3";
    private static final String AUDIO_BENGALI_ALPHABETS_WITH_EXTRA = "bn_alphabets_with_extra.mp3";
    private static final String AUDIO_BENGALI_NUMBERS = "bn_numbers.mp3";
    private static final String AUDIO_ENGLISH_ALPHABETS = "en_alphabets.mp3";
    private static final String AUDIO_ENGLISH_ALPHABETS_WITH_EXTRA = "en_alphabets_with_extra.mp3";
    private static final String AUDIO_ENGLISH_NUMBERS = "en_numbers.mp3";
    private static final String AUDIO_ENGLISH_ANIMALS = "en_animals.mp3";
    private static final String DATABASE_AUGMENTED_IMAGE_BENGALI_VOWELS = "bengali_vowels.imgdb";
    private static final String DATABASE_AUGMENTED_IMAGE_BENGALI_ALPHABETS = "bengali_alphabets.imgdb";
    private static final String DATABASE_AUGMENTED_IMAGE_BENGALI_NUMBERS = "bengali_numbers.imgdb";
    private static final String DATABASE_AUGMENTED_IMAGE_ENGLISH_ALPHABETS = "english_alphabets.imgdb";
    private static final String DATABASE_AUGMENTED_IMAGE_ENGLISH_NUMBERS = "english_numbers.imgdb";
    private static final String DATABASE_AUGMENTED_IMAGE_ENGLISH_ANIMALS = "english_animals.imgdb";

    private AppResource() {}

    /**
     * Used to get an image {@link Uri} that stored in asset.
     * @param imageName A {@link String} name of the image.
     * @return An {@link Uri} of an image that stored in asset.
     */
    public static Uri getAssetImageUri(String imageName) {
        if (imageName.startsWith(Subject.SUBJECT_BENGALI_VOWEL))
            return Uri.parse(
                    "file:///android_asset/" + DIRECTORY_IMAGES_ROOT + File.separator
                            + DIRECTORY_BENGALI_VOWELS + File.separator + imageName
            );
        else if (imageName.contains(Subject.SUBJECT_BENGALI_ALPHABET))
            return Uri.parse(
                    "file:///android_asset/" + DIRECTORY_IMAGES_ROOT + File.separator
                            + DIRECTORY_BENGALI_ALPHABETS + File.separator + imageName
            );
        else if (imageName.contains(Subject.SUBJECT_BENGALI_NUMBER))
            return Uri.parse(
                    "file:///android_asset/" + DIRECTORY_IMAGES_ROOT + File.separator
                            + DIRECTORY_BENGALI_NUMBERS + File.separator + imageName
            );
        else if (imageName.contains(Subject.SUBJECT_ENGLISH_ALPHABET))
            return Uri.parse(
                    "file:///android_asset/" + DIRECTORY_IMAGES_ROOT + File.separator
                            + DIRECTORY_ENGLISH_ALPHABETS + File.separator + imageName
            );
        else if (imageName.contains(Subject.SUBJECT_ENGLISH_NUMBER))
            return Uri.parse(
                    "file:///android_asset/" + DIRECTORY_IMAGES_ROOT + File.separator
                            + DIRECTORY_ENGLISH_NUMBERS + File.separator + imageName
            );
        else if (imageName.contains(Subject.SUBJECT_ENGLISH_ANIMAL))
            return Uri.parse(
                    "file:///android_asset/" + DIRECTORY_IMAGES_ROOT + File.separator
                            + DIRECTORY_ENGLISH_ANIMALS + File.separator + imageName
            );
        else
            return null;
    }

    /**
     * Used to get an array {@link Uri} to get a {@link Subject} {@link Voice} (s) with extra.
     * @param SubjectId An id of {@link Subject}.
     * @return An array of {@link Uri}.
     */
    public static Uri[] getAudioUriWithExtra(int SubjectId) {
        // Sort the elements of audios array according to Subject id order.
        String[] audios = new String [] {
                AUDIO_BENGALI_VOWELS, AUDIO_BENGALI_ALPHABETS, AUDIO_BENGALI_NUMBERS,
                AUDIO_ENGLISH_ALPHABETS, AUDIO_ENGLISH_NUMBERS, AUDIO_ENGLISH_ANIMALS
        };
        String[] audios_extra = new String [] {
                AUDIO_BENGALI_VOWELS_WITH_EXTRA, AUDIO_BENGALI_ALPHABETS_WITH_EXTRA, null,
                AUDIO_ENGLISH_ALPHABETS_WITH_EXTRA, null, null
        };

        return new Uri[] {
                Uri.parse(
                        DIRECTORY_AUDIOS_ROOT + File.separator + audios[SubjectId]
                ), Uri.parse(
                        DIRECTORY_AUDIOS_ROOT + File.separator + audios_extra[SubjectId]
                )
        };
    }

    /**
     * Used to get a font {@link Uri} of a {@link Language}.
     * @param languageId An id of {@link Language}.
     * @return An {@link Uri} of a font.
     */
    public static Uri getFontUri(int languageId) {
        if (languageId == Language.BENGALI)
            return Uri.parse(
                    DIRECTORY_FONTS_ROOT + File.separator + FONT_BENGALI_SOLAIMAN_LIPI
            );
        else if (languageId == Language.ENGLISH)
            return Uri.parse(
                    DIRECTORY_FONTS_ROOT + File.separator + FONT_ENGLISH_BERKSHIRE_SWASH_REGULAR
            );
        else
            return null;
    }

    /**
     * Used to get an {@link Uri} of an {@link ArModel}'s 3D object.
     * @param SubjectId An id of {@link Subject}.
     * @return An {@link Uri} of an {@link ArModel}'s 3D object.
     */
    public static Uri getModelUri(int SubjectId) {
        // Sort the elements of models array according to Subject id order.
        String[] models = new String [] {
                DIRECTORY_BENGALI_VOWELS, DIRECTORY_BENGALI_ALPHABETS, DIRECTORY_BENGALI_NUMBERS,
                DIRECTORY_ENGLISH_ALPHABETS, DIRECTORY_ENGLISH_NUMBERS, DIRECTORY_ENGLISH_ANIMALS
        };

        return Uri.parse(
                DIRECTORY_MODELS_ROOT + File.separator + models[SubjectId]
        );
    }

    /**
     * Used to get an {@link Uri} of an {@link AugmentedImageDatabase}.
     * @param SubjectId An id of {@link Subject}.
     * @return An {@link Uri} of an {@link AugmentedImageDatabase}.
     */
    public static Uri getDatabaseAugmentedImageUri(int SubjectId) {
        // Sort the elements of databases array according to Subject id order.
        String[] databases = new String [] {
                DATABASE_AUGMENTED_IMAGE_BENGALI_VOWELS, DATABASE_AUGMENTED_IMAGE_BENGALI_ALPHABETS,
                DATABASE_AUGMENTED_IMAGE_BENGALI_NUMBERS, DATABASE_AUGMENTED_IMAGE_ENGLISH_ALPHABETS,
                DATABASE_AUGMENTED_IMAGE_ENGLISH_NUMBERS, DATABASE_AUGMENTED_IMAGE_ENGLISH_ANIMALS
        };

        return Uri.parse(
                DIRECTORY_DATABASES_ROOT + File.separator + databases[SubjectId]
        );
    }
}
