<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
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

    .font12 {
        font-size: 12px;
    }

    td {
        padding: 6px !important;
        font-weight: 100;
    }

    tr th {
        vertical-align: middle !important;
        padding: 3px !important;
        font-size: 12px;
        font-weight: 100;
    }

    .margin {
        margin-top: -30px;
        margin-left: 10px;
        margin-right: 10px;
    }

    .box-left {
        text-align: left;
        margin-left: 100px;
        border: none;
    }

    .select {
        border-radius: 3px;
        height: 26px;
        border: 1px solid #ccc;
    }
</style>
<script>


</script>
<div class="row card-box margin" style="margin-top: 78px !important;">
    <div class="col-sm-12">
        <div class="col-lg-8 col-md-d">
            <div class="card-box widget-box-two widget-two-custom box-left">
                <i class="mdi mdi-cube-send widget-two-icon top10" style="left: 0 !important;"></i>
                <div class="wigdet-two-content" style="margin-left: -40px;">
                    <div class="col-sm-12">
                        <div class="col-sm-2" style="margin-left: -20px;">
                            <label> 应用服务:</label>
                        </div>
                        <div class="col-sm-2">
                            <select class="from-control select" onchange="to_detail('${appName!}',$(this).val())">
                                <option class="text-info" value="${service.name!}">&nbsp;&nbsp;${service.name!}&nbsp;&nbsp;</option>
                            <#list services as app>
                            ${(app.serviceName??)?then('<option value="${app.serviceName}">&nbsp;&nbsp;${app.serviceName!}&nbsp;&nbsp;</option>','')}
                            </#list>
                            </select>
                        </div>
                        <div class="col-sm-4">
                                <span style="margin-left: 10px;">所属应用栈; <a class="text-custom">${appData.appName}</a></span>
                        </div>
                        <div class="col-sm-4">
                            <button type="button" class="btn btn-custom waves-effect btn-xs">服务升级</button>
                            <select class="from-control select" onchange="service_action($(this).val())">
                                <option class="text-info" value="">更多操作</option>
                                <option value="rollback-service">服务回滚</option>
                                <option value="delete-rollback">更新完成</option>
                                <option value="remove-service">删除服务</option>
                            </select>
                        </div>
                        </span>
                        <br>
                    </div>
                    <table>
                        <tr>
                            <th><div class="from-control">服务描述:${(appData.appDescription??)?then(appData.appDescription, '该服务没有描述信息')}</div></th>
                        </tr>
                        <tr>
                            <th><div class="from-control">创建时间:${(service.createdAt??)?then(service.createdAt, '')}</div></th>
                        </tr>
                    </table>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<br>
<div class="row card-box margin" id="app_list">
    <label class="col-sm-2 form-control-label" style="margin-left: 8px;margin-top: 4px;">
        <i class="mdi mdi-check-circle-outline" style="font-size: 18px;"></i>&nbsp;&nbsp;基本信息:&nbsp;</label>
    <div>
        <br>
        <table style="width: 100%;margin-left: 40px;" class="font12">
            <tr>
                <th style="width: 8%">容器数</th>
                <th style="width: 35%"><span id="service_docker_container_number">${service.replicas!}</span>
                    <div
                            class="input-group bootstrap-touchspin pull-right"
                            style="right: 78%;margin-top: -5px;"><span class="input-group-btn"
                                                                       style="width: 0px !important;"><button
                            class="btn btn-default btn-xs bootstrap-touchspin-down" onclick="scale_number(-1)"
                            style="width: 30px;background-color: #F2F2F2;" type="button">-</button></span><span
                            class="input-group-btn" style="width: 0px !important;"><button onclick="scale_number(1)"
                                                                                           class="btn btn-default btn-xs bootstrap-touchspin-up"
                                                                                           style="width: 30px;background-color: #F2F2F2;"
                                                                                           type="button">+</button></span>
                    </div>
                </th>
                <th style="width: 8%">CPU设置</th>
                <th style="width: 35%"> ${(service.limitCpu??)?then('${service.limitCpu!}','无')}
            </tr>
            <tr>
                <th style="width: 8%">镜像</th>
                <th style="width: 35%"> ${(service.image??)?then('${service.image!}','无')}
                <th style="width: 8%">内存限制</th>
                <th style="width: 35%"> ${(service.limitMemory??)?then('${service.limitMemory!} MB','无')}
            </tr>
            <tr>
                <th style="width: 8%">运行命令</th>
                <th style="width: 35%"> ${(service.cmd??)?then('${service.cmd}','无')}
                </th>
                <th style="width: 8%">特权模式</th>
                <th style="width: 35%"> ${(service.privileged??)?then('${service.privileged}','无')}
            </tr>
            <tr>
                <th>&nbsp;</th>
            </tr>
        </table>
        <div style="border-top: 1px solid #f0f0f0;padding: 10px;margin-left: 30px;">
            <b>服务访问路径</b><br>
            <span>xxxxxx:${(appData.appName??)?then(appData.appName, '')}</span>
        </div>
    </div>
