package com.redhat.webapp;

import java.net.URL;
import java.util.List;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;
import org.apache.xmlrpc.client.util.ClientFactory;

import com.redhat.model.HwCert;
import com.redhat.service.HwCertService;

public class HwCertXmlRpcServiceTestClient {

    public static void main(final String[] args) throws Exception {
        // create configuration
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL("http://127.0.0.1:8080/xmlrpc"));
        config.setEnabledForExtensions(true);
        config.setConnectionTimeout(60 * 1000);
        config.setReplyTimeout(60 * 1000);

        XmlRpcClient client = new XmlRpcClient();

        // use Commons HttpClient as transport
        client.setTransportFactory(new XmlRpcCommonsTransportFactory(client));
        // set configuration
        client.setConfig(config);

        // make the a regular call
        Object[] params = new Object[] { "testmodel1" };
        List<HwCert> result = (List<HwCert>) client.execute("HwCertService.getHwCertsByModel",
                params);
        System.out.println("There are " + result.size() + " for model ");

        // make a call using dynamic proxy
        ClientFactory factory = new ClientFactory(client);
        HwCertService certService = (HwCertService) factory.newInstance(HwCertService.class);
        List<HwCert> certList = certService.getAllHwCerts();
        System.out.println("There are " + certList.size() + " certs totally");
    }

}
