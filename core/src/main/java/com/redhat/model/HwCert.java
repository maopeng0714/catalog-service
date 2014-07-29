/*
 * HwCert.java
 *
 * This file is the exclusive property of RedHat.
 * Reproduction and Unauthorized use are forbidden.
 *
 * Copyright (C) 2014 RedHat. All rights reserved.
 */
package com.redhat.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
@Entity
@Table(name="hwcert")
@PrimaryKeyJoinColumn(name="cert_id")
public class HwCert extends Cert {

    /** */
    private static final long serialVersionUID = 3215848906401538298L;
    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(HwCert.class);

    private HwArch arch = new HwArch();

    private HwSpec spec = new HwSpec();

    private HwMake make = new HwMake();

    private HWModel model = new HWModel();

    /**
     *
     * Creates a new HwCert.
     *
     * @param name
     */
    public HwCert(String name){
        super(name);
    }

    /**
     * Creates a new HwCert.
     *
     * @param arch
     * @param spec
     * @param make
     * @param model
     */
    public HwCert(String name, Vendor vendor, Product product, boolean isPublic, HwArch arch, HwSpec spec, HwMake make, HWModel model) {
        super(name, vendor, product, isPublic);
        this.arch = arch;
        this.spec = spec;
        this.make = make;
        this.model = model;
        LOGGER.debug(toString());
    }

    /**
     * Creates a new HwCert.
     *
     */
    public HwCert() {

    }


    /**
     * @return the arch
     */
    @ManyToOne
    @JoinColumn(name = "arch_id")
    public HwArch getArch() {
        return arch;
    }

    /**
     * @param arch the arch to set
     */
    public void setArch(HwArch arch) {
        this.arch = arch;
    }

    /**
     * @return the spec
     */
    @ManyToOne
    @JoinColumn(name = "spec_id")
    public HwSpec getSpec() {
        return spec;
    }

    /**
     * @param spec the spec to set
     */
    public void setSpec(HwSpec spec) {
        this.spec = spec;
    }

    /**
     * @return the make
     */
    @ManyToOne
    @JoinColumn(name = "make_id")
    public HwMake getMake() {
        return make;
    }

    /**
     * @param make the make to set
     */
    public void setMake(HwMake make) {
        this.make = make;
    }

    /**
     * @return the model
     */
    @ManyToOne
    @JoinColumn(name = "model_id")
    public HWModel getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(HWModel model) {
        this.model = model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (arch == null ? 0 : arch.hashCode());
        result = prime * result + (make == null ? 0 : make.hashCode());
        result = prime * result + (model == null ? 0 : model.hashCode());
        result = prime * result + (spec == null ? 0 : spec.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        final HwCert other = (HwCert) obj;
        if (arch == null) {
            if (other.arch != null)
                return false;
        } else if (!arch.equals(other.arch))
            return false;
        if (make == null) {
            if (other.make != null)
                return false;
        } else if (!make.equals(other.make))
            return false;
        if (model == null) {
            if (other.model != null)
                return false;
        } else if (!model.equals(other.model))
            return false;
        if (spec == null) {
            if (other.spec != null)
                return false;
        } else if (!spec.equals(other.spec))
            return false;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "HwCert [arch=" + arch + ", spec=" + spec + ", make=" + make + ", model=" + model
                + "]";
    }








}
