<%@ include file="/common/taglibs.jsp"%>

<menu:useMenuDisplayer name="Velocity" config="navbarMenu.vm" permissions="rolesAdapter">
<div class="collapse navbar-collapse" id="navbar">
<ul class="nav navbar-nav">
    <c:if test="${empty pageContext.request.remoteUser}">
        <li class="active">
            <a href="<c:url value="/login"/>"><fmt:message key="login.title"/></a>
        </li>
    </c:if>
    <menu:displayMenu name="Home"/>
    <menu:displayMenu name="UserMenu"/>
    <menu:displayMenu name="AdminMenu"/>
    <menu:displayMenu name="Logout"/>
    
    
    
    
    
    
    <!--HwModel-START-->
    <menu:displayMenu name="HwModelMenu"/>
    <!--HwModel-END-->
    
    <!--HwMake-START-->
    <menu:displayMenu name="HwMakeMenu"/>
    <!--HwMake-END-->
    <!--HwArch-START-->
    <menu:displayMenu name="HwArchMenu"/>
    <!--HwArch-END-->
    <!--HwSpec-START-->
    <menu:displayMenu name="HwSpecMenu"/>
    <!--HwSpec-END-->
    <!--Vendor-START-->
    <menu:displayMenu name="VendorMenu"/>
    <!--Vendor-END-->
    <!--Product-START-->
    <menu:displayMenu name="ProductMenu"/>
    <!--Product-END-->
</ul>
</div>
</menu:useMenuDisplayer>
