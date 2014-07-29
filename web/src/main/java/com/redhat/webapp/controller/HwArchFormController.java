package com.redhat.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.redhat.service.GenericManager;
import com.redhat.model.HwArch;
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
@RequestMapping("/hwArchform*")
public class HwArchFormController extends BaseFormController {
    private GenericManager<HwArch, Long> hwArchManager = null;

    @Autowired
    public void setHwArchManager(@Qualifier("hwArchManager") GenericManager<HwArch, Long> hwArchManager) {
        this.hwArchManager = hwArchManager;
    }

    public HwArchFormController() {
        setCancelView("redirect:hwArches");
        setSuccessView("redirect:hwArches");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected HwArch showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return hwArchManager.get(new Long(id));
        }

        return new HwArch();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(HwArch hwArch, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(hwArch, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "hwArchform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (hwArch.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            hwArchManager.remove(hwArch.getId());
            saveMessage(request, getText("hwArch.deleted", locale));
        } else {
            hwArchManager.save(hwArch);
            String key = (isNew) ? "hwArch.added" : "hwArch.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:hwArchform?id=" + hwArch.getId();
            }
        }

        return success;
    }
}
