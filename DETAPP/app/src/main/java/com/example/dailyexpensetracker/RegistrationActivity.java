package com.example.dailyexpensetracker;

import android.app.AppComponentFactory;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.P)
public class RegistrationActivity extends AppCompatActivity {

    EditText inputName, inputPassword, inputEmail;
    Button buttonRegister;
    TextView linkLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputName = findViewById(R.id.txtName);
        inputEmail = findViewById(R.id.txtEmail);
        inputPassword = findViewById(R.id.txtPwd);
        linkLogin = findViewById(R.id.lnkLogin);
        linkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        buttonRegister = findViewById(R.id.btnregister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputName.getText().toString().equals("")){
                    Toast.makeText(RegistrationActivity.this,"Enter UserName",Toast.LENGTH_SHORT).show();
                }else if (inputEmail.getText().toString().equals("")) {
                    Toast.makeText(RegistrationActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                } else if (inputPassword.getText().toString().equals("")) {
                    Toast.makeText(RegistrationActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                }
                else{
                    HashMap<String,String> params = new HashMap<>();
                    params.put("uname",inputName.getText().toString());
                    params.put("email",inputEmail.getText().toString());
                    params.put("password",inputPassword.getText().toString());
                    register(params);
                }
            }
        });
    }

    private void register(HashMap<String, String> params) {

        final ProgressDialog progressDialog = new ProgressDialog(RegistrationActivity.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Registering...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        NetworkService networkService = NetworkClient.getClient().create(NetworkService.class);
        Call<RegistrationResponse> registerCall = networkService.register(params);

        registerCall.enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                RegistrationResponse response1 = response.body();
                if(response1!=null)
                {
                    Toast.makeText(RegistrationActivity.this,response1.getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrationActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(RegistrationActivity.this,response1.getMessage(),Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });



    }


}
