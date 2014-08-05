package com.redhat.xmlrpc.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.xmlrpc.webserver.XmlRpcServletServer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class SpringXmlRpcFilter extends OncePerRequestFilter {

    public static final String DEFAULT_XML_RPC_SERVIER_NAME = "xmlRpcServer";
    private String servierName = DEFAULT_XML_RPC_SERVIER_NAME;
    private XmlRpcServletServer server;

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain)
                    throws ServletException, IOException {
        server.execute(request, response);
        // filterChain.doFilter(request, response);
    }
    /*
     * (non-Javadoc)
     *
     * @see org.springframework.web.filter.GenericFilterBean#initFilterBean()
     */
    @Override
    protected void initFilterBean() throws ServletException {
        final WebApplicationContext wac = WebApplicationContextUtils
                .getRequiredWebApplicationContext(getServletContext());
        server = wac.getBean(servierName,
                XmlRpcServletServer.class);
    }
    public String getServierName() {
        return servierName;
    }
    public void setServierName(String servierName) {
        this.servierName = servierName;
    }

}
