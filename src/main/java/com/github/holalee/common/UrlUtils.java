//package com.github.holalee.common;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.hj.farm.common.exception.ErrorCodeException;
//import org.apache.commons.compress.utils.Lists;
//import org.apache.commons.lang3.time.DateUtils;
//import org.springframework.data.domain.Sort;
//
//import java.text.ParseException;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//public class UrlUtils {
//
//    /**
//     * 获取URL参数
//     */
//    public static String getParam(Map<String, String[]> parameterMap, String fieldName) {
//        String[] strings = parameterMap.get(fieldName);
//        if (!Tools.isNullOrEmpty(strings)) {
//            return strings[0];
//        }
//        return "";
//    }
//
//    public static String getParam(Map<String, String[]> parameterMap, String fieldName, String defaultValue) {
//        String value = getParam(parameterMap, fieldName);
//        return Tools.isNullOrEmpty(value) ? defaultValue : value;
//    }
//
//    /**
//     * 获取URL参数
//     */
//    public static Integer getParamInt(Map<String, String[]> parameterMap, String fieldName) {
//        String[] strings = parameterMap.get(fieldName);
//        if (!Tools.isNullOrEmpty(strings)) {
//            String string = strings[0];
//            return Integer.parseInt(string);
//        }
//        return null;
//    }
//
//    public static Boolean getParamBoolean(Map<String, String[]> parameterMap, String fieldName) {
//        String[] strings = parameterMap.get(fieldName);
//        if (!Tools.isNullOrEmpty(strings)) {
//            String string = strings[0];
//            return Boolean.parseBoolean(string);
//        }
//        return false;
//    }
//
//    /**
//     * 获取URL参数
//     */
//    public static Date getParamDate(Map<String, String[]> parameterMap, String fieldName) {
//        String[] strings = parameterMap.get(fieldName);
//        if (!Tools.isNullOrEmpty(strings)) {
//            String string = strings[0];
//            try {
//                return DateUtils.parseDate(string, "yyyy-MM-dd");
//            } catch (ParseException e) {
//                e.printStackTrace();
//                throw new ErrorCodeException("日期类型转换错误");
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 获取URL参数
//     */
//    public static Date getParamDateTime(Map<String, String[]> parameterMap, String fieldName) {
//        String[] strings = parameterMap.get(fieldName);
//        if (!Tools.isNullOrEmpty(strings)) {
//            String string = strings[0];
//            try {
//                return DateUtils.parseDate(string, "yyyy-MM-dd HH:mm:ss");
//            } catch (ParseException e) {
//                e.printStackTrace();
//                throw new ErrorCodeException("日期时间类型转换错误");
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 获取URL参数
//     */
//    public static Date getParamTime(Map<String, String[]> parameterMap, String fieldName) {
//        String[] strings = parameterMap.get(fieldName);
//        if (!Tools.isNullOrEmpty(strings)) {
//            String string = strings[0];
//            try {
//                return DateUtils.parseDate(string, "HH:mm:ss");
//            } catch (ParseException e) {
//                e.printStackTrace();
//                throw new ErrorCodeException("时间类型转换错误");
//            }
//        }
//        return null;
//    }
//
//
//    /**
//     * 获取排序
//     *
//     * @param parameterMap
//     * @return
//     */
//    public static List<Sort.Order> getOrders(Map<String, String[]> parameterMap) {
//        String sort = UrlUtils.getParam(parameterMap, "sort");
//        List<Sort.Order> orders = Lists.newArrayList();
//        JSONArray jsonArray = JSONArray.parseArray(sort);
//        if (!Tools.isNullOrEmpty(jsonArray)) {
//            for (Object order : jsonArray) {
//                JSONObject s1 = (JSONObject) order;
//                String direction = s1.getString("sort");
//                String field = s1.getString("field");
//                if ("desc".equals(direction)) {
//                    orders.add(new Sort.Order(Sort.Direction.DESC, field));
//                } else if ("asc".equals(direction)) {
//                    orders.add(new Sort.Order(Sort.Direction.ASC, field));
//                }
//            }
//        }
//        return orders;
//    }
//}
