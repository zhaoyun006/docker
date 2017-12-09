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
    .left-30{
        margin-left: -30px;
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
<div id="add_network_html_id">

</div>
<div class="row" style="margin-top: 78px;margin-left: 1px;margin-right: 1px;">
    <div class="col-sm-12">
        <div class="card-box table-responsive">
            <div class="col-sm-2 left-30" >
            <button onclick="add_network()" class="btn btn-custom waves-effect w-md waves-light btn-xs"
                    style="margin-top: -3px;padding: 4px;border-radius: 5px;font-size:15px;margin-left: 20px;"><i class="fa fa-plus"></i>&nbsp;&nbsp;添加子网
            </button>
            </div>
            <div class="col-sm-2 left-30" >
                <select class="btn-xs w-md waves-effect" style="border: 1px solid #ccc;height: 29px;top: -3px;border-radius: 1px;" onchange="loadData($(this).val())" id="docker_server_groups">
                    <option value="">--请选择--</option>
                <#if groups ?? >
                    <#list groups as group>
                        <option value="${group.groupsId!}">${group.groupsName!}</option>
                    </#list>
                </#if>
                </select>
            </div>
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
                <th>网络名称</th>
                <th>所属组</th>
                <th>网络ID</th>
                <th>子网地址</th>
                <th>容器数量</th>
                <th>创建人</th>
                <th>创建时间</th>
                <th>备注信息</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody style="vertical-align: middle !important;">
            </tbody>
        </table>
</div>

<script type="text/javascript">
    function loadData(groupsId) {
        if(!groupsId){
            groupsId = 0;
        }
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
                "url": "/network/listData?t=" + new Date().getTime()+"&groupsId="+groupsId,
                "type": 'post'
            },
            "columns": [ // 数据映射
                {"data": "name", "mRender":function (data,type,full) {
                    var clustername = full["groupsName"] + full["entId"];
                    return "<a onclick='to_network_detail(\""+data+"\",\""+full["networkIp"]+"\",\""+clustername+"\")'><span class='fron-custom'>"+data+"</span></a>";
                }},
                {"data": "groupsName"},
                {
                    "data": "subnetId", "mRender": function (data, type, full) {
                        return data;
                }
                },
                {"data": "networkIp"},
                {"data": "containerNumber"},
                {"data": "createUser"},
                {"data": "createTime"},
                {"data": "description", "mRender":function (data) {
                    if(data.length > 30){
                        return "<span title='"+data+"'>"  + data.substring(0, 30) + "</span>";
                    }
                    return data;
                }},
                {
                    "data": "networkId", "mRender": function (data, type, full) {
                        return ' <button onclick="add_network()" class="btn btn-custom waves-effect  waves-light btn-xs"' +
                                'style="padding: 4px;border-radius: 5px;">加入容器</button>'+
                                ' <button onclick="delete_network('+data+',\''+full["name"]+'\',\''+full["networkIp"]+'\')" class="btn btn-danger waves-effect  waves-light btn-xs"' +
                                'style="padding: 4px;border-radius: 5px;">删除子网</button>'
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

    function delete_network(id, name,ip) {

        parent.layer.confirm("点击同意后,将删除子网 <b><span class='text-danger'>"+name + "</span></b><br>"+ ip, {
            btn: ['同意', '不同意'], //按钮
            shade: false//不显示遮罩
        }, function () {
            var url = "/network/delete/"+id
            var result = post({}, url);
            result = JSON.stringify(result);
            if(result.indexOf("网络完成") == -1){
                parent.layer.msg(result, {icon: 2})
            }else{
                parent.layer.msg(result, {icon: 1})
                loadData();
            }
        }, function () {
            parent.layer.msg("取消操作", {icon: 2})
        });
    }

    function add_network() {
        html = post({}, "/network/add");
        $("#add_network_html_id").html(html);
        $("#add_getui_config").modal("toggle")
    }

    function to_network_detail(name, networkIp, clustername) {
        var url = "/network/detail/" +name
        make_page(url, "/网络管理/虚拟子网/"+name+"/详情", "?networkIp="+networkIp+"&clusterName="+clustername);
    }
</script>


</body>
</html>