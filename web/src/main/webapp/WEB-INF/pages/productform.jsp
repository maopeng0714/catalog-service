<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="productDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='productDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="productList.product"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h2><fmt:message key="productDetail.heading"/></h2>
    <fmt:message key="productDetail.message"/>
</div>

<div class="col-sm-7">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="product" method="post" action="productform" cssClass="well"
           id="productForm" onsubmit="return validateProduct(this)">
<form:hidden path="id"/>
    <spring:bind path="product.majorVersion">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="product.majorVersion" styleClass="control-label"/>
        <form:input cssClass="form-control" path="majorVersion" id="majorVersion"  maxlength="255"/>
        <form:errors path="majorVersion" cssClass="help-block"/>
    </div>
    <spring:bind path="product.minorVersion">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="product.minorVersion" styleClass="control-label"/>
        <form:input cssClass="form-control" path="minorVersion" id="minorVersion"  maxlength="255"/>
        <form:errors path="minorVersion" cssClass="help-block"/>
    </div>
    <spring:bind path="product.name">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="product.name" styleClass="control-label"/>
        <form:input cssClass="form-control" path="name" id="name"  maxlength="80"/>
        <form:errors path="name" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty product.id}">
            <button type="submit" class="btn btn-warning" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
                <i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>
            </button>
        </c:if>

        <button type="submit" class="btn btn-default" name="cancel" onclick="bCancel=true">
            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
        </button>
    </div>
</form:form>
</div>

<v:javascript formName="product" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['productForm']).focus();
    });
</script>
