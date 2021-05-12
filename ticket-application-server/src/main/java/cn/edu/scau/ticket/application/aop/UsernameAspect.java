package cn.edu.scau.ticket.application.aop;

import cn.edu.scau.ticket.application.beans.User;
import cn.edu.scau.ticket.application.beans.result.ResultEntity;
import cn.edu.scau.ticket.application.beans.result.ResultStatus;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RBloomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 切面类
 * @author: Tensh
 * @createDate: 2021/5/11
 */
@Aspect
@Component
public class UsernameAspect {

    @Autowired
    private RBloomFilter<String> usernameBloomFilter;

    @Around("execution(* cn.edu.scau.ticket.application.controller.UserController.register(..))")
    public Object checkUsername(ProceedingJoinPoint joinPoint) {
        User user = (User) joinPoint.getArgs()[0];
        boolean contains = usernameBloomFilter.contains(user.getUsername());
        if (contains) {
            return ResultEntity
                    .getResultEntity(ResultStatus.FAILED)
                    .addInfo("username","用户名已经存在");
        }
        Object proceed = null;
        //执行方法
        try {
            //正常方法执行结果
            Object[] args = joinPoint.getArgs();
            proceed = joinPoint.proceed(args);
            usernameBloomFilter.add(user.getUsername());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return proceed;
    }
}
