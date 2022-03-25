package com.ons.testapplication.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


import com.ons.testapplication.repository.Repository;

import javax.inject.Inject;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final Repository repository;

    @Inject
    public ViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ResponseDataViewModel.class)) {
            return (T) new ResponseDataViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}
