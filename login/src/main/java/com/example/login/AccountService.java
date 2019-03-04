package com.example.login;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.componentbase.service.IAccountService;

@Route(path = "/service/account")
public class AccountService implements IAccountService {
    @Override
    public boolean isLogin() {
        return AccountUtils.userInfo != null;
    }

    @Override
    public String getAccountName() {
        return AccountUtils.userInfo == null ? null : AccountUtils.userInfo.getUserName();
    }

    @Override
    public void init(Context context) {

    }
}

