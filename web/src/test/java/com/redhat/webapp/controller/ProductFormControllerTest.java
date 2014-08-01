package com.redhat.webapp.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import com.redhat.model.Product;

public class ProductFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private ProductFormController form;
    private Product product;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/productform");
        request.addParameter("id", "-1");

        product = form.showForm(request);
        assertNotNull(product);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/productform");
        request.addParameter("id", "-1");

        product = form.showForm(request);
        assertNotNull(product);

        request = newPost("/productform");

        product = form.showForm(request);
        // update required fields
        product.setMajorVersion(1327556666);
        product.setMinorVersion(307995428);
        product.setName("OhZfKeLjUgXfNgNnXqSnBeYwHnGvAx");

        final BindingResult errors = new DataBinder(product).getBindingResult();
        form.onSubmit(product, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/productform");
        request.addParameter("delete", "");
        product = new Product();
        product.setId(-2L);

        final BindingResult errors = new DataBinder(product).getBindingResult();
        form.onSubmit(product, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
