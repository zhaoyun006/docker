<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<body>
<div class="modal fade" id="userEdit" tabindex="-1" role="dialog" data-backdrop="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header gray-bg">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title">用户编辑</h4>
            </div>
            <div class="modal-body">
                <form id="editForm">
                    <input type="hidden" name="userId" id = "userId" value="${(user??)?then(user.userId!, "")}">
                    <div class="form-group">
                        <label class="control-label">用户名:</label>
                        <div>
                            <input type="text" name="userName" class="form-control" value="${(user??)?then(user.userName!, "")}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">认证账号:</label>
                        <div>
                            <input type="text" name="thirdId" class="form-control"
                                   value="${(user??)?then(user.thirdId!, '')}" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">真实姓名:</label>
                        <div>
                            <input type="text" name="thirdTrueName" class="form-control"
                                   value="${(user??)?then(user.thirdTrueName!, "")}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">用户邮箱:</label>
                        <div>
                            <input type="text" name="userEmail" class="form-control"
                                   value="${(user??)?then(user.userEmail!, '')}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">用户电话:</label>
                        <div>
                            <input type="text" name="userMobile" class="form-control"
                                   value="${(user??)?then(user.userMobile!, "")}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">是否启用:</label>
                        <div>
                            <select name="isValid" class="form-control">
                                <option value="1" ${(user?? && user.isValid == 1)?then('selected', '')}>启用</option>
                                <option value="0" ${(user?? && user.isValid == 0)?then('selected', '')}>禁用</option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary btn-mid" type="button" onclick="saveUser()">
                    <i class="fa fa-check"></i>&nbsp;提交
                </button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">

</script>
</body>
