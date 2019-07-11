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
        switch (holder.getItemViewType()) {
            case TYPE_LEVEL_0:
                final LevelBean lv0 = (LevelBean) item;
                holder.setText(R.id.tv_levelName, lv0.getLevelName())
                        .setText(R.id.tv_listingCount, lv0.getListingCount())
                        .setText(R.id.tv_getInCount, lv0.getGetInCount())
                        .setText(R.id.tv_getInRate, lv0.getGetInRate())
                        .setTextColor(R.id.tv_levelName, ContextCompat.getColor(context, R.color.c_3))
                        .setTextColor(R.id.tv_listingCount, ContextCompat.getColor(context, R.color.c_3))
                        .setTextColor(R.id.tv_getInCount, ContextCompat.getColor(context, R.color.c_3))
                        .setTextColor(R.id.tv_getInRate, ContextCompat.getColor(context, R.color.c_3))
                        .setBackgroundRes(R.id.iv_arrow, lv0.isExpanded() ? R.mipmap.ic_arrow_gray_up : R.mipmap
                                .ic_arrow_gray_down);
                holder.itemView.setOnClickListener(v -> {
                    int pos = holder.getAdapterPosition();
                    if (lv0.isExpanded()) {
                        collapse(pos);
                    } else {
                        expand(pos);
                    }
                    holder.setBackgroundRes(R.id.iv_arrow, lv0.isExpanded() ? R.mipmap.ic_arrow_gray_up : R.mipmap
                                    .ic_arrow_gray_down);
                });
                break;
            case TYPE_LEVEL_1:
                final LevelBean lv1 = (LevelBean) item;
                holder.setText(R.id.tv_levelName, lv1.getLevelName())
                        .setText(R.id.tv_listingCount, lv1.getListingCount())
                        .setText(R.id.tv_getInCount, lv1.getGetInCount())
                        .setText(R.id.tv_getInRate, lv1.getGetInRate())
                        .setTextColor(R.id.tv_levelName, ContextCompat.getColor(context, R.color.orange_hi))
                        .setTextColor(R.id.tv_listingCount, ContextCompat.getColor(context, R.color.orange_hi))
                        .setTextColor(R.id.tv_getInCount, ContextCompat.getColor(context, R.color.orange_hi))
                        .setTextColor(R.id.tv_getInRate, ContextCompat.getColor(context, R.color.orange_hi));
                break;
            case TYPE_LEVEL_2:
                break;
            default:
                break;
        }
    }
}
