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
<script type="text/javascript">
var timeout	= 500;
var closetimer	= 0;
var ddmenuitem	= 0;

$(document).ready(function(){
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
</script>
</head>

<body>
<div id="doc">
	<div id="s_hdw">
		<%@ include file="head.jsp" %>
	</div><!--s_hdw end-->
	<div id="s_bdw">
		<div id="s_bd">
			<div class="cf">
				<div id="i_col_lft" class="i_col_lft">
					<div class="categories"></div>
				</div>
				<div id="i_col_rgt" class="i_col_rgt">
					<div class="i_col_rgt_box">
					
						<div class="i_slides" id="slideshow">
							<%-- <div class="i"><a href="#"><img src="${ctx}/images/slide-1.jpg" /></a></div>
							<div class="i"><a href="#"><img src="${ctx}/images/slide-2.jpg" /></a></div>
							<div class="i"><a href="#"><img src="${ctx}/images/slide-3.jpg" /></a></div>
							<div class="i"><a href="#"><img src="${ctx}/images/slide-4.jpg" /></a></div>
							<div class="i"><a href="#"><img src="${ctx}/images/slide-5.jpg" /></a></div> --%>
						</div>
						
						<div class="pbt10"></div>
						
						<div class="lft">
							<div class="hotp">
								<div class="lft_icon"><a href="#"><span>pre</span></a></div>
								<div class="hot_list">
									<ul>
										<li><a href="#"><img src="${ctx}/images/f1.jpg" /></a></li>
										<li><a href="#"><img src="${ctx}/images/f2.jpg" /></a></li>
										<li><a href="#"><img src="${ctx}/images/f3.jpg" /></a></li>
										<li><a href="#"><img src="${ctx}/images/f4.jpg" /></a></li>
										<li><a href="#"><img src="${ctx}/images/f5.jpg" /></a></li>
										<li><a href="#"><img src="${ctx}/images/f6.jpg" /></a></li>
										<li><a href="#"><img src="${ctx}/images/f7.jpg" /></a></li>
										<li><a href="#"><img src="${ctx}/images/f8.jpg" /></a></li>
									</ul>
								</div>
								<div class="rgt_icon"><a href="#"><span>Nexr</span></a></div>
							</div>
						</div>
						
						<div class="rgt">
							<div class="rgt-box">
								<div class="loginbox">
									<div class="login_icon cf">
										<ul>
											<li><a href="#">免费注册</a></li>
											<li><a href="#">用户登录</a></li>
										</ul>
									</div>
									
									<div class="announce_top cf"><h3>关注EGO商城<span><a href="#">更多</a></span></h3></div>
								
									<div class="announce_cont">
										<ul>
											<li><a href="#">EGO商城迎国庆促销活动28号开始</a></li>
											<li><a href="#">迎中秋，上品轩专卖店开业</a></li>
											<li><a href="#">贺EGO商城全南店盛大开业</a></li>
											<li><a href="#">凡注册为EGO商城网上商城的会员</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
						
					</div>
					
				</div>
				
			</div>
			
			<div class="cf">
				<div class="i_col_lft">
					<div class="i_lads">
						<ul>
							<li><a href="#"><img src="${ctx}/images/lad1.jpg" /></a></li>
							<li><a href="#"><img src="${ctx}/images/lad2.jpg" /></a></li>
							<li><a href="#"><img src="${ctx}/images/lad3.jpg" /></a></li>
						</ul>
					</div>
				</div>
				
				<div class="i_col_rgt">
					<div class="i_col_rgt_box1">
						<div class="lft">
							<div class="i_qg">
								<dl id="slide-qg">
									<dt class="cf">
										<a href="javascript:void(0);">限时抢购</a>
										<a href="javascript:void(0);">品牌精选</a>
										<a href="javascript:void(0);">热卖推荐</a>
										<a href="javascript:void(0);" class="last">新品上市</a>
									</dt>
									<dd>
										<div class="cat_list_box slide_cont">
											<ul>
												<li>
													<div class="cat_pd">
														<div class="pic"><a href="#"><img src="${ctx}/images/qg1-1.jpg" border="0" /></a></div>
														<div class="ptitle"><a href="#">伊宝爬行垫大号 伊宝动物乐园便携爬行</a></div>
														<div class="sprice">特价：<span>¥69</span></div>
													</div>
												</li>
												<li>
													<div class="cat_pd">
														<div class="pic"><a href="#"><img src="${ctx}/images/qg1-2.jpg" border="0" /></a></div>
														<div class="ptitle"><a href="#">清风纤巧装2层100抽面纸*5包</a></div>
														<div class="sprice">特价：<span>¥9.5</span></div>
													</div>
												</li>
												<li>
													<div class="cat_pd">
														<div class="pic"><a href="#"><img src="${ctx}/images/qg1-3.jpg" border="0" /></a></div>
														<div class="ptitle"><a href="#">TCL滚筒洗衣机 XQG60-601AS</a></div>
														<div class="sprice">特价：<span>¥1598<span></div>
													</div>
												</li>
												<li>
													<div class="cat_pd">
														<div class="pic"><a href="#"><img src="${ctx}/images/qg1-4.jpg" border="0" /></a></div>
														<div class="ptitle"><a href="#">云南蒙自纸皮薄皮石榴 皮薄果肉细嫰</a></div>
														<div class="sprice">特价：<span>¥65<span></div>
													</div>
												</li>
											</ul>
										</div>
									</dd>
									<dd>
										<div class="cat_list_box slide_cont">
											<ul>
												<li>
													<div class="cat_pd">
														<div class="pic"><a href="#"><img src="${ctx}/images/qg2-1.jpg" border="0" /></a></div>
														<div class="ptitle"><a href="#">乐扣乐扣 树叶百纳箱（55L）LLB51</a></div>
														<div class="sprice">特价：<span>¥59</span></div>
													</div>
												</li>
												<li>
													<div class="cat_pd">
														<div class="pic"><a href="#"><img src="${ctx}/images/qg2-2.jpg" border="0" /></a></div>
														<div class="ptitle"><a href="#">昂立多邦礼罐特惠组合特价125元（8包）</a></div>
														<div class="sprice">特价：<span>¥125</span></div>
													</div>
												</li>
												<li>
													<div class="cat_pd">
														<div class="pic"><a href="#"><img src="${ctx}/images/qg2-3.jpg" border="0" /></a></div>
														<div class="ptitle"><a href="#">贝亲 滋润型润肤霜35g</a></div>
														<div class="sprice">特价：<span>¥18.9<span></div>
													</div>
												</li>
												<li>
													<div class="cat_pd">
														<div class="pic"><a href="#"><img src="${ctx}/images/qg2-4.jpg" border="0" /></a></div>
														<div class="ptitle"><a href="#">好奇干爽舒适纸尿裤S小号 14片/包 赠品变动</a></div>
														<div class="sprice">特价：<span>¥12.8<span></div>
													</div>
												</li>
											</ul>
										</div>
									</dd>
									<dd>
										<div class="cat_list_box slide_cont">
											<ul>
												<li>
													<div class="cat_pd">
														<div class="pic"><a href="#"><img src="${ctx}/images/qg3-1.jpg" border="0" /></a></div>
														<div class="ptitle"><a href="#">休闲饼干DIY 10.8元选3件</a></div>
														<div class="sprice">特价：<span>¥3.6</span></div>
													</div>
												</li>
												<li>
													<div class="cat_pd">
														<div class="pic"><a href="#"><img src="${ctx}/images/qg3-2.jpg" border="0" /></a></div>
														<div class="ptitle"><a href="#">L'OREAL欧莱雅清润全日保湿水精华凝露 </a></div>
														<div class="sprice">特价：<span>¥90</span></div>
													</div>
												</li>
												<li>
													<div class="cat_pd">
														<div class="pic"><a href="#"><img src="${ctx}/images/qg3-3.jpg" border="0" /></a></div>
														<div class="ptitle"><a href="#">5830升级版跌破2000，还不来抢！</a></div>
														<div class="sprice">特价：<span>¥1998<span></div>
													</div>
												</li>
												<li>
													<div class="cat_pd">
														<div class="pic"><a href="#"><img src="${ctx}/images/qg3-4.jpg" border="0" /></a></div>
														<div class="ptitle"><a href="#">智多熊 早教益智玩具v15</a></div>
														<div class="sprice">特价：<span>¥99<span></div>
													</div>
												</li>
											</ul>
										</div>
									</dd>
									<dd>
										<div class="cat_list_box slide_cont">
											<ul>
												<li>
													<div class="cat_pd">
														<div class="pic"><a href="#"><img src="${ctx}/images/qg4-1.jpg" border="0" /></a></div>
														<div class="ptitle"><a href="#">【满200减60】 Lansiwear 浪氏威尔</a></div>
														<div class="sprice">特价：<span>¥189</span></div>
													</div>
												</li>
												<li>
													<div class="cat_pd">
														<div class="pic"><a href="#"><img src="${ctx}/images/qg4-2.jpg" border="0" /></a></div>
														<div class="ptitle"><a href="#">李宁全场3折起，满199送T恤</a></div>
														<div class="sprice">特价：<span>¥129</span></div>
													</div>
												</li>
												<li>
													<div class="cat_pd">
														<div class="pic"><a href="#"><img src="${ctx}/images/qg4-3.jpg" border="0" /></a></div>
														<div class="ptitle"><a href="#">闽龙达 嫩香菇200g</a></div>
														<div class="sprice">特价：<span>¥56.5<span></div>
													</div>
												</li>
												<li>
													<div class="cat_pd">
														<div class="pic"><a href="#"><img src="${ctx}/images/qg4-4.jpg" border="0" /></a></div>
														<div class="ptitle"><a href="#">鲜得味天然泉水金枪鱼180g/罐（泰国）</a></div>
														<div class="sprice">特价：<span>¥14.5<span></div>
													</div>
												</li>
											</ul>
										</div>
									</dd>
								</dl>
							</div>
						</div>
					</div>
					
					<div class="rgt">
						<div class="rgt-box">
							<div class="i_tg">
								<div class="i_tg_top cf"><h3>团购<span><a href="#">更多</a></span></h3></div>
								<div class="i_tg_cont">
									<h4><a href="#">【包邮】仅售308元！原价688元的阳澄湖大闸蟹8只装</a></h4>
									<div class="i_tg_pic">
										<div class="price">
											<div class="sprice">¥<span>308</span></div>
											<div class="lprice">¥<span>688</span></div>
										</div>
										<div class="pic"><a href="#"><img src="${ctx}/images/tg1.jpg" border="0" /></a></div>
									</div>
									<div class="i_tg_buynums">已有<span>102</span>个人购买</div>
								</div>
							</div>
						</div>
					</div>
					
				</div>
			</div>
			
			<div class="banner2 ptt10 cf"><a href="#"><img src="${ctx}/images/banner1.jpg" border="0" /></a></div>
		
			<div id="channel_0" class="i_channels cf">
				<div class="i_channels_tit">
					<div class="i_channels_cat_title"><h3 class="cf1">食品饮料</h3></div>
					<div class="i_channels_brand">
						<ul>
							<li class="first"><a href="#">康师傅</a></li>
							<li><a href="#">福临门</a></li>
							<li><a href="#">天喔</a></li>
							<li><a href="#">洽洽</a></li>
							<li><a href="#">卡夫</a></li>
							<li><a href="#">可口可乐</a></li>
							<li><a href="#">张裕</a></li>
							<li><a href="#">乐事</a></li>
							<li><a href="#">格力高</a></li>
							<li><a href="#">金龙鱼</a></li>
							<li><a href="#">更多</a></li>
						</ul>
					</div>
				</div>
				
				<div class="i_channels_cont">
					<div class="channel_lft">
						<div class="big_ad_box"><a href="#"><img src="${ctx}/images/cat_ad1.jpg" border="0" /></a></div>
					</div>
					
					<div class="channel_mid">
						<div class="cat_list_box">
							<ul>
								<li>
									<div class="cat_pd">
										<div class="pic"><a href="#"><img src="${ctx}/images/pd_1_1.jpg" border="0" /></a></div>
										<div class="ptitle"><a href="#">太平 加铁梳打奶盐口味400g 19元选2件</a></div>
										<div class="list_price">¥11.9</div>
										<div class="price">¥9.9</div>
									</div>
								</li>
								<li>
									<div class="cat_pd">
										<div class="pic"><a href="#"><img src="${ctx}/images/pd_1_2.jpg" border="0" /></a></div>
										<div class="ptitle"><a href="#">农夫山泉 维他命水美丽速度(石榴蓝莓风</a></div>
										<div class="list_price">¥30</div>
										<div class="price">¥20.8</div>
									</div>
								</li>
								<li>
									<div class="cat_pd">
										<div class="pic"><a href="#"><img src="${ctx}/images/pd_1_3.jpg" border="0" /></a></div>
										<div class="ptitle"><a href="#">丝宝宝 茶树菇145g/袋</a></div>
										<div class="list_price">¥26.5</div>
										<div class="price">¥20.4</div>
									</div>
								</li>
								<li>
									<div class="cat_pd">
										<div class="pic"><a href="#"><img src="${ctx}/images/pd_1_4.jpg" border="0" /></a></div>
										<div class="ptitle"><a href="#">梅林 午餐肉罐头 340g/罐</a></div>
										<div class="list_price">¥14.5</div>
										<div class="price">¥12.5</div>
									</div>
								</li>
							</ul>
						</div>
					</div>
				
					<div class="channel_rgt">
						<div class="small_ad_box">
							<ul>
								<li><a href="#"><img src="${ctx}/images/cat_s_ad1.jpg" border="0" /></a></li>
								<li><a href="#"><img src="${ctx}/images/cat_s_ad2.jpg" border="0" /></a></li>
								<li><a href="#"><img src="${ctx}/images/cat_s_ad3.jpg" border="0" /></a></li>
							</ul>
						</div>
					</div>
					
				</div>
				
			</div>
		
			<div id="channel_1" class="i_channels cf">
				<div class="i_channels_tit">
				<div class="i_channels_cat_title"><h3 class="cf2">美容护理</h3></div>
				<div class="i_channels_brand">
					<ul>
						<li class="first"><a href="#">欧莱雅</a></li>
						<li><a href="#">护舒宝</a></li>
						<li><a href="#">Hada</a></li>
						<li><a href="#">Labo肌研</a></li>
						<li><a href="#">玉兰油</a></li>
						<li><a href="#">相宜本草</a></li>
						<li><a href="#">曼秀雷敦</a></li>
						<li><a href="#">资生堂</a></li>
						<li><a href="#">苏菲</a></li>
						<li><a href="#">佳洁士</a></li>
						<li><a href="#">潘婷</a></li>
						<li><a href="#">更多</a></li>
					</ul>
				</div>
			</div>
			
			<div class="i_channels_cont">
				<div class="channel_lft">
					<div class="big_ad_box"><a href="#"><img src="${ctx}/images/cat_ad2.jpg" border="0" /></a></div>
				</div>
				<div class="channel_mid">
					<div class="cat_list_box">
						<ul>
							<li>
								<div class="cat_pd">
									<div class="pic"><a href="#"><img src="${ctx}/images/cat2-1.jpg" border="0" /></a></div>
									<div class="ptitle"><a href="#">膜法世家1908 樱桃(车厘子)睡眠面膜100g</a></div>
									<div class="list_price">¥186</div>
									<div class="price">¥116</div>
								</div>
							</li>
							<li>
								<div class="cat_pd">
									<div class="pic"><a href="#"><img src="${ctx}/images/cat2-2.jpg" border="0" /></a></div>
									<div class="ptitle"><a href="#">凌仕魅动男士香氛-契合</a></div>
									<div class="list_price">¥59</div>
									<div class="price">¥52.9</div>
								</div>
							</li>
							<li>
								<div class="cat_pd">
									<div class="pic"><a href="#"><img src="${ctx}/images/cat2-3.jpg" border="0" /></a></div>
									<div class="ptitle"><a href="#">多芬日常损伤理护洗发乳400ml+润发精华素</a></div>
									<div class="list_price">¥65</div>
									<div class="price">¥55</div>
								</div>
							</li>
							<li>
								<div class="cat_pd">
									<div class="pic"><a href="#"><img src="${ctx}/images/cat2-4.jpg" border="0" /></a></div>
									<div class="ptitle"><a href="#">德国爱姬玛琳经典香水沐浴露(100%原装进</a></div>
									<div class="list_price">¥58</div>
									<div class="price">¥38.6</div>
								</div>
							</li>
						</ul>
					</div>
				</div>
				
				<div class="channel_rgt">
					<div class="small_ad_box">
						<ul>
							<li><a href="#"><img src="${ctx}/images/cat_s_2-1.jpg" width="208" height="63" border="0" /></a></li>
							<li><a href="#"><img src="${ctx}/images/cat_s_2-2.jpg" width="208" height="63" border="0" /></a></li>
							<li><a href="#"><img src="${ctx}/images/cat_s_ad_2_3.jpg" width="203" height="98" border="0" /></a></li>
						</ul>
					</div>
				</div>
				
			</div>
			
		</div>
		
		<div id="channel_2" class="i_channels cf">
			<div class="i_channels_tit">
				<div class="i_channels_cat_title"><h3 class="cf3">母婴</h3></div>
				<div class="i_channels_brand">
					<ul>
						<li class="first"><a href="#">康师傅</a></li>
						<li><a href="#">福临门</a></li>
						<li><a href="#">天喔</a></li>
						<li><a href="#">洽洽</a></li>
						<li><a href="#">卡夫</a></li>
						<li><a href="#">可口可乐</a></li>
						<li><a href="#">张裕</a></li>
						<li><a href="#">乐事</a></li>
						<li><a href="#">格力高</a></li>
						<li><a href="#">金龙鱼</a></li>
						<li><a href="#">更多</a></li>
					</ul>
				</div>
			</div>
		
			<div class="i_channels_cont">
				<div class="channel_lft">
					<div class="big_ad_box"><a href="#"><img src="${ctx}/images/cat_ad3.jpg" border="0" /></a></div>
				</div>
				
				<div class="channel_mid">
					<div class="cat_list_box">
						<ul>
							<li>
								<div class="cat_pd">
									<div class="pic"><a href="#"><img src="${ctx}/images/cat3-1.jpg" border="0" /></a></div>
									<div class="ptitle"><a href="#">太平 加铁梳打奶盐口味400g 19元选2件</a></div>
									<div class="list_price">¥11.9</div>
									<div class="price">¥9.9</div>
								</div>
							</li>
							<li>
								<div class="cat_pd">
									<div class="pic"><a href="#"><img src="${ctx}/images/cat3-2.jpg" border="0" /></a></div>
									<div class="ptitle"><a href="#">农夫山泉 维他命水美丽速度(石榴蓝莓风</a></div>
									<div class="list_price">¥30</div>
									<div class="price">¥20.8</div>
								</div>
							</li>
							<li>
								<div class="cat_pd">
									<div class="pic"><a href="#"><img src="${ctx}/images/cat3-3.jpg" border="0" /></a></div>
									<div class="ptitle"><a href="#">丝宝宝 茶树菇145g/袋</a></div>
									<div class="list_price">¥26.5</div>
									<div class="price">¥20.4</div>
								</div>
							</li>
							<li>
								<div class="cat_pd">
									<div class="pic"><a href="#"><img src="${ctx}/images/cat3-4.jpg" border="0" /></a></div>
									<div class="ptitle"><a href="#">梅林 午餐肉罐头 340g/罐</a></div>
									<div class="list_price">¥14.5</div>
									<div class="price">¥12.5</div>
								</div>
							</li>
						</ul>
					</div>
				</div>
				
				<div class="channel_rgt">
					<div class="small_ad_box">
						<ul>
							<li><a href="#"><img src="${ctx}/images/cat_s_ad3-1.jpg" border="0" /></a></li>
							<li><a href="#"><img src="${ctx}/images/cat_s_ad3-2.jpg" border="0" /></a></li>
							<li><a href="#"><img src="${ctx}/images/cat_s_ad3-3.jpg" border="0" /></a></li>
						</ul>
					</div>
				</div>
			
			</div>
		</div>
		
		<div id="channel_3" class="i_channels cf">
			<div class="i_channels_tit">
				<div class="i_channels_cat_title"><h3 class="cf4">厨卫清洁</h3></div>
				<div class="i_channels_brand">
					<ul>
						<li class="first"><a href="#">康师傅</a></li>
						<li><a href="#">福临门</a></li>
						<li><a href="#">天喔</a></li>
						<li><a href="#">洽洽</a></li>
						<li><a href="#">卡夫</a></li>
						<li><a href="#">可口可乐</a></li>
						<li><a href="#">张裕</a></li>
						<li><a href="#">乐事</a></li>
						<li><a href="#">格力高</a></li>
						<li><a href="#">金龙鱼</a></li>
						<li><a href="#">更多</a></li>
					</ul>
				</div>
			</div>
			
			<div class="i_channels_cont">
				<div class="channel_lft">
					<div class="big_ad_box"><a href="#"><img src="${ctx}/images/cat_ad4.jpg" border="0" /></a></div>
				</div>
				
				<div class="channel_mid">
					<div class="cat_list_box">
						<ul>
							<li>
								<div class="cat_pd">
									<div class="pic"><a href="#"><img src="${ctx}/images/cat4-1.jpg" border="0" /></a></div>
									<div class="ptitle"><a href="#">太平 加铁梳打奶盐口味400g 19元选2件</a></div>
									<div class="list_price">¥11.9</div>
									<div class="price">¥9.9</div>
								</div>
							</li>
							<li>
								<div class="cat_pd">
									<div class="pic"><a href="#"><img src="${ctx}/images/cat4-2.jpg" border="0" /></a></div>
									<div class="ptitle"><a href="#">农夫山泉 维他命水美丽速度(石榴蓝莓风</a></div>
									<div class="list_price">¥30</div>
									<div class="price">¥20.8</div>
								</div>
							</li>
							<li>
								<div class="cat_pd">
									<div class="pic"><a href="#"><img src="${ctx}/images/cat4-3.jpg" border="0" /></a></div>
									<div class="ptitle"><a href="#">丝宝宝 茶树菇145g/袋</a></div>
									<div class="list_price">¥26.5</div>
									<div class="price">¥20.4</div>
								</div>
							</li>
							<li>
								<div class="cat_pd">
									<div class="pic"><a href="#"><img src="${ctx}/images/cat4-4.jpg" border="0" /></a></div>
									<div class="ptitle"><a href="#">梅林 午餐肉罐头 340g/罐</a></div>
									<div class="list_price">¥14.5</div>
									<div class="price">¥12.5</div>
								</div>
							</li>
						</ul>
					</div>
				</div>
				
				<div class="channel_rgt">
					<div class="small_ad_box">
						<ul>
							<li><a href="#"><img src="${ctx}/images/cat_s_ad4-1.jpg" border="0" /></a></li>
							<li><a href="#"><img src="${ctx}/images/cat_s_ad4-2.jpg" border="0" /></a></li>
							<li><a href="#"><img src="${ctx}/images/cat_s_ad4-3.jpg" border="0" /></a></li>
						</ul>
					</div>
				</div>
				
			</div>
		</div>
		
		<div id="channel_4" class="i_channels cf">
			<div class="i_channels_tit">
				<div class="i_channels_cat_title"><h3 class="cf5">家居</h3></div>
				<div class="i_channels_brand">
					<ul>
						<li class="first"><a href="#">康师傅</a></li>
						<li><a href="#">福临门</a></li>
						<li><a href="#">天喔</a></li>
						<li><a href="#">洽洽</a></li>
						<li><a href="#">卡夫</a></li>
						<li><a href="#">可口可乐</a></li>
						<li><a href="#">张裕</a></li>
						<li><a href="#">乐事</a></li>
						<li><a href="#">格力高</a></li>
						<li><a href="#">金龙鱼</a></li>
						<li><a href="#">更多</a></li>
					</ul>
				</div>
			</div>
			
			<div class="i_channels_cont">
				<div class="channel_lft">
					<div class="big_ad_box"><a href="#"><img src="${ctx}/images/cat_ad5.jpg" border="0" /></a></div>
				</div>
				
				<div class="channel_mid">
					<div class="cat_list_box">
						<ul>
							<li>
								<div class="cat_pd">
									<div class="pic"><a href="#"><img src="${ctx}/images/pd_1_1.jpg" border="0" /></a></div>
									<div class="ptitle"><a href="#">太平 加铁梳打奶盐口味400g 19元选2件</a></div>
									<div class="list_price">¥11.9</div>
									<div class="price">¥9.9</div>
								</div>
							</li>
							<li>
								<div class="cat_pd">
									<div class="pic"><a href="#"><img src="${ctx}/images/pd_1_2.jpg" border="0" /></a></div>
									<div class="ptitle"><a href="#">农夫山泉 维他命水美丽速度(石榴蓝莓风</a></div>
									<div class="list_price">¥30</div>
									<div class="price">¥20.8</div>
								</div>
							</li>
							<li>
								<div class="cat_pd">
									<div class="pic"><a href="#"><img src="${ctx}/images/pd_1_3.jpg" border="0" /></a></div>
									<div class="ptitle"><a href="#">丝宝宝 茶树菇145g/袋</a></div>
									<div class="list_price">¥26.5</div>
									<div class="price">¥20.4</div>
								</div>
							</li>
							<li>
								<div class="cat_pd">
									<div class="pic"><a href="#"><img src="${ctx}/images/pd_1_4.jpg" border="0" /></a></div>
									<div class="ptitle"><a href="#">梅林 午餐肉罐头 340g/罐</a></div>
									<div class="list_price">¥14.5</div>
									<div class="price">¥12.5</div>
								</div>
							</li>
						</ul>
					</div>
				</div>

				<div class="channel_rgt">
					<div class="small_ad_box">
						<ul>
							<li><a href="#"><img src="${ctx}/images/cat_s_ad5-1.jpg" border="0" /></a></li>
							<li><a href="#"><img src="${ctx}/images/cat_s_ad5-2.jpg" border="0" /></a></li>
							<li><a href="#"><img src="${ctx}/images/cat_s_ad5-3.jpg" border="0" /></a></li>
						</ul>
					</div>
				</div>
				
			</div>
		</div>
		
		</div>
	</div><!--s_bdw end-->	
	<%@ include file="bottom.jsp" %>
</div>
<script>
	$(function(){
		$.get("/home/content/loadAdvice",function(data){
			if(null != data){
				var html = "";
				$.each(data,function(index,obj){
					html += "<div class='i'><a href='"+obj.url+"'><img src='"+obj.pic+"' /></a></div>";
				});
				
				$("#slideshow").html(html);
				$('#slideshow').imgSlideShow({itemclass: 'i'})
			}
		});
	});
</script>
</body>
</html>
