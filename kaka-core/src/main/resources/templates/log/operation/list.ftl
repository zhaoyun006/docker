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
<link href="/static/css/menu.css" rel="stylesheet" type="text/css"/>
<div class="row" style="margin-top: 98px;margin-left: 1px;margin-right: 1px;">
    <div class="row card-box col-lg-12" style="margin-left:11px; margin-top: -11px;">
        <table id="datatable" class=" table table-bordered table-hover" style="margin-right: 10px;">
            <thead>
            <tr>
                <th>操作人</th>
                <th style="word-wrap:break-word;">日志内容</th>
                <th>操作IP</th>
                <th>操作时间</th>
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
                "url": "/log/operation/data?t=" + new Date().getTime(),
                "type": 'post'
            },
            "columns": [ // 数据映射
                {"data": "user", "sWidth": "15%"},
                {
                    "data": "messages", "sWidth": "54%", "mRender": function (data) {
                    d = "";
                    for (i = 0; i < data.length; i++) {
                        d += data[i]
                        if(i % 110 == 0){
                            d += "<br>"
                        }
                    }
                    return d;
                }
                },
                {"data": "ip", "sWidth": "14%"},
                {"data": "time", "sWidth": "17%"},
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