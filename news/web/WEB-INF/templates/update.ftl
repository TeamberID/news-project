<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="robots" content="noindex, nofollow">

    <title>Create</title>
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

    <div class="overlay"></div>

    <h2 style="text-align: center">Редактирование новости</h2>

    <div id="page-content-wrapper">


        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <div class="well" style="padding-bottom: 50px">
                        <form method="post" enctype="multipart/form-data" action="/update">
                            <h3><strong>Название</strong></h3>
                            <input type="text" id="title" name="title" style="width: 700px" value="${new.title}">
                            <br>
                            <h3><strong>Текст новости</strong></h3>
                            <textarea name="description" id="description" style="width: 700px; height: 350px; resize:none">${new.description}</textarea>

                            <input type="hidden" name="new_id" value="${new.id}">

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
            </div>
        </div>
    </div>

    <h3 style="text-align: center">Комментарии</h3>

<#list coms as com>
    <div class="row comment" style="margin-top: 45px">
        <div style="margin-left: 10px" class="comment-date">${com.pubDate}</div>
        <div class="col-md-2">
            <div class="to-centre">${com.user.login}</div>
        </div>
        <p class="col-md-10" style="margin-left: 0">
        ${com.description}
        </p>
        <form action="/updatecom" method="post">
            <input type="hidden" value="${com.id}" name="com_id">
            <input type="hidden" value="${new.id}" name="new_id">
            <input type="submit" value="УДАЛИТЬ КОММЕНТАРИЙ">
        </form>
    </div>
<br><br>
</#list>
</div>

</body>
</html>
