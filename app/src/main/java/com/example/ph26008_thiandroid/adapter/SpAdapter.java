package com.example.ph26008_thiandroid.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ph26008_thiandroid.R;
import com.example.ph26008_thiandroid.api.ApiService;
import com.example.ph26008_thiandroid.models.SpModel;
import com.google.android.gms.common.api.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpAdapter extends RecyclerView.Adapter<SpAdapter.SpViewHolder>{
    private List<SpModel> list;

    private Context context;

    public SpAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<SpModel> list) {
        this.list = list;
    }

    private EditText edtName, edtDes, edtImage, edtColor, edtPrice;
    private Button btnCancle, btnUpdate;

    @NonNull
    @Override
    public SpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_iteam, parent, false);
        return new SpViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if(list != null){
            return list.size();

        }
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull SpViewHolder holder, int position) {
        SpModel spModel = list.get(position);
        if(spModel == null){
            return;
        }
//        String url  = decodeBase64(hackNasa.getUrl());

        Glide.with(context).load(spModel.getImage()).error(R.drawable.ic_launcher_background).into(holder.img);
        holder.tv_name.setText(spModel.getName());
        holder.tv_description.setText(spModel.getDescription());


        holder.imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);

                dialog.setContentView(R.layout.layout_update);

                edtName = dialog.findViewById(R.id.edtName);
                edtDes = dialog.findViewById(R.id.edtDes);
                edtImage = dialog.findViewById(R.id.edtImage);
                edtColor = dialog.findViewById(R.id.edtColor);
                edtPrice = dialog.findViewById(R.id.edtPrice);

                btnUpdate = dialog.findViewById(R.id.btnUpdate);

                edtName.setText(spModel.getName());
                edtDes.setText(spModel.getDescription());
                edtImage.setText(spModel.getImage());
                edtColor.setText(spModel.getColor());

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = edtName.getText().toString().trim();
                        String des = edtDes.getText().toString().trim();
                        String image = edtImage.getText().toString().trim();
                        String color = edtColor.getText().toString().trim();

                        spModel.setName(name);
                        spModel.setImage(image);
                        spModel.setColor(color);
                        spModel.setDescription(des);

                        Call<SpModel> call = ApiService.apiService.UpdateData(spModel.getId(), spModel );

                        call.enqueue(new Callback<SpModel>() {
                            @Override
                            public void onResponse(Call<SpModel> call, Response<SpModel> response) {
                                Toast.makeText(context, "thanh cong", Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();
                                dialog.dismiss();

                            }

                            @Override
                            public void onFailure(Call<SpModel> call, Throwable t) {

                            }
                        });




                    }
                });
                dialog.show();
            }
        });
    }





    public class SpViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name, tv_description;
        private ImageView img, imgDelete, imgUpdate;



        public SpViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);
            tv_description = itemView.findViewById(R.id.tv_description);
            img = itemView.findViewById(R.id.img);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            imgUpdate = itemView.findViewById(R.id.imgUpdate);



        }



    }
}
