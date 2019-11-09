package com.hzht.course01.handler;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 黄昭鸿
 * 定义切面和处理方法
 * 注解@Aspect用来描述一个切面类，定义切面类的时候需要打上这个注解。
 * 注解@Component 将该类交给 Spring 来管理
 * 注解@Pointcut ，用来定义一个切面（切入点）
 * @create 2019-08-01 下午2:19
 */
@Aspect
@Component
public class LogAspectHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * doBefore方法在切面切入目标方法之前执行
     * JointPoint 对象很有用，可以用它来获取一个签名，
     * 利用签名可以获取请求的包名、方法名，包括参数（通过 joinPoint.getArgs() 获取）等
     *
     * @param joinPoint
     */
    @Before("com.hzht.course01.handler.LogAspectHandler.pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        logger.info("====doBefore方法进入了====");

        /* 获取签名 */
        Signature signature = joinPoint.getSignature();
        /* 获取切入的包名 */
        String declaringTypeName = signature.getDeclaringTypeName();
        /* 获取即将执行的方法名 */
        String funcName = signature.getName();
        logger.info("即将执行方法为: {}，属于{}包", funcName, declaringTypeName);

        /* 也可以用来记录一些信息，比如获取请求的 URL 和 IP */
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes != null ? attributes.getRequest() : null;
        /* 获取请求 URL */
        String url = request != null ? request.getRequestURL().toString() : null;
        /* 获取请求 IP */
        String ip = request.getRemoteAddr();
        logger.info("用户请求的url为：{}，ip地址为：{}", url, ip);
    }

    /**
     * 定义一个切面，拦截com.hzht.course01.controller包和子包下的所有方法
     * 定义需要拦截的东西，两个常用的表达式：execution()和annotation()。
     * <p>
     * execution() 为表达式主体。
     * 第一个 * 号的位置：表示返回值类型，* 表示所有类型。
     * 包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包
     * 第二个 * 号的位置：表示类名，* 表示所有类。
     * *(..)：这个星号表示方法名，* 表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数。
     * <p>
     * annotation() 方式是针对某个注解来定义切面
     * 例如@Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
     * public void annotationCut() {}
     */
    @Pointcut("execution(* com.hzht.course01.controller..*.*(..))")
    public void pointCut() {
    }

    /**
     * 注解@After 指定的方法在切面切入目标方法之后执行，也可以做一些完成某方法之后的 Log 处理
     *
     * @param joinPoint
     */
    @After("pointCut()")
    public void doAfter(JoinPoint joinPoint) {
        logger.info("====doAfter方法进入了====");
        Signature signature = joinPoint.getSignature();
        String method = signature.getName();
        logger.info("方法{}已经执行完", method);
    }

    /**
     * 注解@AfterReturning 可以用来捕获切入方法执行完之后的返回值，对返回值进行业务逻辑上的增强处理
     *
     * @param joinPoint
     * @param result
     */
    @AfterReturning(pointcut = "pointCut()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("====AfterReturning方法进入了====");
        /*该方法中的第二个入参就是被切方法的返回值*/
        Signature signature = joinPoint.getSignature();
        String classMethod = signature.getName();
        logger.info("方法{}执行完毕，返回参数为：{}", classMethod, result);
        /*实际项目中可以根据业务做具体的返回值增强*/
        logger.info("对返回参数进行业务上的增强：{}", result + "增强版");
    }

    /**
     * 当被切方法执行过程中抛出异常时，会进入 @AfterThrowing 注解的方法中执行，在该方法中可以做一些异常的处理逻辑。
     * throwing 属性的值必须要和参数一致，否则会报错。
     * 该方法中的第二个入参即为抛出的异常。
     *
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(pointcut = "pointCut()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        logger.info("====AfterThrowing方法进入了====");
        Signature signature = joinPoint.getSignature();
        String method = signature.getName();
        /*处理异常的逻辑*/
        logger.info("执行方法{}出错，异常为：{}", method, ex);
    }
}
