<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
    <meta content="text/html;charset=UTF-8" http-equiv="Content-Type">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="chrome=1,IE=Edge">
    <meta name="applicable-device" content="pc">
    <title>订单/需求中心_个人中心_药房网商城</title>
    <meta name="keywords">
    <meta name="description">
<link rel="stylesheet" type="text/css" href="/medicine/css/yfw-style.css">
<style>
    .header-img {
        position: absolute;
        right: 0;
        top: 50px;
        width: 180px;
        height: 330px;
        font-size: 0;
        display: none;
    }
    .lazyload textarea {
        display:none;
    }
    .lazyload {
        
    }
</style>

    <link rel="stylesheet" type="text/css" href="/medicine/css/usercenter.css">
    <link rel="stylesheet" type="text/css" href="/medicine/css/ui.all.css">
    <link rel="stylesheet" type="text/css" href="/medicine/css/jquery.Jcrop.css">
    <style type="text/css">
            .breadCrumb {
                background: url("#") no-repeat scroll 0 1px transparent;
                color: #333333;
                height: 28px;
                line-height: 28px;
                margin-bottom: 10px;
                margin-top: 10px;
                padding-left: 24px;
                text-align: left;
                vertical-align: middle;
            }
            /********************************stripbg  &&  chaxun********************************************/
            .stripbg1 {
                border: 1px #CCC solid;
            }

            .stripbg2 {
                background: #FFF;
                height: 35px;
                border-bottom: 1px #CCC solid;
            }

            .stripbg3 {
                background: #FFF;
                height: 32px;
            }

            .boxbg_1 {
                padding: 20px 0 10px 20px;
                background: #FBFBFB;
                border: 1px #DDD solid;
                overflow: hidden;
            }

            .chaxun {
                height: 25px;
                padding: 0 0 0 10px;
                margin: 4px 0 0 0;
                font-size: 12px;
                text-align: left;
                float: left;
            }

                .chaxun span, .chaxun input, .chaxun .chaxunbtn {
                    float: left;
                    margin-right: 5px;
                    _display: inline;
                }

                .chaxun span {
                    height: 24px;
                    line-height: 24px;
                    vertical-align: middle;
                }

                .chaxun input {
                    height: 20px;
                    padding: 2px 0 0 3px;
                    border: 1px #CCC solid;
                    margin: 0 5px 0 0;
                    font-size: 12px;
                    vertical-align: middle;
                    width: 200px;
                }

                .chaxun .inputfile {
                    height: auto;
                    padding: 0;
                    border: 1px #ddd inset;
                    margin: 0;
                    height: 22px;
                    font-size: 12px;
                    vertical-align: middle;
                    width: 200px;
                }

                .chaxun .sarchlx {
                    position: relative;
                    z-index: 3;
                }

            .sarchlx .sarchlianxiang {
                border: 1px #aaa solid;
                width: 353px;
                left: 0px;
                top: 23px;
                position: absolute;
                background: #FFF;
                z-index: 30;
            }

                .sarchlx .sarchlianxiang p {
                    background: #FFF;
                    padding: 4px 10px;
                    height: 20px;
                    line-height: 20px;
                    font-size: 12px;
                    cursor: default;
                    text-align: left;
                }

                    .sarchlx .sarchlianxiang p span {
                        float: right;
                    }

                    .sarchlx .sarchlianxiang p.hoverbg {
                        background: #EEE;
                    }

            .chaxun input.w60 {
                width: 60px;
            }

            .chaxun input.w75 {
                width: 75px;
            }

            .chaxun input.w100 {
                width: 100px;
            }

            .chaxun input.w350 {
                width: 350px;
            }

            .gltianjian {
                float: left;
                padding: 0 0 0 10px;
                margin: 4px 0 0 0;
                font-size: 12px;
                text-align: left;
                position: relative;
                z-index: 1;
                font-size: 12px;
            }
    </style>

    
    <link rel="stylesheet" href="/medicine/css/ui.all.css" type="text/css">
    <style type="text/css">
        .cp_bottom_span {
            width: 160px;
        }

        .paydiv {
        }

            .paydiv .dtip {
                color: #333;
                line-height: 20px;
                text-align: left;
            }

            .paydiv table td {
                line-height: 40px;
                text-align: center;
            }

                .paydiv table td.line {
                    border-bottom: 1px dotted #EEEEEE;
                    height: 10px;
                    line-height: 10px;
                }

            .paydiv span {
                float: left;
            }

            .paydiv label {
                width: 155px;
                height: 40px;
            }

            .paydiv .payPic {
                background-image: url("#");
                height: 40px;
                width: 140px;
                cursor: pointer;
                border: 1px solid #ddd;
                margin: 0 10px 0 5px;
            }

            .paydiv .cur {
                border: 1px solid red;
            }

            .paydiv .alipay {
                background-position: 0px -88px;
            }

            .paydiv .unionpay {
                background-position: 0px -45px;
            }

            .paydiv .unionpaynocard {
                background-position: 0px -3px;
            }

        .ptip {
            margin-left: 65px;
            display: none;
            text-align: left;
            background: none repeat scroll 0 0 #FFFBE9;
            width: 500px;
            border: 1px solid #FFECCF;
            line-height: 22px;
            padding: 10px;
        }

        .htip {
            color: red;
        }

        .ltip {
            padding-left: 35px;
        }


        .info {
            margin-bottom: 20px;
            color: #999;
        }

        .tips {
            line-height: 24px;
            margin: 10px auto 0 auto;
            width: 530px;
            border: 1px solid #dcdddd;
            background-color: #fffceb;
            padding: 5px;
            color: #666;
        }

        .selectStar {
            margin: 0 auto;
        }

            .selectStar li {
                float: left;
                text-align: left;
                _display: inline;
                line-height: 20px;
            }

                .selectStar li span.red {
                    color: #d00;
                    width: 10px;
                }

                .selectStar li span.zi {
                }

                .selectStar li em {
                    border: 0px solid #f6b88e;
                    height: 20px;
                    width: 104px;
                    display: block;
                    background: url("#") no-repeat;
                }

                    .selectStar li em.selectS1 {
                        background-position: 0px -21px;
                    }

                    .selectStar li em.selectS2 {
                        background-position: 0px -42px;
                    }

                    .selectStar li em.selectS3 {
                        background-position: 0px -63px;
                    }

                    .selectStar li em.selectS4 {
                        background-position: 0px -84px;
                    }

                    .selectStar li em.selectS5 {
                        background-position: 0px -105px;
                    }

                    .selectStar li em a {
                        text-indent: -999em;
                        overflow: hidden;
                        display: block;
                        float: left;
                        width: 20px;
                    }

        .table1b #spatishifuwu {
            color: #999;
            line-height: 200%;
        }

        .table1b #spatishifahuo {
            color: #999;
            line-height: 200%;
        }

        .table1b #spatishisonghuo {
            color: #999;
            line-height: 200%;
        }

        .table1b #spatishibaozhuang {
            color: #999;
            line-height: 200%;
        }

        .dpCont {
            color: #f60;
        }
    </style>
	<style>
    .header_top .left {
        width: 380px !important;
    }

    .header_top .right {
        width: 785px !important;
    }

        .header_top .right li.entry_btn a {
            background-color: #0cb95f;
            display: block;
            width: 69px;
            height: 22px;
            border-radius: 1px;
            line-height: 22px;
            text-align: center;
            color: #fff;
            margin-top: 9px;
            margin-right: 0;
        }
