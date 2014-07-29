package com.redhat.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.redhat.service.GenericManager;
import com.redhat.model.Product;
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
@RequestMapping("/productform*")
public class ProductFormController extends BaseFormController {
    private GenericManager<Product, Long> productManager = null;

    @Autowired
    public void setProductManager(@Qualifier("productManager") GenericManager<Product, Long> productManager) {
        this.productManager = productManager;
    }

    public ProductFormController() {
        setCancelView("redirect:products");
        setSuccessView("redirect:products");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Product showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return productManager.get(new Long(id));
        }

        return new Product();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Product product, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(product, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "productform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (product.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            productManager.remove(product.getId());
            saveMessage(request, getText("product.deleted", locale));
        } else {
            productManager.save(product);
            String key = (isNew) ? "product.added" : "product.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:productform?id=" + product.getId();
            }
        }

        return success;
    }
}
