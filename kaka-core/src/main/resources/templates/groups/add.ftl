<style>

    .radius{
        border-radius:5px;
    }

    label {
        font-weight: 100 !important;
    }

</style>
<div class="row" xmlns="http://www.w3.org/1999/html" style="margin-top: 80px;margin-left: 8px;">
    <div class="col-lg-12" style="margin-top: 3px">
        <div class="panel panel-heading panel-info">
            <h5 class="panel-title"><span style="color: #64c5b1;">添加主机组</span></h5>
        </div>
        <div style="border-top: 2px solid #64c5b1;width: 150px;margin-top: 0px;"></div>
        <div class="card-box col-sm-12" style="margin-top: 0px;margin-left: -3px;">
            <form>
                <div class="col-md-12">
                    <p>&nbsp;
                    </p>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="col-sm-4 control-label"><span style="color: red;margin-right: 2px;">*</span>组名称:</label>
                        <div class="col-sm-8">
                            <input type="text" name="groupsName" id="groupsName" class="form-control"  value="${configs.groupsName!}">
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <p>
                    <p>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="col-sm-4 control-label"><span style="color: red;margin-right: 2px;">*</span>选择环境</label>
                        <div class="col-sm-8">
                            <select class="form-control" name="entname" onchange="$('#entname').val($(this).val())" >
                                <option value="${configs.entId!}">${configs.entname!}</option>
                                <option value="">--请选择--</option>
                            <#if entnames ?? >
                                <#list entnames as ent>
                                <#if ent ?? >
                                    <option value="${ent.entId}">${ent.entname}</option>
                                </#if>
                                </#list>
                            </#if>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <p>
                    <p>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="col-sm-4 control-label">&nbsp;描述信息:</label>
                        <div class="col-sm-8">
                            <input type="text"  name="description" class="form-control"   value="${configs.description!}">
                        </div>
                    </div>
                </div>
        </div>
                <div class="form-group col-sm-8">
                    <div>
                        <button onclick="save_server(${configs.groupsId!})" type="button" class="btn btn-custom waves-effect waves-light radius">
                            提交
                        </button>
                        <button onclick="save_server(${configs.groupsId!},1)" type="button" class="btn btn-info waves-effect waves-light radius">
                            保存后创建仓库
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <script>

        function save_server(id,createRepo) {
            var url = "/groups/save"
            data = get_form_data();
            if(id){
                data["serverId"] = id
            }
            if(!data["groupsName"] || !data["entname"] ){
                return;
            }
            post(data, url);
            if(createRepo){
                make_page("/registry/server/add", "/镜像管理/仓库配置/添加仓库", "?serverId="+id+"&groupsName="+data["groupsName"]+"&entId"+data["entname"]);
            }else{
                make_page('/groups/list', '/主机管理/主机组管理');
            }
        }
    </script>
