package com.redhat.xmlrpc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * <li>xml rpc service 注解</li>
 *
 * @author yangpeng 2008-8-1 下午03:37:34
 */
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface XmlRpcService {

    /**
     * value为空则xml rpc service的名称默认使用sping bean的id。否则使用value
     */
    String value() default "";
    /**
     * 是否使用方法注解
     *
     * @return false:默认服务组件中的所有公共方法都作为xml rpc的服务方法 <br>
     *         true:在服务组件
     */
    boolean useMethodAnnotation() default false;
}
