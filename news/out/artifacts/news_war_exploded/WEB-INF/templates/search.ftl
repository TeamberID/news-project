<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="robots" content="noindex, nofollow">

    <title>Search</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="bootstrap-3.3.0/dist/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link rel=stylesheet href="css/styles.css">
    <script type="text/javascript"
            src="bootstrap-3.3.0/dist/js/bootstrap.min.js"
            charset="UTF-8"></script>
    <script src="js/jquery-3.2.1.js"></script>
    <script src="bootstrap-3.3.0/dist/js/bootstrap.min.js"></script>
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
                <div class="search col-md-12 input-group">
                    <form action="/search" method="get">
                    <input type="text" class="search-query form-control" placeholder="поиск" name="name" />
                    </form>
                </div>
            <#list news as new>
                <div class="row">
                    <div class="col-md-12">
                        <div class="block-grid block-left news-block" style="
                                background-image: url('${new.imageSrc}');
                                ">
                            <div class="date">
                                <a href="/news?id=${new.id}">
                                    <h3><strong>${new.pubDate}</strong></h3>
                                    <p>${new.title}</p>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
            </div>

        <#include "top.ftl">
        </div>



        </div>
        <!-- /#page-content-wrapper -->

    </div>
    <!-- /#wrapper -->
<#include "login.ftl">
<#include "registr.ftl">

    <script src="js/menu.js"></script>
    <script src="js/registration.js"></script>
    <script src="js/login.js"></script>

    <#include "footer.ftl">

</div>
</body>
</html>
