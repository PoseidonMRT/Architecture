package com.dfwd.remotecontroller.common.rxjava;

import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;

/**
 * Class description
 *
 * @author tuozhaobing
 * @date 2018/5/24
 */
public interface BaseSchedulerProvider {

    @NonNull
    Scheduler computation();

    @NonNull
    Scheduler io();

    @NonNull
    Scheduler ui();
}
