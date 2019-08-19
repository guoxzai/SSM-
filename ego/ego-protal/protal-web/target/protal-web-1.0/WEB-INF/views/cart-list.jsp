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
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-imgslideshow.js"></script>
<script type="text/javascript" src="${ctx}/js/ks-switch.js"></script>
<script type="text/javascript" src="${ctx}/js/lib.js"></script>
<script type="text/javascript" src="${ctx}/js/layer/layer.js"></script>
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
	<link type="text/css" href="${ctx}/css/info.css" rel="stylesheet" />
	
	<div id="s_bdw">
		<div id="s_bd">
			
			<div class="stepflow"><img src="${ctx}/images/step01.gif" width="980" height="32" alt="" /></div>
			
			<div class="cartlist">
				<form id="my_cart_form">
					<table width="100%">
						<thead>
							<tr>
								<th>购物车中的商品</th><th>EGO价</th><th>购买数量</th><th>是否包邮</th><th>操作</th>
							</tr>
						</thead>
						<tbody id="cart_list_body">
							<!-- 通过ajax加载 -->
						</tbody>
						<tr>
							<td colspan="5">
							<div class="pagecon">
								<div class="f-r pagination" id="product_pagination">
								<!--<span class="disabled">&lt; 上一页</span>-->
								</div><!--pagination end-->
							</div>
							</td>
						</tr>
						<tr>
							<td valign="top"><a href="#"><img src="${ctx}/images/deleteicon.gif" /> 清空购物车</a></td>
							<td align="right" colspan="5">
								<p>共3件商品，总重3.580kg</p>
								<p style="margin-top:10px;font-size:14px;">总计金额(不含运费)：￥2206.00(商品金额) - ￥0.00(返现金额)= <strong style="font-size:18px;color:#d80000;">￥2206.00</strong> </p>
							</td>
						</tr>
						<tr>
							<td style="border:none;padding-top:20px;" colspan="6">
							<!-- 去结算按钮 -->
							<input type="button" value="" id="" onclick="orderSubmit()" class="btnimg f-r" />
							<a class="f-r goonbtn" href="#"><img src="${ctx}/images/gooncat.gif" width="86" height="24" alt="" /></a></td>
						</tr>
					</table>
				</form>
			</div><!--cartlist end-->
			<script type="text/javascript">
					$(function(){
						findCartByPage();
					});
					//提交订单
					function orderSubmit(){
						$.ajax({
							url:"http://localhost:8084/order/submit",
							data:$("#my_cart_form").serialize(),// key=value&&key=value
							dataType:"jsonp",  // jsonp 请求，支持跨域的跨域的请求
							jsonp:"callBackFunc",// 回调函数
							contentType: "application/jsonp; charset=utf-8",
							success:function(res){
								if(200==res.status){
									//location.href="http://localhost:8110/order/check";
									location.href="http://localhost:8084/order/check";
									
								}else{
									alert(res.msg);
								}
							},
							error:function(res){
								alert("提交订单失败");
							}
						});
					}
					//执行查询购物车
					function findCartByPage(currentPage,pageSize){
						var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
//						使用js 从cookie 里面取值
						var label= getCartLable();   
						$.ajax({
							url:"${ctx}/cart/list/findByPage",
							data:{"currentPage":currentPage,"pageSize":pageSize},
							type:"POST",
							 headers:{
					             "LABEL":label
					         },
							dataType:"JSON",
							success:function(res){
								//展示购物车列表
								showProductsList(res);
								//展示分页信息
								//showProductPagination(res);
								layer.close(index);
							},
							error:function(res){
								alert("检索失败");
							}
						});
					}
					
					//展示搜索列表
					function showProductsList(res){
						var htmlStr='';
						//展示搜索列表
						var result = res.results;
						for(i=0;i<result.length;i++){
							htmlStr+='<input type="hidden" name="goodsVoList['+i+'].id" value="'+result[i].id+'" />';
							htmlStr+='<input type="hidden" name="goodsVoList['+i+'].goodsNameHl" value="'+result[i].goodsNameHl+'" />';
							htmlStr+='<input type="hidden" name="goodsVoList['+i+'].goodsPrice" value="'+result[i].goodsPrice+'" />';
							htmlStr+='<tr bgcolor="#fffaf1" id="goods_cart_tr_'+result[i].id+'">';
							htmlStr+='<td>';
							htmlStr+='<a href="#"><img class="smallpic" src="'+result[i].goodsImg+'" width="80" height="80" /></a>';
							htmlStr+='<a href="#">'+result[i].goodsNameHl+'</a>';
							htmlStr+='</td>';
							htmlStr+='<td><strong class="red">￥'+result[i].goodsPrice+'</strong></td>';
							htmlStr+='<td>';
							htmlStr+='<div class="addinput">';
							htmlStr+='<input type="text" name="goodsVoList['+i+'].goodsCommentCount" value="'+result[i].goodsCommentCount+'" id="qty_item_'+result[i].id+'" onKeyUp="setAmount.modify(\'#qty_item_'+result[i].id+'\')" class="stext"/>';
							htmlStr+='<a class="add" onClick="setAmount.add(\'#qty_item_'+result[i].id+'\')" href="javascript:void(0)"></a>';
							htmlStr+='<a class="reduce" onClick="setAmount.reduce(\'#qty_item_'+result[i].id+'\')" href="javascript:void(0)"></a>';
							htmlStr+='</div>';
							htmlStr+='</td>';
							if(1==result[i].isFreeShipping){
								htmlStr+='<td style="color:green;">包邮</td>';
							}else{
								htmlStr+='<td style="color:red;">不包邮</td>';
							}
							htmlStr+='<td><a href="javascript:delGoods('+result[i].id+')" class="blue">删除</a></td>';
							htmlStr+='</tr>';
						}
						$("#cart_list_body").html(htmlStr);
					}
					//删除商品
					function delGoods(goodsId){
						//询问框
						layer.confirm('您确定删除该商品吗？', {
						  btn: ['确定','取消'] //按钮
						}, function(){
						  $.get("${ctx}/cart/del/"+goodsId,function(res){
							  if(200==res.status){
								  $("#goods_cart_tr_"+goodsId).remove();
								  layer.msg('删除成功', {icon: 1});
							  }
						  })
						}, function(){
						  
						});
					}
					//加入购物车
					function addCart(goodsId,goodsNum){
						$.post("${ctx}/cart/add",{"goodsId":goodsId,"goodsNum":goodsNum},function(res){
							if(200==res.status){
								getCartTotal();
								layer.msg("添加成功！");
							}
						});
					}
					//展示分页信息
					function showProductPagination(res){
						//当前页
						var currentPage = res.page;
						//上一页
						var prePage = currentPage-1;
						prePage = prePage<=0?1:prePage;
						var pre5Page = currentPage-5;
						pre5Page = pre5Page<=0?1:pre5Page;
						//下一页
						var nextPage = currentPage+1;
						nextPage = nextPage>res.total?res.total:nextPage;
						var next5Page = currentPage+5;
						next5Page = next5Page>res.total?res.total:next5Page;
						
						var pageStr='';
						pageStr+='<a href="javascript:findCartByPage('+pre5Page+')">上5页</a>';
						pageStr+='<a href="javascript:findCartByPage('+prePage+')">上一页</a>';
						var begin = 1;
						var end=res.total;
						//展示具体分页内容
						if(currentPage<6){
							end=end>7?7:end;
							pageStr = setPageInfo(begin,end,pageStr,res.currentPage);
							if(end>7){
								pageStr+='<span class="current">……</span>';
							}
						}else if(6<=currentPage<res.total-3){
							pageStr+='<a href="javascript:findCartByPage(1)">1</a>';
							pageStr+='<span class="current">……</span>';
							begin = currentPage-3;
							end = currentPage+3;
							end = end>=res.total?res.total:end;
							pageStr = setPageInfo(begin,end,pageStr,res.currentPage);
							if(currentPage<res.total-4){
								pageStr+='<span class="current">……</span>';
							}
						}
						if(res.total==currentPage){
							pageStr+='<span class="current">'+res.total+'</span>';
						}else{
							pageStr+='<a href="javascript:findCartByPage('+res.total+')">'+res.total+'</a>';
						}
						pageStr+='<a href="javascript:findCartByPage('+nextPage+')">下一页</a>';
						pageStr+='<a href="javascript:findCartByPage('+next5Page+')">下5页</a>';
						pageStr+='<div class="yepage">到第<input class="stext" type="text" name="" id="jumpPage" value="'+currentPage+'" />页';
						pageStr+='<input class="btnimg" type="submit" onclick="jumpPage()" name="" id="" value="" /></div>';
						$("#product_pagination").html(pageStr);
					}
					//跳转页面
					function jumpPage(){
						var jumpPage = $("#jumpPage").val();
						findCartByPage(jumpPage);
					}
					
					//拼接字符串
					function setPageInfo(begin,end,pageStr,currentPage){
						for(j=begin;j<end;j++){
							if(j==currentPage){
 								pageStr+='<span class="current">'+j+'</span>';
 							}else{
 								pageStr+='<a href="javascript:findCartByPage('+j+')">'+j+'</a>';
 							}
						}
						return pageStr;
					}
				
				</script>
			<script type="text/javascript">
			/* reduce_add */
			var setAmount = {
				min:1,
				max:999,
				reg:function(x) {
					return new RegExp("^[1-9]\\d*$").test(x);
				},
				amount:function(obj, mode) {
					var x = $(obj).val();
					if (this.reg(x)) {
						if (mode) {
							x++;
						} else {
							x--;
						}
					} else {
						alert("请输入正确的数量！");
						$(obj).val(1);
						$(obj).focus();
					}
					return x;
				},
				reduce:function(obj) {
					var x = this.amount(obj, false);
					if (x >= this.min) {
						var goodsId = obj.replace("#qty_item_","");
						addCart(goodsId,-1);
						$(obj).val(x);
					} else {
						alert("商品数量最少为" + this.min);
						$(obj).val(1);
						$(obj).focus();
					}
				},
				add:function(obj) {
					var x = this.amount(obj, true);
					if (x <= this.max) {
						var goodsId = obj.replace("#qty_item_","");
						addCart(goodsId,1);
						$(obj).val(x);
					} else {
						alert("商品数量最多为" + this.max);
						$(obj).val(999);
						$(obj).focus();
					}
				},
				modify:function(obj) {
					var x = $(obj).val();
					if (x < this.min || x > this.max || !this.reg(x)) {
						alert("请输入正确的数量！");
						$(obj).val(1);
						$(obj).focus();
					}
				}
			}
			</script>
			
		</div><!--s_bd end-->
	</div><!--s_bdw end-->	
	<%@ include file="bottom.jsp" %>
</div>
</body>
</html>