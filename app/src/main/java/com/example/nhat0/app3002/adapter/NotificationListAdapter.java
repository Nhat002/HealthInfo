package com.example.nhat0.app3002.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.nhat0.app3002.R;
import com.example.nhat0.app3002.entity.AppNotification;

import java.util.ArrayList;

/**
 * Created by nhat0 on 29/3/2016.
 */
public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.ViewHolder> {
    private ArrayList<AppNotification> notis;
    private Context context;
    public NotificationListAdapter(ArrayList<AppNotification> notis, Context context){
        this.notis = notis;
        this.context = context;
    }

    public void addNoti(AppNotification appNotification) {
        notis.add(appNotification);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView msg;
        private Button delete;
        public ViewHolder(View itemView){
            super(itemView);
            delete = (Button) itemView.findViewById(R.id.btn_delete_noti);
            title = (TextView) itemView.findViewById(R.id.notification_title);
            msg = (TextView) itemView.findViewById(R.id.notification_msg);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.notification_single_view, parent, false);

        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        AppNotification noti = notis.get(position);
        holder.title.setText(noti.getTitle());
        holder.msg.setText(noti.getMsg());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notis.remove(notis.get(position));
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return notis.size();
    }

    public ArrayList<AppNotification> getNotis(){
        return notis;
    }

}
