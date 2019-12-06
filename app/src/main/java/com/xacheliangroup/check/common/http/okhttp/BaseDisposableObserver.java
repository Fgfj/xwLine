package com.xacheliangroup.check.common.http.okhttp;

import android.text.TextUtils;


import com.xacheliangroup.check.common.api.AppCache;
import com.xacheliangroup.check.common.log.LogUtils;
import com.xacheliangroup.check.utils.NetworkUtil;
import com.xacheliangroup.check.utils.ToastUtils;

import java.net.SocketTimeoutException;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

/**
 * Created by yangshuai on 2017/10/9.
 * {link http://afra55.github.io}
 */

public abstract class BaseDisposableObserver<T> extends DisposableObserver<T> {

    @Override
    public void onNext(@NonNull T t) {
        try {
            onSuccess(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        Throwable throwable = e;
        //获取最根源的异常
        while (throwable.getCause() != null) {
            e = throwable;
            throwable = throwable.getCause();
        }
        //无网络连接
        if (!NetworkUtil.isNetAvailable(AppCache.getContext())
                || e instanceof SocketTimeoutException) {
            onFailure(1, e.getMessage());
            ToastUtils.showToast(AppCache.getContext(), "网络不正常,请检查网络");
        } else if (e instanceof HttpRuntimeException) {
            //业务异常
            HttpRuntimeException exception = (HttpRuntimeException) e;
            int errorCode = 0;
            try {
                errorCode = Integer.parseInt(exception.getErrorCode());
            } catch (NumberFormatException e1) {
                e1.printStackTrace();
            }
            onFailure(errorCode, exception.getErrorMsg());
            if (errorCode == 401 || (!TextUtils.isEmpty(exception.getErrorMsg()) && exception.getErrorMsg().contains("登录过期"))) {
                ToastUtils.showToast(AppCache.getContext(), "您的登录已过期，请重新登录");
                return;
            }
            String message = e.getMessage();
            if (!TextUtils.isEmpty(message)) {
//                ToastUtils.showToast(AppCache.getContext(), message);
                LogUtils.e("业务异常", message);
            }
        } else if (e instanceof HttpException) {
            //网络连接异常
            HttpException httpException = (HttpException) e;
            int errorCode = httpException.code();
            onFailure(errorCode,
                    httpException.getMessage());
            if (errorCode == 401 || httpException.getMessage().contains("登录过期")) {
                ToastUtils.showToast(AppCache.getContext(), "您的登录已过期，请重新登录");
            }
        } else {
            //其他异常
            onFailure(0, e.getMessage());
            if (!TextUtils.isEmpty(e.getMessage())) {
                if (!e.getMessage().contains("Attempt to invoke interface method")) {
//                    ToastUtils.showToast(AppCache.getContext(), e.getMessage());
                    LogUtils.e("其他异常", e.getMessage());
                } else {
                    ToastUtils.showToast(AppCache.getContext(), "获取数据失败，请稍后再试");
                }
            }
        }
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(T t);

    public abstract void onFailure(int errorCode, String errorMsg);
}
