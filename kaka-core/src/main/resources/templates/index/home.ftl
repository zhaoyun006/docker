<!-- Start content -->
<div class="content">
    <div class="container">
        <!-- end row -->
        <div class="row"><br></div>
        <div class="row text-dark">
            <div class="col-lg-3 col-md-6 width21">
                <div class="card-box widget-box-two widget-two-custom">
                    <i class="mdi mdi-desktop-mac widget-two-icon top10"></i>
                    <div class="wigdet-two-content">
                        <p class="m-0 text-uppercase font-bold font-secondary text-overflow" title="Statistics">
                            主机数量(台)</p>
                        <h5 class="text-dark"><b> <span data-plugin="counterup">${hostNumber?default("0")}</span></b></h5>
                    </div>
                </div>
            </div><!-- end col -->

            <div class="col-lg-2 col-md-4 width12">
                <div class="card-box widget-box-two widget-two-custom">
                    <div class="wigdet-two-content">
                        <h6><i class="mdi mdi-arrow-down-bold-hexagon-outline text-success"></i><span
                                class="text-success">13</span>&nbsp;在线</h6>
                        <h6><i class="mdi mdi-arrow-down-bold-hexagon-outline text-danger"></i><span
                                class="text-danger">10</span>&nbsp;离线</h6>
                    </div>
                </div>
            </div><!-- end col -->
            <div class="col-lg-4 col-md-6">
                <div class="card-box widget-box-two widget-two-custom">
                    <div class="wigdet-two-content" style="height: 440px;" id="host_detail_html">
                    </div>
                </div>
            </div><!-- end col -->
            <div class="row text-dark">
                <div class="col-lg-3 col-md-6 width21">
                    <div class="card-box widget-box-two widget-two-custom">
                        <i class="mdi mdi-fish widget-two-icon top10"></i>
                        <div class="wigdet-two-content">
                            <p class="m-0 text-uppercase font-bold font-secondary text-overflow"
                               title="Statistics">容器总数(个)</p>
                            <h5 class="text-dark"><b><span data-plugin="counterup">${containerNumber.totle!}</span></b></h5>
                        </div>
                    </div>
                </div><!-- end col -->

                <div class="col-lg-1 col-md-4 width12">
                    <div class="card-box widget-box-two widget-two-custom">
                        <div class="wigdet-two-content">
                            <div class="wigdet-two-content" style="font-size: 10px;    margin-left: -110px;">
                                <i class="mdi mdi-arrow-down-bold-hexagon-outline text-success"></i>&nbsp;运行中<br><span
                                    class="text-success">${containerNumber.runing!}</span><br>
                              <i class="mdi mdi-arrow-down-bold-hexagon-outline text-danger"></i>&nbsp;已停止<br><span
                                    class="text-danger">${containerNumber.shutdown!}</span>
                            </div>
                        </div>
                    </div>
                </div><!-- end col -->
            </div>
            <!-- end row -->
            <div class="col-lg-4 top-390">
                <div class="card-box">
                    <h4 class="text-dark header-title m-t-0 m-b-30">主机状态分布</h4>
                    <div class="widget-chart text-center" style="height: 280px;">
                        <div id="server-pie-chart" style="height: 200px;"></div>
                        <table class="col-sm-12 ">
                            <tr>
                                <th class="host_th text-success">在线</th>
                                <th class="host_th text-warning">离线</th>
                                <th class="host_th text-primary">安装中</th>
                                <th class="host_th text-danger">已失败</th>
                                <th class="host_th text-dark">未知</th>
                            </tr>
                            <tr>
                                <td><span id="server_online_number">20</span>台</td>
                                <td><span id="server_offline_number">0</span>台</td>
                                <td><span id="server_install_number">0</span>台</td>
                                <td><span id="server_install_fail_number">0</span>台</td>
                                <td><span id="server_unknown_number">0</span>台</td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
            <div class="col-lg-4  top-390" style="margin-left: 66%">
                <div class="card-box">
                    <h4 class="text-dark header-title m-t-0 m-b-30">容器状态分布</h4>
                    <div class="widget-chart text-center" style="height: 280px;">
                        <div id="container-pie-chart" style="height: 200px;"></div>
                        <table class="col-sm-12 ">
                            <tr>
                                <th class="host_th text-success">运行中</th>
                                <th class="host_th text-warning">已停止</th>
                                <th class="host_th text-primary">创建中</th>
                                <th class="host_th text-danger">已失败</th>
                                <th class="host_th text-dark">未知</th>
                            </tr>
                            <tr>
                                <td><span id="docker_online_number">${containerNumber.runing!}</span>台</td>
                                <td><span id="docker_offline_number">${containerNumber.shutdown!}</span>台</td>
                                <td><span id="docker_install_number">0</span>台</td>
                                <td><span id="docker_install_fail_number">1</span>台</td>
                                <td><span id="docker_unknown_number">0</span>台</td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
            <div class="col-lg-12 col-md-12">
                <div class="text-center m-b-30 card-box">
                    <div class="row">
                        <div class="col-xs-6 col-sm-3  widget-box-two widget-two-custom">
                            <i class="mdi mdi-application widget-two-icon  no-top "></i>
                            <div class="m-t-20 m-b-20">
                                <div class="m-b-15 font-13 font-600"><span class="text-dark  font-18"
                                                                           id="application-number">${envSize!}</span>&nbsp;(个)应用
                                </div>
                                <p class="text-uppercase m-b-5 font-10 font-600">应用模板&nbsp;<span
                                        id="application-template-number" class="text-success">${templateSize!}</span>&nbsp;</p>
                            </div>
                        </div>
                        <div class="col-xs-6 col-sm-3  widget-box-two widget-two-custom">
                            <i class="mdi mdi-library  widget-two-icon  no-top "></i>
                            <div class="m-t-20 m-b-20">
                                <div class="m-b-15 font-13 font-600"><b><span class="text-dark  font-18"
                                                                              id="item-number">25</span></b>&nbsp;(个)工程
                                </div>
                                <p class="text-uppercase m-b-5 font-10 font-600">构建次数&nbsp;<span
                                        id="item-compile-number" class="text-success">${buildLog!}</span>&nbsp;</p>
                            </div>
                        </div>
                        <div class="col-xs-6 col-sm-3 widget-box-two widget-two-custom">
                            <i class="mdi mdi-image-filter widget-two-icon  no-top "></i>
                            <div class="m-t-20 m-b-20">
                                <div class="m-b-15 font-13 font-600"><b><span class="text-dark  font-18"
                                                                              id="image-public-number">25</span></b>&nbsp;(个)公共镜像
                                </div>
                                <p class="text-uppercase m-b-5 font-10 font-600">私有镜像&nbsp;<span
                                        id="image-privite-number" class="text-success">0</span>&nbsp;</p>
                            </div>
                        </div>
                        <div class="col-xs-6 col-sm-3  widget-box-two widget-two-custom">
                            <i class="mdi mdi-history  widget-two-icon  no-top"></i>
                            <div class="m-t-20 m-b-20">
                                <div class="m-b-15 font-13 font-600"><span class="text-dark font-18 "
                                                                           id="history-number">${operationLog!}</span><span
                                        class="font-10 font-600">&nbsp;(条)操作日志</span></div>
                                <p class="text-uppercase m-b-5 font-10 font-600">容器日志&nbsp;<span
                                        class="text-success">${containerLog!}</span>&nbsp;</p>
                            </div>
                        </div>

                    </div>
                </div>
            </div><!-- end col -->
        </div>
        <!--- end row -->
    </div> <!-- container -->
