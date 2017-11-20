function checkLogin(){
    var login = document.getElementById('uLogin').value;
    var password = document.getElementById('uPassword').value;

    if(login.length === 0 || password.length === 0){
        $("#error2").append('Empty fields  ');
        return false;
    }
    document.forms["f"].submit();
    return true;
}
