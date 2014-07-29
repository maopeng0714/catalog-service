/*
 * Vendor.java
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
@Entity @Table(name="model")
public class HwModel extends BaseObject {

    /** */
    private static final long serialVersionUID = 4755592464042723105L;

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(HwModel.class);
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(unique=true, nullable=false, length=30)
    private String name;

    /**
     * Creates a new Model.
     *
     */
    public HwModel(){};

    /**
     * Creates a new Model.
     * @param name
     */
    public HwModel(String name) {
        super();
        this.name = name;
        LOGGER.debug(toString());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Model [ name=" + name + "]";
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        final HwModel other = (HwModel) obj;

        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
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


}
