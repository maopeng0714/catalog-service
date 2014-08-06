package com.redhat.service;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.springframework.transaction.annotation.Transactional;

import com.redhat.model.HwCert;
import com.redhat.xmlrpc.annotation.XmlRpcMethod;
import com.redhat.xmlrpc.annotation.XmlRpcService;

@WebService
@Path("/hwcerts")
@Transactional
@XmlRpcService(useMethodAnnotation = true)
public interface HwCertService {
    /**
     * Retrieves a HwCert by certId.
     *
     * @param certId the identifier for the user
     * @return HwCert
     */
    @GET
    @Path("{id}")
    @XmlRpcMethod
    HwCert getHwCert(@PathParam("id") String certId);

    /**
     * Finds a list of certs by their model name.
     *
     * @param model the model's name
     * @return List<HwCert> a list of HwCerts.
     */
    @GET
    @Path("{model}")
    @XmlRpcMethod
    List<HwCert> getHwCertsByModel(@PathParam("model") String model);

    /**
     *
     * @return
     */
    @GET
    @XmlRpcMethod
    public List<HwCert> getAllHwCerts();
    //
    // public List<Vendor> getAllVendors();
    //
    // public List<Product> getAllProducts();

}
