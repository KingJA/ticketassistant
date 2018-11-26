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
import com.kingja.ticketassistant.view.DeleteTextView;
import com.kingja.ticketassistant.view.StringTextView;

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

    @OnClick({R.id.tv_confirm})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_confirm:
                finish();
                break;
            default:
                break;
        }
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
