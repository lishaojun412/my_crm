package top.lishaojun.crm.util;

import javax.servlet.http.HttpServletRequest;

public class UserContext {

    public static final String USER_IN_SESSION = "USER_IN_SESSION";
    public static final String MENU_IN_SESSION = "MENU_IN_SESSION";

    private static ThreadLocal<HttpServletRequest> local = new ThreadLocal<>();

    public static HttpServletRequest getLocal() {
        return local.get();
    }

    public static void setLocal(HttpServletRequest httpServletRequest) {
        local.set(httpServletRequest);
    }

    public static void removeLocal(){
        local.remove();
    }


}
