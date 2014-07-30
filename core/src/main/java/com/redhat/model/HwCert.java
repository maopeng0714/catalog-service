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
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;

/**
 *
 */
@Entity
@Table(name = "hw_cert")
@Indexed
public class HwCert extends Cert {

    /** */
    private static final long serialVersionUID = 3215848906401538298L;
    @ManyToOne
    @JoinColumn(name = "arch_id")
    private HwArch arch = new HwArch();

    @ManyToOne
    @JoinColumn(name = "spec_id")
    private HwSpec spec = new HwSpec();

    @ManyToOne
    @JoinColumn(name = "make_id")
    private HwMake make = new HwMake();

    @ManyToOne
    @JoinColumn(name = "model_id")
    private HwModel model = new HwModel();

    /**
     *
     * Creates a new HwCert.
     *
     * @param name
     */
    public HwCert(final String name) {
        super(name);
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
    public HwArch getArch() {
        return arch;
    }

    /**
     * @param arch the arch to set
     */
    public void setArch(final HwArch arch) {
        this.arch = arch;
    }

    /**
     * @return the spec
     */
    public HwSpec getSpec() {
        return spec;
    }

    /**
     * @param spec the spec to set
     */
    public void setSpec(final HwSpec spec) {
        this.spec = spec;
    }

    /**
     * @return the make
     */
    public HwMake getMake() {
        return make;
    }

    /**
     * @param make the make to set
     */
    public void setMake(final HwMake make) {
        this.make = make;
    }

    /**
     * @return the model
     */
    public HwModel getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(final HwModel model) {
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
    public boolean equals(final Object obj) {
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
