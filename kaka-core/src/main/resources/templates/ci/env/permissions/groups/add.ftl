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
            <h5 class="panel-title"><span style="color: #64c5b1;">添加权限组</span></h5>
        </div>
        <div style="border-top: 2px solid #64c5b1;width: 150px;margin-top: 0px;"></div>
        <div class="card-box col-sm-12" style="margin-top: 0px;margin-left: -3px;">
            <form>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="col-sm-3 control-label"><span style="color: red;margin-right: 2px;">*</span>组名称:</label>
                        <div class="col-sm-8">
                            <input type="hidden" name="groupsId" id="groupsId" class="form-control" value="${configs.groupsId!}">
                            <input type="text" name="groupsName"  id="" class="form-control"
                                   value="${configs.groupsName!}">
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
                            <button onclick="save_perm(${configs.groupsId!})" type="button"
                                    class="btn btn-custom  waves-effect waves-light radius">
                                提交
                            </button>
                            <button onclick="to_groups()" type="button"
                                    class="btn btn-default  waves-effect waves-light radius">
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
        function search(key) {
            setFrom("undo_redo","/user/data?id=",key)
        }
        function  setTo() {
            var id = "${configs.username!}"
            if(id.length<1){
                return
            }
            setFrom("undo_redo_to","/user/data?id="+id)
        }

        function setFrom(html_id,url,key) {
            if(!key){
                key=""
            }
            var result = post({key:key},url);
            result = eval(result);
            var data = result["data"]
            var html = ""
            for(i=0;i<data.length;i++){
                html += '<option value='+data[i]["userId"]+'>'+data[i]["userName"]+"</option>";
            }
            $('#'+html_id).html(html);
        }
        setFrom("undo_redo","/user/data?id=")
        setTo();
        function to_groups() {
            make_page("/ci/env/permissions/groups/list", "/应用管理/我的应用/${serviceName!}/管理组", "?groupsId=0");
        }

        function save_perm(id) {
            var url = "/ci/env/permissions/groups/save"
            var data = get_form_data();
            if (id) {
                data["groupsId"] = id
            }
            if(!data["description"]){
                parent.layer.msg("描述信息必须填写", {icon: 2})
                return;
            }
            var to = $('#undo_redo_to').html();
            to = to.match(/"(\d+)"/g);
            to = to+""
            to = to.replace(/"/g,"");
            data["username"] = to;
            var result = post(data, url);
            result = JSON.stringify(result);
            if (result.indexOf("保存应用权限组成功") != -1) {
                parent.layer.msg(result, {icon: 1})
                make_page("/ci/env/permissions/groups/list", "/应用管理/我的应用/${serviceName!}/管理组", "?groupsId="+id);
            } else {
                parent.layer.msg(result, {icon: 2})
            }
        }
    </script>
