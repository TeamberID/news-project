<nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
    <ul class="nav sidebar-nav">
        <li class="sidebar-head">
            <a href="#" data-toggle="modal" data-target="#login_modal">
            <#if cur??>
                Перезаход
            <#else>
                Вход
            </#if>
            </a></li>
        <li class="sidebar-head">
            <a href="#" data-toggle="modal" data-target="#reg_modal">Регистрация</a>
        </li>
        <li><a href="/main?name=sports">Спорт</a></li>
        <li><a href="/main?name=politics">Политика</a></li>
        <li><a href="/main?name=science">Наука и техника</a></li>
        <li><a href="/main?name=economics">Экономика</a></li>
        <li><a href="/main?name=world">Мир</a></li>
        <li><a href="/main?name=culture">Культура</a></li>
        <li class="sidebar-brand">
            <div id="custom-search-input">
                <div class="input-group col-md-12">
                    <form method="get" action="/search">
                        <input type="text" class="search-query form-control" placeholder="поиск" name="name"/>
                    </form>
                </div>
            </div>
        </li>

        <li><a href="/create">Добавить новость</a></li>
    </ul>
</nav>