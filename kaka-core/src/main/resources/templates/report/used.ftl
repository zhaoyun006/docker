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
            <div class="col-sm-2" style="margin-left:10px;">
                <select class="btn-xs w-md waves-effect" style="border: 1px solid #ccc;height: 29px;top: -3px;border-radius: 1px;" onchange="loadData($(this).val())" id="docker_server_groups">
                    <option value="">--部门请选择--</option>
                </select>
            </div>
            <div class="col-sm-2" style="margin-left:-50px;">
                <select class="btn-xs w-md waves-effect" style="border: 1px solid #ccc;height: 29px;top: -3px;border-radius: 1px;" onchange="loadData($(this).val())" id="docker_server_groups">
                    <option value="">--用户请选择--</option>
                </select>
            </div>
        </div>
    </div>
    <div class="row card-box col-lg-12" style="margin-left:11px; margin-top: -11px;">
        <table id="datatable" class=" table table-bordered table-hover" style="margin-right: 10px;">
            <thead>
            <tr>
                <th>部门</th>
                <th>容器数量</th>
                <th>资源使用率</th>
                <th>CPU</th>
                <th>内存</th>
                <th>磁盘</th>
                <th>入网</th>
                <th>出网</th>

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
                    "url": "/report/data?t=" + new Date().getTime() + "&groups=" + groups,
                    "type": 'post'
                },
                "columns": [ // 数据映射
                    {"data": "groups"},
                    {"data": "containerNumber"},
                    {"data": "memory"},
                    {"data": "cpu"},
                    {"data": "memory"},
                    {"data": "disk"},
                    {"data": "trafficInput"},
                    {"data": "trafficOutput"},
                ],
                "fnRowCallback": function (row, data) { // 每行创建完毕的回调
                    $(row).data('recordId', data.recordId);
                }
            })
            ;
        }
        loadData();
    </script>


    </body>
</html>