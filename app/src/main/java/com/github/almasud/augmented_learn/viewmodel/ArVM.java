package com.github.almasud.augmented_learn.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.almasud.augmented_learn.R;
import com.github.almasud.augmented_learn.model.entity.ArModel;
import com.github.almasud.augmented_learn.model.entity.Language;
import com.github.almasud.augmented_learn.model.entity.Subject;
import com.github.almasud.augmented_learn.model.entity.Voice;

import java.util.ArrayList;
import java.util.List;

/**
 * An {@link AndroidViewModel} for the {@link ArModel}.
 *
 * @author Abdullah Almasud
 */
public class ArVM extends AndroidViewModel {
    private Application mApplication;
    private MutableLiveData<List<ArModel>> mMutableLiveDataListArModel;
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
                Subject.BENGALI_VOWEL, mApplication.getResources().getString(R.string.vowel_bengali),
                "bn_vowel_cover.jpg", new Language(Language.BENGALI, mApplication.getResources().getString(R.string.language_bengali)),
                Subject.URL_MODEL_BENGALI_VOWELS
        );

        List<ArModel> arModels = new ArrayList<>();
        arModels.add(new ArModel(
                0, mApplication.getResources().getString(R.string.bn_vowel_01),
                "bn_vowel_01.gif", "bn_vowel_01.sfb",
                new Voice(Voice.VOICE_BENGALI_VOWELS, 3500,  4400)
                        .setExtraStart(1000 + 100).setExtraEnd((3 * 1000) + 900), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_vowel_01_extra))
                .setExtraPhoto("bn_vowel_01_extra.jpg")
        );

        arModels.add(new ArModel(
                1, mApplication.getResources().getString(R.string.bn_vowel_02),
                "bn_vowel_02.gif", "bn_vowel_02.sfb",
                new Voice(Voice.VOICE_BENGALI_VOWELS, 6300, 7500)
                        .setExtraStart((4 * 1000) + 300).setExtraEnd((6 * 1000) + 700), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_vowel_02_extra))
                .setExtraPhoto("en_alphabet_capital_m_extra.gif")
        );
        arModels.add(new ArModel(
                2, mApplication.getResources().getString(R.string.bn_vowel_03),
                "bn_vowel_03.gif", "bn_vowel_03.sfb",
                new Voice(Voice.VOICE_BENGALI_VOWELS,  9500, 10700)
                        .setExtraStart(7*1000).setExtraEnd((9 * 1000) + 300), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_vowel_03_extra))
                .setExtraPhoto("bn_vowel_03_extra.gif")
        );
        arModels.add(new ArModel(
                3, mApplication.getResources().getString(R.string.bn_vowel_04),
                "bn_vowel_04.gif", "bn_vowel_04.sfb",
                new Voice(Voice.VOICE_BENGALI_VOWELS, 11900, 13300)
                        .setExtraStart((9 * 1000) + 800).setExtraEnd((11 * 1000) + 900), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_vowel_04_extra))
                .setExtraPhoto("bn_vowel_04_extra.gif")
        );
        arModels.add(new ArModel(
                4, mApplication.getResources().getString(R.string.bn_vowel_05),
                "bn_vowel_05.gif", "bn_vowel_05.sfb",
                new Voice(Voice.VOICE_BENGALI_VOWELS, 15800, 16900)
                        .setExtraStart((12 * 1000) + 500).setExtraEnd((14 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_vowel_05_extra))
                .setExtraPhoto("bn_vowel_05_extra.gif")
        );
        arModels.add(new ArModel(
                5, mApplication.getResources().getString(R.string.bn_vowel_06),
                "bn_vowel_06.gif", "bn_vowel_06.sfb",
                new Voice(Voice.VOICE_BENGALI_VOWELS, 18500, 19500)
                        .setExtraStart(15 * 1000).setExtraEnd(17 * 1000), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_vowel_06_extra))
                .setExtraPhoto("bn_vowel_06_extra.jpg")
        );
        arModels.add(new ArModel(
                6, mApplication.getResources().getString(R.string.bn_vowel_07),
                "bn_vowel_07.gif", "bn_vowel_07.sfb",
                new Voice(Voice.VOICE_BENGALI_VOWELS, 21000, 22300)
                        .setExtraStart(18 * 1000) .setExtraEnd((20 * 1000)+500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_vowel_07_extra))
                .setExtraPhoto("bn_vowel_07_extra.jpg")
        );
        arModels.add(new ArModel(
                7, mApplication.getResources().getString(R.string.bn_vowel_08),
                "bn_vowel_08.gif", "bn_vowel_08.sfb",
                new Voice(Voice.VOICE_BENGALI_VOWELS, 24000, 25200)
                        .setExtraStart(21 * 1000).setExtraEnd((23 * 1000) + 300), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_vowel_08_extra))
                .setExtraPhoto("bn_vowel_08_extra.gif")
        );
        arModels.add(new ArModel(
                8, mApplication.getResources().getString(R.string.bn_vowel_09),
                "bn_vowel_09.gif", "bn_vowel_09.sfb",
                new Voice(Voice.VOICE_BENGALI_VOWELS, 28000, 29000)
                        .setExtraStart((23 * 1000) + 900).setExtraEnd(26 * 1000), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_vowel_09_extra))
                .setExtraPhoto("en_animal_elephant.gif")
        );
        arModels.add(new ArModel(
                9, mApplication.getResources().getString(R.string.bn_vowel_10),
                "bn_vowel_10.gif", "bn_vowel_10.sfb",
                new Voice(Voice.VOICE_BENGALI_VOWELS, 30800, 31900)
                        .setExtraStart(26 * 1000).setExtraEnd((28* 1000) +400), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_vowel_10_extra))
                .setExtraPhoto("bn_vowel_10_extra.gif")
        );
        arModels.add(new ArModel(
                10, mApplication.getResources().getString(R.string.bn_vowel_11),
                "bn_vowel_11.gif", "bn_vowel_11.sfb",
                new Voice(Voice.VOICE_BENGALI_VOWELS, 33200, 34800)
                        .setExtraStart((28 * 1000) + 500).setExtraEnd((30 * 1000) + 800), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_vowel_11_extra))
                .setExtraPhoto("bn_vowel_11_extra.gif")
        );

        return arModels;
    }

    /**
     * @return The {@link List} of bengali alphabet of {@link ArModel}.
     */
    private List<ArModel> getBengaliAlphabets() {
        Subject subject = new Subject(
                Subject.BENGALI_ALPHABET, mApplication.getResources().getString(R.string.alphabet_bengali),
                "bn_alphabet_cover.jpg", new Language(Language.BENGALI, mApplication.getResources().getString(R.string.language_bengali)),
                Subject.URL_MODEL_BENGALI_ALPHABETS
        );

        List<ArModel> arModels = new ArrayList<>();
        arModels.add(new ArModel(
                0, mApplication.getResources().getString(R.string.bn_alphabet_01),
                "bn_alphabet_01.gif", "bn_alphabet_01.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, 2000, (2 * 1000) +900)
                        .setExtraStart(2 * 1000).setExtraEnd((4 * 1000) + 300), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_01_extra))
                .setExtraPhoto("bn_alphabet_01_extra.gif")
        );
        arModels.add(new ArModel(
                1, mApplication.getResources().getString(R.string.bn_alphabet_02),
                "bn_alphabet_02.gif", "bn_alphabet_02.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, (4 * 1000) + 100, (5 * 1000) + 100)
                        .setExtraStart(5 * 1000).setExtraEnd((7 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_02_extra))
                .setExtraPhoto("bn_alphabet_02_extra.gif")
        );
        arModels.add(new ArModel(
                2, mApplication.getResources().getString(R.string.bn_alphabet_03),
                "bn_alphabet_03.gif", "bn_alphabet_03.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, (6 * 1000) + 100, (7 * 1000) + 100)
                        .setExtraStart(8 * 1000).setExtraEnd((10 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_03_extra))
                .setExtraPhoto("en_animal_cow.gif")
        );
        arModels.add(new ArModel(
                3, mApplication.getResources().getString(R.string.bn_alphabet_04),
                "bn_alphabet_04.gif", "bn_alphabet_04.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, (8 * 1000), (9 * 1000) + 100)
                        .setExtraStart((10 * 1000) + 500).setExtraEnd(13 * 1000), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_04_extra))
                .setExtraPhoto("en_animal_horse.gif")
        );
        arModels.add(new ArModel(
                4, mApplication.getResources().getString(R.string.bn_alphabet_05),
                "bn_alphabet_05.gif", "bn_alphabet_05.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, (10 * 1000) + 200, (11 * 1000) + 200)
                        .setExtraStart( (13 * 1000) + 500).setExtraEnd((15 * 1000) + 700), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_05_extra))
                .setExtraPhoto("bn_alphabet_05_extra.gif")
        );
        arModels.add(new ArModel(
                5, mApplication.getResources().getString(R.string.bn_alphabet_06),
                "bn_alphabet_06.gif", "bn_alphabet_06.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, (12 * 1000) + 200, (13 * 1000) + 300)
                        .setExtraStart(16 * 1000).setExtraEnd((19 * 1000) + 200), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_06_extra))
                .setExtraPhoto("bn_alphabet_06_extra.gif")
        );
        arModels.add(new ArModel(
                6, mApplication.getResources().getString(R.string.bn_alphabet_07),
                "bn_alphabet_07.gif", "bn_alphabet_07.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, (14 * 1000) + 300, (15 * 1000) + 500)
                        .setExtraStart((18 * 1000) + 800).setExtraEnd((21 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_07_extra))
                .setExtraPhoto("en_alphabet_capital_u_extra.gif")
        );
        arModels.add(new ArModel(
                7, mApplication.getResources().getString(R.string.bn_alphabet_08),
                "bn_alphabet_08.gif", "bn_alphabet_08.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, (16 * 1000) + 200, (17 * 1000) + 700)
                        .setExtraStart(22 * 1000).setExtraEnd((24 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_08_extra))
                .setExtraPhoto("bn_alphabet_08_extra.gif")
        );
        arModels.add(new ArModel(
                8, mApplication.getResources().getString(R.string.bn_alphabet_09),
                "bn_alphabet_09.gif", "bn_alphabet_09.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, (18 * 1000) + 500, (19 * 1000) + 600)
                        .setExtraStart(25 * 1000).setExtraEnd((27 * 1000) + 200), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_09_extra))
                .setExtraPhoto("bn_alphabet_09_extra.gif")
        );
        arModels.add(new ArModel(
                9, mApplication.getResources().getString(R.string.bn_alphabet_10),
                "bn_alphabet_10.gif", "bn_alphabet_10.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, (20 * 1000) + 200, (21 * 1000) + 300)
                        .setExtraStart((27 * 1000) + 500).setExtraEnd((29 * 1000) + 900), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_10_extra))
                .setExtraPhoto("bn_alphabet_10_extra.gif")
        );
        arModels.add(new ArModel(
                10, mApplication.getResources().getString(R.string.bn_alphabet_11),
                "bn_alphabet_11.gif", "bn_alphabet_11.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, (22 * 1000), (23 * 1000) + 100)
                        .setExtraStart(30 * 1000).setExtraEnd((32 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_11_extra))
                .setExtraPhoto("en_alphabet_capital_p_extra.gif")
        );
        arModels.add(new ArModel(
                11, mApplication.getResources().getString(R.string.bn_alphabet_12),
                "bn_alphabet_12.gif", "bn_alphabet_12.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, (24 * 1000) + 200, (25* 1000) + 100)
                        .setExtraStart(33 * 1000).setExtraEnd((35 * 1000) + 800), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_12_extra))
                .setExtraPhoto("bn_alphabet_12_extra.gif")
        );
        arModels.add(new ArModel(
                12, mApplication.getResources().getString(R.string.bn_alphabet_13),
                "bn_alphabet_13.gif", "bn_alphabet_13.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, (26 * 1000) + 200, (27 * 1000) + 300)
                        .setExtraStart(36 * 1000).setExtraEnd((38 * 1000) + 800), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_13_extra))
                .setExtraPhoto("bn_alphabet_13_extra.gif")
        );
        arModels.add(new ArModel(
                13, mApplication.getResources().getString(R.string.bn_alphabet_14),
                "bn_alphabet_14.gif", "bn_alphabet_14.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, (28 * 1000) + 300, (29 * 1000) + 300)
                        .setExtraStart(39 * 1000).setExtraEnd((41 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_14_extra))
                .setExtraPhoto("bn_alphabet_14_extra.gif")
        );
        arModels.add(new ArModel(
                14, mApplication.getResources().getString(R.string.bn_alphabet_15),
                "bn_alphabet_15.gif", "bn_alphabet_15.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, (30 * 1000) + 300, (31 * 1000) + 600)
                        .setExtraStart(42 * 1000).setExtraEnd((43 * 1000) + 800), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_15_extra))
                .setExtraPhoto("en_animal_deer.gif")
        );
        arModels.add(new ArModel(
                15, mApplication.getResources().getString(R.string.bn_alphabet_16),
                "bn_alphabet_16.gif", "bn_alphabet_16.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, (32 * 1000) + 300, (33 * 1000) + 300)
                        .setExtraStart(44 * 1000).setExtraEnd((46 * 1000) + 700), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_16_extra))
                .setExtraPhoto("bn_alphabet_16_extra.gif")
        );
        arModels.add(new ArModel(
                16, mApplication.getResources().getString(R.string.bn_alphabet_17),
                "bn_alphabet_17.gif", "bn_alphabet_17.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, (34 * 1000) + 200, (35 * 1000) + 300)
                        .setExtraStart(47 * 1000).setExtraEnd((49 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_17_extra))
                .setExtraPhoto("bn_alphabet_17_extra.gif")
        );
        arModels.add(new ArModel(
                17, mApplication.getResources().getString(R.string.bn_alphabet_18),
                "bn_alphabet_18.gif", "bn_alphabet_18.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, 36* 1000, (37 * 1000) + 100)
                        .setExtraStart(50 * 1000).setExtraEnd((52 * 1000) + 300), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_18_extra))
                .setExtraPhoto("bn_alphabet_18_extra.gif")
        );
        arModels.add(new ArModel(
                18, mApplication.getResources().getString(R.string.bn_alphabet_19),
                "bn_alphabet_19.gif", "bn_alphabet_19.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, (38 * 1000) + 100, (39 * 1000) + 200)
                        .setExtraStart((52 * 1000) + 500).setExtraEnd((55 * 1000) + 100), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_19_extra))
                .setExtraPhoto("bn_alphabet_19_extra.gif")
        );
        arModels.add(new ArModel(
                19, mApplication.getResources().getString(R.string.bn_alphabet_20),
                "bn_alphabet_20.gif", "bn_alphabet_20.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, (40 * 1000) + 100, (41 * 1000) + 100)
                        .setExtraStart((55 * 1000) + 500).setExtraEnd((58 * 1000) + 200), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_20_extra))
                .setExtraPhoto("bn_alphabet_20_extra.jpg")
        );
        arModels.add(new ArModel(
                20, mApplication.getResources().getString(R.string.bn_alphabet_21),
                "bn_alphabet_21.gif", "bn_alphabet_21.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, 42 * 1000, (43 * 1000) + 200)
                        .setExtraStart((58 *1000) + 500).setExtraEnd((60 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_21_extra))
                .setExtraPhoto("bn_alphabet_21_extra.gif")
        );
        arModels.add(new ArModel(
                21, mApplication.getResources().getString(R.string.bn_alphabet_22),
                "bn_alphabet_22.gif", "models/bn_alphabets/bn_alphabet_22.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, (43 * 1000) + 800, 45 * 1000)
                        .setExtraStart(61 * 1000).setExtraEnd((63 * 1000) + 200), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_22_extra))
                .setExtraPhoto("bn_alphabet_22_extra.gif")
        );
        arModels.add(new ArModel(
                22, mApplication.getResources().getString(R.string.bn_alphabet_23),
                "bn_alphabet_23.gif", "bn_alphabet_23.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, (46 * 1000), (47 * 1000) + 100)
                        .setExtraStart(64 * 1000).setExtraEnd((66 * 1000) + 100), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_23_extra))
                .setExtraPhoto("en_animal_cat.gif")
        );
        arModels.add(new ArModel(
                23, mApplication.getResources().getString(R.string.bn_alphabet_24),
                "bn_alphabet_24.gif", "bn_alphabet_24.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, (48 * 1000), (49 * 1000) + 100)
                        .setExtraStart((66 * 1000) + 500).setExtraEnd((68 * 1000) + 900), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_24_extra))
                .setExtraPhoto("en_animal_bear.gif")
        );
        arModels.add(new ArModel(
                24, mApplication.getResources().getString(R.string.bn_alphabet_25),
                "bn_alphabet_25.gif", "bn_alphabet_25.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, 50 * 1000, (51 * 1000) + 100)
                        .setExtraStart((69 * 1000) + 500).setExtraEnd((71 * 1000) + 900), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_25_extra))
                .setExtraPhoto("en_alphabet_capital_s_extra.gif")
        );
        arModels.add(new ArModel(
                25, mApplication.getResources().getString(R.string.bn_alphabet_26),
                "alphabet_26.gif", "bn_alphabet_26.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, (52 * 1000) + 200, (53* 1000) + 300)
                        .setExtraStart(72 * 1000).setExtraEnd((74 * 1000) + 800), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_26_extra))
                .setExtraPhoto("bn_alphabet_26_extra.gif")
        );
        arModels.add(new ArModel(
                26, mApplication.getResources().getString(R.string.bn_alphabet_27),
                "bn_alphabet_27.gif", "bn_alphabet_27.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, (54 * 1000) + 100, (55 * 1000) + 200)
                        .setExtraStart(75 * 1000).setExtraEnd((77 * 1000) + 100), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_27_extra))
                .setExtraPhoto("en_alphabet_capital_q_extra.gif")
        );
        arModels.add(new ArModel(
                27, mApplication.getResources().getString(R.string.bn_alphabet_28),
                "bn_alphabet_28.gif", "bn_alphabet_28.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, (56 * 1000) + 200, (57 * 1000) + 200)
                        .setExtraStart((77 * 1000) + 500).setExtraEnd((79 * 1000) + 800), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_28_extra))
                .setExtraPhoto("bn_alphabet_28_extra.gif")
        );
        arModels.add(new ArModel(
                28, mApplication.getResources().getString(R.string.bn_alphabet_29),
                "bn_alphabet_29.gif", "bn_alphabet_29.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, (58 * 1000) + 100, (59 * 1000) + 500)
                        .setExtraStart((80 * 1000) + 200).setExtraEnd((82 * 1000) + 700), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_29_extra))
                .setExtraPhoto("bn_alphabet_29_extra.gif")
        );
        arModels.add(new ArModel(
                29, mApplication.getResources().getString(R.string.bn_alphabet_30),
                "bn_alphabet_30.gif", "bn_alphabet_30.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, 60 * 1000, (61 * 1000) + 400)
                        .setExtraStart(83 * 1000).setExtraEnd((85 * 1000) + 400), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_30_extra))
                .setExtraPhoto("bn_alphabet_30_extra.gif")
        );
        arModels.add(new ArModel(
                30, mApplication.getResources().getString(R.string.bn_alphabet_31),
                "bn_alphabet_31.gif", "bn_alphabet_31.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, (61 * 1000) + 800, (63 * 1000) + 200)
                        .setExtraStart((85 * 1000) + 800).setExtraEnd((87 * 1000) + 900), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_31_extra))
                .setExtraPhoto("en_animal_lion.gif")
        );
        arModels.add(new ArModel(
                31, mApplication.getResources().getString(R.string.bn_alphabet_32),
                "bn_alphabet_32.gif", "bn_alphabet_32.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, 64 * 1000, (65 * 1000) + 100)
                        .setExtraStart((88 * 1000)+ 500).setExtraEnd((90 * 1000) + 700), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_32_extra))
                .setExtraPhoto("en_animal_elephant.gif")
        );
        arModels.add(new ArModel(
                32, mApplication.getResources().getString(R.string.bn_alphabet_33),
                "bn_alphabet_33.gif", "bn_alphabet_33.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, (66 * 1000), (67 * 1000) + 800)
                        .setExtraStart(91 * 1000).setExtraEnd((93 * 1000) + 900), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_09_extra))
                .setExtraPhoto("bn_alphabet_09_extra.gif")
        );
        arModels.add(new ArModel(
                33, mApplication.getResources().getString(R.string.bn_alphabet_34),
                "bn_alphabet_34.gif", "bn_alphabet_34.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, (68 * 1000) + 200, 70 * 1000)
                        .setExtraStart(94 * 1000).setExtraEnd((96 * 1000) + 800), subject
                ).setExtraName("আষাঢ়")
                .setExtraPhoto("bn_alphabet_34_extra.jpg")
        );
        arModels.add(new ArModel(
                34, mApplication.getResources().getString(R.string.bn_alphabet_35),
                "bn_alphabet_35.gif", "bn_alphabet_35.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS,  72 * 1000, (73 * 1000) + 500)
                        .setExtraStart(97 * 1000).setExtraEnd((99 * 1000) + 200), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_35_extra))
                .setExtraPhoto("bn_alphabet_35_extra.gif")
        );
        arModels.add(new ArModel(
                35, mApplication.getResources().getString(R.string.bn_alphabet_36),
                "bn_alphabet_36.gif", "bn_alphabet_36.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, 75 * 1000, (76 * 1000) + 500)
                        .setExtraStart(100 * 1000).setExtraEnd((101 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_36_extra))
                .setExtraPhoto("bn_alphabet_36_extra.jpg")
        );
        arModels.add(new ArModel(
                36, mApplication.getResources().getString(R.string.bn_alphabet_37),
                "bn_alphabet_37.gif", "bn_alphabet_37.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, (77 * 1000) + 500, (78 * 1000) + 700)
                        .setExtraStart((102 * 1000) + 500).setExtraEnd((104 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_37_extra))
                .setExtraPhoto("bn_alphabet_37_extra.gif")
        );
        arModels.add(new ArModel(
                37, mApplication.getResources().getString(R.string.bn_alphabet_38),
                "bn_alphabet_38.gif", "bn_alphabet_38.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, 80 * 1000, (81 * 1000) + 400)
                        .setExtraStart(105 * 1000).setExtraEnd((106 * 1000) + 600), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_38_extra))
                .setExtraPhoto("bn_alphabet_38_extra.gif")
        );
        arModels.add(new ArModel(
                38, mApplication.getResources().getString(R.string.bn_alphabet_39),
                "bn_alphabet_39.gif", "bn_alphabet_39.sfb",
                new Voice(Voice.VOICE_BENGALI_ALPHABETS, (82 * 1000) + 500, (83 * 1000) +900)
                        .setExtraStart((107 * 1000) + 100).setExtraEnd(110 * 1000), subject
                ).setExtraName(mApplication.getResources().getString(R.string.bn_alphabet_39_extra))
                .setExtraPhoto("bn_alphabet_39_extra.jpg")
        );

        return arModels;
    }

    /**
     * @return The {@link List} of bengali alphabet of {@link ArModel}.
     */
    private List<ArModel> getBengaliNumbers() {
        Subject subject = new Subject(
                Subject.BENGALI_NUMBER, mApplication.getResources().getString(R.string.number_bengali),
                "bn_number_cover.jpg", new Language(Language.BENGALI, mApplication.getResources().getString(R.string.language_bengali)),
                Subject.URL_MODEL_BENGALI_NUMBERS
        );

        List<ArModel> arModels = new ArrayList<>();
        arModels.add(new ArModel(
                0, mApplication.getResources().getString(R.string.bn_number_1),
                "bn_number_1.gif", "bn_number_1.sfb",
                new Voice(Voice.VOICE_BENGALI_NUMBERS, 2300, 3200), subject
                ));
        arModels.add(new ArModel(
                1, mApplication.getResources().getString(R.string.bn_number_2),
                "bn_number_2.gif", "bn_number_2.sfb",
                new Voice(Voice.VOICE_BENGALI_NUMBERS, 4800, 5500), subject
        ));
        arModels.add(new ArModel(
                2, mApplication.getResources().getString(R.string.bn_number_3),
                "bn_number_3.gif", "bn_number_3.sfb",
                new Voice(Voice.VOICE_BENGALI_NUMBERS, 7300, 8300), subject
        ));
        arModels.add(new ArModel(
                3, mApplication.getResources().getString(R.string.bn_number_4),
                "bn_number_4.gif", "bn_number_4.sfb",
                new Voice(Voice.VOICE_BENGALI_NUMBERS, 9500, 10500), subject
        ));
        arModels.add(new ArModel(
                4, mApplication.getResources().getString(R.string.bn_number_5),
                "bn_number_5.gif", "bn_number_5.sfb",
                new Voice(Voice.VOICE_BENGALI_NUMBERS, 13000, 14000), subject
        ));
        arModels.add(new ArModel(
                5, mApplication.getResources().getString(R.string.bn_number_6),
                "bn_number_6.gif", "bn_number_6.sfb",
                new Voice(Voice.VOICE_BENGALI_NUMBERS, 15500, 16500), subject
        ));
        arModels.add(new ArModel(
                6, mApplication.getResources().getString(R.string.bn_number_7),
                "bn_number_7.gif", "bn_number_7.sfb",
                new Voice(Voice.VOICE_BENGALI_NUMBERS, 18000, 19100), subject
        ));
        arModels.add(new ArModel(
                7, mApplication.getResources().getString(R.string.bn_number_8),
                "bn_number_8.gif", "bn_number_8.sfb",
                new Voice(Voice.VOICE_BENGALI_NUMBERS, 21200, 22000), subject
        ));
        arModels.add(new ArModel(
                8, mApplication.getResources().getString(R.string.bn_number_9),
                "bn_number_9.gif", "bn_number_9.sfb",
                new Voice(Voice.VOICE_BENGALI_NUMBERS, 24100, 25500), subject
        ));

        return arModels;
    }

    /**
     * @return The {@link List} of english alphabet of {@link ArModel}.
     */
    private List<ArModel> getEnglishAlphabets() {
        Subject subject = new Subject(
                Subject.ENGLISH_ALPHABET, mApplication.getResources().getString(R.string.alphabet_english),
                "en_alphabet_cover.gif", new Language(Language.ENGLISH, mApplication.getResources().getString(R.string.language_english)),
                Subject.URL_MODEL_ENGLISH_ALPHABETS
        );

        List<ArModel> arModels = new ArrayList<>();
        arModels.add(new ArModel(
                0, mApplication.getResources().getString(R.string.capital_a),
                "en_alphabet_capital_a.gif", "en_alphabet_capital_a.sfb",
                new Voice(Voice.VOICE_ENGLISH_ALPHABETS, 1300, 2400)
                        .setExtraStart(2 * 1000).setExtraEnd(4 * 1000), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_apple))
                .setExtraPhoto("en_alphabet_capital_a_extra.gif")
        );
        arModels.add(new ArModel(
                1, mApplication.getResources().getString(R.string.capital_b),
                "en_alphabet_capital_b.gif", "en_alphabet_capital_b.sfb",
                new Voice(Voice.VOICE_ENGLISH_ALPHABETS, (3 * 1000), (4 * 1000) + 300)
                        .setExtraStart(5 * 1000).setExtraEnd((6 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_ball))
                .setExtraPhoto("en_alphabet_capital_b_extra.gif")
        );
        arModels.add(new ArModel(
                2, mApplication.getResources().getString(R.string.capital_c),
                "en_alphabet_capital_c.gif", "en_alphabet_capital_c.sfb",
                new Voice(Voice.VOICE_ENGLISH_ALPHABETS, (5 * 1000) + 300, (6 * 1000) + 500)
                        .setExtraStart(8 * 1000).setExtraEnd((9 * 1000) + 200), subject
                ).setExtraName(mApplication.getResources().getString(R.string.cat))
                .setExtraPhoto("en_animal_cat.gif")
        );
        arModels.add(new ArModel(
                3, mApplication.getResources().getString(R.string.capital_d),
                "en_alphabet_capital_d.gif", "en_alphabet_capital_d.sfb",
                new Voice(Voice.VOICE_ENGLISH_ALPHABETS, (7 * 1000) + 500, (8 * 1000) + 600)
                        .setExtraStart((10 * 1000) + 500).setExtraEnd((12 * 1000)), subject
                ).setExtraName(mApplication.getResources().getString(R.string.dog))
                .setExtraPhoto("en_animal_dog.gif")
        );
        arModels.add(new ArModel(
                4, mApplication.getResources().getString(R.string.capital_e),
                "en_alphabet_capital_e.gif", "en_alphabet_capital_e.sfb",
                new Voice(Voice.VOICE_ENGLISH_ALPHABETS, (9 * 1000) + 600, (10 * 1000) + 800)
                        .setExtraStart(13 * 1000).setExtraEnd((14 * 1000) + 800), subject
                ).setExtraName(mApplication.getResources().getString(R.string.elephant))
                .setExtraPhoto("en_animal_elephant.gif")
        );
        arModels.add(new ArModel(
                5, mApplication.getResources().getString(R.string.capital_f),
                "en_alphabet_capital_f.gif", "en_alphabet_capital_f.sfb",
                new Voice(Voice.VOICE_ENGLISH_ALPHABETS, (11 * 1000) + 300, (12 * 1000) + 500)
                        .setExtraStart(16 * 1000).setExtraEnd((17 * 1000) + 700), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_fish))
                .setExtraPhoto("en_alphabet_capital_f_extra.gif")
        );
        arModels.add(new ArModel(
                6, mApplication.getResources().getString(R.string.capital_g),
                "en_alphabet_capital_g.gif", "en_alphabet_capital_g.sfb",
                new Voice(Voice.VOICE_ENGLISH_ALPHABETS, (14 * 1000) + 200, (15 * 1000) + 500)
                        .setExtraStart(19 * 1000).setExtraEnd(21 * 1000), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_goat))
                .setExtraPhoto("en_alphabet_capital_g_extra.gif")
        );
        arModels.add(new ArModel(
                7, mApplication.getResources().getString(R.string.capital_h),
                "en_alphabet_capital_h.gif", "en_alphabet_capital_h.sfb",
                new Voice(Voice.VOICE_ENGLISH_ALPHABETS, (17 * 1000) + 200, (18 * 1000) + 300)
                        .setExtraStart(22 * 1000).setExtraEnd((23 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.horse))
                .setExtraPhoto("en_animal_horse.gif")
        );
        arModels.add(new ArModel(
                8, mApplication.getResources().getString(R.string.capital_i),
                "en_alphabet_capital_i.gif", "en_alphabet_capital_i.sfb",
                new Voice(Voice.VOICE_ENGLISH_ALPHABETS, (19 * 1000) + 300, (20 * 1000) + 900)
                        .setExtraStart(25 * 1000).setExtraEnd((26 * 1000) + 400), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_ice_cream))
                .setExtraPhoto("en_alphabet_capital_i_extra.gif")
        );
        arModels.add(new ArModel(
                9, mApplication.getResources().getString(R.string.capital_j),
                "en_alphabet_capital_j.gif", "en_alphabet_capital_j.sfb",
                new Voice(Voice.VOICE_ENGLISH_ALPHABETS, (22 * 1000) + 300, (23 * 1000) + 500)
                        .setExtraStart(27 * 1000).setExtraEnd(29 * 1000), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_jackfruit))
                .setExtraPhoto("en_alphabet_capital_j_extra.gif")
        );
        arModels.add(new ArModel(
                10, mApplication.getResources().getString(R.string.capital_k),
                "en_alphabet_capital_k.gif", "en_alphabet_capital_k.sfb",
                new Voice(Voice.VOICE_ENGLISH_ALPHABETS, (25 * 1000) + 300, (26 * 1000) + 500)
                        .setExtraStart(30 * 1000).setExtraEnd((31 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_kite))
                .setExtraPhoto("en_alphabet_capital_k_extra.gif")
        );
        arModels.add(new ArModel(
                11, mApplication.getResources().getString(R.string.capital_l),
                "en_alphabet_capital_l.gif", "en_alphabet_capital_l.sfb",
                new Voice(Voice.VOICE_ENGLISH_ALPHABETS, (28 * 1000) + 200, (29 * 1000) + 100)
                        .setExtraStart(33 * 1000).setExtraEnd((34 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.lion))
                .setExtraPhoto("en_animal_lion.gif")
        );
        arModels.add(new ArModel(
                12, mApplication.getResources().getString(R.string.capital_m),
                "en_alphabet_capital_m.gif", "en_alphabet_capital_m.sfb",
                new Voice(Voice.VOICE_ENGLISH_ALPHABETS, (30 * 1000) + 500, (31 * 1000) + 900)
                        .setExtraStart(36 * 1000).setExtraEnd((37 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_mango))
                .setExtraPhoto("en_alphabet_capital_m_extra.gif")
        );
        arModels.add(new ArModel(
                13, mApplication.getResources().getString(R.string.capital_n),
                "en_alphabet_capital_n.gif", "en_alphabet_capital_n.sfb",
                new Voice(Voice.VOICE_ENGLISH_ALPHABETS, (33 * 1000) + 200, (34 * 1000) + 300)
                        .setExtraStart(39 * 1000).setExtraEnd((40 * 1000) + 400), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_nest))
                .setExtraPhoto("en_alphabet_capital_n_extra.gif")
        );
        arModels.add(new ArModel(
                14, mApplication.getResources().getString(R.string.capital_o),
                "en_alphabet_capital_o.gif", "en_alphabet_capital_o.sfb",
                new Voice(Voice.VOICE_ENGLISH_ALPHABETS, (36 * 1000), (37 * 1000) + 100)
                        .setExtraStart((41 * 1000) + 500).setExtraEnd((43 * 1000) + 200), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_orange))
                .setExtraPhoto("en_alphabet_capital_o_extra.gif")
        );
        arModels.add(new ArModel(
                15, mApplication.getResources().getString(R.string.capital_p),
                "en_alphabet_capital_p.gif", "en_alphabet_capital_p.sfb",
                new Voice(Voice.VOICE_ENGLISH_ALPHABETS, (38 * 1000) + 800, (39 * 1000) + 700)
                        .setExtraStart(44 * 1000).setExtraEnd((45 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_parrot))
                .setExtraPhoto("en_alphabet_capital_p_extra.gif")
        );
        arModels.add(new ArModel(
                16, mApplication.getResources().getString(R.string.capital_q),
                "en_alphabet_capital_q.gif", "en_alphabet_capital_q.sfb",
                new Voice(Voice.VOICE_ENGLISH_ALPHABETS, (41 * 1000) + 500, (42 * 1000) + 900)
                        .setExtraStart(47 * 1000).setExtraEnd((48 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_queen))
                .setExtraPhoto("en_alphabet_capital_q_extra.gif")
        );
        arModels.add(new ArModel(
                17, mApplication.getResources().getString(R.string.capital_r),
                "en_alphabet_capital_r.gif", "en_alphabet_capital_r.sfb",
                new Voice(Voice.VOICE_ENGLISH_ALPHABETS, (44 * 1000) + 800, (45 * 1000) + 900)
                        .setExtraStart(50 * 1000).setExtraEnd((51 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet__rainbow))
                .setExtraPhoto("en_alphabet_capital_r_extra.gif")
        );
        arModels.add(new ArModel(
                18, mApplication.getResources().getString(R.string.capital_s),
                "en_alphabet_capital_s.gif", "en_alphabet_capital_s.sfb",
                new Voice(Voice.VOICE_ENGLISH_ALPHABETS, (48 * 1000), (49 * 1000) + 200)
                        .setExtraStart(53 * 1000).setExtraEnd((54 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_sweet))
                .setExtraPhoto("en_alphabet_capital_s_extra.gif")
        );
        arModels.add(new ArModel(
                19, mApplication.getResources().getString(R.string.capital_t),
                "en_alphabet_capital_t.gif", "en_alphabet_capital_t.sfb",
                new Voice(Voice.VOICE_ENGLISH_ALPHABETS, (51 * 1000), (52 * 1000) + 500)
                        .setExtraStart((55 * 1000) + 500).setExtraEnd((57 * 1000) + 400), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_tiger))
                .setExtraPhoto("en_alphabet_capital_t_extra.gif")
        );
        arModels.add(new ArModel(
                20, mApplication.getResources().getString(R.string.capital_u),
                "en_alphabet_capital_u.gif", "en_alphabet_capital_u.sfb",
                new Voice(Voice.VOICE_ENGLISH_ALPHABETS, (54 * 1000), (55 * 1000) + 300)
                        .setExtraStart(58 * 1000).setExtraEnd((59 * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_umbrella))
                .setExtraPhoto("en_alphabet_capital_u_extra.gif")
        );
        arModels.add(new ArModel(
                21, mApplication.getResources().getString(R.string.capital_v),
                "en_alphabet_capital_v.gif", "en_alphabet_capital_v.sfb",
                new Voice(Voice.VOICE_ENGLISH_ALPHABETS, (56 * 1000) + 500, (57 * 1000) + 800)
                        .setExtraStart((60 * 1000) + 500).setExtraEnd(((60 + 2) * 1000) + 200), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_vegetable))
                .setExtraPhoto("en_alphabet_capital_v_extra.gif")
        );
        arModels.add(new ArModel(
                22, mApplication.getResources().getString(R.string.capital_w),
                "en_alphabet_capital_w.gif", "en_alphabet_capital_w.sfb",
                new Voice(Voice.VOICE_ENGLISH_ALPHABETS, (61 * 1000), (62  * 1000) + 300)
                        .setExtraStart((60 + 4) * 1000).setExtraEnd(((60 + 5) * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_water))
                .setExtraPhoto("en_alphabet_capital_w_extra.gif")
        );
        arModels.add(new ArModel(
                23, mApplication.getResources().getString(R.string.capital_x),
                "en_alphabet_capital_x.gif", "en_alphabet_capital_x.sfb",
                new Voice(Voice.VOICE_ENGLISH_ALPHABETS, (63 * 1000) + 700, (64 * 1000) + 700)
                        .setExtraStart((60 + 7) * 1000).setExtraEnd(((60 + 8) * 1000) + 500), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_x_ray))
                .setExtraPhoto("en_alphabet_capital_x_extra.gif")
        );
        arModels.add(new ArModel(
                24, mApplication.getResources().getString(R.string.capital_y),
                "en_alphabet_capital_y.gif", "en_alphabet_capital_y.sfb",
                new Voice(Voice.VOICE_ENGLISH_ALPHABETS, (67 * 1000), (68 * 1000) + 500)
                        .setExtraStart((60 + 10) * 1000).setExtraEnd(((60 + 11) * 1000) + 700), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_yak))
                .setExtraPhoto("en_alphabet_capital_y_extra.gif")
        );
        arModels.add(new ArModel(
                25, mApplication.getResources().getString(R.string.capital_z),
                "en_alphabet_capital_z.gif", "en_alphabet_capital_z.sfb",
                new Voice(Voice.VOICE_ENGLISH_ALPHABETS, (69 * 1000), (70 * 1000) + 800)
                        .setExtraStart((60 + 13) * 1000).setExtraEnd(((60 + 14) * 1000) + 700), subject
                ).setExtraName(mApplication.getResources().getString(R.string.alphabet_zebra))
                .setExtraPhoto("en_alphabet_capital_z_extra.gif")
        );

        return arModels;
    }

    /**
     * @return The {@link List} of english number of {@link ArModel}.
     */
    private List<ArModel> getEnglishNumbers() {
        Subject subject = new Subject(
                Subject.ENGLISH_NUMBER, mApplication.getResources().getString(R.string.number_english),
                "en_number_cover.gif", new Language(Language.ENGLISH, mApplication.getResources().getString(R.string.language_english)),
                Subject.URL_MODEL_ENGLISH_NUMBERS
        );

        List<ArModel> arModels = new ArrayList<>();
        arModels.add(new ArModel(
                0, mApplication.getResources().getString(R.string.number_one),
                "en_number_1.gif", "en_number_1.sfb",
                new Voice(Voice.VOICE_ENGLISH_NUMBERS, 700, 1900), subject
                ));
        arModels.add(new ArModel(
                1, mApplication.getResources().getString(R.string.number_two),
                "en_number_2.gif", "en_number_2.sfb",
                new Voice(Voice.VOICE_ENGLISH_NUMBERS, (2 * 1000) + 500, (3 * 1000) + 800), subject
        ));
        arModels.add(new ArModel(
                2, mApplication.getResources().getString(R.string.number_three),
                "en_number_3.gif", "en_number_3.sfb",
                new Voice(Voice.VOICE_ENGLISH_NUMBERS, (4 * 1000) + 400, (5 * 1000) + 900), subject
        ));
        arModels.add(new ArModel(
                3, mApplication.getResources().getString(R.string.number_four),
                "en_number_4.gif", "en_number_4.sfb",
                new Voice(Voice.VOICE_ENGLISH_NUMBERS, (6 * 1000) + 600, (8 * 1000) + 200), subject
        ));
        arModels.add(new ArModel(
                4, mApplication.getResources().getString(R.string.number_five),
                "en_number_5.gif", "en_number_5.sfb",
                new Voice(Voice.VOICE_ENGLISH_NUMBERS, 9 * 1000, (10 * 1000) + 500), subject
        ));
        arModels.add(new ArModel(
                5, mApplication.getResources().getString(R.string.number_six),
                "en_number_6.gif", "en_number_6.sfb",
                new Voice(Voice.VOICE_ENGLISH_NUMBERS, 12 * 1000, 13 * 1000), subject
        ));
        arModels.add(new ArModel(
                6, mApplication.getResources().getString(R.string.number_seven),
                "en_number_7.gif", "en_number_7.sfb",
                new Voice(Voice.VOICE_ENGLISH_NUMBERS, 14 * 1000, 15 * 1000), subject
        ));
        arModels.add(new ArModel(
                7, mApplication.getResources().getString(R.string.number_eight),
                "en_number_8.gif", "en_number_8.sfb",
                new Voice(Voice.VOICE_ENGLISH_NUMBERS, (16 * 1000) + 500, (17 * 1000) + 200), subject
        ));
        arModels.add(new ArModel(
                8, mApplication.getResources().getString(R.string.number_nine),
                "en_number_9.gif", "en_number_9.sfb",
                new Voice(Voice.VOICE_ENGLISH_NUMBERS, (18 * 1000) + 400, (19 * 1000) + 700), subject
        ));

        return arModels;
    }

    /**
     * @return The {@link List} of english animal of {@link ArModel}.
     */
    private List<ArModel> getAnimals() {
        Subject subject = new Subject(
                Subject.ENGLISH_ANIMAL, mApplication.getResources().getString(R.string.animal_english),
                "en_animal_cover.gif", new Language(Language.ENGLISH, mApplication.getResources().getString(R.string.language_english)),
                Subject.URL_MODEL_ENGLISH_ANIMALS
        );

        List<ArModel> arModels = new ArrayList<>();
        arModels.add(new ArModel(
                0, mApplication.getResources().getString(R.string.bear),
                "en_animal_bear.gif", "en_animal_bear.sfb",
                new Voice(Voice.VOICE_ENGLISH_ANIMALS, 500, 1800), subject
                ));
        arModels.add(new ArModel(
                1, mApplication.getResources().getString(R.string.cat),
                "en_animal_cat.gif", "en_animal_cat.sfb",
                new Voice(Voice.VOICE_ENGLISH_ANIMALS, (2 * 1000) + 500, (3 * 1000) + 700), subject
        ));
        arModels.add(new ArModel(
                2, mApplication.getResources().getString(R.string.cow),
                "en_animal_cow.gif", "en_animal_cow.sfb",
                new Voice(Voice.VOICE_ENGLISH_ANIMALS, (4 * 1000) + 500, 6 * 1000), subject
        ));
        arModels.add(new ArModel(
                3, mApplication.getResources().getString(R.string.deer),
                "en_animal_deer.gif", "en_animal_deer.sfb",
                new Voice(Voice.VOICE_ENGLISH_ANIMALS, 7 * 1000, 8 * 1000), subject
        ));
        arModels.add(new ArModel(
                4, mApplication.getResources().getString(R.string.dog),
                "en_animal_dog.gif", "en_animal_dog.sfb",
                new Voice(Voice.VOICE_ENGLISH_ANIMALS, 9 * 1000, (9 * 1000) + 900), subject
        ));
        arModels.add(new ArModel(
                5, mApplication.getResources().getString(R.string.elephant),
                "en_animal_elephant.gif", "en_animal_elephant.sfb",
                new Voice(Voice.VOICE_ENGLISH_ANIMALS, 11 * 1000, (11 * 1000) + 900), subject
        ));
        arModels.add(new ArModel(
                6, mApplication.getResources().getString(R.string.hippopotamus),
                "en_animal_hippopotamus.gif", "en_animal_hippopotamus.sfb",
                new Voice(Voice.VOICE_ENGLISH_ANIMALS, 13 * 1000, (14 * 1000) + 400), subject
        ));
        arModels.add(new ArModel(
                7, mApplication.getResources().getString(R.string.horse),
                "en_animal_horse.gif", "en_animal_horse.sfb",
                new Voice(Voice.VOICE_ENGLISH_ANIMALS, 15 * 1000, 16 * 1000), subject
        ));
        arModels.add(new ArModel(
                8, mApplication.getResources().getString(R.string.lion),
                "en_animal_lion.gif", "en_animal_lion.sfb",
                new Voice(Voice.VOICE_ENGLISH_ANIMALS, (16 * 1000) + 500, 18 * 1000), subject
        ));

        return arModels;
    }

    /**
     * @return A {@link LiveData} {@link List} of {@link ArModel} for a given {@link Subject}.
     * to be observed.
     */
    public LiveData<List<ArModel>> getArModelsLivedData(int subjectId) {
        if (mMutableLiveDataListArModel == null) {
            mMutableLiveDataListArModel = new MutableLiveData<>();

            // The list of items (ArModel) to be set in mMutableLiveDataList
            List<ArModel> arModels = new ArrayList<>();
            // Data should be fetched from cloud but we use static data here
            switch (subjectId) {
                case Subject.BENGALI_VOWEL:
                    arModels = getBengaliVowels();
                    break;
                case Subject.BENGALI_ALPHABET:
                    arModels = getBengaliAlphabets();
                    break;
                case Subject.BENGALI_NUMBER:
                    arModels = getBengaliNumbers();
                    break;
                case Subject.ENGLISH_ALPHABET:
                    arModels = getEnglishAlphabets();
                    break;
                case Subject.ENGLISH_NUMBER:
                    arModels = getEnglishNumbers();
                    break;
                case Subject.ENGLISH_ANIMAL:
                    arModels = getAnimals();
                    break;
            }

            // Set the value of MutableLiveData
            mMutableLiveDataListArModel.setValue(arModels);
        }

        return mMutableLiveDataListArModel;
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
    public LiveData<List<Subject>> getSubjectsLivedData(int languageId) {
        mMutableLiveDataListSubject = new MutableLiveData<>();

        // The list of items (ArModel) to be set in mMutableLiveDataList
        List<Subject> subjects = new ArrayList<>();
        // Data should be fetched from cloud but we use static data here
        switch (languageId) {
            case Language.BENGALI:
                subjects.add(new Subject(
                        Subject.BENGALI_VOWEL, mApplication.getResources().getString(R.string.vowel_bengali),
                        "bn_vowel_cover.jpg", new Language(Language.BENGALI, mApplication.getResources().getString(R.string.language_bengali)),
                        Subject.URL_MODEL_BENGALI_VOWELS
                ));
                subjects.add(new Subject(
                        Subject.BENGALI_ALPHABET, mApplication.getResources().getString(R.string.alphabet_bengali),
                        "bn_alphabet_cover.jpg", new Language(Language.BENGALI, mApplication.getResources().getString(R.string.language_bengali)),
                        Subject.URL_MODEL_BENGALI_ALPHABETS
                ));
                subjects.add(new Subject(
                        Subject.BENGALI_NUMBER, mApplication.getResources().getString(R.string.number_bengali),
                        "bn_number_cover.jpg", new Language(Language.BENGALI, mApplication.getResources().getString(R.string.language_bengali)),
                        Subject.URL_MODEL_BENGALI_NUMBERS
                ));
                break;
            case Language.ENGLISH:
                subjects.add(new Subject(
                        Subject.ENGLISH_ALPHABET, mApplication.getResources().getString(R.string.alphabet_english),
                        "en_alphabet_cover.gif", new Language(Language.ENGLISH, mApplication.getResources().getString(R.string.language_english)),
                        Subject.URL_MODEL_ENGLISH_ALPHABETS
                ));
                subjects.add(new Subject(
                        Subject.ENGLISH_NUMBER, mApplication.getResources().getString(R.string.number_english),
                        "en_number_cover.gif", new Language(Language.ENGLISH, mApplication.getResources().getString(R.string.language_english)),
                        Subject.URL_MODEL_ENGLISH_NUMBERS
                ));
                subjects.add(new Subject(
                        Subject.ENGLISH_ANIMAL, mApplication.getResources().getString(R.string.animal_english),
                        "en_animal_cover.gif", new Language(Language.ENGLISH, mApplication.getResources().getString(R.string.language_english)),
                        Subject.URL_MODEL_ENGLISH_ANIMALS
                ));
                break;
        }

        // Set the value of MutableLiveData
        mMutableLiveDataListSubject.setValue(subjects);

        return mMutableLiveDataListSubject;
    }
}
