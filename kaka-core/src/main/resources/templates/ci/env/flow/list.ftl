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
                        style="margin-top: -3px;padding: 4px;border-radius: 5px;font-size:15px;margin-left: 0px;"><i
                        class="fa fa-plus"></i>&nbsp;&nbsp;添加流程
                </button>
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
                <th>流程顺序</th>
                <th>是否默认</th>
                <th>备注</th>
                <th>创建时间</th>
                <th>修改时间</th>
                <th>最近修改人</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody style="vertical-align: middle !important;">
            </tbody>
        </table>
    </div>
</div>
<script type="text/javascript">
    function loadData() {
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
                "url": "/ci/env/flow/data?t=" + new Date().getTime(),
                "type": 'post'
            },
            "columns": [ // 数据映射
                {"data": "serviceName"},
                {"data": "domain"},
                {"data": "releaseOrder"},
                {
                    "data": "envId", "mRender": function (data) {
                    if (data == "0") {
                        return "<span style='color:red'>是</span>";
                    }
                    return "<span style='color:green'>否</span>";
                }
                },
                {"data": "description"},
                {"data": "createTime"},
                {"data": "lastModifyTime"},
                {"data": "lastModifyUser"},
                {
                    "data": "flowId", "mRender": function (data) {
                    return ' <button onclick="add_env(' + data + ')" class="btn btn-custom waves-effect  waves-light btn-xs"' +
                            'style="padding: 4px;border-radius: 5px;">编辑</button>' +
                            ' <button onclick="delete_flow(' + data + ')" class="btn btn-danger waves-effect  waves-light btn-xs"' +
                            'style="padding: 4px;border-radius: 5px;">删除</button>';
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


    function delete_flow(id) {
        parent.layer.confirm("点击同意后,将删除该流程", {
            btn: ['同意', '不同意'], //按钮
            shade: false//不显示遮罩
        }, function () {
            var url = "/ci/env/flow/delete/" + id;
            var result = post({}, url);
            result = JSON.stringify(result);
            if (result.indexOf("error") != -1) {
                parent.layer.msg(result, {icon: 2})
            } else {
                parent.layer.msg(result, {icon: 1})
            }
        }, function () {
            parent.layer.msg("取消操作", {icon: 2})
        });
    }

    function add_env(id) {
        if (!id) {
            id = 0;
        }
        make_page("/ci/env/flow/add", "/持续集成/流程配置/添加流程", "?flowId=" + id);
    }
</script>
</body>
</html>