</div>
<br>
<div class="row card-box margin" id="app_list">
    <label class="col-sm-2 form-control-label" onclick="reload_log()" style="margin-left: 8px;">
        <i class="mdi mdi-check-circle-outline" style="font-size: 18px;"></i>&nbsp;&nbsp;日志信息:&nbsp;</label>
    <span style="margin-left: -60px;margin-top: 5px;"><a class="text-custom btn-xs">刷新</a></span>
    <br>
    <div id="log_content" style="margin-left: 20px;font-weight: 100;font-size: 12px;">
        <table class="table">
            <thead>
            <tr>
            </tr>
            </thead>
            <tbody>
            <#list logs as log>
            <tr>
                <td style="border: none;width: 10%;">${log.time!}</td>
                <td style="border: none;width: 70%">${log.messages!}</td>
            </tr>
            </#list>
            </tbody>
        </table>
    </div>
    <br>
    <div class="pull-left" style="margin-left: 20px;margin-top: 10px;color: #ccc;font-size:12px;font-weight: 100" \>
        <span class="text-custom">查看更多</span>
        <span>收起日志</span>
    </div>
</div>
<#include "/service/add_service.ftl">

<script>
    function scale_number(number) {
        var v = $("#service_docker_container_number").html()
        if (!v) {
            v = 0
        }
        v = parseInt(v);
        if (v < 1) {
            return;
        }
        if (number < 1) {
            action = "缩小中"
            v -= 1;
        } else {
            action = "扩容中"
            v += 1;
        }
        var url = "/service/scale"
        var result = post({number: v, serviceName: '${serviceName!}', action: action}, url);
        success(result+"<br><br>");
        var timer;
        var counter = 0;
        timer = setInterval(function () {
            if(counter > 5){
                clearInterval(timer);
                to_detail("${appName!}", "${serviceName!}");
            }
            counter += 1;
            set_containers();
            $("#service_docker_container_number").html(v)
        }, 3000)
    }

    function create_service() {
        make_page("/service/add", "/应用管理/应用栈管理/创建服务", "?appName=${appName!}");
    }
    function reload_log() {
        var data = post({appName: "${appName!}", serviceName: "${serviceName!}"}, "/log/service")
        var html = '<table class="table"> <thead><tr> </tr></thead> <tbody>'
        if (data.length < 30) {
            return
        }
        data = JSON.parse(data)
        data = data["data"]
        for (i = 0; i < data.length; i++) {
            html += '<tr>'
            html += '<td style="border: none;width: 10%;">' + data[i]["time"] + '</td>'
            html += '<td style="border: none;width: 70%">' + data[i]["messages"] + '</td>'
            html += '</tr>'
        }
        html += '</tbody> </table>';
        console.log(html)
        $("#log_content").html(html);
    }


    function to_detail(appName, serviceName) {
        make_page("/app/detail", "/应用管理/应用栈列表/应用详情/" + appName + "/" + serviceName, "?appName=" + appName + "&serviceName=" + serviceName);
    }

    function add_app() {
        make_page("/app/add", "/应用管理/创建应用栈");
    }
    function reload_service(serviceName) {
        make_page("/app/list", "/应用管理/应用栈列表/${appData.appName!}", "?appName =${appData.appName!}&serviceName = " + serviceName);
    }
    function reload(appName) {
        make_page("/app/list", "/应用管理/应用栈列表/" + appName, "?appName=" + appName);
    }
    function change_add_color(obj) {
        $(".btn-class").removeClass("btn-asura")
        $(".btn-class").addClass("btn-asura-default")
        $(obj).removeClass("btn-asura-default")
        $(obj).addClass("btn-asura");
    }
    function service_action(action) {
        if(action == "rollback-service"){
            var url = "/service/rollback/${service.name!}"
            var service = '${service.name!}';
            var result = post({}, url)
            var timer ;
            var count =0;
            timer = setInterval(function () {
                set_containers();
                if(count > 7){
                    clearInterval(timer);
                }
                count += 1;
            },7000);
        }
        if (action == "delete-rollback"){
            var url = "/service/delete/rollback/${service.name!}"
            var service = '${service.name!}';
            var url = "/service/${service.name!}/invalid/delete"
            var service = '${service.name!}';
            var result = post({}, url)
            success(result)
            var timer ;
            var count =0;
            timer = setInterval(function () {
                set_containers();
                if(count > 7){
                    clearInterval(timer);
                }
                count += 1;
            },7000);
        }
    }
</script>
</body>
</html>