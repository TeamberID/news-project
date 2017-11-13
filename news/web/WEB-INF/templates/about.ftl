<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="robots" content="noindex, nofollow">

    <title>About</title>
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
            <div class="container col-md-8">
                <div class="row">
                    <div class="col-md-12">
                        <div class="block-grid block-left news-block">
                            <div class="well">
                                <h3><strong>О нас</strong></h3>
                                <br>
                                <p>Краткая инфа Краткая инфа Краткая инфа Краткая инфа Краткая инфа Краткая инфа
                                    Краткая инфа Краткая инфа Краткая инфа Краткая инфа Краткая инфа Краткая инфа
                                    Краткая инфа Краткая инфа Краткая инфа Краткая инфа Краткая инфа Краткая инфа
                                </p>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
            <!-- /#page-content-wrapper -->
        <#include "top.ftl">
        </div>
        <!-- /#wrapper -->

    <#include "login.ftl">
    <#include "registr.ftl">

        <script src="js/menu.js"></script>

    <#include "footer.ftl">

</body>
</html>