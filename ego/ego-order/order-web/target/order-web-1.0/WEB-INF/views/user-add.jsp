<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${ctx}/js/jquery-1.11.1.min.js"></script>
</head>
<body>
	<form action="${ctx}/user/add" method="post">
		<input id="username" name="username" placeholder="请输入用户名称" ><br />
	   <input name="password" placeholder="请输入密码" ><br />
	   <input name="code" placeholder="验证码" ><br />
	   <input type="submit" value="提交"/>&nbsp;&nbsp;&nbsp;
	   <button type="button" onclick="getAuthCode()">获取验证码</button>
	</form>
	
</body>
<script type="text/javascript">
  function getAuthCode (){
	 var username = $("#username").val();
	 $.post("${pageContext.request.contextPath }/auth/code",{"username":username},function(res){
		 if(200==res.status){
			 alert("验证码发送成功，请检查你的微信")
		 }else{
			 alert("发送失败")
		 }
	 })
 }
</script>
</html>