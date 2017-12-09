<style>

    .radius{
        border-radius:5px;
    }

    label {
        font-weight: 100 !important;
    }

</style>
<div class="row" xmlns="http://www.w3.org/1999/html" style="margin-top: 80px;margin-left: 8px;">
    <div class="col-lg-12" style="margin-top: 3px">
        <div class="panel panel-heading panel-info">
            <h5 class="panel-title"><span style="color: #64c5b1;">添加镜像</span></h5>
        </div>
        <div style="border-top: 2px solid #64c5b1;width: 150px;margin-top: 0px;"></div>
        <div class="card-box col-sm-12" style="margin-top: 0px;margin-left: -3px;">
            <form>
                <div class="col-md-12">
                    <p>&nbsp;
                    </p>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="col-sm-4 control-label"><span style="color: red;margin-right: 2px;">*</span>镜像名称:</label>
                        <div class="col-sm-8">
                            <input type="text" name="name"  class="form-control"  value="${configs.name!}">
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="col-sm-4 control-label"><span style="color: red;margin-right: 2px;">*</span>仓库:</label>
                        <div class="col-sm-8">
                            <input type="text" name="registry"  class="form-control"  value="${configs.registry!}">
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <p>
                    <p>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="col-sm-4 control-label"><span style="color: red;margin-right: 2px;">*</span>标签:</label>
                        <div class="col-sm-8">
                            <input type="text" name="tag"  class="form-control"  value="${configs.tag!}">
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <p>
                    <p>
                </div>
                <div class="col-md-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">&nbsp;描述信息:</label>
                        <div class="col-sm-10">
                            <input type="text"  name="description" class="form-control"   value="${configs.description!}">
                        </div>
                    </div>
                </div>
        </div>
                <div class="form-group col-sm-8">
                    <div>
                        <button onclick="save_images(${configs.imagesId!})" type="button" class="btn btn-custom waves-effect waves-light radius">
                            提交
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <script>

        function save_images(id) {
            var url = "/images/save"
            data = get_form_data();
            if(id){
                data["images"] = id
            }
            post(data, url);
            make_page('/images/list', '/镜像管理/镜像列表');
        }
    </script>
