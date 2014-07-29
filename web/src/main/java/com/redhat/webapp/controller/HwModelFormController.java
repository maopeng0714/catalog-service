package com.redhat.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.redhat.service.GenericManager;
import com.redhat.model.HwModel;
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
@RequestMapping("/hwModelform*")
public class HwModelFormController extends BaseFormController {
    private GenericManager<HwModel, Long> hwModelManager = null;

    @Autowired
    public void setHwModelManager(@Qualifier("hwModelManager") GenericManager<HwModel, Long> hwModelManager) {
        this.hwModelManager = hwModelManager;
    }

    public HwModelFormController() {
        setCancelView("redirect:hwModels");
        setSuccessView("redirect:hwModels");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected HwModel showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return hwModelManager.get(new Long(id));
        }

        return new HwModel();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(HwModel hwModel, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(hwModel, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "hwModelform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (hwModel.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            hwModelManager.remove(hwModel.getId());
            saveMessage(request, getText("hwModel.deleted", locale));
        } else {
            hwModelManager.save(hwModel);
            String key = (isNew) ? "hwModel.added" : "hwModel.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:hwModelform?id=" + hwModel.getId();
            }
        }

        return success;
    }
}
