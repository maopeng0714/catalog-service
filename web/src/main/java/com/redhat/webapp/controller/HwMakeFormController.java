package com.redhat.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.redhat.service.GenericManager;
import com.redhat.model.HwMake;
import com.redhat.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequestMapping("/hwMakeform*")
public class HwMakeFormController extends BaseFormController {
    private GenericManager<HwMake, Long> hwMakeManager = null;

    @Autowired
    public void setHwMakeManager(@Qualifier("hwMakeManager") GenericManager<HwMake, Long> hwMakeManager) {
        this.hwMakeManager = hwMakeManager;
    }

    public HwMakeFormController() {
        setCancelView("redirect:hwMakes");
        setSuccessView("redirect:hwMakes");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected HwMake showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return hwMakeManager.get(new Long(id));
        }

        return new HwMake();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(HwMake hwMake, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(hwMake, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "hwMakeform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (hwMake.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            hwMakeManager.remove(hwMake.getId());
            saveMessage(request, getText("hwMake.deleted", locale));
        } else {
            hwMakeManager.save(hwMake);
            String key = (isNew) ? "hwMake.added" : "hwMake.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:hwMakeform?id=" + hwMake.getId();
            }
        }

        return success;
    }
}
