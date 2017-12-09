<style>

    .radius {
        border-radius: 5px;
    }

    label {
        font-weight: 100 !important;
    }

</style>
<div class="row" xmlns="http://www.w3.org/1999/html" style="margin-top: 80px;margin-left: 8px;">
    <div class="col-lg-12" style="margin-top: 3px">
        <div class="panel panel-heading panel-info">
            <h5 class="panel-title"><span style="color: #64c5b1;">添加环境</span></h5>
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
                        <label class="col-sm-4 control-label"><span style="color: red;margin-right: 2px;">*</span>环境名称:</label>
                        <div class="col-sm-8">
                            <input type="text" placeholder="环境信息" name="entname" class="form-control" id="entname"
                                   value="${configs.entname!}">
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <p>
                    <p>
                </div>
                <div class="col-md-6">
                    <div class="form-group" id="">
                        <label class="col-sm-4 control-label" style="margin-top: 3px;">&nbsp;最小容器数:</label>
                        <div class="col-sm-8">
                            <input type="number" name="containerMin" maxlength="30" class="form-control"
                                   id="onlineNumber"
                                   value="<#if configs.containerMin?? >${configs.containerMin?c}<#else >1</#if>">
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <p>
                    <p>
                </div>
                <div class="col-md-6" style="margin-left:0px;" title="配置应用时，默认最大扩容数量">
                    <div class="form-group" id="">
                        <label class="col-sm-4 control-label" style="margin-top: 3px;">最大扩容数:</label>
                        <div class="col-sm-8">
                            <input type="number" name="containerMax" maxlength="30" class="form-control"

                            value="<#if configs.containerMax?? >${configs.containerMax?c}<#else >1</#if>">

                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <p>
                    <p>
                </div>
                <div class="col-md-6">
                    <label class="col-sm-4 control-label" style="margin-top: 3px;">cpu(核):</label>
                    <div class="col-sm-8">
                        <input type="number" min="1" max="32" name="cpu" maxlength="30" class="form-control"
                               value="<#if configs.cpu?? >${configs.cpu?c}<#else >1</#if>">

                    </div>
                </div>
                <div class="col-md-12">
                    <p>
                    <p>
                </div>
                <div class="col-md-6" title="配置应用时,默认内存">
                    <label class="col-sm-4 control-label" style="margin-top: 3px;">memory(MB):</label>
                    <div class="col-sm-8">
                        <input type="number" min="2048" max="32768" name="memory" maxlength="30" class="form-control"
                               value="<#if configs.memory?? >${configs.memory?c}<#else >2048</#if>">
                    </div>
                </div>
                <div class="col-md-12">
                    <p>
                    <p>
                </div>
                <div class="col-md-6" title="配置应用时,默认网络">
                    <label class="col-sm-4 control-label" style="margin-top: 3px;">网络选择:</label>
                    <div class="col-sm-8">
                       <select class="form-control" name="defaultNetwork">
                           <#if configs.defaultNetwork?? >
                               <option value="${configs.defaultNetwork}">${configs.defaultNetwork}</option>
                           <#else >
                               <option title="默认网络,建议修改成自己的" value="default_0">default_0</option>
                           </#if>
                           <#list networks! as network >
                               <option value="${network.name}">${network.name}</option>
                           </#list>
                       </select>
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
                            <input type="text" name="description" class="form-control" value="${configs.description!}">
                        </div>
                    </div>
                </div>
        </div>
        <div class="form-group col-sm-8">
            <div>
                <button onclick="save_ent(${configs.entId!})" type="button"
                        class="btn btn-custom waves-effect waves-light radius">
                    提交
                </button>
                <button onclick="to_ent()" type="button" class="btn btn-default waves-effect waves-light radius">
                    取消
                </button>
            </div>
        </div>
        </form>
    </div>
</div>
<script>

    function save_ent(id) {
        var url = "/ent/save"
        data = get_form_data();
        if (id) {
            data["entId"] = id
        }
        if (!data["entname"]) {
            parent.layer.msg("环境名称必须填写", {icon: 2})
            return
        }
        var result = post(data, url);
        result = JSON.stringify(result);
        if (result.indexOf("添加环境成功") != -1) {
            parent.layer.msg(result, {icon: 1})
            to_ent();
        } else {
            parent.layer.msg(result, {icon: 2})
        }
    }

    function to_ent() {
        make_page('/ent/list', '/环境管理/环境配置管理');
    }
</script>
