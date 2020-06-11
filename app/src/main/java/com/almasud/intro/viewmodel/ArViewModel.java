package com.almasud.intro.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.almasud.intro.BaseApplication;
import com.almasud.intro.R;
import com.almasud.intro.model.ArModel;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@link AndroidViewModel} of the {@link ArModel}.
 *
 * @author Abdullah Almasud
 */
public class ArViewModel extends AndroidViewModel {
    private MutableLiveData<List<ArModel>> mMutableAlphabetLiveData, mMutableNumberLiveData, mMutableAnimalLiveData;
    private Context mContext;

    public ArViewModel(@NonNull Application application) {
        super(application);
        mContext = application;
    }

    /**
     * @return The {@link List} of alphabet type {@link ArModel}'s {@link LiveData} to be observed.
     */
    public LiveData<List<ArModel>> getAlphabetsLivedData() {
        if (mMutableAlphabetLiveData == null) {
            mMutableAlphabetLiveData = new MutableLiveData<>();

            // The list of items (Alphabet) to be set in mMutableAlphabetLiveData
            List<ArModel> arModels = new ArrayList<>();
            arModels.add(new ArModel(0, BaseApplication.MODEL_ALPHABET,
                    mContext.getResources().getString(R.string.capital_a),
                    R.drawable.capital_a, "models/alphabets/capital_a.sfb")
                    .setLabelName(mContext.getResources().getString(R.string.alphabet_apple))
                    .setLabelPhoto(R.drawable.alphabet_apple)
            );
            arModels.add(new ArModel(1, BaseApplication.MODEL_ALPHABET,
                    mContext.getResources().getString(R.string.capital_b),
                    R.drawable.capital_b, "models/alphabets/capital_b.sfb")
                    .setLabelName(mContext.getResources().getString(R.string.alphabet_ball))
                    .setLabelPhoto(R.drawable.alphabet_ball)
            );
            arModels.add(new ArModel(2, BaseApplication.MODEL_ALPHABET,
                    mContext.getResources().getString(R.string.capital_c),
                    R.drawable.capital_c, "models/alphabets/capital_c.sfb")
                    .setLabelName(mContext.getResources().getString(R.string.cat))
                    .setLabelPhoto(R.drawable.cat)
            );
            arModels.add(new ArModel(3, BaseApplication.MODEL_ALPHABET,
                    mContext.getResources().getString(R.string.capital_d),
                    R.drawable.capital_d, "models/alphabets/capital_d.sfb")
                    .setLabelName(mContext.getResources().getString(R.string.dog))
                    .setLabelPhoto(R.drawable.dog)
            );
            arModels.add(new ArModel(4, BaseApplication.MODEL_ALPHABET,
                    mContext.getResources().getString(R.string.capital_e),
                    R.drawable.capital_e, "models/alphabets/capital_e.sfb")
                    .setLabelName(mContext.getResources().getString(R.string.elephant))
                    .setLabelPhoto(R.drawable.elephant)
            );
            arModels.add(new ArModel(5, BaseApplication.MODEL_ALPHABET,
                    mContext.getResources().getString(R.string.capital_f),
                    R.drawable.capital_f, "models/alphabets/capital_f.sfb")
                    .setLabelName(mContext.getResources().getString(R.string.alphabet_fish))
                    .setLabelPhoto(R.drawable.alphabet_fish)
            );
            arModels.add(new ArModel(6, BaseApplication.MODEL_ALPHABET,
                    mContext.getResources().getString(R.string.capital_g),
                    R.drawable.capital_g, "models/alphabets/capital_g.sfb")
                    .setLabelName(mContext.getResources().getString(R.string.alphabet_goat))
                    .setLabelPhoto(R.drawable.alphabet_goat)
            );
            arModels.add(new ArModel(7, BaseApplication.MODEL_ALPHABET,
                    mContext.getResources().getString(R.string.capital_h),
                    R.drawable.capital_h, "models/alphabets/capital_h.sfb")
                    .setLabelName(mContext.getResources().getString(R.string.horse))
                    .setLabelPhoto(R.drawable.horse)
            );
            arModels.add(new ArModel(8, BaseApplication.MODEL_ALPHABET,
                    mContext.getResources().getString(R.string.capital_i),
                    R.drawable.capital_i, "models/alphabets/capital_i.sfb")
                    .setLabelName(mContext.getResources().getString(R.string.alphabet_ice_cream))
                    .setLabelPhoto(R.drawable.alphabet_ice_cream)
            );
            arModels.add(new ArModel(9, BaseApplication.MODEL_ALPHABET,
                    mContext.getResources().getString(R.string.capital_j),
                    R.drawable.capital_j, "models/alphabets/capital_j.sfb")
                    .setLabelName(mContext.getResources().getString(R.string.alphabet_jackfruit))
                    .setLabelPhoto(R.drawable.alphabet_jackfruit)
            );
            arModels.add(new ArModel(10, BaseApplication.MODEL_ALPHABET,
                    mContext.getResources().getString(R.string.capital_k),
                    R.drawable.capital_k, "models/alphabets/capital_k.sfb")
                    .setLabelName(mContext.getResources().getString(R.string.alphabet_kite))
                    .setLabelPhoto(R.drawable.alphabet_kite)
            );
            arModels.add(new ArModel(11, BaseApplication.MODEL_ALPHABET,
                    mContext.getResources().getString(R.string.capital_l),
                    R.drawable.capital_l, "models/alphabets/capital_l.sfb")
                    .setLabelName(mContext.getResources().getString(R.string.lion))
                    .setLabelPhoto(R.drawable.lion)
            );
            arModels.add(new ArModel(12, BaseApplication.MODEL_ALPHABET,
                    mContext.getResources().getString(R.string.capital_m),
                    R.drawable.capital_m, "models/alphabets/capital_m.sfb")
                    .setLabelName(mContext.getResources().getString(R.string.alphabet_mango))
                    .setLabelPhoto(R.drawable.alphabet_mango)
            );
            arModels.add(new ArModel(13, BaseApplication.MODEL_ALPHABET,
                    mContext.getResources().getString(R.string.capital_n),
                    R.drawable.capital_n, "models/alphabets/capital_n.sfb")
                    .setLabelName(mContext.getResources().getString(R.string.alphabet_nest))
                    .setLabelPhoto(R.drawable.alphabet_nest)
            );
            arModels.add(new ArModel(14, BaseApplication.MODEL_ALPHABET,
                    mContext.getResources().getString(R.string.capital_o),
                    R.drawable.capital_o, "models/alphabets/capital_o.sfb")
                    .setLabelName(mContext.getResources().getString(R.string.alphabet_orange))
                    .setLabelPhoto(R.drawable.alphabet_orange)
            );
            arModels.add(new ArModel(15, BaseApplication.MODEL_ALPHABET,
                    mContext.getResources().getString(R.string.capital_p),
                    R.drawable.capital_p, "models/alphabets/capital_p.sfb")
                    .setLabelName(mContext.getResources().getString(R.string.alphabet_parrot))
                    .setLabelPhoto(R.drawable.alphabet_parrot)
            );
            arModels.add(new ArModel(16, BaseApplication.MODEL_ALPHABET,
                    mContext.getResources().getString(R.string.capital_q),
                    R.drawable.capital_q, "models/alphabets/capital_q.sfb")
                    .setLabelName(mContext.getResources().getString(R.string.alphabet_queen))
                    .setLabelPhoto(R.drawable.alphabet_queen)
            );
            arModels.add(new ArModel(17, BaseApplication.MODEL_ALPHABET,
                    mContext.getResources().getString(R.string.capital_r),
                    R.drawable.capital_r, "models/alphabets/capital_r.sfb")
                    .setLabelName(mContext.getResources().getString(R.string.alphabet__rainbow))
                    .setLabelPhoto(R.drawable.alphabet_rainbow)
            );
            arModels.add(new ArModel(18, BaseApplication.MODEL_ALPHABET,
                    mContext.getResources().getString(R.string.capital_s),
                    R.drawable.capital_s, "models/alphabets/capital_s.sfb")
                    .setLabelName(mContext.getResources().getString(R.string.alphabet_sweet))
                    .setLabelPhoto(R.drawable.alphabet_sweet)
            );
            arModels.add(new ArModel(19, BaseApplication.MODEL_ALPHABET,
                    mContext.getResources().getString(R.string.capital_t),
                    R.drawable.capital_t, "models/alphabets/capital_t.sfb")
                    .setLabelName(mContext.getResources().getString(R.string.alphabet_tiger))
                    .setLabelPhoto(R.drawable.alphabet_tiger)
            );
            arModels.add(new ArModel(20, BaseApplication.MODEL_ALPHABET,
                    mContext.getResources().getString(R.string.capital_u),
                    R.drawable.capital_u, "models/alphabets/capital_u.sfb")
                    .setLabelName(mContext.getResources().getString(R.string.alphabet_umbrella))
                    .setLabelPhoto(R.drawable.alphabet_umbrealla)
            );
            arModels.add(new ArModel(21, BaseApplication.MODEL_ALPHABET,
                    mContext.getResources().getString(R.string.capital_v),
                    R.drawable.capital_v, "models/alphabets/capital_v.sfb")
                    .setLabelName(mContext.getResources().getString(R.string.alphabet_vegetable))
                    .setLabelPhoto(R.drawable.alphabet_vegetable)
            );
            arModels.add(new ArModel(22, BaseApplication.MODEL_ALPHABET,
                    mContext.getResources().getString(R.string.capital_w),
                    R.drawable.capital_w, "models/alphabets/capital_w.sfb")
                    .setLabelName(mContext.getResources().getString(R.string.alphabet_water))
                    .setLabelPhoto(R.drawable.alphabet_water_glass)
            );
            arModels.add(new ArModel(23, BaseApplication.MODEL_ALPHABET,
                    mContext.getResources().getString(R.string.capital_x),
                    R.drawable.capital_x, "models/alphabets/capital_x.sfb")
                    .setLabelName(mContext.getResources().getString(R.string.alphabet_x_ray))
                    .setLabelPhoto(R.drawable.alphabet_x_ray)
            );
            arModels.add(new ArModel(24, BaseApplication.MODEL_ALPHABET,
                    mContext.getResources().getString(R.string.capital_y),
                    R.drawable.capital_y, "models/alphabets/capital_y.sfb")
                    .setLabelName(mContext.getResources().getString(R.string.alphabet_yak))
                    .setLabelPhoto(R.drawable.alphabet_yak)
            );
            arModels.add(new ArModel(25, BaseApplication.MODEL_ALPHABET,
                    mContext.getResources().getString(R.string.capital_z),
                    R.drawable.capital_z, "models/alphabets/capital_z.sfb")
                    .setLabelName(mContext.getResources().getString(R.string.alphabet_zebra))
                    .setLabelPhoto(R.drawable.alphabet_zebra)
            );

            // Set the value of mMutableLiveData
            mMutableAlphabetLiveData.setValue(arModels);
        }

        return mMutableAlphabetLiveData;
    }

