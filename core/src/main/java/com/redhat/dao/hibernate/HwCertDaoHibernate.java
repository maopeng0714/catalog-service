/*
 * HwCertDaoHibernate.java
 *
 * This file is the exclusive property of RedHat.
 * Reproduction and Unauthorized use are forbidden.
 *
 * Copyright (C) 2014 RedHat. All rights reserved.
 */
package com.redhat.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.redhat.dao.HwCertDao;
import com.redhat.model.HwCert;

/**
 *
 */
@Repository("hwCertDao")
public class HwCertDaoHibernate extends GenericDaoHibernate<HwCert, Long> implements HwCertDao {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(HwCertDaoHibernate.class);

    public HwCertDaoHibernate() {
        super(HwCert.class);
    }

    @Override
    public List<HwCert> getCertsByModel(final String modelName) {
        List<HwCert> allCerts = super.getAll();

        List<HwCert> result = new ArrayList<HwCert>();
        for (HwCert cert : allCerts) {
            if (cert.getModel().getName().equalsIgnoreCase(modelName)) {
                result.add(cert);
            }
        }

        LOGGER.debug("Found " + result.size() + "Certs for model " + modelName);
        return result;
    }

}
