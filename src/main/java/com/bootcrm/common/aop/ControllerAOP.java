package com.bootcrm.common.aop;


import com.bootcrm.common.bean.ResultBean;
import com.bootcrm.common.exception.CheckException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 处理和包装异常
 *
 * 主要就是打印日志和捕获异常，异常要区分已知异常和未知异常，
 * 其中未知的异常是我们重点关注的，可以做一些邮件通知啥的，已知异常可以再细分一下，可以不同的异常返回不同的返回码
 */
public class ControllerAOP {
    private static final Logger logger = LoggerFactory.getLogger(ControllerAOP.class);

    public Object handlerControllerMethod(ProceedingJoinPoint pjp){
        long startTime = System.currentTimeMillis();

        ResultBean<?> result;

        try{
            result = (ResultBean<?>)pjp.proceed();
            logger.info(pjp.getSignature() + "use time:" + (System.currentTimeMillis() - startTime));     //无异常打印日志
        } catch (Throwable throwable) {
            result =  handlerException(pjp,throwable);
        }

        return result;
    }

    private ResultBean<?> handlerException(ProceedingJoinPoint pjp,Throwable e){

        ResultBean<?> result = new ResultBean();
        //已知异常
        if(e instanceof CheckException){
            result.setMsg(e.getLocalizedMessage());
            result.setCode(ResultBean.FAIL);
        }else{
            logger.error(pjp.getSignature()+"error",e);
            //TODO 未知的异常，需要注意，可以发送邮件通知等
            result.setMsg(e.toString());
            result.setCode(ResultBean.FAIL);
        }

        return result;
    }
}
