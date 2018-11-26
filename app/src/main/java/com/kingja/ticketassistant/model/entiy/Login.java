package com.kingja.ticketassistant.model.entiy;


/**
 * Description:TODO
 * Create Time:2018/4/6 20:17
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Login {


    /**
     * verifierId : 7c631ca66f624194a4eef50986b51c27
     * headImg : /upload/headImg/default.png
     * nickName : vvho
     * mobile : 13780157756
     * isManager : 1
     * token : fa68cdc2c39744d6b5530030d5dbc4e4
     */

    private String verifierId;
    private String headImg;
    private String nickName;
    private String mobile;
    private int isManager;
    private String token;

    public String getVerifierId() {
        return verifierId;
    }

    public void setVerifierId(String verifierId) {
        this.verifierId = verifierId;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getIsManager() {
        return isManager;
    }

    public void setIsManager(int isManager) {
        this.isManager = isManager;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
