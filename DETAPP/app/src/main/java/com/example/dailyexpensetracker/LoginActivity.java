package com.example.dailyexpensetracker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    public static final String PREFERENCE_NAME = "session";
    public static final String KEY_ISE_LOGGED_IN = "isLoggedIn";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_USERNAME = "username";

    EditText inputEmail, inputPassword;
    Button buttonLogin;
    TextView textCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstancetate) {

        super.onCreate(savedInstancetate);
        setContentView(R.layout.activity_login);
        textCreateAccount = findViewById(R.id.lnkRegister);
        textCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });
        inputEmail = findViewById(R.id.txtloginEmail);
        inputPassword = findViewById(R.id.txtloginPwd);
        buttonLogin = findViewById(R.id.btnlogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputEmail.getText().toString().equals("")){
                    Toast.makeText(LoginActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                }
                else if(inputPassword.getText().toString().equals("")){
                    Toast.makeText(LoginActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }else {
                    login();
                }
            }
        });
    }

    private void login(){
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Logging in...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        NetworkService networkService = NetworkClient.getClient().create(NetworkService.class);
        Call<LoginResponse> login = networkService.login(inputEmail.getText().toString(),inputPassword.getText().toString());
        login.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse response1 = response.body();
                if(response1!=null){
                    if(response1.getSuccess().equals("1")){
                        SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME,MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean(KEY_ISE_LOGGED_IN,true);
                        editor.putString(KEY_USERNAME,response1.getUserDetailObject().getUserDetails().get(0).getFirstName()+" "+response1.getUserDetailObject().getUserDetails().get(0).getLastName());
                        editor.apply();
                        Toast.makeText(LoginActivity.this,response1.getMessage(),Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                    }
                    else {
                        Toast.makeText(LoginActivity.this,response1.getMessage(),Toast.LENGTH_SHORT).show();;
                    }
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    public boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();

    }
}
