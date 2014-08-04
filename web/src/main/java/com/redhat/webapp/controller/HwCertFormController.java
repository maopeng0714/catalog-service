package com.redhat.webapp.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.redhat.model.HwArch;
import com.redhat.model.HwCert;
import com.redhat.model.HwMake;
import com.redhat.model.HwModel;
import com.redhat.model.HwSpec;
import com.redhat.model.Product;
import com.redhat.model.Vendor;
import com.redhat.service.GenericManager;
import com.redhat.service.HwCertManager;

@Controller
@RequestMapping("/hwCertform*")
public class HwCertFormController extends BaseFormController {
    @Autowired
    private HwCertManager hwCertManager = null;
    private GenericManager<Product, Long> productManager;
    private GenericManager<Vendor, Long> vendorManager;
    private GenericManager<HwModel, Long> modelManager;
    private GenericManager<HwMake, Long> makeManager;
    private GenericManager<HwSpec, Long> specManager;
    private GenericManager<HwArch, Long> archManager;

    public HwCertFormController() {
        setCancelView("redirect:hwCerts");
        setSuccessView("redirect:hwCerts");
    }

    /**
     * @param productManager the productManager to set
     */
    @Autowired
    public void setProductManager(
            @Qualifier("productManager") final GenericManager<Product, Long> productManager) {
        this.productManager = productManager;
    }

    @ModelAttribute("productList")
    public List<Product> getProductList() {
        return productManager.getAll();
    }

    /**
     * @param vendorManager the vendorManager to set
     */
    @Autowired
    public void setVendorManager(
            @Qualifier("vendorManager") final GenericManager<Vendor, Long> vendorManager) {
        this.vendorManager = vendorManager;
    }

    @ModelAttribute("vendorList")
    public List<Vendor> getVendorList() {
        return vendorManager.getAll();
    }

    /**
     * @param modelManager the modelManager to set
     */
    @Autowired
    public void setModelManager(
            @Qualifier("hwModelManager") final GenericManager<HwModel, Long> modelManager) {
        this.modelManager = modelManager;
    }

    @ModelAttribute("modelList")
    public List<HwModel> getModelList() {
        return modelManager.getAll();
    }

    /**
     * @param makeManager the makeManager to set
     */
    @Autowired
    public void setMakeManager(
            @Qualifier("hwMakeManager") final GenericManager<HwMake, Long> makeManager) {
        this.makeManager = makeManager;
    }

    @ModelAttribute("makeList")
    public List<HwMake> getMakeList() {
        return makeManager.getAll();
    }

    /**
     * @param specManager the specManager to set
     */
    @Autowired
    public void setSpecManager(
            @Qualifier("hwSpecManager") final GenericManager<HwSpec, Long> specManager) {
        this.specManager = specManager;
    }

    @ModelAttribute("specList")
    public List<HwSpec> getSpecList() {
        return specManager.getAll();
    }

    /**
     * @param archManager the archManager to set
     */
    @Autowired
    public void setArchManager(
            @Qualifier("hwArchManager") final GenericManager<HwArch, Long> archManager) {
        this.archManager = archManager;
    }

    @ModelAttribute("archList")
    public List<HwArch> getArchList() {
        return archManager.getAll();
    }

    @Autowired
    public void setHwCertManager(@Qualifier("hwCertManager") final HwCertManager hwCertManager) {
        this.hwCertManager = hwCertManager;
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected HwCert showForm(final HttpServletRequest request) throws Exception {
        final String id = request.getParameter("id");

        if (!StringUtils.isBlank(id))
            return hwCertManager.get(new Long(id));

        return new HwCert();
    }

    @RequestMapping
    private ModelAndView getAllLists() {
        final ModelAndView mav = new ModelAndView("hwCertform");

        mav.addObject("productList", getProductList());
        mav.addObject("vendorList", getVendorList());
        mav.addObject("archList", getArchList());
        mav.addObject("modelList", getModelList());
        mav.addObject("makeList", getMakeList());

        mav.addObject("specList", getSpecList());
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(final HwCert hwCert, final BindingResult errors,
            final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        if (request.getParameter("cancel") != null)
            return getCancelView();

        if (validator != null) { // validator is null during testing
            validator.validate(hwCert, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null)
                return "hwCertform";
        }

        log.debug("entering 'onSubmit' method...");

        final boolean isNew = hwCert.getId() == null;
        String success = getSuccessView();
        final Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            hwCertManager.remove(hwCert.getId());
            saveMessage(request, getText("hwCert.deleted", locale));
        } else {
            hwCertManager.save(hwCert);
            final String key = isNew ? "hwCert.added" : "hwCert.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:hwCertform?id=" + hwCert.getId();
            }
        }

        return success;
    }
}
