package com.xacheliangroup.check.common.http.okhttp;


/**
 * Created by yangshuai on 2017/8/19.
 * {link http://afra55.github.io}
 */

public class IActionListener {
    /**
     * viewAction 接口的监听
     */
    public interface ViewAction<T> extends HttpBaseView {
        void showInfoView(String type, Object obj);
    }

    /**
     * presenter 接口的监听
     */
    interface PresenterAciton extends BasePresenter {

    }
}
