<div class="modal fade" id="login_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">Вход</h4>
            </div> <!-- /.modal-header -->
            <form name="f" id = "f" role="form" action="/login" method="post"><div class="modal-body">

                    <div class="form-group">
                        <div class="input-group">
                            <input type="text" class="form-control" id="uLogin" name='username' placeholder="Логин">
                            <label for="uLogin" class="input-group-addon glyphicon glyphicon-user"></label>
                        </div>
                    </div> <!-- /.form-group -->

                    <div class="form-group">
                        <div class="input-group">
                            <input type="password" class="form-control" id="uPassword" name='userpassword'
                                   placeholder="Пароль">
                            <label for="uPassword" class="input-group-addon glyphicon glyphicon-lock"></label>
                        </div> <!-- /.input-group -->
                    </div> <!-- /.form-group -->

                    <div class="checkbox">
                        <label>
                            <input type="checkbox" name="remember"> Запомнить меня
                        </label>
                    </div> <!-- /.checkbox -->

                <div id="error2" style="color: red"></div>
            </div> <!-- /.modal-body -->
            </form>
            <div class="modal-footer">
                <button class="form-control btn btn-primary" onclick="checkLogin()">Войти</button>

            </div> <!-- /.modal-footer -->

        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script src="js/login.js"></script>