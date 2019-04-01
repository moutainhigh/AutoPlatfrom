<%--
  Created by IntelliJ IDEA.
  User: Wjhsmart
  Date: 2017/4/13
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
%>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
    <title>维修保养记录管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/select2.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">

    <link href="<%=path %>/css/my-table.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>


<div class="container">
    <table class="table table-hover" id="cusTable"
           data-pagination="true"
           data-show-refresh="true"
           data-show-toggle="true"
           data-showColumns="true"
           data-height="520">
        <thead>
        <tr>
            <th data-field="state" data-checkbox="true"></th>
            <th data-field="checkin.userName">
                车主姓名
            </th>
            <th data-field="checkin.carPlate">
                登记车牌号
            </th>
            <th data-field="startTime" data-formatter="formatterDate">
                维修保养开始时间
            </th>
            <th data-field="endTime" data-formatter="formatterDate">
                预估结束时间
            </th>
            <th data-field="actualEndTime" data-formatter="formatterDate">
                实际结束时间
            </th>
            <th data-field="recordCreatedTime" data-formatter="formatterDate">
                创建记录时间
            </th>
            <th data-field="pickupTime" data-formatter="formatterDate">
                提车时间
            </th>
            <th data-field="checkin.maintainOrFix">
                保养&nbsp;|&nbsp;维修
            </th>
            <th data-field="speedStatus">
                当前进度
            </th>
            <th data-field="trackStatus" data-formatter="formatterTrack">
                是否回访
            </th>
            <th data-field="recordDes">
                记录描述
            </th>
            <th data-field="company.companyName">
                汽修公司
            </th>
            <th data-field="recordStatus" data-formatter="status">
                记录状态
            </th>
            <shiro:hasAnyRoles name="companyAdmin, companyReceive">
                <th data-field="caozuo" data-formatter="operateFormatter" data-events="operateEvents">
                    操作
                </th>
            </shiro:hasAnyRoles>
        </tr>
        </thead>
        <form id="formSearch" class="form-horizontal">
            <div class="form-group" id="searchDiv" style="margin-top:15px; display: none;">
                <div class="col-sm-2" style="margin-left: -15px;">
                    <input type="text" id="searchUserName" name="userName" class="form-control" placeholder="请输入车主姓名">
                </div>
                <div class="col-sm-2">
                    <input type="text" id="searchCarPlate" name="carPlate" class="form-control" placeholder="请输入车牌号码">
                </div>
                <div class="col-sm-2">
                    <select class="js-example-tags form-control" id="searchMaintainOrFix" name="maintainOrFix">
                        <option value="all">维修&nbsp;&&nbsp;保养</option>
                        <option value="维修">维修</option>
                        <option value="保养">保养</option>
                    </select>
                </div>
                <shiro:hasAnyRoles name="systemOrdinaryAdmin, systemSuperAdmin">
                    <div class="col-sm-2">
                        <select class="js-example-tags form-control company" id="searchCompanyId" name="comanyId">
                        </select>
                    </div>
                </shiro:hasAnyRoles>
                <div class="col-sm-2">
                    <button type="button" onclick="searchCondition()" class="btn btn-primary">
                        查询
                    </button>
                    <button type="button" onclick="closeSearchForm()" class="btn btn-default">
                        关闭
                    </button>

                </div>
            </div>
        </form>
        <tbody>
        <div id="toolbar" class="btn-group">
            <shiro:hasAnyRoles name="companyAdmin, companyArtificer, companyReceive">
                <a>
                    <button onclick="showAddDetailWin();" type="button" class="btn btn-default">
                        <i class="glyphicon glyphicon-glass"></i> 生成明细
                    </button>
                </a>
            </shiro:hasAnyRoles>

            <a>
                <button onclick="showDetailWin();" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i> 查看明细
                </button>
            </a>

            <a>
                <button onclick="searchStatus('/record/pager?status=Y');" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i> 查看可用记录
                </button>
            </a>
            <a>
                <button onclick="searchStatus('/record/pager?status=N');" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i> 查看不可用记录
                </button>
            </a>
            <a>
                <button onclick="searchStatus('/record/pager?status=ALL');" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i> 查看全部
                </button>
            </a>
            <a>
                <button onclick="showSearchForm()" id="showButton" type="button" class="btn btn-primary">
                    <i class="glyphicon glyphicon-search"></i> 条件查询
                </button>
            </a>

        </div>
        </tbody>

    </table>
