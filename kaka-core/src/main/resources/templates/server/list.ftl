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
                <button onclick="add_app()" class="btn btn-custom waves-effect w-md waves-light btn-xs"
                        style="margin-top: -3px;padding: 4px;border-radius: 5px;font-size:15px;margin-left: 0px;"><i class="fa fa-plus"></i>&nbsp;&nbsp;添加服务器
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
                <th>集群名称</th>
                <th>服务器地址</th>
                <th>集群类型</th>
                <th>运行中</th>
                <th>停止中</th>
                <th>暂停</th>
                <th>镜像</th>
                <th>物理CPU</th>
                <th>内存容量</th>
                <th>状态</th>
                <th>Master</th>
                <th>创建时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody style="vertical-align: middle !important;">
            </tbody>
        </table>
    </div>
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
                    "url": "/server/data?t=" + new Date().getTime() + "&groups=" + groups,
                    "type": 'post'
                },
                "columns": [ // 数据映射
                    {"data": "groups"},
                    {
                        "data": "serverAddress", "mRender": function (data, type, full) {
                        return data + "<br>(" + full["hostname"] + ")";
                    }
                    },
                    {"data": "cluterTp"},
                    {"data": "instance"},
                    {"data": "noRunning"},
                    {"data": "paused"},
                    {"data": "images"},
                    {"data": "cpuNumber"},
                    {
                        "data": "memSize", "mRender": function (data) {
                        if (data.length > 30) {
                            return "<span title='" + data + "'>" + data.substring(0, 30) + "</span>";
                        }
                        return data;
                    }
                    },
                    {"data": "status"},
                    {"data": "isMaster"},
                    {"data": "lastModifyTime"},
                    {
                        "data": "serverId", "mRender": function (data, type, full) {
                        return ' <button onclick="add_app('+full["serverId"]+')" class="btn btn-primary waves-effect  waves-light btn-xs"' +
                                'style="padding: 4px;border-radius: 5px;">编辑</button>' +
//                                ' <button onclick="add_app()" class="btn btn-custom waves-effect  waves-light btn-xs"' +
//                                'style="padding: 4px;border-radius: 5px;">提升为主</button>' +
//                                ' <button onclick="add_app()" class="btn btn-warning waves-effect  waves-light btn-xs"' +
//                                'style="padding: 4px;border-radius: 5px;">降级</button>' +
                                ' <button onclick="add_app()" class="btn btn-danger waves-effect  waves-light btn-xs"' +
                                'style="padding: 4px;border-radius: 5px;">删除</button>'
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
        function to_detail(appName) {
            make_page("/app/detail", "/主机管理/主机列表/主机详情/" + appName, "?appName=" + appName);
        }
        function add_app(id) {
            if(!id){
                id=0;
            }
            make_page("/server/add", "/主机管理/添加主机", "?serverId="+id);
        }
    </script>


    </body>
</html>