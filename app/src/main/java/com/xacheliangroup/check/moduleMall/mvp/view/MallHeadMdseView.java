package com.xacheliangroup.check.moduleMall.mvp.view;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseBean;
import com.xacheliangroup.check.common.base.holder.ViewSugar;

import butterknife.BindView;

/**
 * author:yz
 * data: 2018/12/14,15:01
 */
public class MallHeadMdseView extends ViewSugar {
    @BindView(R.id.module_cbb_mall_mdse_name)
    TextView mNameTx;
    @BindView(R.id.module_cbb_mall_mdse_img)
    ImageView img;

    public static ViewSugar getInstance(Context context,ViewGroup parent){
        return new MallHeadMdseView(context,parent);
    }
    public MallHeadMdseView(Context context, ViewGroup parent) {
        super(context, parent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.module_cbb_mall_mdse_recy_item;
    }

    @Override
    public <T extends BaseBean> void bind(T obj) {
    }
}
