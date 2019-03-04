package com.example.share;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.componentbase.service.IAccountService;

@Route(path = "/share/share")
public class ShareActivity extends AppCompatActivity {

    @Autowired(name = "/service/account")
    IAccountService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        ARouter.getInstance().inject(this);

        if (getIntent() != null) {
            String content = getIntent().getStringExtra("share_content");
            if (!TextUtils.isEmpty(content)) {
                ((TextView) findViewById(R.id.share_content)).setText(content);
            }
        }

        share();
    }

    private void share() {
        boolean isLogin = false;
        String name = "";
        if(service != null){
            isLogin = service.isLogin();
            name = service.getAccountName();
        }
        if (isLogin) {
            Toast.makeText(this, name + " 分享成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "分享失败：用户未登录", Toast.LENGTH_SHORT).show();
        }
    }

    public void shareLogin(View view) {
        ARouter.getInstance().build("/account/login").navigation();
    }
}
