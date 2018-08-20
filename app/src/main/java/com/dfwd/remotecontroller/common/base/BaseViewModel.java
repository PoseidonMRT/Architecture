package com.dfwd.remotecontroller.common.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

/**
 * Class description
 *
 * @author tuozhaobing
 * @date 2018/5/24
 */
public class BaseViewModel<T extends BaseRepository> extends AndroidViewModel {
    public BaseViewModel(@NonNull Application application) {
        super(application);
    }
}
