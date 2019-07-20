package com.kingja.ticketassistant.page.query;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.kingja.supershapeview.view.SuperShapeTextView;
import com.kingja.ticketassistant.R;
import com.kingja.ticketassistant.adapter.ExpandableItemAdapter;
import com.kingja.ticketassistant.base.BaseFragment;
import com.kingja.ticketassistant.base.DaggerBaseCompnent;
import com.kingja.ticketassistant.injector.component.AppComponent;
import com.kingja.ticketassistant.model.entiy.LevelBean;
import com.kingja.ticketassistant.model.entiy.ScenicType;
import com.kingja.ticketassistant.page.home.ScenicTypeContract;
import com.kingja.ticketassistant.page.home.ScenicTypePresenter;
import com.kingja.ticketassistant.util.DateUtil;
import com.kingja.ticketassistant.util.ListItemDecoration;
import com.kingja.ticketassistant.util.LogUtil;
import com.kingja.ticketassistant.util.SpSir;
import com.kingja.ticketassistant.view.DiscountPop;
import com.kingja.ticketassistant.view.ScenicPop;
import com.kingja.ticketassistant.view.StringTextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.MultipartBody;

/**
 * Description:TODO
 * Create Time:2018/11/26 0026 下午 1:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class QueryDataFragment extends BaseFragment implements QueryDataContract.View, ScenicTypeContract.View {
    @Inject
    QueryDataPresenter queryDataPresenter;
    @BindView(R.id.rl_startDate)
    RelativeLayout rlStartDate;
    @BindView(R.id.rl_endDate)
    RelativeLayout rlEndDate;
    @BindView(R.id.rl_scenic)
    RelativeLayout rlScenic;
    @BindView(R.id.rl_ticketType)
    RelativeLayout rlTicketType;
    @BindView(R.id.tv_query)
    SuperShapeTextView tvQuery;
    @BindView(R.id.tv_listingCount)
    TextView tvListingCount;
    //    @BindView(R.id.tv_getInCount)
//    StringTextView tvGetInCount;
    @BindView(R.id.tv_getInRate)
    TextView tvGetInRate;
    @BindView(R.id.tv_startDate)
    TextView tvStartDate;
    @BindView(R.id.tv_endDate)
    TextView tvEndDate;
    @BindView(R.id.tv_scenic)
    TextView tvScenic;
    @BindView(R.id.tv_ticketType)
    TextView tvTicketType;
    @BindView(R.id.ll_root)
    LinearLayout llRoot;
    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder;
    @BindView(R.id.ll_scenic_selector)
    LinearLayout llScenicSelector;
    Unbinder unbinder1;
    private TimePickerDialog startDateSelector;
    private TimePickerDialog endDateSelector;
    private String startDate = "";
    private String endDate = "";
    private String scenicId = "";
    private String discountRate = "";
    @Inject
    ScenicTypePresenter scenicTypePresenter;
    private ScenicPop scenicPop;
    private List<ScenicType> scenicTypes;
    private DiscountPop discountPop;

    @OnClick({R.id.rl_startDate, R.id.rl_endDate, R.id.rl_scenic, R.id.rl_ticketType, R.id.tv_query})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.rl_startDate:
                startDateSelector = new TimePickerDialog.Builder()
                        .setType(Type.YEAR_MONTH_DAY)
                        .setThemeColor(ContextCompat.getColor(getActivity(), R.color.orange_hi))
                        .setWheelItemTextSize(15)
                        .setCurrentMillseconds(!TextUtils.isEmpty(startDate) ? DateUtil.getMillSeconds(startDate,
                                "yyyy-MM-dd") : System.currentTimeMillis())
                        .setMaxMillseconds(!TextUtils.isEmpty(endDate) ? DateUtil.getMillSeconds(endDate,
                                "yyyy-MM-dd") : System.currentTimeMillis())
                        .setTitleStringId("请选择起始日期")
                        .setCallBack((timePickerView, millseconds) -> {
                            startDate = DateUtil.getDateString(millseconds);
                            tvStartDate.setText(startDate);
                        })
                        .build();

                startDateSelector.show(getActivity().getSupportFragmentManager(), String.valueOf(rlStartDate.hashCode
                        ()));
                break;
            case R.id.rl_endDate:
                endDateSelector = new TimePickerDialog.Builder()
                        .setType(Type.YEAR_MONTH_DAY)
                        .setThemeColor(ContextCompat.getColor(getActivity(), R.color.orange_hi))
                        .setWheelItemTextSize(15)
                        .setTitleStringId("请选择结束日期")
                        .setMinMillseconds(!TextUtils.isEmpty(startDate) ? DateUtil.getMillSeconds(startDate,
                                "yyyy-MM-dd") : System.currentTimeMillis())
                        .setCurrentMillseconds(!TextUtils.isEmpty(endDate) ? DateUtil.getMillSeconds(endDate,
                                "yyyy-MM-dd") : (!TextUtils.isEmpty(startDate) ? DateUtil.getMillSeconds(startDate,
                                "yyyy-MM-dd") : System.currentTimeMillis()))
                        .setCallBack((timePickerView, millseconds) -> {
                            endDate = DateUtil.getDateString(millseconds);
                            tvEndDate.setText(endDate);
                        })
                        .build();
                endDateSelector.show(getActivity().getSupportFragmentManager(), String.valueOf(rlEndDate.hashCode()));
                break;
            case R.id.rl_scenic:
                scenicPop.showPopupAbove();
                break;
            case R.id.rl_ticketType:
                discountPop.showPopupAbove();
                break;
            case R.id.tv_query:
                query();
                break;

        }

    }


    private void query() {
        queryDataPresenter.queryData(new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("startDate", startDate)
                .addFormDataPart("endDate", endDate)
                .addFormDataPart("scenicId", scenicId)
                .addFormDataPart("discountRate", discountRate)
                .build());
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        queryDataPresenter.attachView(this);
        scenicTypePresenter.attachView(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        llScenicSelector.setVisibility(SpSir.getInstance().IsManager() ? View.VISIBLE : View.GONE);
        tvListingCount.setVisibility(SpSir.getInstance().IsManager() ? View.VISIBLE : View.INVISIBLE);
        tvGetInRate.setVisibility(SpSir.getInstance().IsManager() ? View.VISIBLE : View.INVISIBLE);
        initScenicTypeData();
        initScenicPop();
        discountPop = new DiscountPop(getActivity(), llRoot);
        discountPop.setOnDiscountRateSelectedLintener((discountDes, rate) -> {
            tvTicketType.setText(discountDes);
            discountRate = rate;
            LogUtil.e(TAG, "discountRate:" + discountRate);
        });
    }

    private void initScenicPop() {
        scenicPop = new ScenicPop(getActivity(), llRoot);
        scenicPop.setData(scenicTypes);
        scenicPop.setOnScenicTypeSelectedListener(scenicType -> {
            tvScenic.setText(scenicType.getName());
            scenicId = scenicType.getId();

        });
    }

    @Override
    public void initNet() {

    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_query;
    }

    @Override
    public void onQueryDataSuccess(List<LevelBean> levelBeanList) {
        ArrayList<MultiItemEntity> res = new ArrayList<>();

        if (levelBeanList != null && levelBeanList.size() > 0) {
            for (LevelBean levelBean : levelBeanList) {
                setLevelData(levelBean);
                res.add(levelBean);
            }
        }
        ExpandableItemAdapter adapter = new ExpandableItemAdapter(getActivity(), res);
        rv.setAdapter(adapter);
//        adapter.expandAll();//默认关闭
        rv.addItemDecoration(new ListItemDecoration(getActivity(), 1, ContextCompat.getColor(getActivity(), R.color
                .diver)));
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    public void setLevelData(LevelBean levelBean) {
        List<LevelBean> levelBeanList = levelBean.getChildren();
        if (levelBeanList != null && levelBeanList.size() > 0) {
            for (LevelBean bean : levelBeanList) {
                levelBean.addSubItem(bean);
                setLevelData(bean);
            }
        }
    }

    private void initScenicTypeData() {
        String scenicTypeGson = SpSir.getInstance().getScenicType();
        if (!TextUtils.isEmpty(scenicTypeGson)) {
            LogUtil.e(TAG, "有初始化景区类型缓存");
            scenicTypes = new Gson().fromJson(scenicTypeGson, new TypeToken<List<ScenicType>>() {
            }.getType());
        } else {
            LogUtil.e(TAG, "没有初始化景区类型缓存");
            scenicTypePresenter.getScenicType();
        }
    }

    @Override
    public void onGetScenicTypeSuccess(List<ScenicType> scenicTypeList) {
        SpSir.getInstance().putScenicType(new Gson().toJson(scenicTypeList));
        scenicPop.setData(scenicTypeList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }
}
