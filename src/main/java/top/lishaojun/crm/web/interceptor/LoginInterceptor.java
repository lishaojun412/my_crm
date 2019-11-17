package top.lishaojun.crm.web.interceptor;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.lishaojun.crm.domain.Employee;
import top.lishaojun.crm.domain.Permission;
import top.lishaojun.crm.util.PermissionUtils;
import top.lishaojun.crm.util.UserContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        UserContext.setLocal(httpServletRequest);
        Employee employee = (Employee) httpServletRequest.getSession().getAttribute(UserContext.USER_IN_SESSION);
        if (employee == null) {
            httpServletResponse.sendRedirect("/login.jsp");
            return false;
        }

        if (o instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) o;

            String name = handlerMethod.getBean().getClass().getName();
            String methodName = handlerMethod.getMethod().getName();

            String function = name + ":" + methodName;


            if (!PermissionUtils.checkPermission(function)) {
                ResponseBody methodAnnotation = handlerMethod.getMethodAnnotation(ResponseBody.class);
                if (methodAnnotation != null) {
                    httpServletResponse.sendRedirect("nopermission.json");
                } else {
                    httpServletResponse.sendRedirect("nopermission.jsp");
                }
                return false;
            }

        }

        return true;


    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
