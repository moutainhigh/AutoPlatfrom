<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-06-15
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="printDiv">
    <div style="width: 100%;">
        <div style="width: 100%;">
            <div style="width: 33.33333333%; float: left;">&nbsp;</div>
            <div style="text-align: center; width: 33.33333333%; float: left;">
                <h2>
                    &nbsp;收&nbsp;费&nbsp;单&nbsp;据&nbsp;
                </h2>
                <hr style="margin-top: -5px; border-color: black; width: 50%;"/>
                <hr style="margin-top: -15px; border-color: black; width: 50%;"/>
            </div>
            <div style="width: 33.33333333%; float: left;">
                <h2>Νο<span id="code" style="color: red;"></span></h2>
            </div>
            <p style="clear: both;"></p>
        </div>


        <div style="width: 100%;">
            <div style="width: 33.33333333%; float: left;">&nbsp;</div>
            <div style="text-align: center;width: 33.33333333%; float: left;">
                入账日期：<span id="chargeTime"></span>
            </div>
            <div style="width: 33.33333333%; float: left;">&nbsp;</div>
            <p style="clear: both;"></p>
        </div>

        <div style="border: 1px solid black; height: 200px; font-size: 20px; padding-top: 20px;width: 100%;">
            <div style="margin-bottom: 10px; width: 100%;">
                <div style="width: 110px; float: left; margin-left: 30px;">收款单位：</div>
                <div id="companyName" style="border-bottom: 1px solid black; width: 330px; float: left; padding-left: 20px; height: 28px;"></div>
                <div style="width: 110px; float: left; margin-left: 30px;">收款方式：</div>
                <div id="paymentMethod" style="border-bottom: 1px solid black; width: 330px; float: left; padding-left: 20px; height: 28px;"></div>
                <p style="clear: both;"></p>
            </div>

            <div style="margin-bottom: 10px; width: 100%;">
                <div style="width: 130px; margin-left: 30px; float: left;">人民币<span style="font-size: 13px;">(大写)</span>：</div>
                <div id="actualPaymentMax" style="border-bottom: 1px solid black; width: 350px; float: left; padding-left: 20px; height: 28px;"></div>
                <div style="width: 40px; margin-left: 30px; float: left;">￥：</div>
                <div id="actualPayment" style="border-bottom: 1px solid black; width: 350px; float: left; padding-left: 20px; height: 28px;"></div>
                <p style="clear: both;"></p>
            </div>

            <div style="margin-bottom: 10px; width: 100%;">
                <div style="width: 130px; margin-left: 30px; float: left;">收款事由：</div>
                <div id="chargeBillDes" style="border-bottom: 1px solid black; width: 640px; float: left; padding-left: 20px; height: 28px;"></div>
                <p style="clear: both;"></p>
            </div>

            <div style="width: 100%;">
                <div style="width: 75%; float: left;">&nbsp;</div>
                <div style="width: 130px; float: left;">车主签字：</div>
                <div style="float: left; width: 30px;">&nbsp;</div>
                <p style="clear: both;"></p>
            </div>
        </div>

        <div tyle="margin-top: 10px; width: 100%;">
            <div style="width: 13px; float: left; margin-left: 20px; margin-top: 10px;">单位盖章</div>
            <div style="width: 41.66666667%; float: left;">&nbsp;</div>
            <div style="width: 13px; float: left;">才会主管</div>
            <div style="width: 8.33333333%; float: left;">&nbsp;</div>
            <div style="width: 13px; float: left;">记账</div>
            <div style="width: 8.33333333%; float: left;">&nbsp;</div>
            <div style="width: 13px; float: left;">出纳</div>
            <div style="width: 8.33333333%; float: left;">&nbsp;</div>
            <div style="width: 13px; float: left;">审核</div>
            <div style="width: 8.33333333%; float: left;">&nbsp;</div>
            <div style="width: 13px; float: left;">经办</div>
            <div style="width: 8.33333333%; float: left;">&nbsp;</div>
            <p style="clear: both;"></p>
        </div>

    </div>
</div>
