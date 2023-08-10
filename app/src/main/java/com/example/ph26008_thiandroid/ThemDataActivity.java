package com.example.ph26008_thiandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ph26008_thiandroid.adapter.SpAdapter;
import com.example.ph26008_thiandroid.api.ApiService;
import com.example.ph26008_thiandroid.models.SpModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemDataActivity extends AppCompatActivity {
    private EditText ed_name, ed_des, ed_image, ed_color, ed_price;
    private Button btnThemData;

    private List<SpModel> list;

    private SpAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_data);
        ed_name =findViewById(R.id.ed_name);
        ed_des = findViewById(R.id.ed_description);
        ed_image =findViewById(R.id.ed_img);
        ed_color =findViewById(R.id.ed_color);
        ed_price = findViewById(R.id.ed_price);
        btnThemData =findViewById(R.id.btnThemData);

        btnThemData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemData();
            }
        });
    }

    private void ThemData() {


        String name = ed_name.getText().toString().trim();
        String des = ed_des.getText().toString().trim();
        String image = ed_image.getText().toString().trim();
        String color = ed_color.getText().toString().trim();


        SpModel sp = new SpModel(name, des, image, color);


        Call<SpModel> call = ApiService.apiService.ThemDataToSever(sp);
        call.enqueue(new Callback<SpModel>() {
            @Override
            public void onResponse(Call<SpModel> call, Response<SpModel> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(ThemDataActivity.this,response.code(), Toast.LENGTH_SHORT).show();
                }
                List<SpModel> postlist = new ArrayList<>();
                postlist.add(response.body());
                adapter.setData(postlist);
                onBackPressed();
            }

            @Override
            public void onFailure(Call<SpModel> call, Throwable t) {
                Toast.makeText(ThemDataActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });







    }
}