<div class="modal fade" id="reg_modal" tabindex="-1" role="dialog" aria-labelledby="RegModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="RegModalLabel">Регистрация</h4>
            </div> <!-- /.modal-header -->
            <form role="form" action="/registration" name="f1" id="f1" method="post">
                <div class="modal-body">

                    <div class="form-group">
                        <div class="input-group">
                            <input type="text" class="form-control" id="RegLogin" name="login" placeholder="Логин">
                            <label for="RegLogin" class="input-group-addon glyphicon glyphicon-user"></label>
                        </div>
                    </div> <!-- /.form-group -->

                    <div class="form-group">
                        <div class="input-group">
                            <input type="password" class="form-control" id="RegPassword" name="password"
                                   placeholder="Пароль">
                            <label for="RegPassword" class="input-group-addon glyphicon glyphicon-lock"></label>
                        </div> <!-- /.input-group -->
                    </div> <!-- /.form-group -->

                    <div class="form-group">
                        <div class="input-group">
                            <input type="password" class="form-control" id="RegRepeatPassword" name="checkPassword"
                                   placeholder="Повторите пароль">
                            <label for="RegRepeatPassword" class="input-group-addon glyphicon glyphicon-lock"></label>
                        </div> <!-- /.input-group -->
                    </div> <!-- /.form-group -->


                    <div id="error" style="color: red"></div>
        </div> <!-- /.modal-body -->    </form>  <div class="modal-footer">
            <button class="form-control btn btn-primary" onclick="check()" >Зарегистрироваться</button>
        </div>
        <!-- /.modal-footer -->

    </div><!-- /.modal-content -->
</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script src="js/registration.js"></script>