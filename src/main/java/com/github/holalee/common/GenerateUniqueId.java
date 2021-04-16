package com.github.holalee.common;

import java.util.UUID;

public class GenerateUniqueId {

    public static String getIds(){
        int random = (int) (Math.random()*9+1);
        String valueOf = String.valueOf(random);
        //生成uuid的hashCode值
        int hashCodes = UUID.randomUUID().toString().hashCode();
//    //可能为负数
//        if(hashCode<0){
//        hashCode = -hashCode;
//    }
        String value = valueOf + String.format("%015d", hashCodes);
        return  value;
    }


}
