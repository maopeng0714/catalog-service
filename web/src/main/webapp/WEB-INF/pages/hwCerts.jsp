<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="hwCertList.title"/></title>
    <meta name="menu" content="HwCertMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="col-sm-10">
    <h2><fmt:message key="hwCertList.heading"/></h2>

    <form method="get" action="${ctx}/hwCerts" id="searchForm" class="form-inline">
    <div id="search" class="text-right">
        <span class="col-sm-9">
            <input type="text" size="20" name="q" id="query" value="${param.q}"
                   placeholder="<fmt:message key="search.enterTerms"/>" class="form-control input-sm"/>
        </span>
        <button id="button.search" class="btn btn-default" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="hwCertList.message"/>

    <div id="actions" class="btn-group">
        <a href='<c:url value="/hwCertform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="hwCertList" class="table table-condensed table-striped table-hover" requestURI="" id="hwCertList" export="true" pagesize="25">
    <display:column property="name" sortable="true" href="hwCertform" media="html"
        paramId="id" paramProperty="id" titleKey="hwCert.name"/>
    <display:column property="name" media="csv excel xml pdf" titleKey="hwCert.id"/>
    <display:column sortProperty="public" sortable="true" titleKey="hwCert.public">
        <input type="checkbox" disabled="disabled" <c:if test="${hwCertList.public}">checked="checked"</c:if>/>
    </display:column>
	<display:column property="product.name" sortable="true" titleKey="Product"/>
	<display:column property="model.name" sortable="true" titleKey="Model"/>
	<display:column property="arch.name" sortable="true" titleKey="Architecture"/>
	<display:column property="make.name" sortable="true" titleKey="Make"/>
	<display:column property="vendor.name" sortable="true" titleKey="Vendor"/>
	<display:column property="spec.name" sortable="true" titleKey="Specification"/>
	
    <display:setProperty name="paging.banner.item_name"><fmt:message key="hwCertList.hwCert"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="hwCertList.hwCerts"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="hwCertList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="hwCertList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="hwCertList.title"/>.pdf</display:setProperty>
</display:table>
