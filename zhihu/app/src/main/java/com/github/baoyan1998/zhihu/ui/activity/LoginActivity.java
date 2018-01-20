package com.github.baoyan1998.zhihu.ui.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.baoyan1998.zhihu.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mtv_mimadenglu;

    private EditText et_phone;
    private EditText et_password;
    private Button bt_get;
    private Button bt_login;
    private String mVerifyCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //初始化布局
        initview();

        bt_login.setOnClickListener((View.OnClickListener) this);
        bt_get.setOnClickListener((View.OnClickListener) this);
        mtv_mimadenglu = (TextView)findViewById(R.id.tv_mimadenglu);
        mtv_mimadenglu.setOnClickListener(this);
    }

    private void initview() {
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_password = (EditText) findViewById(R.id.et_password);

        bt_get = (Button) findViewById(R.id.button2);
        bt_login=(Button)findViewById(R.id.login_bt);
    }

    @Override
    public void onClick(View v) {


//        Intent intent = new Intent();
//        intent.setClass(getApplicationContext(),passwordActivity.class);
//        startActivity(intent);


        String phone = et_phone.getText().toString();
        if (v.getId() == R.id.button2) {
            if (phone == null || phone.length() < 11) {
                Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                return;
            } else {
                mVerifyCode = String.format("%06d", (int) (Math.random() * 1000000 % 1000000));
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("请记住验证码");
                builder.setMessage("手机号" + phone + "，本次验证码是" + mVerifyCode + "，请输入验证码");
                builder.setPositiveButton("好的", null);
                AlertDialog alert = builder.create();
                alert.show();
            }
        }

        if (v.getId() == R.id.login_bt) {
            if (phone == null || phone.length() < 11) {
                Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                return;
            }
            if (et_password.getText().toString().equals(mVerifyCode) != true) {
                Toast.makeText(this, "请输入正确的验证码", Toast.LENGTH_SHORT).show();
                return;
            }
            else {
                loginSuccess();
            }
        }

        if(v.getId() == R.id.tv_mimadenglu){
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(),passwordActivity.class);
            startActivity(intent);
        }
    }

    private void loginSuccess() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        Toast.makeText(this,"恭喜账号为"+et_phone.getText().toString()+"的用户成功登录", Toast.LENGTH_SHORT).show();
        finish();
    }


    }

