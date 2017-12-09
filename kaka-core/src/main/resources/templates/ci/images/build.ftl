<style>

    .radius {
        border-radius: 5px;
    }

    label {
        font-size: 14px;
        color: #797979 !important;
        letter-spacing: 0.01em;
        font-family: 'Nunito Sans', sans-serif;
    }

    .top {
        margin-top: 10px;
    }
</style>
<script src="/static/js/jquery.core.js"></script>
<link rel="stylesheet" href="/static/plugins/tooltipster/tooltipster.bundle.min.css">
<div class="row" xmlns="http://www.w3.org/1999/html" style="margin-top: 80px;margin-left: 8px;">
    <div class="col-lg-12" style="margin-top: 3px">
        <div class="panel panel-heading panel-info">
            <h5 class="panel-title"><span style="color: #64c5b1;">构建镜像</span></h5>
        </div>
        <div style="border-top: 2px solid #64c5b1;width: 150px;margin-top: 0px;"></div>
        <div class="card-box col-sm-12" style="margin-top: 0px;margin-left: -3px;">
            <form>
                <div class="col-md-12 top">
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><span style="color: red;margin-right: 2px;">*</span>构建方式:</label>
                        <div class="radio radio-info col-sm-2" style="margin-top: -4px;width: 100px;">
                            <input type="radio" onchange="set_build_tp()" checked name="buildTp" id="buildTp1" value="1">
                            <label for="buildTp1">
                                代码构建
                            </label>
                        </div>
                        <div class="radio radio-info col-sm-2" style="width: 100px;">
                            <input type="radio" onchange="set_build_tp()" <#if configs.buildTp! == "2">checked</#if> name="buildTp" id="buildTp2" value="2">
                            <label for="buildTp2">
                                Dockerfile
                            </label>
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <p>
                    <p>
                </div>
                <div class="col-md-12">
                    <div class="form-group">
                        <label for="registry" class="col-sm-2 control-label"><span style="color: red;margin-right: 2px;">*</span>仓库名称:</label>
                        <div class="col-sm-6">
                            <input type="text" readonly placeholder="仓库名称" name="registry" class="form-control" id="registry"
                                   value="${registry!}">
                        </div>
                    </div>
                </div>
                <div class="col-md-12 top">
                    <div class="form-group">
                        <label for="version" class="col-sm-2 control-label"><span style="color: red;margin-right: 2px;">*</span>版本名称:</label>
                        <div class="col-sm-6">
                            <input type="text" placeholder="版本名称" name="version" class="form-control" id="version"   value="${tag!}"
                                   value="latest">
                        </div>
                    </div>
                </div>
                <div id="build_of_docker_file" style="<#if configs.buildTp! == "1">display: none</#if>">
                    <div class="col-md-12 top">
                        <div class="form-group">
                            <label for="dockerFile" class="col-sm-2 control-label"><span style="color: red;margin-right: 2px;">*</span>Dockerfile内容:</label>
                            <div class="col-sm-6">
                                <textarea type="text" rows="14" placeholder="Dockerfile内容,Dockerfile不支持ADD命令,
请使用网络模式自行下载,模板容器提供下载工具,
如果没有请自行安装
应用编译脚本请写在代码中
模板示例
FROM tomcatd
MAINTAINER ${user!}
ENV GIT_SSH=/root/.ssh/ssh-git.sh
ENV MAVEN_HOME=/home/runtime/maven
RUN wget http://xxx.com/package.war -O /home/runtime/tomcat_8081/webapps/ROOT.war
ENTRYPOINT cd /home/runtime/tomcat_8081/bin ; sh restart.sh ; /usr/sbin/sshd -D

