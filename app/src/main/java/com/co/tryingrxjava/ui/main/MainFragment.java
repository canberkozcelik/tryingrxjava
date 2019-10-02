package com.co.tryingrxjava.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.co.tryingrxjava.DataSource;
import com.co.tryingrxjava.Injection;
import com.co.tryingrxjava.R;
import com.co.tryingrxjava.persistence.Task;
import com.co.tryingrxjava.ui.ViewModelFactory;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Main screen of the app. Displays a tasks.
 */
public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";
    private ViewModelFactory mViewModelFactory;
    private MainViewModel mViewModel;
    private final CompositeDisposable mDisposable = new CompositeDisposable();

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModelFactory = Injection.provideViewModelFactory(getContext());
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainViewModel.class);
        for (Task t :
                Task.createTasksList()) {
            mViewModel.updateTaskName(t.getName());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Subscribe to the emissions of the task name from the view model.
        // Update the task name text view, at every onNext emission.
        // In case of error, log the exception.
        mDisposable.add(mViewModel.getTaskName().
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(taskName -> Log.d(TAG, "onStart: " + taskName),
                        throwable -> Log.e(TAG, "onStart: Unable to get task name", throwable)));
    }

    @Override
    public void onStop() {
        super.onStop();

        // clear all the subscriptions
        mDisposable.clear();
    }
}
