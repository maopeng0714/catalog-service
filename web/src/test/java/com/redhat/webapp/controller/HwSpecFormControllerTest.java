package com.redhat.webapp.controller;

import com.redhat.webapp.controller.BaseControllerTestCase;
import com.redhat.model.HwSpec;
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

public class HwSpecFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private HwSpecFormController form;
    private HwSpec hwSpec;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/hwSpecform");
        request.addParameter("id", "-1");

        hwSpec = form.showForm(request);
        assertNotNull(hwSpec);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/hwSpecform");
        request.addParameter("id", "-1");

        hwSpec = form.showForm(request);
        assertNotNull(hwSpec);

        request = newPost("/hwSpecform");

        hwSpec = form.showForm(request);
        // update required fields
        hwSpec.setName("IwOlFoHaMnByJhPbLlLgAbAdIwMkHi");

        BindingResult errors = new DataBinder(hwSpec).getBindingResult();
        form.onSubmit(hwSpec, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/hwSpecform");
        request.addParameter("delete", "");
        hwSpec = new HwSpec();
        hwSpec.setId(-2L);

        BindingResult errors = new DataBinder(hwSpec).getBindingResult();
        form.onSubmit(hwSpec, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
