<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-05-17
  Time: 21:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>意向公司记录</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/select2.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/city-picker.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/js/accessories/bootstrap-switch/css/bootstrap3/bootstrap-switch.min.css" rel="stylesheet"
          type="text/css">

    <link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css">
    <script type="text/javascript"
            src="http://api.map.baidu.com/api?v=2.0&ak=kb2drrx8WGDVfXy9UVEGOaNhtkGLxVEV"></script>
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
            <th data-field="name">
                意向商家姓名
            </th>
            <th data-field="phone">
                意向商家联系电话
            </th>
            <th data-field="email">
                意向商家邮箱
            </th>
            <th data-field="intentionStatus" data-formatter="status">
                记录状态
            </th>
            <th data-field="createdTime" data-formatter="formatterDate">
                记录创建时间
            </th>
            <th data-field="des">
                用户要求
            </th>
            <th data-field="operate" data-formatter="operateFormatter" data-events="operateEvents">
                操作
            </th>
        </tr>
        </thead>
        <form id="formSearch" class="form-horizontal">
            <div class="form-group" id="searchDiv" style="margin-top:15px; display: none;">
                <div class="col-sm-3" style="margin-left: -15px;">
                    <input type="text" id="searchName" name="name" class="form-control" placeholder="请输入负责人姓名">
                </div>
                <div class="col-sm-3">
                    <input type="text" id="searchPhone" name="phone" class="form-control" placeholder="请输入负责人电话">
                </div>
                <div class="col-sm-3">
                    <input type="text" id="searchEmail" name="email" class="form-control" placeholder="请输入负责人邮箱">
                </div>

                <div class="col-sm-3">
                    <button type="button" onclick="searchCheckin()" class="btn btn-primary">
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

            <a>
                <button onclick="searchStatus('/intention/intention_pager?status=Y');" type="button"
                        class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i> 查看可用记录
                </button>
            </a>
            <a>
                <button onclick="searchStatus('/intention/intention_pager?status=N');" type="button"
                        class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i> 查看不可用记录
                </button>
            </a>
            <a>
                <button onclick="searchStatus('/intention/intention_pager?status=ALL');" type="button"
                        class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i> 查看全部
                </button>
            </a>
            <a>
                <button onclick="showSearchForm()" id="showButton" type="button" class="btn btn-primary">
                    <i class="glyphicon glyphicon-search"></i> 条件查询
                </button>
            </a>
            <a>
                <button type="button" id="add" onclick="showAddWin();" class="btn btn-default">
                    <i class="glyphicon glyphicon-plus"></i>转成正式公司
                </button>
            </a>

        </div>
        </tbody>

    </table>
</div>

