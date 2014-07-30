package com.redhat.webapp.controller;

import com.redhat.webapp.controller.BaseControllerTestCase;
import com.redhat.model.HwCert;
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

public class HwCertFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private HwCertFormController form;
    private HwCert hwCert;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/hwCertform");
        request.addParameter("id", "-1");

        hwCert = form.showForm(request);
        assertNotNull(hwCert);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/hwCertform");
        request.addParameter("id", "-1");

        hwCert = form.showForm(request);
        assertNotNull(hwCert);

        request = newPost("/hwCertform");

        hwCert = form.showForm(request);
        // update required fields
        hwCert.setName("LvEbAlFsVoWtQfAjYtWkPnViVbYkXr");

        BindingResult errors = new DataBinder(hwCert).getBindingResult();
        form.onSubmit(hwCert, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/hwCertform");
        request.addParameter("delete", "");
        hwCert = new HwCert();
        hwCert.setId(-2L);

        BindingResult errors = new DataBinder(hwCert).getBindingResult();
        form.onSubmit(hwCert, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
