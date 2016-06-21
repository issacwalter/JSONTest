package com.example.shit.jsontest;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.show.api.ShowApiRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by He on 2016/5/16.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.RecycViewHolder> {
    public static final String TAG = "TTEST";
    public static final int howManyToAdd = 20;
    public static int page = 1;
    Handler mhandler = new Handler();

    private boolean hasInited = false;
    public List<String> datas = new ArrayList<>(howManyToAdd);
    public List<String> tempDatas = new ArrayList<>();

    private Context context;
    private long lastExecuteTime = 0;

    public MyRecyclerAdapter(Context context) {
        lastExecuteTime = System.currentTimeMillis();
        this.context = context;
        //第一次启动，初始化时做的事情
        if (!hasInited) {
            //addItems(howManyToAdd);
            for (char c = 'A'; c < 91; c++) {
                datas.add(c + "");
            }
            hasInited = true;
            Log.d(TAG, "MyRecyclerAdapter IN: executed" + datas.size());

        }
        Log.d(TAG, "MyRecyclerAdapter OUT: executed" + datas.size());
    }


    public boolean addItems(final int howManyToAdd) {
        long thisExecuteTime = System.currentTimeMillis();
        Log.d(TAG, "hasInited=" + hasInited + ";     lastExecuteTime-thisExecuteTime" + (thisExecuteTime - lastExecuteTime));

        if (hasInited && thisExecuteTime - lastExecuteTime < 3000) {
            //lastExecuteTime = thisExecuteTime;
            return false;
        }


        new Thread() {
            //在新线程中发送网络请求
            public void run() {
                String appid = "19660";//要替换成自己的
                String secret = "a1e3aad1be1840a28034a9495dca5f14";//要替换成自己的
                Log.d(TAG, "PAGE: " + page);
                String res = null;
                try {
                    res = new ShowApiRequest("http://route.showapi.com/341-1", appid, secret)
                            .addTextPara("time", "2015-07-10")
                            .addTextPara("page", String.valueOf(page))
                            .addTextPara("maxResult", String.valueOf(howManyToAdd))
                            .post();
                } catch (Exception e) {
                    Toast.makeText(context, "network wrong", Toast.LENGTH_SHORT).show();
                    res = null;
                    for (int i = 0; i < 16; i++) {
                        datas.add(null);
                    }
                }
                page += 1;
                // res is the original JSON String,which should be resolved with "JSON.parseObject(res, MyJSON.class)" to the outest class,
                // which contains three system parameters:showapi_res_code,showapi_res_error,showapi_res_body,and the content we need is in the last one
                MyJSON jsonobs = JSON.parseObject(res, MyJSON.class);
                // get the data in the form of String,which contains contentlist, allpages,currentpage,ect.
                if (jsonobs.getShowapi_res_code() == 0) {

                    String body = jsonobs.getShowapi_res_body();
                    // parse the data String to inner class,to get the member var which contains main text(jokes).
                    MyJSONofSubclass subclass = JSON.parseObject(body, MyJSONofSubclass.class);
                    // contentlist is an array,to be specific,a JSONArray.so we can get every class of the true content by using listOfGrandSubclass.get(i).getText()
                    final List<MyJSONofGrandSubclass> listOfGrandSubclass = JSON.parseArray(subclass.getContentlist(), MyJSONofGrandSubclass.class);
                    // Log.d(TAG, "listOfGrandSubclass . size: " + listOfGrandSubclass.size());


                    //把返回内容通过handler对象更新到界面

                    for (int i = 0; i < listOfGrandSubclass.size(); i++) {
                        tempDatas.add(listOfGrandSubclass.get(i).getText());
                    }
                } else {
                    for (int i = 0; i < 15; i++) {
                        tempDatas.add(null);
                    }
                }

            }
        }.start();

        datas.addAll(tempDatas);
        tempDatas.clear();
        Log.d(TAG, "addItems: " + datas.size());
        lastExecuteTime = thisExecuteTime;
        return true;
    }


    @Override
    public RecycViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecycViewHolder holder = new RecycViewHolder(LayoutInflater.from(context).inflate(R.layout.item_of_recyclerview, parent, false));


        return holder;
    }

    @Override
    public void onBindViewHolder(MyRecyclerAdapter.RecycViewHolder holder, int position) {
        holder.tv.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public static class RecycViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public RecycViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.textview_of_recycler_item);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
