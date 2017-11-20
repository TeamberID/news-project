function check() {
    var login = document.getElementById('RegLogin').value;
    var reg_password = document.getElementById('RegPassword').value;
    var reg_password2 = document.getElementById('RegRepeatPassword').value;

    if (login.length === 0 || reg_password.length === 0 || reg_password2.length === 0) {
        $("#error").append('empty fields  ');
        return false;
    }
    if (/^[a-zA-Z1-9]+$/.test(login) === false) {
        $("#error").append('just English  ');
        return false;
    }
    if (login.length < 4 || login.length > 16) {
        $("#error").append('login should include from 4 to 16 chars  ');
        return false;
    }
    if (reg_password !== reg_password2) {
        $("#error").append('passwords are different  ');
        return false;
    }
    document.forms["f1"].submit();
    return true;
}
