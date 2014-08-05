package com.redhat.xmlrpc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <li>标注此方法会作为xmlrpc server的响应方法</li>
 *
 * @author yangpeng 2008-8-1 下午03:33:12
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface XmlRpcMethod {

    String value() default "";
}
