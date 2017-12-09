<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
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

    .font12 {
        font-size: 12px;
    }

    td {
        padding: 6px !important;
        font-weight: 100;
    }

    tr th {
        vertical-align: middle !important;
        padding: 3px !important;
        font-size: 12px;
        font-weight: 100;
    }

    .margin {
        margin-top: -30px;
        margin-left: 10px;
        margin-right: 10px;
    }

    .box-left {
        text-align: left;
        margin-left: 100px;
        border: none;
    }

    .select {
        border-radius: 3px;
        height: 26px;
        border: 1px solid #ccc;
    }
</style>
<div class="row card-box margin" style="margin-top: 78px !important;">
    <div class="col-sm-12">
        <div class="col-lg-12 col-md-d">
            <div class="card-box widget-box-two widget-two-custom box-left">
                <i class="mdi mdi-cube-send widget-two-icon top10" style="left: 0 !important;"></i>
                <div class="wigdet-two-content" style="margin-left: -40px;">
                    <div class="col-sm-12">
                        <div class="col-sm-1" style="margin-left: -20px;">
                            <label> 容器:</label>
                        </div>
                        <div class="col-sm-3">
                            <select class="from-control select" onchange="to_container_d($(this).val(),$(this).find('option:selected').text())">
                                <option class="text-info" value="${name!}">&nbsp;&nbsp;${name!}&nbsp;&nbsp;</option>
                            <#list containers as app>
                            ${(app.id??)?then('<option value="${app.id}">&nbsp;&nbsp;${app.name!}&nbsp;&nbsp;</option>','')}
                            </#list>
                            </select>
                        </div>
                        <div class="col-sm-5">
                                <span style="margin-left: 10px;">所属主机; <a class="text-custom">${host!}</a></span>
                        </div>
                        <div class="col-sm-3">
                            <button type="button" class="btn btn-custom waves-effect btn-xs">命令终端</button>
                            <select class="from-control select" onchange="">
                                <option class="text-info" value="">更多操作</option>
                                <option value="rollback-data">删除</option>
                                <option value="stop-data">停止</option>
                            </select>
                        </div>
                        </span>
                        <br>
                    </div>
                </div>
            </div>
            <div style="margin-left: 57px;">
                    <div class="col-lg-12" style="margin: 3px;    margin-top: -5px;">
                        <div class="col-sm-12">
                            <div> ${(data.image??)?then('<span style="color:#000;margin-right:5px;">镜像:</span>${data.image!}','<span style="color:#000;margin-right:5px;">镜像:</span>无')}</div>
                        </div>
                    </div>
                        <div class="col-lg-12" style="margin: 3px;">
                            <div class="col-sm-3" style="width: 220px;">
                                <div class="from-control"><span style="color:#000;margin-right:5px;">创建时间:</span>${createdAt!}</div>
                            </div>
                            <div class="col-sm-3" style="width: 220px;">
                            <div  class="from-control"> ${(data.limitMemory??)?then('<span style="color:#000;margin-right:5px;">内存限制:</span>${data.limitMemory!}','<span style="color:#000;margin-right:5px;">内存限制:</span>:无')}
                                </div>
                            </div>
                            <div class="col-sm-4">
                            <div class="from-control"> ${(data.limitCpu??)?then('<span style="color:#000;margin-right:5px;">CPU设置:</span>${data.limitCpu!}','<span style="color:#000;margin-right:5px;">CPU设置:</span>无')}</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<br>
<div class="row card-box margin" id="app_list">
    <label class="col-sm-2 form-control-label" style="margin-left: 8px;margin-top: 4px;">
        <i class="mdi mdi-check-circle-outline" style="font-size: 18px;"></i>&nbsp;&nbsp;基本信息:&nbsp;</label>
    <div>
        <br>
        <table style="width: 100%;margin-left: 40px;" class="font12">
            <tr>
                <th style="width: 8%">启动时间</th>
                <th style="width: 35%"><span id="data_docker_container_number">${data.startedAt!}</span>
                </th>
            </tr>

            <tr>
                <th style="width: 8%">命令</th>
                <th style="width: 35%"> ${(data.cmd??)?then('${data.cmd}','无')}
                </th>
                <th style="width: 8%">停止时间</th>
                <th style="width: 35%"> ${(data.privileged??)?then('${data.privileged}','无')}
            </tr>
            <tr>
                <th>&nbsp;</th>
            </tr>
        </table>
    </div>
</div>
<br>
<div class="row card-box margin">
    <div class="col-sm-6">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>动态图</h5>
                <div class="ibox-tools">
                    <a class="collapse-link">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                    <a class="dropdown-toggle" data-toggle="dropdown" href="graph_flot.html#">
                        <i class="fa fa-wrench"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="graph_flot.html#">选项1</a>
                        </li>
                        <li><a href="graph_flot.html#">选项2</a>
                        </li>
                    </ul>
                    <a class="close-link">
                        <i class="fa fa-times"></i>
                    </a>
                </div>
            </div>
            <div class="ibox-content">

                <div class="flot-chart">
                    <div class="flot-chart-content" id="flot-line-chart-moving"></div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<script src="/static/plugins/flot/jquery.flot.js"></script>
<script>
    function to_container_d(id,name) {
        var url = "/container/detail/${host!}/"+id
        make_page(url, "/容器管理/"+name.trim()+"/详情","?serviceName=${serviceName!}");
    }
        var container = $("#flot-line-chart-moving");

        // Determine how many data points to keep based on the placeholder's initial size;
        // this gives us a nice high-res plot while avoiding more than one point per pixel.

        var maximum = container.outerWidth() / 2 || 300;

        //

        var data = [];

        function getRandomData() {

            if (data.length) {
                data = data.slice(1);
            }

            while (data.length < maximum) {
                var previous = data.length ? data[data.length - 1] : 50;
                var y = previous + Math.random() * 10 - 5;
                data.push(y < 0 ? 0 : y > 100 ? 100 : y);
            }

            // zip the generated y values with the x values

            var res = [];
            for (var i = 0; i < data.length; ++i) {
                res.push([i, data[i]])
            }

            return res;
        }

        //

        series = [{
            data: getRandomData(),
            lines: {
                fill: true
            }
        }];

        //

        var plot = $.plot(container, series, {
            grid: {

                color: "#999999",
                tickColor: "#D4D4D4",
                borderWidth:0,
                minBorderMargin: 20,
                labelMargin: 10,
                backgroundColor: {
                    colors: ["#ffffff", "#ffffff"]
                },
                margin: {
                    top: 8,
                    bottom: 20,
                    left: 20
                },
                markings: function(axes) {
                    var markings = [];
                    var xaxis = axes.xaxis;
                    for (var x = Math.floor(xaxis.min); x < xaxis.max; x += xaxis.tickSize * 2) {
                        markings.push({
                            xaxis: {
                                from: x,
                                to: x + xaxis.tickSize
                            },
                            color: "#fff"
                        });
                    }
                    return markings;
                }
            },
            colors: ["#1ab394"],
            xaxis: {
                tickFormatter: function() {
                    return "";
                }
            },
            yaxis: {
                min: 0,
                max: 110
            },
            legend: {
                show: true
            }
        });

        // Update the random dataset at 25FPS for a smoothly-animating chart

        setInterval(function updateRandom() {
            series[0].data = getRandomData();
            plot.setData(series);
            plot.draw();
        }, 40);

</script>
</body>
</html>