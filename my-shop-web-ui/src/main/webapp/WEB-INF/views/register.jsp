<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>MyShop——个人注册</title>
    <link rel="stylesheet" type="text/css" href="/static/css/index.css">
    <link rel="stylesheet" type="text/css" href="/static/css/ziy.css">
    <!--  <script src="js/jquery-1.11.3.min.js" ></script>
    <script src="js/index.js" ></script>  -->
    <!-- <script type="text/javascript" src="js/jquery1.42.min.js"></script> -->
    <!--
    <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
     <script type="text/javascript" src="js/jquery.SuperSlide.2.1.1.source.js"></script> -->

</head>
<body>
<!--dengl-->
<div class="yiny">
    <div class="beij_center">
        <div class="dengl_logo">
            <img src="/static/images/logo_1.png">
            <h1>欢迎注册</h1>
        </div>
    </div>
</div>
<div class="beij_center">
    <div class="ger_zhuc_beij">
        <div class="ger_zhuc_biaot">
            <ul>
                <li class="ger_border_color"><a href="zhuc.html">个人注册</a></li>
                <i>丨</i>
                <li><a href="shenq_ruz.html">申请入驻</a></li>
                <p>我已经注册，现在就<a class="ftx-05 ml10" href="dengl.html">登录</a></p>
            </ul>
        </div>
        <form id="inputForm" action="/register" method="post">
            <div class="zhuc_biaod" >
                <div class="reg-items" ${baseResult.status != 500 ? "style='display:none;'" : "style='padding-bottom: 0px !important;'"} >
                    <h3 style="color: red;font-weight: bold;">${baseResult.message}</h3>
                </div>
                <div class="reg-items">
              	<span class="reg-label">
                	<label for="email">邮箱：</label>
              	</span>
                    <input class="i-text required email" name="email" id="email" type="text">
                </div>
                <div class="reg-items">
              	<span class="reg-label">
                	<label for="password">设置密码：</label>
              	</span>
                    <input type="password" class="i-text required" name="password" id="password">
                </div>
                <div class="reg-items">
              	<span class="reg-label">
                	<label for="password2">确认密码：</label>
              	</span>
                    <input type="password" class="i-text required" name="password2" id="password2">
                </div>
                <div class="reg-items">
              	<span class="reg-label">
                	<label for="verificationInput">验证码：</label>
              	</span>
                    <input type="text" class="i-text i-short required" name="verification" id="verificationInput"
                           style="width: 150px;">
                    <img id="verification" src="/verification"
                         style="cursor: pointer;position: absolute;padding-left: 27px;" title="看不清？换一张"/>
                </div>
                <div class="reg-items">
              	<span class="reg-label">
                	<label for="check_box"> </label>
              	</span>
                    <div class="dag_biaod">
                        <input type="checkbox" class="required" name="check_box" id="check_box">
                        阅读并同意
                        <a href="#" class="ftx-05 ml10">《MyShop用户注册协议》</a>
                        <a href="#" class="ftx-05 ml10">《隐私协议》</a>
                    </div>
                </div>
                <div class="reg-items">
              	<span class="reg-label">
                	<label for="regs"> </label>
              	</span>
                    <input class="reg-btn" id="regs" value="立即注册" type="submit">
                </div>
            </div>
        </form>
        <div class="xiaogg">
            <img src="/static/images/cdsgfd.jpg">
        </div>
    </div>
</div>


<div class="jianj_dib jianj_dib_1">
    <div class="beia_hao">
        <p>京ICP备：123456789号 </p>
        <p class="gonga_bei">京公网安备：123456789号</p>
    </div>
</div>

<!-- Jquery 3 -->
<script src="/static/js/jquery.min.js"></script>
<!-- jQuery Validation 1.14.0 -->
<script src="/static/plugins/jquery-validation/js/jquery.validate.js"></script>
<script src="/static/plugins/jquery-validation/js/additional-methods.js"></script>
<script src="/static/plugins/jquery-validation/js/localization/messages_zh.js"></script>

<script>

    $(function () {
        $("#inputForm").validate({
            errorElement: 'span',
            errorClass: 'help-block',
            errorPlacement: function (error, element) {
                if (element.is(':radio') || element.is(':checkbox')) { //如果是radio或checkbox
                    var eid = element.attr('name'); //獲取元素的name屬性
                    error.appendTo(element.parent()); //將錯誤信息添加當前元素的父結點後面
                } else {
                    element.parent().attr("class", "reg-items has-error");
                    error.insertAfter(element);
                }
            }
        });

        // 刷新验证码
        $("#verification").bind("click", function () {
            $(this).hide().attr('src', '/verification?random=' + Math.random()).fadeIn();
        });

    });

</script>


</body>
</html>
