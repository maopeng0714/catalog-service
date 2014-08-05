package com.redhat.xmlrpc.server;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.common.TypeConverterFactory;
import org.apache.xmlrpc.server.AbstractReflectiveHandlerMapping;
import org.apache.xmlrpc.server.RequestProcessorFactoryFactory;
import org.apache.xmlrpc.server.XmlRpcHandlerMapping;
import org.apache.xmlrpc.util.ReflectionUtil;
import org.apache.xmlrpc.webserver.XmlRpcServletServer;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.ApplicationObjectSupport;

/**
 * <li>xmlrpc server 工厂</li>
 *
 * @author yangpeng 2008-8-1 上午09:18:34
 */
public class XmlRpcServletServerFactoryBean extends ApplicationObjectSupport implements
        FactoryBean<Object>, InitializingBean {

    private XmlRpcServletServer server;
    /** XmlRpcServletServer的属性集合 */
    private Map<String, String> serverProperties;
    /** 是否在父BeanFactory中寻找xml rpc services */
    private boolean detectServersInAncestorContexts = false;
    private AbstractReflectiveHandlerMapping.AuthenticationHandler authenticationHandler;
    private RequestProcessorFactoryFactory requestProcessorFactoryFactory;
    private TypeConverterFactory typeConverterFactory;
    protected Log log = LogFactory.getLog(XmlRpcServletServerFactoryBean.class);

    @Override
    public Object getObject() throws Exception {
        return server;
    }

    @Override
    public Class<?> getObjectType() {
        return XmlRpcServletServer.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        server = new XmlRpcServletServer();
        initServerProperties();
        server.setHandlerMapping(newXmlRpcHandlerMapping());
    }

    protected void initServerProperties() {
        if (null != serverProperties) {
            final Set<String> keys = serverProperties.keySet();
            for (final String key : keys) {
                final String value = serverProperties.get(key);
                try {
                    if (!ReflectionUtil.setProperty(this, key, value)
                            && !ReflectionUtil.setProperty(server, key, value)
                            && !ReflectionUtil.setProperty(server.getConfig(), key, value)) {
                        throw new BeanInitializationException("key:" + key + ";value:" + value
                                + " is wrong property!");
                    }
                } catch (final IllegalAccessException e) {
                    log.error(e);
                    throw new BeanInitializationException("key:" + key + ";value:" + value
                            + " is wrong property!");
                } catch (final InvocationTargetException e) {
                    log.error(e);
                    throw new BeanInitializationException("key:" + key + ";value:" + value
                            + " is wrong property!");
                }
            }
        }
    }

    protected XmlRpcHandlerMapping newXmlRpcHandlerMapping() throws XmlRpcException {
        final SpringPropertyHandlerMapping mapping = new SpringPropertyHandlerMapping();
        mapping.setAuthenticationHandler(authenticationHandler);
        if (requestProcessorFactoryFactory != null) {
            mapping.setRequestProcessorFactoryFactory(requestProcessorFactoryFactory);
        }
        if (typeConverterFactory != null) {
            mapping.setTypeConverterFactory(typeConverterFactory);
        } else {
            mapping.setTypeConverterFactory(server.getTypeConverterFactory());
        }
        mapping.setVoidMethodEnabled(server.getConfig().isEnabledForExtensions());
        mapping.addHandler(detectServersInAncestorContexts, getApplicationContext());
        return mapping;
    }

    public Map<String, String> getServerProperties() {
        return serverProperties;
    }

    public void setServerProperties(Map<String, String> serverProperties) {
        this.serverProperties = serverProperties;
    }

    public AbstractReflectiveHandlerMapping.AuthenticationHandler getAuthenticationHandler() {
        return authenticationHandler;
    }

    public void setAuthenticationHandler(
            AbstractReflectiveHandlerMapping.AuthenticationHandler authenticationHandler) {
        this.authenticationHandler = authenticationHandler;
    }

    public RequestProcessorFactoryFactory getRequestProcessorFactoryFactory() {
        return requestProcessorFactoryFactory;
    }

    public void setRequestProcessorFactoryFactory(
            RequestProcessorFactoryFactory requestProcessorFactoryFactory) {
        this.requestProcessorFactoryFactory = requestProcessorFactoryFactory;
    }

    public TypeConverterFactory getTypeConverterFactory() {
        return typeConverterFactory;
    }

    public void setTypeConverterFactory(TypeConverterFactory typeConverterFactory) {
        this.typeConverterFactory = typeConverterFactory;
    }

    public boolean isDetectServersInAncestorContexts() {
        return detectServersInAncestorContexts;
    }

    public void setDetectServersInAncestorContexts(boolean detectServersInAncestorContexts) {
        this.detectServersInAncestorContexts = detectServersInAncestorContexts;
    }
}
