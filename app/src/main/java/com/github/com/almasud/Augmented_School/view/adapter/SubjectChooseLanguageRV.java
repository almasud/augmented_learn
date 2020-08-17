package com.github.com.almasud.Augmented_School.view.adapter;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.com.almasud.Augmented_School.BaseApplication;
import com.github.com.almasud.Augmented_School.databinding.SubjectChooseLanguageRowBinding;
import com.github.com.almasud.Augmented_School.model.entity.Language;
import com.github.com.almasud.Augmented_School.model.entity.Subject;
import com.github.com.almasud.Augmented_School.view.activity.SubjectChooseActivity;
import com.github.com.almasud.Augmented_School.viewmodel.ArVM;

import java.util.List;

/**
 * The adapter subclass of {@link RecyclerView.Adapter} for {@link SubjectChooseActivity}.
 *
 * @author Abdullah Almasud
 */
public class SubjectChooseLanguageRV extends RecyclerView.Adapter<SubjectChooseLanguageRV.LanguageViewHolder> {
    private static final String TAG = "SubjectChooseLanguageRV";
    private SubjectChooseLanguageRowBinding mViewBinding;
    private List<Language> mLanguages;
    private SubjectChooseActivity mActivity;
    private int mChooseService;
    private SubjectChooseSubjectRV mChooseSubjectRVAdapter;
    // An object of RecyclerView.RecycledViewPool is created to share the Views between
    // the child and the parent RecyclerViews
    private RecyclerView.RecycledViewPool mRecycledViewPool = new RecyclerView.RecycledViewPool();

    public SubjectChooseLanguageRV(List<Language> languages, SubjectChooseActivity activity, int chooseService) {
        mLanguages = languages;
        mActivity = activity;
        mChooseService = chooseService;
    }

    @NonNull
    @Override
    public LanguageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mViewBinding = SubjectChooseLanguageRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new LanguageViewHolder(mViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull LanguageViewHolder holder, int position) {
        // Get each instance and update it into UI
        Language language = mLanguages.get(position);
        holder.chooseLanguageRowBinding.textViewLanguage.setText(language.getName());
        // Add a font according to language type
        BaseApplication.changeTextViewFont(
                mActivity, language.getId(), Typeface.NORMAL,
                holder.chooseLanguageRowBinding.textViewLanguage
        );

        // Get an instance of ViewModel
        ArVM arVM = new ViewModelProvider(mActivity).get(ArVM.class);
        // Get the list of live data from ArViewModel
        LiveData<List<Subject>> subjectsLivedData = arVM.getSubjectsLivedData(language.getId());
        // Observe the list of live data
        subjectsLivedData.observe(mActivity, subjects -> {
            if (subjects.size() > 0) {
                // Initialize the RV adapter
                mChooseSubjectRVAdapter = new SubjectChooseSubjectRV(subjects, mActivity, mChooseService);
                // Set a layout manager to RecyclerView
                mViewBinding.rvSubject.setLayoutManager(
                        new LinearLayoutManager(
                                holder.chooseLanguageRowBinding.rvSubject.getContext(),
                                RecyclerView.HORIZONTAL, false
                        )
                );
                // Set the adapter to RecyclerView
                mViewBinding.rvSubject.setAdapter(mChooseSubjectRVAdapter);
                // Set the recycler view pool to the child RecyclerView to share common view between parent and child recycler view.
                mViewBinding.rvSubject.setRecycledViewPool(mRecycledViewPool);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLanguages.size();
    }

    public class LanguageViewHolder extends RecyclerView.ViewHolder {
        private SubjectChooseLanguageRowBinding chooseLanguageRowBinding;

        public LanguageViewHolder(@NonNull SubjectChooseLanguageRowBinding chooseLanguageRowBinding) {
            super(chooseLanguageRowBinding.getRoot());
            this.chooseLanguageRowBinding = chooseLanguageRowBinding;
        }
    }
}
