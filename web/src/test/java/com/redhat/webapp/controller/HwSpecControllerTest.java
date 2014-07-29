package com.redhat.webapp.controller;

import com.redhat.service.GenericManager;
import com.redhat.model.HwSpec;

import com.redhat.webapp.controller.BaseControllerTestCase;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class HwSpecControllerTest extends BaseControllerTestCase {
    @Autowired
    private HwSpecController controller;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null);
        Map m = model.asMap();
        assertNotNull(m.get("hwSpecList"));
        assertTrue(((List) m.get("hwSpecList")).size() > 0);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        GenericManager<HwSpec, Long> hwSpecManager = (GenericManager<HwSpec, Long>) applicationContext.getBean("hwSpecManager");
        hwSpecManager.reindex();

        Model model = controller.handleRequest("*");
        Map m = model.asMap();
        List results = (List) m.get("hwSpecList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}