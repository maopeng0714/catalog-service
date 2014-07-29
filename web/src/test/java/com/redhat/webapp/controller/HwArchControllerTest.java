package com.redhat.webapp.controller;

import com.redhat.service.GenericManager;
import com.redhat.model.HwArch;

import com.redhat.webapp.controller.BaseControllerTestCase;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class HwArchControllerTest extends BaseControllerTestCase {
    @Autowired
    private HwArchController controller;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null);
        Map m = model.asMap();
        assertNotNull(m.get("hwArchList"));
        assertTrue(((List) m.get("hwArchList")).size() > 0);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        GenericManager<HwArch, Long> hwArchManager = (GenericManager<HwArch, Long>) applicationContext.getBean("hwArchManager");
        hwArchManager.reindex();

        Model model = controller.handleRequest("*");
        Map m = model.asMap();
        List results = (List) m.get("hwArchList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}