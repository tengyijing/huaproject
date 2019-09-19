<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>花草商城-正品低价、品质保障、货到付款、配送及时、放心服务、轻松购物！</title>
<meta name="description" content="花草网，万种花草，应有尽有">
<meta name="Keywords" content="网上购物,网上商城,花草,玫瑰">
<link href="/css/taotao.css" rel="stylesheet"/>
<script type="text/javascript">
	window.pageConfig={
	compatible:true,
	navId:"home",
	enableArea: true
	};
</script>
<style type="text/css">
#categorys-2013 .mc {
	display: block;
}
#categorys-2013 .mt {
	background: 0
}
</style>
</head>
<body>
<!-- header start -->
<jsp:include page="commons/header.jsp" />
<!-- header end -->
<div class="w">
	<!-- 滑动轮播模块 -->
	<div id="o-slide">
		<div class="slide" id="slide">
			<script type="text/javascript">
				(function(cfg, doc) {
				    if ( !cfg.DATA_MSlide ) {
				        cfg.DATA_MSlide=[];
				    }
					var data = ${ad1};
				    cfg.DATA_MSlide = data;
				    // 初始化一个广告信息
				    if ( cfg.DATA_MSlide.length > 1 ) {
				    	var first = pageConfig.FN_GetCompatibleData( cfg.DATA_MSlide[0] );
				        var TPL = ''
				            +'<ul class="slide-items">'
				            +'<li clstag="homepage|keycount|home2013|09a1">'
				            +'<a href="'+ first.href +'" target="_blank" title="'+ first.alt +'">'
				            +'<img src="'+ first.src +'" width="'+ first.width +'" height="'+ first.height +'" >'
				            +'</a>'
				            +'</li>'
				            +'</ul><div class="slide-controls"><span class="curr">1</span></div>';
				        doc.write(TPL);
				    }
			})(pageConfig, document);;
				</script>
		</div>
<!--slide end-->
<div class="jscroll" id="mscroll">
<div class="ctrl" id="mscroll-ctrl-prev"><b></b>
</div>
<div class="ctrl" id="mscroll-ctrl-next"><b></b></div>
<div class="o-list">
<div class="list" id="mscroll-list"></div>
</div>
</div><!--mscroll end-->
<script type="text/javascript">
pageConfig.DATA_MScroll =[
    {
        "alt": "钟爱 鲜花速递香水百合花束同城送花 生日礼物全国北京上海广州深圳花店送花当日速达 12朵香水百合A 平日价",
        "href": "http://localhost:8086/item/154279165063793.html",
        "index": 0,
        "src": "http://192.168.25.175/group1/M00/00/04/wKgZr1v1IdqAejwBAAGVlKD6lsA328.jpg",
        "ext": ""
    },
    {
        "alt": "山水盆景植物文竹四季常青蓬莱松客厅室内盆栽净化空气吸甲醛绿植 山水盆【蓬莱松】 带盆栽好",
        "href": "http://localhost:8086/item/154279090009819.html",
        "index": 1,
        "src": "http://192.168.25.175/group1/M00/00/04/wKgZr1v1HtSAMlkDAAC0s9lzhBM293.jpg",
        "ext": ""
    },
    {
    	 "alt": "山水盆景植物文竹四季常青蓬莱松客厅室内盆栽净化空气吸甲醛绿植 山水盆【蓬莱松】 带盆栽好",
         "href": "http://localhost:8086/item/154279090009819.html",
         "index": 2,
         "src": "http://192.168.25.175/group1/M00/00/04/wKgZr1v1HtSAMlkDAAC0s9lzhBM293.jpg",
         "ext": ""
    },
    {
    	 "alt": "山水盆景植物文竹四季常青蓬莱松客厅室内盆栽净化空气吸甲醛绿植 山水盆【蓬莱松】 带盆栽好",
         "href": "http://localhost:8086/item/154279090009819.html",
         "index": 3,
         "src": "http://192.168.25.175/group1/M00/00/04/wKgZr1v1HtSAMlkDAAC0s9lzhBM293.jpg",
         "ext": ""
  },
    {
		  "alt": "山水盆景植物文竹四季常青蓬莱松客厅室内盆栽净化空气吸甲醛绿植 山水盆【蓬莱松】 带盆栽好",
	      "href": "http://localhost:8086/item/154279090009819.html",
	      "index": 4,
	      "src": "http://192.168.25.175/group1/M00/00/04/wKgZr1v1HtSAMlkDAAC0s9lzhBM293.jpg",
	      "ext": ""
    },
    {
    	 "alt": "山水盆景植物文竹四季常青蓬莱松客厅室内盆栽净化空气吸甲醛绿植 山水盆【蓬莱松】 带盆栽好",
         "href": "http://localhost:8086/item/154279090009819.html",
         "index": 5,
         "src": "http://192.168.25.175/group1/M00/00/04/wKgZr1v1HtSAMlkDAAC0s9lzhBM293.jpg",
         "ext": ""
    },
    {
    	 "alt": "山水盆景植物文竹四季常青蓬莱松客厅室内盆栽净化空气吸甲醛绿植 山水盆【蓬莱松】 带盆栽好",
         "href": "http://localhost:8086/item/154279090009819.html",
         "index": 6,
         "src": "http://192.168.25.175/group1/M00/00/04/wKgZr1v1HtSAMlkDAAC0s9lzhBM293.jpg",
         "ext": ""
    }] ;
(function(object, data) {
    var a = data, b = [], c = [], d, h;
    a.sort(function(a, b) {
        return a.ext - b.ext
    });
    while (a.length > 0) {
        d = a.shift();
        if (d.ext) {
            b.push(d)
        } else {
            c.push(d)
        }
    }
    c.sort(function() {
        return 0.5 - Math.random()
    });
    h = b.length;
    if (h >= 3) {
        for (var i = 0; i < 3; i++) {
            a.push(b.shift())
        }
    } else {
        for (var i = 0; i < h; i++) {
            a.push(b.shift())
        }
    }
    var f = a.length, g = c.length;
    for (var i = 0; i < 18 - f; i++) {
        if (i > g - 1) {
            continue;
        }
        a.push(c.shift())
    }
    var e = [], x;
    e.push("<ul class=\"lh\">");
    for (var i = 0; i < 3; i++) {
        x = pageConfig.FN_GetCompatibleData(a[i]);
        e.push("<li class=\"item\"><a href=\"");
        e.push(x.href);
        e.push("\"><img src=\"/images/blank.gif\" style=\"background:url(");
        e.push(x.src);
        e.push(") no-repeat #fff center 0;\" alt=\"");
        e.push(x.alt);
        e.push("\" width=\"");
        e.push(x.width);
        e.push("\" height=\"");
        e.push(x.height);
        e.push("\" /></a></li>")
    }
    e.push("</ul>");
    document.getElementById(object).innerHTML = e.join("");
    pageConfig.DATA_MScroll = a
})("mscroll-list", pageConfig.DATA_MScroll);
</script>
</div>
<div class="m fr da0x70" clstag="homepage|keycount|home2013|10a">
</div><!--da end-->
<!--新闻结束-->
 

<!--virtuals end-->
<span class="clr"></span>
</div>


<!-- footer start -->
<jsp:include page="commons/footer.jsp" />
<!-- footer end -->
 
<script type="text/javascript" src="/js/home.js" charset="utf-8"></script>
</body>
</html>