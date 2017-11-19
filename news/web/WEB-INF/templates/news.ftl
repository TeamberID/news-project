<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="robots" content="noindex, nofollow">

    <title>News</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="bootstrap-3.3.0/dist/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link rel=stylesheet href="css/styles.css">
    <script type="text/javascript"
            src="bootstrap-3.3.0/dist/js/bootstrap.min.js"
            charset="UTF-8"></script>
    <script src="js/jquery-3.2.1.js"></script>
    <script src="bootstrap-3.3.0/dist/js/bootstrap.min.js"></script>
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
                    <div class="well">
                        <h3><strong>${new.title}</strong></h3>
                        <br>
                        <p class="to-centre">
                            <img style="border-radius: 10px" src="${new.imageSrc}" width="300" height="300">
                        </p>
                        <br>
                        <p>${new.description}
                        </p>
                    </div>

                    <h2>Комментарии</h2>
                    <div class="well" xmlns="http://www.w3.org/1999/html">

                        <form method="post" action="/news">
                            <textarea class="user-comment" name="comment" id="comment" placeholder="Комментарий" style="width: 600px; height: 100px; resize:none">
                            </textarea>
                            <input type="hidden" value=${news_id} name="id">
                            <input type="submit" value="отправить" style=" float: right; margin-right: 10px">

                        </form>



                    <#include "comments.ftl">

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
<#include "registr.ftl">

<script src="js/menu.js"></script>

<#include "footer.ftl">


</body>
</html>
