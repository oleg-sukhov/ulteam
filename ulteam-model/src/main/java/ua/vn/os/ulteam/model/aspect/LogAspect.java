package ua.vn.os.ulteam.model.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.Arrays;

/**
 * Created by root on 13.08.14.
 */
@Aspect
public class LogAspect {
    private Logger logger = LoggerFactory.getLogger(LogAspect.class);

    //@Autowired
    //private MessageSource messageSource;

    @Around("execution(* ua.vn.os.ulteam.model.dao.hibernate.*HibernateDao.*(..))")
    public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        joinPoint.proceed();
        long duration = System.currentTimeMillis() - start;
        logger.info("Calling method " + joinPoint.getSignature().getName() + " with args: " + Arrays.toString(joinPoint.getArgs()) + " takes " + duration);

    }


}
