package com.seeksolution.ottplateformdemo.Adapter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.seeksolution.ottplateformdemo.MainActivity;
import com.seeksolution.ottplateformdemo.Model.Packages;
import com.seeksolution.ottplateformdemo.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

public class SubsrciberPackageAdapter extends RecyclerView.Adapter<SubsrciberPackageAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Packages> packagesList;
    private RadioButton rbChecked = null;
    private int rbPosition = 0;

    public SubsrciberPackageAdapter(Context context, ArrayList<Packages> packagesList) {
        this.context = context;
        this.packagesList = packagesList;

        SharedPreferences sp2 = context.getSharedPreferences("user_data",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp2.edit();
        editor.remove("user_package_id");
        editor.remove("user_package_name");
        editor.commit();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mycustom_subscription_package_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final int index = position;

        holder.tv_packge_name.setText(packagesList.get(position).getPackage_name());
        holder.tv_package_price.setText(packagesList.get(position).getPackage_price());
        holder.tv_package_duration.setText(packagesList.get(position).getPackage_duration());
        holder.tv_package_desc.setText(packagesList.get(position).getPackage_desc());

        Picasso.get().load( Uri.parse( packagesList.get(position).package_pic) ).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                if(index > 0){
                    holder.rv_package_bg.setBackground(new BitmapDrawable(bitmap));
                }

            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });



        //Code for disabling the Other RadioButton








    }

    @Override
    public int getItemCount() {
        return packagesList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout rv_package_bg;
        public TextView tv_packge_name,tv_package_price,tv_package_duration,tv_package_desc;
        public RadioButton rd_package_btn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_packge_name = itemView.findViewById(R.id.tv_package_name);
            tv_package_price = itemView.findViewById(R.id.tv_package_price);
            tv_package_duration = itemView.findViewById(R.id.tv_package_duration);
            tv_package_desc = itemView.findViewById(R.id.tv_package_desc);
            rv_package_bg = itemView.findViewById(R.id.rl_package_background);

            //Radio_Button
            rd_package_btn = itemView.findViewById(R.id.rd_package_btn);

            if (rbPosition == 0 && rd_package_btn.isChecked())
            {
                rbChecked = rd_package_btn;
                rbPosition = 0;
            }
            rd_package_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RadioButton rb = (RadioButton) v;
                    int clickedPos = getAdapterPosition();
                    if (rb.isChecked()){
                        if(rbChecked != null)
                        {
                            rbChecked.setChecked(false);
                        }

                        rbChecked = rb;
                        rbPosition = clickedPos;
                    }
                    else{
                        rbChecked = null;
                        rbPosition = 0;
                    }

                       // Toast.makeText(context, ""+packagesList.get(rbPosition).getPackage_name(), Toast.LENGTH_SHORT).show();
                       // Toast.makeText(context, ""+packagesList.get(rbPosition).getId(), Toast.LENGTH_SHORT).show();

                        //sharedPreferencesCode : session
                        SharedPreferences sp = context.getSharedPreferences("user_data", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("user_package_id",packagesList.get(rbPosition).getId());
                        editor.putString("user_package_name",packagesList.get(rbPosition).getPackage_name());
                        editor.commit();



                }
            });



        }
    }
}
