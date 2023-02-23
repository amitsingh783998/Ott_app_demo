package com.seeksolution.ottplateformdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.seeksolution.ottplateformdemo.Adapter.SubsrciberPackageAdapter;
import com.seeksolution.ottplateformdemo.Api.RetrofitClient;
import com.seeksolution.ottplateformdemo.Model.PackageResponse;
import com.seeksolution.ottplateformdemo.Model.Packages;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PackageSubscribe extends AppCompatActivity implements View.OnClickListener {

    public RecyclerView rv_package_list;
    public ArrayList<Packages> packagesArrayList;
    public Button btn_package_proceed;
    public String IntentData_userId,IntentData_userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_subscribe);

        //Find View By Id of Recycler View
        rv_package_list = (RecyclerView)findViewById(R.id.rc_subscribe_package_list);
        packagesArrayList = new ArrayList<>();
        rv_package_list.setLayoutManager(new LinearLayoutManager(this));
        //Api Call
        //data set prepare
        //Adapter Bind
        //show the Progress Dialog

        btn_package_proceed = (Button)findViewById(R.id.btn_package_proceed);
        btn_package_proceed.setOnClickListener(this);

        //Get Intent Data........
        Bundle extras = getIntent().getExtras();
        IntentData_userId = extras.getString("user_id",null);
        IntentData_userName = extras.getString("user_name",null);


        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Call<PackageResponse> call = RetrofitClient.getInstance().getAPI().getSubscriptionPackages();
        call.enqueue(new Callback<PackageResponse>() {
            @Override
            public void onResponse(Call<PackageResponse> call, Response<PackageResponse> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(PackageSubscribe.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                        PackageResponse packageResponse = response.body();
                        if(packageResponse.isStatus() == true && packageResponse.getCode().equals("201")){

                            int PackageSize = packageResponse.getData().size();
                            for(int i =0; i<PackageSize; i++) {
                                packagesArrayList.add(new Packages(
                                        packageResponse.getData().get(i).getId(),
                                        packageResponse.getData().get(i).getPackage_name(),
                                        packageResponse.getData().get(i).getPackage_price(),
                                       " / "+packageResponse.getData().get(i).getPackage_duration(),
                                        packageResponse.getData().get(i).getPackage_desc(),
                                        packageResponse.getData().get(i).getPackage_pic()
                                ));
                            }

                            SubsrciberPackageAdapter adapter = new SubsrciberPackageAdapter(getApplicationContext(),packagesArrayList);
                            rv_package_list.setAdapter(adapter);


                            //Intent Goes Here
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.dismiss();

                                }
                            },2000);

                        }else{
                            Toast.makeText(PackageSubscribe.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }
            }

            @Override
            public void onFailure(Call<PackageResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        //get the Subscribed Package
        SharedPreferences sp = getSharedPreferences("user_data",MODE_PRIVATE);
        String package_id = sp.getString("user_package_id",null);
        String package_name= sp.getString("user_package_name",null);

        if(package_id !=null && package_name !=null) {

            //Confirm Box
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Confirm Proceed ?");
            builder.setMessage("Hi,"+IntentData_userName+" are going to subscribe the " + package_name + " Press Yes to continue.");
            builder.setIcon(R.drawable.subscribe_icon);

            //yes : pasitive Button
            //No : Negative Button
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                     //Intent
                    Toast.makeText(getApplicationContext(), "" + package_id, Toast.LENGTH_SHORT).show();
                     Toast.makeText(getApplicationContext(), "" + package_name, Toast.LENGTH_SHORT).show();
                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

        }else{

             Toast.makeText(this, "Please Subscribe the Package", Toast.LENGTH_SHORT).show();
        }

        SharedPreferences sp2 = getSharedPreferences("user_data",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp2.edit();
        editor.remove("user_package_id");
        editor.remove("user_package_name");
        editor.commit();




    }
}