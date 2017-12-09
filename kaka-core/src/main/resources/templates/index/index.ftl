<#include "/commons/header.ftl"/>

<!-- Dashboard init -->
<#--<script src="/static/pages/jquery.dashboard.js"></script>-->

<!-- App js -->
<script src="/static/js/jquery.core.js"></script>
<script src="/static/js/jquery.app.js"></script>
<script src="/static/plugins/jquery-toastr/jquery.toast.min.js" type="text/javascript"></script>
<script src="/static/js/sockjs.min.js"></script>
<script src="/static/js/stomp.min.js"></script>
<script>

    result = post({}, "/user.do")
    if (result) {
        $('#login-user').html(result)
    }
    var stompClient;
    function messages() {
        var socket = new SockJS('/cloud-messages');
        stompClient = Stomp.over(socket);
        stompClient.debug = false;
        stompClient.connect({}, function (frame) {
            // 注册发送消息
            stompClient.subscribe('/topic/send', function (mgs) {
            });
            // 注册推送时间回调
            stompClient.subscribe('/topic/callback', function (r) {
                r = r.body;
                r = JSON.parse(r);
                if (r.user){
                    if (r.user == "${username}"){
                        success(r.messages)
                    }
                }else{
                    success(r.messages)
                }
            });
        });
    }
    messages();

    function make_page(url, title, param) {
        if(param){
            url = url + param;
        }
        html = post({}, url)
        $("#page_title").html("<span onclick='make_page(\"" + url + "\",\"" + title + "\")'>" + title + "</span>")
        $("#html_content").html(html);
    }
    make_page("/home", "/主页");
</script>
</body>
</html>