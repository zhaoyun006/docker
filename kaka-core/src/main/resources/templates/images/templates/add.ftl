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
            <h5 class="panel-title"><span style="color: #64c5b1;">添加镜像模板</span></h5>
        </div>
        <div style="border-top: 2px solid #64c5b1;width: 150px;margin-top: 0px;"></div>
        <div class="card-box col-sm-12" style="margin-top: 0px;margin-left: -3px;">
            <form>
                <div class="col-md-12">
                    <p>&nbsp;
                    </p>
                </div>
                <div class="col-md-8">
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><span style="color: red;margin-right: 2px;">*</span>模板名称:</label>
                        <div class="col-sm-8">
                            <input type="text" name="imageName"  class="form-control"  value="${configs.imageName!}">
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <p>
                    <p>
                </div>
                <div class="col-md-12 top">
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><span style="color: red;margin-right: 2px;">*</span>模板属性:</label>
                        <div title="公开后所有人可以看到该模板内容" class="radio radio-info col-sm-2" style="margin-top: -4px;width: 100px;margin-left: -50px;top: 5px;
}">
                            <input type="radio"  checked name="property" id="buildTp1" value="0">
                            <label for="buildTp1">
                                公开
                            </label>
                        </div>
                        <div title="私有属性只有创建人能看到该模板" class="radio radio-info col-sm-2" style="width: 100px;top: 5px;">
                            <input type="radio"  name="property" id="buildTp2" value="1">
                            <label for="buildTp2">
                                私有
                            </label>
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <p>
                    <p>
                </div>
                <div class="col-md-8">
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><span style="color: red;margin-right: 2px;">*</span>图标地址:</label>
                        <div class="col-sm-8">
                            <input type="text" name="imageUrl"  class="form-control"  value="${configs.imageUrl!}">
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <p>
                    <p>
                </div>
                <div class="col-md-8">
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><span style="color: red;margin-right: 2px;">*</span>Dockerfile:</label>
                        <div class="col-sm-8">
                            <textarea type="text" rows="10" name="dockerFile"  class="form-control"  value="">${configs.dockerFile!}</textarea>
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <p>
                    <p>
                </div>
                <div class="col-md-8">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">&nbsp;描述信息:</label>
                        <div class="col-sm-8">
                            <input type="text"  name="description" class="form-control"   value="${configs.description!}">
                        </div>
                    </div>
                </div>
        </div>
                <div class="form-group col-sm-8">
                    <div>
                        <button onclick="save_images(${configs.templateId!})" type="button" class="btn btn-custom waves-effect waves-light radius">
                            提交
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <script>

        function save_images(id) {
            var url = "/images/templates/save"
            data = get_form_data();
            if(id){
                data["templateId"] = id
            }
            post(data, url);
            make_page('/images/templates/list', '/镜像管理/模板管理');
        }
    </script>
