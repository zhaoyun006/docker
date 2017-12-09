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

</style>
<link href="/static/css/menu.css" rel="stylesheet" type="text/css"/>
<div id="add_network_html_id">

</div>
<div class="row" style="margin-top: 78px;margin-left: 1px;margin-right: 1px;">
    <div class="col-sm-12">
        <div class="card-box table-responsive" style="border-left: 2px solid hsla(168, 46%, 58%, 0.57);">
           <b> 子网容器详情 </b><span style="margin-left: 10px;"><b>名称:</b>&nbsp;&nbsp;${name}</span><span style="margin-left: 10px;"><b>IP:</b>&nbsp;&nbsp;${networkIp}</span>
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
        <table id="datatable" class=" table table-bordered table-simple table-hover" style="margin-right: 10px;">
            <thead>
            <tr>
                <th>容器名称</th>
                <th>所属服务</th>
                <th>所在网络</th>
                <th>IP信息</th>
                <th>所属主机</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody style="vertical-align: middle !important;">
            </tbody>
        </table>
</div>

<script type="text/javascript">
    function loadData() {
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
                "url": "/network/detailData?t=" + new Date().getTime()+"&name=${name}&clusterName=${clusterName!}",
                "type": 'post'
            },
            "columns": [ // 数据映射
                {"data": "name", "mRender":function (data) {
                    return "<a onclick='to_network_detail(\""+data+"\")'><span class='fron-custom'>"+data+"</span></a>";
                }},
                {"data": "networkName","mRender":function (data,type,full) {
                    return full["name"].split(".")[0];
                }},
                {
                    "data": "networkName", "mRender": function (data, type, full) {
                        return data;
                }
                },
                {"data": "network"},
                {"data": "host"},
                {
                    "data": "id", "mRender": function (data, type, full) {
                        return ' <button onclick="" class="btn btn-custom waves-effect  waves-light btn-xs"' +
                                'style="padding: 4px;border-radius: 5px;">从网络中移除</button>';
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

</script>


</body>
</html>