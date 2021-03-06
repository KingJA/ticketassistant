package com.kingja.ticketassistant.page;

import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.kingja.supershapeview.view.SuperShapeTextView;
import com.kingja.ticketassistant.R;
import com.kingja.ticketassistant.base.BaseTitleActivity;
import com.kingja.ticketassistant.injector.component.AppComponent;
import com.kingja.ticketassistant.util.VersionUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:联系我们
 * Create Time:2018/7/2 16:37
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ContactUsActivity extends BaseTitleActivity {
    @BindView(R.id.stv_contact_phone)
    SuperShapeTextView stvContactPhone;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    private long[] mHits = new long[5];

    @OnClick({R.id.stv_contact_phone, R.id.tv_version})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.stv_contact_phone:
                callPhone("057788218708");
                break;
            case R.id.tv_version:
                tvVersion.setText(VersionUtil.getVerName(this) + "/" + VersionUtil.getVersionCode(this));
                System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
                mHits[mHits.length - 1] = SystemClock.uptimeMillis();
                if (mHits[0] >= (SystemClock.uptimeMillis() - 1000)) {
                    Log.e(TAG, "检查更新: " );
                }
                break;

        }

    }

    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }

    @Override
    public void initVariable() {

    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "联系我们";
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_contact;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void initNet() {

    }

}
