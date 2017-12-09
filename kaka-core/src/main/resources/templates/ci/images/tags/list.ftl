<!DOCTYPE html>
<html>
<style>
    .row {
        font-size: 14px;
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
<div class="modal fade" id="show_log_html" tabindex="-1"
     role="dialog" style="margin-top: 16px;"
     data-backdrop="false">
    <div class="modal-dialog pull-right"
         style="margin-top: 0px; width: 95%; ">
        <div class="modal-content"
             style="height: auto; min-height: 400px; width: 100%; ">
            <div class="modal-header" style="    border-bottom-width: 0px !important;">
                <button type="button" class="close" style="color: #ccc;  right: 8px;top:8px;background-color:#ffffff;"
                        data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h5 class="modal-title">&nbsp;&nbsp;日志详情</h5>
                <div id="show_log_html_content" style="border-top: 1px solid #ddd; margin-top: 5px;">
                </div>
            </div>
        </div>
    </div>
</div>


<link href="/static/css/menu.css" rel="stylesheet" type="text/css"/>
<div class="row" style="margin-top: 98px;margin-left: 1px;margin-right: 1px;">
    <div class="row card-box col-lg-12" style="margin-left:11px; margin-top: -11px;">
        <table id="datatabletag" class=" table table-bordered table-hover" style="margin-right: 10px;">
            <thead>
            <tr>
                <th>应用名称</th>
                <th>应用域名</th>
                <th>版本</th>
                <th>编译类型</th>
                <th>描述信息</th>
                <th>状态</th>
                <th>编译时间</th>
                <th>日志</th>
            </tr>
            </thead>
            <tbody style="vertical-align: middle !important;">
            </tbody>
        </table>
    </div>
</div>
<link href="/static/css/components.css" rel="stylesheet" type="text/css"/>
<link href="/static/plugins/spinkit/spinkit.css" rel="stylesheet"/>
<script type="text/javascript">
    function loadTagsData() {
        $("#datatabletag").dataTable({
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
                "url": "/ci/images/tags/data?t=" + new Date().getTime() + "&envId=${envId}",
                "type": 'post'
            },
            "columns": [ // 数据映射
                {"data": "serviceName"},
                {"data": "domain"},
                {"data": "tag"},
                {
                    "data": "buildTp", "mRender": function (data) {
                    if (!data) {
                        return "<span class='text-warning'>手动</span>";
                    }
                    return "<span class='text-success'>"+data+"</span>";
                }
                },
                {"data": "description"},
                {
                    "data": "buildStatus", "mRender": function (data) {
                    if (data == "0") {
                        return "<span class='text-danger font-14'>编译失败</span>";
                    }
                    if (data == "1") {
                        return "<span class='text-success font-14'>编译成功</span>";
                    }
                    if (data == "2") {
                        return "<span class='text-info font-14'>编译中</span>";
                    }
                }
                },
                {"data": "createTime"},
                {
                    "data": "envId", "mRender": function (data) {
                    return "<span title='显示日志' onclick='show_compile_log(" + data + ")'><i class='fa fa-terminal'></i></span>";
                }
                },
            ],
            "fnRowCallback": function (row, data) { // 每行创建完毕的回调
                $(row).data('recordId', data.recordId);
            }
        })
        ;
    }
    loadTagsData("${groupsName!}");

    var logTimer;
    var loading = '<img src="/static/images/loading.gif"/>';
    function get_build_log(id) {
        var url = "/ci/images/log/" + id;
        var result = post({}, url);

        if (result.indexOf("结束本次构建") != -1) {
            $("#show_log_html_content").html(result)
            $('#show_log_html').scrollTop($('#show_log_html_content')[0].scrollHeight);
            clearInterval(logTimer);
            return
        }
        $('#show_log_html').scrollTop($('#show_log_html_content')[0].scrollHeight);
        $("#show_log_html_content").html(result + loading)
    }

    function show_compile_log(id) {
        if (!id) {
            id = 0;
        }
        $("#show_log_html").modal("toggle")
        get_build_log(id);
        logTimer = setInterval(function () {
            get_build_log(id);
        }, 3000);
    }

    $(function () { $('#show_log_html').on('hide.bs.modal', function () {
        clearInterval(logTimer);
    })
    });
</script>


</body>
</html>