package com.almasud.intro.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.almasud.intro.R;
import com.almasud.intro.model.entity.ArModel;
import com.almasud.intro.model.entity.Category;
import com.almasud.intro.model.entity.Voice;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@link AndroidViewModel} of the {@link ArModel}.
 *
 * @author Abdullah Almasud
 */
public class ArViewModel extends AndroidViewModel {
    private MutableLiveData<List<ArModel>> mMutableArModelLiveData;
    private Context mContext;

    public ArViewModel(@NonNull Application application) {
        super(application);
        mContext = application;
    }

    /**
     * @return The {@link List} of bengali vowel of {@link ArModel}.
     */
    public List<ArModel> getBengaliVowels() {
        List<ArModel> arModels = new ArrayList<>();
        arModels.add(new ArModel(
                0, mContext.getResources().getString(R.string.bn_vowel_01),
                R.drawable.bn_vowel_01, "models/vowels_bengali/bn_vowel_01.sfb",
                new Voice(Voice.VOICE_VOWELS_BENGALI, 500, 1500)
                        .setExtraStart(500).setExtraEnd(4 * 1000),
                new Category(
                        Category.CATEGORY_VOWEL_BENGALI,
                        mContext.getResources().getString(R.string.vowel_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_vowel_01_extra))
                .setExtraPhoto(R.drawable.bn_vowel_01_extra)
        );

        arModels.add(new ArModel(
                1, mContext.getResources().getString(R.string.bn_vowel_02),
                R.drawable.bn_vowel_02, "models/vowels_bengali/bn_vowel_02.sfb",
                new Voice(Voice.VOICE_VOWELS_BENGALI, (2 * 1000) + 500, (3 * 1000) + 100)
                        .setExtraStart(500).setExtraEnd(3 * 1000),
                new Category(
                        Category.CATEGORY_VOWEL_BENGALI,
                        mContext.getResources().getString(R.string.vowel_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_vowel_02_extra))
                .setExtraPhoto(R.drawable.alphabet_mango)
        );
        arModels.add(new ArModel(
                2, mContext.getResources().getString(R.string.bn_vowel_03),
                R.drawable.bn_vowel_03, "models/vowels_bengali/bn_vowel_03.sfb",
                new Voice(Voice.VOICE_VOWELS_BENGALI, 5 * 1000, (5 * 1000) + 500)
                        .setExtraStart(500).setExtraEnd(3 * 1000),
                new Category(
                        Category.CATEGORY_VOWEL_BENGALI,
                        mContext.getResources().getString(R.string.vowel_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_vowel_03_extra))
                .setExtraPhoto(R.drawable.bn_vowel_03_extra)
        );
        arModels.add(new ArModel(
                3, mContext.getResources().getString(R.string.bn_vowel_04),
                R.drawable.bn_vowel_04, "models/vowels_bengali/bn_vowel_04.sfb",
                new Voice(Voice.VOICE_VOWELS_BENGALI, (7 * 1000) + 500, (8 * 1000) + 100)
                        .setExtraStart(500).setExtraEnd(3 * 1000),
                new Category(
                        Category.CATEGORY_VOWEL_BENGALI,
                        mContext.getResources().getString(R.string.vowel_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_vowel_04_extra))
                .setExtraPhoto(R.drawable.bn_vowel_04_extra)
        );
        arModels.add(new ArModel(
                4, mContext.getResources().getString(R.string.bn_vowel_05),
                R.drawable.bn_vowel_05, "models/vowels_bengali/bn_vowel_05.sfb",
                new Voice(Voice.VOICE_VOWELS_BENGALI, (10 * 1000) + 100, (11 * 1000) + 100)
                        .setExtraStart(500).setExtraEnd(3 * 1000),
                new Category(
                        Category.CATEGORY_VOWEL_BENGALI,
                        mContext.getResources().getString(R.string.vowel_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_vowel_05_extra))
                .setExtraPhoto(R.drawable.bn_vowel_05_extra)
        );
        arModels.add(new ArModel(
                5, mContext.getResources().getString(R.string.bn_vowel_06),
                R.drawable.bn_vowel_06, "models/vowels_bengali/bn_vowel_06.sfb",
                new Voice(Voice.VOICE_VOWELS_BENGALI, (12 * 1000) + 200, (13 * 1000) + 100)
                        .setExtraStart(500).setExtraEnd(3 * 1000),
                new Category(
                        Category.CATEGORY_VOWEL_BENGALI,
                        mContext.getResources().getString(R.string.vowel_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_vowel_06_extra))
                .setExtraPhoto(R.drawable.bn_vowel_06_extra)
        );
        arModels.add(new ArModel(
                6, mContext.getResources().getString(R.string.bn_vowel_07),
                R.drawable.bn_vowel_07, "models/vowels_bengali/bn_vowel_07.sfb",
                new Voice(Voice.VOICE_VOWELS_BENGALI, (14 * 1000) + 200, (15 * 1000) + 500)
                        .setExtraStart(500).setExtraEnd(3 * 1000),
                new Category(
                        Category.CATEGORY_VOWEL_BENGALI,
                        mContext.getResources().getString(R.string.vowel_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_vowel_07_extra))
                .setExtraPhoto(R.drawable.bn_vowel_07_extra)
        );
        arModels.add(new ArModel(
                7, mContext.getResources().getString(R.string.bn_vowel_08),
                R.drawable.bn_vowel_08, "models/vowels_bengali/bn_vowel_08.sfb",
                new Voice(Voice.VOICE_VOWELS_BENGALI, (16 * 1000) + 500, (17 * 1000) + 100)
                        .setExtraStart(500).setExtraEnd(3 * 1000),
                new Category(
                        Category.CATEGORY_VOWEL_BENGALI,
                        mContext.getResources().getString(R.string.vowel_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_vowel_08_extra))
                .setExtraPhoto(R.drawable.bn_vowel_08_extra)
        );
        arModels.add(new ArModel(
                8, mContext.getResources().getString(R.string.bn_vowel_09),
                R.drawable.bn_vowel_09, "models/vowels_bengali/bn_vowel_09.sfb",
                new Voice(Voice.VOICE_VOWELS_BENGALI, (18 * 1000) + 500, (19 * 1000) + 500)
                        .setExtraStart(500).setExtraEnd(3 * 1000),
                new Category(
                        Category.CATEGORY_VOWEL_BENGALI,
                        mContext.getResources().getString(R.string.vowel_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_vowel_09_extra))
                .setExtraPhoto(R.drawable.animal_elephant)
        );
        arModels.add(new ArModel(
                9, mContext.getResources().getString(R.string.bn_vowel_10),
                R.drawable.bn_vowel_10, "models/vowels_bengali/bn_vowel_10.sfb",
                new Voice(Voice.VOICE_VOWELS_BENGALI, (20 * 1000), (20 * 1000) + 900)
                        .setExtraStart(500).setExtraEnd(3 * 1000),
                new Category(
                        Category.CATEGORY_VOWEL_BENGALI,
                        mContext.getResources().getString(R.string.vowel_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_vowel_10_extra))
                .setExtraPhoto(R.drawable.bn_vowel_10_extra)
        );
        arModels.add(new ArModel(
                10, mContext.getResources().getString(R.string.bn_vowel_11),
                R.drawable.bn_vowel_11, "models/vowels_bengali/bn_vowel_11.sfb",
                new Voice(Voice.VOICE_VOWELS_BENGALI, (21 * 1000) + 500, (22 * 1000) + 200)
                        .setExtraStart(500).setExtraEnd(3 * 1000),
                new Category(
                        Category.CATEGORY_VOWEL_BENGALI,
                        mContext.getResources().getString(R.string.vowel_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_vowel_11_extra))
                .setExtraPhoto(R.drawable.bn_vowel_11_extra)
        );

        return arModels;
    }

    /**
     * @return The {@link List} of bengali alphabet of {@link ArModel}.
     */
    public List<ArModel> getBengaliAlphabets() {
        List<ArModel> arModels = new ArrayList<>();
        arModels.add(new ArModel(
                0, mContext.getResources().getString(R.string.bn_alphabet_01),
                R.drawable.bn_alphabet_01, "models/alphabets_bengali/bn_alphabet_01.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, 1000, 5 * 1000)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_01_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_01_extra)
        );
        arModels.add(new ArModel(
                1, mContext.getResources().getString(R.string.bn_alphabet_02),
                R.drawable.bn_alphabet_02, "models/alphabets_bengali/bn_alphabet_02.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (3 * 1000) + 300, (4 * 1000) + 100)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_02_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_02_extra)
        );
        arModels.add(new ArModel(
                2, mContext.getResources().getString(R.string.bn_alphabet_03),
                R.drawable.bn_alphabet_03, "models/alphabets_bengali/bn_alphabet_03.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (5 * 1000) + 300, (6 * 1000) + 100)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_03_extra))
                .setExtraPhoto(R.drawable.animal_cow)
        );
        arModels.add(new ArModel(
                3, mContext.getResources().getString(R.string.bn_alphabet_04),
                R.drawable.bn_alphabet_04, "models/alphabets_bengali/bn_alphabet_04.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (7 * 1000) + 200, (8 * 1000) + 200)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_04_extra))
                .setExtraPhoto(R.drawable.animal_horse)
        );
        arModels.add(new ArModel(
                4, mContext.getResources().getString(R.string.bn_alphabet_05),
                R.drawable.bn_alphabet_05, "models/alphabets_bengali/bn_alphabet_05.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (9 * 1000) + 200, (10 * 1000) + 100)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_05_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_05_extra)
        );
        arModels.add(new ArModel(
                5, mContext.getResources().getString(R.string.bn_alphabet_06),
                R.drawable.bn_alphabet_06, "models/alphabets_bengali/bn_alphabet_06.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (10 * 1000) + 800, (11 * 1000) + 500)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_06_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_06_extra)
        );
        arModels.add(new ArModel(
                6, mContext.getResources().getString(R.string.bn_alphabet_07),
                R.drawable.bn_alphabet_07, "models/alphabets_bengali/bn_alphabet_07.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (12 * 1000) + 600, (13 * 1000) + 500)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_07_extra))
                .setExtraPhoto(R.drawable.alphabet_umbrealla)
        );
        arModels.add(new ArModel(
                7, mContext.getResources().getString(R.string.bn_alphabet_08),
                R.drawable.bn_alphabet_08, "models/alphabets_bengali/bn_alphabet_08.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (14 * 1000) + 700, (15 * 1000) + 700)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_08_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_08_extra)
        );
        arModels.add(new ArModel(
                8, mContext.getResources().getString(R.string.bn_alphabet_09),
                R.drawable.bn_alphabet_09, "models/alphabets_bengali/bn_alphabet_09.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (16 * 1000), (16 * 1000) + 900)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_09_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_09_extra)
        );
        arModels.add(new ArModel(
                9, mContext.getResources().getString(R.string.bn_alphabet_10),
                R.drawable.bn_alphabet_10, "models/alphabets_bengali/bn_alphabet_10.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (18 * 1000) + 500, (18 * 1000) + 900)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_10_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_10_extra)
        );
        arModels.add(new ArModel(
                10, mContext.getResources().getString(R.string.bn_alphabet_11),
                R.drawable.bn_alphabet_11, "models/alphabets_bengali/bn_alphabet_11.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (19 * 1000) + 500, (20 * 1000) + 300)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_11_extra))
                .setExtraPhoto(R.drawable.alphabet_parrot)
        );
        arModels.add(new ArModel(
                11, mContext.getResources().getString(R.string.bn_alphabet_12),
                R.drawable.bn_alphabet_12, "models/alphabets_bengali/bn_alphabet_12.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (20 * 1000) + 200, 22 * 1000)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_12_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_12_extra)
        );
        arModels.add(new ArModel(
                12, mContext.getResources().getString(R.string.bn_alphabet_13),
                R.drawable.bn_alphabet_13, "models/alphabets_bengali/bn_alphabet_13.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (23 * 1000) + 200, 24 * 1000)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_13_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_13_extra)
        );
        arModels.add(new ArModel(
                13, mContext.getResources().getString(R.string.bn_alphabet_14),
                R.drawable.bn_alphabet_14, "models/alphabets_bengali/bn_alphabet_14.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (24 * 1000) + 500, (25 * 1000) + 500)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_14_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_14_extra)
        );
        arModels.add(new ArModel(
                14, mContext.getResources().getString(R.string.bn_alphabet_15),
                R.drawable.bn_alphabet_15, "models/alphabets_bengali/bn_alphabet_15.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (26 * 1000) + 700, (27 * 1000) + 700)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_15_extra))
                .setExtraPhoto(R.drawable.animal_deer)
        );
        arModels.add(new ArModel(
                15, mContext.getResources().getString(R.string.bn_alphabet_16),
                R.drawable.bn_alphabet_16, "models/alphabets_bengali/bn_alphabet_16.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (28 * 1000) + 500, (29 * 1000) + 700)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_16_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_16_extra)
        );
        arModels.add(new ArModel(
                16, mContext.getResources().getString(R.string.bn_alphabet_17),
                R.drawable.bn_alphabet_17, "models/alphabets_bengali/bn_alphabet_17.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (30 * 1000) + 500, (31 * 1000) + 100)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_17_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_17_extra)
        );
        arModels.add(new ArModel(
                17, mContext.getResources().getString(R.string.bn_alphabet_18),
                R.drawable.bn_alphabet_18, "models/alphabets_bengali/bn_alphabet_18.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, 32 * 100, (33 * 1000) + 100)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_18_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_18_extra)
        );
        arModels.add(new ArModel(
                18, mContext.getResources().getString(R.string.bn_alphabet_19),
                R.drawable.bn_alphabet_19, "models/alphabets_bengali/bn_alphabet_19.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (33 * 1000) + 800, (34 * 1000) + 500)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_19_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_19_extra)
        );
        arModels.add(new ArModel(
                19, mContext.getResources().getString(R.string.bn_alphabet_20),
                R.drawable.bn_alphabet_20, "models/alphabets_bengali/bn_alphabet_20.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (37 * 1000) + 100, (38 * 1000) + 100)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_20_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_20_extra)
        );
        arModels.add(new ArModel(
                20, mContext.getResources().getString(R.string.bn_alphabet_21),
                R.drawable.bn_alphabet_21, "models/alphabets_bengali/bn_alphabet_21.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (39 * 1000) + 500, (40 * 1000) + 300)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_21_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_21_extra)
        );
        arModels.add(new ArModel(
                21, mContext.getResources().getString(R.string.bn_alphabet_22),
                R.drawable.bn_alphabet_22, "models/alphabets_bengali/bn_alphabet_22.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (41 * 1000) + 500, (42 * 1000) + 200)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_22_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_22_extra)
        );
        arModels.add(new ArModel(
                22, mContext.getResources().getString(R.string.bn_alphabet_23),
                R.drawable.bn_alphabet_23, "models/alphabets_bengali/bn_alphabet_23.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (43 * 1000) + 200, (44 * 1000) + 100)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_23_extra))
                .setExtraPhoto(R.drawable.animal_cat)
        );
        arModels.add(new ArModel(
                23, mContext.getResources().getString(R.string.bn_alphabet_24),
                R.drawable.bn_alphabet_24, "models/alphabets_bengali/bn_alphabet_24.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (45 * 1000) + 500, (46 * 1000) + 200)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_24_extra))
                .setExtraPhoto(R.drawable.animal_bear)
        );
        arModels.add(new ArModel(
                24, mContext.getResources().getString(R.string.bn_alphabet_25),
                R.drawable.bn_alphabet_25, "models/alphabets_bengali/bn_alphabet_25.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, 47 * 1000, (47 * 1000) + 800)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_25_extra))
                .setExtraPhoto(R.drawable.alphabet_sweet)
        );
        arModels.add(new ArModel(
                25, mContext.getResources().getString(R.string.bn_alphabet_26),
                R.drawable.bn_alphabet_26, "models/alphabets_bengali/bn_alphabet_26.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, 39 * 1000, 40 * 1000)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_26_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_26_extra)
        );
        arModels.add(new ArModel(
                26, mContext.getResources().getString(R.string.bn_alphabet_27),
                R.drawable.bn_alphabet_27, "models/alphabets_bengali/bn_alphabet_27.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (51 * 1000) + 500, (52 * 1000))
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_27_extra))
                .setExtraPhoto(R.drawable.alphabet_queen)
        );
        arModels.add(new ArModel(
                27, mContext.getResources().getString(R.string.bn_alphabet_28),
                R.drawable.bn_alphabet_28, "models/alphabets_bengali/bn_alphabet_28.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (53 * 1000) + 200, 54 * 1000)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_28_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_28_extra)
        );
        arModels.add(new ArModel(
                28, mContext.getResources().getString(R.string.bn_alphabet_29),
                R.drawable.bn_alphabet_29, "models/alphabets_bengali/bn_alphabet_29.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (55 * 1000) + 100, (56 * 1000) + 100)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_29_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_29_extra)
        );
        arModels.add(new ArModel(
                29, mContext.getResources().getString(R.string.bn_alphabet_30),
                R.drawable.bn_alphabet_30, "models/alphabets_bengali/bn_alphabet_30.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, 57 * 1000, (58 * 1000) + 200)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_30_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_30_extra)
        );
        arModels.add(new ArModel(
                30, mContext.getResources().getString(R.string.bn_alphabet_31),
                R.drawable.bn_alphabet_31, "models/alphabets_bengali/bn_alphabet_31.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, 59 * 1000, (59 * 1000) + 900)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_31_extra))
                .setExtraPhoto(R.drawable.animal_lion)
        );
        arModels.add(new ArModel(
                31, mContext.getResources().getString(R.string.bn_alphabet_32),
                R.drawable.bn_alphabet_32, "models/alphabets_bengali/bn_alphabet_32.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, 60 * 1000, (60 * 1000) + 900)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_32_extra))
                .setExtraPhoto(R.drawable.animal_elephant)
        );
        arModels.add(new ArModel(
                32, mContext.getResources().getString(R.string.bn_alphabet_33),
                R.drawable.bn_alphabet_33, "models/alphabets_bengali/bn_alphabet_33.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (61 * 1000) + 500, (62 * 1000) + 500)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_09_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_09_extra)
        );
        arModels.add(new ArModel(
                33, mContext.getResources().getString(R.string.bn_alphabet_34),
                R.drawable.bn_alphabet_34, "models/alphabets_bengali/bn_alphabet_34.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (63 * 1000) + 500, (64 * 1000) + 400)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_34_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_34_extra)
        );
        arModels.add(new ArModel(
                34, mContext.getResources().getString(R.string.bn_alphabet_35),
                R.drawable.bn_alphabet_35, "models/alphabets_bengali/bn_alphabet_35.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (65 * 1000) + 500, (66 * 1000) + 500)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_35_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_35_extra)
        );
        arModels.add(new ArModel(
                35, mContext.getResources().getString(R.string.bn_alphabet_36),
                R.drawable.bn_alphabet_36, "models/alphabets_bengali/bn_alphabet_36.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (67 * 1000) + 600, (68 * 1000) + 500)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_36_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_36_extra)
        );
        arModels.add(new ArModel(
                36, mContext.getResources().getString(R.string.bn_alphabet_37),
                R.drawable.bn_alphabet_37, "models/alphabets_bengali/bn_alphabet_37.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (69 * 1000) + 500, (70 * 1000) + 500)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_37_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_37_extra)
        );
        arModels.add(new ArModel(
                37, mContext.getResources().getString(R.string.bn_alphabet_38),
                R.drawable.bn_alphabet_38, "models/alphabets_bengali/bn_alphabet_38.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, 71 * 1000, (72 * 1000) + 300)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_38_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_38_extra)
        );
        arModels.add(new ArModel(
                38, mContext.getResources().getString(R.string.bn_alphabet_39),
                R.drawable.bn_alphabet_39, "models/alphabets_bengali/bn_alphabet_39.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, 73 * 1000, 74 * 1000)
                        .setExtraStart(500).setExtraEnd((2 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_bengali)
                ))
                .setExtraName(mContext.getResources().getString(R.string.bn_alphabet_39_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_39_extra)
        );

        return arModels;
    }

    /**
     * @return The {@link List} of bengali alphabet of {@link ArModel}.
     */
    public List<ArModel> getBengaliNumbers() {
        List<ArModel> arModels = new ArrayList<>();
        arModels.add(new ArModel(
                0, mContext.getResources().getString(R.string.bn_number_1),
                R.drawable.bn_number_1, "models/numbers_bengali/bn_number_1.sfb",
                new Voice(Voice.VOICE_NUMBERS_BENGALI, 500, 1500),
                new Category(
                        Category.CATEGORY_NUMBER_BENGALI,
                        mContext.getResources().getString(R.string.number_bengali)
                ))
        );
        arModels.add(new ArModel(
                1, mContext.getResources().getString(R.string.bn_number_2),
                R.drawable.bn_number_2, "models/numbers_bengali/bn_number_2.sfb",
                new Voice(Voice.VOICE_NUMBERS_BENGALI, 500, 1500),
                new Category(
                        Category.CATEGORY_NUMBER_BENGALI,
                        mContext.getResources().getString(R.string.number_bengali)
                ))
        );
        arModels.add(new ArModel(
                2, mContext.getResources().getString(R.string.bn_number_3),
                R.drawable.bn_number_3, "models/numbers_bengali/bn_number_3.sfb",
                new Voice(Voice.VOICE_NUMBERS_BENGALI, 500, 1500),
                new Category(
                        Category.CATEGORY_NUMBER_BENGALI,
                        mContext.getResources().getString(R.string.number_bengali)
                ))
        );
        arModels.add(new ArModel(
                3, mContext.getResources().getString(R.string.bn_number_4),
                R.drawable.bn_number_4, "models/numbers_bengali/bn_number_4.sfb",
                new Voice(Voice.VOICE_NUMBERS_BENGALI, 500, 1500),
                new Category(
                        Category.CATEGORY_NUMBER_BENGALI,
                        mContext.getResources().getString(R.string.number_bengali)
                ))
        );
        arModels.add(new ArModel(
                4, mContext.getResources().getString(R.string.bn_number_5),
                R.drawable.bn_number_5, "models/numbers_bengali/bn_number_5.sfb",
                new Voice(Voice.VOICE_NUMBERS_BENGALI, 500, 1500),
                new Category(
                        Category.CATEGORY_NUMBER_BENGALI,
                        mContext.getResources().getString(R.string.number_bengali)
                ))
        );
        arModels.add(new ArModel(
                5, mContext.getResources().getString(R.string.bn_number_6),
                R.drawable.bn_number_6, "models/numbers_bengali/bn_number_6.sfb",
                new Voice(Voice.VOICE_NUMBERS_BENGALI, 500, 1500),
                new Category(
                        Category.CATEGORY_NUMBER_BENGALI,
                        mContext.getResources().getString(R.string.number_bengali)
                ))
        );
        arModels.add(new ArModel(
                6, mContext.getResources().getString(R.string.bn_number_7),
                R.drawable.bn_number_7, "models/numbers_bengali/bn_number_7.sfb",
                new Voice(Voice.VOICE_NUMBERS_BENGALI, 500, 1500),
                new Category(
                        Category.CATEGORY_NUMBER_BENGALI,
                        mContext.getResources().getString(R.string.number_bengali)
                ))
        );
        arModels.add(new ArModel(
                7, mContext.getResources().getString(R.string.bn_number_8),
                R.drawable.bn_number_8, "models/numbers_bengali/bn_number_8.sfb",
                new Voice(Voice.VOICE_NUMBERS_BENGALI, 500, 1500),
                new Category(
                        Category.CATEGORY_NUMBER_BENGALI,
                        mContext.getResources().getString(R.string.number_bengali)
                ))
        );
        arModels.add(new ArModel(
                8, mContext.getResources().getString(R.string.bn_number_9),
                R.drawable.bn_number_9, "models/numbers_bengali/bn_number_9.sfb",
                new Voice(Voice.VOICE_NUMBERS_BENGALI, 500, 1500),
                new Category(
                        Category.CATEGORY_NUMBER_BENGALI,
                        mContext.getResources().getString(R.string.number_bengali)
                ))
        );

        return arModels;
    }

    /**
     * @return The {@link List} of english alphabet of {@link ArModel}.
     */
    public List<ArModel> getEnglishAlphabets() {
        List<ArModel> arModels = new ArrayList<>();
        arModels.add(new ArModel(
                0, mContext.getResources().getString(R.string.capital_a),
                R.drawable.capital_a, "models/alphabets/capital_a.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (2 * 1000), (3 * 1000))
                        .setExtraStart(2 * 1000).setExtraEnd(4 * 1000),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_english)
                ))
                .setExtraName(mContext.getResources().getString(R.string.alphabet_apple))
                .setExtraPhoto(R.drawable.alphabet_apple)
        );
        arModels.add(new ArModel(
                1, mContext.getResources().getString(R.string.capital_b),
                R.drawable.capital_b, "models/alphabets/capital_b.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (5 * 1000), (5 * 1000) + 500)
                        .setExtraStart(5 * 1000).setExtraEnd((6 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_english)
                ))
                .setExtraName(mContext.getResources().getString(R.string.alphabet_ball))
                .setExtraPhoto(R.drawable.alphabet_ball)
        );
        arModels.add(new ArModel(
                2, mContext.getResources().getString(R.string.capital_c),
                R.drawable.capital_c, "models/alphabets/capital_c.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (8 * 1000), (8 * 1000) + 500)
                        .setExtraStart(8 * 1000).setExtraEnd((9 * 1000) + 200),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_english)
                ))
                .setExtraName(mContext.getResources().getString(R.string.cat))
                .setExtraPhoto(R.drawable.animal_cat)
        );
        arModels.add(new ArModel(
                3, mContext.getResources().getString(R.string.capital_d),
                R.drawable.capital_d, "models/alphabets/capital_d.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (10 * 1000) + 500, (10 * 1000) + 1000)
                        .setExtraStart((10 * 1000) + 500).setExtraEnd((12 * 1000)),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_english)
                ))
                .setExtraName(mContext.getResources().getString(R.string.dog))
                .setExtraPhoto(R.drawable.animal_dog)
        );
        arModels.add(new ArModel(
                4, mContext.getResources().getString(R.string.capital_e),
                R.drawable.capital_e, "models/alphabets/capital_e.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (13 * 1000), (13 * 1000) + 500)
                        .setExtraStart(13 * 1000).setExtraEnd((14 * 1000) + 800),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_english)
                ))
                .setExtraName(mContext.getResources().getString(R.string.elephant))
                .setExtraPhoto(R.drawable.animal_elephant)
        );
        arModels.add(new ArModel(
                5, mContext.getResources().getString(R.string.capital_f),
                R.drawable.capital_f, "models/alphabets/capital_f.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (16 * 1000), (16 * 1000) + 500)
                        .setExtraStart(16 * 1000).setExtraEnd(18 * 1000),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_english)
                ))
                .setExtraName(mContext.getResources().getString(R.string.alphabet_fish))
                .setExtraPhoto(R.drawable.alphabet_fish)
        );
        arModels.add(new ArModel(
                6, mContext.getResources().getString(R.string.capital_g),
                R.drawable.capital_g, "models/alphabets/capital_g.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (19 * 1000), (19 * 1000) + 500)
                        .setExtraStart(19 * 1000).setExtraEnd(21 * 1000),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_english)
                ))
                .setExtraName(mContext.getResources().getString(R.string.alphabet_goat))
                .setExtraPhoto(R.drawable.alphabet_goat)
        );
        arModels.add(new ArModel(
                7, mContext.getResources().getString(R.string.capital_h),
                R.drawable.capital_h, "models/alphabets/capital_h.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (22 * 1000), (22 * 1000) + 500)
                        .setExtraStart(22 * 1000).setExtraEnd((23 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_english)
                ))
                .setExtraName(mContext.getResources().getString(R.string.horse))
                .setExtraPhoto(R.drawable.animal_horse)
        );
        arModels.add(new ArModel(
                8, mContext.getResources().getString(R.string.capital_i),
                R.drawable.capital_i, "models/alphabets/capital_i.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (25 * 1000), (25 * 1000) + 500)
                        .setExtraStart(25 * 1000).setExtraEnd((26 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_english)
                ))
                .setExtraName(mContext.getResources().getString(R.string.alphabet_ice_cream))
                .setExtraPhoto(R.drawable.alphabet_ice_cream)
        );
        arModels.add(new ArModel(
                9, mContext.getResources().getString(R.string.capital_j),
                R.drawable.capital_j, "models/alphabets/capital_j.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (27 * 1000), (27 * 1000) + 500)
                        .setExtraStart(27 * 1000).setExtraEnd(29 * 1000),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_english)
                ))
                .setExtraName(mContext.getResources().getString(R.string.alphabet_jackfruit))
                .setExtraPhoto(R.drawable.alphabet_jackfruit)
        );
        arModels.add(new ArModel(
                10, mContext.getResources().getString(R.string.capital_k),
                R.drawable.capital_k, "models/alphabets/capital_k.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (30 * 1000), (30 * 1000) + 500)
                        .setExtraStart(30 * 1000).setExtraEnd((31 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_english)
                ))
                .setExtraName(mContext.getResources().getString(R.string.alphabet_kite))
                .setExtraPhoto(R.drawable.alphabet_kite)
        );
        arModels.add(new ArModel(
                11, mContext.getResources().getString(R.string.capital_l),
                R.drawable.capital_l, "models/alphabets/capital_l.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (33 * 1000), (33 * 1000) + 500)
                        .setExtraStart(33 * 1000).setExtraEnd((34 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_english)
                ))
                .setExtraName(mContext.getResources().getString(R.string.lion))
                .setExtraPhoto(R.drawable.animal_lion)
        );
        arModels.add(new ArModel(
                12, mContext.getResources().getString(R.string.capital_m),
                R.drawable.capital_m, "models/alphabets/capital_m.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (36 * 1000), (36 * 1000) + 500)
                        .setExtraStart(36 * 1000).setExtraEnd(38 * 1000),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_english)
                ))
                .setExtraName(mContext.getResources().getString(R.string.alphabet_mango))
                .setExtraPhoto(R.drawable.alphabet_mango)
        );
        arModels.add(new ArModel(
                13, mContext.getResources().getString(R.string.capital_n),
                R.drawable.capital_n, "models/alphabets/capital_n.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (39 * 1000), (39 * 1000) + 500)
                        .setExtraStart(39 * 1000).setExtraEnd((40 * 1000) + 400),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_english)
                ))
                .setExtraName(mContext.getResources().getString(R.string.alphabet_nest))
                .setExtraPhoto(R.drawable.alphabet_nest)
        );
        arModels.add(new ArModel(
                14, mContext.getResources().getString(R.string.capital_o),
                R.drawable.capital_o, "models/alphabets/capital_o.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (41 * 1000), (41 * 1000) + 500)
                        .setExtraStart((41 * 1000) + 500).setExtraEnd((43 * 1000) + 200),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_english)
                ))
                .setExtraName(mContext.getResources().getString(R.string.alphabet_orange))
                .setExtraPhoto(R.drawable.alphabet_orange)
        );
        arModels.add(new ArModel(
                15, mContext.getResources().getString(R.string.capital_p),
                R.drawable.capital_p, "models/alphabets/capital_p.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (44 * 1000), (44 * 1000) + 500)
                        .setExtraStart(44 * 1000).setExtraEnd((45 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_english)
                ))
                .setExtraName(mContext.getResources().getString(R.string.alphabet_parrot))
                .setExtraPhoto(R.drawable.alphabet_parrot)
        );
        arModels.add(new ArModel(
                16, mContext.getResources().getString(R.string.capital_q),
                R.drawable.capital_q, "models/alphabets/capital_q.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (47 * 1000), (47 * 1000) + 500)
                        .setExtraStart(47 * 1000).setExtraEnd((48 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_english)
                ))
                .setExtraName(mContext.getResources().getString(R.string.alphabet_queen))
                .setExtraPhoto(R.drawable.alphabet_queen)
        );
        arModels.add(new ArModel(
                17, mContext.getResources().getString(R.string.capital_r),
                R.drawable.capital_r, "models/alphabets/capital_r.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (50 * 1000), (50 * 1000) + 500)
                        .setExtraStart(50 * 1000).setExtraEnd(52 * 1000),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_english)
                ))
                .setExtraName(mContext.getResources().getString(R.string.alphabet__rainbow))
                .setExtraPhoto(R.drawable.alphabet_rainbow)
        );
        arModels.add(new ArModel(
                18, mContext.getResources().getString(R.string.capital_s),
                R.drawable.capital_s, "models/alphabets/capital_s.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (53 * 1000), (53 * 1000) + 500)
                        .setExtraStart(53 * 1000).setExtraEnd(55 * 1000),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_english)
                ))
                .setExtraName(mContext.getResources().getString(R.string.alphabet_sweet))
                .setExtraPhoto(R.drawable.alphabet_sweet)
        );
        arModels.add(new ArModel(
                19, mContext.getResources().getString(R.string.capital_t),
                R.drawable.capital_t, "models/alphabets/capital_t.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (55 * 1000), (55 * 1000) + 500)
                        .setExtraStart((55 * 1000) + 500).setExtraEnd((57 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_english)
                ))
                .setExtraName(mContext.getResources().getString(R.string.alphabet_tiger))
                .setExtraPhoto(R.drawable.alphabet_tiger)
        );
        arModels.add(new ArModel(
                20, mContext.getResources().getString(R.string.capital_u),
                R.drawable.capital_u, "models/alphabets/capital_u.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (58 * 1000), (58 * 1000) + 500)
                        .setExtraStart(58 * 1000).setExtraEnd((59 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_english)
                ))
                .setExtraName(mContext.getResources().getString(R.string.alphabet_umbrella))
                .setExtraPhoto(R.drawable.alphabet_umbrealla)
        );
        arModels.add(new ArModel(
                21, mContext.getResources().getString(R.string.capital_v),
                R.drawable.capital_v, "models/alphabets/capital_v.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, ((60 * 1000) + 500), (60 + 1) * 1000)
                        .setExtraStart((60 * 1000) + 500).setExtraEnd(((60 + 2) * 1000) + 200),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_english)
                ))
                .setExtraName(mContext.getResources().getString(R.string.alphabet_vegetable))
                .setExtraPhoto(R.drawable.alphabet_vegetable)
        );
        arModels.add(new ArModel(
                22, mContext.getResources().getString(R.string.capital_w),
                R.drawable.capital_w, "models/alphabets/capital_w.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, ((60 + 4) * 1000), ((60 + 4) * 1000) + 300)
                        .setExtraStart((60 + 4) * 1000).setExtraEnd(((60 + 5) * 1000) + 500),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_english)
                ))
                .setExtraName(mContext.getResources().getString(R.string.alphabet_water))
                .setExtraPhoto(R.drawable.alphabet_water_glass)
        );
        arModels.add(new ArModel(
                23, mContext.getResources().getString(R.string.capital_x),
                R.drawable.capital_x, "models/alphabets/capital_x.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, ((60 + 7) * 1000), ((60 + 7) * 1000) + 500)
                        .setExtraStart((60 + 7) * 1000).setExtraEnd((60 + 9) * 1000),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_english)
                ))
                .setExtraName(mContext.getResources().getString(R.string.alphabet_x_ray))
                .setExtraPhoto(R.drawable.alphabet_x_ray)
        );
        arModels.add(new ArModel(
                24, mContext.getResources().getString(R.string.capital_y),
                R.drawable.capital_y, "models/alphabets/capital_y.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, ((60 + 10) * 1000), ((60 + 10) * 1000) + 500)
                        .setExtraStart((60 + 10) * 1000).setExtraEnd((60 + 12) * 1000),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_english)
                ))
                .setExtraName(mContext.getResources().getString(R.string.alphabet_yak))
                .setExtraPhoto(R.drawable.alphabet_yak)
        );
        arModels.add(new ArModel(
                25, mContext.getResources().getString(R.string.capital_z),
                R.drawable.capital_z, "models/alphabets/capital_z.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, ((60 + 13) * 1000), ((60 + 13) * 1000) + 500)
                        .setExtraStart((60 + 13) * 1000).setExtraEnd((60 + 15) * 1000),
                new Category(
                        Category.CATEGORY_ALPHABET_ENGLISH,
                        mContext.getResources().getString(R.string.alphabet_english)
                ))
                .setExtraName(mContext.getResources().getString(R.string.alphabet_zebra))
                .setExtraPhoto(R.drawable.alphabet_zebra)
        );

        return arModels;
    }

    /**
     * @return The {@link List} of english number of {@link ArModel}.
     */
    public List<ArModel> getEnglishNumbers() {
        List<ArModel> arModels = new ArrayList<>();
        arModels.add(new ArModel(
                0, mContext.getResources().getString(R.string.number_one),
                R.drawable.number_1, "models/numbers/number_1.sfb",
                new Voice(Voice.VOICE_NUMBERS_ENGLISH, 600, 1800),
                new Category(
                        Category.CATEGORY_NUMBER_ENGLISH,
                        mContext.getResources().getString(R.string.number_english)
                ))
        );
        arModels.add(new ArModel(
                1, mContext.getResources().getString(R.string.number_two),
                R.drawable.number_2, "models/numbers/number_2.sfb",
                new Voice(Voice.VOICE_NUMBERS_ENGLISH, (2 * 1000) + 500, (3 * 1000) + 800),
                new Category(
                        Category.CATEGORY_NUMBER_ENGLISH,
                        mContext.getResources().getString(R.string.number_english)
                ))
        );
        arModels.add(new ArModel(
                2, mContext.getResources().getString(R.string.number_three),
                R.drawable.number_3, "models/numbers/number_3.sfb",
                new Voice(Voice.VOICE_NUMBERS_ENGLISH, 4 * 1000, (5 * 1000) + 700),
                new Category(
                        Category.CATEGORY_NUMBER_ENGLISH,
                        mContext.getResources().getString(R.string.number_english)
                ))
        );
        arModels.add(new ArModel(
                3, mContext.getResources().getString(R.string.number_four),
                R.drawable.number_4, "models/numbers/number_4.sfb",
                new Voice(Voice.VOICE_NUMBERS_ENGLISH, (6 * 1000) + 600, 8 * 1000),
                new Category(
                        Category.CATEGORY_NUMBER_ENGLISH,
                        mContext.getResources().getString(R.string.number_english)
                ))
        );
        arModels.add(new ArModel(
                4, mContext.getResources().getString(R.string.number_five),
                R.drawable.number_5, "models/numbers/number_5.sfb",
                new Voice(Voice.VOICE_NUMBERS_ENGLISH, 9 * 1000, (10 * 1000) + 500),
                new Category(
                        Category.CATEGORY_NUMBER_ENGLISH,
                        mContext.getResources().getString(R.string.number_english)
                ))
        );
        arModels.add(new ArModel(
                5, mContext.getResources().getString(R.string.number_six),
                R.drawable.number_6, "models/numbers/number_6.sfb",
                new Voice(Voice.VOICE_NUMBERS_ENGLISH, 12 * 1000, (13 * 1000) + 500),
                new Category(
                        Category.CATEGORY_NUMBER_ENGLISH,
                        mContext.getResources().getString(R.string.number_english)
                ))
        );
        arModels.add(new ArModel(
                6, mContext.getResources().getString(R.string.number_seven),
                R.drawable.number_7, "models/numbers/number_7.sfb",
                new Voice(Voice.VOICE_NUMBERS_ENGLISH, 14 * 1000, (16 * 1000) + 500),
                new Category(
                        Category.CATEGORY_NUMBER_ENGLISH,
                        mContext.getResources().getString(R.string.number_english)
                ))
        );
        arModels.add(new ArModel(
                7, mContext.getResources().getString(R.string.number_eight),
                R.drawable.number_8, "models/numbers/number_8.sfb",
                new Voice(Voice.VOICE_NUMBERS_ENGLISH, (16 * 1000) + 200, (17 * 1000) + 200),
                new Category(
                        Category.CATEGORY_NUMBER_ENGLISH,
                        mContext.getResources().getString(R.string.number_english)
                ))
        );
        arModels.add(new ArModel(
                8, mContext.getResources().getString(R.string.number_nine),
                R.drawable.number_9, "models/numbers/number_9.sfb",
                new Voice(Voice.VOICE_NUMBERS_ENGLISH, (18 * 1000) + 400, 20 * 1000),
                new Category(
                        Category.CATEGORY_NUMBER_ENGLISH,
                        mContext.getResources().getString(R.string.number_english)
                ))
        );

        return arModels;
    }

    /**
     * @return The {@link List} of english animal of {@link ArModel}.
     */
    public List<ArModel> getAnimals() {
        List<ArModel> arModels = new ArrayList<>();
        arModels.add(new ArModel(
                0, mContext.getResources().getString(R.string.bear),
                R.drawable.animal_bear, "models/animals/bear.sfb",
                new Voice(Voice.VOICE_ANIMALS_ENGLISH, 500, 2 * 1000),
                new Category(
                        Category.CATEGORY_ANIMAL_ENGLISH,
                        mContext.getResources().getString(R.string.animal_english)
                ))
        );
        arModels.add(new ArModel(
                1, mContext.getResources().getString(R.string.cat),
                R.drawable.animal_cat, "models/animals/cat.sfb",
                new Voice(Voice.VOICE_ANIMALS_ENGLISH, (2 * 1000) + 500, (3 * 1000) + 700),
                new Category(
                        Category.CATEGORY_ANIMAL_ENGLISH,
                        mContext.getResources().getString(R.string.animal_english)
                ))
        );
        arModels.add(new ArModel(
                2, mContext.getResources().getString(R.string.cow),
                R.drawable.animal_cow, "models/animals/cow.sfb",
                new Voice(Voice.VOICE_ANIMALS_ENGLISH, (4 * 1000) + 500, 6 * 1000),
                new Category(
                        Category.CATEGORY_ANIMAL_ENGLISH,
                        mContext.getResources().getString(R.string.animal_english)
                ))
        );
        arModels.add(new ArModel(
                3, mContext.getResources().getString(R.string.deer),
                R.drawable.animal_deer, "models/animals/deer.sfb",
                new Voice(Voice.VOICE_ANIMALS_ENGLISH, 7 * 1000, 8 * 1000),
                new Category(
                        Category.CATEGORY_ANIMAL_ENGLISH,
                        mContext.getResources().getString(R.string.animal_english)
                ))
        );
        arModels.add(new ArModel(
                4, mContext.getResources().getString(R.string.dog),
                R.drawable.animal_dog, "models/animals/dog.sfb",
                new Voice(Voice.VOICE_ANIMALS_ENGLISH, 9 * 1000, 10 * 1000),
                new Category(
                        Category.CATEGORY_ANIMAL_ENGLISH,
                        mContext.getResources().getString(R.string.animal_english)
                ))
        );
        arModels.add(new ArModel(
                5, mContext.getResources().getString(R.string.elephant),
                R.drawable.animal_elephant, "models/animals/elephant.sfb",
                new Voice(Voice.VOICE_ANIMALS_ENGLISH, 11 * 1000, 12 * 1000),
                new Category(
                        Category.CATEGORY_ANIMAL_ENGLISH,
                        mContext.getResources().getString(R.string.animal_english)
                ))
        );
        arModels.add(new ArModel(
                6, mContext.getResources().getString(R.string.hippopotamus),
                R.drawable.animal_hippopotamus, "models/animals/hippopotamus.sfb",
                new Voice(Voice.VOICE_ANIMALS_ENGLISH, 13 * 1000, (14 * 1000) + 500),
                new Category(
                        Category.CATEGORY_ANIMAL_ENGLISH,
                        mContext.getResources().getString(R.string.animal_english)
                ))
        );
        arModels.add(new ArModel(
                7, mContext.getResources().getString(R.string.horse),
                R.drawable.animal_horse, "models/animals/horse.sfb",
                new Voice(Voice.VOICE_ANIMALS_ENGLISH, 15 * 1000, 16 * 1000),
                new Category(
                        Category.CATEGORY_ANIMAL_ENGLISH,
                        mContext.getResources().getString(R.string.animal_english)
                ))
        );
        arModels.add(new ArModel(
                8, mContext.getResources().getString(R.string.lion),
                R.drawable.animal_lion, "models/animals/lion.sfb",
                new Voice(Voice.VOICE_ANIMALS_ENGLISH, (16 * 1000) + 500, 18 * 1000),
                new Category(
                        Category.CATEGORY_ANIMAL_ENGLISH,
                        mContext.getResources().getString(R.string.animal_english)
                ))
        );

        return arModels;
    }

    /**
     * @return A {@link LiveData} of {@link List} of {@link ArModel} to be observed.
     */
    public LiveData<List<ArModel>> getArModelLivedData(int modelCategory) {
        if (mMutableArModelLiveData == null) {
            mMutableArModelLiveData = new MutableLiveData<>();

            // The list of items (ArModel) to be set in mMutableArModelLiveData
            List<ArModel> arModels = new ArrayList<>();
            // Data should be fetched from cloud but we use static data here
            switch (modelCategory) {
                case Category.CATEGORY_VOWEL_BENGALI:
                    arModels = getBengaliVowels();
                    break;
                case Category.CATEGORY_ALPHABET_BENGALI:
                    arModels = getBengaliAlphabets();
                    break;
                case Category.CATEGORY_NUMBER_BENGALI:
                    arModels = getBengaliNumbers();
                    break;
                case Category.CATEGORY_ALPHABET_ENGLISH:
                    arModels = getEnglishAlphabets();
                    break;
                case Category.CATEGORY_NUMBER_ENGLISH:
                    arModels = getEnglishNumbers();
                    break;
                case Category.CATEGORY_ANIMAL_ENGLISH:
                    arModels = getAnimals();
                    break;
            }

            // Set the value of mMutableLiveData
            mMutableArModelLiveData.setValue(arModels);
        }

        return mMutableArModelLiveData;
    }
}
