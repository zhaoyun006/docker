<!DOCTYPE html>
<html>
<style>
    .row {
        font-size: 14px;
    }

    .btn-xs {
        padding: 3px !important;
        font-size: 12px;
    }

    button {
        border-radius: 3px !important;
    }

    td {
        padding: 6px !important;
        font-size: 12px;
        vertical-align: baseline !important;
    }

    thead tr th {
        padding: 10px !important;
        background-color: #f5f5f5;
        font-size: 12px;
        border-bottom: none !important;
        vertical-align: baseline !important;
    }
</style>
<link href="/static/css/menu.css" rel="stylesheet" type="text/css"/>
<div class="row" style="margin-top: 78px;margin-left: 1px;margin-right: 1px;">
    <div class="col-sm-12">
        <div class="card-box table-responsive">
            <div class="col-sm-2">
                <button onclick="add_env()" class="btn btn-custom waves-effect w-md waves-light btn-xs"
                        style="margin-top: -3px;padding: 4px;border-radius: 5px;font-size:15px;margin-left: 0px;"><i class="fa fa-plus"></i>&nbsp;&nbsp;添加应用
                </button>
            </div>
            <div class="col-sm-4" style="margin-left: -50px;">
                <select class="btn-xs w-md waves-effect" style="border: 1px solid #ccc;height: 29px;top: -3px;border-radius: 1px;" onchange="loadData($(this).val())" id="docker_server_groups">
                    <option value="">--请选择--</option>
                <#if groups ?? >
                    <#list groups as group>
                        <option value="${group.groupsName!}">${group.groupsName!}</option>
                    </#list>
                </#if>
                </select>
            </div>
        </div>
    </div>

    <div class="row" id="app_add_html" style="display: none">
        <div class="col-sm-12">
            <div class="card-box table-responsive" id="app_add_html_content">
            </div>
        </div>
    </div>


    <div class="row card-box col-lg-12" style="margin-left:11px; margin-top: -11px;">
        <table id="datatable" class=" table table-bordered table-hover" style="margin-right: 10px;">
            <thead>
            <tr>
                <th>应用名称</th>
                <th>应用域名</th>
                <th>节点数</th>
                <th>应用类型</th>
                <th>主机类型</th>
                <th>修改时间</th>
                <th>最近修改人</th>
                <th>审核状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody style="vertical-align: middle !important;">
            </tbody>
        </table>
    </div>

    <script type="text/javascript">
        function loadData(groups) {
            if (!groups) {
                groups = ""
            }
            $("#app_list").show()
            $("#app_add_html").hide()
            $("#datatable").dataTable({
                "filter": true,//去掉搜索框
                "ordering": false, // 是否允许排序
                "paginationType": "full_numbers", // 页码类型
                "destroy": true,
                "processing": true,
                "serverSide": true,
                "scrollX": false, // 是否允许左右滑动
                "displayLength": 10, // 默认长度
                "bLengthChange": true, // 下啦选择每页显示
                "ajax": { // 请求地址
                    "url": "/ci/env/data?t=" + new Date().getTime() + "&groups=" + groups,
                    "type": 'post'
                },
                "columns": [ // 数据映射
                    {"data": "serviceName"},
                    {"data": "domain"},
                    {"data": "containerInfo","mRender":function (data,type,full) {
                    if(data){
                        var html = "";
                        var datas = data.split("<br>");
                        for (i=0;i<datas.length;i++){
                            html += "<span onclick='to_service("+full["envId"]+",\""+datas[i].split(":")[0]+"\")'>"+datas[i]+"</span><br>"
                        }
                        return html;
                    }
                    return "未知";
                    }},
                    {"data": "itemTp"},
                    {"data": "clusterTp"},
                    {"data": "lastModifyTime"},
                    {"data": "lastModifyUser"},
                    {"data": "approve","mRender":function (data) {
                        if(!data){
                            return "<span class='text-danger'>未审核</span>";
                        }else{
                            return "<span class='text-success'>已审核</span>";
                        }
                    }},
                    {
                        "data": "envId", "mRender": function (data, type, full) {
                        return ' <button onclick="add_env('+full["envId"]+')" class="btn btn-custom waves-effect  waves-light btn-xs"' +
                                'style="padding: 4px;border-radius: 5px;">编辑</button>' +
                                ' <button onclick="delete_env(\''+full["serviceName"]+'\')" class="btn btn-danger waves-effect  waves-light btn-xs"' +
                                'style="padding: 4px;border-radius: 5px;">删除</button>'+
                                ' <button onclick="to_perm(\''+data+'\',\''+full["serviceName"]+'\')" class="btn btn-primary waves-effect  waves-light btn-xs"' +
                                'style="padding: 4px;border-radius: 5px;">权限</button>'+
                                ' <button onclick="verify(\''+data+'\',\''+full["serviceName"]+'\')" class="btn btn-warning waves-effect  waves-light btn-xs"' +
                                'style="padding: 4px;border-radius: 5px;">审核</button>'
                    }
                    },
                ],
                "fnRowCallback": function (row, data) { // 每行创建完毕的回调
                    $(row).data('recordId', data.recordId);
                }
            })
            ;
        }
        loadData("${groupsName!}");

        /**
         * 跳转到应用列表页面
         * @param envId
         */
        function to_service(envId,entname) {
            make_page('/app/list', '/应用管理/应用栈列表',"?envId="+envId+"&entname="+entname);
        }

        function to_perm(envId,name) {
            var url = "/ci/env/permissions/list";
            make_page(url, "/应用管理/我的应用/"+name+"/权限管理", "?envId="+envId+"&serviceName="+name);
        }

        function verify(envId) {
            var url = "/ci/env/verify/"+envId;
            var result = post({}, url);
            result = JSON.stringify(result);
            if(result.indexOf("error") != -1){
                parent.layer.msg(result, {icon: 2})
            }else{
                parent.layer.msg(result, {icon: 1})
            }
            loadData();
        }

        function delete_env(id) {
            parent.layer.confirm("点击同意后,将删除服务"+id, {
                btn: ['同意', '不同意'], //按钮
                shade: false//不显示遮罩
            }, function () {
                var url = "/ci/env/delete/"+id;
                var result = post({}, url);
                result = JSON.stringify(result);
                if(result.indexOf("error") != -1){
                    parent.layer.msg(result, {icon: 2})
                }else{
                    parent.layer.msg(result, {icon: 1})
                }
                loadData()
            }, function () {
                parent.layer.msg("取消操作", {icon: 2})
            });
        }

        function add_env(id) {
            if(!id){
                id=0;
            }
            make_page("/ci/env/add", "/应用管理/我的应用/添加应用", "?envId="+id);
        }
    </script>


    </body>
</html>