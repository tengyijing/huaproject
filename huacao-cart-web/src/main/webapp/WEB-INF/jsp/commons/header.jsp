<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="/js/base-v1.js" charset="utf-8"></script>
<!--shortcut start-->
<jsp:include page="shortcut.jsp" />
<!--shortcut end-->
<div id="o-header-2013">
	<div class="w" id="header-2013">
		<div id="logo-2013" class="ld"><a href="http://localhost:8082" hidefocus="true" clstag="homepage|keycount|home2013|02a"><b></b><img src="/images/logo.gif" width="270" height="60" alt="花草网"></a></div>
		<!--logo end-->
		<div id="search-2013">
			<div class="i-search ld">
				<ul id="shelper" class="hide">
				</ul>
				<div class="form">
					<input type="text" class="text" accesskey="s" id="key" autocomplete="off" onkeydown="javascript:if(event.keyCode==13) search('key');">
					<input type="button" value="搜索" class="button" onclick="search('key');return false;" clstag="homepage|keycount|home2013|03a">
				</div>
			</div>
			<div id="hotwords" clstag="homepage|keycount|home2013|03b"></div>
		</div>
		<!--search end-->
		<!--my360buy end-->
		<div id="settleup-2013" clstag="homepage|keycount|home2013|05a">
			<dl>
				<dt class="ld">
					<span class="shopping">
						<span id="shopping-amount"></span>
					</span>
					<a href="http://localhost:8089/cart/cart.html" id="settleup-url">去购物车结算</a>
				</dt>
			</dl>
		</div>
		<!--settleup end-->
	</div>
	<!--header end-->
	<div class="w">
		<div id="nav-2013">
				<div id="categorys-2013" class="categorys-2014">
				<div class="mt ld">
					<h2><a href="http://www.jd.com/allSort.aspx" clstag="homepage|keycount|home2013|06a">全部商品分类<b></b></a></h2>
				</div>
				<div id="_JD_ALLSORT" class="mc">
					<div class="item fore1">
						<span data-split="1"><h3>
								<a href="http://localhost:8085/search.html?q=绿色盆栽">绿色盆栽</a>
							</h3>
						</span>
					</div>
					<div class="item fore2">
						<span data-split="1"><h3>
								<a href="http://localhost:8085/search.html?q=花卉">花卉</a>
							</h3>
						</span>
					</div>
					<div class="item fore3">
						<span data-split="1"><h3>
								<a href="http://localhost:8085/search.html?q=多肉植物">多肉植物</a>
							</h3>
						</span>
					</div>
					<div class="item fore4">
						<span data-split="1"><h3>
								<a href="http://localhost:8085/search.html?q=草苗">草苗</a>
							</h3>
						</span>
					</div>
					<div class="item fore5">
						<span data-split="1"><h3>
								<a href="http://localhost:8085/search.html?q=微景观">微景观</a>
							</h3>
						</span>
					</div>
					<div class="item fore6">
						<span data-split="1"><h3>
								<a href="http://localhost:8085/search.html?q=绿色租赁">绿色租赁</a>
							</h3>
						</span>
					</div>
					<div class="item fore7">
						<span data-split="1"><h3>
								<a href="http://localhost:8085/search.html?q=造景设计">造景设计</a>
							</h3>
						</span>
					</div>
					<div class="item fore10">
						<span data-split="1"><h3>
								<a href="http://localhost:8085/search.html?q=手机">手机</a>
							</h3>
						</span>
					</div>
					<div class="item fore12">
						<span data-split="1"><h3>
								<a href="#">家居家装</a>
							</h3>
							</span>
					</div>
					<div class="item fore13">
						<span data-split="1"><h3>
								<a href="#">厨具</a>
							</h3>
							</span>
					</div>
					<div class="item fore14">
						<span data-split="1"><h3>
								<a href="#">服饰内衣</a>
							</h3>
							</span>
					</div>
					<div class="item fore13">
						<span data-split="1"><h3>
								<a href="#">厨具</a>
							</h3>
							</span>
					</div>
					<div class="item fore14">
						<span data-split="1"><h3>
								<a href="#">服饰内衣</a>
							</h3>
							</span>
					</div>
					</div>
					
					</div>
			<div id="treasure" clstag="homepage|keycount|home2013|08a"></div>
				<ul id="navitems-2013">
					<li class="fore1" id="nav-home" clstag="homepage|keycount|home2013|07a"><a href="/">首页</a></li>
					<li class="fore2" id="nav-fashion" clstag="homepage|keycount|home2013|07b"><a href="#">服装城</a></li>
					<li class="fore3" id="nav-chaoshi" clstag="homepage|keycount|home2013|07c"><a href="#">食品</a></li>
					<li class="fore4" id="nav-tuan" clstag="homepage|keycount|home2013|07d"><a href="#" target="_blank">团购</a></li>
					<li class="fore5" id="nav-auction" clstag="homepage|keycount|home2013|07e"><a href="#">夺宝岛</a></li>
					<li class="fore6" id="nav-shan" clstag="homepage|keycount|home2013|07f"><a href="#" target="_blank">闪购</a></li>
					<li class="fore7" id="nav-jinrong" clstag="homepage|keycount|home2013|07g1"><a href="#" target="_blank">金融</a></li>
				</ul>
		</div>
	</div>
</div>
<script type="text/javascript">
(function(){if(pageConfig.navId){var object=document.getElementById("nav-"+pageConfig.navId);if(object)object.className+=" curr";}})();
</script>