    /**
     * @return The {@link List} of number type {@link ArModel}'s {@link LiveData} to be observed.
     */
    public LiveData<List<ArModel>> getNumbersLivedData() {
        if (mMutableNumberLiveData == null) {
            mMutableNumberLiveData = new MutableLiveData<>();

            // The list of items (Number) to be set in mMutableNumberLiveData
            List<ArModel> arModels = new ArrayList<>();
            arModels.add(new ArModel(0, BaseApplication.MODEL_NUMBER,
                    mContext.getResources().getString(R.string.number_one), R.drawable.number_1,
                    "models/numbers/number_1.sfb")
            );
            arModels.add(new ArModel(1, BaseApplication.MODEL_NUMBER,
                    mContext.getResources().getString(R.string.number_two), R.drawable.number_2,
                    "models/numbers/number_2.sfb")
            );
            arModels.add(new ArModel(2, BaseApplication.MODEL_NUMBER,
                    mContext.getResources().getString(R.string.number_three), R.drawable.number_3,
                    "models/numbers/number_3.sfb")
            );
            arModels.add(new ArModel(3, BaseApplication.MODEL_NUMBER,
                    mContext.getResources().getString(R.string.number_four), R.drawable.number_4,
                    "models/numbers/number_4.sfb")
            );
            arModels.add(new ArModel(4, BaseApplication.MODEL_NUMBER,
                    mContext.getResources().getString(R.string.number_five), R.drawable.number_5,
                    "models/numbers/number_5.sfb")
            );
            arModels.add(new ArModel(5, BaseApplication.MODEL_NUMBER,
                    mContext.getResources().getString(R.string.number_six), R.drawable.number_6,
                    "models/numbers/number_6.sfb")
            );
            arModels.add(new ArModel(6, BaseApplication.MODEL_NUMBER,
                    mContext.getResources().getString(R.string.number_seven), R.drawable.number_7,
                    "models/numbers/number_7.sfb")
            );
            arModels.add(new ArModel(7, BaseApplication.MODEL_NUMBER,
                    mContext.getResources().getString(R.string.number_eight), R.drawable.number_8,
                    "models/numbers/number_8.sfb")
            );
            arModels.add(new ArModel(8, BaseApplication.MODEL_NUMBER,
                    mContext.getResources().getString(R.string.number_nine), R.drawable.number_9,
                    "models/numbers/number_9.sfb")
            );

            // Set the value of mMutableLiveData
            mMutableNumberLiveData.setValue(arModels);
        }

        return mMutableNumberLiveData;
    }

