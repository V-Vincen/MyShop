var $table;
var _table;

$(function (){
    $table = $('#table_content');

    _table = $table.dataTable(
        $.extend(true,{},CONSTANT.DATA_TABLES.DEFAULT_OPTION, {
        ajax : function(data, callback, settings) {//ajax配置为function,手动调用异步查询
            //封装请求参数
            var param = contentManage.getQueryCondition(data);
            $.ajax({
                type: "GET",
                url: "/content/selectAll",
                cache : false,  //禁用缓存
                data: param,    //传入已封装的参数
                dataType: "json",
                success: function(result) {
                    //setTimeout仅为测试延迟效果
                    setTimeout(function(){
                        //异常判断与处理
                        if (result.errorCode) {
                            $.dialog.alert("查询失败。错误码："+result.errorCode);
                            return;
                        }

                        //封装返回数据，这里仅演示了修改属性名
                        var returnData = {};
                        returnData.draw = data.draw;//这里直接自行返回了draw计数器,应该由后台返回
                        returnData.recordsTotal = result.total;
                        returnData.recordsFiltered = result.total;//后台不实现过滤功能，每次查询均视作全部结果
                        returnData.data = result.rows;

                        //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
                        //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
                        callback(returnData);
                    },200);
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    swal("Loading fail !!!");
                }
            });
        },
        columns: [
            CONSTANT.DATA_TABLES.COLUMN.CHECKBOX,
            // {
            //     "data": function (row, type, val, meta) {
            //         return '<input id="' + row.id + '" type="checkbox" class="minimal" />';
            //     }
            // },
            {"data": "id"},
            {"data": "tbContentCategory.name"},
            {"data": "title"},
            {"data": "subTitle"},
            {"data": "titleDesc"},
            {
                "data": function (row, type, val, meta) {
                    if(row.url == null || row.url == ''){
                        return  '';
                    }
                    return '<a href="' + row.url + '" target="_blank">查看</a>';
                }
            },
            {
                // className : "ellipsis",	//文字过长时用省略号显示，CSS实现
                // "data": "pic",
                // render : CONSTANT.DATA_TABLES.RENDER.ELLIPSIS
                "data": function (row, type, val, meta) {
                    if(row.pic == null || row.pic == ''){
                        return  '';
                    }
                    return '<a href="' + row.pic + '" target="_blank">查看</a>';
                }

            },
            {
                "data": function (row, type, val, meta) {
                    if(row.pic2 == null || row.pic2 == ''){
                        return  '';
                    }
                    return '<a href="' + row.pic2 + '" target="_blank">查看</a>';
                }
            },
            {
                "data": function (row, type, val, meta) {
                    return DateTime.format(row.updated,"yyyy-MM-dd HH:mm:ss");
                }
            },
            /*{"data": "content"},*/
            {
                className : "ellipsis operation",	//文字过长时用省略号显示，CSS实现
                data: "",
                render : CONSTANT.DATA_TABLES.RENDER.ELLIPSIS
            }
        ],
        "createdRow": function ( row, data, dataIndex ) {
            /*var $btnInfo = $('<button type="button" class="btn btn-info btn-sm" id="showDetail" style="width: 100px;"><i class="fa fa-search"></i>&nbsp Info</button>');*/
            var $btnEdit = $('<a href="/content/form?id='+data.id+'" class="btn btn-primary btn-sm" id="" style="width: 100px;" ><i class="fa fa-edit"></i>&nbsp Edit</a>');
            var $btnDel = $('<button type="button" class="btn btn-danger btn-sm btn-del" style="width: 100px;" ><i class="fa fa-trash-o"></i>&nbsp Delete</button>');
            $('td', row).eq(10)./*append($btnInfo).*/append($btnEdit).append($btnDel);
        },
        "drawCallback": function( settings, json) {
            //渲染完毕后的回调
            //清空全选状态
            $(":checkbox[class='minimal icheck_master']").prop('checked', false);
            //默认选中第一行
            $("tbody tr",$table).eq(0).click();
            App.init();
        }
    })).api();//此处需调用api()方法,否则返回的是JQuery对象而不是DataTables的API对象




    //隐藏和显示高级查询
    $("#toggle-advanced-search").click(function(){
        $("i",this).toggleClass("fa-angle-double-down fa-angle-double-up");
        $("#div-advanced-search").slideToggle("fast");
    });
    $("#btn-simple-search").click(function(){
        contentManage.fuzzySearch = true;

        //reload效果与draw(true)或者draw()类似,draw(false)则可在获取新数据的同时停留在当前页码,可自行试验
        //_table.ajax.reload();
        //_table.draw(false);
        _table.draw();
    });

    $("#btn-advanced-search").click(function(){
        contentManage.fuzzySearch = false;
        _table.draw();
    });

    // $table.on("change",":checkbox",function() {
    //     if ($(this).is("[name='cb-check-all']")) {
    //         //全选
    //         $(":checkbox",$table).prop("checked",$(this).prop("checked"));
    //     }else{
    //         //一般复选
    //         var checkbox = $("tbody :checkbox",$table);
    //         $(":checkbox[name='cb-check-all']",$table).prop('checked', checkbox.length == checkbox.filter(':checked').length);
    //     }
    // })
    //     .on("click",".td-checkbox",function(event) {
    //     //点击单元格即点击复选框
    //     !$(event.target).is(":checkbox") && $(":checkbox",this).trigger("click");
    // }).on("click",".btn-edit",function() {
    //     //点击编辑按钮
    //     var item = _table.row($(this).closest('tr')).data();
    //     $(this).closest('tr').addClass("active").siblings().removeClass("active");
    //     contentManage.currentItem = item;
    //     contentManage.editItemInit(item);
    // })
    //根据id删除
    $table.on("click",".btn-del",function() {
        //点击删除按钮
        var item = _table.row($(this).closest('tr')).data();
        contentManage.deleteItem([item]);

    });

    //批量删除
    $("#deleteMulti").click(function(){
        var arrItemId = [];
        $("tbody :checkbox:checked",$table).each(function(i) {
            var item = _table.row($(this).closest('tr')).data();
            arrItemId.push(item.id);
        });
        contentManage.deleteItem(arrItemId);
    });
});

