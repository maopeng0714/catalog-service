package com.redhat.webapp.xmlrpc;

/**
 * <li>xml rpc client d�İ�װ��</li>
 *
 * @author yangpeng 2008-8-11 ����04:27:35
 */
public interface XmlRpcClientWrapper {

    /**
     * ִ�з���
     *
     * @param serviceName ������ƣ����磺"Calculator.add"
     * @param parameters ����
     * @return ����ִ�еõ��Ľ��
     */
    public Object execute(String serviceName, Object[] parameters);
}
