
<div class="modal fade" id="add_getui_config" tabindex="-1"
     role="dialog" style="margin-top: 16px;"
     data-backdrop="false">
    <div class="modal-dialog"
         style="margin-top: 0px; width: 45%; ">
        <div class="modal-content"
             style="height: 340px; width: 100%; ">
            <div class="modal-header">
                <button type="button" class="close" style="color: #ccc;  right: 8px;top:8px;background-color:#ffffff;" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h5 class="modal-title">&nbsp;&nbsp;添加子网</h5>
            </div>
            <div class="animated fadeInRight">
                <form>
                    <div class="col-md-12" style="margin-top: 20px;">
                        <div class="form-group" style="margin-left: -5px;">
                            <label class="col-sm-2 control-label"><span style="color: red;margin-right: 2px;">*</span>网络名称:</label>
                            <div class="col-sm-9">
                                <input type="text" name="name" placeholder="mynetwork,以字母开头" class="form-control"
                                       value="<#if configs?? >${configs.name??}</#if>">
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-12" style="margin-top: 20px;">
                        <label class="col-sm-2 control-label"><span style="color: red;margin-right: 2px;">*</span>所属组:</label>
                        <div class="col-sm-10">
                            <input type="hidden" name="groupsName" value="" id="groupsName">
                        <select  name="groupsId" class="form-control" style="border: 1px solid #ccc;border-radius: 1px;"  id="docker_server_groups">
                            <option value="">--请选择--</option>
                        <#if groups ?? >
                            <#list groups as group>
                                <option value="${group.groupsId!}">${group.groupsName!}</option>
                            </#list>
                        </#if>
                        </select>
                        </div>
                    </div>
                    <div class="col-md-12" style="margin-top: 10px;">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">子网网段:</label>
                            <div class="col-sm-10">
                                <input id="input1" onkeyup="checkValue($(this).val(),'input1')" type="number"
                                       maxlength="1"  min="1" max="254" placeholder="172" size="3" name="networkPrefix" style="width: 16%;float: left;"
                                       class="form-control"
                                       value=""><span style="float: left;margin-top:10px;font-size:20px;">.</span>
                                <input id="input2" onkeyup="checkValue($(this).val(),'input2')" type="number"
                                       maxlength="3"  min="0" max="254" placeholder="16"  size="3" name="networkPrefix" style="width: 16%;float: left;"
                                       class="form-control"
                                       value=""><span style="float: left;margin-top:10px;font-size:20px;">.</span>
                                <input id="input3" onkeyup="checkValue($(this).val(),'input3')" type="number"
                                       maxlength="3"  min="0" max="254" placeholder="0"  size="3" name="networkPrefix" style="width: 16%;float: left;"
                                       class="form-control"
                                       value=""><span style="float: left;margin-top:10px;font-size:20px;">.</span>
                                <input type="number" max="0"  min="0" size="3" placeholder="0" id="input4" style="width: 16%;float: left;"
                                       class="form-control"
                                       value="">
                                <span class="form-control" style="border:none;width: 3%;float: left;">/</span>
                                <input type="number"  min="8"  max="28"  value="" placeholder="16" id="input5" class="form-control" style="width: 16%;float: left;">
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12" style="margin-top: 10px;">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">描述信息:</label>
                            <div class="col-sm-9">
                                <input type="text" name="description" class="form-control"
                                       value="<#if configs?? >${configs.description??}</#if>">
                            </div>
                        </div>
                    </div>
                </form>
                <div class="col-md-12">
                    <p>
                    <p>
                </div>
                <button style="margin-left: 82%;margin-top: 15px;"
                        onclick="save();" class="btn btn-custom btn-sm"
                        type="button">
                    <i class="fa fa-check"></i>&nbsp;保存
                </button>
            </div>
        </div>
    </div>
</div>
<script>
    function checkValue(value,id) {
        if(value.indexOf(".")!=-1){
            $('#'+id).val("");
        }

        if(id=="input1"){
            if(value==0){
                $('#'+id).val("");
            }
        }
        if(parseInt(value)>254){
            $('#'+id).val("");
        }
    }

function save() {
    var data = get_form_data();
    var networkIp =""
    for(i=1;i<6;i++){
        if(parseInt($("#inpput"+i).val()) > 255){
            return;
        }
        if(parseInt($("#inpput5").val()) > 30 || parseInt($("#inpput5").val()) < 8 ){
            return;
        }
        if (i == 4){
            networkIp += $("#input"+i).val() + "/"
        }else{
            networkIp += $("#input"+i).val() + "."
        }

    }
    networkIp = networkIp.substring(0, networkIp.length-1);
    data["networkIp"] = networkIp
    var url = "/network/save"
    var result = post(data, url)
    result = JSON.stringify(result);
    if(result.indexOf("成功") != -1){
        parent.layer.msg(result, {icon: 1})
    }else{
        parent.layer.msg(result, {icon: 2})
    }
    loadData();
}
</script>