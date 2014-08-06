package com.redhat.xmlrpc;

import org.apache.xmlrpc.webserver.ServletWebServer;
import org.apache.xmlrpc.webserver.XmlRpcServlet;

public class HwCertXmlRpcServiceTestServer {

    private static final int port = 8080;

    public static void main(final String[] args) throws Exception {
        XmlRpcServlet servlet = new XmlRpcServlet();
        ServletWebServer webServer = new ServletWebServer(servlet, port);
        webServer.start();
    }
}
