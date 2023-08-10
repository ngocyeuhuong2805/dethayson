package com.example.ph26008_thiandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ph26008_thiandroid.adapter.SpAdapter;
import com.example.ph26008_thiandroid.api.ApiService;
import com.example.ph26008_thiandroid.models.SpModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpActivity extends AppCompatActivity {
    private Button btnGet, btnPush;
    private RecyclerView rcv;

    private List<SpModel> list;

    private SpAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp);

        btnGet = findViewById(R.id.btnGetData);
        btnPush = findViewById(R.id.btnPushData);
        rcv = findViewById(R.id.recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcv.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcv.addItemDecoration(itemDecoration);

        list =new ArrayList<>();
        adapter =new SpAdapter(this);

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallApiMockApi();
            }
        });
        btnPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpActivity.this, ThemDataActivity.class);
                startActivity(intent);
            }
        });




    }
    private void CallApiMockApi(){
        ApiService.apiService.GetDataMockApi().enqueue(new Callback<List<SpModel>>() {
            @Override
            public void onResponse(Call<List<SpModel>> call, Response<List<SpModel>> response) {
                list = response.body();
                adapter.setData(list);
                rcv.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<SpModel>> call, Throwable t) {
                Toast.makeText(SpActivity.this, "get data fail", Toast.LENGTH_SHORT).show();
                Log.d("aaaa", t.getMessage());

            }
        });
    }
}