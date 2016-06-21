package com.example.shit.jsontest;

import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.show.api.ShowApiRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by He on 2016/6/14.
 */
public class GetDataFromService {
    public static final String TAG = "TTEST";
    public static final int howManyToAdd = 20;
    public static int PAGE = 1;
    public static List<String> datas = new ArrayList<>(howManyToAdd);

    public static boolean addItems(final int howManyToAdd) {

        long thisExecuteTime = System.currentTimeMillis();
//        Log.d("TTEST", "hasInited=" + hasInited + ";     lastExecuteTime-thisExecuteTime" + (thisExecuteTime - lastExecuteTime));

        datas.addAll(GetDataFromService.getJSONFromService());
        Log.d(TAG, "addItems: " + datas.size());
        return true;
    }

    public static ArrayList<String> getJSONFromService(){
        final ArrayList<String> loading = new ArrayList<>();
        new Thread() {
            //在新线程中发送网络请求
            public void run() {
                String appid = "19660";//要替换成自己的
                String secret = "a1e3aad1be1840a28034a9495dca5f14";//要替换成自己的
                Log.d(TAG, "PAGE: " + PAGE);
                String res = null;
                try {
                    res = new ShowApiRequest("http://route.showapi.com/341-1", appid, secret)
                            .addTextPara("time", "2015-07-10")
                            .addTextPara("page", String.valueOf(PAGE))
                            .addTextPara("maxResult", String.valueOf(howManyToAdd))
                            .post();
                } catch (Exception e) {
                    res = null;
                    for (int i = 0; i < 16; i++) {
                        loading.add(null);
                    }
                }
                PAGE += 1;
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
                        loading.add(listOfGrandSubclass.get(i).getText());
                    }
                } else {
                    for (int i = 0; i < 15; i++) {
                        loading.add(null);
                    }
                }

            }
        }.start();

        return loading;
    }
}
