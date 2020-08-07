package com.github.com.almasud.Augmented_School.ui.adapter;

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
import com.github.com.almasud.Augmented_School.ui.activity.SubjectChooseActivity;
import com.github.com.almasud.Augmented_School.viewmodel.ArViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * The adapter subclass of {@link RecyclerView.Adapter} for {@link SubjectChooseActivity}.
 *
 * @author Abdullah Almasud
 */
public class SubjectChooseLanguageRVAdapter extends RecyclerView.Adapter<SubjectChooseLanguageRVAdapter.LanguageViewHolder> {
    private SubjectChooseLanguageRowBinding mViewBinding;
    private List<Language> mLanguages;
    private SubjectChooseActivity mActivity;
    private int mChooseService;
    private SubjectChooseSubjectRVAdapter mChooseSubjectRVAdapter;

    public SubjectChooseLanguageRVAdapter(List<Language> languages, SubjectChooseActivity activity, int chooseService) {
        mLanguages = languages;
        mActivity = activity;
        mChooseService = chooseService;
    }

    @NonNull
    @Override
    public LanguageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mViewBinding = SubjectChooseLanguageRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        // Initialize the RV adapter
        mChooseSubjectRVAdapter = new SubjectChooseSubjectRVAdapter(new ArrayList<>(), mActivity, mChooseService);
        // Set a layout manager to RV
        mViewBinding.rvSubject.setLayoutManager(new LinearLayoutManager(mActivity, RecyclerView.HORIZONTAL, false));
        // Set the adapter to RV
        mViewBinding.rvSubject.setAdapter(mChooseSubjectRVAdapter);

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

        // Get an instance of ArViewModel
        ArViewModel arViewModel = new ViewModelProvider(mActivity).get(ArViewModel.class);
        // Get the list of live data from ArViewModel
        LiveData<List<Subject>> subjectsLivedData = arViewModel.getSubjectsLivedData(language.getId());
        // Observe the list of live data
        subjectsLivedData.observe(mActivity, subjects -> {

            if (subjects.size() > 0) {
                // Update the adapter items
                mChooseSubjectRVAdapter.setSubjects(subjects);
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

    public void setLanguages(List<Language> languages) {
        mLanguages = languages;
        notifyDataSetChanged();
    }
}
