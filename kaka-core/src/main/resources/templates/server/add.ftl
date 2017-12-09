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
            <h5 class="panel-title"><span style="color: #64c5b1;">添加主机</span></h5>
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
                        <label class="col-sm-4 control-label"><span style="color: red;margin-right: 2px;">*</span>所属组:</label>
                        <div class="col-sm-8">
                            <input type="hidden" name="groupsId" id="groupsId" class="form-control"  value="${configs.groupsId!}">

                            <input type="text" name="groups" readonly id="groups" class="form-control"  value="${configs.groups!}">
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
                            <input type="text" placeholder="docker服务器的地址"  name="serverAddress" class="form-control"   value="${configs.serverAddress!}">
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <p>
                    <p>
                </div>
                <div class="col-md-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><span style="color: red;margin-right: 2px;">*</span>API端口:</label>
                        <div class="col-sm-10">
                            <input type="number"  max="65535" min="1024" name="apiPort" placeholder="agent的api端口号,不是docker的" class="form-control"   value="${configs.apiPort?default("9999")}">
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <p>
                    <p>
                </div>
                <div class="form-group col-sm-12">
                    <label for="appName" class="col-sm-2 form-control-label "><span style="color: red;margin-right: 2px;">*</span>主机类型:</label>
                    <div class="radio radio-info col-sm-2" style="margin-top: 0px;width: 60px;margin-left: 0px">
                        <input type="radio" name="cluterTp" onclick="alert_messages('swarm')"  checked id="swarm" value="swarm">
                        <label for="swarm">
                            swarm
                        </label>
                    </div>
                    <div class="radio radio-info col-sm-2" style="width: 100px;margin-top: 1px;margin-left: 35px;">
                        <input type="radio" onclick="alert_messages('host')" name="cluterTp" id="host" value="host">
                        <label for="host">
                            host
                        </label>
                    </div>
                    <div class="radio radio-info col-sm-2" style="width: 180px;margin-top: 1px;">
                        <input type="radio" name="cluterTp" onclick="alert_messages('kubernetes')"  id="kubernetes" value="kubernetes">
                        <label for="kubernetes">
                            kubernetes
                        </label>
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">&nbsp;描述信息:</label>
                        <div class="col-sm-10">
                            <input type="text"  name="comm" class="form-control"   value="${configs.comm!}">
                        </div>
                    </div>
                </div>
        </div>
                <div class="form-group col-sm-8">
                    <div>
                        <button onclick="save_server(${configs.serverId!})" type="button" class="btn btn-custom waves-effect waves-light radius">
                            提交
                        </button>
                        <button type="reset"  onclick="make_page('/server/list', '/主机管理/主机列表');" class="btn btn-default waves-effect m-l-5 radius ">
                            取消
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <script type="text/javascript" src="/static/plugins/parsleyjs/parsley.min.js"></script>
    <script>

        function alert_messages(name) {
            var result;
            if (name == "host"){
                result = "主机模式运行,只能创建容器,不能创建serivce和编排,只基于稳定运行的容器服务";
            }
            if (name == "swarm"){
                result = "基于swarm,可以使用编排特性,动态伸缩等高级特性";
            }
            if(name == "kubernetes"){
                result = "基于kubernetes,可以使用编排特性,动态伸缩等高级特性";
            }
            parent.layer.msg(result, {icon: 1})
        }

        function save_server(id) {
            url = "/server/save"
            data = get_form_data();
            if(id){
                data["serverId"] = id
            }
            if (!data["groups"] || !data["groupsId"] || !data["serverAddress"] || !data["apiPort"]){
                return
            }
            result = post(data, url);
            make_page('/server/list', '/主机管理/主机列表');
        }
    </script>
