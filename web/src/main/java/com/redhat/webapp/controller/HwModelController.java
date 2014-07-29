package com.redhat.webapp.controller;

import com.redhat.dao.SearchException;
import com.redhat.service.GenericManager;
import com.redhat.model.HwModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hwModels*")
public class HwModelController {
    private GenericManager<HwModel, Long> hwModelManager;

    @Autowired
    public void setHwModelManager(@Qualifier("hwModelManager") GenericManager<HwModel, Long> hwModelManager) {
        this.hwModelManager = hwModelManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(hwModelManager.search(query, HwModel.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(hwModelManager.getAll());
        }
        return model;
    }
}
