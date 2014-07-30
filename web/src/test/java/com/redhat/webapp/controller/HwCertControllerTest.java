package com.redhat.webapp.controller;

import com.redhat.service.GenericManager;
import com.redhat.model.HwCert;

import com.redhat.webapp.controller.BaseControllerTestCase;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class HwCertControllerTest extends BaseControllerTestCase {
    @Autowired
    private HwCertController controller;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null);
        Map m = model.asMap();
        assertNotNull(m.get("hwCertList"));
        assertTrue(((List) m.get("hwCertList")).size() > 0);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        GenericManager<HwCert, Long> hwCertManager = (GenericManager<HwCert, Long>) applicationContext.getBean("hwCertManager");
        hwCertManager.reindex();

        Model model = controller.handleRequest("*");
        Map m = model.asMap();
        List results = (List) m.get("hwCertList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}