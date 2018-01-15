package com.bootcrm.common.util;

import com.bootcrm.common.exception.UnloginException;
import org.slf4j.MDC;

import java.util.Locale;

//使用ThreadLocal去掉国际化 记录用户信息
public class UserUtil {
    private final static ThreadLocal<String> tlUser = new ThreadLocal<String>();
    private final static ThreadLocal<Locale> tlLocale = new ThreadLocal<Locale>(){
        protected Locale initialValue(){
            //语言的默认值
            return Locale.CHINESE;
        }
    };
    public static final String KEY_LANG = "lang";

    public static final String KEY_USER = "user";

    public static void setUser(String userName) {
        tlUser.set(userName);

        // 把用户信息放到log4j
        MDC.put(KEY_USER, userName);
    }

    /**
     * 如果没有登录，返回null
     *（可以不登陆访问）
     */
    public static String getUserIfLogin() {
        return tlUser.get();
    }

    /**
     * 如果没有登录会抛出异常
     * (必须登录访问)
     */
    public static String getUser() {
        String user = tlUser.get();
        if (user == null) {
            throw new UnloginException ();
        }
        return user;
    }

    public static void setLocale(String locale) {
        setLocale(new Locale(locale));
    }

    public static void setLocale(Locale locale) {
        tlLocale.set(locale);
    }

    public static Locale getLocale() {
        return tlLocale.get();
    }

    public static void clearAllUserInfo() {
        tlUser.remove();
        tlLocale.remove();

        MDC.remove(KEY_USER);
    }



}
