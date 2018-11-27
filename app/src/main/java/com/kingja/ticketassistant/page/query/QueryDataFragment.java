package com.kingja.ticketassistant.page.query;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.kingja.supershapeview.view.SuperShapeTextView;
import com.kingja.ticketassistant.R;
import com.kingja.ticketassistant.base.BaseFragment;
import com.kingja.ticketassistant.base.DaggerBaseCompnent;
import com.kingja.ticketassistant.injector.component.AppComponent;
import com.kingja.ticketassistant.model.entiy.CheckResult;
import com.kingja.ticketassistant.model.entiy.ScenicType;
import com.kingja.ticketassistant.page.home.ScenicTypeContract;
import com.kingja.ticketassistant.page.home.ScenicTypePresenter;
import com.kingja.ticketassistant.util.DateUtil;
import com.kingja.ticketassistant.util.LogUtil;
import com.kingja.ticketassistant.util.SpSir;
import com.kingja.ticketassistant.util.VersionUtil;
import com.kingja.ticketassistant.view.StringTextView;

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
    StringTextView tvListingCount;
    @BindView(R.id.tv_getInCount)
    StringTextView tvGetInCount;
    @BindView(R.id.tv_getInRate)
    StringTextView tvGetInRate;
    @BindView(R.id.tv_startDate)
    TextView tvStartDate;
    @BindView(R.id.tv_endDate)
    TextView tvEndDate;
    @BindView(R.id.tv_scenic)
    TextView tvScenic;
    @BindView(R.id.tv_ticketType)
    TextView tvTicketType;
    private TimePickerDialog startDateSelector;
    private TimePickerDialog endDateSelector;
    private String startDate;
    private String endDate;

    @Inject
    ScenicTypePresenter scenicTypePresenter;

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
                                "yyyy-MM-dd") : DateUtil.getMillSeconds(startDate, "yyyy-MM-dd"))
                        .setCallBack((timePickerView, millseconds) -> {
                            endDate = DateUtil.getDateString(millseconds);
                            tvEndDate.setText(endDate);
                        })
                        .build();
                endDateSelector.show(getActivity().getSupportFragmentManager(), String.valueOf(rlEndDate.hashCode()));
                break;
            case R.id.rl_scenic:
                break;
            case R.id.rl_ticketType:
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
                .addFormDataPart("scenicId", "")
                .addFormDataPart("discountRate", "")
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
        initScenicTypeData();
    }

    @Override
    public void initNet() {

    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_query;
    }

    @Override
    public void onQueryDataSuccess(CheckResult checkResult) {
        tvListingCount.setString(checkResult.getListingCount());
        tvGetInRate.setString(checkResult.getGetInRate());
        tvGetInCount.setString(checkResult.getGetInCount());

    }
    private void initScenicTypeData() {
        String scenicTypeGson = SpSir.getInstance().getScenicType();
        if (!TextUtils.isEmpty(scenicTypeGson)) {
            LogUtil.e(TAG, "有初始化景区类型缓存");
            List<ScenicType>   scenicTypes = new Gson().fromJson(scenicTypeGson, new TypeToken<List<ScenicType>>() {
            }.getType());
        } else {
            LogUtil.e(TAG, "没有初始化景区类型缓存");
            scenicTypePresenter.getScenicType();
        }
    }

    @Override
    public void onGetScenicTypeSuccess(List<ScenicType> scenicTypeList) {
        SpSir.getInstance().putScenicType(new Gson().toJson(scenicTypeList));
    }
}
