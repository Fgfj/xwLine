package com.xacheliangroup.check.common.base.pullrefresh.core;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.ScrollView;

import com.xacheliangroup.check.common.log.LogUtils;
import com.xacheliangroup.check.utils.ScreenUtil;


public abstract class InterceptLayout extends DrawLayout {

    protected boolean isAutoLoadMore = false;
    protected boolean isAutoLoading = false;
    protected boolean canAutoLoadMore = true;
    private AutoLoadMoreListener autoLoadMoreListener;

    public void setAutoLoadMore(boolean autoLoadMore, AutoLoadMoreListener listener) {
        isAutoLoadMore = autoLoadMore;
        this.autoLoadMoreListener = listener;
        setCanLoadMore(!isAutoLoadMore);
    }

    public void setCanAutoLoadMore(boolean canAutoLoadMore) {
        this.canAutoLoadMore = canAutoLoadMore;
    }

    public void setStopAutoLoadMore(boolean loading) {
        isAutoLoading = loading;
    }

    public void stopAutoLoadMore() {
        setStopAutoLoadMore(false);
    }

    public interface AutoLoadMoreListener {
        void onLoadMore();
    }

    private final static String TAG = LogUtils.makeLogTag(InterceptLayout.class);

    // 用于计算滑动距离的Y坐标中介
    public int lastYMove;
    // 用于判断是否拦截触摸事件的Y坐标中介
    public int lastYIntercept;

    //是否可以下拉刷新
    public boolean canPullRefresh = true;

    //是否可以上拉加载更多
    public boolean canLoadMore = true;

    //是否正在下拉刷新或加载更多中
    public boolean isLoading = false;

    public InterceptLayout(Context context) {
        super(context);
    }

