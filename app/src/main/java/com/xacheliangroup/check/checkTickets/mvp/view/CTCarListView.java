package com.xacheliangroup.check.checkTickets.mvp.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.checkTickets.mvp.bean.CTCarListBean;
import com.xacheliangroup.check.common.base.BaseBean;
import com.xacheliangroup.check.common.base.holder.ViewSugar;

import butterknife.BindView;

/**
 * author:yz
 * data: 2019/6/5,15:30
 */
public class CTCarListView extends ViewSugar {
    @BindView(R.id.module_ct_item_car_list_bottom_ly)
    LinearLayout mBottomLy;
    @BindView(R.id.item_car_no_tx)
    TextView mCarTrainTx;
    @BindView(R.id.item_car_time_tx)
    TextView mCarDataTx;
    @BindView(R.id.item_car_type_tx)
    TextView mCarTypeTx;
    @BindView(R.id.item_car_start_tx)
    TextView mCarStartStationTx;
    @BindView(R.id.item_car_start_time_tx)
    TextView mUpCarTimeTx;
    @BindView(R.id.item_car_end_tx)
    TextView mCarEndStationTx;
    @BindView(R.id.item_car_start_location_tx)
    TextView mCarStartLocationTx;
    @BindView(R.id.item_car_end_location_tx)
    TextView mCarEndLocationTx;
    @BindView(R.id.item_car_car_no_tx)
    TextView mCarNoTx;
    @BindView(R.id.item_car_car_company_tx)
    TextView mCarCompanyTx;
    @BindView(R.id.item_car_sell_number_tx)
    TextView mSellNumberTX;
    @BindView(R.id.item_car_check_number_tx)
    TextView mCheckNumberTx;
    @BindView(R.id.item_car_over_number_tx)
    TextView mOverNumberTx;
    public static ViewSugar getInstance(Context context,ViewGroup parent){
        return new CTCarListView(context,parent);
    }

    public CTCarListView(Context context, ViewGroup parent) {
        super(context, parent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.module_ct_rv_item_car_list;
    }

    @Override
    public <T extends BaseBean> void bind(T obj) {
        if(obj!=null&&obj instanceof CTCarListBean.DataBean){

            CTCarListBean.DataBean ctCarListBean= (CTCarListBean.DataBean) obj;
            if(TextUtils.equals(ctCarListBean.getType(),"0")){//待发车
                mBottomLy.setVisibility(View.GONE);
            }else {
                mBottomLy.setVisibility(View.VISIBLE);
                mSellNumberTX.setText(ctCarListBean.getTicketCount()+"");
                mCheckNumberTx.setText(ctCarListBean.getCheckCount()+"");
                mOverNumberTx.setText("");
            }
            mCarEndStationTx.setText(ctCarListBean.getQX());
            mCarNoTx.setText(ctCarListBean.getBusCode());
            mUpCarTimeTx.setText(ctCarListBean.getClasTime());
            mCarStartStationTx.setText(ctCarListBean.getStationName());
            mCarTrainTx.setText("班次号："+ctCarListBean.getBus()+"");
            mCarCompanyTx.setText("车属公司："+ctCarListBean.getBalanceComp());
            mCarDataTx.setText(ctCarListBean.getClasDate());
        }
    }
}
