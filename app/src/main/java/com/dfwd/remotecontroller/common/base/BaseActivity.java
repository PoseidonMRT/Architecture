package com.dfwd.remotecontroller.common.base;

import android.app.AlertDialog;
import android.arch.lifecycle.AndroidViewModel;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.dfwd.remotecontroller.R;
import com.dfwd.remotecontroller.common.ViewModelFactory;

/**
 * Class description
 *
 * @author tuozhaobing
 * @date 2018/5/24
 */
public abstract class BaseActivity<VM extends AndroidViewModel> extends AppCompatActivity {

    private VM mViewModel;
    private AlertDialog mAlertDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createLayout();
        initViewModel();
        initView(savedInstanceState);
        initData();
    }



    public abstract VM createViewModel(ViewModelFactory factory);

    public abstract void createLayout();

    public abstract void initView(@Nullable Bundle savedInstanceState);

    public abstract void initData();

    public void showLoading() {
        if (mAlertDialog!= null && mAlertDialog.isShowing()) {
            return;
        }
        mAlertDialog = new AlertDialog.Builder(this).create();
        mAlertDialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        mAlertDialog.setCancelable(false);
        mAlertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_SEARCH || keyCode == KeyEvent.KEYCODE_BACK) {
                    return true;
                }
                return false;
            }
        });
        mAlertDialog.show();
        mAlertDialog.setContentView(R.layout.loading);
        mAlertDialog.setCanceledOnTouchOutside(false);
    }

    public void hideLoading() {
        if (mAlertDialog != null) {
            mAlertDialog.hide();
            mAlertDialog = null;
        }
    }

    public VM getViewModel() {
        if (mViewModel == null) {
            throw new NullPointerException("[" + getClass().getCanonicalName() + "]:" + "ViewModel is null when call getViewModel method!");
        }
        return mViewModel;
    }

    private void initViewModel() {
        if (mViewModel == null) {
            mViewModel = createViewModel(ViewModelFactory.getInstance(getApplication()));
        }
    }
}
