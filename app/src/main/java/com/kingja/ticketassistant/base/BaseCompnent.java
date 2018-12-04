package com.kingja.ticketassistant.base;


import com.kingja.ticketassistant.page.check.CheckFragment;
import com.kingja.ticketassistant.fragment.MineFragment;
import com.kingja.ticketassistant.page.home.HomeActivity;
import com.kingja.ticketassistant.page.query.QueryDataFragment;
import com.kingja.ticketassistant.injector.annotation.PerActivity;
import com.kingja.ticketassistant.injector.component.AppComponent;
import com.kingja.ticketassistant.injector.module.ActivityModule;
import com.kingja.ticketassistant.page.headimg.PersonalActivity;
import com.kingja.ticketassistant.page.login.LoginActivity;
import com.kingja.ticketassistant.page.modifynickname.ModifyNicknameActivity;
import com.kingja.ticketassistant.page.modifypassword.ModifyPasswordActivity;
import com.kingja.ticketassistant.update.VersionUpdateSir;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface BaseCompnent {
    void inject(VersionUpdateSir target);
    void inject(HomeActivity target);
    void inject(PersonalActivity target);
    void inject(ModifyPasswordActivity target);
    void inject(ModifyNicknameActivity target);
    void inject(LoginActivity target);
    void inject(CheckFragment target);
    void inject(MineFragment target);
    void inject(QueryDataFragment target);

}
