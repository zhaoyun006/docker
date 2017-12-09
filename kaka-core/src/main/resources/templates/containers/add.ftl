<style>
    .label {
        font-weight: 100;
        font-size: 14px;
        color: #444;
    }
    .top{
        margin-top: 10px;
    }
</style>
<script src="/static/js/jquery.core.js"></script>

<div class="col-lg-12">
    <div class="card-box" style="border: none !important;">
        <ul class="nav nav-tabs" style="border:0px;">
            <li class="active">
                <a href="#container-b1" data-toggle="tab" aria-expanded="true">
                    <span class="visible-xs-color"><i class="fa  fa-info-circle"></i></span>
                    <span class="hidden-xs">基础信息(必填)</span>
                </a>
            </li>
            <li class="">
                <a href="#network-b1" data-toggle="tab" aria-expanded="false">
                    <span class="visible-xs-color"><i class="fa  fa-sitemap"></i></span>
                    <span class="hidden-xs">网络配置(必填)</span>
                </a>
            </li>
            <li class="">
                <a href="#setting-b1" data-toggle="tab" aria-expanded="false">
                    <span class="visible-xs-color"><i class="fa fa-database"></i></span>
                    <span class="hidden-xs">运行选项(可选)</span>
                </a>
            </li>
            <li class="">
                <a href="#mount-b1" data-toggle="tab" aria-expanded="false">
                    <span class="visible-xs-color"><i class="fa fa-cog"></i></span>
                    <span class="hidden-xs">存储配置(可选)</span>
                </a>
            </li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane active" id="container-b1" >
                <div class="col-sm-12 top">
                    <label for="appName" class="col-sm-3 form-control-label label"><span class="text-danger">*</span>容器名:</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control input-sm" name="appName" required id="appName"
                               placeholder="50"/>
                    </div>
                </div>
                <div class="col-sm-12 top">
                    <label for="appName" class="col-sm-3 form-control-label label"><span class="text-danger">*</span>宿主机:</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control input-sm" name="appName" required id="appName"
                               placeholder="50"/>
                    </div>
                </div>
                <div class="col-sm-12 top">
                    <label for="appName" class="col-sm-3 form-control-label label">CPU核数(核数):</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control input-sm" name="appName" required id="appName"
                               placeholder="50"/>
                    </div>
                </div>
                <div class="col-sm-12 top">
                    <label for="appName" class="col-sm-3 form-control-label label">内存容量(MB):</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control input-sm" name="appName" required id="appName"
                               placeholder="50"/>
                    </div>
                </div>
                <div class="col-sm-12 top">
                    <label for="appName" class="col-sm-3 form-control-label label"><span class="text-danger">*</span>镜像:</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control input-sm" name="appName" required id="appName"
                               placeholder="50"/>
                    </div>
                </div>
            </div>
            <div class="tab-pane" id="network-b1">
                <div class="col-sm-12 top">
                    <label for="appName" class="col-sm-3 form-control-label label">网络类型:</label>
                    <div class="col-sm-9">
                     <select class="form-control">
                         <option value="bridge">Bridge</option>
                         <option value="overlay">Overlay</option>
                     </select>
                    </div>
                </div>
                <div class="col-sm-12 top">
                    <label for="appName" class="col-sm-3 form-control-label label">私有网络:</label>
                    <div class="col-sm-9">
                        <select class="form-control">
                            <option value="bridge">--请选择私有网络--</option>
                            <option value="bridge">Bridge</option>
                            <option value="overlay">Overlay</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="tab-pane" id="setting-b1">
                <div class="btn-group col-sm-12" style="margin-top: -90px;margin-left: -10px;">
                    <button type="button" onclick="add_mount()" class="btn btn-custom waves-effect btn-xs"><i
                            class="fa  fa-plus"></i>运行配置
                    </button>
                </div>
            </div>
            <div class="tab-pane" id="mount-b1">
                <div class="btn-group col-sm-12" style="margin-top: -90px;margin-left: -10px;">
                    <button type="button" onclick="add_mount()" class="btn btn-custom waves-effect btn-xs"><i
                            class="fa  fa-plus"></i>添加数据卷
                    </button>
                </div>
            </div>
            </div>
            <div class="form-group col-sm-8" style="margin-left: 75%;margin-top: 25px;">
                <div>
                    <button onclick="service_save()" type="button"
                            class="btn btn-custom waves-effect waves-light radius">
                        提交
                    </button>
                    <button type="reset" class="btn btn-default waves-effect m-l-5 radius ">
                        重置
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<script>

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
</script>