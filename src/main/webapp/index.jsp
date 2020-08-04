<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <link rel="shortcut icon" href="Image/favicon.ico">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no, minimal-ui">
    <title>Home</title>
    <!-- FontAwesome -->
    <link href="font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <!-- Twitter Bootstrap -->
    <link rel="stylesheet" href="bootstrap-4.5.0-dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="CSS/IndexTable/default.css">
    <link rel="stylesheet" href="CSS/IndexTable/bootstrap-vertical-menu.css">
    <script src="jQuery/jquery-3.5.1.js" type="text/javascript"></script>
</head>
<body>
<nav class="navbar navbar-vertical-left">
    <ul class="nav navbar-nav">
        <li>
            <a href>
                <i class="fa fa-fw fa-lg fa-home"></i>
                <span>Menu 1</span>
            </a>
        </li>
        <li>
            <a href="listFileServlet">
                <i class="fa fa-fw fa-lg fa-download "></i>
                <span>Menu 2</span>
            </a>
        </li>
        <li>
            <a href>
                <i class="fa fa-fw fa-lg fa-comments-o"></i>
                <span>Menu 3</span>
            </a>
        </li>
        <li>
            <a href>
                <i class="fa fa-fw fa-lg fa-desktop"></i>
                <span>Menu 4</span>
            </a>
        </li>
        <li>
            <a href>
                <i class="fa fa-fw fa-lg fa-tablet"></i>
                <span>Menu 5</span>
            </a>
        </li>
        <li id="Upload">
            <a href="upload.jsp">
                <i class="fa fa-fw fa-lg fa-laptop"></i>
                <span>Menu 6</span>
            </a>
        </li>
    </ul>
</nav>
<div class="htmleaf-container">
    <header class="htmleaf-header">
        <h1>欢迎进入徐佳伟的个人空间 <span>Welcome to JarvisHsu's personal space</span></h1>
        <div class="htmleaf-links">
            <a class="htmleaf-icon icon-htmleaf-home-outline" href="#" title="Home"
               target=""><span> Home</span></a>
            <a class="htmleaf-icon icon-htmleaf-arrow-forward-outline"
               href="#" title="LogOut" target=""><span> LogOut</span></a>
        </div>
    </header>
    <div class="htmleaf-content bgcolor-8">
        <h2>Bootstrap</h2>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quasi cum quisquam, libero molestiae cupiditate,
            omnis? Provident excepturi, nobis cupiditate, nulla dolorem dignissimos error necessitatibus, sint
            praesentium dolores, harum! Aliquam sequi impedit corrupti, numquam maiores ipsa commodi excepturi eligendi
            cupiditate adipisci atque sint iste aspernatur, hic sed modi at voluptatum nihil incidunt, fugiat
            repellendus beatae debitis, soluta blanditiis culpa! Magnam quisquam tempore exercitationem molestias
            cupiditate quam aut eum perferendis deleniti doloribus et quos dolore excepturi, rem incidunt consectetur
            quia accusantium placeat quae officiis in ab. Veniam sapiente aut est asperiores nihil reprehenderit
            incidunt cupiditate, voluptatibus nemo iste. Quos, ullam. Cupiditate, sed.</p>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quasi cum quisquam, libero molestiae cupiditate,
            omnis? Provident excepturi, nobis cupiditate, nulla dolorem dignissimos error necessitatibus, sint
            praesentium dolores, harum! Aliquam sequi impedit corrupti, numquam maiores ipsa commodi excepturi eligendi
            cupiditate adipisci atque sint iste aspernatur, hic sed modi at voluptatum nihil incidunt, fugiat
            repellendus beatae debitis, soluta blanditiis culpa! Magnam quisquam tempore exercitationem molestias
            cupiditate quam aut eum perferendis deleniti doloribus et quos dolore excepturi, rem incidunt consectetur
            quia accusantium placeat quae officiis in ab. Veniam sapiente aut est asperiores nihil reprehenderit
            incidunt cupiditate, voluptatibus nemo iste. Quos, ullam. Cupiditate, sed.</p>
    </div>
    <div class="related" hidden disabled="true">

        <h3>如果你喜欢这个插件，那么你可能也喜欢:</h3>
        <a href="#">
            <img src="Image/online_campaign_monochromatic.svg" width="280px" alt="时尚响应式侧边栏布局和切换页面动画特效"/>
            <h3>时尚响应式侧边栏布局和切换页面动画特效</h3>
        </a>
        <a href="#">
            <img src="Image/online_campaign_monochromatic.svg" width="280px" alt="谷歌Material design风格隐藏侧边栏特效"/>
            <h3>谷歌Material design风格隐藏侧边栏特效</h3>
        </a>
    </div>
<%--    占位图--%>
    <div class="related">

        <img src="Image/1920_358.png" alt="">
    </div>
</div>
</body>
<script>
</script>
</html>