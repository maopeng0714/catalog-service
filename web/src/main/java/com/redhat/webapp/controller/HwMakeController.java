package com.redhat.webapp.controller;

import com.redhat.dao.SearchException;
import com.redhat.service.GenericManager;
import com.redhat.model.HwMake;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hwMakes*")
public class HwMakeController {
    private GenericManager<HwMake, Long> hwMakeManager;

    @Autowired
    public void setHwMakeManager(@Qualifier("hwMakeManager") GenericManager<HwMake, Long> hwMakeManager) {
        this.hwMakeManager = hwMakeManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(hwMakeManager.search(query, HwMake.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(hwMakeManager.getAll());
        }
        return model;
    }
}
