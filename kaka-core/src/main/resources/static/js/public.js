/*
 * 
 *  url = "/devops/?page=user.user"
 *  paramter = {id:id,user:user}
 *  
 */
function post(paramter , url){
    var result = "";
    $.ajax({
        type:"POST",
        url:url,
        data: paramter,
        async:false,
        success:function(data){
            result = data;
        }
    });
    return result;
}


function post_get(url) {
    xml = GenXMLData(tableName, fieldID, "", "");
    contentTD.innerHTML = content;
    var XmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    XmlHttp.onreadystatechange = function () {
        if (XmlHttp.readyState == 4) {
            if (XmlHttp.status == 200) {
                firstPost = true;
            }
        }
    }
    XmlHttp.open("post", url, true);
    XmlHttp.send(xml);
}

//获取from数据
function get_form_data(){
    var result = {}
    forch = ["input","textarea","select"]
    for(i=0;i<forch.length;i++){
        $.each($("form "+forch[i]),
            function(name,object){
                result[$(object).attr("name")] = $(object).val()
            }
        );
    }
    return result;
}