</div>
</div> <!-- content -->

<!--C3 Chart-->
<script type="text/javascript" src="/static/plugins/d3/d3.min.js"></script>
<script type="text/javascript" src="/static/plugins/c3/c3.min.js"></script>

<script>
    !function ($) {
        "use strict";

        var ChartC3 = function () {
        };

        ChartC3.prototype.init = function () {

            //Pie Chart
            c3.generate({
                bindto: '#container-pie-chart',
                data: {
                    columns: [
                        ['在线', 13],
                        ['离线', 1],
                        ['正在安装', 0],
                        ['安装失败', 0],
                        ['未知', 0],
                    ],
                    type: 'pie'
                },
                color: {
                    pattern: ["#64c5b1", "#ec4758", "#32c861", "#e68900", "#f0f0f0"]
                },
                pie: {
                    label: {
                        show: false
                    }
                },
                legend: {
                    hide: true
                    //or hide: 'data1'
                    //or hide: ['data1', 'data2']
                }
            });


            //Pie Chart
            c3.generate({
                bindto: '#server-pie-chart',
                data: {
                    columns: [
                        ['在线', ${containerNumber.runing!}],
                        ['离线', ${containerNumber.shutdown!}],
                        ['正在安装', 0],
                        ['安装失败', 1],
                        ['未知', 0],
                    ],
                    type: 'pie'
                },
                color: {
                    pattern: ["#64c5b1", "#ec4758", "#32c861", "#e68900", "#f0f0f0"]
                },
                legend: {
                    hide: true
                    //or hide: 'data1'
                    //or hide: ['data1', 'data2']
                },
                pie: {
                    label: {
                        show: false
                    }
                }
            });

        },
                $.ChartC3 = new ChartC3, $.ChartC3.Constructor = ChartC3

    }(window.jQuery),
//initializing
            function ($) {
                "use strict";
                $.ChartC3.init()
            }(window.jQuery);

    function make_host_detail() {
        height = 50;
        var html = '<div  style="border:3px solid #64c5b1;height: 130px;overflow: hidden;border-radius: 8px;margin-top:5px;">'+
                '                   <div class="col-sm-12" style="border-bottom:3px solid #64c5b1;height: 35px;padding: 5px;"><b><span class="pull-left font-15 text-dark">www-www-www 10.16.10.1</span></b><br></div>'+
                '                       <i class="mdi mdi-fish widget-two-icon-server  " title="容器数量"'+
                '                                       style="color:#64c5b1;border: 2px double #64c5b1;top:50px;"><br>11112</i>'+
                '                     <i class="mdi mdi-dice-4  widget-two-icon-server  " title="cpu使用率"'+
                '                                       style="color:#008000;margin-left: 70px;  border: 2px double #008000;top:50px;"><br>12%</i>'+
                '                     <i class="mdi mdi-memory  widget-two-icon-server  " title="内存使用率"'+
                '                                       style="color:#6b0392;margin-left: 130px;  border: 2px double #6b0392;top:50px;"><br>12%</i>'+
                '                     <i class="mdi mdi-database  widget-two-icon-server" title="磁盘使用率"'+
                '                                       style="color:#ffa91c; margin-left: 190px; border: 2px double #ffa91c !important;top:50px;"><br>12%</i>'+
                ' </div>';
        var data = ""
        data += html.replace(/50px/g, "50px");
        data += html.replace(/50px/g, "190px") ;
        data += html.replace(/50px/g, "330px");
        $("#host_detail_html").html(data)
    }
    make_host_detail();
    </script>