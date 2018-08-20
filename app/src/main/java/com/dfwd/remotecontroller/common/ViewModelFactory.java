package com.dfwd.remotecontroller.common;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.dfwd.remotecontroller.main.MainViewModel;
import com.dfwd.remotecontroller.common.rxjava.SchedulerProvider;

/**
 * Class description
 *
 * @author tuozhaobing
 * @date 2018/5/24
 */
public class ViewModelFactory extends ViewModelProvider.AndroidViewModelFactory {

    private Application mApplication;
    @SuppressLint("StaticFieldLeak")
    private static volatile ViewModelFactory INSTANCE;

    private ViewModelFactory(@NonNull Application application) {
        super(application);
        mApplication = application;
    }

    public static ViewModelFactory getInstance(Application application) {

        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(application);
                }
            }
        }
        return INSTANCE;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            //noinspection unchecked
            return (T) new MainViewModel(mApplication, Injection.provideMainRepository(mApplication), SchedulerProvider.getInstance());
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
