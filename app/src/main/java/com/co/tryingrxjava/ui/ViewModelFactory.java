package com.co.tryingrxjava.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.co.tryingrxjava.DataSource;
import com.co.tryingrxjava.ui.main.MainViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final DataSource dataSource;

    public ViewModelFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(dataSource);
        }
        throw new IllegalArgumentException("Unknown Viewmodel class");
    }
}
