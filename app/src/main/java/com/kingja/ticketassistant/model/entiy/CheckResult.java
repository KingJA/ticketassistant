package com.kingja.ticketassistant.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/11/26 0026 上午 10:15
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class CheckResult {

    /**
     * levelName : 全部
     * listingCount : 0
     * getInCount : 0
     * getInRate : 0.0%
     * children : [{"levelName":"优惠券","listingCount":"0","getInCount":"0","getInRate":"0.0%"},{"levelName":"点赞券",
     * "listingCount":"0","getInCount":"0","getInRate":"0.0%"}]
     */

    private String levelName;
    private String listingCount;
    private String getInCount;
    private String getInRate;
    private List<ChildrenBean> children;

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

    public List<ChildrenBean> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBean> children) {
        this.children = children;
    }

    public static class ChildrenBean {
        /**
         * levelName : 优惠券
         * listingCount : 0
         * getInCount : 0
         * getInRate : 0.0%
         */

        private String levelName;
        private String listingCount;
        private String getInCount;
        private String getInRate;

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
    }
}
