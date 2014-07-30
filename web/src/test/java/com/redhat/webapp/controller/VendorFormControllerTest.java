package com.redhat.webapp.controller;

import com.redhat.webapp.controller.BaseControllerTestCase;
import com.redhat.model.Vendor;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class VendorFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private VendorFormController form;
    private Vendor vendor;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/vendorform");
        request.addParameter("id", "-1");

        vendor = form.showForm(request);
        assertNotNull(vendor);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/vendorform");
        request.addParameter("id", "-1");

        vendor = form.showForm(request);
        assertNotNull(vendor);

        request = newPost("/vendorform");

        vendor = form.showForm(request);
        // update required fields
        vendor.setName("EvKqZhEtWeXhSyXvYnFbWoAdGyBhMq");

        BindingResult errors = new DataBinder(vendor).getBindingResult();
        form.onSubmit(vendor, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/vendorform");
        request.addParameter("delete", "");
        vendor = new Vendor();
        vendor.setId(-2L);

        BindingResult errors = new DataBinder(vendor).getBindingResult();
        form.onSubmit(vendor, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
