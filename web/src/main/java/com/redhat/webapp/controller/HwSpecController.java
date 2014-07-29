package com.redhat.webapp.controller;

import com.redhat.dao.SearchException;
import com.redhat.service.GenericManager;
import com.redhat.model.HwSpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hwSpecs*")
public class HwSpecController {
    private GenericManager<HwSpec, Long> hwSpecManager;

    @Autowired
    public void setHwSpecManager(@Qualifier("hwSpecManager") GenericManager<HwSpec, Long> hwSpecManager) {
        this.hwSpecManager = hwSpecManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(hwSpecManager.search(query, HwSpec.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(hwSpecManager.getAll());
        }
        return model;
    }
}
