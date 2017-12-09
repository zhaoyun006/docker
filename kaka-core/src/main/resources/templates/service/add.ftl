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

    .form-group {
        margin-bottom: 8px;
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
        border: 1px solid #34d3ed;
        border-radius: 3px;
        color: #34d3ed;
        width: 150px;
    }

    .label {
        font-weight: 500;
        font-size: 12px;
        color: #000;
    }

    .visible-xs-color {
        color: #aaa;
    }

    .switchery-small {
        border-radius: 20px;
        height: 20px;
        width: 44px;
    }
</style>

<div class="row" xmlns="http://www.w3.org/1999/html" style="margin-top: 80px;margin-left: 8px;">
    <div class="col-lg-12" style="margin-top: 10px">
        <div class="panel panel-heading panel-info">
            <h6 class="panel-title"><span
                    style="color: #64c5b1;    font-size: 16px;font-weight: 300;">${(readonly??)?then('服务升级','创建服务')}</span>
            </h6>
        </div>
        <div style="border-top: 2px solid #64c5b1;width: 150px;margin-top: 0px;"></div>
        <div class="card-box col-sm-12" style="margin-top: 0px;margin-left: -3px;">
            <form>
                <div class="form-group col-sm-4" style="margin-left: -10px;">
                    <label for="appName" class="col-sm-5 form-control-label label">名称<span
                            class="text-danger">*</span></label>
                    <div class="col-sm-7">
                        <input type="text" style="margin-left: -15px;" class="form-control input-sm " ${readonly!}
                               value="${serviceName!}" name="name" required id="appName"
                               placeholder="名称"/>
                    </div>
                </div>
                <div class="form-group col-sm-4">
                    <label for="appName" class="col-sm-4 form-control-label label">环境名称<span
                            class="text-danger">*</span></label>
                    <div class="col-sm-8">
                        <input name="entId" type="hidden" value="${service.entId!}">
                        <input type="text" class="form-control input-sm " ${readonly!} style="margin-left: 8px;"
                               value="${service.entname!}" name="entname" required id="entname"
                               placeholder="名称"/>
                    </div>
                </div>

                <div class="form-group col-sm-8">
                    <label class="col-sm-2 form-control-label label">描述:</label>
                    <div class="col-sm-10">
                        <textarea rows="3" class="form-control " name="appDescription"
                                  placeholder="应用描述信息">${(service??)?then("${service.appDescription!}","")}</textarea>
                    </div>
                </div>

                <div class="form-group col-sm-8">
                    <label class="col-sm-2 form-control-label label">镜像:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control input-sm " name="image" required id="image"
                               value="${(service??)?then("${service.image!}","")}"
                               placeholder="名称"/>
                    </div>
                </div>
                <div class="form-group col-sm-8">
                    <label class="col-sm-2 form-control-label label">初始化容器:</label>
                    <div class="col-sm-2">
                        <input type="number" class="form-control input-sm" ${readonly!}
                               value="<#if service ??>${service.replicas!}</#if>" name="replicas" required id="replicas"
                               placeholder="1"/>
                    </div>
                </div>
                <div class="form-group col-sm-8">
                    <label class="col-sm-2 form-control-label label">网络选择:</label>
                    <div class="col-sm-10">
                        <select class="form-control input-sm" name="network" data-live-search="true" style="width: 200px;">
                            <option value="${networkName!}">${networkName!}</option>
                        <#list networks as network>
                            <option value="${network.name!}">${network.name!}</option>
                        </#list>
                        </select>
                    </div>
                </div>
            <#if service ??>
                <div class="form-group col-sm-8" title="俩个容器中间升级的间隔时间">
                    <label class="col-sm-2 form-control-label label">升级间隔:</label>
                    <div class="col-sm-2">
                        <input type="number" class="form-control input-sm" value="10" name="updateDelay" required
                               id="updateDelay"
                               placeholder="10"/>
                    </div>
                    <div class="col-sm-2">
                        <span class="fa">秒</span>
                    </div>
                </div>
                <div class="form-group col-sm-8" title="同时升级的容器数量">
                    <label class="col-sm-2 form-control-label label">并行升级:</label>
                    <div class="col-sm-2">
                        <input type="number" class="form-control input-sm" value="1" name="updateParallelism" required
                               id="updateParallelism"
                               placeholder="1"/>
                    </div>
                    <div class="col-sm-2">
                        <span class="fa">个</span>
                    </div>
                </div>
                <div class="form-group col-sm-8" title="更新失败后的动作">
                    <label for="appName" class="col-sm-2 form-control-label label" style="margin-top: 10px;">失败动作:</label>
                    <div class="radio radio-custom col-sm-3" style="width: 100px;">
                        <input type="radio" name="updateFailureAction" checked id="updateFailureAction1" value="pause">
                        <label for="updateFailureAction1">
                            暂停
                        </label>
                    </div>
                    <div class="radio radio-custom col-sm-3" style="width: 100px;    margin-top: 10px;margin-left: -30px">
                        <input type="radio" name="updateFailureAction" id="updateFailureAction2" value="continue">
                        <label for="updateFailureAction2">
                            继续
                        </label>
                    </div>
                    <div class="radio radio-custom col-sm-3" style="width: 100px; margin-top: 10px;margin-left: -30px">
                        <input type="radio" name="updateFailureAction" id="updateFailureAction3" value="rollback">
                        <label for="updateFailureAction3">
                            回滚
                        </label>
                    </div>
                </div>
            </#if>
        </div>
    </div>
