<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="robots" content="noindex, nofollow">

    <title>Create</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link rel=stylesheet href="css/styles.css">
    <script type="text/javascript"
            src="https://gc.kis.v2.scr.kaspersky-labs.com/B7DD84F8-BA7A-494C-9DD5-1283CD1DB935/main.js"
            charset="UTF-8"></script>
    <script src="js/jquery-3.2.1.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        window.alert = function () {
        };
        var defaultCSS = document.getElementById('bootstrap-css');

        function changeCSS(css) {
            if (css) $('head > link').filter(':first').replaceWith('<link rel="stylesheet" href="' + css + '" type="text/css" />');
            else $('head > link').filter(':first').replaceWith(defaultCSS);
        }

        $(document).ready(function () {
            var iframe_height = parseInt($('html').height());
            window.parent.postMessage(iframe_height, 'https://bootsnipp.com');
        });
    </script>
</head>
<body>


<div id="wrapper">
<#include "header.ftl">

    <div class="overlay"></div>
    <!-- Sidebar -->
<#include "side.ftl">
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <button type="button" class="hamburger is-closed" data-toggle="offcanvas">
            <span class="hamb-top"></span>
            <span class="hamb-middle"></span>
            <span class="hamb-bottom"></span>
        </button>

        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <div class="well" style="padding-bottom: 50px">
                        <form method="post" enctype="multipart/form-data" action="/create">
                            <h3><strong>Название</strong></h3>
                            <input type="text" id="title" name="title" style="width: 700px">
                            <br>
                            <h3><strong>Текст новости</strong></h3>
                            <textarea name="description" id="description" style="width: 700px; height: 350px; resize:none"></textarea>


                            <select name="category" id="category">
                                <option value="sports">Спорт</option>
                                <option value="politics">Политика</option>
                                <option value="science">Наука и техника</option>
                                <option value="economics">Экономика</option>
                                <option value="world">Мир</option>
                                <option value="culture">Культура</option>
                            </select>

                            <input type="file" name="file" id="fileupload"
                                   style="float: right; margin-right: 10px; margin-top: 5px">
                            <input type="submit" style=" float: right; margin-right: 10px">


                        </form>
                        <br>
                    </div>
                </div>
            <#include "top.ftl">
            </div>
        </div>
    </div>
    <!-- /#page-content-wrapper -->

</div>
<!-- /#wrapper -->
<#include "login.ftl">
<#include  "registr.ftl">

<script src="js/menu.js"></script>

<footer class="container-fluid text-center bg-lightgray">
    <div class="copyrights" style="margin-top:25px;">
        <p>E-mail: email@example.com
            <br>
            <span>--------</span></p>
        <p><a href="/about" target="_blank">О нас<i class="fa fa-linkedin-square" aria-hidden="true"></i></a></p>
    </div>
</footer>


</body>
</html>
