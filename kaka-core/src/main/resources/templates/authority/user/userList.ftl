<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户管理</title>

    <link rel="shortcut icon" href="favicon.ico">
    <#include "/commons/basecss.ftl"/>
    <!-- 全局js -->
    <#include "/commons/basejs.ftl"/>
    <script src="/static/js/plugins/layer/laydate/laydate.js"></script>
    <script src="/static/js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="/static/js/plugins/dataTables/dataTables.bootstrap.js"></script>
</head>
<div id="editUser"></div>
<body>
	<div class=" animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
                    <form id="searchForm">
						<div class="ibox-title">
                            <div class="row">
                                <div class="col-sm-4">
                                    <div class="input-group">
                                        <span class="input-group-addon">用户名：</span>
                                        <input type="text" id="userName" name="userName" class="form-control" placeholder="用户名">
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="input-group">
                                        <span class="input-group-addon">认证账号：</span>
                                        <input type="text" id="thirdId" name="thirdId" class="form-control" placeholder="认证账号">
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="input-group">
                                        <span class="input-group-addon">真实姓名：</span>
                                        <input type="text" id="thirdTrueName" name="thirdTrueName" class="form-control" placeholder="真实姓名">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <div class="ibox-title">
                        <button class="btn btn-primary " id="queryBtn" type="button">
                            <i class="fa fa-search"></i>&nbsp;&nbsp;<span class="bold">查询</span>
                        </button>
                        <button class="btn btn-success " type="button"
                                onclick="toEditUser();">
                            <i class="fa fa-upload"></i>&nbsp;&nbsp;<span class="bold">添加</span>
                        </button>
                        <div class="ibox-tools">
                            <a class="collapse-link"> <i class="fa fa-chevron-up"></i>
                            </a> <a class="dropdown-toggle" data-toggle="dropdown"
                                    href="table_data_tables.html#"> <i class="fa fa-wrench"></i>
                        </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="table_data_tables.html#">选项1</a></li>
                                <li><a href="table_data_tables.html#">选项2</a></li>
                            </ul>
                            <a class="close-link"> <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
					<div class="ibox-content">
						<table id="listTable"
							class="table table-striped table-bordered table-hover dataTables-example">
							<thead>
								<tr>
                                    <th>用户名</th>
                                    <th>真实姓名</th>
									<th>认证账号</th>
                                    <th>用户邮箱</th>
                                    <th>用户电话</th>
                                    <th>状态</th>
                                    <th>操作</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">

		function loadData(query){
			$("#listTable").dataTable({
                "filter": false,//去掉搜索框
                "ordering": false, // 是否允许排序
                "paginationType": "full_numbers", // 页码类型
                "destroy": true,
                "processing": true,
                "serverSide": true,
                "scrollX": true, // 是否允许左右滑动
                "displayLength": 10, // 默认长度
                "ajax": { // 请求地址
                    "url": "/authority/user/search?t=" + new Date().getTime() + "&" + query,
                    "type": 'post'
                },
                "columns": [ // 数据映射
                    {"data": "userName"},
                    {"data": "thirdTrueName"},
                    {"data": "thirdId"},
                    {"data": "userEmail"},
                    {"data": "userMobile"},
                    {
                        "data": "isValid",
                        "render":function(data, type, full){
                            if(data == 1){
                                return "启用";
                            }else{
                                return "禁用";
                            }
                        }
                    },
                    {
                        "data":"userId",
                        "render": function (data, type, full) {
                            return '<button type="button" onclick="toEditUser('+data+')" class="btn btn-sm btn-warning">编辑</button>&nbsp;'+
                                   '<button type="button" onClick="deleteUser('+data+')" class="btn btn-sm btn-danger">删除</button>';
                        }
                    }
                ],
                "fnRowCallback": function (row, data) { // 每行创建完毕的回调
                }
			});
		}



		$(document).ready(function(){
			//请求数据
			loadData($("#searchForm").serialize());

            // 查询按钮
            $("#queryBtn").click(function () {
                loadData($("#searchForm").serialize());
            });

		});

        /**
         * 跳转页面
         */
        function toEditUser(userId){
            url = "/authority/user/toEdit";
            html = post({'userId':userId}, url);
            $('#editUser').html(html);
            $('#userEdit').modal('toggle');
        }

        /**
         * 保存名单
         */
        function saveUser(){
            $.ajax({
                url: '/authority/user/save?t='+new Date().getTime(),
                type: 'POST',
                data: $("#editForm").serialize(),
                dataType: 'json',
                success: function (data) {
                    parent.layer.msg(JSON.stringify(data), {icon: 1});
                    if (data.status == "ok") {
                        $('#userEdit').modal('toggle');
                        setTimeout(function () {
                            location.reload();
                        }, 1000)
                    }
                }
            });

        }




        /**
         * 删除名单
         * @param id
         */
        function deleteUser(userId){
            url = "/authority/user/deleteById";
            parent.layer.confirm("点击同意后,将删除数据", {
                btn: ['同意','不同意'], //按钮
                shade: false//不显示遮罩
            }, function(){
                result = post({'userId':userId}, url);
                parent.layer.msg(JSON.stringify(result),  {icon:1})
                location.reload();
            }, function(){
                parent.layer.msg("取消操作",  {icon:2})
            });
        }

	</script>
</body>
</html>
