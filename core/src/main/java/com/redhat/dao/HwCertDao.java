/*
 * HwCertDao.java
 *
 * This file is the exclusive property of RedHat.
 * Reproduction and Unauthorized use are forbidden.
 *
 * Copyright (C) 2014 RedHat. All rights reserved.
 */
package com.redhat.dao;

import java.util.List;

import com.redhat.model.HwArch;
import com.redhat.model.HwCert;
import com.redhat.model.HwMake;
import com.redhat.model.HwModel;
import com.redhat.model.Product;
import com.redhat.model.Vendor;

/**
 *
 */
public interface HwCertDao extends GenericDao<HwCert, Long> {

    public List<HwModel> getAllModels();

    public List<HwMake> getAllMakes();

    public List<HwArch> getAllArchs();

    public List<Product> getAllProducts();

    public List<Vendor> getAllVendors();

}
