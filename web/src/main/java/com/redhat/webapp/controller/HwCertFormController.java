package com.redhat.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.redhat.service.GenericManager;
import com.redhat.model.HwCert;
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
@RequestMapping("/hwCertform*")
public class HwCertFormController extends BaseFormController {
    private GenericManager<HwCert, Long> hwCertManager = null;

    @Autowired
    public void setHwCertManager(@Qualifier("hwCertManager") GenericManager<HwCert, Long> hwCertManager) {
        this.hwCertManager = hwCertManager;
    }

    public HwCertFormController() {
        setCancelView("redirect:hwCerts");
        setSuccessView("redirect:hwCerts");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected HwCert showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return hwCertManager.get(new Long(id));
        }

        return new HwCert();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(HwCert hwCert, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(hwCert, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "hwCertform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (hwCert.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            hwCertManager.remove(hwCert.getId());
            saveMessage(request, getText("hwCert.deleted", locale));
        } else {
            hwCertManager.save(hwCert);
            String key = (isNew) ? "hwCert.added" : "hwCert.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:hwCertform?id=" + hwCert.getId();
            }
        }

        return success;
    }
}
