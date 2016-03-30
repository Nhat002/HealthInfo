package com.example.nhat0.app3002.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.nhat0.app3002.R;
import com.example.nhat0.app3002.adapter.FilterListAdapter;
import com.example.nhat0.app3002.controller.MainController;
import com.example.nhat0.app3002.entity.AppPreferences;
import com.example.nhat0.app3002.entity.Category;
import com.example.nhat0.app3002.entity.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.prefs.AbstractPreferences;

/**
 * Created by nhat0 on 13/3/2016.
 */
public class FilterFragment extends Fragment {
    public static String TAG ="FilterFragment";

    private MainController mainController = MainController.getInstance();
    private RecyclerView categoryListView;
    private Button applyButton, resetButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.filter_fragment,container,false);
        categoryListView = (RecyclerView) view.findViewById(R.id.category_list);

        final FilterListAdapter adapter = new FilterListAdapter(this.getContext(),mainController.getCategories());
        categoryListView.setAdapter(adapter);
        categoryListView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        categoryListView.addItemDecoration(new SimpleDividerItemDecoration(this.getActivity()));
        applyButton = (Button) view.findViewById(R.id.btn_apply_filter);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> categoryID = new ArrayList<Integer>();
                int index = 0;
                for(Category category : adapter.getCategories()){
                    if(!category.isSelectForView()){
                        categoryID.add(index);
                    }
                    index++;
                }
                int[] id =  new int[categoryID.size()];
                for(int i =0; i < id.length;++i){
                    id[i] = categoryID.get(i);
                }
                mainController.getInformationListAdapter().filterData(id,mainController.getRemovedData());
            }
        });


        return view;
    }
}
