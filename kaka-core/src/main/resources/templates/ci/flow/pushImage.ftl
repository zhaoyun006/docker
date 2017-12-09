<style>

    .radius{
        border-radius:5px;
    }

    label {
        font-weight: 100 !important;
    }
    .top{
        margin-top: 13px;
    }
</style>
<div class="modal fade" id="app_add_html" tabindex="-1"
     role="dialog" style="margin-top: 36px;"
     data-backdrop="false">
    <div class="modal-dialog"
         style="margin-top: 0px; width: 40%; ">
        <div class="modal-content"
             style="height: 460px; width: 100%; ">
            <div class="modal-header" style="    border-bottom-width: 0px !important;">
                <button type="button" class="close" style="color: #ccc;  right: 8px;top:8px;background-color:#ffffff;" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
    <div class="col-lg-12" style="margin-top: -23px">
        <div class="panel panel-heading panel-info">
            <h5 class="panel-title"><span style="color: #64c5b1;">推送镜像</span></h5>
        </div>
        <div style="border-top: 2px solid #64c5b1;width: 150px;margin-top: 0px;"></div>
        <div class="card-box col-sm-12" style="margin-top: 0px;margin-left: -3px;">
            <form>
                <code>推送镜像功能可实现镜像先下载到服务器,在启动容器时无需再次下载,加快更新速度</code>
                <div class="col-md-12"><br>
                </div>
                <div class="col-sm-12 top">
                    <label class="col-sm-3 control-label">服务名</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control"  readonly name="serviceName" required value="${serviceName}" style="    background-color: #ffffff;"
                               placeholder="50"/>
                    </div>
                </div>
                <div class="col-sm-12 top">
                    <label class="col-sm-3 control-label">应用域名</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control " readonly name="domain" required value="${domain}" style="    background-color: #ffffff;"
                               placeholder="50"/>
                    </div>
                </div>
                <div class="col-md-12 top">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">选择版本</label>
                        <div class="col-sm-9">
                            <select class="form-control" name="version" onchange="$('#entname').val($(this).val())" >
                                <option value="">--请选择--</option>
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
                <div class="col-md-12">
                    <p>
                    <p>
                </div>
                <div class="col-md-12">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">选择环境</label>
                        <div class="col-sm-9">
                            <select class="form-control" name="entname" onchange="$('#entid').val($(this).val())" >
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
                <input id="entid" type="hidden">
                <div class="col-md-12">
                    <p>
                    <p>
                </div>
        </div>
                <div class="form-group col-sm-8">
                    <div>
                        <button onclick="push_start()" type="button" class="btn btn-custom waves-effect waves-light radius">
                            开始推送
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <script>

        function push_start() {
            var url = "/ci/flow/image/push/${envId!}/"+$("#entid").val();
            var  data = get_form_data();
            if(!data["version"]){
                parent.layer.msg("必须选择版本", {icon: 2})
                return
            }
            if(!data["entname"]){
                parent.layer.msg("必须选择环境", {icon: 2})
                return
            }
            var result = post(data, url);
            parent.layer.msg(JSON.stringify(result), {icon: 1})

        }
    </script>
