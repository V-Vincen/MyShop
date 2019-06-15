<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>My-Shop | Content ${tbContent.id == null ? "Add New" : "Eidt"}</title>

    <jsp:include page="../includes/header.jsp" />

    <!-- dropzone--5.5.0 -->
    <link rel="stylesheet" href="/static/assets/plugins/dropzone/dist/min/dropzone.min.css" />
    <link rel="stylesheet" href="/static/assets/plugins/dropzone/dist/basic.css" />

    <!-- wangEditor--3.1.1 -->
    <link rel="stylesheet" href="/static/assets/plugins/wangEditor/wangEditor.min.css" />

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <jsp:include page="../includes/nav.jsp" />
    <jsp:include page="../includes/menu.jsp" />

    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Content Management
                <small>Control panel</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="#">Content Management</a></li>
                <li class="active">Content ${tbContent.id == null ? "Add New" : "Eidt"}</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <!-- Horizontal Form -->
                    <div class="box box-info">
                        <div class="box-header with-border text-center">
                            <h3 class="box-title">${tbContent.id == null ? "Add New" : "Eidt"}</h3>
                        </div>
                        <!-- /.box-header -->
                        <!-- form start -->
                        <form:form id="inputForm" cssClass="form-horizontal" action="/content/save" method="post" modelAttribute="tbContent">
                            <form:hidden path="id"/>
                            <div class="col-sm-12">
                            <c:if test="${baseResult != null}">
                                <div class="col-sm-8 col-sm-offset-2">
                                    <div class="alert alert-${baseResult.status == 200 ? "success" : "danger"} alert-dismissible">
                                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                        <h5>${baseResult.message} !!!</h5>
                                    </div>
                                </div>
                            </c:if>
                            </div>
                            <div class="box-body">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">ParentTitle</label>

                                    <div class="col-sm-8">
                                        <form:hidden id="categoryId" path="categoryId"/>
                                        <input class="form-control required" id="categoryName" placeholder="Select ParentTitle" value="${tbContent.tbContentCategory.name}" readonly="true" data-toggle="modal" data-target="#modal-default"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="title" class="col-sm-2 control-label">Title</label>

                                    <div class="col-sm-8">
                                        <form:input path="title" cssClass="form-control required" placeholder="Enter Title" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="subTitle" class="col-sm-2 control-label">SubTitle</label>

                                    <div class="col-sm-8">
                                        <form:input path="subTitle" cssClass="form-control required" placeholder="Enter SubTitle" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="titleDesc" class="col-sm-2 control-label">TitleDesc</label>

                                    <div class="col-sm-8">
                                        <form:input path="titleDesc" cssClass="form-control required" placeholder="Enter titleDesc" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="url" class="col-sm-2 control-label">Url</label>

                                    <div class="col-sm-8">
                                        <form:input path="url" cssClass="form-control" placeholder="Enter Url" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="pic" class="col-sm-2 control-label">Pic</label>

                                    <div class="col-sm-8">
                                        <form:hidden path="pic" cssClass="form-control" placeholder="Enter Pic" />
                                        <div id="dropz" class="dropzone" style="border: 2px dashed #0087F7;border-radius: 5px;background: white;">
                                            <div class="dz-message needsclick">
                                                Drop files here or click to upload.
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="pic2" class="col-sm-2 control-label">Pic2</label>

                                    <div class="col-sm-8">
                                        <form:hidden path="pic2" cssClass="form-control" placeholder="Enter Pic2" />
                                        <div id="dropz2" class="dropzone" style="border: 2px dashed #0087F7;border-radius: 5px;background: white;">
                                            <div class="dz-message needsclick">
                                                Drop files here or click to upload.
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Content</label>

                                    <div class="col-sm-8">
                                        <form:hidden path="content"/>
                                        <div id="editor">
                                            ${tbContent.content}
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="button" class="btn btn-default btn-sm" onclick="history.go(-1)">&nbsp; Back &nbsp;</button>
                                <button type="submit" id="submitWEditor" class="btn btn-info btn-sm pull-right">Submit</button>
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

        <div class="modal fade" id="modal-default">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span></button>
                        <h4 class="modal-title">ParentTitle Modal</h4>
                    </div>
                    <div class="modal-body">
                        <div id="jstree">

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary" id="btn-primary">Save changes</button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
        </div>

<jsp:include page="../includes/footer.jsp" />

<!-- jsTree - v3.3.8 -->
<link rel="stylesheet" href="/static/assets/plugins/jstree/dist/themes/default/style.min.css" />
<script src="/static/assets/plugins/jstree/dist/jstree.min.js"></script>

<!-- dropzone--5.5.0 -->
<script src="/static/assets/plugins/dropzone/dist/min/dropzone.min.js"></script>

<!-- wangEditor--3.1.1 -->
<script src="/static/assets/plugins/wangEditor/wangEditor.min.js"></script>

