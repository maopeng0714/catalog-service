package com.redhat.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.redhat.service.GenericManager;
import com.redhat.model.HwSpec;
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
@RequestMapping("/hwSpecform*")
public class HwSpecFormController extends BaseFormController {
    private GenericManager<HwSpec, Long> hwSpecManager = null;

    @Autowired
    public void setHwSpecManager(@Qualifier("hwSpecManager") GenericManager<HwSpec, Long> hwSpecManager) {
        this.hwSpecManager = hwSpecManager;
    }

    public HwSpecFormController() {
        setCancelView("redirect:hwSpecs");
        setSuccessView("redirect:hwSpecs");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected HwSpec showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return hwSpecManager.get(new Long(id));
        }

        return new HwSpec();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(HwSpec hwSpec, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(hwSpec, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "hwSpecform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (hwSpec.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            hwSpecManager.remove(hwSpec.getId());
            saveMessage(request, getText("hwSpec.deleted", locale));
        } else {
            hwSpecManager.save(hwSpec);
            String key = (isNew) ? "hwSpec.added" : "hwSpec.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:hwSpecform?id=" + hwSpec.getId();
            }
        }

        return success;
    }
}
