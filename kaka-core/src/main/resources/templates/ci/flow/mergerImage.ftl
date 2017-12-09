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
<script>

</script>
<div class="modal fade" id="app_add_html" tabindex="-1"
     role="dialog" style="margin-top: 36px;"
     data-backdrop="false">
    <div class="modal-dialog"
         style="margin-top: 0px; width: 45%; ">
        <div class="modal-content"
             style="height: 520px; width: 100%; ">
            <div class="modal-header" style="    border-bottom-width: 0px !important;">
                <button type="button" class="close" style="color: #ccc;  right: 8px;top:8px;background-color:#ffffff;" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
    <div class="col-lg-12" style="margin-top: -23px">
        <div class="panel panel-heading panel-info">
            <h5 class="panel-title"><span style="color: #64c5b1;">合并镜像</span></h5>
        </div>
        <div style="border-top: 2px solid #64c5b1;width: 150px;margin-top: 0px;"></div>
        <div class="card-box col-sm-12" style="margin-top: 0px;margin-left: -3px;">
            <form>
                <code>合并镜像按照不同的镜像仓库存储不同的镜像,按照发布流程配置顺序依次合并<br>可以在更上游的服务器拥有更稳定的镜像版本</code>
                <div class="col-md-12"><br>
                </div>
                <div class="col-sm-12 top">
                    <label class="col-sm-3 control-label">服务名</label>
                    <div class="col-sm-9">
                        <input name="envId" type="hidden" value="${envId!}" />
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
                            <select class="form-control" name="entname" onchange="load_groups($(this).val());" >
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
                <div class="col-md-12">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">选择组</label>
                        <div class="col-sm-9">
                            <select class="form-control" id="load_groups_id" name="groupsId">
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
                <div class="col-md-12" style="margin-top: 10px;">
                    <div class="form-group">
                    <label class="col-sm-3 control-label">&nbsp;&nbsp;</label>
                        <div class="col-sm-9">
                    <code>如果不选择组的话,那么选择的环境所有组都会被合并,选择组后只合并该环境的已选择的组</code>
                        </div>
                    </div>
                </div>
        </div>
                <div class="form-group col-sm-8">
                    <div>
                        <button onclick="merger_start()" type="button" class="btn btn-custom waves-effect waves-light radius">
                            开始合并
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <script>

        function merger_start() {
            var url = "/ci/flow/image/merger/"+$("#entid").val();
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

        function load_groups(entId) {
            $('#entid').val(entId);
            var url = "/groups/data?start=1&length=1000&draw=1";
            var data = post({entId:entId}, url);
            data = data["data"];
            var html = "<option value=''>--请选择--</option>";
            for (i=0;i<data.length;i++){
                html += "<option value='"+data[i]["groupsId"]+"'>"+data[i]["groupsName"]+"</option>";
            }
            $("#load_groups_id").html(html);
        }
    </script>
