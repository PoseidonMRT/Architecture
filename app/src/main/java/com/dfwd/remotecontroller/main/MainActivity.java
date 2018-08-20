package com.dfwd.remotecontroller.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dfwd.remotecontroller.R;
import com.dfwd.remotecontroller.common.ViewModelFactory;
import com.dfwd.remotecontroller.common.base.BaseActivity;

import java.util.List;

/**
 * Class description
 *
 * @author tuozhaobing
 * @date 2018/5/24
 */
public class MainActivity extends BaseActivity<MainViewModel> {

    private ListView mListView;
    private ArrayAdapter<String> mStringArrayAdapter;

    @Override
    public void createLayout() {
        setContentView(R.layout.activity_main);
    }

    @NonNull
    @Override
    public MainViewModel createViewModel(ViewModelFactory factory) {
        return ViewModelProviders.of(this,factory).get(MainViewModel.class);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        mListView = findViewById(R.id.list);
    }

        @Override
    public void initData() {
        getViewModel().getLoadingState().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    showLoading();
                }else {
                    hideLoading();
                }
            }
        });
        getViewModel().getPosts().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> strings) {
                displayList(strings);
            }
        });
    }

    private void displayList(List<String> datas) {
        if (mStringArrayAdapter == null){
            mStringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas);
            mListView.setAdapter(mStringArrayAdapter);
        }else{
            mStringArrayAdapter.clear();
            mStringArrayAdapter.addAll(datas);
        }
    }
}