    /**
     * @return The {@link List} of animal type {@link ArModel}'s {@link LiveData} to be observed.
     */
    public LiveData<List<ArModel>> getAnimalsLivedData() {
        if (mMutableAnimalLiveData == null) {
            mMutableAnimalLiveData = new MutableLiveData<>();

            // The list of items (Animal) to be set in mMutableAnimalLiveData
            List<ArModel> arModels = new ArrayList<>();
            arModels.add(new ArModel(0, BaseApplication.MODEL_ANIMAL,
                    mContext.getResources().getString(R.string.bear), R.drawable.bear,
                    "models/animals/bear.sfb")
            );
            arModels.add(new ArModel(1, BaseApplication.MODEL_ANIMAL,
                    mContext.getResources().getString(R.string.cat), R.drawable.cat,
                    "models/animals/cat.sfb")
            );
            arModels.add(new ArModel(2, BaseApplication.MODEL_ANIMAL,
                    mContext.getResources().getString(R.string.cow), R.drawable.cow,
                    "models/animals/cow.sfb")
            );
            arModels.add(new ArModel(3, BaseApplication.MODEL_ANIMAL,
                    mContext.getResources().getString(R.string.deer), R.drawable.deer,
                    "models/animals/deer.sfb")
            );
            arModels.add(new ArModel(4, BaseApplication.MODEL_ANIMAL,
                    mContext.getResources().getString(R.string.dog), R.drawable.dog,
                    "models/animals/dog.sfb")
            );
            arModels.add(new ArModel(5, BaseApplication.MODEL_ANIMAL,
                    mContext.getResources().getString(R.string.elephant), R.drawable.elephant,
                    "models/animals/elephant.sfb")
            );
            arModels.add(new ArModel(6, BaseApplication.MODEL_ANIMAL,
                    mContext.getResources().getString(R.string.hippopotamus), R.drawable.hippopotamus,
                    "models/animals/hippopotamus.sfb")
            );
            arModels.add(new ArModel(7, BaseApplication.MODEL_ANIMAL,
                    mContext.getResources().getString(R.string.horse), R.drawable.horse,
                    "models/animals/horse.sfb")
            );
            arModels.add(new ArModel(8, BaseApplication.MODEL_ANIMAL,
                    mContext.getResources().getString(R.string.lion), R.drawable.lion,
                    "models/animals/lion.sfb")
            );

            // Set the value of mMutableLiveData
            mMutableAnimalLiveData.setValue(arModels);
        }

        return mMutableAnimalLiveData;
    }
}
