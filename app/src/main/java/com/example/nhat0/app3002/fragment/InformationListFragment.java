package com.example.nhat0.app3002.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nhat0.app3002.R;
import com.example.nhat0.app3002.controller.MainController;
import com.example.nhat0.app3002.entity.SimpleDividerItemDecoration;

/**
 * Created by nhat0 on 12/3/2016.
 */
public class InformationListFragment extends Fragment{
    public static final String TAG = "InformationListFragment";
    private MainController mainController = MainController.getInstance();
    private RecyclerView informationListView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.information_fragment, container, false);
        informationListView = (RecyclerView) view.findViewById(R.id.information_list);
        informationListView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        informationListView.addItemDecoration(new SimpleDividerItemDecoration(this.getActivity()));
        informationListView.setAdapter(mainController.getInformationListAdapter());
        Log.d("list view create", "true");
        return view;
    }

}
