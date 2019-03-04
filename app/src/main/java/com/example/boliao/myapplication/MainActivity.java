package com.example.boliao.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        ((TextView)findViewById(com.example.login.R.id.tv_version)).setText(BuildConfig.env);
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        findViewById(R.id.btn_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();
            }
        });
        findViewById(R.id.btn_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment();
            }
        });
    }

    public void login(){
        ARouter.getInstance().build("/account/login").navigation();
    }

    public void share(){
        ARouter.getInstance().build("/share/share").withString("share_content", "分享数据到微博").navigation(this, new NavCallback() {
            @Override
            public void onArrival(Postcard postcard) {  //路由到达之后调用
                Log.d("MainActivity" , "onArrival : " + postcard.getPath());
            }

            @Override
            public void onInterrupt(Postcard postcard) {  //路由被拦截时调用
                Log.d("MainActivity" , "onInterrupt : " + postcard.getPath());
            }

            @Override
            public void onLost(Postcard postcard) {   //路由被丢失时调用
                Log.d("MainActivity" , "onLost : " + postcard.getPath());
            }

            @Override
            public void onFound(Postcard postcard) {  //路由目标被发现时调用
                Log.d("MainActivity" , "onFound : " + postcard.getPath());
            }
        });
    }

    public void fragment(){
        startActivity(new Intent(this, FragmentActivity.class));
    }
}

