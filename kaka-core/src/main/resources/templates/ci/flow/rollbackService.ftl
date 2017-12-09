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
                        <h5 class="panel-title"><span style="color: #64c5b1;">回滚服务</span></h5>
                    </div>
                    <div style="border-top: 2px solid #64c5b1;width: 150px;margin-top: 0px;"></div>
                    <div class="card-box col-sm-12" style="margin-top: 0px;margin-left: -3px;">
                        <form>
                            <code>根据不同的环境回滚服务，只能回滚到上次发布的版本,其他版本走更新发布</code>
                            <div class="col-md-12"><br>
                            </div>
                            <div class="col-sm-12 top">
                                <label class="col-sm-2 control-label">服务名</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" readonly name="serviceName" required
                                           value="${serviceName!}"
                                           style=" border: none; margin-top: -10px;  background-color: #ffffff;"
                                    />
                                </div>
                            </div>
                            <div class="col-sm-12 top">
                                <label class="col-sm-2 control-label">应用域名</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control " readonly name="domain" required
                                           value="${domain!}"
                                           style="border: none;margin-top: -10px;    background-color: #ffffff;"
                                    />
                                </div>
                            </div>

                            <div class="col-md-12" style="margin-top: 25px;">
                            </div>
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">选择环境</label>
                                    <div class="col-sm-10">
                                        <table>
                                            <tr>
                                                <th>&nbsp;&nbsp;环境信息</th>
                                                <th>&nbsp;&nbsp;&nbsp;&nbsp;最近版本</th>
                                                <th>&nbsp;&nbsp;&nbsp;&nbsp;发布时间</th>
                                            </tr>
                                            <tr>
                                                <th><br></th>
                                                <th><br></th>
                                                <th><br></th>
                                            </tr>
                                        <#if entnames ?? >
                                            <#list entnames as ent>
                                                <#if ent ?? >
                                                    <tr>
                                                        <td>
                                                            <div title="${ent.title!}" class="radio radio-info "
                                                                 style="margin-top: -4px;">
                                                                <input type="radio" ${ent.checked!} ${ent.disabled!}
                                                                       name="updateEnv"
                                                                       id="dockerFileSource${ent.entId!}"
                                                                       value="${ent.entId!}">
                                                                <label for="dockerFileSource${ent.entId!}">
                                                                ${ent.entname!}
                                                                </label>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <div class="radio " style="margin-top: -4px;">
                                                            ${(ent.version??)?then( '<span id="version${ent.entId!}" class="text-primary">${ent.version}</span>','<span class="text-warning">未发布</span>')}
                                                            </div>
                                                        </td>
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
                            <button onclick="save_rollback_service()" type="button" id="save_btn_id"
                                    class="btn btn-custom waves-effect waves-light radius">
                                <i class="fa fa-send"></i>&nbsp;回滚服务
                            </button>
                        </div>
                    </div>
                    </form>
                </div>
            </div>
            <script>

                function save_rollback_service() {
                    var url = "/ci/flow/saveRollbackService"
                    var data = get_form_data();
                    data["envId"] = "${envId}"
                    data["entId"] = $('input[name="updateEnv"]').filter(":checked").val();
                    data["version"] = $("#version"+data["entId"]).html();
                    if(data["version"] == "未更新" || !data["version"] || data["version"] == "null"){
                        parent.layer.msg("该服务不可回滚", {icon: 2})
                        return;
                    }
                    var result = post(data, url);
                    result = JSON.stringify(result);
                    if (result.indexOf("没有权限") != -1 || result.indexOf("该项目已经发布完成") != -1) {
                        parent.layer.msg(result, {icon: 2})
                        return;
                    } else {
                        parent.layer.msg(result, {icon: 1})
                    }
                    make_page('/ci/flow/list', '/持续集成/流水线');
                }
            </script>
