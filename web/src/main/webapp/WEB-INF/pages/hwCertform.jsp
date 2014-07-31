<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="hwCertDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='hwCertDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="hwCertList.hwCert"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h2><fmt:message key="hwCertDetail.heading"/></h2>
    <fmt:message key="hwCertDetail.message"/>
</div>

<div class="col-sm-7">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="hwCert" method="post" action="hwCertform" cssClass="well"
           id="hwCertForm" onsubmit="return validateHwCert(this)">
<form:hidden path="id"/>
    <spring:bind path="hwCert.name">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="hwCert.name" styleClass="control-label"/>
        <form:input cssClass="form-control" path="name" id="name"  maxlength="30"/>
        <form:errors path="name" cssClass="help-block"/>
    </div>
    
    <form:select cssClass="form-control" path="product" items="productList" itemLabel="label" itemValue="value"/>
    <spring:bind path="hwCert.public">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="hwCert.public" styleClass="control-label"/>
        <form:checkbox path="public" id="public" cssClass="checkbox"/>
        <form:errors path="public" cssClass="help-block"/>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="vendor" items="vendorList" itemLabel="label" itemValue="value"/> 
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="arch" items="archList" itemLabel="label" itemValue="value"/> 
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="make" items="makeList" itemLabel="label" itemValue="value"/> 
    <!-- todo: change this to read the identifier field from the other pojo -->
   <form:select cssClass="form-control" path="model" items="modelList" itemLabel="label" itemValue="value"/> 
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="spec" items="specList" itemLabel="label" itemValue="value"/> 

    <div class="form-group">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty hwCert.id}">
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

<v:javascript formName="hwCert" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['hwCertForm']).focus();
    });
</script>