<div id="addWin" style="overflow:scroll" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">添加公司</h3>
                        <form role="form" id="addForm">
                            <div class="form-group">
                                <label class="control-label">公司名称：</label>
                                <input type="text" name="companyName" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">公司地址：</label>
                                <div style="position: relative;">
                                    <input data-toggle="city-picker" class="col-sm-6" name="companyAddress"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label">公司咨询电话：</label>
                                <input type="text" name="companyTel"
                                       class="form-control" maxlength="13"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">公司负责人：</label>
                                <input type="text" name="companyPricipal"
                                       class="form-control" attr="intentionId.name" />
                            </div>
                            <div class="form-group">
                                <label class="control-label">公司负责人手机号码：</label>
                                <input type="text" id="userPhone" name="companyPricipalPhone"
                                       class="form-control" attr="intentionId.phone" />
                            </div>
                            <div class="form-group">
                                <label class="control-label">公司官网</label>
                                <input type="text" name="companyWebsite"
                                       class="form-control" id="url_input"/>
                            </div>

                            <div class="form-group">
                                <label class="control-label">公司图片</label>
                                <div id="preview">
                                    <img  name="file"  style="width:120px;height:60px;"/>
                                </div>
                                <input type="file" name="file" onchange="previewImage(this)" style="display: none;"
                                       id="previewImg">
                                <a onclick="$('#previewImg').click();" style="cursor:pointer">上传公司图片</a>
                            </div>

                            <div class="form-group">
                                <label class="control-label">公司规模</label>
                                <select class="form-control" name="companySize">
                                    <option value="请选择公司规模">请选择公司规模</option>
                                    <option value="5~10">5~10</option>
                                    <option value="10~50">10~50</option>
                                    <option value="50人以上">50人以上</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="control-label">公司创建时间</label>
                                <input size="16" type="text" name="companyOpenDate" readonly
                                       class="form_datetime form-control " id="establishTime">
                                <span class="add-on"><i class="icon-th"></i></span>
                            </div>
                            <div class="form-group">
                                <label class="control-label">公司纬度</label>
                                <input id="addLatitude" type="text" name="companyLatitude"
                                       class="form-control" readonly/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">公司经度</label>
                                <input id="addLongitude" type="text" name="companyLongitude"
                                       class="form-control" readonly/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">查询公司位置:</label>
                                <input type="button" class="btn btn-primary" onclick="showMap();" value="选择公司位置">
                                </input>
                            </div>
                            <div class="form-group">
                                <label class="control-label">公司描述：</label>
                                <textarea name="companyDes" type="textarea" cols="20" rows="5"
                                          class="form-control"></textarea>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" id="addButton" class="btn btn-primary" onclick="add();" value="添加">
                                </input>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<div id="mapWin" style="overflow:scroll" class="modal fade" aria-hidden="true">
    <div class="modal-dialog" style="width: 1000px;">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">选择公司位置</h3>

                        <div style="width:730px;margin:auto;">
                            <div class="form-group col-sm-4">
                                <label class="control-label">要查询的地址：</label>
                                <input id="text_" class="form-control col-sm-4" type="text" value="赣州市"/>
                            </div>
                            <div class="form-group col-sm-4">
                                <label class="control-label">查询结果(经纬度)：</label>
                                <input id="result_" readonly class="form-control" type="text"
                                       value="114.94126,25.837179"/>
                            </div>
                            <div class="form-group col-sm-2">
                                <label class="control-label">&nbsp;</label>
                                <input type="button" value="查询" class="form-control btn btn-success"
                                       onclick="searchByStationName();"/>
                            </div>
                            <div id="addMap" style="width: 100%; height: 60%;">
                            </div>
                        </div>

                        <div class="modal-footer" style="overflow:hidden;">
                            <button type="button" class="btn btn-default"
                                    data-dismiss="modal">关闭
                            </button>
                            <input type="button" class="btn btn-primary" onclick="determine();" value="确定">
                            </input>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
    var map = new BMap.Map("addMap");
    var point = new BMap.Point(114.93502999999998, 25.831829);
    map.centerAndZoom(point, 12);
    map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
    map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用

    map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
    map.addControl(new BMap.OverviewMapControl()); //添加默认缩略地图控件
    map.addControl(new BMap.OverviewMapControl({isOpen: true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT}));   //右下角，打开
    var marker = new BMap.Marker(new BMap.Point(114.94126, 25.837179));
    map.addOverlay(marker);
    var localSearch = new BMap.LocalSearch(map);
    localSearch.enableAutoViewport(); //允许自动调节窗体大小
    function searchByStationName() {
        map.clearOverlays();//清空原来的标注
        var keyword = document.getElementById("text_").value;
        localSearch.setSearchCompleteCallback(function (searchResult) {
            var poi = searchResult.getPoi(0);
            document.getElementById("result_").value = poi.point.lng + "," + poi.point.lat;
            map.centerAndZoom(poi.point, 13);
            var marker = new BMap.Marker(new BMap.Point(poi.point.lng, poi.point.lat));  // 创建标注，为要查询的地方对应的经纬度
            map.addOverlay(marker);
            var content = document.getElementById("text_").value + "<br/><br/>经度：" + poi.point.lng + "<br/>纬度：" + poi.point.lat;
            var infoWindow = new BMap.InfoWindow("<p style='font-size:14px;'>" + content + "</p>");
            marker.addEventListener("click", function () {
                this.openInfoWindow(infoWindow);
            });
            // marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
        });
        localSearch.search(keyword);
    }

</script>

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
<script src="<%=path %>/js/jquery.formFill.js"></script>
<script src="<%=path %>/js/jquery.form.min.js"></script>
<script src="<%=path %>/js/city-picker.data.js"></script>
<script src="<%=path %>/js/city-picker.js"></script>
<script src="<%=path %>/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.fr.js"></script>
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=path %>/js/accessories/bootstrap-switch/js/bootstrap-switch.min.js"></script>
<script src="<%=path %>/js/main.js"></script>
<script src="<%=path %>/js/company/intention_company.js"></script>

</body>
</html>
