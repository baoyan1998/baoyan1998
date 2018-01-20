package com.github.baoyan1998.zhihu.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.baoyan1998.zhihu.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class passwordActivity extends AppCompatActivity {
    private Button btn_login;
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;
    private List<Map<String, String>> paths = new ArrayList<Map<String, String>>();
    private Map<String, String> map;
    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public boolean validatePassword(String password) {
        return password.length() > 5;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        //getAllImagePath();

        final EditText usernameWrapper = (EditText) findViewById(R.id.et_phoneOrEmail);
        final EditText passwordWrapper = (EditText) findViewById(R.id.et_password);
        usernameWrapper.setHint(" ");
        passwordWrapper.setHint(" ");

        btn_login = (Button)findViewById(R.id.bt_button);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                String username = usernameWrapper.getText().toString();
                String password = passwordWrapper.getText().toString();
                if (!validateEmail(username)) {
                    usernameWrapper.setError("请输入正确的用户名");
                } else if (!validatePassword(password)) {
                    passwordWrapper.setError("请输入正确的密码");
                } else {
                    usernameWrapper.setEnabled(false);
                    passwordWrapper.setEnabled(false);
                    doLogin();
                }
            }
        });

    }

    private void doLogin() {
        Toast.makeText(getApplicationContext(),"你已成功登录",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(),MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("paths", (Serializable) paths);
        bundle.putSerializable("bbb", "bbb");
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    }

