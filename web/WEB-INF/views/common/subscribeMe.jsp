<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-05-19
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="gtco-subscribe">
    <div class="gtco-container">
        <div class="row animate-box">
            <div class="col-md-8 col-md-offset-2 text-center gtco-heading">
                <h2>订阅我们</h2>
                <p>了解更多汽车资讯您可以订阅我们的公众号和邮件，我们将竭诚为您服务.</p>
            </div>
        </div>
        <div class="row animate-box">
            <div class="col-md-8 col-md-offset-2">
                <form class="form-inline">
                    <div id="subError" style="color: red; padding-left: 15px;"></div>
                    <div id="subSuccess" style="color: white; padding-left: 15px;"></div>
                    <div class="col-md-6 col-sm-6">
                        <div class="form-group">
                            <label for="subEmail" class="sr-only">Email</label>
                            <input type="text" class="form-control" id="subEmail" placeholder="请输入您的手机号或邮箱">
                        </div>
                    </div>
                    <div class="col-md-6 col-sm-6">
                        <button type="button" onclick="subscribMe()" class="btn btn-default btn-block">订阅</button>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>