</style>


<link href="css/WdatePicker.css" rel="stylesheet" type="text/css"><style></style></head>
<body>   
<div class="b10px"></div>
    <div class="user_ddtable_header">
        <ul>
            <li class="w10">售后</li>
            <li class="w13">总价</li>
            <li class="w13">交易状态</li>
            <li class="w12">操作</li>
        </ul>
    </div>
    <c:forEach var="order" items="${orderList}">
    	<div class="b10px"></div>
        <table id="${order.orderId }" cellpadding="0" cellspacing="0" class="tabledingdan" scheduleddays="0">
            <colgroup class="group32" span="1"></colgroup>
            <colgroup class="group10" span="1"></colgroup>
            <colgroup class="group10" span="1"></colgroup>
            <colgroup class="group10" span="1"></colgroup>
            <colgroup class="group13" span="1"></colgroup>
            <colgroup class="group13" span="1"></colgroup>
            <colgroup class="group12" span="1"></colgroup>
            <thead>
                <tr>
                    <td colspan="7">
                        <div style="float:left;">
                                <input type="checkbox" odrno="C904181348396738">
                            订单编号：${order.orderId }&nbsp;&nbsp;
                            创建时间：${order.createTime }&nbsp;&nbsp;
                            <div style="display:none">{"desc":"发货周期：商家承诺付款后 24 小时内发货","buttons":[]}</div>
                                    <label style="color:#333;">发货周期：商家承诺付款后 24 小时内发货</label>
                        </div>
                        <div class="clear"></div>
                    </td>
                </tr>
               
            </thead>
            <tbody>
				<tr>       
                    <td rowspan="1" class="top">
                        <div class="line18" style=" margin-top:3px;">
                            <p><a target="_blank" href="#">验收标准</a></p>
                            <p><a target="_blank" href="#">退货政策</a></p>
                        </div>
                 	</td>
                    <td rowspan="1" class="top">
                        <div class="line18">
                            <p class="pla"><span id="C904181348396738-total"><em class="money">¥</em>${order.payment }</span></p>
                            <input type="hidden" id="C904181348396738-returntotal" value="28">
                            <input type="hidden" id="C904181348396738-UserFull" value="@*@itemOrder.point*@">

                        </div>
                        
                    </td>

                   <td rowspan="1" class="top">
                       <div class="line18">
                           <p style="padding-bottom:3px;"></p>
                        <c:choose>
                     	<c:when test="${order.status == 1 }">
                     		  <p id="C904181348396738-status" title="暂未付款">
                              				 等待买家付款
                           		  </p>
                     	</c:when>
                     	<c:when test="${order.status == 2 }">
                     		<p id="C904181348396738-status" title="等待发货">
                              				 等待卖家发货
                           		</p>
                     	</c:when>
                    		<c:when test="${order.status == 3 }">
                     		<p id="C904181348396738-status" title="已发货">
                              				 卖家已发货
                           		</p>
                     	</c:when>
                     	<c:when test="${order.status == 4 }">
                     		<p id="C904181348396738-status" title="交易成功">
                              				交易成功
                           		</p>
                     	</c:when>
                     	<c:otherwise>
                     		<p id="C904181348396738-status" title="交易关闭">
                              				交易关闭
                           		</p>
                     	</c:otherwise>
                   		</c:choose>
                           <p><a href="/order/getOrderItem?orderId=${order.orderId }">订单详情</a></p>
                       </div>
                   </td>
                   <td rowspan="1" class="top">
                            <div class="line18">
                                <p style="padding-bottom:5px;"></p>
										
										<c:if test="${order.status ==1 }">
											<div class="redbutton_orange_s2 width60px submit">
                                            <a href="/order/pay?orderId=${order.orderId }" odrno=${order.order_id } odrsts="10" return="0"><div>付款<span></span></div></a>
                                        	</div>
										</c:if>
                                    <div class="b6px"></div>
                                    <p><a href="#" odrno="${order.orderId }" rel="order_cancel">取消订单</a></p>
                        	</div>
                    </td>
                </tr>
            </tbody>
        </table>
       </c:forEach>
    <style>
        .mc td {
            text-align: center;
            border-bottom: 1px solid #eee;
        }
    </style>
    