var contentManage = {
    currentItem : null,
    fuzzySearch : true,
    getQueryCondition : function(data) {
        var param = {};
        //组装排序参数
        // if (data.order&&data.order.length&&data.order[0]) {
        //     switch (data.order[0].column) {
        //         case 1:
        //             param.orderColumn = "name";
        //             break;
        //         case 2:
        //             param.orderColumn = "subTitle";
        //             break;
        //         case 3:
        //             param.orderColumn = "status";
        //             break;
        //         case 4:
        //             param.orderColumn = "start_date";
        //             break;
        //         default:
        //             param.orderColumn = "name";
        //             break;
        //     }
        //     param.orderDir = data.order[0].dir;
        // }
        //组装查询参数
        param.fuzzySearch = contentManage.fuzzySearch;
        if (contentManage.fuzzySearch) {
            param.fuzzy = $("#fuzzy-search").val().trim();
        }else{
            param.title = $("#title-search").val().trim();
            param.subTitle = $("#subTitle-search").val().trim();
            param.titleDesc = $("#titleDesc-search").val().trim();
        }
        //组装分页参数
        param.page = Math.floor(data.start/ data.length) + 1 || 1,
        param.row = data.length;
        param.draw = data.draw;

        return param;
    },

    /**
     * @Method:
     * @Description:    删除和批量删除
     * @Param:
     * @return:
     * @Author:        Mr.Vincent
     * @Date:          2019/6/15
     */
    deleteItem: function (selectedItems) {
        if (selectedItems && selectedItems.length) {
            /**
             * @Method:
             * @Description:    根据id删除数据
             * @Param:
             * @return:
             * @Author:        Mr.Vincent
             * @Date:          2019/6/15
             */
            if (selectedItems.length == 1) {
                swal({
                    title: "Are you sure ?",
                    text: "Once deleted, you will not be able to recover this data !",
                    icon: "warning",
                    buttons: ["Cancel", "Yes, delete it !"],
                    dangerMode: true,
                })
                    .then((willDelete) => {
                        if (willDelete) {
                            $.post('/content/deleteByPrimaryKey', {id: selectedItems[0].id}, function (data) {
                                if (data.status == 200) {
                                    swal("Deleted !",
                                        "Your data have been deleted !", {
                                            icon: "success",
                                        });

                                } else {
                                    swal("Deleted fail !", "Something went wrong !", "error", {
                                        buttons: false,
                                        timer: 2000,
                                    });
                                }
                                _table.draw();
                            });
                        }
                    });

            }
            //批量删除
            else {
                swal({
                    title: "Are you sure ?",
                    text: "Once deleted, you will not be able to recover these data !",
                    icon: "warning",
                    buttons: ["Cancel", "Yes, delete it !"],
                    dangerMode: true,
                })
                    .then((willDelete) => {
                        if (willDelete) {
                            $.post('/content/deleteMulti', {ids: selectedItems.toString()}, function (data) {
                                if (data.status == 200) {
                                    swal("Deleted !",
                                        "Your " + data.message + " data have been deleted !", {
                                            icon: "success",
                                        });

                                } else {
                                    swal("Deleted " + data.message + " !", "Something went wrong !", "error", {
                                        buttons: false,
                                        timer: 2000,
                                    });
                                }
                                _table.draw();
                            });
                        }
                    });
            }
        } else {
            swal("First select the checkbox !");
        }
    }
};
