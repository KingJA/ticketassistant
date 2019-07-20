package com.kingja.ticketassistant.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.IExpandable;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.kingja.ticketassistant.R;
import com.kingja.ticketassistant.model.entiy.LevelBean;
import com.kingja.ticketassistant.util.SpSir;

import java.util.List;

/**
 * Created by luoxw on 2016/8/9.
 */
public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    private static final String TAG = ExpandableItemAdapter.class.getSimpleName();

    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;
    public static final int TYPE_LEVEL_2 = 2;
    private final Context context;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ExpandableItemAdapter(Context context, List<MultiItemEntity> data) {
        super(data);
        this.context = context;
        addItemType(TYPE_LEVEL_0, R.layout.item_query_0);
        addItemType(TYPE_LEVEL_1, R.layout.item_query_0);
        addItemType(TYPE_LEVEL_2, R.layout.item_query_0);
    }


    @Override
    protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {
        final LevelBean levelBean = (LevelBean) item;
        holder.setText(R.id.tv_levelName, levelBean.getLevelName())
                .setVisible(R.id.tv_listingCount, SpSir.getInstance().IsManager() )
                .setVisible(R.id.tv_getInRate, SpSir.getInstance().IsManager() )
                .setText(R.id.tv_listingCount, levelBean.getListingCount())
                .setText(R.id.tv_getInCount, levelBean.getGetInCount())
                .setText(R.id.tv_getInRate, levelBean.getGetInRate()).setVisible(R.id.iv_arrow, levelBean.isExpandable());
        holder.itemView.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (levelBean.isExpanded()) {
                collapse(pos);
            } else {
                expand(pos);
            }
            holder.setBackgroundRes(R.id.iv_arrow, levelBean.isExpanded() ? R.mipmap.ic_arrow_gray_up : R.mipmap
                    .ic_arrow_gray_down);
        });
        holder.setTextColor(R.id.tv_levelName, getColor(levelBean.getLevel()))
                .setTextColor(R.id.tv_listingCount, getColor(levelBean.getLevel()))
                .setTextColor(R.id.tv_getInCount, getColor(levelBean.getLevel()))
                .setTextColor(R.id.tv_getInRate, getColor(levelBean.getLevel()))
                .setBackgroundRes(R.id.iv_arrow, levelBean.isExpanded() ? R.mipmap.ic_arrow_gray_up : R.mipmap
                        .ic_arrow_gray_down);
    }

    public int getColor(int level) {
        int resultCololrResId= ContextCompat.getColor(context, R.color.c_3);
        switch (level) {
            case TYPE_LEVEL_0:
                resultCololrResId= ContextCompat.getColor(context, R.color.c_3);
                break;
            case TYPE_LEVEL_1:
                resultCololrResId= ContextCompat.getColor(context, R.color.orange_hi);
                break;
            case TYPE_LEVEL_2:
                resultCololrResId= ContextCompat.getColor(context, R.color.c_9);
                break;
            default:
                break;
        }
        return resultCololrResId;
    }
}
