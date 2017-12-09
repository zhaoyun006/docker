<style>

    .radius {
        border-radius: 5px;
    }

    label {
        font-weight: 100 !important;
    }

    .top {
        margin-top: 13px;
    }
</style>
<div class="modal fade" id="app_add_html" tabindex="-1"
     role="dialog" style="margin-top: 36px;"
     data-backdrop="false">
    <div class="modal-dialog"
         style="margin-top: 0px; width: 50%; ">
        <div class="modal-content"
             style=" width: 100%; ">
            <div class="modal-header" style="    border-bottom-width: 0px !important;">
                <button type="button" class="close" style="color: #ccc;  right: 8px;top:8px;background-color:#ffffff;"
                        data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <div class="col-lg-12" style="margin-top: -23px">
                    <div class="panel panel-heading panel-info">
                        <h5 class="panel-title"><span style="color: #64c5b1;">更新服务</span></h5>
                    </div>
                    <div style="border-top: 2px solid #64c5b1;width: 150px;margin-top: 0px;"></div>
                    <div class="card-box col-sm-12" style="margin-top: 0px;margin-left: -3px;">
                        <form>
                            <code>根据不同的环境更新服务</code>
                            <div class="col-md-12"><br>
                            </div>
                            <div class="col-sm-12 top">
                                <label class="col-sm-2 control-label">服务名</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" readonly name="serviceName" required
                                           value="${serviceName!}"
                                           style=" border: none; margin-top: -10px;  background-color: #ffffff;"
                                           placeholder="50"/>
                                </div>
                            </div>
                            <div class="col-sm-12 top">
                                <label class="col-sm-2 control-label">应用域名</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control " readonly name="domain" required
                                           value="${domain!}"
                                           style="border: none;margin-top: -10px;    background-color: #ffffff;"
                                           placeholder="50"/>
                                </div>
                            </div>
                            <div class="col-md-12 top">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">选择版本</label>
                                    <div class="col-sm-10">
                                        <select class="form-control" name="version" onchange="update_service(${envId},$(this).val())">
                                            <option value="${selectVersion!}">${selectVersion!}</option>
                                        <#if imageTags ?? >
                                            <#list imageTags as tag>
                                                <#if tag ?? >
                                                    <option value="${tag.tag}">${tag.tag}</option>
                                                </#if>
                                            </#list>
                                        </#if>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12" style="margin-top: 25px;">
                            </div>
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">选择环境</label>
                                    <div class="col-sm-10">
                                        <table>
                                        <#if entnames ?? >
                                            <#list entnames as ent>
                                                <#if ent ?? >
                                                    <tr>
                                                        <th>
                                                            <div title="${ent.title!}" class="radio radio-info col-sm-12"
                                                                 style="margin-top: -4px;">
                                                                <input type="radio" ${ent.checked!} ${ent.disabled!} name="updateEnv"
                                                                       id="dockerFileSource${ent.entId!}"
                                                                       value="${ent.entId!}">
                                                                <label for="dockerFileSource${ent.entId!}">
                                                                ${ent.entname!}
                                                                </label>
                                                            </div>
                                                        </th>
                                                        <th>
                                                            <div class="radio  col-sm-12" style="margin-top: -4px;">
                                                            ${(ent.release??)?then( '<span class="text-primary">已发布</span>','<span class="text-warning">未发布</span>')}
                                                            </div>
                                                        </th>
                                                        <th>
                                                            <div class="radio radio-info col-sm-12"
                                                                 style="margin-top: -4px;">
                                                            ${ent.version!}
                                                            </div>
                                                        </th>
                                                        <th>
                                                            <div title="该发布选项支持重新发布该服务应用" class="radio radio-info col-sm-12"
                                                                 style="margin-top: -4px;">
                                                            <i  class="fa fa-send"></i><a onclick="save_update_service('${ent.entId!}')">重新发布</a></font>
                                                            </div>
                                                        </th>
                                                    </tr>
                                                </#if>
                                            </#list>
                                        </#if>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <p>
                                <p>
                            </div>
                    </div>
                    <div class="form-group col-sm-8">
                        <div>
                            <button onclick="save_update_service()" type="button"
                                    class="btn btn-custom waves-effect waves-light radius">
                                更新服务
                            </button>
                        </div>
                    </div>
                    </form>
                </div>
            </div>
            <script>

                function save_update_service(entId) {
                    var url = "/ci/flow/saveUpdateService"
                    var data = get_form_data();
                    data["resume"] = "0";
                    data["envId"] = "${envId}"
                    data["entId"] = $('input[name="updateEnv"]').filter(":checked").val();
                    if(entId){
                        data["entId"] = entId;
                        data["resume"] = "1";
                    }
                    var result = post(data, url);
                    result = JSON.stringify(result);
                    if(result.indexOf("没有权限") != -1 || result.indexOf("该项目已经发布完成") != -1){
                        parent.layer.msg(result, {icon: 2})
                        return;
                    }else{
                        parent.layer.msg(result, {icon: 1})
                    }
                    make_page('/ci/flow/list', '/持续集成/流水线');
                }
            </script>