</div>
<#include "/service/add_service.ftl">
</div>
<div class="form-group col-sm-8">
    <div>
        <button onclick="service_save()" type="button"
                class="btn btn-custom waves-effect waves-light radius">
            提交
        </button>
        <button type="reset" class="btn btn-default waves-effect m-l-5 radius ">
            重置
        </button>
    </div>
</div>

</form>

<script>

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
        $("#log_content").html(html);
    }



    function get_value(key, data) {
        var v = $("#" + key).val()
        if (v) {
            data[key] = v;
        }
        return data;
    }
    function service_save() {
        var data = get_form_data();
        var env = ""
        var port = ""
        var mount = ""
        for (i = 1; i < 30; i++) {
            e = $("#env-" + i).val()
            v = $("#env-value-" + i).val()
            if (e && v) {
                env += " --env " + e + "=" + v
            }
            if (i < 6) {
                pe = $("#port-" + i).val()
                pv = $("#port-value-" + i).val()
                if (pe && pv) {
                    port += " --publish " + pe.trim() + ":" + pv.trim()
                }
                me = $("#mount-" + i).val()
                mv = $("#mount-value-" + i).val()
                if (me && mv) {
                    mount += " --mount type=bind,source=" + me.trim() + ",destination=" + mv.trim()
                }
            }
        }
        data["env"] = env;
        if (port.length > 1) {
            data["port"] = port;
        }

        data = get_value("limitMemory", data)
        if (data["limitMemory"]){
            data["limitMemory"] = parseInt(data["limitMemory"]) * 1024 * 1024
        }
        data = get_value("limitCpu", data)
        var scaleOn = $("#scaleOn").is(":checked");
        if (scaleOn) {
            data = get_value("scaleCpu", data)
            data = get_value("scaleMem", data)
            data = get_value("scaleMin", data)
            data = get_value("scaleMax", data)
            data["scaleOn"] = 1
        }
        if (mount.length > 0) {
            data["mount"] = mount
        }
        data["operUser"] = '${operUser!}'

        data["appName"] = "${appName!}"
        if ("${serviceName!}") {
            // 更新
            var updateFailureAction =$('input:radio[name="updateFailureAction"]:checked').val();
            data["updateFailureAction"] = updateFailureAction
            data["updateDelay"] = parseInt($("#updateDelay").val()) + "s"
            data["updateParallelism"] = parseInt($("#updateParallelism").val())
            var url = "/service/save?update=1";
        } else {
            // 创建
            var url = "/service/save";
        }
        if (data["port"]) {
            data["port"] = data["port"].replace(/--publish/g, " --publish ")
        }
        var gson_data = JSON.stringify(data)
        data["gsonData"] = gson_data;
        var result = post(data, url);
        success(result + "<br><br>", 5000)
        make_page("/app/list", "/应用管理/应用栈列表/${appName!}", "?appName=${appName!}");
    }


</script>
</body>
</html>