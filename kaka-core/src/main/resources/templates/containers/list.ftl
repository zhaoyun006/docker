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


<div class="modal fade" id="app_add_html" tabindex="-1"
     role="dialog" style="margin-top: 16px;"
     data-backdrop="false">
    <div class="modal-dialog"
         style="margin-top: 0px; width: 55%; ">
        <div class="modal-content"
             style="height: 420px; width: 100%; ">
            <div class="modal-header" style="    border-bottom-width: 0px !important;">
                <button type="button" class="close" style="color: #ccc;  right: 8px;top:8px;background-color:#ffffff;" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h5 class="modal-title">&nbsp;&nbsp;创建容器</h5>
                <div id="app_add_html_content" style="border-top: 3px solid #ddd">
                </div>
            </div>
        </div>
    </div>
</div>

    <div class="row card-box col-lg-12" style="margin-left:11px; margin-top: 80px;">
                    <button onclick="add_containers()" class="btn btn-custom waves-effect w-md waves-light btn-xs"
                            style="margin-top: -3px;padding: 4px;border-radius: 5px;font-size:15px;margin-left: 0px;"><i class="fa fa-plus"></i>&nbsp;&nbsp;添加容器
                    </button>
        <table class="table table-bordered table-hover" id="docker-container-table"
               style="font-size: 12px !important;    width: 100% !important;">
            <thead>
            <tr>
                <th class="th-center">选择</th>
                <th class="th-center">容器名</th>
                <th class="th-center">镜像</th>
                <th class="th-center">宿主机</th>
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

    <script type="text/javascript">
        function loadData() {
            $("#docker-container-table").dataTable({
                "filter": true,//去掉搜索框
                "ordering": false, // 是否允许排序
                "paginationType": "full_numbers", // 页码类型
                "destroy": true,
                "processing": true,
                "serverSide": true,
                "scrollX": false, // 是否允许左右滑动
                "displayLength": 10, // 默认长度
                "bLengthChange": false, // 下啦选择每页显示
                "ajax": { // 请求地址
                    "url": "/container/data?t=" + new Date().getTime(),
                    "type": 'post'
                },
                "columns": [ // 数据映射
                    {
                        "data": "containerId", "sWidth": "1%", "mRender": function (data) {
                            return "<input type='checkbox'>";
                    }
                    },
                    {"data": "name", "sWidth": "12%", "mRender": function (data,type,full) {
                        if (data.length > 18) {
                            return "<span onclick='to_container_d(\""+full["id"]+"\",\""+full["host"]+"\",\""+full["name"]+"\")' style='font-weight: 500;' class='text-primary' title='" + data + "'>" + data.substring(0, 18) + "</span>";
                        }else{
                            return "<span onclick='to_container_d(\""+full["id"]+"\",\""+full["host"]+"\",\""+full["name"]+"\")'  style='font-weight: 500;' class='text-primary'>" + data + "</span>";
                        }
                    }},
                    {
                        "data": "image", "sWidth": "20%", "mRender": function (data) {
                        if (data.length > 30) {
                            d = data.split("/")
                            v = new Array();
                            for (i = 1; i < d.length; i++) {
                                v.push(d[i])
                            }
                            v = v.join("/");
                            return "<span title='" + data + "'>" + v + "</span>";
                        } else {
                            return data;
                        }
                    }
                    },
                    {"data": "host", "sWidth": "22%"},
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
                    {"data": "port", "sWidth": "10%"},
                    {"data": "network", "sWidth": "10%"},
                    {
                        "data": "serverId", "sWidth": "8%", "mRender": function (data, type, full) {
                        if (full["state"] == "exited") {
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

        function add_containers(id) {
            if(!id){
                id=0;
            }
            var result = post({id:id},"/container/add");
            $("#app_add_html_content").html(result)
            $("#app_add_html").modal("toggle")
        }
    </script>
    </body>
</html>