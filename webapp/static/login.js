//login

function login(){
  user = $("form#login input#inputUser").val();
  pw = $("form#login input#inputPassword").val();
  alert(md5(pw).toUpperCase());
  return false;
};