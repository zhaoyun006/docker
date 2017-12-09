<style>

    .radius{
        border-radius:5px;
    }

    label {
        font-weight: 100 !important;
    }
    .btn-asura {
        padding: 4px;
        border-right: 0px !important;
        border: 1px solid #64c5b1 !important;
        border-bottom-left-radius: 5px;
        border-top-left-radius: 5px;
        color: #64c5b1;
        padding: 3px;
    }

    .btn-asura-default {
        padding: 4px;
        border-right: 0px !important;
        border: 1px solid #ccc !important;
        border-bottom-left-radius: 5px;
        border-top-left-radius: 5px;
        color: #ccc;
        padding: 3px;
    }
</style>
<div class="row" xmlns="http://www.w3.org/1999/html" style="margin-top: 80px;margin-left: 8px;">
    <div class="col-lg-12" style="margin-top: 3px">
        <div class="panel panel-heading panel-info">
            <h5 class="panel-title"><span style="color: #64c5b1;">创建应用栈</span></h5>
        </div>
        <div style="border-top: 2px solid #64c5b1;width: 150px;margin-top: 0px;"></div>
        <div class="card-box col-sm-12" style="margin-top: 0px;margin-left: -3px;">
            <form>
                <div class="form-group col-sm-8">
                    <label for="appName" class="col-sm-2 form-control-label">名称<span
                            class="text-danger">*</span></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control " name="appName" required id="appName"
                               placeholder="应用名称"/>
                    </div>
                </div>

                <div class="form-group col-sm-8">
                    <label class="col-sm-2 form-control-label">描述:</label>
                    <div class="col-sm-10">
                        <textarea rows="3" class="form-control " name="appDescription"
                                  placeholder="应用描述信息"/></textarea>
                    </div>
                </div>
                <div class="form-group col-sm-8">
                    <label class="col-sm-2 form-control-label">创建方式:</label>
                    <div class="col-sm-10">
                        <span onclick="change_add_color(this)" style="padding: 3px;"
                              class="btn-class btn btn-asura btn-bordered waves-effect w-xs waves-light">编排脚本</span>
                        <span onclick="change_add_color(this)" style="margin-left: -3px;padding: 3px;"
                              class="btn-class btn btn-asura-default btn-bordered waves-effect w-xs waves-light">上传文件</span>
                        <span onclick="change_add_color(this)" style="margin-left: -3px;padding: 3px;"
                              class="btn-class btn btn-asura-default btn-bordered waves-effect w-xs waves-light">本地文件</span>
                    </div>
                </div>
                <div class="form-group col-sm-8">
                    <label class="col-sm-2 form-control-label">编排脚本:</label>
                    <div class="col-sm-10">
                        <textarea rows="5" class="form-control" name="compose"
                                  placeholder=""/></textarea>
                    </div>
                </div>
                <div class="form-group col-sm-8">
                    <div>
                        <button onclick="add_app_save()" type="button" class="btn btn-custom waves-effect waves-light radius">
                            提交
                        </button>
                        <button type="reset" class="btn btn-default waves-effect m-l-5 radius ">
                            重置
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <script type="text/javascript" src="/static/plugins/parsleyjs/parsley.min.js"></script>
    <script>
        function change_add_color(obj) {
            $(".btn-class").removeClass("btn-asura")
            $(".btn-class").addClass("btn-asura-default")
            $(obj).removeClass("btn-asura-default")
            $(obj).addClass("btn-asura");
        }
        function add_app_save() {
            url = "/app/save"
            data = get_form_data();
            if (data["appName"].length<3){
                parent.layer.msg("应用栈名称必须填写",  {icon:2});
                return
            }
            result = post(data, url);
            html = '<font class="text-dark"><strong>容器创建成功!</strong><br> 创建容器成功:1个<br>创建容器失败:0个<br></font>';
            success(html, 5000)
            make_page('/app/list', '/应用管理/应用栈列表');
        }
    </script>
