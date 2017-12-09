<style>
    .th-center {
        border-bottom: none !important;
        text-align: center !important;
        padding: 5px !important;
        background-color: #eee;
    }
    .label {
        font-weight: 500;
        font-size: 12px;
        color: #000;
    }
</style>
<script src="/static/js/jquery.core.js"></script>
<div class="col-lg-12">
    <div class="card-box " style="  <#if containers?? ><#else >height: 300px;</#if>;min-height: 300px;">
        <ul class="nav nav-tabs tabs-bordered">
        ${(containers??)?then(' <li class="active">
              <a href="#container-b1" onclick="set_containers();" data-toggle="tab" aria-expanded="true">
                    <span class="visible-xs"><i class="fa fa-home"></i></span>
                    <span class="hidden-xs">容器列表</span>
                </a>
            </li>
','         <li class="active">
                <a href="#container-b1" data-toggle="tab" aria-expanded="true">
                    <span class="visible-xs-color"><i class="fa  fa-info-circle"></i></span>
                    <span class="hidden-xs">基础信息</span>
                </a>
            </li>
')}
            <li class="">
                <a href="#env-b1" data-toggle="tab" aria-expanded="false">
                    <span class="visible-xs-color"><i class="fa fa-text-width"></i></span>
                    <span class="hidden-xs">环境变量</span>
                </a>
            </li>
            <li class="">
                <a href="#mount-b1" data-toggle="tab" aria-expanded="false">
                    <span class="visible-xs-color"><i class="fa fa-database"></i></span>
                    <span class="hidden-xs">挂载卷</span>
                </a>
            </li>
            <li class="">
                <a href="#release-b1" data-toggle="tab" aria-expanded="false">
                    <span class="visible-xs-color"><i class="fa fa-cog"></i></span>
                    <span class="hidden-xs">服务发布</span>
                </a>
            </li>
            <li class="">
                <a href="#health-b1" data-toggle="tab" aria-expanded="false">
                    <span class="visible-xs-color"><i class="fa fa-cog"></i></span>
                    <span class="hidden-xs">健康检查</span>
                </a>
            </li>
            <li class="">
                <a href="#expand-b1" data-toggle="tab" aria-expanded="false">
                    <span class="visible-xs-color"><i class="fa fa-sliders"></i></span>
                    <span class="hidden-xs">自动伸缩</span>
                </a>
            </li>
            <li class="">
                <a href="#dispatch-b1" data-toggle="tab" aria-expanded="false">
                    <span class="visible-xs-color"><i class="fa  fa-gears"></i></span>
                    <span class="hidden-xs">调度</span>
                </a>
            </li>
        </ul>
        <div class="tab-content">
        <#if containers?? >
            <div class="tab-pane active" id="container-b1">
                <table class="table table-bordered table-hover" id="docker-container-table"
                       style="font-size: 12px !important;    width: 100% !important;">
                    <thead>
                    <tr>
                        <th class="th-center">容器名</th>
                        <th class="th-center">镜像/宿主机</th>
                        <th class="th-center">创建时间</th>
                        <th class="th-center">状态</th>
                        <th class="th-center">端口映射</th>
                        <th class="th-center">网络</th>
                        <th class="th-center">操作</th>
                    </tr>
                    </thead>
                    <tbody id="containers">
                    </tbody>
                </table>
            </div>
        <#else >
            <div class="tab-pane active" id="container-b1">
                <div class="form-group col-sm-8">
                    <label for="appName" class="col-sm-2 form-control-label label">CPU设置:</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control input-sm "
                               value="${(service??)?then('${service.limitCpu!}','')}" name="limitCpu" required
                               id="limitCpu"
                               placeholder="例如0,2,默认为无限制"/>
                    </div>
                </div>
                <div class="form-group col-sm-8">
                    <label for="appName" class="col-sm-2 form-control-label label">内存限制(MB):</label>
                    <div class="col-sm-10">
                        <input type="number" max="1024000" class="form-control input-sm" name="limitMemory" required
                               value="${(service??)?then('${service.limitMemory!}','')}"
                               id="limitMemory"
                               placeholder="默认为无限制"/>
                    </div>
                </div>
                <div class="form-group col-sm-8">
                    <label for="appName" class="col-sm-2 form-control-label label">运行命令:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control input-sm"
                               value="${(service??)?then('${service.Command!}','')}" name="Command" required
                               id="Command"
                               placeholder="应用名称"/>
                    </div>
                </div>
                <div class="form-group col-sm-8">
                    <label for="appName" class="col-sm-2 form-control-label label">访问入口:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control input-sm " name="appName" required id="appName"
                               placeholder="应用名称"/>
                    </div>
                </div>
                <div class="form-group col-sm-8">
                    <label for="appName" class="col-sm-2 form-control-label label">特权模式:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control input-sm  " name="appName" required id="appName"
                               placeholder="应用名称"/>
                    </div>
                </div>
            </div>
        </#if>


            <div class="tab-pane" id="env-b1">
                <div class="col-sm-12">
                    <div id="var-setting" style="overflow-y: scroll;: scroll;height: 270px;">
                    </div>
                    <div class="btn-group col-sm-12" style="margin-top: -80px;margin-left: -20px;">
                        <button type="button" onclick="add_var()" style="margin-top: -10px;"
                                class="btn btn-custom waves-effect btn-xs"><i class="fa  fa-plus"></i>添加环境变量
                        </button>
                    </div>
                </div>
            </div>
            <div class="tab-pane" id="mount-b1">
                <div id="mount-setting" style="overflow-y: scroll;height: 270px;">
                </div>
                <div class="btn-group col-sm-12" style="margin-top: -90px;margin-left: -10px;">
                    <button type="button" onclick="add_mount()" class="btn btn-custom waves-effect btn-xs"><i
                            class="fa  fa-plus"></i>添加数据卷
                    </button>
                </div>
            </div>
            <div class="tab-pane" id="release-b1">
                <div id="port-setting" style="overflow-y: scroll;height: 270px;">
                </div>
                <div class="btn-group col-sm-12" style="margin-top: -90px;margin-left: -10px;">
                    <button type="button" onclick="add_port()" class="btn btn-custom waves-effect btn-xs"><i
                            class="fa  fa-plus"></i>添加端口
                    </button>
                </div>
            </div>
            <div class="tab-pane" id="health-b1">
                <div class="form-group col-sm-12">
                    <label for="appName" class="col-sm-2 form-control-label label">健康检查:</label>
                    <div class="radio radio-info col-sm-2" style="margin-top: -4px;width: 60px;margin-left: -30px">
                        <input type="radio" name="radio" id="radio10" value="option10">
                        <label for="radio5">
                            无
                        </label>
                    </div>
                    <div class="radio radio-info col-sm-2" style="width: 100px;">
                        <input type="radio" name="radio" id="radio11" value="option6">
                        <label for="radio11">
                            TCP连接
                        </label>
                    </div>
                    <div class="radio radio-info col-sm-2" style="width: 180px;">
                        <input type="radio" name="radio" id="radio12" value="option7">
                        <label for="radio12">
                            HTTP响应 2XX/3XX
                        </label>
                    </div>
                </div>
                <div class="form-group col-sm-6">
                    <label for="appName" class="col-sm-3 form-control-label label">请求路径:</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control input-sm" name="appName" required id="appName"
                               placeholder="50"/>
                    </div>
                    <div class="col-sm-2">
                        <span class="fa">%</span>
                    </div>
                </div>
                <div class="form-group col-sm-6">
                    <label for="appName" class="col-sm-3 form-control-label label">绑定域名:</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control input-sm" name="appName" required id="appName"
                               placeholder="50"/>
                    </div>
                    <div class="col-sm-2">
                        <span class="fa">%</span>
                    </div>
                </div>
                <div class="form-group col-sm-6">
                    <label for="appName" class="col-sm-3 form-control-label label">端口:</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control input-sm" name="appName" required id="appName"
                               placeholder="50"/>
                    </div>
                    <div class="col-sm-2">
                        <span class="fa">%</span>
                    </div>
                </div>
                <div class="form-group col-sm-6">
                    <label for="appName" class="col-sm-3 form-control-label label">初始化超时:</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control input-sm" name="appName" required id="appName"
                               placeholder="50"/>
                    </div>
                    <div class="col-sm-2">
                        <span class="fa">%</span>
                    </div>
                </div>
                <div class="form-group col-sm-6">
                    <label for="appName" class="col-sm-3 form-control-label label">检查间隔:</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control input-sm" name="appName" required id="appName"
                               placeholder="50"/>
                    </div>
                    <div class="col-sm-2">
                        <span class="fa">%</span>
                    </div>
                </div>
                <div class="form-group col-sm-6">
                    <label for="appName" class="col-sm-3 form-control-label label">健康阈值:</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control input-sm" name="appName" required id="appName"
                               placeholder="50"/>
                    </div>
                    <div class="col-sm-2">
                        <span class="fa">%</span>
                    </div>
                </div>
                <div class="form-group col-sm-6">
                    <label for="appName" class="col-sm-3 form-control-label label">不健康阈值:</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control input-sm" name="appName" required id="appName"
                               placeholder="50"/>
                    </div>
                    <div class="col-sm-2">
                        <span class="fa">%</span>
                    </div>
                </div>
            </div>
            <div class="tab-pane " id="expand-b1">
                <div class="form-group col-sm-6" style="margin-left: 20px;">
                    <div class="switchery-demo">
                        <label for="scaleOn" class="col-sm-3 form-control-label label">是否启用:</label>
                        <input onchange="change_scale(this)" type="checkbox" style="width: 45px;!important;"
                               data-size="small" <#if service??>${(service.scaleOn??)?then("checked", "")} </#if>
                               data-plugin="switchery" data-color="#039cfd" id="scaleOn"/>
                    </div>
                </div>
                <br>
                <div id="show_expand_html"
                     style="<#if service??>${(service.scaleOn??)?then("", "display:none")} </#if>">
                    <div class="form-group col-sm-6">
                        <label for="scaleMin" class="col-sm-3 form-control-label label">最小运行容器数:</label>
                        <div class="col-sm-4">
                            <input type="number" class="form-control input-sm"
                                   value="<#if service??>${service.scaleMin!}</#if>" required id="scaleMin"
                                   placeholder="1"/>
                        </div>
                        <div class="col-sm-2">
                            <span class="fa">个</span>
                        </div>
                    </div>
                    <div class="form-group col-sm-6">
                        <label for="scaleMax" class="col-sm-3 form-control-label label">最大运行容器数:</label>
                        <div class="col-sm-4">
                            <input type="number" class="form-control input-sm"
                                   value="<#if service??>${service.scaleMax!}</#if>" name="scaleMax" required
                                   id="scaleMax"
                                   placeholder="1"/>
                        </div>
                        <div class="col-sm-2">
                            <span class="fa">个</span>
                        </div>
                    </div>
                    <div class="form-group col-sm-6" title="最大值100，最小值50">
                        <label for="scaleCpu" class="col-sm-3 form-control-label label">CPU阈值:</label>
                        <div class="col-sm-4">
                            <input type="number" min="50" class="form-control input-sm"
                                   value="<#if service??>${service.scaleCpu!}</#if>" name="scaleCpu" required
                                   id="scaleCpu"
                                   placeholder="50" max="100"/>
                        </div>
                        <div class="col-sm-2">
                            <span class="fa">%</span>
                        </div>
                    </div>
                    <div class="form-group col-sm-6" title="最大值100，最小值50">
                        <label for="scaleMem" class="col-sm-3 form-control-label label">内存阈值:</label>
                        <div class="col-sm-4">
                            <input type="number" min="50" class="form-control input-sm"
                                   value="<#if service??>${service.scaleMem!}</#if>" name="scaleMem" required
                                   id="scaleMem"
                                   placeholder="50" max="100"/>
                        </div>
                        <div class="col-sm-2">
                            <span class="fa">%</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="tab-pane" id="dispatch-b1">
                <div class="form-group col-sm-12">
                    <label for="appName" class="col-sm-2 form-control-label label">调度策略:</label>
                    <div class="radio radio-info col-sm-2" style="margin-top: -3px;">
                        <input type="radio" name="radio" id="radio5" value="option5">
                        <label for="radio5">
                            均衡分散
                        </label>
                    </div>
                    <div class="radio radio-info col-sm-2">
                        <input type="radio" name="radio" id="radi6" value="option6">
                        <label for="radio6">
                            聚焦
                        </label>
                    </div>
                    <div class="radio radio-info col-sm-2">
                        <input type="radio" name="radio" id="radio7" value="option7">
                        <label for="radio7">
                            每个主机运行一个容器
                        </label>
                    </div>
                </div>
                <div class="form-group col-sm-12">
                    <label for="appName" class="col-sm-2 form-control-label label">调度规则:</label>
                    <div class="radio radio-info col-sm-2" style="margin-top: -3px;">
                        <input type="radio" name="radio" id="radio8" value="option5">
                        <label for="radio8">
                            指定服务器
                        </label>
                    </div>
                    <div class="radio radio-info col-sm-2">
                        <input type="radio" name="radio" id="radio9" value="option6">
                        <label for="radio9">
                            筛选服务器
                        </label>
                    </div>

                </div>
                <div class="form-group col-sm-12">
                    <label for="appName" class="col-sm-2 form-control-label label">服务器:</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control input-sm" name="appName" required id="appName"
                               placeholder="1"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function to_container_d(id, host,name) {
        var serviceName = ""
    <#if service?? >
        serviceName = "${service.name!}"
    </#if>

        var url = "/container/detail/"+host+"/"+id
        make_page(url, "/容器管理/"+name+"/详情","?serviceName="+serviceName+"&name="+name);
    }
    function set_containers() {
        var serviceName = "${serviceName!}"
        if(!serviceName){
        <#if service?? >
            serviceName = "${service.name!}"
        </#if>
        }


        function loadData() {
            $("#docker-container-table").dataTable({
                "filter": false,//去掉搜索框
                "ordering": false, // 是否允许排序
                "paginationType": "full_numbers", // 页码类型
                "destroy": true,
                "processing": true,
                "serverSide": true,
                "scrollX": false, // 是否允许左右滑动
                "displayLength": 10, // 默认长度
                "bLengthChange": false, // 下啦选择每页显示
                "ajax": { // 请求地址
                    "url": "/service/containers?t=" + new Date().getTime() + "&serviceName=" + serviceName+"&clusterName=${clusterName!}",
                    "type": 'post'
                },
                "columns": [ // 数据映射
                    {"data": "name", "sWidth": "12%", "mRender": function (data,type,full) {
                        if (data.length > 18) {
                            return "<span onclick='to_container_d(\""+full["id"]+"\",\""+full["host"]+"\",\""+full["name"]+"\")' style='font-weight: 500;' class='text-primary' title='" + data + "'>" + data.substring(0, 18) + "</span>";
                        }else{
                            return "<span onclick='to_container_d(\""+full["id"]+"\",\""+full["host"]+"\",\""+full["name"]+"\")'  style='font-weight: 500;' class='text-primary'>" + data + "</span>";
                        }
                    }},
                    {
                        "data": "image", "sWidth": "20%", "mRender": function (data,type, full) {
                        if (data.length > 30) {
                            d = data.split("/")
                            v = new Array();
                            for (i = 1; i < d.length; i++) {
                                v.push(d[i])
                            }
                            v = v.join("/");
                            return "<span title='" + data + "'>" + v + "</span><br>"+full["host"];
                        } else {
                            return data + "<br>"+full["host"];
                        }
                    }
                    },
                    {"data": "created", "sWidth": "13%"},
                    {
                        "data": "state", "sWidth": "4%", "mRender": function (data, type, full) {
                        if (full["state"] == "running") {
                            return "<span class='text-success'>运行</span>"
                        }
                        if(full["state"] == "created" ){
                            return "<span class='text-warning'>创建</span>"
                        } else {
                            return "<span class='text-danger'>停止</span>"
                        }
                    }
                    },
                    {"data": "port", "sWidth": "10%","mRender":function (data) {
                       if (data){
                           return data.replace(/,/g,"<br>");
                       }
                       return "[]";
                    }},
                    {"data": "network", "sWidth": "10%"},
                    {
                        "data": "serverId", "sWidth": "8%", "mRender": function (data, type, full) {
                        if (full["state"] == "exited") {
                            return '<button onclick="container_oper(\''+full["host"]+'\',\''+full["id"]+'\',\'remove\')"  class="btn btn-danger waves-effect  waves-light btn-xs" style="padding: 4px;border-radius: 5px;">删除</button>'
                        }
                        if (full["state"] == "created") {
                            return '<button onclick="container_oper(\''+full["host"]+'\',\''+full["id"]+'\',\'remove\')"  class="btn btn-danger waves-effect  waves-light btn-xs" style="padding: 4px;border-radius: 5px;">删除</button>'
                        }
                        if (full["state"] == "running") {
                            return '<button onclick="container_oper(\''+full["host"]+'\',\''+full["id"]+'\',\'stop\')" class="btn btn-custom waves-effect  waves-light btn-xs" style="padding: 4px;border-radius: 5px;">停止</button>' +
                                    ' <button onclick="container_oper(\''+full["host"]+'\',\''+full["id"]+'\',\'remove\')" class="btn btn-danger waves-effect  waves-light btn-xs"' +
                                    'style="padding: 4px;border-radius: 5px;">删除</button>'
                        }
                        return "";
                    }
                    },
                ],
                "fnRowCallback": function (row, data) { // 每行创建完毕的回调
                    $(row).data('recordId', data.recordId);
                }
            })
            ;
        }

        loadData();
    }
    <#if service ??>
    set_containers();
    </#if>

    function add_mount(k, v) {
        var old = $("#mount-setting").html()
        var html = '    <div id="mount-html-1">    <div class="form-group col-sm-5">' +
                '                            <label for="mount-1" class="col-sm-2 form-control-label label">物理机路径:</label>' +
                '                            <div class="col-sm-10">' +
                '                                <input value="SRC" type="text" class="form-control input-sm" id="mount-1" required ' +
                '                                       placeholder="物理机提供挂载的路径"/>' +
                '                            </div>' +
                '              </div>' +
                '             <div class="form-group col-sm-1" style="width: 0px;"> =</div>' +
                '                   <div class="form-group col-sm-5">' +
                '                            <label for="mount-value-1" class="col-sm-2 form-control-label label">容器路径:</label>' +
                '                            <div class="col-sm-10" style="margin-left: -10px;">' +
                '                                <input value="TO" type="text" class="form-control input-sm" id="mount-value-1" required ' +
                '                                       placeholder="挂载到容器里的路径"/>' +
                '                            </div>' +
                '                        </div>' +
                '                        <div class="form-group col-sm-1 text-custom" onclick="delete_var(\'mount-html-1\');">删除</div>' +
                '                        <div class="form-group col-sm-12"></div>' +
                '        </div></div>';
        var new_html = html;
        for (i = 1; i < 5; i++) {
            if (old.indexOf("mount-value-" + i) == -1) {
                new_html = html.replace(/mount-value-1/g, "mount-value-" + i)
                new_html = new_html.replace(/mount-1/g, "mount-" + i)
                new_html = new_html.replace(/mount-html-1/g, "mount-html-" + i)

                if (k) {
                    new_html = new_html.replace(/SRC/g, k)
                } else {
                    new_html = new_html.replace(/SRC/g, "")
                }
                if (v) {
                    new_html = new_html.replace(/TO/g, v)
                } else {
                    new_html = new_html.replace(/TO/g, "")
                }
                $("#mount-setting").append(new_html);
                return
            }
        }
    }


    function add_port(k, v) {
        var old = $("#port-setting").html()
        var html = '    <div id="port-html-1">    <div class="form-group col-sm-5">' +
                '                            <label for="port-1" class="col-sm-2 form-control-label label">入口:</label>' +
                '                            <div class="col-sm-10">' +
                '                                <input value="KEY" type="number" class="form-control input-sm" id="port-1" required ' +
                '                                       placeholder="访问入口端口"/>' +
                '                            </div>' +
                '              </div>' +
                '             <div class="form-group col-sm-1" style="width: 0px;"> =</div>' +
                '                   <div class="form-group col-sm-5">' +
                '                            <label for="port-value-1" class="col-sm-2 form-control-label label">容器端口:</label>' +
                '                            <div class="col-sm-10" style="margin-left: -10px;">' +
                '                                <input value="VALUE"  type="number" class="form-control input-sm" id="port-value-1" required ' +
                '                                       placeholder="对应容器的端口"/>' +
                '                            </div>' +
                '                        </div>' +
                '                        <div class="form-group col-sm-1 text-custom" onclick="delete_var(\'port-html-1\');">删除</div>' +
                '                        <div class="form-group col-sm-12"></div>' +
                '        </div></div>';
        var new_html = html;
        for (i = 1; i < 5; i++) {
            if (old.indexOf("port-value-" + i) == -1) {
                new_html = html.replace(/port-value-1/g, "port-value-" + i)
                new_html = new_html.replace(/port-1/g, "port-" + i)
                new_html = new_html.replace(/port-html-1/g, "port-html-" + i)

                if (k) {
                    new_html = new_html.replace(/KEY/g, k)
                } else {
                    new_html = new_html.replace(/KEY/g, "")
                }
                if (v) {
                    new_html = new_html.replace(/VALUE/g, v)
                } else {
                    new_html = new_html.replace(/VALUE/g, "")
                }
                $("#port-setting").append(new_html);
                return
            }
        }
    }

    /**
     *
     * @param host
     * @param id
     * @param action
     */
    function container_oper(host, id, action) {
        if (action == "remove"){
            var url = "/container/"+host+"/"+id+"/remove"
        }
        if (action == "start"){
            var url = "/container/"+host+"/"+id+"/start"
        }
        if (action == "stop"){
            var url = "/container/"+host+"/"+id+"/stop"
        }
        var result = post({}, url);
        success(result,5000)
        set_containers();
    }

    function change_scale(obj) {
        if ($(obj).is(":checked")) {
            $("#show_expand_html").show();
        } else {
            $("#show_expand_html").hide();
        }
    }
    function change_add_color(obj) {
        $(".btn-class").removeClass("btn-asura")
        $(".btn-class").addClass("btn-asura-default")
        $(obj).removeClass("btn-asura-default")
        $(obj).addClass("btn-asura");
    }

    // 删除环境变量
    function delete_var(id) {
        console.log(id)
        $("#" + id).remove();
    }

    function add_var(k, v) {
        var old = $("#var-setting").html()
        var html = '    <div id="env-html-1">    <div class="form-group col-sm-5">' +
                '                            <label for="appName" class="col-sm-2 form-control-label label">变量名:</label>' +
                '                            <div class="col-sm-10">' +
                '                                <input type="text" value="KEY" class="form-control input-sm" id="env-1" required ' +
                '                                       placeholder="变量名"/>' +
                '                            </div>' +
                '              </div>' +
                '             <div class="form-group col-sm-1" style="width: 0px;"> =</div>' +
                '                   <div class="form-group col-sm-5">' +
                '                            <label for="appName" class="col-sm-1 form-control-label label">值:</label>' +
                '                            <div class="col-sm-11">' +
                '                                <input type="text" value="VALUE" class="form-control input-sm" id="env-value-1" required ' +
                '                                       placeholder="变量值"/>' +
                '                            </div>' +
                '                        </div>' +
                '                        <div class="form-group col-sm-1 text-custom" onclick="delete_var(\'env-html-1\');">删除</div>' +
                '                        <div class="form-group col-sm-12"></div>' +
                '        </div></div>';
        var new_html = html;
        for (var i = 1; i < 30; i++) {
            if (old.indexOf("env-value-" + i) == -1) {
                new_html = html.replace(/env-value-1/g, "env-value-" + i)
                new_html = new_html.replace(/env-1/g, "env-" + i)
                new_html = new_html.replace(/env-html-1/g, "env-html-" + i)
                if (k) {
                    new_html = new_html.replace(/KEY/g, k)
                } else {
                    new_html = new_html.replace(/KEY/g, "")
                }
                if (v) {
                    new_html = new_html.replace(/VALUE/g, v)
                } else {
                    new_html = new_html.replace(/VALUE/g, "")
                }
                $("#var-setting").append(new_html);
                return
            }
        }
    }

    function update_port(portdata) {
        if (portdata.indexOf("--publish-add") != -1){
            var portdatas = portdata.split('--publish-add')

        }else{
            var portdatas = portdata.split('--publish')
        }
        for (var i = 0; i < portdatas.length; i++) {
            if (portdatas[i].indexOf(":") != -1 && portdatas[i].length > 0) {
                var ports = portdatas[i].split(":")
                add_port(ports[0].trim(), ports[1].trim());
            }
        }
    }

    function update_mount(mountdata) {
        var mountdatas = mountdata.split('--mount ')
        for (var i = 0; i < mountdatas.length; i++) {
            if (mountdatas[i].indexOf("=") != -1 && mountdatas[i].length > 0) {
                var mounts = mountdatas[i].split("=")
                add_mount(mounts[2].split(",")[0].trim(), mounts[3].trim());
            }
        }
    }

    function update_var(envdata) {
        var envdatas = envdata.split('--env')
        for (var i = 0; i < envdatas.length; i++) {
            if (envdatas[i].indexOf("=") != -1 && envdatas[i].length > 0) {
                envs = envdatas[i].split("=")
                add_var(envs[0].trim(), envs[1].trim());
            }
        }
    }
    <#if service ??>
    ${(service.env??)?then("
      var envdata='${service.env!}';
      update_var(envdata);
   ","")}

    ${(service.port??)?then("
      var portdata='${service.port!}';
      update_port(portdata);
   ","")}

    ${(service.mount??)?then("
      var mountdata='${service.mount!}';
      update_mount(mountdata);
   ","")}
    <#else >
    add_var();
    add_port();
    add_mount();
    </#if>


</script>