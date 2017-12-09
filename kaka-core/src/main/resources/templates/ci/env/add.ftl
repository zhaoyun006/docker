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
            <h5 class="panel-title"><span style="color: #64c5b1;">添加应用</span></h5>
        </div>
        <div style="border-top: 2px solid #64c5b1;width: 150px;margin-top: 0px;"></div>
        <div class="card-box col-sm-12" style="margin-top: 0px;margin-left: -3px;">
            <form>

                <div class="col-md-12">
                    <label class="col-sm-8 control-label"><span  style="color: red;margin-right: 2px;">*</span>环境资源配置:<span class="font-100"><code>该属性在配置后不可更改，请注意认真填写!</code></span></label>
                </div>

                <#list ents as ent>
                <div class="col-sm-12" style="margin-top: 10px;">
                    ${ent.entname!}
                </div>
                <div class="col-sm-12" style="margin-top: 10px;margin-left: 60px;">
                    <div class="col-md-2"  style="margin-left:-60px;">
                        <div class="form-group" id="">
                            <label class="col-sm-5 control-label" style="margin-top: 3px;">&nbsp;最小:</label>
                            <div class="col-sm-7">
                                <input type="number" <#if configs.envId?? >readonly</#if>    name="containerMin${ent.entId}" maxlength="30" class="form-control" id="containerMin${ent.entId}"
                                       value="${ent.containerMin?c}">
                            </div>
                        </div>
                    </div>
                    <div class="col-md-2"  style="margin-left:0px;">
                        <div class="form-group" id="">
                            <label class="col-sm-5 control-label" style="margin-top: 3px;">最大:</label>
                            <div class="col-sm-7">
                                <input type="number" <#if configs.envId?? >readonly</#if> name="containerMax${ent.entId}" maxlength="30" class="form-control" id="containerMax${ent.entId}"
                                       value="${ent.containerMax?c}">
                            </div>
                        </div>
                    </div>
                    <div class="col-md-2 border2">
                        <label class="col-sm-5 control-label" style="margin-top: 3px;">cpu(核):</label>
                        <div class="col-sm-7">
                        <input type="number" min="1" <#if configs.envId?? >readonly</#if> max="32" name="cpu${ent.entId}" maxlength="30" class="form-control" id="cpu${ent.entId}"
                               value="${ent.cpu?c}">
                        </div>
                    </div>
                    <div class="col-md-3 border2">
                        <label class="col-sm-6 control-label" style="margin-top: 3px;">memory(MB):</label>
                        <div class="col-sm-6">
                        <input type="number"  min="2048" <#if configs.envId?? >readonly</#if>  max="32768" name="memory${ent.entId}" maxlength="30" class="form-control" id="memory${ent.entId}"
                               value="${ent.memory?c}">
                        </div>
                    </div>
                    <div class="col-md-3 border2">
                        <label class="col-sm-3 control-label" style="margin-top: 3px;">网络:</label>
                        <div class="col-sm-9">
                            <input type="text"  readonly name="network${ent.entId}" maxlength="30" class="form-control"
                                   value="${ent.defaultNetwork!}">
                        </div>
                    </div>
                </div>
                </#list>
                <div class="col-md-12" style="margin-top: 15px;">
                </div>
                <div class="form-group col-sm-12">
                    <label for="appName" class="col-sm-2 form-control-label "><span style="color: red;margin-right: 2px;">*</span>主机类型:</label>
                    <div class="radio radio-info col-sm-2" style="margin-top: 0px;width: 60px;margin-left: 0px">
                        <input type="radio" <#if variable!cluterTp == "swarm"> checked </#if> name="cluterTp" onclick="alert_messages('swarm')"   id="swarm" value="swarm">
                        <label for="swarm">
                            swarm
                        </label>
                    </div>
                    <div class="radio radio-info col-sm-2" style="width: 100px;margin-top: 1px;margin-left: 35px;">
                        <input type="radio" <#if variable!cluterTp == "host"> checked </#if>   onclick="alert_messages('host')" name="cluterTp" id="host" value="host">
                        <label for="host">
                            host
                        </label>
                    </div>
                    <div class="radio radio-info col-sm-2" style="width: 180px;margin-top: 1px;">
                        <input type="radio" <#if variable!cluterTp == "kubernetes"> checked </#if>   name="cluterTp" onclick="alert_messages('kubernetes')"  checked id="kubernetes" value="kubernetes">
                        <label for="kubernetes">
                            kubernetes
                        </label>
                    </div>
                </div>
                <div class="col-md-12">
                    <p>
                    <p>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="col-sm-4 control-label"><span style="color: red;margin-right: 2px;">*</span>所属部门:</label>
                        <div class="col-sm-8">
                            <input type="text" name="dept" id="dept" class="form-control" value="${configs.dept!}">
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">选择部门</label>
                        <div class="col-sm-9">
                            <select class="form-control"
                                    onchange="$('#dept').val($(this).find('option:selected').text())">
                                <option value="">--请选择--</option>
                            <#if depts ?? >
                                <#list depts as dept>
                                    <option value="${dept.dept!}">${dept.dept!}</option>
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
                        <label class="col-sm-4 control-label"><span style="color: red;margin-right: 2px;">*</span>应用类型:</label>
                        <div class="col-sm-8">
                            <input type="text" name="itemTp" placeholder="请优先选择列表,列表没有需手动添加" id="itemTp"
                                   class="form-control" value="${configs.itemTp!}">
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">选择类型</label>
                        <div class="col-sm-9">
                            <select class="form-control"
                                    onchange="$('#itemTp').val($(this).find('option:selected').text())">
                                <option value="">--请选择--</option>
                            <#if itemTps ?? >
                                <#list itemTps as itemTp>
                                    <option value="${itemTp.itemTp!}">${itemTp.itemTp!}</option>
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
                <div class="col-md-6" title="只能包含大小写,不能包含特殊字符,不可以修改">
                    <div class="form-group">
                        <label class="col-sm-4 control-label"><span style="color: red;margin-right: 2px;">*</span>应用名称:</label>
                        <div class="col-sm-8">
                            <input type="text" <#if configs.approve?? >readonly</#if> onchange="check_name()" placeholder="应用名称,不能包含特殊字符或." id="serviceName"
                                   name="serviceName" class="form-control" value="${configs.serviceName!}">
                        </div>
                    </div>
                </div>
                <div class="col-md-6" title="应用域名，没有可以不写,但是不可以修改">
                    <div class="form-group">
                        <label class="col-sm-4 control-label">应用域名:</label>
                        <div class="col-sm-8">
                            <input type="text"  name="domain" placeholder="应用的访问域名信息" class="form-control"
                                   value="${configs.domain!}">
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <p>
                    <p>
                </div>

                <div class="form-group col-sm-6" title="服务如果是http的服务,可以使用nginx进行负载均衡">
                    <label for="appName" class="col-sm-3 control-label" style="margin-top: 10px;color: #000"><span
                            style="color: red;margin-right: 2px;">*</span>负载方式:</label>
                    <div class="radio radio-custom col-sm-3" style="width: 150px;margin-left: 50px;">
                        <input type="radio"
                               name="loadblanceTp"  <#if configs.loadblanceTp??><#if configs.loadblanceTp == "nginx">chcked</#if></#if>
                               id="loadblanceTp1" value="nginx">
                        <label for="loadblanceTp1">
                            创建负载均衡
                        </label>
                    </div>
                    <div class="radio radio-custom col-sm-4" style="width: 130px;    margin-top: 10px;margin-left: 0px">
                        <input type="radio" name="loadblanceTp"
                               <#if configs?? >checked</#if>  <#if configs.loadblanceTp??><#if configs.loadblanceTp == "">chcked</#if></#if>
                               id="loadblanceTp2" value="">
                        <label for="loadblanceTp2">
                            不需要负载
                        </label>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="col-sm-4 control-label"><span style="color: red;margin-right: 2px;">*</span>应用端口:</label>
                        <div class="col-sm-8">
                            <input type="text" name="port" placeholder="8081" class="form-control"
                                   value="${gsonData.port!}">
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <p>
                    <p>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="col-sm-4 control-label">代码路径:</label>
                        <div class="col-sm-8">
                            <input type="text" name="codePath" placeholder="代码路径,该参数可以传给编译脚本" class="form-control"
                                   value="${configs.codePath!}">
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="col-sm-4 control-label">代码分支:</label>
                        <div class="col-sm-8">
                            <input type="text" name="codeBranch" placeholder="代码分支,该参数可以传给编译脚本" class="form-control"
                                   value="${configs.codeBranch!}">
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <p>
                    <p>
                </div>
                <div class="form-group col-sm-12" title="脚本获取方式">
                    <label for="appName" class="col-sm-3 control-label" style="margin-top: 10px;color: #000"><span
                            style="color: red;margin-right: 2px;">*</span>镜像方式:</label>
                    <div class="radio radio-custom col-sm-3" style="width: 100px;margin-left: -80px;">
                        <input type="radio"
                               name="imagesTp"  <#if configs.imagesTp??><#if configs.imagesTp == "1">chcked</#if></#if>
                               id="imagesTp1" value="1">
                        <label for="imagesTp1">
                            手动上传
                        </label>
                    </div>
                    <div class="radio radio-custom col-sm-3" style="width: 100px;    margin-top: 10px;margin-left: 0px">
                        <input type="radio" name="imagesTp"
                               <#if configs?? >checked</#if>  <#if configs.imagesTp??><#if configs.imagesTp == "2">chcked</#if></#if>
                               id="imagesTp2" value="2">
                        <label for="imagesTp2">
                            脚本编译
                        </label>
                    </div>
                    <div class="radio radio-custom col-sm-3" style="width: 100px; margin-top: 10px;margin-left: 0px">
                        <input type="radio"
                               name="imagesTp"  <#if configs.imagesTp??><#if configs.imagesTp == "3">chcked</#if></#if>
                               id="imagesTp3" value="3">
                        <label for="imagesTp3">
                            公共镜像
                        </label>
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">&nbsp;健康检查脚本:</label>
                        <div class="col-sm-10">
                            <textarea type="text" placeholder="可以写一个简单的脚本,通过返回值判断应用是否正常" name="healthScript"
                                      class="form-control">${configs.healthScript!}</textarea>
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <p>
                    <p>
                </div>
                <div class="col-md-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">&nbsp;镜像编译脚本:</label>
                        <div class="col-sm-10">
                            <textarea type="text"
                                      placeholder="使用该脚本执行镜像编译工作,镜像编译工作在镜像编译的服务器上执行,所依赖的文件可以从网络下载或者在编译服务器上面手动上传"
                                      name="buildScript" class="form-control">${configs.buildScript!}</textarea>
                        </div>
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
                            <input type="text" name="description" class="form-control" value="${configs.description!}">
                        </div>
                    </div>
                </div>
                </di>
                <div class="form-group col-sm-8">
                    <div>
                        <button onclick="save_env(${configs.envId!})" type="button"
                                class="btn btn-custom waves-effect waves-light radius">
                            提交
                        </button>
                        <button type="button" class="btn btn-default waves-effect m-l-5 radius "
                                onclick="   make_page('/ci/env/list', '/应用管理/我的应用');">
                            取消
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <script>

        function check_name() {
            var name = $("#serviceName").val();
            var url = "/ci/env/check/" + name
            var result = post({}, url);
            if (result != "ok") {
                parent.layer.msg("名称" + name + "已经被使用了", {icon: 2})
                $("#serviceName").val("");
            }
        }
        function save_env(id) {
            var url = "/ci/env/save"
            var data = get_form_data();
            if (id) {
                data["envId"] = id
            }
            if($("#loadblanceTp1").is(":checked")){
                data["loadblanceTp"] = "nginx"
            }

            if($("#swarm").is(":checked")){
                data["cluterTp"] = "swarm"
            }
            if($("#host").is(":checked")){
                data["cluterTp"] = "host"
            }
            if($("#kubernetes").is(":checked")){
                data["cluterTp"] = "kubernetes"
            }
            if (!data["dept"] || !data["itemTp"]) {
                return
            }
            if (!data["port"]){
                parent.layer.msg("端口必须填写", {icon: 2})
                return;
            }
            data["gsonData"] = JSON.stringify(data);

            var result = post(data, url);
            result = JSON.stringify(result);
            if (result.indexOf("error") != -1) {
                parent.layer.msg(result, {icon: 2})
                return;
            } else {
                parent.layer.msg(result, {icon: 1})
            }
//            make_page('/ci/env/list', '/应用管理/我的应用');
        }
    </script>
