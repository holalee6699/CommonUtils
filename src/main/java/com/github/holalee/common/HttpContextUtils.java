package com.github.holalee.common;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 *         <dependency>
 *             <groupId>org.springframework.boot</groupId>
 *             <artifactId>spring-boot-starter-web</artifactId>
 *             <!-- 移除嵌入式tomcat插件 -->
 *             <exclusions>
 *                 <exclusion>
 *                     <groupId>org.springframework.boot</groupId>
 *                     <artifactId>spring-boot-starter-tomcat</artifactId>
 *                 </exclusion>
 *             </exclusions>
 *         </dependency>
 */
public class HttpContextUtils {

  public static HttpServletRequest getHttpServletRequest() {
    return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
  }
}