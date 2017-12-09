<!DOCTYPE html>
<html>
<style>
    .row {
        font-size: 14px;
    }

    .btn-xs {
        padding: 3px !important;
        font-size: 12px;
    }

    button {
        border-radius: 3px !important;
    }
    thead tr th {
        background-color: #f5f5f5;
        border-bottom: none !important;
        vertical-align: baseline !important;
    }
</style>
<link href="/static/css/menu.css" rel="stylesheet" type="text/css"/>
<div class="row" style="margin-top: 78px;">
    <div class="col-sm-12">
        <div class="card-box table-responsive">
            <span class="m-t-0 header-title"><b>全部应用栈</b></span>
            <button onclick="add_app()" class="btn btn-default waves-effect w-md waves-light btn-xs"
                    style="margin-top: -3px;padding: 4px;border-radius: 5px;font-size:15px;margin-left: 20px;">创建应用栈
            </button>
        </div>
    </div>
</div>

<div class="row" id="app_add_html" style="display: none">
    <div class="col-sm-12">
        <div class="card-box table-responsive" id="app_add_html_content">
        </div>
    </div>
</div>


<div class="row card-box sol-sm-12" id="app_list" style="    margin-top: -10px;">
    <div class="col-sm-2" style="width: 100px;">
        <i class="mdi mdi-check-circle-outline" style="font-size: 18px;"></i>&nbsp;&nbsp;应用栈:&nbsp;
    </div>
    <div class="col-sm-2">
        <select class="form-control input-sm" data-live-search="true" style="width: 200px;"
                onchange="reload($(this).val())">
            <option value="${appName!}">${appName!}</option>
        <#list datas as apps>
            <option value="${apps.appName!}">${apps.appName!}</option>
        </#list>
        </select>
    </div>
    <div class="col-sm-2">
    ${(datas??)?then('<button type="button" class="btn btn-custom waves-effect btn-xs">立即备份</button>', '')}
    </div>


    <div class="btn-group m-b-10 pull-right" style="margin-right: 18px;">
        <div class="col-sm-4">
            <select class="form-control  btn-xs" style="    height: 27px;" onchange="create_service($(this).val())">
                <option>--请选择--</option>
                <option value="create_service">创建服务</option>
                <option value="create_loadblance">创建负载均衡</option>
                <option value="create_cluster">创建高可用集群</option>
            </select>
        </div>
        <button type="button" class="btn btn-default waves-effect btn-xs">启动服务</button>
        <button type="button" class="btn btn-default waves-effect btn-xs">停止服务</button>
        <button type="button" class="btn btn-default waves-effect btn-xs">查看详情</button>
        <button type="button" class="btn btn-default waves-effect btn-xs">删除</button>
    </div>
</div>

<div class="row card-box col-lg-12" style="margin-left:11px; margin-top: -11px;">
    <table id="datatable1" class=" table table-bordered table-hover" style="margin-right: 10px;">
        <thead>
        <tr>
            <th>应用名称</th>
            <th>环境</th>
            <th>所属组</th>
            <th>镜像</th>
            <th>容器数量</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody style="vertical-align: middle !important;">
        </tbody>
    </table>
