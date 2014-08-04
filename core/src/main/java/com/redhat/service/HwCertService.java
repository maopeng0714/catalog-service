package com.redhat.service;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.springframework.transaction.annotation.Transactional;

import com.redhat.model.HwCert;

@WebService
@Path("/hwcerts")
@Transactional
public interface HwCertService {
    /**
     * Retrieves a user by userId. An exception is thrown if user not found
     *
     * @param certId the identifier for the user
     * @return User
     */
    @GET
    @Path("{id}")
    HwCert getHwCert(@PathParam("id") String certId);

    /**
     * Finds a list of certs by their model name.
     *
     * @param model the model's name
     * @return List<HwCert> a list of HwCerts.
     */
    @GET
    @Path("{model}")
    List<HwCert> getHwCertsByModel(@PathParam("model") String model);

    /**
     *
     * @return
     */
    @GET
    public List<HwCert> getAllHwCerts();
    //
    // public List<Vendor> getAllVendors();
    //
    // public List<Product> getAllProducts();

}
