package com.kingja.ticketassistant.page;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.kingja.supershapeview.view.SuperShapeTextView;
import com.kingja.ticketassistant.R;
import com.kingja.ticketassistant.base.BaseTitleActivity;
import com.kingja.ticketassistant.constants.Constants;
import com.kingja.ticketassistant.injector.component.AppComponent;
import com.kingja.ticketassistant.model.entiy.TicketInfo;
import com.kingja.ticketassistant.util.AppUtil;
import com.kingja.ticketassistant.util.ToastUtil;
import com.kingja.ticketassistant.view.DeleteTextView;
import com.kingja.ticketassistant.view.StringTextView;
import com.sunmi.trans.util.AidlUtil;
import com.sunmi.trans.util.BytesUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:TODO
 * Create Time:2018/11/26 0026 下午 4:10
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class TicketDetailActivity extends BaseTitleActivity {
    @BindView(R.id.tv_ticketName)
    StringTextView tvTicketName;
    @BindView(R.id.tv_orderno)
    StringTextView tvOrderno;
    @BindView(R.id.tv_touristName)
    StringTextView tvTouristName;
    @BindView(R.id.tv_touristMobile)
    StringTextView tvTouristMobile;
    @BindView(R.id.tv_quantity)
    StringTextView tvQuantity;
    @BindView(R.id.tv_marketPrice)
    DeleteTextView tvMarketPrice;
    @BindView(R.id.tv_buyPrice)
    StringTextView tvBuyPrice;
    @BindView(R.id.tv_useDate)
    StringTextView tvUseDate;
    @BindView(R.id.tv_orderTime)
    StringTextView tvOrderTime;
    @BindView(R.id.tv_confirm)
    SuperShapeTextView tvConfirm;
    private TicketInfo tickeInfo;

    @OnClick({R.id.tv_confirm, R.id.tv_print})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_confirm:
                finish();
                break;
            case R.id.tv_print:
                print();
                break;
            default:
                break;
        }
    }

    private void print() {
        AidlUtil.getInstance().printText("DDCHICK 验票系统", AppUtil.dp2px(20), true, false);
        AidlUtil.getInstance().sendRawData(BytesUtil.printStarLineDiv());

        StringBuffer printContentSb = new StringBuffer();
        //标题
        String printContent = printContentSb.append(tickeInfo.getTicketName()).append("\n")
                //订单号
                .append(String.format("订单号：%s", tickeInfo.getOrderno())).append("\n")
                //游客
                .append(String.format("游客：%s  %s",tickeInfo.getTouristName(), tickeInfo.getTouristMobile())).append("\n")
                //数量
                .append(String.format("数量：%d张", tickeInfo.getQuantity())).append("\n")
                //门市价
                .append(String.format("门市价：¥%d元", tickeInfo.getMarketPrice())).append("\n")
                //抵用金额
                .append(String.format("抵用金额：抵用%d元/张", tickeInfo.getBuyPrice())).append("\n")
                //使用期限
                .append(String.format("使用期限：%s", tickeInfo.getUseDate())).append("\n")
                //领券日期
                .append(String.format("领券日期：%s", tickeInfo.getOrderTime())).append("\n").toString();

        AidlUtil.getInstance().printText(printContent, AppUtil.dp2px(12), false, false);
        AidlUtil.getInstance().print3Line();
    }

    @Override
    public void initVariable() {
        tickeInfo = (TicketInfo) getIntent().getSerializableExtra(Constants.Extra.TicketStatus);

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_ticket_detail;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        tvTicketName.setString(tickeInfo.getTicketName());
        tvOrderno.setString(tickeInfo.getOrderno());
        tvTouristName.setString(tickeInfo.getTouristName());
        tvTouristMobile.setString(tickeInfo.getTouristMobile());
        tvQuantity.setString(tickeInfo.getQuantity());
        tvMarketPrice.setText(String.valueOf(tickeInfo.getMarketPrice()));
        tvBuyPrice.setString(tickeInfo.getBuyPrice());
        tvUseDate.setString(tickeInfo.getUseDate());
        tvOrderTime.setString(tickeInfo.getOrderTime());
    }

    @Override
    public void initNet() {

    }

    public static void goActivity(Context context, TicketInfo ticketInfo) {
        Intent intent = new Intent(context, TicketDetailActivity.class);
        intent.putExtra(Constants.Extra.TicketStatus, ticketInfo);
        context.startActivity(intent);
    }

}
