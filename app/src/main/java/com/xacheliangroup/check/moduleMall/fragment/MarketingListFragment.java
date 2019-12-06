package com.xacheliangroup.check.moduleMall.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseFragment;
import com.xacheliangroup.check.common.base.BaseRecycler.BaseRecyclerAdapter;
import com.xacheliangroup.check.common.base.BaseRecycler.OnItemClickListener;
import com.xacheliangroup.check.common.glide.GlideUtils;
import com.xacheliangroup.check.common.http.okhttp.IActionListener;
import com.xacheliangroup.check.common.type.MarketingEnum;
import com.xacheliangroup.check.moduleMall.activity.GoodsDetailActivity;
import com.xacheliangroup.check.moduleMall.activity.MarketingListActivity;
import com.xacheliangroup.check.moduleMall.activity.SellerHomeActivity;
import com.xacheliangroup.check.moduleMall.mvp.MallPresenter;
import com.xacheliangroup.check.moduleMall.mvp.bean.GoodsItemBean;
import com.xacheliangroup.check.moduleMall.mvp.bean.MallHeadBean;
import com.xacheliangroup.check.moduleMall.mvp.bean.SellerItemBean;
import com.xacheliangroup.check.utils.ProgressHelp;
import com.xacheliangroup.check.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author:yz
 * data: 2019/2/20,10:25
 */
public class MarketingListFragment extends BaseFragment implements IActionListener.ViewAction {
    @BindView(R.id.module_cbb_marketing_list_top_title_Tx)
    TextView mTopTitleTx;
    @BindView(R.id.module_cbb_marketing_list_top_des_tx)
    TextView mTopDesTx;
    @BindView(R.id.module_cbb_marketing_list_top_img)
    ImageView mTopImg;
    MallPresenter mMallPresenter;
    @BindView(R.id.module_cbb_marketing_list_goods_rv)
    RecyclerView mGoodsRecyclerView;
    @BindView(R.id.module_cbb_marketing_list_seller_rv)
    RecyclerView mSellerRecyclerView;

    BaseRecyclerAdapter<GoodsItemBean> mMarketingGoodsListBeanBaseRecyclerAdapter;
    BaseRecyclerAdapter<SellerItemBean> mMarketingSellerListBeanBaseRecyclerAdapter;
    private MarketingEnum mMarketingEnum;
    private MallHeadBean.FuncationalBean mFuncationalBean;
    @Override
    public void showInfoView(String type, Object obj) {
        ProgressHelp.dismissProgress();
        switch (type){
            case MallPresenter.TO_GET_MARKETING_GOODS_LIST_DATA:
                if(obj!=null&& obj instanceof List){
                    List<GoodsItemBean> list= (List<GoodsItemBean>) obj;
                    mMarketingGoodsListBeanBaseRecyclerAdapter.init(list);
                    mMarketingGoodsListBeanBaseRecyclerAdapter.notifyDataSetChanged();
                }
                break;
            case MallPresenter.TO_GET_MARKETING_SELLER_LIST_DATA:
                if(obj!=null&& obj instanceof List){
                    List<SellerItemBean> list= (List<SellerItemBean>) obj;
                    mMarketingSellerListBeanBaseRecyclerAdapter.init(list);
                    mMarketingSellerListBeanBaseRecyclerAdapter.notifyDataSetChanged();
                }
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
        return R.layout.module_cbb_fragment_marketing_list;
    }

    @Override
    protected void onCreateViewInit(View parentView) {
        initRecyclerView();
        getData();
    }

    private void getData() {
        Intent intent = getActivity().getIntent();
        if(intent!=null){
            mMarketingEnum = (MarketingEnum) intent.getSerializableExtra(MarketingListActivity.MARKETING_TYPE);
            if(intent.hasExtra(MarketingListActivity.MARKETING_DATA)){
                mFuncationalBean = (MallHeadBean.FuncationalBean) intent.getSerializableExtra(MarketingListActivity.MARKETING_DATA);
            }else {
                getActivity().finish();
                ToastUtils.showToast(getContext(),"数据错误");
            }
            changeView();
        }
    }

    private void changeView() {
        ProgressHelp.showProgress(getContext());
        mTopTitleTx.setText(mFuncationalBean.getTopTitle());
        mTopDesTx.setText(mFuncationalBean.getTopDes());
        GlideUtils.loadImage(getContext(),mFuncationalBean.getTopPicUrl(),mTopImg);
        switch (mMarketingEnum){
            case GOODS_LIST:
                mGoodsRecyclerView.setVisibility(View.VISIBLE);
                mSellerRecyclerView.setVisibility(View.GONE);
                mMallPresenter.toGetMarketingGoodsListData(mFuncationalBean.getMdseTypeId());
                break;
            case SELLER_LIST:
                mGoodsRecyclerView.setVisibility(View.GONE);
                mSellerRecyclerView.setVisibility(View.VISIBLE);
                mMallPresenter.toGetMarketingSellerListData(mFuncationalBean.getGameId());
                break;
        }
    }

    private void initRecyclerView() {
        mGoodsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mMarketingGoodsListBeanBaseRecyclerAdapter=new BaseRecyclerAdapter<>(getActivity());
        mGoodsRecyclerView.setAdapter(mMarketingGoodsListBeanBaseRecyclerAdapter);
        mGoodsRecyclerView.setNestedScrollingEnabled(false);

        mSellerRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mMarketingSellerListBeanBaseRecyclerAdapter=new BaseRecyclerAdapter<>(getActivity());
        mSellerRecyclerView.setAdapter(mMarketingSellerListBeanBaseRecyclerAdapter);
        mSellerRecyclerView.setNestedScrollingEnabled(false);


        mMarketingGoodsListBeanBaseRecyclerAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClicked(int position, Object obj) {
                if(obj!=null&&obj instanceof GoodsItemBean){
                    GoodsItemBean goodsItemBean= (GoodsItemBean) obj;
                    GoodsDetailActivity.launch(getContext(),getActivity(),goodsItemBean.getMdseId()+"");
                }
            }

            @Override
            public boolean onItemLongClicked(int position, Object obj) {
                return false;
            }
        });
        mMarketingSellerListBeanBaseRecyclerAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClicked(int position, Object obj) {
                if(obj!=null&&obj instanceof SellerItemBean){
                    SellerItemBean mSellerItemBean= (SellerItemBean) obj;
                    SellerHomeActivity.launch(getContext(),getActivity(),mSellerItemBean.getStorefrontid());
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
        mMallPresenter=new MallPresenter(getContext(),this);
        addPresenter(mMallPresenter);
    }

    @Override
    protected void PresenterGetData() {

    }
    @OnClick(R.id.module_cbb_marketing_list_back_img)
    public void toBack(){
        getActivity().finish();
    }


}
