package com.redhat.webapp.controller;

import com.redhat.webapp.controller.BaseControllerTestCase;
import com.redhat.model.HwModel;
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

public class HwModelFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private HwModelFormController form;
    private HwModel hwModel;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/hwModelform");
        request.addParameter("id", "-1");

        hwModel = form.showForm(request);
        assertNotNull(hwModel);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/hwModelform");
        request.addParameter("id", "-1");

        hwModel = form.showForm(request);
        assertNotNull(hwModel);

        request = newPost("/hwModelform");

        hwModel = form.showForm(request);
        // update required fields
        hwModel.setName("WvLiAhOoMbEoEoIgJpWmHwUrWyLcHb");

        BindingResult errors = new DataBinder(hwModel).getBindingResult();
        form.onSubmit(hwModel, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/hwModelform");
        request.addParameter("delete", "");
        hwModel = new HwModel();
        hwModel.setId(-2L);

        BindingResult errors = new DataBinder(hwModel).getBindingResult();
        form.onSubmit(hwModel, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
