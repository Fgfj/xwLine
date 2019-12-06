package com.xacheliangroup.check.moduleMine.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.api.AppCache;
import com.xacheliangroup.check.common.base.BaseFragment;
import com.xacheliangroup.check.common.base.BaseRecycler.BaseRecyclerAdapter;
import com.xacheliangroup.check.common.base.BaseRecycler.OnItemClickListener;
import com.xacheliangroup.check.common.eventbus.MessageEvent;
import com.xacheliangroup.check.common.flag.Flag;
import com.xacheliangroup.check.common.glide.GlideUtils;
import com.xacheliangroup.check.common.sharepreference.AppSharePreference;
import com.xacheliangroup.check.common.type.AddressEnum;
import com.xacheliangroup.check.common.type.MineMenuEnum;
import com.xacheliangroup.check.moduleMine.activity.AppSettingActivity;
import com.xacheliangroup.check.moduleMine.activity.OrderListActivity;
import com.xacheliangroup.check.moduleMine.activity.SettingAddressActivity;
import com.xacheliangroup.check.moduleMine.mvp.bean.MineHomeUiBean;
import com.xacheliangroup.check.moduleOther.activity.LoginActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author:yz
 * data: 2018/12/18,13:58
 */
public class MineFragment extends BaseFragment {
    @BindView(R.id.module_cbb_mine_user_icon_img)
    ImageView mUserIconImg;
    @BindView(R.id.module_cbb_mine_user_name_tx)
    TextView mUserNameTx;
    @BindView(R.id.module_cbb_mine_user_sign_tx)
    TextView mUserSignTx;
    @BindView(R.id.module_cbb_mine_rv)
    RecyclerView mRecyclerView;

    BaseRecyclerAdapter<MineHomeUiBean> mBaseRecyclerAdapter;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getType()) {
            case Flag.EVENT.LOGIN_SUCCESS:
                changeView();
                break;
            case Flag.EVENT.LOGIN_OUT:
                setDefaultView();
                break;
        }
    }


    @Override
    protected void onRequestConnected() {

    }

    @Override
    protected void onRequestNoConnected() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.module_cbb_fragment_mine;
    }

    @Override
    protected void onCreateViewInit(View parentView) {
        setEventBusAction();
        initRecyclerView();
        if (AppCache.hasLogged()) {
            changeView();
        }
    }

    private void changeView() {
        GlideUtils.loadAvatarPicture(getContext(), AppSharePreference.getInstance().getUserIcon(), mUserIconImg);
        mUserNameTx.setText(AppSharePreference.getInstance().getUserName());
        mUserSignTx.setText(AppSharePreference.getInstance().getUserSign());
    }

    private void setDefaultView() {
        GlideUtils.loadAvatarIntPicture(getContext(), R.drawable.default_user_icon, mUserIconImg);
        mUserNameTx.setText("点击登录");
        mUserSignTx.setText("登录更精彩");
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBaseRecyclerAdapter = new BaseRecyclerAdapter<>(getContext());
        mRecyclerView.setAdapter(mBaseRecyclerAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);
        List<MineHomeUiBean> list = new ArrayList<>();
        list.add(new MineHomeUiBean(true, true, R.drawable.module_cbb_mine_home_coupon_icon, "我的卡卷", false, MineMenuEnum.CARD_LIST));
        list.add(new MineHomeUiBean(false, false, R.drawable.module_cbb_mine_home_bill_icon, "我的订单", false, MineMenuEnum.ORDER_LIST));
//        list.add(new MineHomeUiBean(false, false, R.drawable.module_cbb_mine_home_invoice_icon, "我的发票", false, MineMenuEnum.BILL_LIST));
        list.add(new MineHomeUiBean(true, false, R.drawable.module_cbb_mine_home_news_icon, "我的消息", true, MineMenuEnum.DOPE_LIST));
        list.add(new MineHomeUiBean(true, true, R.drawable.module_cbb_mine_home_address_icon, "地址设置", false, MineMenuEnum.ADDRESS_LIST));
        list.add(new MineHomeUiBean(false, false, R.drawable.module_cbb_mine_home_set_up_icon, "系统设置", false, MineMenuEnum.SYSTEM_SETTING));
        mBaseRecyclerAdapter.init(list);
        mBaseRecyclerAdapter.notifyDataSetChanged();
        mBaseRecyclerAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClicked(int position, Object obj) {
                if (obj != null && obj instanceof MineHomeUiBean) {
                    MineHomeUiBean mineHomeUiBean = (MineHomeUiBean) obj;
                    if (AppCache.hasLogged()) {
                        switch (mineHomeUiBean.getMineMenuEnum()) {
                            case CARD_LIST:

                                break;
                            case ORDER_LIST:
                                OrderListActivity.launch(getContext(), getActivity());
                                break;
                            case BILL_LIST:

                                break;
                            case DOPE_LIST:

                                break;
                            case ADDRESS_LIST:
                                SettingAddressActivity.launch(getContext(), getActivity(), AddressEnum.SETTING_IN);
                                break;
                            case SYSTEM_SETTING:
                                AppSettingActivity.launch(getContext(), getActivity());
                                break;
                        }
                    } else {
                        if (mineHomeUiBean.getMineMenuEnum() == MineMenuEnum.SYSTEM_SETTING) {
                            AppSettingActivity.launch(getContext(), getActivity());
                        } else {
                            LoginActivity.launch(getContext(), getActivity());
                        }
                    }
                }
            }

            @Override
            public boolean onItemLongClicked(int position, Object obj) {
                return false;
            }
        });
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void PresenterGetData() {

    }

    @OnClick(R.id.module_cbb_mine_user_icon_img)
    public void toUserIConOnClick() {
        if (AppCache.hasLogged()) {

        } else {
            LoginActivity.launch(getContext(), getActivity());
        }
    }
}
