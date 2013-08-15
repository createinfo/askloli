//global

function getcoockie(){
  if (document.cookie.length>0){
    u_start=document.cookie.indexOf("uname=");
    p_start=document.cookie.indexOf("pw=");
	if (u_start != -1 && p_start != -1){
	  return {"username":document.cookie.substring(u_start + 6,p_start), "pw":document.cookie.substring(p_start + 3)}
	}
	else{return false;};
  };
};

function setcoockie(uname, md5){
  document.cookie = "uname=" + uname + "pw=" + md5;
};

function autologin(){
};