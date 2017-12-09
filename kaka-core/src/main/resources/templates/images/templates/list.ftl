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
                <button onclick="add_template()" class="btn btn-custom waves-effect w-md waves-light btn-xs"
                        style="margin-top: -3px;padding: 4px;border-radius: 5px;font-size:15px;margin-left: 0px;"><i class="fa fa-plus"></i>&nbsp;&nbsp;添加镜像模板
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
                <th>模板名称</th>
                <th>图标</th>
                <th>描述信息</th>
                <th>创建时间</th>
                <th>创建用户</th>
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
                    "url": "/images/templates/data?t=" + new Date().getTime() + "&groups=" + groups,
                    "type": 'post'
                },
                "columns": [ // 数据映射
                    {"data": "imageName"},
                    {"data": "imageUrl","mRender":function (data) {
                        return "<img height='30px' width='30px' src='"+data+"'/>"
                    }},
                    {"data": "description"},
                    {"data": "createTime"},
                    {"data": "createUser"},
                    {
                        "data": "templateId", "mRender": function (data, type, full) {
                            if(!data){return ""}
                        return ' <button onclick="add_template('+full["templateId"]+')" class="btn btn-custom waves-effect  waves-light"' +
                                'style="padding: 4px;border-radius: 5px;">编辑</button>' +
                                ' <button onclick="delete_template('+full["templateId"]+')" class="btn btn-danger waves-effect  waves-light"' +
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

        function delete_template(id, name) {
            parent.layer.confirm("点击同意后,将删除模板 <b><span class='text-danger'>"+name + "</span></b><br>", {
                btn: ['同意', '不同意'], //按钮
                shade: false//不显示遮罩
            }, function () {
                var url = "/images/templates/delete/"+id
                var result = post({}, url);
                result = JSON.stringify(result);
                parent.layer.msg(result, {icon: 1})
                loadData();
            }, function () {
                parent.layer.msg("取消操作", {icon: 2})
            });
        }


        function add_template(id) {
            if(!id){
                id=0;
            }
            make_page("/images/templates/add", "/镜像管理/模板管理/添加模板", "?templateId="+id);
        }
    </script>


    </body>
</html>