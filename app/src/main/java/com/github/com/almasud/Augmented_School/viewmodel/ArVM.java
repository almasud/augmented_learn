package com.github.com.almasud.Augmented_School.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.com.almasud.Augmented_School.R;
import com.github.com.almasud.Augmented_School.model.entity.ArModel;
import com.github.com.almasud.Augmented_School.model.entity.Language;
import com.github.com.almasud.Augmented_School.model.entity.Subject;
import com.github.com.almasud.Augmented_School.model.entity.Voice;

import java.util.ArrayList;
import java.util.List;

/**
 * An {@link AndroidViewModel} for the {@link ArModel}.
 *
 * @author Abdullah Almasud
 */
public class ArVM extends AndroidViewModel {
    private Application mApplication;
    private MutableLiveData<List<ArModel>> mMutableLiveDataListArModelByLanguage,
            mMutableLiveDataListArModelBySubject;
    private MutableLiveData<List<Language>> mMutableLiveDataListLanguage;
    private MutableLiveData<List<Subject>> mMutableLiveDataListSubject;

    public ArVM(@NonNull Application application) {
        super(application);
        mApplication = application;
    }

    /**
     * @return The {@link List} of bengali vowel of {@link ArModel}.
     */
    private List<ArModel> getBengaliVowels() {
        Subject subject = new Subject(
                Subject.VOWEL_BENGALI, mApplication.getResources().getString(R.string.vowel_bengali),
                R.drawable.vowel_bengali, new Language(Language.BENGALI, mApplication.getResources().getString(R.string.language_bengali)),
                Subject.DOWNLOAD_URL_VOWELS_BENGALI
        );

        List<ArModel> arModels = new ArrayList<>();
        arModels.add(new ArModel(
                0, mApplication.getResources().getString(R.string.bn_vowel_01),
                R.drawable.bn_vowel_01, "bn_vowel_01.sfb",
                new Voice(Voice.VOICE_VOWELS_BENGALI, 3500,  4400)
                        .setExtraStart(1000 + 100).setExtraEnd((3 * 1000) + 900), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_vowel_01_extra))
                .setExtraPhoto(R.drawable.bn_vowel_01_extra)
        );

