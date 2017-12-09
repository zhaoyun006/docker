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
            <h5 class="panel-title"><span style="color: #64c5b1;">添加仓库</span></h5>
        </div>
        <div style="border-top: 2px solid #64c5b1;width: 150px;margin-top: 0px;"></div>
        <div class="card-box col-sm-12" style="margin-top: 0px;margin-left: -3px;">
            <form>
                <div class="col-md-12">
                    <p>&nbsp;
                        <code>每个组可以使用一个仓库,用前缀区分，也可以不同环境使用不同的仓库,需要每个组必须有一个仓库配置</code>
                    <br>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="col-sm-4 control-label"><span style="color: red;margin-right: 2px;">*</span>所属组:</label>
                        <div class="col-sm-8">
                            <input type="hidden" name="groupsId" id="groupsId" class="form-control"  value="${configs.groupsId!}">
                            <input type="text" readonly name="groups" id="groups" class="form-control"  value="${configs.groups!}">
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">选择组</label>
                        <div class="col-sm-9">
                            <select class="form-control"  onchange="$('#groups').val($(this).find('option:selected').text());$('#groupsId').val($(this).val())" id="docker_server_groups">
                                <option value="">--请选择--</option>
                            <#if groups ?? >
                                <#list groups as group>
                                    <option value="${group.groupsId!}">${group.groupsName!}</option>
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
                <div class="col-md-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><span style="color: red;margin-right: 2px;">*</span>服务器地址:</label>
                        <div class="col-sm-10">
                            <input type="text" placeholder="docker仓库的服务器地址,使用v2版本,无密码验证的,服务必须为https的"  name="serverAddress" class="form-control"   value="${configs.serverAddress!}">
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <p>
                    <p>
                </div>
                <div class="col-md-12" title="如果没有域名的话就不用写了">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">&nbsp;域名:</label>
                        <div class="col-sm-10">
                            <input type="text"  name="serverDomain" class="form-control"   value="${configs.serverDomain!}">
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <p>
                    <p>
                </div>
                <div class="col-md-12" title="仓库登录用户名">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">&nbsp;用户名:</label>
                        <div class="col-sm-10">
                            <input type="text"  name="username" class="form-control"   value="${configs.username!}">
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <p>
                    <p>
                </div>
                <div class="col-md-12" title="仓库登录密码">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">&nbsp;密码:</label>
                        <div class="col-sm-10">
                            <input type="text"  name="password" class="form-control"   value="${configs.password!}">
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <p>
                    <p>
                </div>
                <div class="col-md-12" title="仓库前缀,在镜像下载或上传时,镜像下载格式: 仓库地址/前缀/服务名:tag
$user 在发布时会替换成 用户名 ">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">&nbsp;前缀:</label>
                        <div class="col-sm-6">
                            <input type="text" <#if configs.prefix?? >readonly</#if>  name="prefix" class="form-control"  placeholder="如 onlinne test qa develop $user 镜像下载格式: 仓库地址/前缀/服务名:tag"  value="${configs.prefix!}">
                        </div>
                        <div class="col-sm-4">
                        </div>
                        <code>配置后不可修改，如修改删除后再重新添加</code>
                        </div>
                </div>
                <div class="col-md-12">
                    <p>
                    <p>
                </div>
                <div class="col-md-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">&nbsp;描述信息:</label>
                        <div class="col-sm-10">
                            <input type="text"  name="description" class="form-control"   value="${configs.description!}">
                        </div>
                    </div>
                </div>
        </div>
                <div class="form-group col-sm-8">
                    <div>
                        <button onclick="save_server(${configs.serverId!})" type="button" class="btn btn-custom waves-effect waves-light radius">
                            提交
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <script>
        function save_server(id) {
            url = "/registry/server/save"
            data = get_form_data();
            if(id){
                data["serverId"] = id
            }
            result = post(data, url);
            make_page('/registry/server/list', '/镜像管理/仓库配置');
        }
    </script>
