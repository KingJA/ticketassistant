package com.kingja.ticketassistant.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaygoo.widget.OnRangeChangedListener;
import com.jaygoo.widget.RangeSeekBar;
import com.kingja.ticketassistant.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:TODO
 * Create Time:2018/11/28 20:15
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DiscountPop extends BaseBottomPopup {
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.tv_all)
    TextView tvAll;
    @BindView(R.id.iv_all)
    ImageView ivAll;
    @BindView(R.id.rl_all)
    RelativeLayout rlAll;
    @BindView(R.id.tv_allDiscount)
    TextView tvAllDiscount;
    @BindView(R.id.iv_allDiscount)
    ImageView ivAllDiscount;
    @BindView(R.id.rl_allDiscount)
    RelativeLayout rlAllDiscount;
    @BindView(R.id.iv_rate_arrow)
    ImageView ivRateArrow;
    @BindView(R.id.ll_rate)
    LinearLayout llRate;
    @BindView(R.id.rl_rate)
    RelativeLayout rlRate;
    @BindView(R.id.tv_startDiscount)
    TextView tvStartDiscount;
    @BindView(R.id.tv_endDiscount)
    TextView tvEndDiscount;
    @BindView(R.id.rsb_discountRate)
    RangeSeekBar rsbDiscountRate;
    @BindView(R.id.ll_discountBar)
    LinearLayout llDiscountBar;
    @BindView(R.id.tv_question)
    TextView tvQuestion;
    @BindView(R.id.iv_question)
    ImageView ivQuestion;
    @BindView(R.id.rl_question)
    RelativeLayout rlQuestion;
    @BindView(R.id.iv_rate)
    ImageView ivRate;
    private OnDiscountRateSelectedLintener onDiscountRateSelectedLintener;
    private String discountRate = "0.1,0.9";
    private boolean openDiscount;
    private int currentStatus;

    public interface DiscountStatus {
        int ALL_TYPE = 0;
        int ALL_DISCOUNT = 1;
        int RATE = 2;
        int QUESTION = 3;
    }

    @OnClick({R.id.tv_cancel, R.id.tv_confirm, R.id.rl_all, R.id.rl_allDiscount, R.id.rl_question, R.id.rl_rate})
    public void click(View view) {
        if (onDiscountRateSelectedLintener == null) {
            dismiss();
            return;
        }
        switch (view.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.rl_rate:
                currentStatus = DiscountStatus.RATE;
                openDiscount = !openDiscount;
                resetDiscount(openDiscount);
                resetSelected();
                break;
            case R.id.tv_confirm:
                switch (currentStatus) {
                    case DiscountStatus.ALL_TYPE:
                        onDiscountRateSelectedLintener.onDiscountRateSelected("全部类型", "");
                        break;
                    case DiscountStatus.ALL_DISCOUNT:
                        onDiscountRateSelectedLintener.onDiscountRateSelected("全额抵扣", "0.01");
                        break;
                    case DiscountStatus.RATE:
                        String startDiscount = tvStartDiscount.getText().toString().trim();
                        String endDiscount = tvEndDiscount.getText().toString().trim();
                        String discountDes = startDiscount.equals(endDiscount) ? "折扣率:" + startDiscount : "折扣率:" +
                                startDiscount + "-" + endDiscount;
                        onDiscountRateSelectedLintener.onDiscountRateSelected(discountDes, discountRate);

                        break;
                    case DiscountStatus.QUESTION:
                        onDiscountRateSelectedLintener.onDiscountRateSelected("点赞券", "99");
                        break;
                }
                dismiss();
                break;
            case R.id.rl_all:
                currentStatus = DiscountStatus.ALL_TYPE;
                openDiscount = false;
                resetDiscount(openDiscount);
                resetSelected();
                break;
            case R.id.rl_allDiscount:
                currentStatus = DiscountStatus.ALL_DISCOUNT;
                openDiscount = false;
                resetDiscount(openDiscount);
                resetSelected();
                break;
            case R.id.rl_question:
                currentStatus = DiscountStatus.QUESTION;
                openDiscount = false;
                resetDiscount(openDiscount);
                resetSelected();
                break;
            default:
                break;
        }

    }

    public DiscountPop(Context context, View parentView) {
        super(context, parentView);
    }

    public void resetSelected() {
        switch (currentStatus) {
            case DiscountStatus.ALL_TYPE:
                ivAll.setVisibility(View.VISIBLE);
                ivAllDiscount.setVisibility(View.GONE);
                ivRate.setVisibility(View.GONE);
                ivQuestion.setVisibility(View.GONE);
                break;
            case DiscountStatus.ALL_DISCOUNT:
                ivAllDiscount.setVisibility(View.VISIBLE);
                ivAll.setVisibility(View.GONE);
                ivRate.setVisibility(View.GONE);
                ivQuestion.setVisibility(View.GONE);
                break;
            case DiscountStatus.RATE:
                ivRate.setVisibility(View.VISIBLE);
                ivAllDiscount.setVisibility(View.GONE);
                ivAll.setVisibility(View.GONE);
                ivQuestion.setVisibility(View.GONE);
                break;
            case DiscountStatus.QUESTION:
                ivQuestion.setVisibility(View.VISIBLE);
                ivRate.setVisibility(View.GONE);
                ivAllDiscount.setVisibility(View.GONE);
                ivAll.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.pop_discount;
    }

    @Override
    protected void initView() {

    }

    public void resetDiscount(boolean openDiscount) {
        ivRateArrow.setBackgroundResource(openDiscount ? R.mipmap.ic_arrow_up : R.mipmap.ic_arrow_down);
        llDiscountBar.setVisibility(openDiscount ? View.VISIBLE : View.GONE);
        if (!openDiscount) {
            tvStartDiscount.setText("1折");
            tvEndDiscount.setText("0折");
            discountRate = "0.1,0.9";
            rsbDiscountRate.setValue(1, 9);
        }
    }

    @Override
    protected void initData() {
        rsbDiscountRate.setValue(1, 9);
        rsbDiscountRate.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
                tvStartDiscount.setText((int) leftValue + "折");
                tvEndDiscount.setText((int) rightValue + "折");
                discountRate = "0." + (int) leftValue + ",0." + (int) rightValue;
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }
        });
    }

    public interface OnDiscountRateSelectedLintener {
        void onDiscountRateSelected(String discountDes, String discountRate);
    }

    public void setOnDiscountRateSelectedLintener(OnDiscountRateSelectedLintener onDiscountRateSelectedLintener) {
        this.onDiscountRateSelectedLintener = onDiscountRateSelectedLintener;
    }
}
