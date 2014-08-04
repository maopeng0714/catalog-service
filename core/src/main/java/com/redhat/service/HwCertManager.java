/*
 * HwCertManager.java
 *
 * This file is the exclusive property of RedHat.
 * Reproduction and Unauthorized use are forbidden.
 *
 * Copyright (C) 2014 RedHat. All rights reserved.
 */
package com.redhat.service;

import java.util.List;

import javax.jws.WebService;

import com.redhat.model.HwCert;

/**
 *
 */
@WebService
public interface HwCertManager extends GenericManager<HwCert, Long> {

    List<HwCert> getHwCertsByModel(String modelName);

}
