/*
 * XmlRpcServices.java
 *
 * This file is the exclusive property of RedHat.
 * Reproduction and Unauthorized use are forbidden.
 *
 * Copyright (C) 2014 RedHat. All rights reserved.
 */
package com.redhat.service;

import java.util.List;

import com.redhat.model.HwCert;



/**
 * Contains all the xml rpc services
 */
public interface XmlRpcServices {
    /**
     * Retrieves a HwCert by certId.
     *
     * @param certId the identifier for the user
     * @return HwCert
     */
    HwCert getHwCert(String certId);

    /**
     * Finds a list of certs by their model name.
     *
     * @param model the model's name
     * @return List<HwCert> a list of HwCerts.
     */
    List<HwCert> getHwCertsByModel( String model);

    /**
     *
     * @return
     */
    public List<HwCert> getAllHwCerts();
}
