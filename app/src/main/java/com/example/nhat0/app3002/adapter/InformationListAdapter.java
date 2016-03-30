package com.example.nhat0.app3002.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nhat0.app3002.R;
import com.example.nhat0.app3002.SingleViewActivity;
import com.example.nhat0.app3002.controller.MainController;
import com.example.nhat0.app3002.entity.AppPreferences;
import com.example.nhat0.app3002.entity.DiseaseInformation;
import com.example.nhat0.app3002.entity.HealthInformation;
import com.example.nhat0.app3002.entity.HealthcareInformation;
import com.example.nhat0.app3002.entity.WeatherForecastInformation;

import java.util.ArrayList;
import java.util.prefs.AbstractPreferences;

import static java.util.Collections.sort;

/**
 * Created by nhat0 on 7/3/2016.
 */
public class InformationListAdapter extends RecyclerView.Adapter<InformationListAdapter.ViewHolder> {
    private Context context;
    private ArrayList<HealthInformation> mData;
    private MainController mainController = MainController.getInstance();
    private String filter = null;
    public InformationListAdapter(Context context, ArrayList<HealthInformation> data) {
        this.context = context;
        this.mData = data;
        Log.d("DATA",data.toString());
        sort(mData);
    }
    public void filterData(int[] categoryID, ArrayList<HealthInformation> removedData){
        Log.d("data", String.valueOf(removedData.size()));
        mData.addAll(removedData);
        removedData.clear();
        sort(mData);
        Log.d("data", String.valueOf(mData.size()));
        for(int  i =0; i < categoryID.length; ++i) {
            if (categoryID[i] == 0) {
                for (int j =0; j < mData.size();++j) {
                    if ( mData.get(j) instanceof WeatherForecastInformation) {
                        removedData.add(mData.remove(j));
                        j--;
                    }
                }
            }
            else if (categoryID[i] == 1) {
                for (int j =0; j < mData.size();++j) {
                    if ( mData.get(j) instanceof DiseaseInformation) {
                        removedData.add(mData.remove(j));
                        j--;
                    }
                }
            }
            else if (categoryID[i] == 2) {
                for (int j =0; j < mData.size();++j) {
                    if ( mData.get(j) instanceof HealthcareInformation) {
                        removedData.add(mData.remove(j));
                        j--;
                    }
                }
            }
            else if(categoryID[i] == 3){
                for (int j =0; j < mData.size();++j) {
                    if ( mData.get(j) instanceof HealthcareInformation && ((HealthcareInformation) mData.get(j)).getCategory().equals(AppPreferences.allCategory[3])) {
                        removedData.add(mData.remove(j));
                        j--;
                    }
                }
            }
            else if(categoryID[i] == 4){
                for (int j =0; j < mData.size();++j) {
                    if ( mData.get(j) instanceof HealthcareInformation && ((HealthcareInformation) mData.get(j)).getCategory().equals(AppPreferences.allCategory[4])) {
                        removedData.add(mData.remove(j));
                        j--;
                    }
                }
            }
            else if(categoryID[i] == 5){
                for (int j =0; j < mData.size();++j) {
                    if ( mData.get(j) instanceof HealthcareInformation && ((HealthcareInformation) mData.get(j)).getCategory().equals(AppPreferences.allCategory[5])) {
                        removedData.add(mData.remove(j));
                        j--;
                    }
                }
            }


        }
        notifyDataSetChanged();
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView type;
        TextView date;
        TextView title;
        RelativeLayout container;
       /* TextView content;*/
 /*       ImageView mark;*/

        public ViewHolder(View itemView) {
            super(itemView);
            type=(ImageView) itemView.findViewById(R.id.information_type);
            date = (TextView) itemView.findViewById(R.id.information_date);
            title = (TextView) itemView.findViewById(R.id.information_title);
            container = (RelativeLayout) itemView.findViewById(R.id.information_container);
            /*content = (TextView) itemView.findViewById(R.id.information_content);*/
            /*mark = (ImageView) itemView.findViewById(R.id.information_mark_read);*/
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.information_single_view, parent, false);

        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final HealthInformation data = mData.get(position);
        if(data instanceof HealthcareInformation){
            holder.type.setImageResource(R.mipmap.ic_icon_h);
        }
        else if(data instanceof DiseaseInformation){
            holder.type.setImageResource(R.mipmap.ic_icon_d);
        }
        else if(data instanceof WeatherForecastInformation){
            holder.type.setImageResource(R.mipmap.ic_icon_w);
        }
        else{
            holder.type.setImageResource(R.mipmap.ic_icon_w);
        }

        holder.date.setText(data.getFormatUpdateTime());
        holder.title.setText(data.getTitle());
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SingleViewActivity.class);
                intent.putExtra(AppPreferences.DATA_ID,data.getId());
                if(data instanceof HealthcareInformation) {
                    intent.putExtra(AppPreferences.DATA_TYPE, "h");
                }
                else if(data instanceof DiseaseInformation){
                    intent.putExtra(AppPreferences.DATA_TYPE, "d");
                }
                else if(data instanceof WeatherForecastInformation){
                    intent.putExtra(AppPreferences.DATA_TYPE,"w");
                }

                context.startActivity(intent);
            }
        });
        /*holder.mark.setImageResource(R.drawable.ic_menu_send);*/
    }

    @Override
    public long getItemId(int position) {
        return mData.get(position).hashCode();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


}