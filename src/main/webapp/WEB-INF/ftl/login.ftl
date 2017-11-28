<!DOCTYPE html>
<html>
<head>
    <title>图片服务系统登录</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <link rel="stylesheet" href="http://s.belle.net.cn/bs3/1.0.0/css/app.css" type="text/css" />
    <link rel="stylesheet" href="http://s.belle.net.cn/bs3/1.0.0/css/base.css" type="text/css" />
    <link rel="stylesheet" href="http://s.belle.net.cn/bs3/1.0.0/js/datetimepicker/datetimepicker.min.css" type="text/css" />
    <link rel="stylesheet" href="http://s.belle.net.cn/bs3/1.0.0/js/table/bootstrap-table.min.css" type="text/css" />
    <link rel="stylesheet" href="http://s.belle.net.cn/bs3/1.0.0/js/table/bootstrap-table.extend.css" type="text/css" />
</head>
<style>

    body {
        padding-top: 40px;
        padding-bottom: 40px;
    }

    .form-signin {
        max-width: 330px;
        padding: 15px;
        margin: 0 auto;
    }
    .form-signin .form-signin-heading,
    .form-signin .checkbox {
        margin-bottom: 10px;
    }
    .form-signin .checkbox {
        font-weight: normal;
    }
    .form-signin .form-control {
        position: relative;
        height: auto;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
        padding: 10px;
        font-size: 16px;
    }
    .form-signin .form-control:focus {
        z-index: 2;
    }
    .form-signin input[type="email"] {
        margin-bottom: -1px;
        border-bottom-right-radius: 0;
        border-bottom-left-radius: 0;
    }
    .form-signin input[type="password"] {
        margin-bottom: 10px;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
    }
</style>
<body >

    <div class="container">
        <form class="form-signin" action="/uploadPicture/uploadPicture" method="post">
            <h2 class="form-signin-heading">图片服务系统</h2>
            <label for="inputEmail" class="sr-only">用户名</label>
            <input type="text" id="username" name="username" class="form-control" placeholder="用户名" required autofocus>

            <label for="inputPassword" class="sr-only">密码</label>
            <input type="password" id="inputPassword" name="password" class="form-control" placeholder="密码" required>

            <button class="btn btn-lg btn-primary btn-block" type="submit">登陆</button>
        </form>
    </div>
</body>
</html>