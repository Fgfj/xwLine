package com.xacheliangroup.check.moduleNetLine.mvp.view;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseBean;
import com.xacheliangroup.check.common.base.holder.ViewSugar;
import com.xacheliangroup.check.moduleNetLine.mvp.bean.NetLineListBean;

import butterknife.BindView;

/**
 * author:yz
 * data: 2019/9/2,10:09
 */
public class NetLineListView  extends ViewSugar {
    @BindView(R.id.his_item_start_tx)
    TextView mStartTx;
    @BindView(R.id.his_item_end_tx)
    TextView mEndTx;
    @BindView(R.id.his_item_time_tx)
    TextView mTimeTx;
    @BindView(R.id.his_item_human_num_tx)
    TextView mHumanNumTx;
    public static ViewSugar getInstance(Context context,ViewGroup parent){
        return new NetLineListView(context,parent);
    }

    public NetLineListView(Context context, ViewGroup parent) {
        super(context, parent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.module_nl_list_recy_item;
    }

    @Override
    public <T extends BaseBean> void bind(T obj) {
        if(obj!=null&&obj instanceof NetLineListBean){
            NetLineListBean netLineListBean= (NetLineListBean) obj;
            mStartTx.setText(netLineListBean.getStartcityname());
            mEndTx.setText(netLineListBean.getEndcityname());
            mTimeTx.setText(netLineListBean.getCreatetime());
            mHumanNumTx.setText(netLineListBean.getBusnumber()+"人");
        }
    }
}