</div>

<div style="display:none;">
 
    <div class="tanchuangbox" id="div_ckcf" title="查看处方">
        <p id="p_ckcf" style="text-align:left;padding-top:5px;"></p>
    </div>

    <div id="div_delete1"><div class="tanchuang_tishi tanchuang_inco3" style="margin-left:20px;margin-bottom:15px;text-align:left;font-size:12px;line-height:24px;font-weight:normal;">您确定要删除该订单吗？<br>删除后，您可在订单回收站找回，或永久删除</div></div>
    <div id="div_delete0"><div class="tanchuang_tishi tanchuang_inco3" style="margin-left:20px;margin-bottom:15px;text-align:left;font-size:12px;line-height:24px;font-weight:normal;">确定要恢复该订单吗？<br>恢复订单后，可以在订单中心查看。</div></div>
    <div id="div_delete2"><div class="tanchuang_tishi tanchuang_inco3" style="margin-left:20px;margin-bottom:15px;text-align:left;font-size:12px;line-height:24px;font-weight:normal;">确定要永久删除该订单吗？<br>删除订单后，该订单将无法恢复。</div></div>

    <!--物流信息-->
    <div class="wl_popinfo">
        <div class="wl_mask"></div>
        <div class="wl_popbox">
            <div class="wl_pb_close"></div>
            <div class="wl_pb_top">
                <h3>物流跟踪</h3>
            </div>
            <div class="wl_pb_info">
            </div>
            <div class="wl_pb_wrap">
                <div class="wl_pb_detail">
                </div>
            </div>
        </div>
    </div>
</div>

                </div>
            </div>
        </div>

        <div class="b10px"></div>
    </div>

    <div style="display:none;" id="subcatalog"></div>
