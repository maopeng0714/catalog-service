package com.redhat.webapp.controller;

import com.redhat.service.GenericManager;
import com.redhat.model.HwMake;

import com.redhat.webapp.controller.BaseControllerTestCase;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class HwMakeControllerTest extends BaseControllerTestCase {
    @Autowired
    private HwMakeController controller;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null);
        Map m = model.asMap();
        assertNotNull(m.get("hwMakeList"));
        assertTrue(((List) m.get("hwMakeList")).size() > 0);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        GenericManager<HwMake, Long> hwMakeManager = (GenericManager<HwMake, Long>) applicationContext.getBean("hwMakeManager");
        hwMakeManager.reindex();

        Model model = controller.handleRequest("*");
        Map m = model.asMap();
        List results = (List) m.get("hwMakeList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}