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
<link type="text/css" href="${ctx}/css/list.css" rel="stylesheet" />
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
	
	<div id="s_bdw">
		<div id="s_bd">
			<div class="zadv"><a href="#"><img src="${ctx}/images/3215wa.jpg" width="980" height="62" alt="" /></a></div>
			
			<div class="breadcrumbs">
				<div class="f-l"><a href="#">全部结果</a><span>»</span><a href="#">食品、饮料、酒水</a><span>»</span><a href="#">进口食品</a><span>»</span>进口米</div>
				<div class="f-r">搜索结果(<b class="red">19</b>)</div>
			</div>
			
			<div class="f-l leftlist">
				<div class="sort">
					<h2>筛选分类</h2>
					<h3><a href="#">大家电、生活电器(3890)</a></h3>
					<dl>
						<dt><a href="#">厨房电器(1001)</a></dt>
						<dd>
							<span>豆浆机(85)</span>
							<a href="#">微波炉(35)</a>
							<a href="#">电压力锅(181)</a>
							<a href="#">电水壶/热水瓶(144)</a>
							<a href="#">电磁炉(53)</a>
							<a href="#">多用途锅(78)</a>
							<a href="#">电饼铛/煎拷机(3)</a>
							<a href="#">煮蛋器(34)</a>
						</dd>
					</dl>
					<h3><a href="#">大家电、生活电器(3890)</a></h3>
					<dl>
						<dt><span>厨房电器(1001)</span></dt>
						<dd>
							<a href="#">豆浆机(85)</a>
							<a href="#">微波炉(35)</a>
							<a href="#">电压力锅(181)</a>
							<a href="#">电水壶/热水瓶(144)</a>
							<a href="#">电磁炉(53)</a>
							<a href="#">多用途锅(78)</a>
							<a href="#">电饼铛/煎拷机(3)</a>
							<a href="#">煮蛋器(34)</a>
						</dd>
					</dl>
				</div><!--sort end-->
				
				<div class="ladv"><a href="#"><img src="${ctx}/images/2asd.jpg" width="205" height="72" alt="" /></a></div>
				
				<div class="ladv"><a href="#"><img src="${ctx}/images/12ad.jpg" width="205" height="72" alt="" /></a></div>
				
				<div class="ladv"><a href="#"><img src="${ctx}/images/21af.jpg" width="205" height="72" alt="" /></a></div>
				
				<div class="Toplist">
					<div class="Ttitle"><h2 class="f-l">热销商品排行榜</h2></div>
					<div class="Topcon">
						<ul>
							<li>
								<a href="#"><img src="${ctx}/images/124ad.jpg" width="58" height="58" alt="" /></a>
								<p><a href="#">心相印优选装200抽2层塑装面巾纸</a><br /><strong class="red">￥10.9</strong></p>
							</li>
							<li>
								<a href="#"><img src="${ctx}/images/124ad.jpg" width="58" height="58" alt="" /></a>
								<p><a href="#">心相印优选装200抽2层塑装面巾纸</a><br /><strong class="red">￥10.9</strong></p>
							</li>
							<li>
								<a href="#"><img src="${ctx}/images/124ad.jpg" width="58" height="58" alt="" /></a>
								<p><a href="#">心相印优选装200抽2层塑装面巾纸</a><br /><strong class="red">￥10.9</strong></p>
							</li>
							<li>
								<a href="#"><img src="${ctx}/images/124ad.jpg" width="58" height="58" alt="" /></a>
								<p><a href="#">心相印优选装200抽2层塑装面巾纸</a><br /><strong class="red">￥10.9</strong></p>
							</li>
							<li class="last">
								<a href="#"><img src="${ctx}/images/124ad.jpg" width="58" height="58" alt="" /></a>
								<p><a href="#">心相印优选装200抽2层塑装面巾纸</a><br /><strong class="red">￥10.9</strong></p>
							</li>
						</ul>
					</div>
				</div><!--Toplist end-->
				
				<div class="Toplist">
					<div class="Ttitle"><h2 class="f-l">浏览记录</h2><a style="color:#4484db;" class="f-r" href="#"><b>清除</b></a></div>
					<div class="browselist">
						<ul class="cf">
							<li><a href="#"><img src="${ctx}/images/21da.jpg" width="58" height="58" alt="" /></a></li>
							<li><a href="#"><img src="${ctx}/images/21da.jpg" width="58" height="58" alt="" /></a></li>
							<li><a href="#"><img src="${ctx}/images/21da.jpg" width="58" height="58" alt="" /></a></li>
							<li><a href="#"><img src="${ctx}/images/21da.jpg" width="58" height="58" alt="" /></a></li>
							<li><a href="#"><img src="${ctx}/images/21da.jpg" width="58" height="58" alt="" /></a></li>
							<li><a href="#"><img src="${ctx}/images/21da.jpg" width="58" height="58" alt="" /></a></li>
						</ul>
					</div>
				</div><!--Toplist end-->
				
			</div><!--leftlist end-->
			
			<div class="f-r rightlist">
				
				<div class="hotbox cf">
					<div class="f-l hotcon">
						<h2>热卖推荐</h2>
						<ul class="cf">
							<li>
								<a href="#"><img src="${ctx}/images/21ad.jpg" width="115" height="115" alt="" /></a>
								<dl>
									<dt><a href="#">凌仕魅动男士香氛-契合</a></dt>
									<dd>特价：<strong class="red">￥52.9</strong></dd>
									<dd><span class="startotal"></span></dd>
									<dd><a class="addcat" href="#">加入购物车</a></dd>
								</dl>
							</li>
							<li>
								<a href="#"><img src="${ctx}/images/214ad.jpg" width="115" height="115" alt="" /></a>
								<dl>
									<dt><a href="#">凌仕魅动男士香氛-契合</a></dt>
									<dd>特价：<strong class="red">￥52.9</strong></dd>
									<dd><span class="startotal"></span></dd>
									<dd><a class="addcat" href="#">加入购物车</a></dd>
								</dl>
							</li>
						</ul>
					</div>
					<div class="f-l promotion">
						<h2>促销活动</h2>
						<p>指定冰洗买就送插座!液晶电视清仓大放价，小家电惠战十月，最低三大合资空调疯狂抢购</p>
					</div>
				</div><!--hotbox end-->
				
				<div class="retrieve">
					<dl class="cf">
						<dt>品牌：</dt>
						<dd><span><a href="#" class="current">全部</a></span><span><a href="#">泰滋泰味(1)</a></span><span><a href="#">金象(3)</a></span><span><a href="#">金蝶(6)</a></span><span><a href="#">红水晶(8)</a></span><span><a href="#">樱城(5)</a></span></dd>
					</dl>
					<dl class="cf">
						<dt>包装：</dt>
						<dd><span><a href="#" class="current">全部</a></span><span><a href="#">全部袋装(19)</a></span></dd>
					</dl>
					<dl class="cf">
						<dt>产地：</dt>
						<dd><span><a href="#" class="current">全部</a></span><span><a href="#">全部其他国家和地区(12)</a></span></dd>
					</dl>
					<div class="clear"></div>
				</div><!--retrieve end-->
				
				<div class="product">
					<div class="productsreach">
						<dl>
							<dt>显示：</dt><dd><a class="current" id="imgicon" href="#">图片</a><a id="listicon" href="#">列表</a></dd>
						</dl>
						<dl style="margin:0;">
							<dt>排列：</dt>
							<dd>
								<div id="rankmenu">
									<a href="#">默认排序</a>
									<ul class="cf">
										<li><a href="#">价格高低</a></li>
										<li><a href="#">上架时间</a></li>
									</ul>
								</div>
								<div class="iconsreach">
