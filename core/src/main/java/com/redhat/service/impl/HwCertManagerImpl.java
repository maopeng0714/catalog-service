package com.redhat.service.impl;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redhat.dao.HwCertDao;
import com.redhat.model.HwCert;
import com.redhat.service.HwCertManager;
import com.redhat.service.HwCertService;

@Service("hwCertManager")
@WebService(serviceName = "HwCertService", endpointInterface = "com.redhat.service.HwCertService")
public class HwCertManagerImpl extends GenericManagerImpl<HwCert, Long> implements HwCertManager,
HwCertService {

    // private GenericDao<Vendor, Long> vendorDao;
    // private GenericDao<Product, Long> productDao;
    @Autowired
    private HwCertDao hwDao = null;

    public HwCertManagerImpl() {
    };

    public HwCertManagerImpl(final HwCertDao hwDao) {
        super(hwDao);
        this.hwDao = hwDao;
    }

    @Override
    public List<HwCert> getAllHwCerts() {
        return hwDao.getAll();
    }

    @Override
    public List<HwCert> getHwCertsByModel(final String modelName) {
        return hwDao.getCertsByModel(modelName);
    }

    @Override
    public HwCert getHwCert(final String certId) {
        return hwDao.get(Long.parseLong(certId));
    }

    // /**
    // * @param vendorDao the vendorDao to set
    // */
    // @Autowired
    // public void setVendorDao(final GenericDao<Vendor, Long> vendorDao) {
    // this.vendorDao = vendorDao;
    // }
    //
    // /**
    // * @param vendorDao the vendorDao to set
    // */
    // @Autowired
    // public void setProductDao(final GenericDao<Product, Long> productDao) {
    // this.productDao = productDao;
    // }
    //
    // @Override
    // public List<Vendor> getAllVendors() {
    // return vendorDao.getAll();
    // }
    //
    // @Override
    // public List<Product> getAllProducts() {
    // return productDao.getAll();
    // }
}
