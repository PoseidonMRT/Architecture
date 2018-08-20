package com.dfwd.remotecontroller.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;

import com.dfwd.remotecontroller.main.MainRepository;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Class description
 *
 * @author tuozhaobing
 * @date 2018/5/24
 */
public class Injection {

    @SuppressLint("RestrictedApi")
    public static MainRepository provideMainRepository(@NonNull Context context) {
        checkNotNull(context);
        return MainRepository.getInstance();
    }
}
