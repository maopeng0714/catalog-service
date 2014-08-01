package com.redhat.webapp.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.redhat.model.HwArch;
import com.redhat.service.GenericManager;

public class HwArchControllerTest extends BaseControllerTestCase {
    @Autowired
    private HwArchController controller;

    @Test
    public void testHandleRequest() throws Exception {
        final Model model = controller.handleRequest(null);
        final Map m = model.asMap();
        assertNotNull(m.get("hwArchList"));
        assertTrue(((List) m.get("hwArchList")).size() > 0);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        final GenericManager<HwArch, Long> hwArchManager = (GenericManager<HwArch, Long>) applicationContext.getBean("hwArchManager");
        hwArchManager.reindex();

        final Model model = controller.handleRequest("*");
        final Map m = model.asMap();
        final List results = (List) m.get("hwArchList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}