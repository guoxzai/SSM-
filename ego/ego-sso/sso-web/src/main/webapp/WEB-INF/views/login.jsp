<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>EGO商城</title>
<link href="${ctx}/css/main.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]> 
<link href="${ctx}/css/main.ie6.css" rel="stylesheet" type="text/css" />
<![endif]-->
<!--[if IE 7]> 
<link href="${ctx}/css/main.ie7.css" rel="stylesheet" type="text/css" />
<![endif]-->
<script type="text/javascript" src="${ctx}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-imgslideshow.js"></script>
<script type="text/javascript" src="${ctx}/js/ks-switch.js"></script>
<script type="text/javascript" src="${ctx}/js/lib.js"></script>
<script type="text/javascript">
var timeout	= 500;
var closetimer	= 0;
var ddmenuitem	= 0;

$(document).ready(function(){
   	$('.cat_item').mousemove(function(){
		$(this).addClass('cat_item_on');
	});
	$('.cat_item').mouseleave(function(){	
		$(this).removeClass('cat_item_on');
	});
	$('#slideshow').imgSlideShow({itemclass: 'i'})
	$("#slide-qg").switchTab({titCell: "dt a", trigger: "mouseover", delayTime: 0});
	$("#s_cart_nums1").hover(function(){
		mcancelclosetime();
		if(ddmenuitem) ddmenuitem.hide();
		ddmenuitem = $(document).find("#s_cartbox");
		ddmenuitem.fadeIn();
	},function(){
		mclosetime();
	});
	$("#s_cart_nums2").hover(function(){
		mcancelclosetime();
		if(ddmenuitem) ddmenuitem.hide();
		ddmenuitem = $(document).find("#s_cartbox");
		ddmenuitem.fadeIn();
	},function(){
		mclosetime();
	});
	$("#s_cartbox").hover(function(){
		mcancelclosetime();
	},function(){
		mclosetime();
	});
	 var $cur = 1;
    var $i = 4;
    var $len = $('.hot_list>ul>li').length;
    var $pages = Math.ceil($len / $i);
    var $w = $('.hotp').width()-66;
	
    var $showbox = $('.hot_list');
    
    var $pre = $('div.left_icon');
    var $next = $('div.rgt_icon');
 	
    $pre.click(function(){
        if (!$showbox.is(':animated')) { 
            if ($cur == 1) {   
                $showbox.animate({
                    left: '-=' + $w * ($pages - 1)
                }, 500); 
                $cur = $pages; 
            }
            else { 
                $showbox.animate({
                    left: '+=' + $w
                }, 500); 
                $cur--;
            }
           
        }
    });

    $next.click(function(){
        if (!$showbox.is(':animated')) { 
            if ($cur == $pages) {  
                $showbox.animate({
                    left: 0
                }, 500); 
                $cur = 1; 
            }
            else {
                $showbox.animate({
                    left: '-=' + $w
                }, 500);
                $cur++; 
            }
            
        }
    });
    
});
function mclose()
{
	if(ddmenuitem) ddmenuitem.hide();
}
function mclosetime()
{
	closetimer = window.setTimeout(mclose, timeout);
}
function mcancelclosetime()
{
	if(closetimer)
	{
		window.clearTimeout(closetimer);
		closetimer = null;
	}
}
$(document).ready(function(){	
	$("#menu_body").hide();
	$("#s_cats").hoverClass("current");
	$('#s_cats').mousemove(function(){
		$("#menu_body").show();
	});
	$('#s_cats').mouseleave(function(){
		$("#menu_body").hide();
	});
});
</script>
</head>

<body>
<div id="doc">
	<div id="s_hdw">
		<%@ include file="head.jsp" %>	
	</div><!--s_hdw end-->
	
	<link type="text/css" href="${ctx}/css/lr.css" rel="stylesheet" />
	
	<div id="s_bdw">
		<div id="s_bd">
			
			<div class="dl_zc">
				<div class="dl_zc_title">
					<h2 class="f_l">用户登录</h2>
					<div class="rt_bg f_r"></div>
				</div>
				<div class="dl-con cf" id="entry">
					<form id="formlogin" method="post" onsubmit="return false;">
		
						<div class="form" style="width:600px;">
							<div class="item">
								<span class="label">用户名：</span>
		
								<div class="fl">
									<input type="text" id="loginname" name="username" class="text" tabindex="1" value=""/>
									<label id="loginname_succeed" class="blank invisible"></label>
									<span class="clear"></span>
									<label id="loginname_error"></label>
		
								</div>
							</div>
							<!--item end-->
							<div class="item">
								<span class="label">密码：</span>
		
								<div class="fl">
									<input type="password" id="loginpwd" name="password" class="text" tabindex="2"/>
									<label id="loginpwd_succeed" class="blank invisible"></label>
		
									<label><a href="forgot-password.html" class="blue">忘记密码?</a></label>
									<span class="clear"></span>
									<label id="loginpwd_error"></label>
								</div>
							</div>
							<!--item end-->
							<div class="item">
								<span class="label">&nbsp;</span>
								<div class="fl">
									<input type="checkbox" name="dl" id="jz" value="" /><label for="jz">记住用户名</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="dl" id="zd" value="" /><label for="zd">自动登录</label>
								</div>
							</div><!--item end-->
							<div class="item">
								<span class="label">&nbsp;</span>
								<input type="button" class="btnimg" id="loginsubmit" value="" tabindex="8"/>
							</div>
		
							<!--item end-->
						</div>
						<!--form end-->
						<div id="guide">
							<h5>还不是Ego商城用户？</h5>
		
							<div class="content">现在免费注册成为Ego商城用户，便能立刻享受便宜又放心的购物乐趣。</div>
							<a href="register.php" class="btn-personal">注册新用户</a>
		
						</div>
						<!--guide end-->
						<div class="clear"></div>
					</form>
				</div><!--regist end-->
			</div> <!--dl_zc end-->
			<!-- 隐藏域 回调url地址-->
			<input type="hidden" id="redirectUrl" value="${redirectUrl }"/>
			<input type="hidden" id="contextPath" value="${ctx }"/>
			<script type="text/javascript" src="${ctx}/js/Validate.js"></script>
			<script type="text/javascript" src="${ctx}/js/Validate.entry.js"></script>

		</div><!--s_bd end-->
	</div><!--s_bdw end-->	
	<%@ include file="bottom.jsp" %>
</div>
</body>
</html>

