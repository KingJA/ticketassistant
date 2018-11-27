package com.kingja.ticketassistant.model.service;

import android.icu.util.VersionInfo;

import com.kingja.ticketassistant.model.entiy.CheckResult;
import com.kingja.ticketassistant.model.entiy.HttpResult;
import com.kingja.ticketassistant.model.entiy.Login;
import com.kingja.ticketassistant.model.entiy.ScenicType;
import com.kingja.ticketassistant.model.entiy.TicketInfo;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 项目名称：和Api相关联
 * 类描述：TODO
 * 创建人：KingJA
 * 创建时间：2016/6/12 16:32
 * 修改备注：
 */
public interface ApiService {


    /*登录OK*/
    @FormUrlEncoded
    @POST("/app/verifier/login")
    Observable<HttpResult<Login>> login(@Field("mobile") String mobile, @Field("password") String password, @Field
            ("deviceId") String deviceId, @Field("deviceName") String deviceName, @Field("osName") String osName);

    /*退出登录OK*/
    @FormUrlEncoded
    @POST("/app/verifier/logout")
    Observable<HttpResult<Object>> logout(@Field("verifierId") String verifierId, @Field("osName") String osName);


    /*修改昵称*/
    @Headers("Content-Type:application/x-www-form-urlencoded;charset=utf-8")
    @FormUrlEncoded
    @POST("/app/verifier/changeinfo")
    Observable<HttpResult<Object>> modifyNickname(@Field("nickname") String nickname);


    /*上传头像*/
    @Multipart
    @POST("/app/verifier/changeHeadimg")
    Observable<HttpResult<String>> uploadHeadImg(@Part MultipartBody.Part headImg);

    /*修改密码*/
    @FormUrlEncoded
    @POST("/app/verifier/changepasswd")
    Observable<HttpResult<Object>> modifyPassword(@Field("oldpasswd") String oldpasswd, @Field("passwd") String
            password);

    /*获取景区类型*/
    @POST("/app/verifier/scenicList")
    Observable<HttpResult<List<ScenicType>>> getScenicType();

    /*验票*/
    @GET("/app/order/verify/{ticketCode}")
    Observable<HttpResult<TicketInfo>> checkTicket(@Path("ticketCode") String ticketCode);


    /*数据查询*/
    @POST("/app/data/dataView")
    Observable<HttpResult<CheckResult>> queryData(@Body RequestBody requestBody);

    /*版本检测*/
    @FormUrlEncoded
    @POST("/app/version/detail")
    Observable<HttpResult<VersionInfo>> checkUpdate(@Field("version") String version, @Field("flag") int flag);

}