FROM php5.6.7
MAINTAINER ${user!}
RUN wget http://xxx.com/package.war -O /home/runtime/tomcat_8081/webapps/ROOT.war
ENTRYPOINT sh /etc/init.d/php-fpmd start ; /etc/init.d/nginx start ; /usr/sbin/sshd -D
" name="dockerFile" class="form-control" id="dockerFile"
                                          value="latest">${configs.dockerFile!}</textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="build_of_code" style="<#if configs.buildTp! == "2">display:none;</#if>">
                <div class="col-md-12 top">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">&nbsp;&nbsp;源码配置</label>
                        <div class="col-sm-6">
                            版本需要填写源代码管理库对应的版本号，默认为最新版本，子目录为应用运行所在目录，默认为当前目录
                        </div>
                    </div>
                </div>
                    <input type="hidden" name="codeTp" id="codeTp" value="<#if configs.codeTp! != "svn" >git<#else >svn</#if>">
                <div class="col-md-12 top">
                    <div class="card-box" style="border: none !important;">
                        <ul class="nav nav-tabs" style="border:0px;">
                            <li class="<#if configs.codeTp! != "svn" >active</#if>">
                                <a href="#git-b1" onclick="$('#codeTp').val('git')" data-toggle="tab" aria-expanded="true">
                                    <span class="visible-xs-color"><i class="fa   fa-github"></i></span>
                                    <span class="hidden-xs">Git</span>
                                </a>
                            </li>
                            <li class="<#if configs.codeTp! == "svn" >active</#if>">
                                <a href="#svn-b1" onclick="$('#codeTp').val('svn')" data-toggle="tab" aria-expanded="false">
                                    <span class="visible-xs-color"><i class="fa   fa-leaf"></i></span>
                                    <span class="hidden-xs">SVN</span>
                                </a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane <#if configs.codeTp! != "svn" >active</#if>" id="git-b1">
                                <div class="col-sm-12 top">
                                    <label for="gitAddress" class="col-sm-2 control-label"><span
                                            class="text-danger">*</span>git地址:</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control " name="gitAddress" 
                                               id="gitAddress" value="${configs.gitAddress!}"
                                               placeholder="http或https开头的完整地址，不超过256个字符"/>
                                    </div>
                                </div>
                                <div class="col-sm-12 top">
                                    <label for="gitBranch" class="col-sm-2 control-label">分支:</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control " name="gitBranch" 
                                               id="gitBranch" value="${configs.gitBranch!}"
                                               placeholder="默认为空或master分支"/>
                                    </div>
                                </div>
                                <div class="col-sm-12 top">
                                    <label for="gitSubDir" class="col-sm-2 control-label">子目录:</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control " name="gitSubDir" value="${configs.gitSubDir!}"
                                               id="gitSubDir"
                                               placeholder="不超过100个字符"/>
                                    </div>
                                </div>
                                <div class="col-sm-12 top">
                                    <label for="gitUser" class="col-sm-2 control-label">Ci-TOKEN:</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control " name="gitCiToken" value="${configs.gitCiToken!}"  autocomplete="new-password"
                                               placeholder="gitlab Runners token,使用这个token可以获取到代码"/>
                                    </div>
                                </div>
                                <div class="col-sm-12 top">
                                    <label for="gitUser" class="col-sm-2 control-label">用户名:</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control " name="gitUser"  autocomplete="new-password"
                                               id="gitUser" value="${configs.gitUser!}"
                                               placeholder="默认为空"/>
                                    </div>
                                </div>
                                <div class="col-sm-12 top">
                                    <label for="gitPassword" class="col-sm-2 control-label">密码:</label>
                                    <div class="col-sm-6">
                                        <input type="password" class="form-control " name="gitPassword"  autocomplete="new-password"
                                               id="gitPassword" value="${configs.gitPassword!}"
                                               placeholder=" 默认为空"/>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane <#if configs.codeTp! == "svn" >active</#if>" id="svn-b1">
                                <div class="col-sm-12 top">
                                    <label for="svnAddress" class="col-sm-2 control-label"><span
                                            class="text-danger">*</span>svn地址:</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control " name="svnAddress" 
                                               id="svnAddress" value="${configs.svnAddress!}"
                                               placeholder="http或https开头的完整地址，不超过256个字符"/>
                                    </div>
                                </div>
                                <div class="col-sm-12 top">
                                    <label for="svnVersion" class="col-sm-2 control-label">版本:</label>
                                    <div class="col-sm-6">
                                        <input type="number" class="form-control " name="svnVersion"
                                               id="svnVersion"  value="${configs.svnVersion!}"
                                               placeholder="默认为最新版"/>
                                    </div>
                                </div>
                                <div class="col-sm-12 top">
                                    <label for="svnSubDir" class="col-sm-2 control-label">子目录:</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control " name="svnSubDir"  value="${configs.svnSubDir!}"
                                               id="svnSubDir"
                                               placeholder="不超过100个字符"/>
                                    </div>
                                </div>
                                <div class="col-sm-12 top">
                                    <label for="svnUser" class="col-sm-2 control-label">用户名:</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control " name="svnUser"  autocomplete="off"  value="${configs.svnUser!}"
                                               id="svnUser"
                                               placeholder=""/>
                                    </div>
                                </div>
                                <div class="col-sm-12 top">
                                    <label for="svnPassword" class="col-sm-2 control-label">密码:</label>
                                    <div class="col-sm-6">
                                        <input type="password"  autocomplete="off" class="form-control " name="svnPassword"  value="${configs.svnPassword!}"
                                               id="svnPassword"
                                               placeholder=""/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-12 top"></div>
                <div class="col-md-12 top">
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><span style="color: red;margin-right: 2px;">*</span>Dockerfile:</label>
                        <div class="radio radio-info col-sm-2" style="margin-top: -4px;width: 100px;">
                            <input type="radio" checked name="dockerFileSource" onclick="set_build_path()" id="dockerFileSource1" value="custom">
                            <label for="dockerFileSource1">
                                自定义
                            </label>
                        </div>
                        <div class="radio radio-info col-sm-2" style="width: 200px;">
                            <input type="radio" <#if configs.templateId! != "" >checked</#if> name="dockerFileSource" onclick="set_tempalte()" id="dockerFileSource2" value="auto">
                            <label for="dockerFileSource2">
                                使用模板 <span onclick="show_help_info('show_template_help_id','show_template_help_id_text')"><a id="show_template_help_id_text">帮助</a></span>
                            </label>
                        </div>
                    </div>
                </div>
                    <div id="show_template_help_id" style="display: none;    margin-left: 180px;" class="col-sm-12">
                    一般情况下，使用自动生成的默认 Dockerfile 需要按标准规则来产生镜像，模板如下：<br>
                        FROM tomcat<br>
                        add package /home/www/${serviceName!}<br>
                    镜像编译后将编译完成的数据放到 源代码编译后的package目录下面<br>
                    通过默认的 Dockerfile 创建服务约定条件如下：<br>
                    不同的基础镜像, 需要把所有发布的内容放在 package 目录下 <br>
                    </div>
                    <input type="hidden"  id="build_template_id" name="templateId" value="${configs.templateId!}">
                    <div class="col-sm-12 top" style="display: none;" id="template_div">

                    </div>
                <div class="col-sm-12 top" id="docker_file_div">
                    <label for="dockerFilePath" class="col-sm-2 control-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control " name="dockerFilePath" 
                               id="dockerFilePath"
                               placeholder="  Dockerfile 文件在代码源中的路径,默认名称为Dockerfile"/>
                    </div>
                    <div class="col-sm-2"><span onclick="show_help_info('docker_file_path_help','docker_file_path_help_text')"><a id="docker_file_path_help_text">帮助</a></span></div>
                    <div class="col-sm-6" style="display: none" id="docker_file_path_help">Dockerfile 保存在源代码根目录中，文件名为 dockerfile，则填写 dockerfile 即可；
                        Dockerfile 保存在源代码 src/main/resouces/ 文件夹中，文件名为 Dockerfile，则填写 src/main/resouces/Dockerfile
                    </div>
                </div>
                </div>
                <div class="form-group col-sm-6 top" style="margin-left: 20px;">
                    <div class="switchery-demo">
                        <label for="autoBuild" class="col-sm-3 form-control-label label">启用自动编译:</label>
                        <input  type="checkbox" style="width: 65px;!important;"
                               data-size="small" <#if configs??>${(configs.autoBuild??)?then("checked", "")} </#if>
                               data-plugin="switchery" data-color="#039cfd" id="autoBuild"/>
                    </div>
                </div>
                <div class="form-group col-sm-8">
                    <div>
                        <button onclick="build_images(${configs.envId!})" type="button"
                                class="btn btn-custom waves-effect waves-light radius">
                            提交
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <!-- Tooltipster js -->
    <script src="/static/plugins/tooltipster/tooltipster.bundle.min.js"></script>
    <script>
        function set_build_path() {
            $("#template_div").hide();
            $("#docker_file_div").show();
            $("#build_template_id").val("");
        }

        function show_help_info(id,textid) {
           if($("#"+id).is(":hidden")){
               $("#"+id).show();
               $("#"+textid).html("隐藏");
           }else{
               $("#"+id).hide();
               $("#"+textid).html("帮助");
           }
        }

        <#if configs.templateId! != "" >
            set_tempalte();
        </#if>

        function change_color(obj, v) {
            $(".tooltips").removeClass("btn-custom");
            $(".tooltips").addClass("btn-default");
            $(obj).removeClass("btn-default");
            $(obj).addClass("btn-custom");
            $("#build_template_id").val(v);
        }

        function set_tempalte() {
            var url = "/images/templates/data?isTmplate=1&start=1&length=30&draw=1";
            var result = post({},url);
            var data = result["data"]
            var html = "<div class='col-sm-2'></div>"
            var tid = "${configs.templateId!}"
            for(i=0;i<data.length;i++){
                if(data[i]["templateId"] == tid){
                    var css = "btn-custom"
                }else{
                   var css = "btn-default"
                }
                html += '  <div class="col-sm-2 tooltips btn '+css+'" onclick="change_color(this,'+data[i]["templateId"]+')" style="width: 90px;margin: 5px;height: 90px;" data-container="body" title="'+data[i]["description"]+'" data-toggle="popover" '+
                        'data-placement="top"  data-original-title=""><lable class="col-sm-6 control-label" style="margin-left: -15px">'+data[i]["imageName"]+'</lable><br><div class="col-sm-6" style="margin-left: -10px;"><img width="50px" height="50px" src="'+data[i]["imageUrl"]+'"></div>' +
                '<br></div>';
            }
            $("#template_div").show();
            $("#docker_file_div").hide();
            $("#template_div").html(html);
            $('.tooltips').tooltipster();
        }

        function build_images(id) {
            var url = "/ci/images/compile"
            var data = get_form_data();
            if (id) {
                data["envId"] = id
            }
            if(data["dockerFile"].indexOf("ADD ") != -1 || data["dockerFile"].indexOf("add ") != -1){
                parent.layer.msg("docker内容不能包含ADD命令", {icon: 2});
                return;
            }
            if($("#buildTp1").is(":checked")){
                data["buildTp"] = "1"
            }
            if($("#buildTp2").is(":checked")){
                data["buildTp"] = "2"
            }
            if($("#autoBuild").is(":checked")){
                data["autoBuild"] = "1"
            }
            if($("#dockerFileSource1").is(":checked")){
                data["dockerFileSource"] = "custom"
                data["dockerFile"] = ""
            }
            if($("#dockerFileSource2").is(":checked")){
                data["dockerFileSource"] = "auto"
                data["dockerFile"] = ""
            }
            data["codeTp"] = data["codeTp"].trim();
            var result = post(data, url);
            if(result.indexOf("ok") != -1){
                make_page('/ci/images/list', '/镜像管理/镜像列表')
                return
            }
            parent.layer.msg(result, {icon: 2})
        }

        function set_build_tp() {
            if($("#buildTp1").is(":checked")){
                $("#build_of_code").show();
                $("#build_of_docker_file").hide();
            }
            if($("#buildTp2").is(":checked")){
                $("#build_of_docker_file").show();
                $("#build_of_code").hide();
            }
        }
    </script>
