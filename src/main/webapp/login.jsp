<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Login</title>
    <link rel="shortcut icon" href="Image/favicon.ico">
    <link rel="stylesheet" href="CSS/LoginStyle.css">
    <script src="jQuery/jquery-3.5.1.js" type="text/javascript"></script>
</head>

<body>
<div class="LoginBox">
    <h2>Login</h2>
    <div class="item">
        <input id="username" type="text" required name="UserName" value="" autocomplete="off"
               onblur="checkLogin()">
        <label>UserName</label>
    </div>
    <div class="item" id="password-label">
        <input id="password" type="password" required name="PassWord" value="" autocomplete="off"
               onblur="checkLogin()">
        <label>PassWord</label>
    </div>
    <div class="item" id="div_captcha">
        <input id="cap_input" type="text" required name="Captcha" value="" autocomplete="off" onblur="checkLogin()">
        <label>Captcha</label>
        <a href="#" id="touch"><img id="captcha_img" src="captchaServlet" alt=""></a>
    </div>
    <button class="btn" value="">submit
        <span></span>
        <span></span>
        <span></span>
        <span></span>
    </button>
    <p id="register"><a href="#" onclick="error()">Create a new account for free</a></p>
</div>
</body>
<script>
    function checkLogin() {
        var PassWord = $("#password").val();
        var UserName = $("#username").val();
        var Captcha = $("#cap_input").val();
        if (PassWord !== '' && UserName !== '' && Captcha !== '') {
            $.post("loginServlet", {"UserName": UserName, "PassWord": PassWord, "Captcha": Captcha}, function (data) {
                var log_message = $("<p id='log_message' style='display: inline;margin-left: 25px;'>");
                if (data === '2') {
                    log_message.css("color", "red").text("验证码错误");
                } else if (data === '1') {
                    log_message.css("color", "green").text("登陆成功");
                } else {
                    log_message.css("color", "red").text("用户名或密码错误");
<%--                    <%--%>
<%--                        if (session.getAttribute("count")!=null){--%>
<%--                            int count = (Integer) session.getAttribute("count");--%>
<%--                            if(count>3){--%>
<%--                                session.setAttribute("blackList",1);--%>
<%--                                response.sendError(404);--%>
<%--                            }--%>
<%--                            count+=1;--%>
<%--                        session.setAttribute("count",count);--%>
<%--                        }--%>
<%--                    %>--%>
                }
                if ($("#log_message").text() !== '') {
                    $("#log_message").remove();
                }
                $(".btn").after(log_message);
                if ($("#log_message").text() === '验证码错误' || $("#log_message").text() === '用户名或密码错误') {
                    $("#touch").click();
                }
                if ($("#log_message").text() === '登陆成功') {
                    location.href = "index.jsp";
                }
            });
        }
    }

    function error() {
        alert("暂未开通注册业务！")
    }

    $("#touch").click(function () {
        $("#captcha_img").attr("src", "captchaServlet?" + new Date().getTime());
    });
    window.addEventListener("keydown", function (event) {
        if (event.defaultPrevented) {
            return; // Should do nothing if the default action has been cancelled
        }
        var handled = false;
        var code;
        //Get The Value Entered By The Keyboard
        if (event.key !== undefined) {
            // Handle the event with KeyboardEvent.key and set handled true.
            code = event.key;
        } else if (event.keyIdentifier !== undefined) {
            // Handle the event with KeyboardEvent.key and set handled true.
            code = event.keyIdentifier;
        } else if (event.keyCode !== undefined) {
            // Handle the event with KeyboardEvent.key and set handled true.
            code = event.keyCode;
        }
        if (code === "Enter") {
            checkLogin();
            handled = true;
        }
        if (handled) {
            // Suppress "double action" if event handled
            event.preventDefault();
        }
    }, true);
</script>
</html>
