package com.redhat.webapp.controller;

import com.redhat.service.GenericManager;
import com.redhat.model.Product;

import com.redhat.webapp.controller.BaseControllerTestCase;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProductControllerTest extends BaseControllerTestCase {
    @Autowired
    private ProductController controller;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null);
        Map m = model.asMap();
        assertNotNull(m.get("productList"));
        assertTrue(((List) m.get("productList")).size() > 0);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        GenericManager<Product, Long> productManager = (GenericManager<Product, Long>) applicationContext.getBean("productManager");
        productManager.reindex();

        Model model = controller.handleRequest("*");
        Map m = model.asMap();
        List results = (List) m.get("productList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}