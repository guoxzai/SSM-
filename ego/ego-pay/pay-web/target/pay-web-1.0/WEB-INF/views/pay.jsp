<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	 <form action="/doPay" method="post">
   支付类型<input type="text" name="payType" placeholder="" /><br />
   订单号<input type="text" name="outTradeNo" placeholder="" /><br />
   订单名称<input type="text" name="subject" placeholder="" /><br />
   总金额<input type="text" name="totalAmount" placeholder="" /><br />
   商品描述<input type="text" name="body" placeholder="" /><br />
   商店<input type="text" name="storeId" placeholder="" /><br />
   超时时间<input type="text" name="timeoutExpress" placeholder="" /><br />
   商品码<input type="text" name="productCode" placeholder="" /><br />
  </form>
  <input type="button" value="确认支付支付" onclick="submit()">
</body>

<script type="text/javascript">
  function submit(){
	  alert("xxxx");
	  $.post("http://localhost:8120/doPay",$("#pay").serialize(),function(res){
		  if(res.status==200){ // 访问成功
			  var payType = $("#payType").val();
		    if(payType==1){
			  //展示二维码
		    	 $('#qrcode').qrcode(res.data);
			  var orderSn = $("#orderSn").val();
		    	 setInterval(function(){
		    		 $.post("http://localhost:8120/order/query",{orderSn:orderSn},function(res){
		    			 if(res.status==200){
		    				 if(res.data.payStatus==2){
		    					 location.href="http://localhost:8120/pay/ok?orderSn="+orderSn;
		    				 }
		    			 }
		    		 })
		    	      },2000);
		   }else if(payType==2){
			   alert(res.data);
			   $('#qrcode').html(res.data);
		   }
		  }
	  })
  }
</script>
</html>