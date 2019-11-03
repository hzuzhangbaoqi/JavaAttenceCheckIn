package com.attencecheckin.javabackend.util;

import com.alibaba.fastjson.JSONObject;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: JavaAttenceCheckIn
 * @description:
 * @author: zxf
 * @create: 2019-11-03 15:37
 **/
public class DistanceUtils {

    // 地球平均半径
//    private static final double EARTH_RADIUS = 6378137;
    private static final double EARTH_RADIUS = 6371393;

    // 把经纬度转为度（°）
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }



    /**
     * 获取当前用户一定距离以内的经纬度值
     * @param raidus 单位米
     * 最小经度 minLng
     * 最小纬度 maxLat
     * 最大经度 maxLng
     * 最大纬度 minLat
     */
    public static Map<String,String> getAround(String latStr, String lngStr, String raidus) {
        Map<String,String> map = new HashMap<String,String>();

        Double latitude = Double.parseDouble(latStr);// 传值给经度
        Double longitude = Double.parseDouble(lngStr);// 传值给纬度

        Double degree = (24901 * 1609) / 360.0; // 获取每度
        double raidusMile = Double.parseDouble(raidus);

        Double mpdLng = Double.parseDouble((degree * Math.cos(latitude * (Math.PI / 180))+"").replace("-", ""));
        Double dpmLng = 1 / mpdLng;
        Double radiusLng = dpmLng * raidusMile;
        //获取最小经度
        Double minLat = longitude - radiusLng;
        // 获取最大经度
        Double maxLat = longitude + radiusLng;

        Double dpmLat = 1 / degree;
        Double radiusLat = dpmLat * raidusMile;
        // 获取最小纬度
        Double minLng = latitude - radiusLat;
        // 获取最大纬度
        Double maxLng = latitude + radiusLat;

        map.put("minLat", minLat+"");
        map.put("maxLat", maxLat+"");
        map.put("minLng", minLng+"");
        map.put("maxLng", maxLng+"");

        return map;
    }
    /**
     * 根据经纬度和半径计算经纬度范围
     *
     * @param raidus 单位米
     * @return minLat, minLng, maxLat, maxLng
     */
    public static Map<String,String> getAround(double lon, double lat, int raidus) {
        Map<String,String> map = new HashMap<String,String>();
        Double longitude = lon;
        Double latitude = lat;

        Double degree = (24901 * 1609) / 360.0;
        double raidusMile = raidus;

        Double dpmLat = 1 / degree;
        Double radiusLat = dpmLat * raidusMile;
        Double minLat = latitude - radiusLat;
        Double maxLat = latitude + radiusLat;

        Double mpdLng = degree * Math.cos(latitude * (Math.PI/ 180));
        Double dpmLng = 1 / mpdLng;
        Double radiusLng = dpmLng * raidusMile;
        Double minLng = longitude - radiusLng;
        Double maxLng = longitude + radiusLng;
        map.put("minLat", minLat+"");
        map.put("maxLat", maxLat+"");
        map.put("minLng", minLng+"");
        map.put("maxLng", maxLng+"");
        return map;
    }

    public static void main(String[] args) throws ParseException {
//    	System.out.println(Math.PI);
        double distance1 = getDistance(121.406725, 31.197103,121.371408,31.217456);
        System.out.println("Distance is: " + distance1 + " m");

//        System.out.println(getAround("121.462253", "31.223574", "1000"));
//        System.out.println(getAround(121.462253, 31.223574, 1000));
//        System.out.println("A".compareTo("X"));

        String ss ="{\"latitude\":23.12463,\"longitude\":113.36199,\"speed\":-1,\"accuracy\":65,\"verticalAccuracy\":65,\"horizontalAccuracy\":65,\"errMsg\":\"getLocation:ok\"}";

    }
    public static double getDistance(String coordinates1, String coordinates2){
        JSONObject coordinates1Json = JSONObject.parseObject(coordinates1);
        JSONObject coordinates2Json = JSONObject.parseObject(coordinates1);
        return getDistance(coordinates1Json.getDouble("longitude"),coordinates1Json.getDouble("latitude")
        ,coordinates2Json.getDouble("longitude"),coordinates2Json.getDouble("latitude"));
    }
    /**
     * 根据两点间经纬度坐标（double值），计算两点间距离，单位：米
     * @author ershuai
     * @param lng1
     * @param lat1
     * @param lng2
     * @param lat2
     * @return
     */
    public static double getDistance(double lng1, double lat1, double lng2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        distance = distance * EARTH_RADIUS;
        distance = distance / 1000;
        DecimalFormat df = new DecimalFormat("#.00");
        distance = Double.parseDouble(df.format(distance));
        return distance*1000;
    }
}
