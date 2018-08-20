package com.dfwd.remotecontroller.main;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.dfwd.remotecontroller.common.base.BaseViewModel;
import com.dfwd.remotecontroller.common.rxjava.BaseSchedulerProvider;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Class description
 *
 * @author tuozhaobing
 * @date 2018/5/24
 */
public class MainViewModel extends BaseViewModel<MainRepository> {

    private Application mApplication;

    private MutableLiveData<List<String>> mPostsData;

    private MutableLiveData<Boolean> mLoadingState;

    private MainRepository mMainRepository;

    private BaseSchedulerProvider mSchedulerProvider;

    public MainViewModel(@NonNull Application application, MainRepository baseRepository, BaseSchedulerProvider baseSchedulerProvider) {
        super(application);
        mApplication = application;
        mMainRepository = baseRepository;
        mSchedulerProvider = baseSchedulerProvider;
    }

    public LiveData<List<String>> getPosts() {
        if (mPostsData == null) {
            mPostsData = new MutableLiveData<>();
        }
        if (mLoadingState == null) {
            mLoadingState = new MutableLiveData<>();
        }
        mLoadingState.setValue(true);
        loadPosts();
        return mPostsData;
    }

    public LiveData<Boolean> getLoadingState() {
        if (mLoadingState == null) {
            mLoadingState = new MutableLiveData<>();
        }
        return mLoadingState;
    }

    private void loadPosts() {
        mMainRepository.loadPosts()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> strings) throws Exception {
                        mLoadingState.setValue(false);
                        mPostsData.setValue(strings);
                    }
                });
    }

}
