package com.redhat.webapp.controller;

import com.redhat.dao.SearchException;
import com.redhat.service.GenericManager;
import com.redhat.model.HwArch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hwArches*")
public class HwArchController {
    private GenericManager<HwArch, Long> hwArchManager;

    @Autowired
    public void setHwArchManager(@Qualifier("hwArchManager") GenericManager<HwArch, Long> hwArchManager) {
        this.hwArchManager = hwArchManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(hwArchManager.search(query, HwArch.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(hwArchManager.getAll());
        }
        return model;
    }
}
