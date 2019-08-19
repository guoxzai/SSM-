<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order|check</title>
<script type="text/javascript" src="/js/jquery-1.11.1.min.js "></script>
</head>
<body>
	<div id="order"></div>
</body>
<script type="text/javascript">
  // 进来让它查询未完成的订单
     $(function(){
    	 $.post("${pageContext.request.contextPath}/order/query",{"status":0},function(res){
    		 if(res.status==200){
    			 // 展示查询的结果
    			 var result = res.data;
    			 var html = "";
    			$.each(result,function(index,item){
    		     html+= "<ul><li><div><div ><lable>订单名称：</lable><input id='"+index+"' value='"+item.orderSn+"' readonly='true'/></div><div><lable>价格：</lable>"+item.totalAmount+"</div><div><button onclick='pay("+index+")'>立即支付</button>  </div></div></li></ul>";
    			})
    			$("#order").html(html);
    		 }else{
    			 alert(res.msg);
    		 }
    	 })
    	 
     })
      function pay(index){ // 跳转到支付系统里面
	     var id = "#"+index;
	    location.href="http://localhost:8083/toPay?orderSn="+$(id).val();
   }
  </script>
</html>