/*
 * Product.java
 *
 * This file is the exclusive property of RedHat.
 * Reproduction and Unauthorized use are forbidden.
 *
 * Copyright (C) 2014 RedHat. All rights reserved.
 */
package com.redhat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
@Entity @Table(name="product")
public class Product extends BaseObject {

    /** */
    private static final long serialVersionUID = 4143896212107327597L;

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(Product.class);
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(unique=true, nullable=false, length=30)
    private String name;
    @Column(unique=true, nullable=false)
    private int majorVersion;
    private int minorVersion;

    /**
     * Creates a new Product.
     *
     */
    public Product() {
    }

    /**
     * Creates a new Product.
     *
     * @param name
     * @param majorVersion
     * @param minorVersion
     */
    public Product(String name, int majorVersion, int minorVersion) {
        super();
        this.name = name;
        this.majorVersion = majorVersion;
        this.minorVersion = minorVersion;
        LOGGER.debug(toString());
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the majorVersion
     */
    public int getMajorVersion() {
        return majorVersion;
    }

    /**
     * @param majorVersion the majorVersion to set
     */
    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }

    /**
     * @return the minorVersion
     */
    public int getMinorVersion() {
        return minorVersion;
    }

    /**
     * @param minorVersion the minorVersion to set
     */
    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + majorVersion;
        result = prime * result + minorVersion;
        result = prime * result + (name == null ? 0 : name.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Product other = (Product) obj;
        if (majorVersion != other.majorVersion)
            return false;
        if (minorVersion != other.minorVersion)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Product [name=" + name + ", majorVersion=" + majorVersion + ", minorVersion="
                + minorVersion + "]";
    }

}