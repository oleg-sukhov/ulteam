package ua.vn.os.ulteam.model.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.Arrays;
import java.util.Locale;

/**
 * @author os
 */
@Aspect
public class LogAspect {
    private Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    private MessageSource messageSource;

    @Around("execution(* ua.vn.os.ulteam.model.dao.hibernate.*HibernateDao.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object returnValue = joinPoint.proceed();
        long duration = System.currentTimeMillis() - start;
        Object[] args = new Object[]{joinPoint.toShortString(), Arrays.toString(joinPoint.getArgs()), duration};
        String message = messageSource.getMessage("ua.vn.os.ulteam.logging.durationDao.message", args, Locale.getDefault());
        logger.info(message);
        return returnValue;

    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
