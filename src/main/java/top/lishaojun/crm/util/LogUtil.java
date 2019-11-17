package top.lishaojun.crm.util;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import top.lishaojun.crm.domain.Employee;
import top.lishaojun.crm.domain.SystemLog;
import top.lishaojun.crm.service.ISystemLogService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class LogUtil {

    @Autowired
    ISystemLogService logService;

    public void writeLog(JoinPoint joinPoint) {
        try {
            if (joinPoint.getTarget() instanceof ISystemLogService){
                return;
            }
            SystemLog systemLog = new SystemLog();
            systemLog.setOptime(new Date());
            HttpServletRequest request = UserContext.getLocal();
            Employee e = (Employee) request.getSession().getAttribute(UserContext.USER_IN_SESSION);

            systemLog.setOpuser(e);
            systemLog.setOpip(request.getRemoteAddr());

            Object target = joinPoint.getTarget();
            systemLog.setFunction(target.getClass() + ":" + joinPoint.getSignature());

            ObjectMapper mapper = new ObjectMapper();
            // 空的参数就不要转换为json字符串
            mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            String value = mapper.writeValueAsString(joinPoint.getArgs());
            systemLog.setParams(value);

            logService.save(systemLog);

        } catch (JsonProcessingException e1) {
            e1.printStackTrace();
        }

    }
}
