package com.redhat.webapp.controller;

import com.redhat.webapp.controller.BaseControllerTestCase;
import com.redhat.model.HwMake;
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

public class HwMakeFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private HwMakeFormController form;
    private HwMake hwMake;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/hwMakeform");
        request.addParameter("id", "-1");

        hwMake = form.showForm(request);
        assertNotNull(hwMake);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/hwMakeform");
        request.addParameter("id", "-1");

        hwMake = form.showForm(request);
        assertNotNull(hwMake);

        request = newPost("/hwMakeform");

        hwMake = form.showForm(request);
        // update required fields
        hwMake.setName("WoBqOxHoKjEiQoKyBgYkPsIaLlRvDk");

        BindingResult errors = new DataBinder(hwMake).getBindingResult();
        form.onSubmit(hwMake, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/hwMakeform");
        request.addParameter("delete", "");
        hwMake = new HwMake();
        hwMake.setId(-2L);

        BindingResult errors = new DataBinder(hwMake).getBindingResult();
        form.onSubmit(hwMake, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