</div>

<div id="editWin" style="overflow:scroll" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">修改维修保养记录</h3>
                        <form role="form" id="editForm">
                            <input type="hidden" attr="record.recordId" name="recordId" class="form-control"/>
                            <input type="hidden" attr="record.checkin.checkinId" name="checkinId" class="form-control"/>
                            <input type="hidden" attr="record.trackStatus" name="trackStatus" class="form-control"/>
                            <div class="form-group">
                                <label class="control-label">车主姓名：</label>
                                <input readonly type="text" attr="record.checkin.userName" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>开始时间：</label>
                                <input attr="record.startTime" readonly type="text"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>预估结束时间：</label>
                                <input readonly attr="record.endTime" type="text"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label class="control-label">记录描述：</label>
                                <textarea class="form-control" attr="record.recordDes" type="textarea" name="recordDes"
                                          rows="3"></textarea>
                            </div>


                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" onclick="buttonStatus('editForm', 'editButton')" id="editButton"
                                       class="btn btn-primary" value="修改">
                                </input>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<div id="detailWin" style="overflow:scroll" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">生成维修保养明细</h3>
                        <form role="form" id="detailForm">
                            <input type="hidden" id="detailRecordId" attr="record.recordId" name="recordId"
                                   class="form-control"/>
                            <div class="form-group">
                                <label class="control-label">车主姓名：</label>
                                <input readonly type="text" attr="record.checkin.userName" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">维修&nbsp;|&nbsp;保养：</label>
                                <input type="text" id="maintainOrFix" attr="record.checkin.maintainOrFix" readonly
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">维修保养项目：</label>
                                <input type="hidden" id="detailMaintainId" name="maintainId"/>
                                <input type="text" onclick="choiseMaintain();" id="detailMaintainName"
                                       name="maintainName" readonly class="form-control"/>
                                <br/>
                                <a>
                                    <button onclick="choiseMaintain();" type="button" class="btn btn-primary">
                                        <i class="glyphicon glyphicon-plus"></i> 选择维修保养项目
                                    </button>
                                </a>
                            </div>
                            <div class="form-group">
                                <label class="control-label">折扣&nbsp;|&nbsp;减价：</label>
                                <input type="text" maxlength="4" name="maintainDiscount" class="form-control"/>
                                <span style="font-size: 12px; color: green;">小于1大于0是折扣，大于等于1则是减价,如没有折扣或减价，输入0</span>
                            </div>

                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" id="detailButton"
                                       onclick="buttonStatus('detailForm', 'detailButton')" class="btn btn-primary"
                                       value="添加">
                                </input>
                                <input type="reset" name="reset" style="display: none;"/>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<div id="searchDetailWin" class="modal fade" aria-hidden="true" style="overflow:scroll">
    <div class="modal-dialog" style="width: 1000px;">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row" style="position: relative;">
                    <div id="signDiv" style="background: url('/img/userCornfirm.png')-25px -25px no-repeat;position: absolute;z-index:80;width: 250px;height: 250px;background-size:250px;display: none;"></div>
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">查看维修保养明细</h3>
                        <table class="table table-hover" id="detailTable"
                               data-pagination="true"
                               data-show-refresh="true"
                               data-show-toggle="true"
                               data-showColumns="true"
                               data-height="500">
                            <thead>
                            <tr>
                                <th data-field="state" data-checkbox="true"></th>
                                <th data-field="record.checkin.userName">
                                    车主姓名
                                </th>
                                <th data-field="record.checkin.userPhone">
                                    车主电话
                                </th>
                                <th data-field="record.checkin.maintainOrFix">
                                    维修&nbsp;|&nbsp;保养
                                </th>
                                <th data-field="maintain.maintainName">
                                    项目
                                </th>
                                <th data-field="maintain.maintainMoney" data-formatter="formatterMoney">
                                    原价
                                </th>
                                <th data-field="maintainDiscount" data-formatter="formatterDiscount">
                                    打折&nbsp;|&nbsp;减价
                                </th>
                                <th data-field="price" data-formatter="formatterMoney">
                                    现价
                                </th>
                                <th data-field="detailCreatedTime" data-formatter="formatterDate">
                                    明细创建时间
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <div id="toolbar1" class="btn-group">
                                <shiro:hasAnyRoles name="companyAdmin, companyArtificer, companyReceive">
                                    <a id="editBtn">
                                        <button onclick="showEditDetailWin();" type="button" id="editDetail"
                                                class="btn btn-default">
                                            <i class="glyphicon glyphicon-pencil"></i> 修改
                                        </button>
                                    </a>
                                </shiro:hasAnyRoles>
                                <shiro:hasAnyRoles name="companyAdmin, companyReceive, companyArtificer">
                                    <a id="detailBtn">
                                        <button onclick="generateDetail();" type="button" id="generateDetail"
                                                class="btn btn-default">
                                            <i class="glyphicon glyphicon-list-alt"></i> 生成明细清单
                                        </button>
                                    </a>
                                    <a id="signBtn">
                                        <button onclick="userConfirm();" type="button"
                                                class="btn btn-success">
                                            <i class="glyphicon glyphicon-ok"></i> 用户已签字
                                        </button>
                                    </a>
                                </shiro:hasAnyRoles>
                            </div>
                            </tbody>

                        </table>
                        <div style="height: 40px;"></div>
                        <div class="modal-footer" style="overflow:hidden;">
                            <button type="button" class="btn btn-default"
                                    data-dismiss="modal">关闭
                            </button>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<div id="editDetailWin" style="overflow:scroll" class="modal fade" aria-hidden="true" data-backdrop="static"
     keyboard:false>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">修改维修保养记录</h3>
                        <form role="form" id="editDetailForm">
                            <input type="hidden" attr="detail.detailId" name="detailId" class="form-control"/>
                            <input type="hidden" attr="detail.record.recordId" name="recordId" class="form-control"/>
                            <div class="form-group">
                                <label>车主姓名：</label>
                                <input readonly type="text" attr="detail.record.checkin.userName" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>车主电话：</label>
                                <input readonly type="text" attr="detail.record.checkin.userPhone"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label class="control-label">维修保养项目：</label>
                                <select id="editDetailMaintain" class="js-example-tags form-control maintain_fix"
                                        name="maintainId">
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="control-label">折扣&nbsp;|&nbsp;减价：</label>
                                <input type="text" maxlength="4" attr="detail.maintainDiscount" name="maintainDiscount"
                                       class="form-control"/>
                                <span style="font-size: 12px; color: green;">输入小于1大于0是折扣，输入大于等于1则是减价,无折扣或减价输入0</span>
                            </div>

                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default" onclick="closeEditDetailWin()">关闭
                                </button>
                                <input type="button" onclick="buttonStatus('editDetailForm', 'editDetailButton')"
                                       id="editDetailButton" class="btn btn-primary" value="修改">
                                </input>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<div id="maintainWin" class="modal fade" aria-hidden="true" style="overflow:scroll">
    <div class="modal-dialog" style="width: 1000px;">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">选择保养项目</h3>
                        <table class="table table-hover" id="maintainTable"
                               data-pagination="true"
                               data-show-refresh="true"
                               data-show-toggle="true"
                               data-showColumns="true"
                               data-height="500">
                            <thead>
                            <tr>
                                <th data-field="state" data-checkbox="true"></th>
                                <th data-field="maintainName">
                                    保养项目名称
                                </th>
                                <th data-field="maintainHour">
                                    保养所需工时
                                </th>
                                <th data-field="maintainMoney">
                                    保养基础费用
                                </th>
                                <th data-field="maintainManHourFee">
                                    保养工时费
                                </th>
                                <th data-field="maintainDes">
                                    保养描述
                                </th>
                                <th data-field="company.companyName">
                                    公司名称
                                </th>
                                <th data-field="maintainStatus" data-formatter="status">
                                    保养项目状态
                                </th>
                                <th data-field="caozuo" data-formatter="formatterChoiceMaintain"
                                    data-events="operateEvents">
                                    操作
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>

                        </table>
                        <div style="height: 40px;"></div>
                        <div class="modal-footer" style="overflow:hidden;">
                            <button type="button" class="btn btn-default"
                                    data-dismiss="modal">关闭
                            </button>
                            <input type="button" class="btn btn-primary" onclick="determineMaintain()" value="确定">
                            </input>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<div id="fixWin" class="modal fade" aria-hidden="true" style="overflow:scroll">
    <div class="modal-dialog" style="width: 1000px;">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">选择维修项目</h3>
                        <table class="table table-hover" id="fixTable"
                               data-pagination="true"
                               data-show-refresh="true"
                               data-show-toggle="true"
                               data-showColumns="true"
                               data-height="500">
                            <thead>
                            <tr>
                                <th data-field="state" data-checkbox="true"></th>
                                <th data-field="maintainName">
                                    维修项目名称
                                </th>
                                <th data-field="maintainHour">
                                    维修所需工时
                                </th>
                                <th data-field="maintainMoney">
                                    维修基础费用
                                </th>
                                <th data-field="maintainManHourFee">
                                    维修工时费
                                </th>
                                <th data-field="maintainDes">
                                    维修描述
                                </th>
                                <th data-field="company.companyName">
                                    公司名称
                                </th>
                                <th data-field="maintainStatus" data-formatter="status">
                                    维修项目状态
                                </th>
                                <th data-field="caozuo" data-formatter="formatterChoiceFix" data-events="operateEvents">
                                    操作
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>

                        </table>
                        <div style="height: 40px;"></div>
                        <div class="modal-footer" style="overflow:hidden;">
                            <button type="button" class="btn btn-default"
                                    data-dismiss="modal">关闭
                            </button>
                            <input type="button" class="btn btn-primary" onclick="determineFix()" value="确定">
                            </input>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<div id="maintainFixWin" class="modal fade" aria-hidden="true" style="overflow:scroll">
    <div class="modal-dialog" style="width: 1000px;">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">

                        </h3>
                        <div id="printDiv">
                            <table class="my-table col-sm-12">
                                <tr>
                                    <th colspan="5">公司车辆维修、保养清单</th>
                                </tr>
                                <tr>
                                    <td class="alter">车主姓名</td>
                                    <td id="userName"></td>
                                    <td class="alter">车主电话</td>
                                    <td id="userPhone" colspan="2"></td>
                                </tr>
                                <tr>
                                    <td class="alter">车牌号及车型</td>
                                    <td id="carPlate"></td>
                                    <td class="alter">首保里程</td>
                                    <td colspan="2"></td>
                                </tr>
                                <tr>
                                    <td class="alter">保养里程（km）</td>
                                    <td class="alter">保养时间</td>
                                    <td colspan="3" class="alter">保养项目</td>
                                </tr>
                                <tr>
                                    <td id="maintainCarMileage" rowspan="12"></td>
                                    <td id="startTime" rowspan="12"></td>
                                    <td class="alter">项目名</td>
                                    <td class="alter">原价</td>
                                    <td class="alter">现价</td>
                                </tr>
                                <tr>
                                    <td id="maintainName0"></td>
                                    <td id="maintainMoney0"></td>
                                    <td id="maintainPrice0"></td>
                                </tr>
                                <tr>
                                    <td id="maintainName1"></td>
                                    <td id="maintainMoney1"></td>
                                    <td id="maintainPrice1"></td>
                                </tr>
                                <tr>
                                    <td id="maintainName2"></td>
                                    <td id="maintainMoney2"></td>
                                    <td id="maintainPrice2"></td>
                                </tr>
                                <tr>
                                    <td id="maintainName3"></td>
                                    <td id="maintainMoney3"></td>
                                    <td id="maintainPrice3"></td>
                                </tr>
                                <tr>
                                    <td id="maintainName4"></td>
                                    <td id="maintainMoney4"></td>
                                    <td id="maintainPrice4"></td>
                                </tr>
                                <tr>
                                    <td id="maintainName5"></td>
                                    <td id="maintainMoney5"></td>
                                    <td id="maintainPrice5"></td>
                                </tr>
                                <tr>
                                    <td id="maintainName6"></td>
                                    <td id="maintainMoney6"></td>
                                    <td id="maintainPrice6"></td>
                                </tr>
                                <tr>
                                    <td id="maintainName7"></td>
                                    <td id="maintainMoney7"></td>
                                    <td id="maintainPrice7"></td>
                                </tr>
                                <tr>
                                    <td id="maintainName8"></td>
                                    <td id="maintainMoney8"></td>
                                    <td id="maintainPrice8"></td>
                                </tr>
                                <tr>
                                    <td id="maintainName9"></td>
                                    <td id="maintainMoney9"></td>
                                    <td id="maintainPrice9"></td>
                                </tr>

                                <tr>
                                    <td class="alter">合计：</td>
                                    <td id="count" colspan="2"></td>
                                </tr>
                                <tr>
                                    <td colspan="5" class="alter">维修记录</td>
                                </tr>
                                <tr>
                                    <td class="alter">维修项目</td>
                                    <td class="alter">更换配件名称</td>
                                    <td class="alter">里程（km）</td>
                                    <td class="alter">价格</td>
                                    <td class="alter">维修日期</td>
                                </tr>
                                <tr>
                                    <td id="fixName0"></td>
                                    <td id="fixAcc0"></td>
                                    <td id="fixCarMileage0"></td>
                                    <td id="fixPrice0"></td>
                                    <td id="fixTime0"></td>
                                </tr>
                                <tr>
                                    <td id="fixName1"></td>
                                    <td id="fixAcc1"></td>
                                    <td id="fixCarMileage1"></td>
                                    <td id="fixPrice1"></td>
                                    <td id="fixTime1"></td>
                                </tr>
                                <tr>
                                    <td id="fixName2"></td>
                                    <td id="fixAcc2"></td>
                                    <td id="fixCarMileage2"></td>
                                    <td id="fixPrice2"></td>
                                    <td id="fixTime2"></td>
                                </tr>
                                <tr>
                                    <td id="fixName3"></td>
                                    <td id="fixAcc3"></td>
                                    <td id="fixCarMileage3"></td>
                                    <td id="fixPrice3"></td>
                                    <td id="fixTime3"></td>
                                </tr>
                                <tr>
                                    <td id="fixName4"></td>
                                    <td id="fixAcc4"></td>
                                    <td id="fixCarMileage4"></td>
                                    <td id="fixPrice4"></td>
                                    <td id="fixTime4"></td>
                                </tr>
                                <tr>
                                    <td id="fixName5"></td>
                                    <td id="fixAcc5"></td>
                                    <td id="fixCarMileage5"></td>
                                    <td id="fixPrice5"></td>
                                    <td id="fixTime5"></td>
                                </tr>
                                <tr>
                                    <td id="fixName6"></td>
                                    <td id="fixAcc6"></td>
                                    <td id="fixCarMileage6"></td>
                                    <td id="fixPrice6"></td>
                                    <td id="fixTime6"></td>
                                </tr>
                                <tr>
                                    <td id="fixName7"></td>
                                    <td id="fixAcc7"></td>
                                    <td id="fixCarMileage7"></td>
                                    <td id="fixPrice7"></td>
                                    <td id="fixTime7"></td>
                                </tr>
                                <tr>
                                    <td id="fixName8"></td>
                                    <td id="fixAcc8"></td>
                                    <td id="fixCarMileage8"></td>
                                    <td id="fixPrice8"></td>
                                    <td id="fixTime8"></td>
                                </tr>
                                <tr>
                                    <td id="fixName9"></td>
                                    <td id="fixAcc9"></td>
                                    <td id="fixCarMileage9"></td>
                                    <td id="fixPrice9"></td>
                                    <td id="fixTime9"></td>
                                </tr>
                                <tr>
                                    <td>合计：</td>
                                    <td id="fixCount" colspan="4"></td>
                                </tr>
                                <tr>
                                    <td colspan="5">备注：</td>
                                </tr>
                                <tr>
                                    <td colspan="5" style="text-align: right;padding-right:100px;">车主签字：</td>
                                </tr>
                            </table>
                        </div>
                        <div style="height: 40px;"></div>
                        <div class="modal-footer" style="overflow:hidden;">
                            <button type="button" class="btn btn-default"
                                    data-dismiss="modal">关闭
                            </button>
                            <button type="button" class="btn btn-info" onclick="printMaintainAndFix()">
                                <i class="glyphicon glyphicon-print"></i> 打印
                            </button>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="../common/rightMenu.jsp" %>
<script src="<%=path %>/js/contextmenu.js"></script>
<script src="<%=path %>/js/jquery.min.js"></script>
<script src="<%=path %>/js/bootstrapValidator.js"></script>
<script src="<%=path %>/js/bootstrap.min.js"></script>
<script src="<%=path %>/js/bootstrap-table.js"></script>
<script src="<%=path %>/js/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=path %>/js/sweet-alert.min.js"></script>
<script src="<%=path %>/js/jquery.formFill.js"></script>
<script src="<%=path %>/js/select2.full.min.js"></script>
<script src="<%=path %>/js/zh-CN.js"></script>
<script src="<%=path %>/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.fr.js"></script>
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=path %>/js/jquery-migrate-1.2.1.min.js"></script>
<script src="<%=path %>/js/jquery.jqprint-0.3.js"></script>
<script src="<%=path %>/js/main.js"></script>
<script src="<%=path %>/js/maintenanceReception/record.js"></script>
</body>
</html>
