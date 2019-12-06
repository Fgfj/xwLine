package com.xacheliangroup.check.common.banner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.xacheliangroup.check.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * author:yz
 * data: 2018/12/18,16:15
 */
public class BannerHelper {

    private ViewPager bannerVp;
    private Context context;
    private boolean isAuto;

    private BannerHelper() {
    }

    public static BannerHelper getInstance() {
        return new BannerHelper();
    }

    public BannerHelper start(Context context, boolean autoStart, ViewPager viewPager, final ArrayList<View> bannerViewArray, RadioGroup bannerIndicatorRg) {
        this.context = context;
        bannerVp = viewPager;
        bannerVp.setCurrentItem(1);
        bannerVp.setOffscreenPageLimit(bannerViewArray.size());
        this.isAuto = autoStart;

        if (bannerViewArray.size() > 1) {
            addChangeListener(bannerViewArray, bannerIndicatorRg);

            initIndicator(bannerViewArray, bannerIndicatorRg);

            beginPagerTimer();
        }

        return this;
    }

    private void initIndicator(ArrayList<View> bannerViewArray, RadioGroup bannerIndicatorRg) {
        bannerIndicatorRg.removeAllViews();
        for (int i = 0; i < bannerViewArray.size(); i++) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            RadioButton rbIndicator = (RadioButton) layoutInflater.inflate(
                    R.layout.ad_vp_indicator_rb, bannerIndicatorRg, false);
            if (bannerViewArray.size() > 3)
                if (i == 0) {
                    rbIndicator.setVisibility(View.GONE);
                } else if (i == bannerViewArray.size() - 1) {
                    rbIndicator.setVisibility(View.GONE);
                }
            bannerIndicatorRg.addView(rbIndicator);
        }
        ((RadioButton) bannerIndicatorRg.getChildAt(0)).setChecked(true);
    }

    private int lastCheckedPosition = 0;

    private void addChangeListener(final ArrayList<View> bannerViewArray, final RadioGroup bannerIndicatorRg) {
        bannerVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int willCheckedPosition;
                if (positionOffset >= 0.5) {
                    int nextPosition = position + 1;
                    if (nextPosition == bannerViewArray.size() - 1) {
                        willCheckedPosition = 1;
                    } else {
                        willCheckedPosition = nextPosition;
                    }
                } else if (position == 0) {
                    willCheckedPosition = bannerViewArray.size() - 2;
                } else {
                    willCheckedPosition = position;
                }
                if (willCheckedPosition != lastCheckedPosition) {
                    ((RadioButton) bannerIndicatorRg.getChildAt(lastCheckedPosition)).setChecked(false);
                }
                ((RadioButton) bannerIndicatorRg.getChildAt(willCheckedPosition)).setChecked(true);
                lastCheckedPosition = willCheckedPosition;
                if (bannerViewArray.size() <= 1) {
                    return;
                }
                if (position == 0 && positionOffset <= 0.01) {
                    bannerVp.setCurrentItem(bannerViewArray.size() - 2, false);
                } else if (position == bannerViewArray.size() - 1) {
                    bannerVp.setCurrentItem(1, false);
                }
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:// 如果用户点击viewpager，则取消自动翻页
                        pageTouch = true;
                        cancelPagerTimer();
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:// 如果用户点击viewpager并松开，则开启自动翻页
                        if (pageTouch) {
                            pageTouch = false;
                            beginPagerTimer();
                        }
                        break;
                }
            }
        });
    }


    /* 自动翻页功能 start */
    private Timer timer;
    private int index = 0;
    private MyTimerTask mTimerTask;
    /**
     * 自动翻页时间间隔
     **/
    private final int TIMER_OFFSET = 5 * 1000;
    /**
     * viewpager滚动速率
     **/
    private final int SPEED_OFFSET = 800;
    /**
     * 判断手指是否碰触viewpager
     **/
    private boolean pageTouch;

    /**
     * 创建或重置计时器
     */
    public void beginPagerTimer() {
        if (!isAuto) {
            return;
        }
        cancelPagerTimer();
        // 设置viewpaper自动播放
        timer = new Timer();
        if (mTimerTask != null) {
            mTimerTask.cancel(); // 将原任务从队列中移除
        }
        mTimerTask = new MyTimerTask(); // 新建一个任务
        timer.schedule(mTimerTask, TIMER_OFFSET, TIMER_OFFSET + SPEED_OFFSET);
    }

    /***
     * 停止翻页计时器
     */
    public void cancelPagerTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    /**
     * 定时器，实现自动播放
     */
    class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            Message message = new Message();
            message.what = 2;
            index = bannerVp.getCurrentItem();
            index++;
            handler.sendMessage(message);
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 2:
                    bannerVp.setCurrentItem(index);
                    break;
                default:
                    break;
            }
        }
    };
    /* 自动翻页功能 end */

    /**
     * 初始化viewpager资源
     *
     * @param bannerViewArray view list
     * @param startAndEndView 存储开始和结束的 view
     */
    public static void initViewList(ArrayList<View> bannerViewArray, ArrayList<View> startAndEndView) {
        if (bannerViewArray.size() <= 1) {
            return;
        }
        ArrayList<View> bannerViewArrayClone = (ArrayList<View>) bannerViewArray.clone();
        bannerViewArray.clear();
        for (int i = 0; i < bannerViewArrayClone.size() + 2; i++) {
            if (i <= 0) {
                bannerViewArray.add(startAndEndView.get(startAndEndView.size() - 1));
            } else if (i >= bannerViewArrayClone.size() + 1) {
                bannerViewArray.add(startAndEndView.get(0));
            } else {
                bannerViewArray.add(bannerViewArrayClone.get(i - 1));
            }
        }
    }
}
