package com.dom.Friend_IM.model.http.help;



import com.dom.Friend_IM.model.http.response.TestResult;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by dom4j on 2017/3/7.
 */

public class RxHelper {


    /**
     * 统一线程处理
     * @param <T>
     * @return
     */
   public static <T> ObservableTransformer<T,T> rxSchedulerHelper(){
       return new ObservableTransformer<T,T>() {
           @Override
           public ObservableSource<T> apply(Observable<T> upstream) {
               return upstream.subscribeOn(Schedulers.io())
                       .observeOn(AndroidSchedulers.mainThread());
           }
       };
   }

    /**
     * 统一结果处理
     * @param <T>
     * @return
     */
   public static <T> ObservableTransformer<TestResult<T>,T> handleResult(){
       return new ObservableTransformer<TestResult<T>, T>() {
           @Override
           public Observable<T> apply(Observable<TestResult<T>> upstream) {
               return upstream.flatMap(new Function<TestResult<T>, Observable<T>>() {
                   @Override
                   public Observable<T> apply(TestResult<T> tGankHttpResult) throws Exception {
                       if(!tGankHttpResult.getError()){
                           return createData(tGankHttpResult.getResults());
                       }else{
                           throw new ApiExcpetion("服务器异常");
                       }
                   }
               });
           }
       };
   }


    /**
     * 生成Observable
     * @param data
     * @param <T>
     * @return
     */
     public static <T> Observable<T> createData(final T data){
         return  Observable.create(new ObservableOnSubscribe<T>() {
              @Override
              public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                  try {
                      emitter.onNext(data);
                      emitter.onComplete();
                  }catch (Exception error){
                      emitter.onError(error);
                  }
              }
          });
     }

}
