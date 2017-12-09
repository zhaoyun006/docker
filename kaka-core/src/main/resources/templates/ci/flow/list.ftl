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
<div id="add_html"></div>
<div class="row" style="margin-top: 78px;margin-left: 1px;margin-right: 1px;">
    <div class="col-sm-12">
        <div class="card-box table-responsive">
            <div class="col-sm-5">
                <div class="form-group col-sm-3">
                    <div>
                        <button onclick="save_env()" type="button"
                                class="btn btn-info waves-effect waves-light radius">
                            <i class="fa fa-history"></i>&nbsp;合并历史
                        </button>
                    </div>
                </div>
                <div class="form-group col-sm-3">
                    <div>
                        <button onclick="make_page('/ci/flow/image/push/list', '/持续集成/流水线/推送历史');" type="button"
                                class="btn btn-custom waves-effect waves-light radius">
                            <i class="fa fa-history"></i>&nbsp;推送历史
                        </button>
                    </div>
                </div>
                <div class="form-group col-sm-3">
                    <div>
                        <button onclick="make_page('/ci/flow/release/list', '/持续集成/流水线/更新历史');" type="button"
                                class="btn btn-danger  waves-effect waves-light radius">
                            <i class="fa fa-history"></i>&nbsp;更新历史
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
        <div class="col-sm-12">
                <div class="card-box table-responsive">
        <table id="datatable" class=" table table-bordered table-hover" style="margin-right: 10px;">
            <thead>
            <tr>
                <th>应用名称</th>
                <th>应用域名</th>
                <th>所属部门</th>
                <th>最新版本</th>
                <th>构建类型</th>
                <th>创建时间</th>
                <th>应用信息</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody style="vertical-align: middle !important;">
            </tbody>
        </table>
    </div>
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
                    "url": "/ci/flow/data?t=" + new Date().getTime() + "&groups=" + groups,
                    "type": 'post'
                },
                "columns": [ // 数据映射
                    {"data": "serviceName"},
                    {"data": "domain"},
                    {"data": "dept"},
                    {"data": "tag"},
                    {"data": "buildTp"},
                    {"data": "createTime"},
                    {"data": "description","mRender": function (data){
                        if (data.length > 15){
                            return "<span title='"+data+"'>"+data.substring(0, 15)+"</span>"
                        }
                              return data;
                     }},
                    {
                        "data": "envId", "mRender": function (data, type, full) {
                        return '<button onclick="merger_image(' + data + ')" title="将当前镜像合并到不同仓库" class="btn btn-info waves-effect  waves-light "' +
                                'style="padding: 4px;border-radius: 5px;">合并镜像</button>' +
                                ' <button onclick="push_image(' + data + ')"  title="将镜像预先推送到服务器,预加载镜像" class="btn btn-custom waves-effect  waves-light"' +
                                'style="padding: 4px;border-radius: 5px;">推送镜像</button>' +
                                ' <button onclick="update_service(' + data + ')" title="使用镜像更新容器,并重启服务" class="btn btn-danger waves-effect  waves-light "' +
                                'style="padding: 4px;border-radius: 5px;">更新服务</button>' +
                                ' <button onclick="rollback_service(' + data + ')" title="回滚docker容器到上次发布的版本或其他可用版本" class="btn btn-primary waves-effect  waves-light"' +
                                'style="padding: 4px;border-radius: 5px;">回滚服务</button>';
                    }
                    },
                ],
                "fnRowCallback": function (row, data) { // 每行创建完毕的回调
                    $(row).data('recordId', data.recordId);
                }
            })
            ;
        }
        loadData("${groupsName!}");

        function push_image(envId) {
            var url = "/ci/flow/pushImage/" + envId;
            var result = post({}, url)

            $("#add_html").html(result);
            $("#app_add_html").modal('toggle');
        }


        function merger_image(envId) {
            var url = "/ci/flow/mergerImage/" + envId;
            var result = post({}, url)
            $("#add_html").html(result);
            $("#app_add_html").modal('toggle');
        }



        function update_service(envId, version) {
            if (!version) {
                version = ""
            }
            var url = "/ci/flow/updateService/" + envId;
            var result = post({version: version}, url)
            $("#add_html").html(result);
            $("#app_add_html").modal('toggle');
        }

        function rollback_service(envId) {
            var url = "/ci/flow/rollbackService/" + envId;
            var result = post({}, url)
            $("#add_html").html(result);
            $("#app_add_html").modal('toggle');
        }

    </script>


    </body>
</html>