</div>

    <#--<table id="datatable" class="col-lg-12  table-bordered">-->
        <#--<thead>-->
        <#--</thead>-->
        <#--<tbody style="vertical-align: middle !important;">-->
        <#--<#if (service?size) < 1>-->
        <#--<tr style="text-align: center">-->
            <#--<td>暂无数据!</td>-->
        <#--</tr>-->
        <#--</#if>-->
        <#--<#list service as app>-->

        <#--<tr>-->
            <#--<td style="width: 10%;font-size: 13px"><a class="text-info"-->
                                                      <#--onclick="to_detail('${appName!}', '${app.name!}','${app.entId!}','${app.groupsName!}')"-->
                                                      <#--href="#"><i class="mdi mdi-lan"></i><span-->
                    <#--class="text-info waves-light">${app.name!}</span></a></td>-->
            <#--<td style="width: 8%"><span>${app.entname!}</span></td>-->
            <#--<td style="width: 8%"><span>${app.groupsName!}</span></td>-->
            <#--<td style="width: 8%"><span>${app.image!}</span></td>-->
            <#--<td style="width:10%;text-align: center"><span>容器:${app.containerNumber!}个</span></td>-->
            <#--<td style="width: 10%;text-align: center">-->
                <#--<div><#if app.status == 0>-->
                    <#--<span class="text-warning">创建</span>-->
                <#--</#if>-->
                    <#--<#if app.status == 1>-->
                        <#--<span class="text-success">启动</span>-->
                    <#--</#if>-->
                    <#--<#if app.status == 2>-->
                        <#--<span class="text-info">运行</span>-->
                    <#--</#if>-->
                    <#--<#if app.status == 3>-->
                        <#--<span class="text-danger">停止</span>-->
                    <#--</#if>-->
                <#--</div>-->
            <#--</td>-->
            <#--<td style="width: 14%;align-content: center;text-align: center">-->
                <#--<div class="btn-group">-->
                    <#--<#if app.serviceTp ==1>-->
                        <#--<button type="button" onclick="update_service('${appName!}','${app.name!}')" class="btn btn-custom waves-effect btn-xs">服务升级</button>-->
                        <#--<div class="col-sm-4" style="width: 98px;margin-left: -5px;">-->
                            <#--<select class="form-control  btn-xs" style="height: 27px;" onchange="service_oper($(this).val(),'${app.name!}')">-->
                                <#--<option value="">更多操作</option>-->
                                <#--<option value="create_service">服务扩容</option>-->
                                <#--<option value="create_loadblance">服务缩减</option>-->
                                <#--<option value="delete_service">删除服务</option>-->
                            <#--</select>-->
                        <#--</div>-->
                    <#--</#if>-->
                    <#--<#if app.serviceTp == 2>-->
                        <#--<button type="button" class="btn btn-custom waves-effect btn-xs">停止服务</button>-->
                        <#--<button type="button" class="btn btn-danger waves-effect btn-xs">删除服务</button>-->
                    <#--</#if>-->
                    <#--<#if app.serviceTp == 0>-->
                        <#--<div class="btn-group">-->
                            <#--<button type="button"-->
                                    <#--class="btn btn-default dropdown-toggle waves-effect btn-xs waves-light"-->
                                    <#--data-toggle="dropdown" aria-expanded="false">更多操作<span class="caret"></span>-->
                            <#--</button>-->
                            <#--<ul class="dropdown-menu">-->
                                <#--<li><a href="#">创建服务</a></li>-->
                                <#--<li><a href="#">创建负载均衡</a></li>-->
                                <#--<li><a href="#">创建高可用集群</a></li>-->
                            <#--</ul>-->
                        <#--</div>-->
                    <#--</#if>-->
                <#--</div>-->
            <#--</td>-->
        <#--</tr>-->
        <#--</#list>-->
        <#--</tbody>-->
    <#--</table>-->
<#--</div>-->
</div>

<script type="text/javascript">

    function service_oper(action,service) {
        if(action=="delete_service"){
            parent.layer.confirm("点击同意后,将删除服务"+service, {
                btn: ['同意', '不同意'], //按钮
                shade: false//不显示遮罩
            }, function () {
                var url = "/service/delete/"+service;
                result = post({}, url);
                parent.layer.msg(JSON.stringify(result), {icon: 1})
            }, function () {
                parent.layer.msg("取消操作", {icon: 2})
            });
        }
    }

    function create_service(action) {
        if(action=="create_service"){
            make_page("/service/add", "/应用管理/应用栈管理/${appName!}/创建服务", "?appName=${appName!}");
        }
    }

    $('.selectpicker').selectpicker({
        'selectedText': 'cat'
    });

    function to_detail(appName, serviceName, entId, groupsName) {
        make_page("/app/detail", "/应用管理/应用栈列表/应用详情/" + appName, "?appName=" + appName + "&serviceName=" + serviceName+"&entId="+entId+"&groupsName="+groupsName);
    }
    function add_app() {
        make_page("/app/add", "/应用管理/创建应用栈");
    }

    // 服务升级
    function update_service(appName, serviceName, entId) {
        make_page("/service/update", "/应用管理/应用栈列表/服务升级/" + appName +"/"+serviceName, "?appName=" + appName + "&serviceName=" + serviceName+"&entId="+entId);
    }

    // 删除服务
    function delete_service(serviceName, entId) {
        parent.layer.confirm("点击同意后,将删除该服务 <b><span class='text-danger'>"+serviceName + "</span></b><br>", {
            btn: ['同意', '不同意'], //按钮
            shade: false//不显示遮罩
        }, function () {
            var url = "/service/delete/"+serviceName
            var result = post({entId:entId}, url);
            result = JSON.stringify(result);
            if(result.indexOf("服务完成") == -1){
                parent.layer.msg(result, {icon: 2})
            }else{
                parent.layer.msg(result, {icon: 1})
            }
        }, function () {
            parent.layer.msg("取消操作", {icon: 2})
        });
        loadData();
    }

    function reload(appName) {
        make_page("/app/list", "/应用管理/应用栈列表/" + appName, "?appName=" + appName);
    }
