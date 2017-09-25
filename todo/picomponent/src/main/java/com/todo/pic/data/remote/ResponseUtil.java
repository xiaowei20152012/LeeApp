package com.todo.pic.data.remote;


import com.todo.pic.domain.model.HomeInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liwei5 on 2017/9/14.
 */

public class ResponseUtil {
    private static final String ERROR = "error";
    private static final String RESULTS = "results";
    /**
     * 处理请求结果
     *
     * @param json
     * @param callBack
     * @param url
     */
    private static void handleResponse(String json, HttpListener callBack, String url, boolean isCache) {
//        try {
//            //转化为json对象
//            JSONObject jsonObject = new JSONObject(json);
//            //判断error字段是否存在，不存在返回失败信息并结束请求
//            if (jsonObject.isNull(ERROR)) {
////                callBack.onFailure(1, "error key not exists!!");
//                onFail(callBack,1,"error key not exists!!");
//                return;
//            }
//            //判断后台返回结果，true表示失败，false表示成功，失败则返回错误回调并结束请求
//            if (jsonObject.getBoolean(ERROR)) {
////                callBack.onFailure(1, "request failure!!");
//                onFail(callBack,1,"request failure!!");
//                return;
//            }
//            //判断results字段是否存在，不存在返回时报回调并结束请求
//            if (jsonObject.isNull(RESULTS)) {
////                callBack.onFailure(2, "results key not exists!!");
//                onFail(callBack,1,"results key not exists!!");
//                return;
//            }
//            //获取results的值
//            String results = jsonObject.getString(RESULTS);
//            if (isCache) {
//                //插入缓存数据库
////                dbManager.insertData(url, results);
//            }
//
//            //返回成功回调
////            callBack.onSuccess(results);
//            onSuccess(callBack,results);
//        } catch (JSONException e) {
//            callBack.onFailure(1, e.getLocalizedMessage());
//        }
    }
    public static boolean checkSuccess(String json){
        try {
            //转化为json对象
            JSONObject jsonObject = new JSONObject(json);
            //判断error字段是否存在，不存在返回失败信息并结束请求
            if (jsonObject.isNull(ERROR)) {
//                callBack.onFailure(1, "error key not exists!!");
//                onFail(callBack, 1, "error key not exists!!");
                return false;
            }
            //判断后台返回结果，true表示失败，false表示成功，失败则返回错误回调并结束请求
            if (jsonObject.getBoolean(ERROR)) {
//                callBack.onFailure(1, "request failure!!");
//                onFail(callBack, 1, "request failure!!");
                return false;
            }
            //判断results字段是否存在，不存在返回时报回调并结束请求
            if (jsonObject.isNull(RESULTS)) {
//                callBack.onFailure(2, "results key not exists!!");
//                onFail(callBack, 1, "results key not exists!!");
                return false;
            }

            return true;
        }catch (JSONException e){
            return false;
        }
    }

    public static List<HomeInfo> getSuccess(String json){
        List<HomeInfo> list = null;
        try{
            list = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            for (int i=0;i<jsonArray.length();i++){
                JSONObject j = jsonArray.getJSONObject(i);
                HomeInfo info = new HomeInfo();
                info.set_id(j.optString("_id"));
                info.setCreateAt(j.optString("createdAt"));
                info.setDesc(j.optString("desc"));
                info.setPublishAt(j.optString("publishedAt"));
                info.setSource(j.optString("source"));
                info.setType(j.optString("type"));
                info.setUrl(j.optString("url"));
                info.setUsed(j.optString("used"));
                info.setWho(j.optString("who"));
                list.add(info);
            }
        }catch (JSONException e){

        }
        return list;
    }
    public static String getFail(String json){
        try {
            //转化为json对象
            JSONObject jsonObject = new JSONObject(json);
            //判断error字段是否存在，不存在返回失败信息并结束请求
            if (jsonObject.isNull(ERROR)) {
                return "error key not exists!!";
            }
            //判断后台返回结果，true表示失败，false表示成功，失败则返回错误回调并结束请求
            if (jsonObject.getBoolean(ERROR)) {
                return "request failure!!";
            }
            //判断results字段是否存在，不存在返回时报回调并结束请求
            if (jsonObject.isNull(RESULTS)) {
                return "results key not exists!!";
            }
        }catch (JSONException e){
            return ""+e.getMessage();
        }
        return "fail default";
    }
}