<!-- 								 点击a标签，如何执行js里面的funcation -->
									<a class="current" id="price" href="javascript:void(0)" onclick="doSearch('price');doQuery('price')">价格</a>
									<a id="sales" href="javascript:void(0)"   onclick="doSearch('commen');doQuery('commen');">销量</a>
									<a id="discuss" href="javascript:void(0)" onclick="doSearch('comment');doQuery('comment');">评论</a>
								</div>
                                <div class="iconsreach">
                                    <input type="text" id="min" />---
                                    <input type="text" id="max" /> <input type="button" value="确认" onclick="searchByPriceZone()" />
                                </div>
							</dd>
						</dl>
						<dl class="last">
							<dt>筛选：</dt>
							<dd>
								<input type="checkbox" name="" id="cx" /><label for="cx">促销</label>
								<input type="checkbox" name="" id="zp" /><label for="zp">有赠品</label>
								<input type="checkbox" name="" id="xp" /><label for="xp">新品</label>
							</dd>
						</dl>
					</div>
				</div><!--product end-->
				<input id="catId" type="hidden" value="${catId}" />
				<script type="text/javascript">
				$(document).ready(function(){
					$("#rankmenu").hoverClass("current");
				});
				</script>

				<div class="productlist">
					<ul id="product_content_list">
						<!-- 通过ajax加载 -->
					</ul>
				</div>
				<div class="clear"></div>

				<div class="pagecon">
					<div class="f-r pagination" id="product_pagination">
					<!--<span class="disabled">&lt; 上一页</span>-->
					</div><!--pagination end-->
				</div>
				<script type="text/javascript">
					$(function(){
						doSearch();
						doQuery();

					});
					//执行搜索？ keyword!= null
					//执行查询？ catId！=null
					function doQuery(sort,currentPage,pageSize){
						// layui的动画
						var index = layer.load(0, {shade: false});
						// 获取Catid，从表单里面获取
						var catId = $("#catId").val();
						if(catId==''){
							layer.close(index);
							return;
						}

                        $.ajax({
                            url:"${ctx}/search/doQuery1",
                            data:{"currentPage":currentPage,"pageSize":pageSize,"catId":catId,"sort":sort},
                            type:"POST",
                            dataType:"JSON",
                            success:function(res){
                                console.log(res);
                                //展示搜索列表
                                showProductsList(res);
                                //展示分页信息
                                showProductPagination(res);
                                layer.close(index);
                            },
                            error:function(res){
                                alert("检索失败");
                            }
                        });
                        layer.close(index);

					}

					function doSearch(sort,currentPage,pageSize){
						var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
						var keywords = $("#keywords").val();
						if(keywords==''){ // 使用el表达式无法获取null，若为null ，则会自动变为''
							return ;
						}
						$.ajax({
							url:"${ctx}/search/doSearch",
							data:{"currentPage":currentPage,"pageSize":pageSize,"keywords":keywords,"sort":sort},
							type:"POST",
							dataType:"JSON",
							success:function(res){

								//展示搜索列表
								showProductsList(res);
								//展示分页信息
								showProductPagination(res);
								layer.close(index);
							},
							error:function(res){
								alert("检索失败");
							}
						});
						layer.close(index);
					}

					//展示搜索列表
					function showProductsList(res){
						var htmlStr='';
						//展示搜索列表
						var result = res.results;
						for(i=0;i<result.length;i++){
							htmlStr+='<li>';
							htmlStr+='<a href="'+result[i].id+'">';
							htmlStr+='<img src="'+result[i].goodsImg+'" width="170" height="160" alt="" />';
							htmlStr+='</a>';
							htmlStr+='<dl>';
							htmlStr+='<dt><a href="#">'+result[i].goodsNameHl+'</a></dt>';
							htmlStr+='<dd>特价：<strong class="red">￥'+result[i].goodsPrice+'</strong></dd>';
							htmlStr+='<dd><span class="startotal"></span></dd>';
							htmlStr+='<dd><a class="addcat" href="javascript:addCart('+result[i].id+',1)">加入购物车</a></dd>';
							htmlStr+='</dl>';
							htmlStr+='</li>';
						}
						$("#product_content_list").html(htmlStr);
					}

					//加入购物车
					function addCart(goodsId,goodsNum){
						//使用js 从cookie 里面取值
						var label= getCartLable();   
						 if(goodsId && goodsNum){
						        $.ajax({
						            url:"${ctx}/cart/add",
						            method:"POST",
						            headers:{
						                "LABEL":label
						            },
						            data:{
						                "goodsId":goodsId,
						                "goodsNum":goodsNum
						            },
						            success:function(res){
						              if(200 == res.status){
						            	  getCartTotal();
											layer.msg("添加成功！");
						              }else{
						            	  layer.alert("添加失败！");
						              }
						            },error:function(res){
						            	layer.alert("添加失败！");
						            }
						        })
						}
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
						pageStr+='<a href="javascript:doSearch('+pre5Page+')">上5页</a>';
						pageStr+='<a href="javascript:doSearch('+prePage+')">上一页</a>';
						var begin = 1;
						var end=res.total;
						//展示具体分页内容
						if(currentPage<6){
							end=end>7?7:end;
							pageStr = setPageInfo(begin,end,pageStr,res.page);
							if(end>7){
								pageStr+='<span class="current">……</span>';
							}
						}else if(6<=currentPage<res.total-3){
							pageStr+='<a href="javascript:doSearch(1)">1</a>';
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
							pageStr+='<a href="javascript:doSearch('+res.total+')">'+res.total+'</a>';
						}
						pageStr+='<a href="javascript:doSearch('+nextPage+')">下一页</a>';
						pageStr+='<a href="javascript:doSearch('+next5Page+')">下5页</a>';
						pageStr+='<div class="yepage">到第<input class="stext" type="text" name="" id="jumpPage" value="'+currentPage+'" />页';
						pageStr+='<input class="btnimg" type="submit" onclick="jumpPage()" name="" id="" value="" /></div>';
						$("#product_pagination").html(pageStr);
					}
					//跳转页面
					function jumpPage(){
						var jumpPage = $("#jumpPage").val();
						doSearch(jumpPage);
					}

					//拼接字符串
					function setPageInfo(begin,end,pageStr,currentPage){
						for(j=begin;j<end;j++){
							if(j==currentPage){
 								pageStr+='<span class="current">'+j+'</span>';
 							}else{
 								pageStr+='<a href="javascript:doSearch('+j+')">'+j+'</a>';
 							}
						}
						return pageStr;
					}

                    var searchByPriceZone= function(currentPage,pageSize,sort){
                        var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
                        var keywords = $("#keywords").val();
                        var catId = $("#catId").val();
                        var baseUrl= "";
                        if(catId){ // 关键字为null 但是分类id 不为空，用catId 查询
                            baseUrl = "${ctx}/search/doQuery";
                        }

                        if(keywords){
                            baseUrl = "${ctx}/search/doSearch";
                        }
                        var min = $("#min").val();
                        var max = $("#max").val();
                        $.ajax({
                            url:baseUrl,
                            data:{"currentPage":currentPage,"pageSize":pageSize,"keywords":keywords,"catId":catId,sort:sort,min:min,max:max},
                                type:"POST",
                                dataType:"JSON",
                                success:function(res){
                                    //展示搜索列表
                                    showProductsList(res);
                                    //展示分页信息
                                    showProductPagination(res);
                                    layer.close(index);
                                },
                                error:function(res){
                                    alert("检索失败");
                                }
                            });
                        layer.close(index);
                    }

				</script>
				
			</div><!--rightlist end-->
			
			<div class="clear"></div>
			
		</div><!--s_bd end-->
	</div><!--s_bdw end-->	
	<%@ include file="bottom.jsp" %>
</div>
</body>
</html>