package com.redhat.webapp.xmlrpc;

import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcLiteHttpTransportFactory;

/**
 * <li>xml rpc�ͻ���</li>
 *
 * @author yangpeng 2008-8-11 ����04:27:54
 */
public class XmlRpcClientWrapperImpl implements XmlRpcClientWrapper {
    protected Log log = LogFactory.getLog(XmlRpcClientWrapperImpl.class);
    /** Զ�̷����URL��ַ��Ĭ��http://localhost/xmlrpc */
    private String url = "http://localhost:8080/xmlrpc";
    /** �������ӳ��Դ���Ĭ��3�� */
    private int maxTry = 3;
    /** �������ӳ�ʱ��ʱ������λ�����룩��Ĭ��30000ms */
    private int connectionTimeout = 30000;
    private XmlRpcClient client;
    /** �Ƿ�֧����չ��Ĭ�ϣ�true */
    private boolean enabledForExtensions = true;
    /** Ĭ�ϣ�false */
    private boolean enabledForExceptions = false;
    /** Ĭ�ϣ�false */
    private boolean contentLengthOptional = false;
    /** �ַ���룬Ĭ�ϣ�GBK */
    private String basicEncoding = "GBK";
    /** �������� */
    private String basicPassword;
    /** ���������û��� */
    private String basicUserName;

    @PostConstruct
    public void init() {
        final XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        try {
            config.setServerURL(new URL(url));
            config.setEnabledForExtensions(enabledForExtensions);
            config.setEnabledForExceptions(enabledForExceptions);
            config.setConnectionTimeout(connectionTimeout);
            config.setContentLengthOptional(contentLengthOptional);
            config.setBasicEncoding(basicEncoding);
            config.setBasicPassword(basicPassword);
            config.setBasicUserName(basicUserName);
            client = new XmlRpcClient();
            client.setConfig(config);
            client.setTransportFactory(new XmlRpcLiteHttpTransportFactory(client));
        } catch (final MalformedURLException e) {
            log.error(e);
            throw new IllegalArgumentException("the url:" + url
                    + " could not determine the server!");
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.focustech.extend.spring.xmlrpc.client.XmlRpcClientWrapper#execute(java.lang.String,
     * java.lang.Object[])
     */
    @Override
    public Object execute(final String serviceName, final Object[] parameters) {
        Object mOutput = null;
        try {
            boolean run = false;
            Throwable runE = null;
            for (int i = 0; i < maxTry; i++) {
                try {
                    mOutput = client.execute(serviceName, parameters);
                    run = true;
                    break;
                } catch (final Throwable e) {
                    e.printStackTrace();
                    runE = e;
                }
                Thread.sleep(1000);
            }
            if (run == false)
                throw new Exception("�ظ�����" + maxTry + "���κ�û�гɹ���", runE);
        } catch (final Exception e) {
            String param = "";
            for (int i = 0; null != parameters && i < parameters.length; i++) {
                param += parameters[i].toString();
            }
            log.error("service:" + serviceName + " parameters:" + param + " error!", e);
            throw new IllegalArgumentException("service:" + serviceName + " error!");
        }
        return mOutput;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public boolean isEnabledForExtensions() {
        return enabledForExtensions;
    }

    public void setEnabledForExtensions(final boolean enabledForExtensions) {
        this.enabledForExtensions = enabledForExtensions;
    }

    public boolean isEnabledForExceptions() {
        return enabledForExceptions;
    }

    public void setEnabledForExceptions(final boolean enabledForExceptions) {
        this.enabledForExceptions = enabledForExceptions;
    }

    public int getMaxTry() {
        return maxTry;
    }

    public void setMaxTry(final int maxTry) {
        this.maxTry = maxTry;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(final int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public boolean isContentLengthOptional() {
        return contentLengthOptional;
    }

    public void setContentLengthOptional(final boolean contentLengthOptional) {
        this.contentLengthOptional = contentLengthOptional;
    }

    public String getBasicEncoding() {
        return basicEncoding;
    }

    public void setBasicEncoding(final String basicEncoding) {
        this.basicEncoding = basicEncoding;
    }

    public String getBasicPassword() {
        return basicPassword;
    }

    public void setBasicPassword(final String basicPassword) {
        this.basicPassword = basicPassword;
    }

    public String getBasicUserName() {
        return basicUserName;
    }

    public void setBasicUserName(final String basicUserName) {
        this.basicUserName = basicUserName;
    }
}
