<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>My-Shop | Content ${tbContent.id == null ? "Add New" : "Eidt"}</title>

    <jsp:include page="../includes/header.jsp" />

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <jsp:include page="../includes/nav.jsp" />
    <jsp:include page="../includes/menu.jsp" />

    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                ContentCategory Management
                <small>Control panel</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="#">Content Management</a></li>
                <li class="active">Content Add SubCategories</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <!-- Horizontal Form -->
                    <div class="box box-info">
                        <div class="box-header with-border text-center">
                            <h3 class="box-title">Add SubCategories</h3>
                        </div>
                        <!-- /.box-header -->
                        <!-- form start -->
                        <form:form id="inputForm" cssClass="form-horizontal" action="/content/category/save" method="post" modelAttribute="tbContentCategory">
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="name" class="col-sm-3 control-label">ParentTitle</label>

                                    <div class="col-sm-6">
                                        <form:hidden path="parentId" value="${tbContentCategory.id}" />
                                        <input type="text" class="form-control" value="${tbContentCategory.name}" placeholder="Select ParentTitle" readonly="true" />
                                        <%--<form:input path="title" cssClass="form-control required" placeholder="Select Title" readonly="true" data-toggle="modal" data-target="#modal-default"/>--%>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="name" class="col-sm-3 control-label">Name</label>
                                    <%--<label for="inputPhone" class="col-sm-3 control-label">Phone</label>--%>

                                    <div class="col-sm-6">
                                        <input class="form-control required" id="name" name="name" placeholder="Enter Name" />
                                        <%--<input type="text" class="form-control" id="inputPhone" name="phone" placeholder="Enter phone">--%>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label id="sortOrder" class="col-sm-3 control-label">SotrOrder</label>
                                        <%--<label for="inputPhone" class="col-sm-3 control-label">Phone</label>--%>

                                    <div class="col-sm-6">
                                        <input class="form-control required" id="sortOrder" name="sortOrder" placeholder="Enter SotrOrder" />
                                            <%--<input type="text" class="form-control" id="inputPhone" name="phone" placeholder="Enter phone">--%>
                                    </div>
                                </div>
                                <c:if test="${baseResult != null}">
                                    <div class="col-sm-6 col-sm-offset-3">
                                        <div class="alert alert-${baseResult.status == 200 ? "success" : "danger"} alert-dismissible">
                                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                            <h5>${baseResult.message}</h5>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="button" class="btn btn-default btn-sm" onclick="history.go(-1)">&nbsp; Back &nbsp;</button>
                                <button type="submit" class="btn btn-info btn-sm pull-right">Submit</button>
                            </div>
                            <!-- /.box-footer -->

                        </form:form>
                    </div>
                    <!-- /.box -->
                </div>
            </div>
        </section>
        <!-- /.context -->
    </div>

    <jsp:include page="../includes/copyright.jsp" />
<jsp:include page="../includes/footer.jsp" />

<script>



</script>

</body>
</html>