package com.redhat.webapp.controller;

import com.redhat.webapp.controller.BaseControllerTestCase;
import com.redhat.model.HwArch;
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

public class HwArchFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private HwArchFormController form;
    private HwArch hwArch;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/hwArchform");
        request.addParameter("id", "-1");

        hwArch = form.showForm(request);
        assertNotNull(hwArch);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/hwArchform");
        request.addParameter("id", "-1");

        hwArch = form.showForm(request);
        assertNotNull(hwArch);

        request = newPost("/hwArchform");

        hwArch = form.showForm(request);
        // update required fields
        hwArch.setName("ZdBrMtJjYoQiKnVlJnQzJbLfZhXyEa");

        BindingResult errors = new DataBinder(hwArch).getBindingResult();
        form.onSubmit(hwArch, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/hwArchform");
        request.addParameter("delete", "");
        hwArch = new HwArch();
        hwArch.setId(-2L);

        BindingResult errors = new DataBinder(hwArch).getBindingResult();
        form.onSubmit(hwArch, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
