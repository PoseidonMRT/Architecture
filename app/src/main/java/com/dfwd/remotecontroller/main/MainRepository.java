package com.dfwd.remotecontroller.main;

import com.dfwd.remotecontroller.common.base.BaseRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Class description
 *
 * @author tuozhaobing
 * @date 2018/5/24
 */
public class MainRepository extends BaseRepository {

    private volatile static MainRepository INSTANCE = null;

      public static MainRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (MainRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MainRepository();
                }
            }
        }
        return INSTANCE;
    }

    private MainRepository(){
    }

    public Observable<List<String>> loadPosts(){
        return Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(ObservableEmitter<List<String>> emitter) throws Exception {
                Thread.sleep(10000);
                List<String> datas = new ArrayList<>();
                for (int i=1;i <= 100;i++){
                    datas.add("Item Index "+i);
                }
                emitter.onNext(datas);
                emitter.onComplete();
            }
        });
    }
}