<script>
    /**
     * @Method:        jstree树形结构菜单
     * @Description:
     * @Param:
     * @return:
     * @Author:        Mr.Vincent
     * @Date:          2019/6/2
     */
    $(function() {
        $("#jstree").jstree({
            "core": {
                "multiple": false,//没有多选
                "themes": {
                    "responsive": false,
                    "name": false,//
                    "icons": true,//显示节点图标
                    "dots": true,//显示连接点
                    "stripes": true,
                    //"variant":true
                },
                // so that create works
                "check_callback": true,
                'data': function (obj, callback) {
                    var jsonstr = "[]";
                    var jsonarray = eval('(' + jsonstr + ')');
                    $.ajax({
                        type: "GET",
                        url: "/content/category/jstree/data",
                        dataType: "json",
                        async: false,
                        success: function (result) {
                            var arrays = result;
                            for (var i = 0; i < arrays.length; i++) {
                                //console.log(arrays[i].id)
                                var arr = {
                                    "id": arrays[i].id,
                                    "parent": arrays[i].parentId == 0 ? "#" : arrays[i].parentId,
                                    "text": arrays[i].name
                                }
                                jsonarray.push(arr);
                            }
                        }
                    });
                    callback.call(this, jsonarray);
                }
            },
            //types:可以为节点添加“类型”，这意味着可以轻松控制节点组的嵌套规则和图标，而不是单独控制。
            "types": {
                "default": {
                    "icon": "/static/assets/plugins/jstree/dist/themes/tree_icon.png"
                },
                "file": {
                    "icon": "glyphicon glyphicon-file"
                },
                "demo" : {
                    "icon" : "glyphicon glyphicon-ok"
                }
            },
            //"state": {"key": "state_demo"},//如果你在同一个域中有多个树，则密钥很重要和"plugins" : ["state"]连用
            //dnd:拖放重新排列
            //state:在用户的浏览器中保存所有打开和选定的节点，因此当返回到同一个树时，将恢复先前的状态。
            //"plugins": ["dnd", "state", "types", "checkbox", "wholerow"]
            "plugins": ["types"/*,"wholerow"*/]
        });
    });

    var categoryName ;
    var categoryId ;

    $('#jstree').on("select_node.jstree", function (e, data) {
        $('#jstree').jstree();

        if (data.node != undefined) {
            categoryName = data.node.text;
            categoryId = data.node.id;
        }
    });
    $("#btn-primary").click(function () {
        if(categoryName != null){
            $("#categoryName").val(categoryName);
            $("#categoryId").val(categoryId);
            $("#modal-default").modal('hide');
        }else {
            swal("Select the ParentTitle !!!")
        }
    })

//=======================================================这个写法还有问题，有待改进==============================================================//
    //jsTree - v3.3.8 延迟加载ajax的方法，后天传到前台参数格式与github上的文档例子一样，但是页面不显示树状图有待查找问题
    // $(function() {
    //     $('#jstree').jstree({
    //         'core' : {
    //             "multiple": false,//没有多选
    //             "themes": {
    //                 "responsive": false,
    //                 "name": false,//
    //                 "icons": true,//显示节点图标
    //                 "dots": true,//显示连接点
    //                 "stripes": true,
    //                 //"variant":true
    //             },
    //             'data' : {
    //                 // 'url' : function(node) {
    //                 //     return '/content/category/jstree/data/id?lazy';
    //                 // },
    //                 "url" : "/content/category/jstree/data/id?lazy",
    //                 "data" : function(node) {
    //                     return { "id" : node.id };
    //
    //                 }
    //             },
    //             "types": {
    //                 "default": {
    //                     "icon": "/static/assets/plugins/jstree/dist/themes/tree_icon.png"
    //                 },
    //                 "file": {
    //                     "icon": "glyphicon glyphicon-file"
    //                 }
    //             },
    //             "state": {"key": "demo2"},
    //             //dnd:拖放重新排列
    //             //state:在用户的浏览器中保存所有打开和选定的节点，因此当返回到同一个树时，将恢复先前的状态。
    //             //"plugins": ["dnd", "state", "types", "checkbox", "wholerow"]
    //             "plugins": ["types"/*,"wholerow"*/]
    //         }
    //     });
    // });
//=======================================================这个写法还有问题，有待改进==============================================================//

    /**
     * @Method:        图片上传(dropzone插件)
     * @Description:
     * @Param:
     * @return:
     * @Author:        Mr.Vincent
     * @Date:          2019/6/2
     */
    App.initDropzone({
        id:"#dropz",
        url: "/upload",
        init: function () {
            this.on("success", function (file, data) {
                // 上传成功触发的事件
                $("#pic").val(data.fileName)
            });
        }
    });
    App.initDropzone({
        id:"#dropz2",
        url: "/upload",
        init: function () {
            this.on("success", function (file, data) {
                // 上传成功触发的事件
                $("#pic2").val(data.fileName)
            });
        }
    });

    /**
     * @Method:
     * @Description:    富文本编辑器（wangEditor-3.1.1插件）
     * @Param:
     * @return:
     * @Author:        Mr.Vincent
     * @Date:          2019/6/3
     */
    $(function () {
        var E = window.wangEditor;
        var editor = new E('#editor');
        // 配置服务器端地址
        editor.customConfig.uploadImgServer = '/upload';
        editor.customConfig.uploadFileName = 'editorFile';
        editor.create();
        $("#submitWEditor").click(function () {
            $("#content").val(editor.txt.html());
        })
    })

</script>



</body>
</html>