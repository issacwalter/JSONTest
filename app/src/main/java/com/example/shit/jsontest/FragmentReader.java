package com.example.shit.jsontest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;

/**
 * Created by He on 2016/4/12.
 */
public class FragmentReader extends Fragment {
    public static final String TAG = "TTEST";

    View view;
    RecyclerViewHeader header;
    ImageView imageView;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    MyRecyclerAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmentlayout_shit_reader, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.shit_reader);
        //header = (RecyclerViewHeader) view.findViewById(R.id.header);
        myAdapter = new MyRecyclerAdapter(getContext());

//        smartRecyclerAdapter.setHeaderView(container.findViewById(R.id.headerpics));
        Log.d(TAG, "onCreateView: " + container.findViewById(R.id.headerpics));
        linearLayoutManager = new LinearLayoutManager(getContext());
        //linearLayoutManager.setReverseLayout(true);

        recyclerView.setLayoutManager(linearLayoutManager);

        //header.attachTo(recyclerView,true);
        recyclerView.addItemDecoration(new DividerOfRecycler(getContext(), DividerOfRecycler.VERTICAL_LIST));
        recyclerView.setAdapter(myAdapter);
        //监听滚动状态，加载更多
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int totalItems = myAdapter.datas.size() - 4;
                Log.d(TAG, "visible: " + linearLayoutManager.findLastVisibleItemPosition() + "     totalItems:" + totalItems);

                if (linearLayoutManager.findLastVisibleItemPosition() > totalItems) {
                    Log.d(TAG, "datas size before: " + myAdapter.datas.size());
                    if (myAdapter.addItems(20)) {
                        myAdapter.notifyDataSetChanged();
                    }
                    Log.d(TAG, "datas size after: " + myAdapter.datas.size());
                }
                ;
            }

        });
        return view;
    }
}