        arModels.add(new ArModel(
                1, mApplication.getResources().getString(R.string.bn_vowel_02),
                R.drawable.bn_vowel_02, "bn_vowel_02.sfb",
                new Voice(Voice.VOICE_VOWELS_BENGALI, 6300, 7500)
                        .setExtraStart((4 * 1000) + 300).setExtraEnd((6 * 1000) + 700), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_vowel_02_extra))
                .setExtraPhoto(R.drawable.alphabet_mango)
        );
        arModels.add(new ArModel(
                2, mApplication.getResources().getString(R.string.bn_vowel_03),
                R.drawable.bn_vowel_03, "bn_vowel_03.sfb",
                new Voice(Voice.VOICE_VOWELS_BENGALI,  9500, 10700)
                        .setExtraStart(7*1000).setExtraEnd((9 * 1000) + 300), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_vowel_03_extra))
                .setExtraPhoto(R.drawable.bn_vowel_03_extra)
        );
        arModels.add(new ArModel(
                3, mApplication.getResources().getString(R.string.bn_vowel_04),
                R.drawable.bn_vowel_04, "bn_vowel_04.sfb",
                new Voice(Voice.VOICE_VOWELS_BENGALI, 11900, 13300)
                        .setExtraStart((9 * 1000) + 800).setExtraEnd((11 * 1000) + 900), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_vowel_04_extra))
                .setExtraPhoto(R.drawable.bn_vowel_04_extra)
        );
        arModels.add(new ArModel(
                4, mApplication.getResources().getString(R.string.bn_vowel_05),
                R.drawable.bn_vowel_05, "bn_vowel_05.sfb",
                new Voice(Voice.VOICE_VOWELS_BENGALI, 15800, 16900)
                        .setExtraStart((12 * 1000) + 500).setExtraEnd((14 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_vowel_05_extra))
                .setExtraPhoto(R.drawable.bn_vowel_05_extra)
        );
        arModels.add(new ArModel(
                5, mApplication.getResources().getString(R.string.bn_vowel_06),
                R.drawable.bn_vowel_06, "bn_vowel_06.sfb",
                new Voice(Voice.VOICE_VOWELS_BENGALI, 18500, 19500)
                        .setExtraStart(15 * 1000).setExtraEnd(17 * 1000), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_vowel_06_extra))
                .setExtraPhoto(R.drawable.bn_vowel_06_extra)
        );
        arModels.add(new ArModel(
                6, mApplication.getResources().getString(R.string.bn_vowel_07),
                R.drawable.bn_vowel_07, "bn_vowel_07.sfb",
                new Voice(Voice.VOICE_VOWELS_BENGALI, 21000, 22300)
                        .setExtraStart(18 * 1000) .setExtraEnd((20 * 1000)+500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_vowel_07_extra))
                .setExtraPhoto(R.drawable.bn_vowel_07_extra)
        );
        arModels.add(new ArModel(
                7, mApplication.getResources().getString(R.string.bn_vowel_08),
                R.drawable.bn_vowel_08, "bn_vowel_08.sfb",
                new Voice(Voice.VOICE_VOWELS_BENGALI, 24000, 25200)
                        .setExtraStart(21 * 1000).setExtraEnd((23 * 1000) + 300), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_vowel_08_extra))
                .setExtraPhoto(R.drawable.bn_vowel_08_extra)
        );
        arModels.add(new ArModel(
                8, mApplication.getResources().getString(R.string.bn_vowel_09),
                R.drawable.bn_vowel_09, "bn_vowel_09.sfb",
                new Voice(Voice.VOICE_VOWELS_BENGALI, 28000, 29000)
                        .setExtraStart((23 * 1000) + 900).setExtraEnd(26 * 1000), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_vowel_09_extra))
                .setExtraPhoto(R.drawable.animal_elephant)
        );
        arModels.add(new ArModel(
                9, mApplication.getResources().getString(R.string.bn_vowel_10),
                R.drawable.bn_vowel_10, "bn_vowel_10.sfb",
                new Voice(Voice.VOICE_VOWELS_BENGALI, 30800, 31900)
                        .setExtraStart(26 * 1000).setExtraEnd((28* 1000) +400), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_vowel_10_extra))
                .setExtraPhoto(R.drawable.bn_vowel_10_extra)
        );
        arModels.add(new ArModel(
                10, mApplication.getResources().getString(R.string.bn_vowel_11),
                R.drawable.bn_vowel_11, "bn_vowel_11.sfb",
                new Voice(Voice.VOICE_VOWELS_BENGALI, 33200, 34800)
                        .setExtraStart((28 * 1000) + 500).setExtraEnd((30 * 1000) + 800), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_vowel_11_extra))
                .setExtraPhoto(R.drawable.bn_vowel_11_extra)
        );

        return arModels;
    }

    /**
     * @return The {@link List} of bengali alphabet of {@link ArModel}.
     */
    private List<ArModel> getBengaliAlphabets() {
        Subject subject = new Subject(
                Subject.ALPHABET_BENGALI, mApplication.getResources().getString(R.string.alphabet_bengali),
                R.drawable.alphabet_bengali, new Language(Language.BENGALI, mApplication.getResources().getString(R.string.language_bengali)),
                Subject.DOWNLOAD_URL_ALPHABETS_BENGALI
        );

        List<ArModel> arModels = new ArrayList<>();
        arModels.add(new ArModel(
                0, mApplication.getResources().getString(R.string.bn_alphabet_01),
                R.drawable.bn_alphabet_01, "bn_alphabet_01.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, 2000, (2 * 1000) +900)
                        .setExtraStart(2 * 1000).setExtraEnd((4 * 1000) + 300), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_01_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_01_extra)
        );
        arModels.add(new ArModel(
                1, mApplication.getResources().getString(R.string.bn_alphabet_02),
                R.drawable.bn_alphabet_02, "bn_alphabet_02.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (4 * 1000) + 100, (5 * 1000) + 100)
                        .setExtraStart(5 * 1000).setExtraEnd((7 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_02_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_02_extra)
        );
        arModels.add(new ArModel(
                2, mApplication.getResources().getString(R.string.bn_alphabet_03),
                R.drawable.bn_alphabet_03, "bn_alphabet_03.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (6 * 1000) + 100, (7 * 1000) + 100)
                        .setExtraStart(8 * 1000).setExtraEnd((10 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_03_extra))
                .setExtraPhoto(R.drawable.animal_cow)
        );
        arModels.add(new ArModel(
                3, mApplication.getResources().getString(R.string.bn_alphabet_04),
                R.drawable.bn_alphabet_04, "bn_alphabet_04.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (8 * 1000), (9 * 1000) + 100)
                        .setExtraStart((10 * 1000) + 500).setExtraEnd(13 * 1000), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_04_extra))
                .setExtraPhoto(R.drawable.animal_horse)
        );
        arModels.add(new ArModel(
                4, mApplication.getResources().getString(R.string.bn_alphabet_05),
                R.drawable.bn_alphabet_05, "bn_alphabet_05.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (10 * 1000) + 200, (11 * 1000) + 200)
                        .setExtraStart( (13 * 1000) + 500).setExtraEnd((15 * 1000) + 700), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_05_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_05_extra)
        );
        arModels.add(new ArModel(
                5, mApplication.getResources().getString(R.string.bn_alphabet_06),
                R.drawable.bn_alphabet_06, "bn_alphabet_06.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (12 * 1000) + 200, (13 * 1000) + 300)
                        .setExtraStart(16 * 1000).setExtraEnd((19 * 1000) + 200), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_06_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_06_extra)
        );
        arModels.add(new ArModel(
                6, mApplication.getResources().getString(R.string.bn_alphabet_07),
                R.drawable.bn_alphabet_07, "bn_alphabet_07.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (14 * 1000) + 300, (15 * 1000) + 500)
                        .setExtraStart((18 * 1000) + 800).setExtraEnd((21 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_07_extra))
                .setExtraPhoto(R.drawable.alphabet_umbrealla)
        );
        arModels.add(new ArModel(
                7, mApplication.getResources().getString(R.string.bn_alphabet_08),
                R.drawable.bn_alphabet_08, "bn_alphabet_08.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (16 * 1000) + 200, (17 * 1000) + 700)
                        .setExtraStart(22 * 1000).setExtraEnd((24 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_08_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_08_extra)
        );
        arModels.add(new ArModel(
                8, mApplication.getResources().getString(R.string.bn_alphabet_09),
                R.drawable.bn_alphabet_09, "bn_alphabet_09.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (18 * 1000) + 500, (19 * 1000) + 600)
                        .setExtraStart(25 * 1000).setExtraEnd((27 * 1000) + 200), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_09_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_09_extra)
        );
        arModels.add(new ArModel(
                9, mApplication.getResources().getString(R.string.bn_alphabet_10),
                R.drawable.bn_alphabet_10, "bn_alphabet_10.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (20 * 1000) + 200, (21 * 1000) + 300)
                        .setExtraStart((27 * 1000) + 500).setExtraEnd((29 * 1000) + 900), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_10_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_10_extra)
        );
        arModels.add(new ArModel(
                10, mApplication.getResources().getString(R.string.bn_alphabet_11),
                R.drawable.bn_alphabet_11, "bn_alphabet_11.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (22 * 1000), (23 * 1000) + 100)
                        .setExtraStart(30 * 1000).setExtraEnd((32 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_11_extra))
                .setExtraPhoto(R.drawable.alphabet_parrot)
        );
        arModels.add(new ArModel(
                11, mApplication.getResources().getString(R.string.bn_alphabet_12),
                R.drawable.bn_alphabet_12, "bn_alphabet_12.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (24 * 1000) + 200, (25* 1000) + 100)
                        .setExtraStart(33 * 1000).setExtraEnd((35 * 1000) + 800), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_12_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_12_extra)
        );
        arModels.add(new ArModel(
                12, mApplication.getResources().getString(R.string.bn_alphabet_13),
                R.drawable.bn_alphabet_13, "bn_alphabet_13.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (26 * 1000) + 200, (27 * 1000) + 300)
                        .setExtraStart(36 * 1000).setExtraEnd((38 * 1000) + 800), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_13_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_13_extra)
        );
        arModels.add(new ArModel(
                13, mApplication.getResources().getString(R.string.bn_alphabet_14),
                R.drawable.bn_alphabet_14, "bn_alphabet_14.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (28 * 1000) + 300, (29 * 1000) + 300)
                        .setExtraStart(39 * 1000).setExtraEnd((41 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_14_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_14_extra)
        );
        arModels.add(new ArModel(
                14, mApplication.getResources().getString(R.string.bn_alphabet_15),
                R.drawable.bn_alphabet_15, "bn_alphabet_15.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (30 * 1000) + 300, (31 * 1000) + 600)
                        .setExtraStart(42 * 1000).setExtraEnd((43 * 1000) + 800), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_15_extra))
                .setExtraPhoto(R.drawable.animal_deer)
        );
        arModels.add(new ArModel(
                15, mApplication.getResources().getString(R.string.bn_alphabet_16),
                R.drawable.bn_alphabet_16, "bn_alphabet_16.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (32 * 1000) + 300, (33 * 1000) + 300)
                        .setExtraStart(44 * 1000).setExtraEnd((46 * 1000) + 700), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_16_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_16_extra)
        );
        arModels.add(new ArModel(
                16, mApplication.getResources().getString(R.string.bn_alphabet_17),
                R.drawable.bn_alphabet_17, "bn_alphabet_17.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (34 * 1000) + 200, (35 * 1000) + 300)
                        .setExtraStart(47 * 1000).setExtraEnd((49 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_17_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_17_extra)
        );
        arModels.add(new ArModel(
                17, mApplication.getResources().getString(R.string.bn_alphabet_18),
                R.drawable.bn_alphabet_18, "bn_alphabet_18.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, 36* 1000, (37 * 1000) + 100)
                        .setExtraStart(50 * 1000).setExtraEnd((52 * 1000) + 300), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_18_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_18_extra)
        );
        arModels.add(new ArModel(
                18, mApplication.getResources().getString(R.string.bn_alphabet_19),
                R.drawable.bn_alphabet_19, "bn_alphabet_19.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (38 * 1000) + 100, (39 * 1000) + 200)
                        .setExtraStart((52 * 1000) + 500).setExtraEnd((55 * 1000) + 100), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_19_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_19_extra)
        );
        arModels.add(new ArModel(
                19, mApplication.getResources().getString(R.string.bn_alphabet_20),
                R.drawable.bn_alphabet_20, "bn_alphabet_20.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (40 * 1000) + 100, (41 * 1000) + 100)
                        .setExtraStart((55 * 1000) + 500).setExtraEnd((58 * 1000) + 200), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_20_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_20_extra)
        );
        arModels.add(new ArModel(
                20, mApplication.getResources().getString(R.string.bn_alphabet_21),
                R.drawable.bn_alphabet_21, "bn_alphabet_21.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, 42 * 1000, (43 * 1000) + 200)
                        .setExtraStart((58 *1000) + 500).setExtraEnd((60 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_21_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_21_extra)
        );
        arModels.add(new ArModel(
                21, mApplication.getResources().getString(R.string.bn_alphabet_22),
                R.drawable.bn_alphabet_22, "models/alphabets_bengali/bn_alphabet_22.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (43 * 1000) + 800, 45 * 1000)
                        .setExtraStart(61 * 1000).setExtraEnd((63 * 1000) + 200), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_22_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_22_extra)
        );
        arModels.add(new ArModel(
                22, mApplication.getResources().getString(R.string.bn_alphabet_23),
                R.drawable.bn_alphabet_23, "bn_alphabet_23.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (46 * 1000), (47 * 1000) + 100)
                        .setExtraStart(64 * 1000).setExtraEnd((66 * 1000) + 100), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_23_extra))
                .setExtraPhoto(R.drawable.animal_cat)
        );
        arModels.add(new ArModel(
                23, mApplication.getResources().getString(R.string.bn_alphabet_24),
                R.drawable.bn_alphabet_24, "bn_alphabet_24.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (48 * 1000), (49 * 1000) + 100)
                        .setExtraStart((66 * 1000) + 500).setExtraEnd((68 * 1000) + 900), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_24_extra))
                .setExtraPhoto(R.drawable.animal_bear)
        );
        arModels.add(new ArModel(
                24, mApplication.getResources().getString(R.string.bn_alphabet_25),
                R.drawable.bn_alphabet_25, "bn_alphabet_25.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, 50 * 1000, (51 * 1000) + 100)
                        .setExtraStart((69 * 1000) + 500).setExtraEnd((71 * 1000) + 900), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_25_extra))
                .setExtraPhoto(R.drawable.alphabet_sweet)
        );
        arModels.add(new ArModel(
                25, mApplication.getResources().getString(R.string.bn_alphabet_26),
                R.drawable.bn_alphabet_26, "bn_alphabet_26.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (52 * 1000) + 200, (53* 1000) + 300)
                        .setExtraStart(72 * 1000).setExtraEnd((74 * 1000) + 800), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_26_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_26_extra)
        );
        arModels.add(new ArModel(
                26, mApplication.getResources().getString(R.string.bn_alphabet_27),
                R.drawable.bn_alphabet_27, "bn_alphabet_27.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (54 * 1000) + 100, (55 * 1000) + 200)
                        .setExtraStart(75 * 1000).setExtraEnd((77 * 1000) + 100), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_27_extra))
                .setExtraPhoto(R.drawable.alphabet_queen)
        );
        arModels.add(new ArModel(
                27, mApplication.getResources().getString(R.string.bn_alphabet_28),
                R.drawable.bn_alphabet_28, "bn_alphabet_28.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (56 * 1000) + 200, (57 * 1000) + 200)
                        .setExtraStart((77 * 1000) + 500).setExtraEnd((79 * 1000) + 800), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_28_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_28_extra)
        );
        arModels.add(new ArModel(
                28, mApplication.getResources().getString(R.string.bn_alphabet_29),
                R.drawable.bn_alphabet_29, "bn_alphabet_29.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (58 * 1000) + 100, (59 * 1000) + 500)
                        .setExtraStart((80 * 1000) + 200).setExtraEnd((82 * 1000) + 700), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_29_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_29_extra)
        );
        arModels.add(new ArModel(
                29, mApplication.getResources().getString(R.string.bn_alphabet_30),
                R.drawable.bn_alphabet_30, "bn_alphabet_30.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, 60 * 1000, (61 * 1000) + 400)
                        .setExtraStart(83 * 1000).setExtraEnd((85 * 1000) + 400), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_30_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_30_extra)
        );
        arModels.add(new ArModel(
                30, mApplication.getResources().getString(R.string.bn_alphabet_31),
                R.drawable.bn_alphabet_31, "bn_alphabet_31.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (61 * 1000) + 800, (63 * 1000) + 200)
                        .setExtraStart((85 * 1000) + 800).setExtraEnd((87 * 1000) + 900), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_31_extra))
                .setExtraPhoto(R.drawable.animal_lion)
        );
        arModels.add(new ArModel(
                31, mApplication.getResources().getString(R.string.bn_alphabet_32),
                R.drawable.bn_alphabet_32, "bn_alphabet_32.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, 64 * 1000, (65 * 1000) + 100)
                        .setExtraStart((88 * 1000)+ 500).setExtraEnd((90 * 1000) + 700), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_32_extra))
                .setExtraPhoto(R.drawable.animal_elephant)
        );
        arModels.add(new ArModel(
                32, mApplication.getResources().getString(R.string.bn_alphabet_33),
                R.drawable.bn_alphabet_33, "bn_alphabet_33.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (66 * 1000), (67 * 1000) + 800)
                        .setExtraStart(91 * 1000).setExtraEnd((93 * 1000) + 900), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_09_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_09_extra)
        );
        arModels.add(new ArModel(
                33, mApplication.getResources().getString(R.string.bn_alphabet_34),
                R.drawable.bn_alphabet_34, "bn_alphabet_34.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (68 * 1000) + 200, 70 * 1000)
                        .setExtraStart(94 * 1000).setExtraEnd((96 * 1000) + 800), subject
                ).setExtraName("আষাঢ়")
                .setExtraPhoto(R.drawable.bn_alphabet_34_extra)
        );
        arModels.add(new ArModel(
                34, mApplication.getResources().getString(R.string.bn_alphabet_35),
                R.drawable.bn_alphabet_35, "bn_alphabet_35.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI,  72 * 1000, (73 * 1000) + 500)
                        .setExtraStart(97 * 1000).setExtraEnd((99 * 1000) + 200), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_35_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_35_extra)
        );
        arModels.add(new ArModel(
                35, mApplication.getResources().getString(R.string.bn_alphabet_36),
                R.drawable.bn_alphabet_36, "bn_alphabet_36.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, 75 * 1000, (76 * 1000) + 500)
                        .setExtraStart(100 * 1000).setExtraEnd((101 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_36_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_36_extra)
        );
        arModels.add(new ArModel(
                36, mApplication.getResources().getString(R.string.bn_alphabet_37),
                R.drawable.bn_alphabet_37, "bn_alphabet_37.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (77 * 1000) + 500, (78 * 1000) + 700)
                        .setExtraStart((102 * 1000) + 500).setExtraEnd((104 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_37_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_37_extra)
        );
        arModels.add(new ArModel(
                37, mApplication.getResources().getString(R.string.bn_alphabet_38),
                R.drawable.bn_alphabet_38, "bn_alphabet_38.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, 80 * 1000, (81 * 1000) + 400)
                        .setExtraStart(105 * 1000).setExtraEnd((106 * 1000) + 600), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_38_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_38_extra)
        );
        arModels.add(new ArModel(
                38, mApplication.getResources().getString(R.string.bn_alphabet_39),
                R.drawable.bn_alphabet_39, "bn_alphabet_39.sfb",
                new Voice(Voice.VOICE_ALPHABETS_BENGALI, (82 * 1000) + 500, (83 * 1000) +900)
                        .setExtraStart((107 * 1000) + 100).setExtraEnd(110 * 1000), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_39_extra))
                .setExtraPhoto(R.drawable.bn_alphabet_39_extra)
        );

        return arModels;
    }

    /**
     * @return The {@link List} of bengali alphabet of {@link ArModel}.
     */
    private List<ArModel> getBengaliNumbers() {
        Subject subject = new Subject(
                Subject.NUMBER_BENGALI, mApplication.getResources().getString(R.string.number_bengali),
                R.drawable.number_bengali, new Language(Language.BENGALI, mApplication.getResources().getString(R.string.language_bengali)),
                Subject.DOWNLOAD_URL_NUMBERS_BENGALI
        );

        List<ArModel> arModels = new ArrayList<>();
        arModels.add(new ArModel(
                0, mApplication.getResources().getString(R.string.bn_number_1),
                R.drawable.bn_number_1, "bn_number_1.sfb",
                new Voice(Voice.VOICE_NUMBERS_BENGALI, 2300, 3200), subject
                ));
        arModels.add(new ArModel(
                1, mApplication.getResources().getString(R.string.bn_number_2),
                R.drawable.bn_number_2, "bn_number_2.sfb",
                new Voice(Voice.VOICE_NUMBERS_BENGALI, 4800, 5500), subject
        ));
        arModels.add(new ArModel(
                2, mApplication.getResources().getString(R.string.bn_number_3),
                R.drawable.bn_number_3, "bn_number_3.sfb",
                new Voice(Voice.VOICE_NUMBERS_BENGALI, 7300, 8300), subject
        ));
        arModels.add(new ArModel(
                3, mApplication.getResources().getString(R.string.bn_number_4),
                R.drawable.bn_number_4, "bn_number_4.sfb",
                new Voice(Voice.VOICE_NUMBERS_BENGALI, 9500, 10500), subject
        ));
        arModels.add(new ArModel(
                4, mApplication.getResources().getString(R.string.bn_number_5),
                R.drawable.bn_number_5, "bn_number_5.sfb",
                new Voice(Voice.VOICE_NUMBERS_BENGALI, 13000, 14000), subject
        ));
        arModels.add(new ArModel(
                5, mApplication.getResources().getString(R.string.bn_number_6),
                R.drawable.bn_number_6, "bn_number_6.sfb",
                new Voice(Voice.VOICE_NUMBERS_BENGALI, 15500, 16500), subject
        ));
        arModels.add(new ArModel(
                6, mApplication.getResources().getString(R.string.bn_number_7),
                R.drawable.bn_number_7, "bn_number_7.sfb",
                new Voice(Voice.VOICE_NUMBERS_BENGALI, 18000, 19100), subject
        ));
        arModels.add(new ArModel(
                7, mApplication.getResources().getString(R.string.bn_number_8),
                R.drawable.bn_number_8, "bn_number_8.sfb",
                new Voice(Voice.VOICE_NUMBERS_BENGALI, 21200, 22000), subject
        ));
        arModels.add(new ArModel(
                8, mApplication.getResources().getString(R.string.bn_number_9),
                R.drawable.bn_number_9, "bn_number_9.sfb",
                new Voice(Voice.VOICE_NUMBERS_BENGALI, 24100, 25500), subject
        ));

        return arModels;
    }

    /**
     * @return The {@link List} of english alphabet of {@link ArModel}.
     */
    private List<ArModel> getEnglishAlphabets() {
        Subject subject = new Subject(
                Subject.ALPHABET_ENGLISH, mApplication.getResources().getString(R.string.alphabet_english),
                R.drawable.alphabet, new Language(Language.ENGLISH, mApplication.getResources().getString(R.string.language_english)),
                Subject.DOWNLOAD_URL_ALPHABETS_ENGLISH
        );

        List<ArModel> arModels = new ArrayList<>();
        arModels.add(new ArModel(
                0, mApplication.getResources().getString(R.string.capital_a),
                R.drawable.capital_a, "capital_a.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, 1300, 2400)
                        .setExtraStart(2 * 1000).setExtraEnd(4 * 1000), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_apple))
                .setExtraPhoto(R.drawable.alphabet_apple)
        );
        arModels.add(new ArModel(
                1, mApplication.getResources().getString(R.string.capital_b),
                R.drawable.capital_b, "capital_b.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (3 * 1000), (4 * 1000) + 300)
                        .setExtraStart(5 * 1000).setExtraEnd((6 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_ball))
                .setExtraPhoto(R.drawable.alphabet_ball)
        );
        arModels.add(new ArModel(
                2, mApplication.getResources().getString(R.string.capital_c),
                R.drawable.capital_c, "capital_c.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (5 * 1000) + 300, (6 * 1000) + 500)
                        .setExtraStart(8 * 1000).setExtraEnd((9 * 1000) + 200), subject
                ).setExtraName(mApplication.getResources().getString(R.string.cat))
                .setExtraPhoto(R.drawable.animal_cat)
        );
        arModels.add(new ArModel(
                3, mApplication.getResources().getString(R.string.capital_d),
                R.drawable.capital_d, "capital_d.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (7 * 1000) + 500, (8 * 1000) + 600)
                        .setExtraStart((10 * 1000) + 500).setExtraEnd((12 * 1000)), subject
                ).setExtraName(mApplication.getResources().getString(R.string.dog))
                .setExtraPhoto(R.drawable.animal_dog)
        );
        arModels.add(new ArModel(
                4, mApplication.getResources().getString(R.string.capital_e),
                R.drawable.capital_e, "capital_e.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (9 * 1000) + 600, (10 * 1000) + 800)
                        .setExtraStart(13 * 1000).setExtraEnd((14 * 1000) + 800), subject
                ).setExtraName(mApplication.getResources().getString(R.string.elephant))
                .setExtraPhoto(R.drawable.animal_elephant)
        );
        arModels.add(new ArModel(
                5, mApplication.getResources().getString(R.string.capital_f),
                R.drawable.capital_f, "capital_f.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (11 * 1000) + 300, (12 * 1000) + 500)
                        .setExtraStart(16 * 1000).setExtraEnd((17 * 1000) + 700), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_fish))
                .setExtraPhoto(R.drawable.alphabet_fish)
        );
        arModels.add(new ArModel(
                6, mApplication.getResources().getString(R.string.capital_g),
                R.drawable.capital_g, "capital_g.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (14 * 1000) + 200, (15 * 1000) + 500)
                        .setExtraStart(19 * 1000).setExtraEnd(21 * 1000), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_goat))
                .setExtraPhoto(R.drawable.alphabet_goat)
        );
        arModels.add(new ArModel(
                7, mApplication.getResources().getString(R.string.capital_h),
                R.drawable.capital_h, "capital_h.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (17 * 1000) + 200, (18 * 1000) + 300)
                        .setExtraStart(22 * 1000).setExtraEnd((23 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.horse))
                .setExtraPhoto(R.drawable.animal_horse)
        );
        arModels.add(new ArModel(
                8, mApplication.getResources().getString(R.string.capital_i),
                R.drawable.capital_i, "capital_i.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (19 * 1000) + 300, (20 * 1000) + 900)
                        .setExtraStart(25 * 1000).setExtraEnd((26 * 1000) + 400), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_ice_cream))
                .setExtraPhoto(R.drawable.alphabet_ice_cream)
        );
        arModels.add(new ArModel(
                9, mApplication.getResources().getString(R.string.capital_j),
                R.drawable.capital_j, "capital_j.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (22 * 1000) + 300, (23 * 1000) + 500)
                        .setExtraStart(27 * 1000).setExtraEnd(29 * 1000), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_jackfruit))
                .setExtraPhoto(R.drawable.alphabet_jackfruit)
        );
        arModels.add(new ArModel(
                10, mApplication.getResources().getString(R.string.capital_k),
                R.drawable.capital_k, "capital_k.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (25 * 1000) + 300, (26 * 1000) + 500)
                        .setExtraStart(30 * 1000).setExtraEnd((31 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_kite))
                .setExtraPhoto(R.drawable.alphabet_kite)
        );
        arModels.add(new ArModel(
                11, mApplication.getResources().getString(R.string.capital_l),
                R.drawable.capital_l, "capital_l.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (28 * 1000) + 200, (29 * 1000) + 100)
                        .setExtraStart(33 * 1000).setExtraEnd((34 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.lion))
                .setExtraPhoto(R.drawable.animal_lion)
        );
        arModels.add(new ArModel(
                12, mApplication.getResources().getString(R.string.capital_m),
                R.drawable.capital_m, "capital_m.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (30 * 1000) + 500, (31 * 1000) + 900)
                        .setExtraStart(36 * 1000).setExtraEnd((37 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_mango))
                .setExtraPhoto(R.drawable.alphabet_mango)
        );
        arModels.add(new ArModel(
                13, mApplication.getResources().getString(R.string.capital_n),
                R.drawable.capital_n, "capital_n.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (33 * 1000) + 200, (34 * 1000) + 300)
                        .setExtraStart(39 * 1000).setExtraEnd((40 * 1000) + 400), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_nest))
                .setExtraPhoto(R.drawable.alphabet_nest)
        );
        arModels.add(new ArModel(
                14, mApplication.getResources().getString(R.string.capital_o),
                R.drawable.capital_o, "capital_o.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (36 * 1000), (37 * 1000) + 100)
                        .setExtraStart((41 * 1000) + 500).setExtraEnd((43 * 1000) + 200), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_orange))
                .setExtraPhoto(R.drawable.alphabet_orange)
        );
        arModels.add(new ArModel(
                15, mApplication.getResources().getString(R.string.capital_p),
                R.drawable.capital_p, "capital_p.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (38 * 1000) + 800, (39 * 1000) + 700)
                        .setExtraStart(44 * 1000).setExtraEnd((45 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_parrot))
                .setExtraPhoto(R.drawable.alphabet_parrot)
        );
        arModels.add(new ArModel(
                16, mApplication.getResources().getString(R.string.capital_q),
                R.drawable.capital_q, "capital_q.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (41 * 1000) + 500, (42 * 1000) + 900)
                        .setExtraStart(47 * 1000).setExtraEnd((48 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_queen))
                .setExtraPhoto(R.drawable.alphabet_queen)
        );
        arModels.add(new ArModel(
                17, mApplication.getResources().getString(R.string.capital_r),
                R.drawable.capital_r, "capital_r.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (44 * 1000) + 800, (45 * 1000) + 900)
                        .setExtraStart(50 * 1000).setExtraEnd((51 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet__rainbow))
                .setExtraPhoto(R.drawable.alphabet_rainbow)
        );
        arModels.add(new ArModel(
                18, mApplication.getResources().getString(R.string.capital_s),
                R.drawable.capital_s, "capital_s.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (48 * 1000), (49 * 1000) + 200)
                        .setExtraStart(53 * 1000).setExtraEnd((54 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_sweet))
                .setExtraPhoto(R.drawable.alphabet_sweet)
        );
        arModels.add(new ArModel(
                19, mApplication.getResources().getString(R.string.capital_t),
                R.drawable.capital_t, "capital_t.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (51 * 1000), (52 * 1000) + 500)
                        .setExtraStart((55 * 1000) + 500).setExtraEnd((57 * 1000) + 400), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_tiger))
                .setExtraPhoto(R.drawable.alphabet_tiger)
        );
        arModels.add(new ArModel(
                20, mApplication.getResources().getString(R.string.capital_u),
                R.drawable.capital_u, "capital_u.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (54 * 1000), (55 * 1000) + 300)
                        .setExtraStart(58 * 1000).setExtraEnd((59 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_umbrella))
                .setExtraPhoto(R.drawable.alphabet_umbrealla)
        );
        arModels.add(new ArModel(
                21, mApplication.getResources().getString(R.string.capital_v),
                R.drawable.capital_v, "capital_v.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (56 * 1000) + 500, (57 * 1000) + 800)
                        .setExtraStart((60 * 1000) + 500).setExtraEnd(((60 + 2) * 1000) + 200), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_vegetable))
                .setExtraPhoto(R.drawable.alphabet_vegetable)
        );
        arModels.add(new ArModel(
                22, mApplication.getResources().getString(R.string.capital_w),
                R.drawable.capital_w, "capital_w.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (61 * 1000), (62  * 1000) + 300)
                        .setExtraStart((60 + 4) * 1000).setExtraEnd(((60 + 5) * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_water))
                .setExtraPhoto(R.drawable.alphabet_water_glass)
        );
        arModels.add(new ArModel(
                23, mApplication.getResources().getString(R.string.capital_x),
                R.drawable.capital_x, "capital_x.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (63 * 1000) + 700, (64 * 1000) + 700)
                        .setExtraStart((60 + 7) * 1000).setExtraEnd(((60 + 8) * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_x_ray))
                .setExtraPhoto(R.drawable.alphabet_x_ray)
        );
        arModels.add(new ArModel(
                24, mApplication.getResources().getString(R.string.capital_y),
                R.drawable.capital_y, "capital_y.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (67 * 1000), (68 * 1000) + 500)
                        .setExtraStart((60 + 10) * 1000).setExtraEnd(((60 + 11) * 1000) + 700), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_yak))
                .setExtraPhoto(R.drawable.alphabet_yak)
        );
        arModels.add(new ArModel(
                25, mApplication.getResources().getString(R.string.capital_z),
                R.drawable.capital_z, "capital_z.sfb",
                new Voice(Voice.VOICE_ALPHABETS_ENGLISH, (69 * 1000), (70 * 1000) + 800)
                        .setExtraStart((60 + 13) * 1000).setExtraEnd(((60 + 14) * 1000) + 700), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_zebra))
                .setExtraPhoto(R.drawable.alphabet_zebra)
        );

        return arModels;
    }

    /**
     * @return The {@link List} of english number of {@link ArModel}.
     */
    private List<ArModel> getEnglishNumbers() {
        Subject subject = new Subject(
                Subject.NUMBER_ENGLISH, mApplication.getResources().getString(R.string.number_english),
                R.drawable.number, new Language(Language.ENGLISH, mApplication.getResources().getString(R.string.language_english)),
                Subject.DOWNLOAD_URL_NUMBERS_ENGLISH
        );

        List<ArModel> arModels = new ArrayList<>();
        arModels.add(new ArModel(
                0, mApplication.getResources().getString(R.string.number_one),
                R.drawable.number_1, "number_1.sfb",
                new Voice(Voice.VOICE_NUMBERS_ENGLISH, 700, 1900), subject
                ));
        arModels.add(new ArModel(
                1, mApplication.getResources().getString(R.string.number_two),
                R.drawable.number_2, "number_2.sfb",
                new Voice(Voice.VOICE_NUMBERS_ENGLISH, (2 * 1000) + 500, (3 * 1000) + 800), subject
        ));
        arModels.add(new ArModel(
                2, mApplication.getResources().getString(R.string.number_three),
                R.drawable.number_3, "number_3.sfb",
                new Voice(Voice.VOICE_NUMBERS_ENGLISH, (4 * 1000) + 400, (5 * 1000) + 900), subject
        ));
        arModels.add(new ArModel(
                3, mApplication.getResources().getString(R.string.number_four),
                R.drawable.number_4, "number_4.sfb",
                new Voice(Voice.VOICE_NUMBERS_ENGLISH, (6 * 1000) + 600, (8 * 1000) + 200), subject
        ));
        arModels.add(new ArModel(
                4, mApplication.getResources().getString(R.string.number_five),
                R.drawable.number_5, "number_5.sfb",
                new Voice(Voice.VOICE_NUMBERS_ENGLISH, 9 * 1000, (10 * 1000) + 500), subject
        ));
        arModels.add(new ArModel(
                5, mApplication.getResources().getString(R.string.number_six),
                R.drawable.number_6, "number_6.sfb",
                new Voice(Voice.VOICE_NUMBERS_ENGLISH, 12 * 1000, 13 * 1000), subject
        ));
        arModels.add(new ArModel(
                6, mApplication.getResources().getString(R.string.number_seven),
                R.drawable.number_7, "number_7.sfb",
                new Voice(Voice.VOICE_NUMBERS_ENGLISH, 14 * 1000, 15 * 1000), subject
        ));
        arModels.add(new ArModel(
                7, mApplication.getResources().getString(R.string.number_eight),
                R.drawable.number_8, "number_8.sfb",
                new Voice(Voice.VOICE_NUMBERS_ENGLISH, (16 * 1000) + 500, (17 * 1000) + 200), subject
        ));
        arModels.add(new ArModel(
                8, mApplication.getResources().getString(R.string.number_nine),
                R.drawable.number_9, "number_9.sfb",
                new Voice(Voice.VOICE_NUMBERS_ENGLISH, (18 * 1000) + 400, (19 * 1000) + 700), subject
        ));

        return arModels;
    }

    /**
     * @return The {@link List} of english animal of {@link ArModel}.
     */
    private List<ArModel> getAnimals() {
        Subject subject = new Subject(
                Subject.ANIMAL_ENGLISH, mApplication.getResources().getString(R.string.animal_english),
                R.drawable.animals, new Language(Language.ENGLISH, mApplication.getResources().getString(R.string.language_english)),
                Subject.DOWNLOAD_URL_ANIMALS_ENGLISH
        );

        List<ArModel> arModels = new ArrayList<>();
        arModels.add(new ArModel(
                0, mApplication.getResources().getString(R.string.bear),
                R.drawable.animal_bear, "bear.sfb",
                new Voice(Voice.VOICE_ANIMALS_ENGLISH, 500, 1800), subject
                ));
        arModels.add(new ArModel(
                1, mApplication.getResources().getString(R.string.cat),
                R.drawable.animal_cat, "cat.sfb",
                new Voice(Voice.VOICE_ANIMALS_ENGLISH, (2 * 1000) + 500, (3 * 1000) + 700), subject
        ));
        arModels.add(new ArModel(
                2, mApplication.getResources().getString(R.string.cow),
                R.drawable.animal_cow, "cow.sfb",
                new Voice(Voice.VOICE_ANIMALS_ENGLISH, (4 * 1000) + 500, 6 * 1000), subject
        ));
        arModels.add(new ArModel(
                3, mApplication.getResources().getString(R.string.deer),
                R.drawable.animal_deer, "deer.sfb",
                new Voice(Voice.VOICE_ANIMALS_ENGLISH, 7 * 1000, 8 * 1000), subject
        ));
        arModels.add(new ArModel(
                4, mApplication.getResources().getString(R.string.dog),
                R.drawable.animal_dog, "dog.sfb",
                new Voice(Voice.VOICE_ANIMALS_ENGLISH, 9 * 1000, (9 * 1000) + 900), subject
        ));
        arModels.add(new ArModel(
                5, mApplication.getResources().getString(R.string.elephant),
                R.drawable.animal_elephant, "elephant.sfb",
                new Voice(Voice.VOICE_ANIMALS_ENGLISH, 11 * 1000, (11 * 1000) + 900), subject
        ));
        arModels.add(new ArModel(
                6, mApplication.getResources().getString(R.string.hippopotamus),
                R.drawable.animal_hippopotamus, "hippopotamus.sfb",
                new Voice(Voice.VOICE_ANIMALS_ENGLISH, 13 * 1000, (14 * 1000) + 400), subject
        ));
        arModels.add(new ArModel(
                7, mApplication.getResources().getString(R.string.horse),
                R.drawable.animal_horse, "horse.sfb",
                new Voice(Voice.VOICE_ANIMALS_ENGLISH, 15 * 1000, 16 * 1000), subject
        ));
        arModels.add(new ArModel(
                8, mApplication.getResources().getString(R.string.lion),
                R.drawable.animal_lion, "lion.sfb",
                new Voice(Voice.VOICE_ANIMALS_ENGLISH, (16 * 1000) + 500, 18 * 1000), subject
        ));

        return arModels;
    }

    /**
     * @return A {@link LiveData} {@link List} of {@link ArModel} for a given {@link Subject}.
     * to be observed.
     */
    public LiveData<List<ArModel>> getArModelsBySubjectLivedData(int subject) {
        if (mMutableLiveDataListArModelBySubject == null) {
            mMutableLiveDataListArModelBySubject = new MutableLiveData<>();

            // The list of items (ArModel) to be set in mMutableLiveDataList
            List<ArModel> arModels = new ArrayList<>();
            // Data should be fetched from cloud but we use static data here
            switch (subject) {
                case Subject.VOWEL_BENGALI:
                    arModels = getBengaliVowels();
                    break;
                case Subject.ALPHABET_BENGALI:
                    arModels = getBengaliAlphabets();
                    break;
                case Subject.NUMBER_BENGALI:
                    arModels = getBengaliNumbers();
                    break;
                case Subject.ALPHABET_ENGLISH:
                    arModels = getEnglishAlphabets();
                    break;
                case Subject.NUMBER_ENGLISH:
                    arModels = getEnglishNumbers();
                    break;
                case Subject.ANIMAL_ENGLISH:
                    arModels = getAnimals();
                    break;
            }

            // Set the value of MutableLiveData
            mMutableLiveDataListArModelBySubject.setValue(arModels);
        }

        return mMutableLiveDataListArModelBySubject;
    }

    /**
     * @return A {@link LiveData} {@link List} of {@link ArModel} for a given {@link Language}.
     * to be observed.
     */
    public LiveData<List<Language>> getLanguagesLivedData() {
        if (mMutableLiveDataListLanguage == null) {
            mMutableLiveDataListLanguage = new MutableLiveData<>();

            // The list of items (Language) to be set in mMutableLiveDataList
            List<Language> languages = new ArrayList<>();
            // Data should be fetched from cloud but we use static data here
            languages.add(new Language(Language.BENGALI, mApplication.getResources().getString(R.string.language_bengali)));
            languages.add(new Language(Language.ENGLISH, mApplication.getResources().getString(R.string.language_english)));

            // Set the value of MutableLiveData
            mMutableLiveDataListLanguage.setValue(languages);
        }

        return mMutableLiveDataListLanguage;
    }

    /**
     * @return A {@link LiveData} {@link List} of {@link ArModel} for a given {@link Language}.
     * to be observed.
     */
    public LiveData<List<Subject>> getSubjectsLivedData(int language) {
        mMutableLiveDataListSubject = new MutableLiveData<>();

        // The list of items (ArModel) to be set in mMutableLiveDataList
        List<Subject> subjects = new ArrayList<>();
        // Data should be fetched from cloud but we use static data here
        switch (language) {
            case Language.BENGALI:
                subjects.add(new Subject(
                        Subject.VOWEL_BENGALI, mApplication.getResources().getString(R.string.vowel_bengali),
                        R.drawable.vowel_bengali, new Language(Language.BENGALI, mApplication.getResources().getString(R.string.language_bengali)),
                        Subject.DOWNLOAD_URL_VOWELS_BENGALI
                ));
                subjects.add(new Subject(
                        Subject.ALPHABET_BENGALI, mApplication.getResources().getString(R.string.alphabet_bengali),
                        R.drawable.alphabet_bengali, new Language(Language.BENGALI, mApplication.getResources().getString(R.string.language_bengali)),
                        Subject.DOWNLOAD_URL_ALPHABETS_BENGALI
                ));
                subjects.add(new Subject(
                        Subject.NUMBER_BENGALI, mApplication.getResources().getString(R.string.number_bengali),
                        R.drawable.number_bengali, new Language(Language.BENGALI, mApplication.getResources().getString(R.string.language_bengali)),
                        Subject.DOWNLOAD_URL_NUMBERS_BENGALI
                ));
                break;
            case Language.ENGLISH:
                subjects.add(new Subject(
                        Subject.ALPHABET_ENGLISH, mApplication.getResources().getString(R.string.alphabet_english),
                        R.drawable.alphabet, new Language(Language.ENGLISH, mApplication.getResources().getString(R.string.language_english)),
                        Subject.DOWNLOAD_URL_ALPHABETS_ENGLISH
                ));
                subjects.add(new Subject(
                        Subject.NUMBER_ENGLISH, mApplication.getResources().getString(R.string.number_english),
                        R.drawable.number, new Language(Language.ENGLISH, mApplication.getResources().getString(R.string.language_english)),
                        Subject.DOWNLOAD_URL_NUMBERS_ENGLISH
                ));
                subjects.add(new Subject(
                        Subject.ANIMAL_ENGLISH, mApplication.getResources().getString(R.string.animal_english),
                        R.drawable.animals, new Language(Language.ENGLISH, mApplication.getResources().getString(R.string.language_english)),
                        Subject.DOWNLOAD_URL_ANIMALS_ENGLISH
                ));
                break;
        }

        // Set the value of MutableLiveData
        mMutableLiveDataListSubject.setValue(subjects);

        return mMutableLiveDataListSubject;
    }
}
