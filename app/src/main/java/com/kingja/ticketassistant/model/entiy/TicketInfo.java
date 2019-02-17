package com.kingja.ticketassistant.model.entiy;

import java.io.Serializable;

/**
 * Description:TODO
 * Create Time:2018/11/26 0026 下午 2:30
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class TicketInfo implements Serializable {

    /**
     * ticketName : string,优惠券名称
     * orderno : string,订单号
     * touristName : string,游客姓名
     * touristMobile : string,游客手机号码
     * touristIdcode : string,游客身份证号码
     * quantity : 8
     * useDate : string,使用期限
     * orderTime : string,领券时间
     * marketPrice : 8
     * buyPrice : 8
     */

    private String ticketName;
    private String orderno;
    private String touristName;
    private String touristMobile;
    private String touristIdcode;
    private int quantity;
    private String useDate;
    private String orderTime;
    private String ticketcode;
    private String verifyUrl;
    private String visitMethod;
    private String useRemarks;
    private int marketPrice;
    private int buyPrice;


    public String getTicketcode() {
        return ticketcode;
    }

    public void setTicketcode(String ticketcode) {
        this.ticketcode = ticketcode;
    }

    public String getVerifyUrl() {
        return verifyUrl;
    }

    public void setVerifyUrl(String verifyUrl) {
        this.verifyUrl = verifyUrl;
    }

    public String getVisitMethod() {
        return visitMethod;
    }

    public void setVisitMethod(String visitMethod) {
        this.visitMethod = visitMethod;
    }

    public String getUseRemarks() {
        return useRemarks;
    }

    public void setUseRemarks(String useRemarks) {
        this.useRemarks = useRemarks;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getTouristName() {
        return touristName;
    }

    public void setTouristName(String touristName) {
        this.touristName = touristName;
    }

    public String getTouristMobile() {
        return touristMobile;
    }

    public void setTouristMobile(String touristMobile) {
        this.touristMobile = touristMobile;
    }

    public String getTouristIdcode() {
        return touristIdcode;
    }

    public void setTouristIdcode(String touristIdcode) {
        this.touristIdcode = touristIdcode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUseDate() {
        return useDate;
    }

    public void setUseDate(String useDate) {
        this.useDate = useDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public int getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(int marketPrice) {
        this.marketPrice = marketPrice;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }
}
