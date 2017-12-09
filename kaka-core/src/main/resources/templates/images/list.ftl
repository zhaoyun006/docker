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
                <button onclick="add_images()" class="btn btn-custom waves-effect w-md waves-light btn-xs"
                        style="margin-top: -3px;padding: 4px;border-radius: 5px;font-size:15px;margin-left: 0px;"><i class="fa fa-plus"></i>&nbsp;&nbsp;添加镜像
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
                <th>镜像名称</th>
                <th>来源</th>
                <th>所属仓库</th>
                <th>tag</th>
                <th>大小</th>
                <th>创建时间</th>
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
                    "url": "/images/data?t=" + new Date().getTime() + "&groups=" + groups,
                    "type": 'post'
                },
                "columns": [ // 数据映射
                    {"data": "name", "mRender": function (data, type, full) {
                            if(data.indexOf("None") == 0 || (full["registry"].indexOf("[") != -1 && full["registry"].indexOf("@") != -1)){
                                var datas = full["registry"].replace(/\[/g,"");
                                datas = datas.replace(/\]/g,"")
                                if(full["registry"].indexOf("/") == -1){
                                    return datas.split("@")[0];
                                }
                                datas = datas.split(",");;
                            var r = "";
                            for (i=0;i<datas.length;i++){
                                if(datas[i]){
                                    r += datas[i].split("@")[0].split("/")[1] +"<br>"
                                }
                            }
                            return r;
                        }
                        return data;
                    }
                    },
                    {"data": "from"},
                    {"data": "registry", "mRender": function (data, type, full) {
                            if(data.indexOf("[") != -1 && data.indexOf("@") != -1){
                                var datas = data.replace(/\[/g,"");
                                datas = datas.replace(/\]/g,"").split(",");
                                var r="";
                                for (i=0;i<datas.length;i++){
                                    if(datas[i]){
                                        r += datas[i].split("@")[0] +"<br>"
                                    }
                                }
                                return r;
                            }
                        return data;
                    }
                    },
                    {"data": "tag", "mRender":function (data) {
                        var datas = data.replace(/\[/g,"");
                        datas = datas.replace(/\]/g,"").split(",");
                        var r="";
                        for (i=0;i<datas.length;i++){
                            if(datas[i]){
                                r += datas[i].split("@")[0] +"<br>"
                            }
                        }
                        return r;
                    }},
                    {"data": "size"},
                    {"data": "createTime"},
                    {
                        "data": "imagesId", "mRender": function (data, type, full) {
                            if(!data){return ""}
                        return ' <button onclick="add_images('+full["imagesId"]+')" class="btn btn-primary waves-effect  waves-light btn-xs"' +
                                'style="padding: 4px;border-radius: 5px;">编辑</button>';
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

        function add_images(id) {
            if(!id){
                id=0;
            }
            make_page("/images/add", "/镜像管理/添加镜像", "?imagesId="+id);
        }
    </script>


    </body>
</html>