//    function change_add_color(obj) {
//        $(".btn-class").removeClass("btn-asura")
//        $(".btn-class").addClass("btn-asura-default")
//        $(obj).removeClass("btn-asura-default")
//        $(obj).addClass("btn-asura");
//    }
    
    function rebuild_service(serviceName,entId) {
        var url = "/service/rebuild/"+serviceName+"/"+entId;
        var result = post({}, url);
        parent.layer.msg(JSON.stringify(result), {icon: 1});
    }

    function loadData() {

        $("#datatable1").dataTable({
            "filter": true,//去掉搜索框
            "ordering": false, // 是否允许排序
            "paginationType": "full_numbers", // 页码类型
            "destroy": true,
            "processing": true,
            "serverSide": true,
            "scrollX": false, // 是否允许左右滑动
            "displayLength": 10, // 默认长度
            "bLengthChange": true, // 下啦选择每页显示
            "ajax": { // 请求地址
                "url": "/app/detailData?t=" + new Date().getTime()+"&entId=${entId!}&entname=${entname!}",
                "type": 'post'
            },
            "columns": [ // 数据映射
                {"data": "name", "mRender":function (data, type, full) {
                        return "<a onclick='to_detail(\"${appName!}\",\""+full["serviceName"]+"\",\""+full["entId"]+"\",\""+full["groupsName"]+"\")'>"+data+"</a>";
                }},
                {"data": "entname"},
                {"data": "groupsName"},
                {"data": "image","mRender":function (data) {
                    if(data){
                        var r = "";
                        var datas = data.split("/");
                        for(i=1;i<datas.length;i++){
                            r += datas[i]+"/";
                        }
                        return "<span title='"+data+"'>" + r.substring(0, r.length-1) + "</span>";
                    }
                    return "未知";
                }},
                {"data": "containerNumber"},
                {"data": "state","mRender":function (data) {
                if (data == "未更新"){
                    return "<span class='text-default'>" + data + "</span>";
                }
                if(!data || data.length < 5){
                    return "<span class='text-danger'>未知或没有更新过</span>";
                }
                    if (data ){
                       var  maps = JSON.parse(data);
                        var state = maps["State"];
                        state = state.trim();
                        if(state=="completed"){
                            state = "<span class='text-primary'>"+state+"</span>"
                        }else if(state=="updating"){
                            state = "<span class='text-warning'>"+state+"</span>"
                        }else{
                            state = "<span class='text-danger'>"+state+"</span>"
                        }
                        var mess = maps["Message"];
                        var startT = new Date(maps["StartedAt"]).toLocaleString();
                        var endT = new Date(maps["CompletedAt"]).toLocaleString();
                        return "状态:" + state+"<br>信息:"+ mess +"<br>开始:" + startT + "<br>完成:" + endT ;
                    }
                    return "未知";
                }},
{
"data": "name", "mRender": function (data, type, full) {
return ' <button onclick="update_service(\''+full["appName"]+'\',\''+full["serviceName"]+'\',\''+full["entId"]+'\')" class="btn btn-custom waves-effect  waves-light btn-xs"' +
        'style="padding: 4px;border-radius: 5px;">更新</button>' +
        ' <button onclick="delete_service(\''+full["serviceName"]+'\',\''+full["entId"]+'\')" class="btn btn-danger waves-effect  waves-light btn-xs"' +
        'style="padding: 4px;border-radius: 5px;">删除</button>'+
        ' <button title="由于未知原因造成服务更新或不可用，需要重建服务" onclick="rebuild_service(\''+full["serviceName"]+'\',\''+full["entId"]+'\')" class="btn btn-warning waves-effect  waves-light btn-xs"' +
        'style="padding: 4px;border-radius: 5px;">重建</button>';
}
},
],
"fnRowCallback": function (row, data) { // 每行创建完毕的回调
$(row).data('recordId', data.recordId);
}
})
;
}
loadData();
</script>


</body>
</html>