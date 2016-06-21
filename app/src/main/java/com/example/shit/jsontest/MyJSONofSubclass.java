package com.example.shit.jsontest;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by He on 2016/5/30.
 */
public class MyJSONofSubclass {

    private int ret_code;
    private int allNum;
    private int allPage;
    private int currentPage;
    private int maxResult;
    private String contentlist;

    public MyJSONofSubclass() {
    }

    public MyJSONofSubclass(int ret_code, int allNum, int allPage, int currentPage, int maxResult, String contentlist) {
        this.ret_code = ret_code;
        this.allNum = allNum;
        this.allPage = allPage;
        this.currentPage = currentPage;
        this.maxResult = maxResult;
        this.contentlist = contentlist;
    }

    public int getRet_code() {
        return ret_code;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public int getAllNum() {
        return allNum;
    }

    public void setAllNum(int allNum) {
        this.allNum = allNum;
    }

    public int getAllPage() {
        return allPage;
    }

    public void setAllPage(int allPage) {
        this.allPage = allPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getMaxResult() {
        return maxResult;
    }

    public void setMaxResult(int maxResult) {
        this.maxResult = maxResult;
    }

    public String getContentlist() {
        return contentlist;
    }

    public void setContentlist(String contentlist) {
        this.contentlist = contentlist;
    }

    @Override
    public String toString() {
        return "MyJSONofSubclass{" +
                "ret_code=" + ret_code +
                ", allNum='" + allNum + '\'' +
                ", allPage='" + allPage + '\'' +
                ", currentPage='" + currentPage + '\'' +
                ", maxResult='" + maxResult + '\'' +
                ", contentlist=" + contentlist +
                '}';
    }
}
