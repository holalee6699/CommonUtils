package com.github.holalee.common;

import com.google.common.base.CaseFormat;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

//import com.hj.farm.common.qqwry.IPZone;
//import com.hj.farm.common.qqwry.QQWry;

/**
 * 常用工具箱
 *
 * @author gaolingfei
 * @date 2019年01月11日 18:44
 */
public class Tools {

    public static final String UNKNOWN = "unknown";

    /**
     * 判断字符串数组元素是否均不为空
     *
     * @param strs 字符串数组
     * @return 布尔值
     */
    public static boolean isNullOrEmpty(String... strs) {
        boolean result = true;
        if (strs != null) {
            for (String str : strs) {
                result = isNull(str) || isEmpty(str);
                if (!result) {
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 判断对象数组是否均不为空
     *
     * @param objs 对象数组
     * @return 布尔值
     */
    public static boolean isNull(Object... objs) {
        boolean result = false;
        for (Object obj : objs) {
            result = isNull(obj);
            if (result) {
                break;
            }
        }
        return result;
    }

    /**
     * 判断集合是否为null或为空
     *
     * @param collection 集合对象
     * @param <T>        元素类型
     * @return 布尔值
     */
    public static <T> boolean isNullOrEmpty(Collection<T> collection) {
        return isNull(collection) || isEmpty(collection);
    }

    /**
     * 判断Map是否为null或为空
     *
     * @param map Map对象
     * @param <S> key类型
     * @param <T> value类型
     * @return 布尔值
     */
    public static <S, T> boolean isNullOrEmpty(Map<S, T> map) {
        return isNull(map) || isEmpty(map);
    }

    /**
     * 判断对象是否为空
     *
     * @param obj 对象
     * @return 布尔值
     */
    public static boolean isNull(Object obj) {
        return obj == null || "".equals(obj);
    }

    /**
     * 判断对象是否为空
     *
     * @param number 对象
     * @return 布尔值
     */
    public static boolean isNullOrZero(Number number) {
        return number == null || number.doubleValue() == 0;
    }

    /**
     * 判断字符串是否为空或空格
     *
     * @param string 字符串
     * @return 布尔值
     */
    public static boolean isEmpty(String string) {
        return string.trim().length() <= 0;
    }

    /**
     * 判断集合是否为空，不包容任何元素
     *
     * @param collection 集合
     * @param <T>        元素类型
     * @return 布尔值
     */
    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection.size() <= 0;
    }

    /**
     * 判断Map是否空
     *
     * @param map
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S, T> boolean isEmpty(Map<S, T> map) {
        return map.size() <= 0;
    }

    /**
     * UUID获取32位随机字符串，去除'-'
     *
     * @return 32位随机字符串
     */
    public static String getUuidRandom() {
        return UUID.randomUUID().toString().trim().replaceAll("-", "");
    }

    /**
     * 对象转Map
     *
     * @param obj
     * @return
     */
    public static Map<String, Object> object2Map(Object obj) {
        Map<String, Object> map = new HashMap<>();
        if (obj == null) {
            return map;
        }
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    /**
     * map拼接成url,升序
     *
     * @param parameters 参数Map
     * @return url字符串
     */
    public static String mapToUrl(Map<String, Object> parameters) {
        // 未设置参数
        if (Tools.isNullOrEmpty(parameters)) {
            return "";
        }
        StringBuilder url = new StringBuilder("?");
        // 升序排列
        TreeMap<String, Object> treeMap = new TreeMap<String, Object>();
        treeMap.putAll(parameters);

        // 拼接参数
        for (String key : treeMap.keySet()) {
            Object obj = treeMap.get(key);
            // 拼接不为空的参数
            if (!Tools.isNull(obj)) {
                url.append(key).append("=").append(obj).append("&");
            }
        }
        return url.substring(0, url.lastIndexOf("&"));
    }

    /**
     * 获取工程路径
     *
     * @return 本地工程路径
     */
    public static String getProjectPath() {
        return System.getProperty("user.dir");
    }

    /**
     * 获取IP地址
     * <p>
     * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }


//    public static String getIpArea(HttpServletRequest request) {
//        QQWry qqwry = null;
//        try {
//            qqwry = new QQWry();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        assert qqwry != null;
//        String ipAddr = getIpAddr(request);
//        IPZone ipzone = qqwry.findIP(ipAddr);
//        if ("127.0.0.1".equals(ipAddr)) {
//            return ipzone.getMainInfo();
//        }
//        return ipzone.getMainInfo() + " " + ipzone.getSubInfo();
//    }


    /**
     * 首字母大写
     */
    public static String captureName(String name) {
        char[] cs = name.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }


    /**
     * 实体类转Map
     */
    public static Map<String, Object> convertEntityToMap(Object model)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Field[] fields = model.getClass().getDeclaredFields();
        Map<String, Object> map = new HashMap<>(fields.length);
        for (Field field : fields) {
            String getter =
                    "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
            map.put(field.getName(), model.getClass().getMethod(getter).invoke(model));
        }
        return map;
    }


    /**
     * 下划线转驼峰
     *
     * @param underLineCode p.s. item_name
     * @return p.s. itemName
     */
    public static String convertUnderLine2Camel(String underLineCode) {
        underLineCode = underLineCode.replaceAll("_", "-");
        underLineCode = CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, underLineCode);
        return underLineCode;
    }


}