    public InterceptLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (isAutoLoading) {
            return false;
        }
        if (isLoading) {
            //正在加载中时，直接拦截掉子控件的手势
            return true;
        }
        boolean intercept = false;
        // 记录此次触摸事件的y坐标
        int y = (int) event.getY();
        // 判断触摸事件类型
        switch (event.getAction()) {
            // Down事件
            case MotionEvent.ACTION_DOWN: {
                // 记录下本次系列触摸事件的起始点Y坐标
                lastYMove = y;
                // 不拦截ACTION_DOWN，因为当ACTION_DOWN被拦截，后续所有触摸事件都会被拦截
                intercept = false;
                LogUtils.i(TAG, "ACTION_DOWN");
                break;
            }
            // Move事件
            case MotionEvent.ACTION_MOVE: {
                LogUtils.i(TAG, "ACTION_MOVE");
                if (ViewConfiguration.getTouchSlop() > Math.abs(y - lastYMove)) {
                    intercept = false;
                } else if (y > lastYIntercept && canPullRefresh) { // 下滑操作
                    // 获取最顶部的子视图
                    View child = getFirstVisibleChild();
                    if (child == null) {
                        intercept = false;
                    } else if (child instanceof AdapterView) {
                        intercept = avPullDownIntercept(child);
                    } else if (child instanceof ScrollView) {
                        intercept = svPullDownIntercept(child);
                    } else if (child instanceof RecyclerView) {
                        intercept = rvPullDownIntercept(child);
                    }
                } else if (y < lastYIntercept && canLoadMore) { // 上拉操作
                    // 获取最底部的子视图
                    View child = getLastVisibleChild();
                    if (child == null) {
                        intercept = false;
                    } else if (child instanceof AdapterView) {
                        intercept = avPullUpIntercept(child);
                    } else if (child instanceof ScrollView) {
                        intercept = svPullUpIntercept(child);
                    } else if (child instanceof RecyclerView) {
                        intercept = rvPullUpIntercept(child);
                    }
                } else if (y < lastYIntercept && isAutoLoadMore) {
                    // 上啦，自动刷新
                    // 获取最底部的子视图
                    View child = getLastVisibleChild();
                    intercept = false;
                    if (child == null || autoLoadMoreListener == null) {
                        intercept = false;
                    } else if (child instanceof AdapterView) {
                        AdapterView adapterView = (AdapterView) child;
                        if (adapterView.getCount() > 0
                                ) {
                            if (adapterView.getCount() < 20) {
                                if (canAutoLoadMore && adapterView.getLastVisiblePosition()
                                        >= adapterView.getCount() / 3)
                                    autoLoadMoreListener.onLoadMore();
                            } else {
                                if (canAutoLoadMore && adapterView.getLastVisiblePosition()
                                        >= adapterView.getCount() - 8) {
                                    autoLoadMoreListener.onLoadMore();
                                }
                            }

                        }
                    } else if (child instanceof ScrollView) {
                        ScrollView scrollView = (ScrollView) child;
                        int screenHeight = ScreenUtil.getScreenHeight(getContext());
                        if (scrollView.getScrollY() >= screenHeight) {
                            if (canAutoLoadMore && scrollView.getScrollY()
                                    >= scrollView.getHeight() - screenHeight / 3) {
                                autoLoadMoreListener.onLoadMore();
                            } else {
                                if (canAutoLoadMore && scrollView.getScrollY()
                                        >= screenHeight / 3) {
                                    autoLoadMoreListener.onLoadMore();
                                }
                            }
                        }
                    } else if (child instanceof RecyclerView) {
                        RecyclerView recyclerView = (RecyclerView) child;
                        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                            LinearLayoutManager linearLayoutManager =
                                    (LinearLayoutManager) recyclerView.getLayoutManager();
                            if (linearLayoutManager.findLastVisibleItemPosition() < 20) {
                                if (canAutoLoadMore
                                        && linearLayoutManager.findLastVisibleItemPosition()
                                        >= linearLayoutManager.getItemCount() / 3) {
                                    autoLoadMoreListener.onLoadMore();
                                }
                            } else {
                                if (canAutoLoadMore
                                        && linearLayoutManager.findLastVisibleItemPosition()
                                        >= linearLayoutManager.getItemCount() - 8) {
                                    autoLoadMoreListener.onLoadMore();
                                }
                            }
                        }
                    }
                } else {
                    intercept = false;
                }
                break;
            }
            // Up事件
            case MotionEvent.ACTION_UP: {
                intercept = false;
                break;
            }
        }
        lastYIntercept = y;
        return intercept;
    }

    private View getLastVisibleChild() {
        for (int i = lastChildIndex; i >= 0; i--) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            } else {
                return child;
            }
        }
        return null;
    }

    private View getFirstVisibleChild() {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            } else {
                return child;
            }
        }
        return null;
    }

    public boolean avPullDownIntercept(View child) {
        boolean intercept = true;
        AdapterView adapterChild = (AdapterView) child;
        // 判断AbsListView是否已经到达内容最顶部
        if (adapterChild == null || adapterChild.getChildCount() == 0) {
            return true;
        }
        if (adapterChild.getFirstVisiblePosition() != 0
                || adapterChild.getChildAt(0).getTop() != 0) {
            // 如果没有达到最顶端，则仍然将事件下放
            intercept = false;
        }
        return intercept;
    }

    public boolean avPullUpIntercept(View child) {
        boolean intercept = false;
        AdapterView adapterChild = (AdapterView) child;

        // 判断AbsListView是否已经到达内容最底部
        if (adapterChild.getLastVisiblePosition() == adapterChild.getCount() - 1
                && (adapterChild.getChildAt(adapterChild.getChildCount() - 1).getBottom() == getMeasuredHeight())) {
            // 如果到达底部，则拦截事件
            intercept = true;
        }
        return intercept;
    }

    public boolean svPullDownIntercept(View child) {
        boolean intercept = false;
        if (child.getScrollY() <= 0) {
            intercept = true;
        }
        return intercept;
    }

    public boolean svPullUpIntercept(View child) {
        boolean intercept = false;
        ScrollView scrollView = (ScrollView) child;
        View scrollChild = scrollView.getChildAt(0);

        if (scrollView.getScrollY() >= (scrollChild.getHeight() - scrollView.getHeight())) {
            intercept = true;
        }
        return intercept;
    }

    public boolean rvPullDownIntercept(View child) {
        boolean intercept = false;

        RecyclerView recyclerChild = (RecyclerView) child;
        if (recyclerChild.computeVerticalScrollOffset() <= 0)
            intercept = true;

        return intercept;
    }

    public boolean rvPullUpIntercept(View child) {
        boolean intercept = false;

        RecyclerView recyclerChild = (RecyclerView) child;
        if (recyclerChild.computeVerticalScrollExtent() + recyclerChild.computeVerticalScrollOffset()
                >= recyclerChild.computeVerticalScrollRange())
            intercept = true;

        return intercept;
    }

    public boolean isCanPullRefresh() {
        return canPullRefresh;
    }

    public void setCanPullRefresh(boolean canPullRefresh) {
        this.canPullRefresh = canPullRefresh;
    }

    public boolean isCanLoadMore() {
        return canLoadMore;
    }

    public void setCanLoadMore(boolean canLoadMore) {
        this.canLoadMore = canLoadMore;
    }
}
