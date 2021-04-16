//package com.github.holalee.common;
//
//import com.hj.farm.common.constant.SessionConstant;
//import com.hj.farm.domain.SessionData;
//import com.hj.farm.domain.ShopPara;
//import org.springframework.web.servlet.i18n.SessionLocaleResolver;
//
//import javax.servlet.http.HttpSession;
//import java.util.Locale;
//import java.util.Optional;
//
//public class SessionUtils {
//
//    public static Long getShopId() {
//        return getSessionData().getShopId();
//    }
//
//    public static String getManageBranchIds() {
//        String branchIds = getSessionData().getBranchIds();
//        if (Tools.isNullOrEmpty(branchIds)) {
//            branchIds = "0";
//        }
//        return branchIds;
//    }
//
//    public static Long getUserId() {
//        return getSessionData().getUserId();
//    }
//
//    public static String getUserCode() {
//        return getSessionData().getUserCode();
//    }
//
//    public static String getLoginType(){
//        return getSessionData().getLoginType();
//    }
//
//    public static String getUserName() {
//        return getSessionData().getUserName();
//    }
//
//    public static String getShopParaValue(Integer paraNo) {
//        return getShopParaValue(paraNo, "paraValue1");
//    }
//
//    public static String getShopParaValue(Integer paraNo, String valueCode) {
//        String result = null;
//        Optional<ShopPara> shopParaOptional = getSessionData().getShopParas()
//                .stream()
//                .filter(shopPara -> paraNo.equals(shopPara.getParaNo()))
//                .findFirst();
//        if (shopParaOptional.isPresent()) {
//            result = (String) ReflectionUtils.getFieldVal(valueCode, shopParaOptional.get());
//        }
//
//        return result;
//    }
//
//    public static SessionData getSessionData() {
//        HttpSession session = HttpContextUtils.getHttpServletRequest().getSession();
//        Object attribute = session.getAttribute(SessionConstant.SESSION_LOGIN_KEY);
//        return attribute == null ? new SessionData() : (SessionData) attribute;
//    }
//
//    public static void updateManageBranchIds(String branchIds) {
//        HttpSession session = HttpContextUtils.getHttpServletRequest().getSession();
//        SessionData sessionData = getSessionData();
//        sessionData.setBranchIds(branchIds);
//        session.setAttribute(SessionConstant.SESSION_LOGIN_KEY, sessionData);
//    }
//
//    public static Locale getLanguageType() {
//        HttpSession session = HttpContextUtils.getHttpServletRequest().getSession();
//        Object attribute = session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
//        return attribute == null ? Locale.SIMPLIFIED_CHINESE : (Locale) attribute;
//    }
//
//}
