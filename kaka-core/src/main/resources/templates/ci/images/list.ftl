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
            <div class="col-sm-4" style="margin-left: 10px;">
                <select class="btn-xs w-md waves-effect" style="border: 1px solid #ccc;height: 29px;top: -3px;border-radius: 1px;" onchange="loadData($(this).val())" id="docker_server_groups">
                    <option value="">--请选择组--</option>
                <#if groups ?? >
                    <#list groups as group>
                        <option value="${group.groupsName!}">${group.groupsName!}</option>
                    </#list>
                </#if>
                </select>
            </div>
        </div>
    </div>



    <div class="row card-box col-lg-12" style="margin-left:11px; margin-top: -11px;">
        <table id="datatable" class=" table table-bordered table-hover" style="margin-right: 10px;">
            <thead>
            <tr>
                <th>应用名称</th>
                <th>应用域名</th>
                <th>应用类型</th>
                <th>描述信息</th>
                <th>状态</th>
                <th>创建时间</th>
                <th>修改时间</th>
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
                    "url": "/ci/images/data?t=" + new Date().getTime() + "&groups=" + groups,
                    "type": 'post'
                },
                "columns": [ // 数据映射
                    {"data": "serviceName"},
                    {"data": "domain"},
                    {"data": "itemTp"},
                    {"data": "description"},
                    {"data": "buildStatus", "mRender":function (data) {
                        if(data){
                            if (data=="1"){
                                return "<span class='text-info font-14'>编译中</span>";
                            }
                            if (data=="2"){
                                return "<span class='text-danger font-14'>编译失败</span>";
                            }
                            if (data=="0"){
                                return "<span class='text-success font-14'>可编译</span>";
                            }
                            if (data=="3"){
                                return "<span class='text-custom font-14'>编译完成</span>";
                            }
                        }
                        return "<span class='text-success font-14'>可编译</span>";
                    }},
                    {"data": "createTime"},
                    {"data": "lastModifyTime"},
                    {
                        "data": "envId", "mRender": function (data, type, full) {
                        return ' <button onclick="compile_images('+full["envId"]+')" class="btn btn-custom waves-effect  waves-light btn-xs"' +
                                'style="padding: 4px;border-radius: 5px;">构建镜像</button>' +
                                ' <button onclick="to_tags('+full["envId"]+',\''+full["serviceName"]+'\')" class="btn btn-primary waves-effect  waves-light btn-xs"' +
                                'style="padding: 4px;border-radius: 5px;">版本信息</button>'+
                                ' <button title="由于某种异常失败,造成状态一直是编译中，需要删除锁定继续更新" onclick="update_build_status('+full["envId"]+')" class="btn btn-warning waves-effect  waves-light btn-xs"' +
                                'style="padding: 4px;border-radius: 5px;">删除锁定</button>';
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

        function update_build_status(envId) {
            var url = "/ci/images/deleteLock/"+envId;
            var result = post({}, url);
            parent.layer.msg(JSON.stringify(result), {icon: 1});
            make_page('/ci/images/list', '/持续集成/镜像编译');
        }
        
        function to_tags(id,name) {
            make_page("/ci/images/tags/list/"+id, "/持续集成/镜像编译/镜像版本/"+name, "?envId="+id);
        }

        function compile_images(id) {
            if(!id){
                id=0;
            }
            make_page("/ci/images/build", "/持续集成/镜像编译/构建镜像", "?envId="+id);
        }
    </script>


    </body>
</html>