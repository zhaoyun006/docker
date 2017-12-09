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
                <button onclick="add_ent()" class="btn btn-custom waves-effect w-md waves-light btn-xs"
                        style="margin-top: -3px;padding: 4px;border-radius: 5px;font-size:15px;margin-left: 0px;"><i class="fa fa-plus"></i>&nbsp;&nbsp;添加环境
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
                <th>环境名称</th>
                <th>最小初始化</th>
                <th>最大扩容</th>
                <th>默认CPU</th>
                <th>默认内存</th>
                <th>默认网络</th>
                <th>描述信息</th>
                <th>最近修改时间</th>
                <th>最近修改人</th>
                <th>创建时间</th>
                <th>创建人</th>
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
                    "url": "/ent/data?t=" + new Date().getTime(),
                    "type": 'post'
                },
                "columns": [ // 数据映射
                    {"data": "entname"},
                    {"data": "containerMin"},
                    {"data": "containerMax"},
                    {"data": "cpu"},
                    {"data": "memory"},
                    {"data": "defaultNetwork"},
                    {"data": "description"},
                    {"data": "lastModifyTime"},
                    {"data": "lastModifyUser"},
                    {"data": "createTime"},
                    {"data": "createUser"},
                    {
                        "data": "entId", "mRender": function (data,type, full) {
                        return ' <button onclick="add_ent('+data+')" class="btn btn-custom waves-effect  waves-light btn-xs"' +
                                'style="padding: 4px;border-radius: 5px;">编辑</button>' +
                                ' <button onclick="delete_ent('+data+',\''+full["entname"]+'\')" class="btn btn-danger waves-effect  waves-light btn-xs"' +
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

        function add_ent(id) {
            if(!id){
                id=0;
            }
            make_page("/ent/add", "/环境管理/环境配置管理/添加环境", "?entId="+id);
        }


        function delete_ent(id, name) {
                parent.layer.confirm("点击同意后,将删除环境"+name, {
                    btn: ['同意', '不同意'], //按钮
                    shade: false//不显示遮罩
                }, function () {
                    var url = "/ent/delete/"+id;
                    var result = post({}, url);
                    result = JSON.stringify(result);
                    if(result.indexOf("error") != -1){
                        parent.layer.msg(result, {icon: 2})
                    }else{
                        parent.layer.msg(result, {icon: 1})
                    }
                }, function () {
                    parent.layer.msg("取消操作", {icon: 2})
                });
        }

    </script>


    </body>
</html>