package com.example.admin.myapplication.customviews;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.myapplication.R;

public class LoginForm extends LinearLayout {
    String name;
    String pass;
    private Button btnValidate;
    private Button btnSubmit;
    private TextView tvName;
    private TextView tvPass;
    private TextView tvError;
    public static final String MY_SHARED_PREF = "mySharedPref";
    private SharedPreferences sharedPreferences;
    private EditText etName;
    private EditText etPass;
    private int buttonColor;

    //region getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    //endregion

    public LoginForm(Context context) {
        super(context);
        initViews(context);
    }

    public LoginForm(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    public LoginForm(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    public LoginForm(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initViews(context);
    }

    private void initViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        sharedPreferences = context.getSharedPreferences(MY_SHARED_PREF, Context.MODE_PRIVATE);
        inflater.inflate(R.layout.loginform_view, this);
        TypedArray typedArray = context.obtainStyledAttributes(null, R.styleable.LoginForm);
        buttonColor = typedArray.getColor(R.styleable.LoginForm_buttonColor,
                getResources().getColor(android.R.color.holo_blue_light));
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        tvError = (TextView) getChildAt(0);
        tvName = (TextView) getChildAt(1);
        etName = (EditText) getChildAt(2);
        tvPass = (TextView) getChildAt(3);
        etPass = (EditText) getChildAt(4);
        btnSubmit = (Button) getChildAt(5);
        btnValidate = (Button) getChildAt(6);
        btnSubmit.setBackgroundColor(buttonColor);
        btnValidate.setBackgroundColor(buttonColor);

        btnSubmit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etName.getText().length() == 0 || etPass.getText().length() == 0) {
                    tvError.setText("Must enter both name and password");
                    return;
                }
                String name = etName.getText().toString();
                String pass = etPass.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(name, pass);
                tvError.setText("Password submitted");
                editor.commit();
            }
        });

        btnValidate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etName.getText().length() == 0 || etPass.getText().length() == 0) {
                    tvError.setText("Must enter both name and password");
                    return;
                }
                String name = etName.getText().toString();
                String pass = etPass.getText().toString();
                String savedPass = sharedPreferences.getString(name, "");
                if (pass.equals(savedPass))
                    tvError.setText("Password matches");
                else
                    tvError.setText("Name and password don't match");
            }
        });
    }
}
