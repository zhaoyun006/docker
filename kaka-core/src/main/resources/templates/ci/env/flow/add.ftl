<style>

    .radius {
        border-radius: 5px;
    }

    label {
        font-weight: 100 !important;
    }
    select[multiple],
    select[size] {
        height: 200px !important;
    }
    select[multiple] option, select[size] option {
        padding: 2px 12px;
        border-radius: 2px;
    }
</style>
<div class="row" xmlns="http://www.w3.org/1999/html" style="margin-top: 80px;margin-left: 8px;">
    <div class="col-lg-12" style="margin-top: 3px">
        <div class="panel panel-heading panel-info">
            <h5 class="panel-title"><span style="color: #64c5b1;">添加环境流程</span></h5>
        </div>
        <div style="border-top: 2px solid #64c5b1;width: 150px;margin-top: 0px;"></div>
        <div class="card-box col-sm-12" style="margin-top: 0px;margin-left: -3px;">
            <form>
                <div class="form-group" style="margin-left: 10px;">
                    <label class="col-sm-1 control-label">选择应用</label>
                    <div class="col-sm-6" style="margin-left: 10px;">
                        <select class="form-control" name="envId">
                            <#if configs?? >
                                <option value="${configs.envId!}">${configs.serviceName!}</option>
                                <#else >
                                    <option value="">--请选择--</option>
                            </#if>
                                <option title="所有没有配置的环境使用该发布流程" value="0">--默认流程--</option>

                            <#if services ?? >
                            <#list services as service>
                                <option value="${service.envId!}">${service.serviceName!}</option>
                            </#list>
                        </#if>
                        </select>
                    </div>
                </div>
                <div  title="每个环境至少选择一个组" class="col-md-12" style="margin-top: 20px;">
                    <label class="col-sm-3 control-label">可选择环境</label>
                    <label class="col-sm-3"><input placeholder="搜索环境" onkeyup="search($(this).val())"
                                                   class="fa-border form-control"
                                                   style="margin-top:-5px;margin-left: -165px;"></label>
                    <label class="col-sm-3 control-label">已选择环境</label>
                </div>
                <div class="col-md-12" style="margin-top: 5px;">
                    <div class="col-xs-5">
                        <select name="from" id="undo_redo" class="form-control" size="10" multiple="multiple">
                        </select>
                    </div>
                    <div class="col-xs-2" style="margin-top: 50px;">
                        <button type="button" id="undo_redo_rightSelected" class="btn btn-primary btn-block"><i
                                class="glyphicon glyphicon-chevron-right"></i></button>
                        <button type="button" id="undo_redo_leftSelected" class="btn btn-warning btn-block"><i
                                class="glyphicon glyphicon-chevron-left"></i></button>
                    </div>
                    <div class="col-xs-5">
                        <select name="to" id="undo_redo_to" class="form-control" size="10"  style="height:200px !important;"  multiple="multiple"></select>
                    </div>
                    <div class="col-md-12">
                        <p>
                        <p>
                        <code>选择环境后，发布顺序将严格按照该顺手发布和推送镜像,所有镜像通过qa审核后,即可发布和推送到上一个环境</code>
                    </div>
                    <div class="col-md-12 " style="margin-top: 10px;">
                        <div class="form-group">
                            <label class="col-sm-1 control-label">备注信息:</label>
                            <div class="col-sm-6">
                                <input type="text" name="description"  class="form-control" value="${configs.description!}">

                            </div>
                        </div>
                    </div>
                    <div class="col-sm-12"><br></div>
                    <div class="form-group col-sm-8">
                        <div>
                            <button onclick="save_flow(${configs.flowId!})" type="button"
                                    class="btn btn-custom  waves-effect waves-light radius">
                                提交
                            </button>
                            <button onclick="  make_page('/ci/env/flow/list', '/持续集成/流程配置');" type="button"
                                    class="btn btn-default waves-effect waves-light radius">
                                取消
                            </button>
                        </div>
                    </div>
            </form>
        </div>
    </div>
</div>
<script>
    jQuery(document).ready(function ($) {
        $('#undo_redo').multiselect({
            keepRenderingSort: true
        });
    });
    function search(key) {
        setFrom("undo_redo","/ent/data?id=", key);
    }
    function setFrom(html_id,url,key) {
        if(!key){
            key=""
        }
        var to = $('#undo_redo_to').html();
        to = to.match(/"(\d+)"/g);
        to = to+""
        to = to.replace(/"/g,"");
        var result = post({key:key,draw:1,start:1,length:2000},url);
        result = eval(result);
        var data = result["data"]
        var html = ""
        var map = {};
        var groupsIds = "${configs.releaseOrder!}"

        for(i=0;i<data.length;i++){
            var groupsId = data[i]["entId"];
            map[groupsId] = '<option value='+groupsId+'>'+data[i]["entname"]+"</option>";
            if(!to){
                html += '<option value='+groupsId+'>'+data[i]["entname"]+"</option>";

            }else{
                if (to.indexOf(","+groupsId) != -1 || to.indexOf(","+groupsId+",") != -1 || to.indexOf(groupsId+",") != -1 || to == groupsId){
                    continue;
                }
                html += '<option value='+groupsId+'>'+data[i]["entname"]+"</option>";
            }
        }
        if(groupsIds){
            var html = "";
            var gids = groupsIds.split(",");
            for (i=0;i<gids.length;i++){
                html += map[gids[i]];
            }
        }
        $('#'+html_id).html(html);
    }


    var groupsIds = "${configs.releaseOrder!}"
    if (groupsIds){
        setFrom("undo_redo_to", "/ent/data?ids="+groupsIds);
    }
    setFrom("undo_redo","/ent/data?id=");

    function save_flow(id) {
        var url = "/ci/env/flow/save"
        var data = get_form_data();
        if (id) {
            data["flowId"] = id;
        }else{
            data["flowId"] = 0
        }
        var to = $('#undo_redo_to').html();
        to = to.match(/"(\d+)"/g);
        to = to+""
        to = to.replace(/"/g,"");
        data["releaseOrder"] = to;
        var result = post(data, url);
        if(!result){
            parent.layer.msg("保存失败", {icon: 2})
            return;
        }

        result = JSON.stringify(result);
        if (result.indexOf("添加环境流程成功") != -1) {
            parent.layer.msg(result, {icon: 1})
            make_page('/ci/env/flow/list', '/持续集成/流程配置');
        } else {
            parent.layer.msg(result, {icon: 2})
        }
    }
</script>