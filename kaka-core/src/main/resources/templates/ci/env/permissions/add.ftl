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
            <h5 class="panel-title"><span style="color: #64c5b1;">添加权限</span></h5>
        </div>
        <div style="border-top: 2px solid #64c5b1;width: 150px;margin-top: 0px;"></div>
        <div class="card-box col-sm-12" style="margin-top: 0px;margin-left: -3px;">
            <form>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="col-sm-3 control-label"><span style="color: red;margin-right: 2px;">*</span>应用名称:</label>
                        <div class="col-sm-8">
                            <input type="hidden" name="envId" id="envId" class="form-control" value="${configs.envId!}">
                            <input type="text" name="" readonly id="" class="form-control"
                                   value="${configs.serviceName!}">
                        </div>
                    </div>
                </div>
                <div class="col-md-12" style="margin-top: 20px;">
                    <label class="col-sm-3 control-label">可选择用户</label>
                    <label class="col-sm-3"><input placeholder="搜索用户" onkeyup="search($(this).val())"
                                                   class="fa-border form-control"
                                                   style="margin-top:-5px;margin-left: -135px;"></label>
                    <label class="col-sm-3 control-label">已选择用户</label>
                </div>
                <div class="col-md-12" style="margin-top: 5px;">
                    <div class="col-xs-5">
                        <select name="from" id="undo_redo" class="form-control" size="10" multiple="multiple">
                        </select>
                    </div>
                    <div class="col-xs-2">
                        <button type="button" style="margin-top: 10px;" id="undo_redo_undo"
                                class="btn btn-primary btn-block">undo
                        </button>
                        <button type="button" id="undo_redo_rightSelected" class="btn btn-default btn-block"><i
                                class="glyphicon glyphicon-chevron-right"></i></button>
                        <button type="button" id="undo_redo_leftSelected" class="btn btn-default btn-block"><i
                                class="glyphicon glyphicon-chevron-left"></i></button>
                        <button type="button" id="undo_redo_redo" class="btn btn-warning btn-block">redo</button>
                    </div>
                    <div class="col-xs-5">
                        <select name="to" id="undo_redo_to" class="form-control" size="10"  style="height:200px !important;"  multiple="multiple"></select>
                    </div>
                    <div class="col-md-12">
                        <p>
                        <p>
                    </div>
                    <div class="col-md-12">
                        <label class="col-sm-3 control-label">可选择组</label>
                        <label class="col-sm-3"><input placeholder="搜索组" onkeyup="searchGroup($(this).val())"
                                                       class="fa-border form-control"
                                                       style="margin-top:-5px;margin-left: -140px;"></label>
                        <label class="col-sm-3 control-label">已选择组</label>
                    </div>
                    <div class="col-md-12" style="margin-top: 5px;">
                        <div class="col-xs-5">
                            <select name="from" id="undo_redo_groups" class="form-control" size="10" style="height:200px !important;" multiple="multiple">
                            </select>
                        </div>
                        <div class="col-xs-2">
                            <button type="button" style="margin-top: 10px;" style="height:200px !important;"  id="undo_redo_groups_undo"
                                    class="btn btn-primary btn-block">undo
                            </button>
                            <button type="button" id="undo_redo_groups_rightSelected" class="btn btn-default btn-block">
                                <i class="glyphicon glyphicon-chevron-right"></i></button>
                            <button type="button" id="undo_redo_groups_leftSelected" class="btn btn-default btn-block">
                                <i class="glyphicon glyphicon-chevron-left"></i></button>
                            <button type="button" id="undo_redo_groups_redo" class="btn btn-warning btn-block">redo
                            </button>
                        </div>
                        <div class="col-xs-5">
                            <select name="to" id="undo_redo_groups_to" class="form-control" size="10"  style="height:200px !important;"  multiple="multiple"></select>
                        </div>
                        <div class="col-md-12">
                            <p>
                            <p>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-sm-4 control-label">&nbsp;描述信息:</label>
                                <div class="col-sm-8">
                                    <input type="text" name="description" class="form-control"
                                           value="${configs.description!}">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group col-sm-8">
                        <div>
                            <button onclick="save_perm(${configs.permissionsId!})" type="button"
                                    class="btn btn-custom  waves-effect waves-light radius">
                                提交
                            </button>
                            <button onclick="to_list()" type="button"
                                    class="btn btn-default waves-effect waves-light radius">
                                取消
                            </button>
                        </div>
                    </div>
            </form>
        </div>
    </div>
    <script>

        jQuery(document).ready(function ($) {
            $('#undo_redo').multiselect({
                keepRenderingSort: true
            });
        });
        jQuery(document).ready(function ($) {
            $('#undo_redo_groups').multiselect({
                keepRenderingSort: true
            });
        });

        function search(key) {
            setFrom("undo_redo","/user/data?id=",key,"userId", "userName")
        }
        function searchGroup(key) {
            setFrom("undo_redo_groups_to","/ci/env/permissions/groups/select?id=", key, "groupsId", "groupsName")
        }

        function  setTo() {
            var id = "${configs.username!}"
            if(id.length<1){
                return
            }
            setFrom("undo_redo_to","/user/data?id="+id,"","userId", "userName")
            var id = "${configs.groupsName!}"
            if(id.length<1){
                return
            }
            setFrom("undo_redo_groups_to","/ci/env/permissions/groups/select?id="+id, "", "groupsId", "groupsName")
        }

        function setFrom(html_id,url,key,k,v) {
            if(!key){
                key=""
            }
            var result = post({key:key},url);
            result = eval(result);
            var data = result["data"]
            var html = ""
            for(i=0;i<data.length;i++){
                html += '<option value='+data[i][k]+'>'+data[i][v]+"</option>";
            }
            $('#'+html_id).html(html);
        }
        setFrom("undo_redo","/user/data?id=","", "userId", "userName")
        setFrom("undo_redo_groups","/ci/env/permissions/groups/select?id=", "", "groupsId", "groupsName")
        setTo();
        function to_list() {
            make_page("/ci/env/permissions/list", "/应用管理/我的应用/权限管理", "?envId=${envId}");
        }

        function get_select(id) {
            var to = $('#'+id).html();
            to = to.match(/"(\d+)"/g);
            to = to+""
            to = to.replace(/"/g,"");
            return to;
        }

        function save_perm(id) {
            var url = "/ci/env/permissions/save"
            var data = get_form_data();
            if (id) {
                data["permissionsId"] = id
            }
            if(!data["description"]){
                parent.layer.msg("描述信息必须填写", {icon: 2})
                return;
            }

            data["username"] = get_select("undo_redo_to");
            data["groupsName"] = get_select("undo_redo_groups_to");
            var result = post(data, url);
            result = JSON.stringify(result);
            if (result.indexOf("保存用户权限成功") != -1) {
                parent.layer.msg(result, {icon: 1})
                make_page("/ci/env/permissions/list", "/应用管理/我的应用/权限管理", "?envId=${envId}");
            } else {
                parent.layer.msg(result, {icon: 2})
            }
        }
    </script>
