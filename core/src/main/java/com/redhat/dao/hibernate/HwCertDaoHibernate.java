/*
 * HwCertDaoHibernate.java
 *
 * This file is the exclusive property of RedHat.
 * Reproduction and Unauthorized use are forbidden.
 *
 * Copyright (C) 2014 RedHat. All rights reserved.
 */
package com.redhat.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.dao.HwCertDao;
import com.redhat.model.HwArch;
import com.redhat.model.HwCert;
import com.redhat.model.HwMake;
import com.redhat.model.HwModel;
import com.redhat.model.Product;
import com.redhat.model.Vendor;

/**
 *
 */
public class HwCertDaoHibernate extends GenericDaoHibernate<HwCert, Long> implements HwCertDao {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(HwCertDaoHibernate.class);

    public HwCertDaoHibernate() {
        super(HwCert.class);
    }

    @Override
    public HwCert get(final Long id) {
        Query qry = getSession().createQuery(
                "from hw_cert order by upper(hw_cert.name) where id = ?");
        qry.setLong(0, id);
        List<HwCert> certs = qry.list();
        if (certs == null || certs.isEmpty())
            return null;
        else
            return certs.get(0);
    }

    @Override
    public HwCert save(final HwCert object) {
        if (log.isDebugEnabled())
            log.debug("hwcert's name: " + object.getName());
        getSession().saveOrUpdate(object);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return object;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<HwModel> getAllModels() {
        Query qry = getSession().createQuery("from hw_model order by upper(hw_model.name)");
        return qry.list();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<HwMake> getAllMakes() {
        Query qry = getSession().createQuery("from hw_make order by upper(hw_make.name)");
        return qry.list();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<HwArch> getAllArchs() {
        Query qry = getSession().createQuery("from hw_arch order by upper(hw_arch.name)");
        return qry.list();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Product> getAllProducts() {
        Query qry = getSession().createQuery("from product order by upper(product.name)");
        return qry.list();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Vendor> getAllVendors() {
        Query qry = getSession().createQuery("from vendor order by upper(vendor.name)");
        return qry.list();
    }
}
