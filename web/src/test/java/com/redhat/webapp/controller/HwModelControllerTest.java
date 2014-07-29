package com.redhat.webapp.controller;

import com.redhat.service.GenericManager;
import com.redhat.model.HwModel;

import com.redhat.webapp.controller.BaseControllerTestCase;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class HwModelControllerTest extends BaseControllerTestCase {
    @Autowired
    private HwModelController controller;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null);
        Map m = model.asMap();
        assertNotNull(m.get("hwModelList"));
        assertTrue(((List) m.get("hwModelList")).size() > 0);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        GenericManager<HwModel, Long> hwModelManager = (GenericManager<HwModel, Long>) applicationContext.getBean("hwModelManager");
        hwModelManager.reindex();

        Model model = controller.handleRequest("*");
        Map m = model.asMap();
        List results = (List) m.get("hwModelList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}