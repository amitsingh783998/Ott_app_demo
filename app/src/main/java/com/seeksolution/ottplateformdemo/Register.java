package com.seeksolution.ottplateformdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.seeksolution.ottplateformdemo.Api.RetrofitClient;
import com.seeksolution.ottplateformdemo.Model.CreateUserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText et_name,et_email,et_mobile,et_password;
    private Button register_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_name = (TextInputEditText)findViewById(R.id.et_register_name);
        et_email = (TextInputEditText)findViewById(R.id.et_register_email);
        et_mobile = (TextInputEditText)findViewById(R.id.et_register_mobile);
        et_password = (TextInputEditText)findViewById(R.id.et_register_password);

        register_button = (Button) findViewById(R.id.register_button);
        //setOnclickListener
       register_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //Please Enter the code
        String name = et_name.getText().toString().trim();
        String email = et_email.getText().toString().trim();
        String mobile = et_mobile.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        //Retrofit Api Call
        Call<CreateUserResponse> call = RetrofitClient
                .getInstance()
                .getAPI()
                .createUser(
                        name,
                        email,
                        password,
                        mobile
                );
        call.enqueue(new Callback<CreateUserResponse>() {
            @Override
            public void onResponse(Call<CreateUserResponse> call, Response<CreateUserResponse> response) {
                if(response.isSuccessful()){
                    CreateUserResponse userResponse = response.body();

                    if(userResponse.getCode().equals("201") && userResponse.isStatus() == true){
                        Toast.makeText(Register.this, ""+userResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            //Intent Code
                        String NewUser_name = userResponse.getData().get(0).getName();
                        String NewUser_id  = userResponse.getData().get(0).getId();

                        //Session : SharedPreference : Login : for Permanent Data Storage.
//                        SharedPreferences sp = getSharedPreferences("user_data",MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sp.edit();
//                        editor.putString("user_id",NewUser_id);
//                        editor.putString("user_name",NewUser_name);
//                        editor.commit();

                        Intent intent = new Intent(Register.this,PackageSubscribe.class);
                        intent.putExtra("user_id",NewUser_id);
                        intent.putExtra("user_name",NewUser_name);
                        startActivity(intent);
                        finish();


                    }else{
                        Toast.makeText(Register.this, ""+userResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<CreateUserResponse> call, Throwable t) {
                Toast.makeText(Register.this, "Internet or Api Issue", Toast.LENGTH_SHORT).show();
            }
        });





    }
}