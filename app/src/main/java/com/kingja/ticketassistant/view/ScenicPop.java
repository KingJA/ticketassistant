package com.kingja.ticketassistant.view;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.kingja.ticketassistant.R;
import com.kingja.ticketassistant.adapter.ScenicTypeAdapter;
import com.kingja.ticketassistant.constants.Constants;
import com.kingja.ticketassistant.model.entiy.ScenicType;
import com.kingja.ticketassistant.util.GoUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Description:TODO
 * Create Time:2018/11/28 0028 下午 2:37
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ScenicPop extends BaseBottomPopup {
    @BindView(R.id.flv)
    FixedListView flv;
    private ScenicTypeAdapter scenicTypeAdapter;
    private OnScenicTypeSelectedListener onScenicTypeSelectedListener;


    @OnItemClick({R.id.flv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        if (onScenicTypeSelectedListener != null) {
            scenicTypeAdapter.selectItem(position);
            dismiss();
            onScenicTypeSelectedListener.onScenicTypeSelected((ScenicType) adapterView.getItemAtPosition(position));
        }
    }

    public ScenicPop(Context context, View parentView) {
        super(context, parentView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.pop_scenic;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    public void setData(List<ScenicType> scenicTypeList) {
        scenicTypeList = scenicTypeList == null ? new ArrayList<>() : scenicTypeList;
        ScenicType scenicType = new ScenicType();
        scenicType.setId("");
        scenicType.setName("全部景区");
        scenicTypeList.add(0, scenicType);
        scenicTypeAdapter = new ScenicTypeAdapter(context, scenicTypeList);
        scenicTypeAdapter.selectItem(0);
        flv.setAdapter(scenicTypeAdapter);
    }

    public interface OnScenicTypeSelectedListener {
        void onScenicTypeSelected(ScenicType scenicType);
    }

    public void setOnScenicTypeSelectedListener(OnScenicTypeSelectedListener onScenicTypeSelectedListener) {
        this.onScenicTypeSelectedListener = onScenicTypeSelectedListener;
    }
}