<div class="footer">
    <div class="navigation">
        <div class="container clearfix">
            <dl>
                <dt>消费者保障</dt>
                <dd>
                    <a href="#" target="_blank" rel="nofollow">网购警示</a>
                    <a href="#" target="_blank" rel="nofollow">隐私保护</a>
                    <a href="#" target="_blank" rel="nofollow">投诉维权</a>
                    <a href="#" target="_blank" rel="nofollow">客服中心</a>
                </dd>
            </dl>
            <dl>
                <dt>新手帮助</dt>
                <dd>
                    <a href="#" target="_blank" rel="nofollow">用户注册</a>
                    <a href="#" target="_blank" rel="nofollow">商品搜索</a>
                    <a href="#" target="_blank" rel="nofollow">微信查药</a>
                    <a href="#" target="_blank" rel="nofollow">订单状态</a>
                </dd>
            </dl>
            <dl>
                <dt>配送方式</dt>
                <dd>
                    <a href="#" target="_blank" rel="nofollow">物流配送</a>
                    <a href="#" target="_blank" rel="nofollow">运费说明</a>
                    <a href="#" target="_blank" rel="nofollow">门店送货</a>
                    <a href="#" target="_blank" rel="nofollow">验收标准</a>
                </dd>
            </dl>
            <dl>
                <dt>支付方式</dt>
                <dd>
                    <a href="#" target="_blank" rel="nofollow">支付宝</a>
                    <a href="#" target="_blank" rel="nofollow" style="display:none;">货到付款</a>
                    <a href="#" target="_blank" rel="nofollow">银联支付</a>
                </dd>
            </dl>
            <dl>
                <dt>售后服务</dt>
                <dd>
                    <a href="#" target="_blank" rel="nofollow">退换货保障</a>
                    <a href="#" target="_blank" rel="nofollow">发票制度</a>
                    <a href="#" target="_blank" rel="nofollow">退款说明</a>
                </dd>
            </dl>
            <dl>
                <dt>商家合作</dt>
                <dd>
                    <a href="#" target="_blank" rel="nofollow">商家入驻</a>
                    <a href="#" target="_blank" rel="nofollow">厂家入驻</a>
                </dd>
            </dl>
            <dl class="service" style="display:none;">
                <dt>商城服务平台</dt>
                <dd>
                    <p>工作日：8:30 - 19:00</p>
                    <p>电&nbsp;&nbsp;&nbsp;&nbsp;话：400-8810-120</p>
                    <p>微&nbsp;&nbsp;&nbsp;&nbsp;博：@药房网商城</p>
                    <p>客服QQ：4008810120</p>
                </dd>
            </dl>
        </div>
    </div>
    <div class="copyright">
        <div class="container">
            <p class="white"><a class="first" href="#" target="_blank" rel="nofollow">关于我们</a> | <a href="#" target="_blank" rel="nofollow">资质证书</a> | <a href="#" target="_blank" rel="nofollow">商家入驻</a> | <a href="#" target="_blank" rel="nofollow">联系我们</a> | <a href="#" target="_blank" rel="nofollow">广告联盟</a> | <a href="#" target="_blank" rel="nofollow">工作机会</a> | <a href="#" target="_blank" rel="nofollow">帮助中心</a> | <a href="#" target="_blank" rel="nofollow">法律声明</a> |  <a href="#" target="_blank">网站地图</a>  |  <a href="#" target="_blank">在售商品</a> |  <a href="#" target="_blank">下架商品</a> |  <a href="#" target="_blank">商品索引</a> |  <a href="#" target="_blank">商品分类</a> |  <a href="#" target="_blank">热门搜索</a></p>
            <p class="white"><a class="first" href="#" target="_blank" rel="nofollow">药房联盟</a> |  <a href="#" target="_blank">医院大全</a> |  <a href="#" target="_blank">热门资讯</a> |  <a href="#" target="_blank">产品目录</a> |  <a href="#" target="_blank">品牌大全</a> |  <a href="#" target="_blank">疾病用药</a> |  <a href="#" target="_blank">症状用药</a> |  <a href="#" target="_blank">药品库</a></p>
            <p class="zz clearfix"><span class="mr">互联网药品信息服务资格证编号：<a href="#" rel="nofollow" target="_blank">(沪)-经营性-2017-0003</a></span><span class="mr"><a target="_blank" href="#" rel="nofollow">ICP备案号：沪ICP备07012885号</a></span><span class="gaba"><img src="img/favicon/gaba.png"><a target="_blank" href="#" rel="nofollow">沪公网安备 31011502002155号</a></span><span class="mr">（沪）网械平台备字[2018]第00001号</span><span class="mr">增值电信业务经营许可证编号：<a href="#" rel="nofollow" target="_blank">沪B2-20170392</a></span></p>
            <p class="copy">©2007-2019 药房网商城版权所有 上海伊邦医药信息科技有限公司</p>
            <p class="font">本网站用字经北京北大方正电子有限公司授权许可</p>
        </div>
    </div>
</div>
  

</body>
</html>
