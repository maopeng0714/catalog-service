package com.redhat.xmlrpc.server;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.XmlRpcHandler;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;

import com.redhat.xmlrpc.annotation.XmlRpcMethod;
import com.redhat.xmlrpc.annotation.XmlRpcService;

/**
 * <li>注册spring bean的HandlerMapping</li>
 *
 * @author yangpeng 2008-8-1 上午10:42:21
 */
public class SpringPropertyHandlerMapping extends PropertyHandlerMapping {

    public void addHandler(boolean detectServersInAncestorContexts,
            final ApplicationContext context) throws XmlRpcException {
        Assert.notNull(context, "context must not be null!");
        final String[] beanNames = detectServersInAncestorContexts ? BeanFactoryUtils
                .beanNamesForTypeIncludingAncestors(context, Object.class)
                : context.getBeanNamesForType(Object.class);
                for (final String beanName : beanNames)
                    registerPublicMethods(beanName, context);
    }
    @SuppressWarnings( { "unchecked" })
    protected void registerPublicMethods(String beanName,
            final ApplicationContext context) throws XmlRpcException {
        Class<?> serviceType = context.getType(beanName);
        XmlRpcService service = AnnotationUtils.findAnnotation(serviceType,
                XmlRpcService.class);
        if (service == null
                && context instanceof ConfigurableApplicationContext
                && context.containsBeanDefinition(beanName)) {
            final ConfigurableApplicationContext cac = (ConfigurableApplicationContext) context;
            final BeanDefinition bd = cac.getBeanFactory().getMergedBeanDefinition(
                    beanName);
            if (bd instanceof AbstractBeanDefinition) {
                final AbstractBeanDefinition abd = (AbstractBeanDefinition) bd;
                if (abd.hasBeanClass()) {
                    final Class<?> beanClass = abd.getBeanClass();
                    serviceType = beanClass;// 得到被代理对象
                    service = AnnotationUtils.findAnnotation(beanClass,
                            XmlRpcService.class);
                }
            }
        }
        if (service != null) {
            final Map<String, Method[]> map = new HashMap<String, Method[]>();
            final Method[] methods = serviceType.getMethods();
            for (final Method method : methods) {
                if (!isHandlerMethod(service.useMethodAnnotation(), method))
                    continue;
                final String serviceName = StringUtils.isEmpty(service.value()) ? beanName
                        : service.value();
                final String name = serviceName + "." + method.getName();
                Method[] mArray;
                final Method[] oldMArray = map.get(name);
                if (oldMArray == null)
                    mArray = new Method[] { method };
                else {
                    mArray = new Method[oldMArray.length + 1];
                    System.arraycopy(oldMArray, 0, mArray, 0, oldMArray.length);
                    mArray[oldMArray.length] = method;
                }
                map.put(name, mArray);
            }
            for (final Iterator<?> iter = map.entrySet().iterator(); iter.hasNext();) {
                final Map.Entry entry = (Map.Entry) iter.next();
                final String name = (String) entry.getKey();
                final Method[] mArray = (Method[]) entry.getValue();
                handlerMap.put(name, newXmlRpcHandler(
                        context.getBean(beanName), mArray));
            }
        }
    }
    protected XmlRpcHandler newXmlRpcHandler(final Object bean,
            final Method[] pMethods) throws XmlRpcException {
        final String[][] sig = getSignature(pMethods);
        final String help = getMethodHelp(bean.getClass(), pMethods);
        if (sig == null || help == null)
            return new SpringXmlRpcHandler(this, getTypeConverterFactory(),
                    bean, pMethods);
        return new SpringReflectiveXmlRpcMetaDataHandler(this,
                getTypeConverterFactory(), bean, pMethods, sig, help);
    }
    protected boolean isHandlerMethod(boolean useMethodAnnotation, Method method) {
        if (useMethodAnnotation) {
            final XmlRpcMethod xmlRpcMethod = AnnotationUtils.getAnnotation(method,
                    XmlRpcMethod.class);
            if (null == xmlRpcMethod)
                return false;
        }
        return super.isHandlerMethod(method);
    }
}
