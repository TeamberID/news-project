<!DOCTYPE html>
<html lang="java">
<head>
    <meta charset="UTF-8">
    <style type="text/css">
        body {
            font-family: Consolas

        }

        row {
            width: 260px;
        }
    </style>
    <meta name="robots" content="text/html">
</head>
<body>
<h1 style="text-align: center; color: #585858" ><a style="color: #585858" href="/main" >Вернуться в матрицу</a></h1>
<#list news as new>
<hr>
<div>
    <div class="col-md-5">

        <div class="date">
        ${new.pubDate}
            <a href="/update?id=${new.id}">
                <h3><strong>${new.title}</strong></h3>
            </a>
            <form method="post" action="/admin">
                <input type="hidden" name="id" value="${new.id}">
                <input type="submit" value="УДАЛИТЬ">
            </form>
        </div>

    </div>
</div>
<hr>
</#list>

</body>
</html>
