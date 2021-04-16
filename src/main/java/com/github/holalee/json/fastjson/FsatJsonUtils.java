package com.github.holalee.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;
import java.util.Map;

public class FsatJsonUtils {

    /**
     * java对象序列化json字符串
     * @param obj
     * @param flag true 将对象转换成格式化的json, false 将对象转换成非格式化的json
     * @return
     */
    public static String toJson(Object obj, boolean flag){
        return JSON.toJSONString(obj,flag);
    }

    /**
     * json字符串转list数组
     * @param jsonStr
     * @return
     */
    public static <T> List<T> toList(String jsonStr, TypeReference<List<T>> jsonTypeReference){
        return JSON.parseObject(jsonStr, jsonTypeReference);
    }

    /*******************************************************************************************************************
    /**
     * 功能描述：把JSON数据转换成指定的java对象
     *
     * @param jsonData JSON数据
     * @param clazz    指定的java对象
     * @return 指定的java对象
     */
    public static <T> T getJsonToBean(String jsonData, Class<T> clazz) {
        return JSON.parseObject(jsonData, clazz);
    }

    /**
     * 功能描述：把java对象转换成JSON数据
     *
     * @param object java对象
     * @return JSON数据
     */
    public static String getBeanToJson(Object object) {
        return JSON.toJSONString(object);
    }

    /**
     * 功能描述：把JSON数据转换成指定的java对象列表
     *
     * @param jsonData JSON数据
     * @param clazz    指定的java对象
     * @return List<T>
     */
    public static <T> List<T> getJsonToList(String jsonData, Class<T> clazz) {
        return JSON.parseArray(jsonData, clazz);
    }

    /**
     * 功能描述：把JSON数据转换成较为复杂的List<Map<String, Object>>
     *
     * @param jsonData JSON数据
     * @return List<Map < String, Object>>
     */
    public static List<Map<String, Object>> getJsonToListMap(String jsonData) {
        return JSON.parseObject(jsonData, new TypeReference<List<Map<String, Object>>>() {
        });
    }


    @SuppressWarnings("unchecked")
    public static Map<String, Object> json2Map(String json) {
        return JSON.parseObject(json, Map.class);
    }

    public static String obj2JsonString(Object obj) {
        return JSON.toJSONString(obj);
    }
}
