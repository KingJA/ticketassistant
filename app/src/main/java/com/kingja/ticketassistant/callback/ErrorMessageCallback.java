package com.kingja.ticketassistant.callback;


import com.kingja.loadsir.callback.Callback;
import com.kingja.ticketassistant.R;


/**
 * Description:TODO
 * Create Time:2017/9/4 10:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

public class ErrorMessageCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.layout_error_network;
    }
}
