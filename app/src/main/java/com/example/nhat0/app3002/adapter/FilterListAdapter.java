package com.example.nhat0.app3002.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhat0.app3002.R;
import com.example.nhat0.app3002.entity.AppPreferences;
import com.example.nhat0.app3002.entity.Category;

import java.util.ArrayList;

/**
 * Created by nhat0 on 13/3/2016.
 */
public class FilterListAdapter extends RecyclerView.Adapter<FilterListAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Category> categories;
    public FilterListAdapter(Context context, ArrayList<Category> categories){
        this.context = context;
        this.categories = categories;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox mCheckBox;
        TextView label;
        public ViewHolder(View itemView) {
            super(itemView);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.category_select);
            label = (TextView) itemView.findViewById(R.id.category_label);
        }
    }
    public ArrayList<Category> getCategories(){
        return categories;
    }
    @Override
    public FilterListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.category_single_view,parent,false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(FilterListAdapter.ViewHolder holder, final int position) {

        holder.label.setText(categories.get(position).getTitle());
        Log.d("text", holder.label.getText().toString());
        holder.mCheckBox.setChecked(categories.get(position).isSelectForView());
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    categories.get(position).setSelectForView(true);
                } else {
                    categories.get(position).setSelectForView(false);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return AppPreferences.allCategory.length;
    }
}
