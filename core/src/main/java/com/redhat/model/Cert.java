/*
 * Certification.java
 *
 * This file is the exclusive property of RedHat.
 * Reproduction and Unauthorized use are forbidden.
 *
 * Copyright (C) 2014 RedHat. All rights reserved.
 */
package com.redhat.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
// @Entity
// @Table(name="cert")
// @Inheritance(strategy=InheritanceType.JOINED)
@MappedSuperclass
@Indexed
@XmlRootElement
public class Cert extends BaseObject {

    /** */
    private static final long serialVersionUID = 308328607273405167L;
    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(Cert.class);
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Field
    @Column(unique = true, nullable = false, length = 30)
    private String name;
    @ManyToOne
    @JoinColumn(name = "vendor_id", referencedColumnName = "id")
    private Vendor vendor = new Vendor();
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product = new Product();
    @Column(name = "show_public")
    private boolean isPublic;

    /**
     *
     * Creates a new Certification.
     *
     */
    public Cert() {
    };

    /**
     * Creates a new Certification.
     *
     * @param name
     * @param vendorId
     * @param productId
     */
    public Cert(final String name) {
        super();
        this.name = name;
        LOGGER.debug(toString());
    }

    /**
     * Creates a new Certification.
     *
     * @param name
     * @param vendor
     * @param product
     * @param isPublic
     */
    public Cert(final String name, final Vendor vendor, final Product product,
            final boolean isPublic) {
        super();
        this.name = name;
        this.vendor = vendor;
        this.product = product;
        this.isPublic = isPublic;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the isPublic
     */
    public boolean isPublic() {
        return isPublic;
    }

    /**
     * @param isPublic the isPublic to set
     */
    public void setPublic(final boolean isPublic) {
        this.isPublic = isPublic;
    }

    /**
     * @return the vendor
     */
    public Vendor getVendor() {
        return vendor;
    }

    /**
     * @param vendor the vendor to set
     */
    public void setVendor(final Vendor vendor) {
        this.vendor = vendor;
    }

    /**
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(final Product product) {
        this.product = product;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (isPublic ? 1231 : 1237);
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (product == null ? 0 : product.hashCode());
        result = prime * result + (vendor == null ? 0 : vendor.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Cert other = (Cert) obj;
        if (isPublic != other.isPublic)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (product == null) {
            if (other.product != null)
                return false;
        } else if (!product.equals(other.product))
            return false;
        if (vendor == null) {
            if (other.vendor != null)
                return false;
        } else if (!vendor.equals(other.vendor))
            return false;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Certification [name=" + name + ", vendor=" + vendor + ", product=" + product
                + ", isPublic=" + isPublic + "]";
    }

}
