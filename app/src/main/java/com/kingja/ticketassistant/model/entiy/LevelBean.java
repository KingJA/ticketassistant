package com.kingja.ticketassistant.model.entiy;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2019/7/11 0011 上午 11:57
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LevelBean extends AbstractExpandableItem<LevelBean> implements MultiItemEntity {

    private String levelName;
    private String listingCount;
    private String getInCount;
    private String getInRate;
    private List<LevelBean> children;
    private int level;

    public String getLevelName() {
        return null == levelName ? "" : levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getListingCount() {
        return null == listingCount ? "" : listingCount;
    }

    public void setListingCount(String listingCount) {
        this.listingCount = listingCount;
    }

    public String getGetInCount() {
        return null == getInCount ? "" : getInCount;
    }

    public void setGetInCount(String getInCount) {
        this.getInCount = getInCount;
    }

    public String getGetInRate() {
        return null == getInRate ? "" : getInRate;
    }

    public void setGetInRate(String getInRate) {
        this.getInRate = getInRate;
    }

    public List<LevelBean> getChildren() {
        return children;
    }

    public void setChildren(List<LevelBean> children) {
        this.children = children;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public int getItemType() {
        return this.level;
    }
}
