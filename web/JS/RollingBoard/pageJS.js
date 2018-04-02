// AJAX请求后台的数据，添加到网页顶部的计数
$(document).ready(function () {
    $.ajax({
        dataType: "json",
        url: "/getCountNum.form",
        success: function (data) {
            $("#buildingCount").text("Processing : " + data["Building"]);
            $("#loadingCompleteCount").text("LoadingComplete : " + data["LoadingComplete"]);
        }
    });
});

// AJAX请求所有的WorkCell数据，数据由后台Java按照Bay number分类 放在Arraylist中
$(document).ready(function () {
    $.ajax({
        dataType: "json",
        url: "/getBayLine.form",
        success: function (data) {
            // 按照每一个Bay 作为一行 写入到网页中
            $.each(data, function (index, lineData) {
                writeDataToLine(lineData);
            });
            // 在写入完所有数据后，计算顶部count的位置，使其对齐各自的Model 表格
            $("#blank_pre").css("flex", "0 0 " + $(".BayNum:first").width() + "px");
            $("#blank_last").css("flex", "0 0 " + ($(".Status:first").width() + $(".LineBoxBlank:first").width() - $("#buildingCount").width()).toString() + "px");

            addSearchMenu();
            listenSlide();

        }
    });
});



// 写入数据到每一条Line中
function writeDataToLine(lineData) {
    // 添加新的一行
    $("ul#container-ul").append("<li class='container-li'><div class='LineBox'></div></li>");
    // 添加行首的BayNum
    $("div.LineBox:last").append("<span class='BayNum'><span class='BayNumBackGround'>" + lineData["bayNum_"] + "</span></span>");
    // 根据后台计算出的status的值染色
    changeBayNumColor($("span.BayNumBackGround:last"), lineData["status_"]);
    // 添加当前状态的Model的数量
    $("div.LineBox:last").append("<span class='Status'><span class='ModelCount'>&nbsp;</span><span class='ModelTable'></span></span>");
    // 写入处于Building状态的数据
    writeModelDataToTable(lineData["building_"]);
    // 添加当前状态的Model的数量
    $("div.LineBox:last").append("<span class='LineBoxBlank'>&nbsp;</span><span class='Status'><span class='ModelCount'>Count : " + lineData["loadingComplete_"].length + "</span><span class='ModelTable'></span></span>");
    // 写入处于loadingComplete状态的数据
    writeModelDataToTable(lineData["loadingComplete_"]);
    $("div.LineBox:last").append("<span class='LineBoxBlankEnd'>&nbsp;</span>");
}

// 写入数据到具体的WorkCell表格
function writeModelDataToTable(data) {
    if (data.length == 0) { //如果长度为0，添加一个No Plan的表格
        $("span.ModelTable:last").append("<table class='NoPlan'><tbody><tr><td>No Plan</td></tr></tbody></table>");
    } else {
        $.each(data, function (index, value) {
            $("span.ModelTable" + ":last").append("<table class='Model'></table>");
            var orderId = combinationText("OrderID", value["orderID"]);
            var Workcell = combinationText("WorkCell", value["workcell"]);
            var ModelName = combinationText("ModelName", value["modelName"]);
            var PlanCompleteTime = combinationText("PlanCompleteTime", value["formatPlaneCompleteTime"]);
            var Sets = combinationText("Sets", value["sets"]);
            var RouteStep = combinationText("RouteStep", value["routeStep"]);
            $("table.Model:last").append(orderId, Workcell, ModelName, RouteStep, Sets, PlanCompleteTime);
            //根据当前时间 计算当前Model是否 delay or pre ，添加对应颜色
            changeBayNumColor($("table.Model:last"), value["progress"]);
            if (value["progress"] == "Building") {
                changeBayNumColor($("table.Model:last tr:first"), {
                    PlanCompleteTime: value["planCompleteTime"],
                    PlanBuildTime: value["planBuildTime"]
                });
            } else {
                changeBayNumColor($("table.Model:last tr:first"), {
                    PlanCompleteTime: value["planCompleteTime"],
                    PlanBuildTime: "NULL"
                });
            }
        });
    }
}

// 拼接填充字符
function combinationText(key, value) {
    return "<tr><td>" + key + ":" + "</td><td class=" + key + ">" + value + "</td></tr>";
}

// 添加各种颜色 包括Bay Num的三种状态颜色，每个WorkCell的边框颜色以及Delay 或者　Ｐｒｅ的颜色
function changeBayNumColor(tag, status) {
    if (status == "0") {
        tag.css("background", "#FFA8D2");
    } else if (status == "1") {
        tag.css("background", "green");
    } else if (status == "2") {
        tag.css("background", "yellow");
    } else if (status == "Building") {
        tag.css("border", "5px solid #96FF71");
    } else if (status == "LoadingCompleted") {
        tag.css("border", "5px solid #82FFF3");
    } else {
        var currentTime = new Date().getTime();
        if (currentTime > status["PlanCompleteTime"]) {
            tag.css("background", "#FFECE5");
        }
        if (status["PlanBuildTime"] != "NULL") {
            if (currentTime < status["PlanBuildTime"]) {
                tag.css("background", "#D2FFC0");
            }
        }
    }
}

// 添加边栏菜单
function addSearchMenu() {
    $("#Menu").append('<span id="searchMenu"><p>Screening</p><div id="A"><p id="SearchTitleA">By BayNum</p><table id="byBayNum"><tr><td><button class="myButton" id="checkAll">Check All</button></td></tr></table></div></span>');
    $("span.BayNum").each(function (index, value) {
        if (index % 3 == 0) {
            $("table#byBayNum").append("<tr></tr>");
        }
        $("table#byBayNum tr:last").append('<td><button class="myButton selected">' + $(this).text() + "</button></td>");
    });

    $("#searchMenu").append('<div id="B" style="display: none"><p id="SearchTitleB">By WorkCell</p><table id="byWorkCell"><tr><td><button class="myButton" id="checkAll">Check All</button></td></tr></table></div>');
    var workCellData = new Set();
    $("td.WorkCell").each(function () {
        workCellData.add($(this).text());
    });
    workCellData = Array.from(workCellData);
    workCellData.forEach(function (element, index, set) {
        if (index % 2 == 0) {
            $("table#byWorkCell").append("<tr></tr>");
        }
        $("table#byWorkCell tr:last").append('<td><button class="myButton">' + element + "</button></td>");
    });
    $("#Menu").append('<span id="option"><p>Option</p></span>');

    $("span#option").append('<table id="optionTable"><tr><td>Open Slide</td><td><input type="checkbox" class="checkbox" checked id="slide"></td></tr>' + '<tr><td>Only show one</td><td><input type="checkbox" class="checkbox" id="showOne"></div></td></tr>' + '<tr><td>Show Count</td><td><input type="checkbox" class="checkbox" id="showCount" checked></td></tr>' + '<tr><td>Open table slide</td><td><input type="checkbox" class="checkbox" id="tableSlide" ></td></tr>' + '<tr><td>Set refresh time : <input type="text" id="refreshTime" style="width: 20%"/> </td><td>  <button id="submitTime">submit</button></td></td>' + '<tr><td><button id="refresh" onclick="updateData()">Refresh</button></td></tr>' + "</table>");
    $(".checkbox").simpleSwitch({
        theme: "FlatRadius"
    });
    //对齐表格
    $('#optionTable').css('width',$('#byBayNum').width());

    //收起菜单栏
    var MenuWidth = $("#Menu").width();
    $("body").css("left", -MenuWidth);
    //监听按钮点击动作
    listenButton();
}

// 刷新时间
var refreshTime = 1e3 * 60 * 5;

// 句柄
var hand = setInterval("updateData()", refreshTime);

// 更新所有的表格数据
function updateData() {
    $.ajax({
        dataType: "json",
        url: "/getBayLine.form",
        success: function (data) {
            //按照每一个lineModel写入
            $("li.container-li").remove();
            $.each(data, function (index, lineData) {
                writeDataToLine(lineData);
            });
            reDraw();
            updateMenu();
            listenButton();
        }
    });
}

// 更新边栏菜单
function updateMenu() {
    var byBayNum = $("table#byBayNum");
    var byWorkCell = $("table#byWorkCell");
    byBayNum.children().remove();
    byWorkCell.children().remove();
    byBayNum.append('<tr><td><button class="myButton" id="checkAll">Check All</button></td></tr>');
    $("span.BayNum").each(function (index, value) {
        if (index % 3 == 0) {
            $("table#byBayNum").append("<tr></tr>");
        }
        $("table#byBayNum tr:last").append('<td><button class="myButton">' + $(this).text() + "</button></td>");
        if ($('#A').attr("style") != "display: none;" && $(this).parents("li:first").attr("style") != "display: none;") {
            $("button.myButton:last").addClass("selected");
        }
    });
    byWorkCell.append('<tr><td><button class="myButton" id="checkAll">Check All</button></td></tr>');
    var workCellData = new Set();
    $("td.WorkCell").each(function () {
        workCellData.add($(this).text());
    });
    workCellData = Array.from(workCellData);
    workCellData.forEach(function (element, index, set) {
        if (index % 2 == 0) {
            $("table#byWorkCell").append("<tr></tr>");
        }
        $("table#byWorkCell tr:last").append('<td><button class="myButton">' + element + "</button></td>");
        $("td.WorkCell").each(function () {
            if ($(this).text() == element) {
                if ($('#B').attr("style") != "display: none;" && $(this).parents("table.Model").attr("style") != "border: 5px solid rgb(150, 255, 113); display: none;" && $(this).parents("table.Model").attr("style") != "border: 5px solid rgb(130, 255, 243); display: none;") {
                    $("button.myButton:last").addClass("selected");
                }
            }
        });
    });
    $(".checkbox").each(function () {
        buttonFunction($(this), "true");
    });
}


// 鼠标移入
var mouseIn = false;
$(document).ready(function () {
    var timer = setInterval("moveUp()", 5e3);
    $("#rolling-container").mouseenter(function () {
        mouseIn = true;
    });
    $("#rolling-container").mouseleave(function () {
        mouseIn = false;
    });
});

// 开启/关闭 滑动
var slideOpen = "true";

// 滑动
function moveUp() {
    if (mouseIn) return;
    if (slideOpen == "false") return;
    var first = $("li.container-li:first");
    var height = first.height();
    $("#container-ul").animate({
        top: "-=" + height + "px"
    }, 1500, function () {
        var first = $("li.container-li:first");
        $("#container-ul").append(first.clone(true));
        first.remove();
        $("#container-ul").css("top", "0px");
    });
}

var MenuStatus = true;

$(document).ready(function () {
    $("#blank_pre").click(function () {
        var MenuWidth = $("#Menu").width();
        if (MenuStatus) {
            $("body").animate({
                left: "+=" + MenuWidth + "px"
            });
        } else {
            $("body").animate({
                left: "-=" + MenuWidth + "px"
            });
        }
        MenuStatus = !MenuStatus;
    });
});

function listenButton() {
    //点击筛选按钮的效果
    $(".myButton").click(function () {
        $(this).toggleClass("selected");
        if ($(this).attr("id") == "checkAll") {
            $(this).parents("table:first").find("button.myButton").addClass("selected");
            $(this).attr("id", "inverse");
            $(this).text("inverse");
        } else if ($(this).attr("id") == "inverse") {
            $(this).parents("table:first").find("button.myButton").removeClass("selected");
            $(this).attr("id", "checkAll");
            $(this).text("Check All");
        }
        reDraw();
    });
    //点击滑动开关
    $(".checkbox").click(function () {
        buttonFunction($(this), "false");
    });

    $('#submitTime').click(function () {
        var setTime = $('#refreshTime').val();
        if (setTime == '' || isNaN(setTime) || parseInt(setTime) <= 0) {
            alert('The time you set is not legal');
        } else {
            refreshTime = parseInt(setTime) * 1000;
            window.clearInterval(hand);
            hand = setInterval("updateData()", refreshTime);

            alert('The refresh time is set to ' + refreshTime / 1000 + 's');
        }
    })

    $('#SearchTitleA').click(function () {
        $('#A').css('display','none');
        $('#B').css('display','block');
        $('#A .myButton').removeClass('selected');
        $('#inverse').each(function () {
            $(this).text('Check All');
            $(this).attr("id", "checkAll");
        })
    })

    $('#SearchTitleB').click(function () {
        $('#A').css('display','block');
        $('#B').css('display','none');
        $('#B .myButton').removeClass('selected');
        $('#inverse').each(function () {
            $(this).text('Check All');
            $(this).attr("id", "checkAll");
        })

    })


}

var tableSlideTable = false;
function buttonFunction(val, result) {
    var id = val.attr("id");
    if (id == "slide") {
        setTimeout("assign('slide')", 100);
    } else if (id == "showOne") {
        if (val.attr("result") == result) {
            showOne();
        } else {
            $(".Model").css("display", "table");
        }
    } else if (id == "showCount") {
        if (val.attr("result") != result) {
            $(".ModelCount").css("display", "none");
        } else {
            $(".ModelCount").css("display", "block");
        }
    }else if(id == "tableSlide"){
        if (val.attr("result") != result) {
            tableSlideTable = false;
        } else {
            tableSlideTable = true;
        }
    }
}

function assign(value) {
    slideOpen = $("#" + value).attr("result");
}

function showOne() {
    $(".Model").css("display", "none");
    $(".Status").find(".Model:first").css("display", "table");
}

function reDraw() {
    $("li.container-li").css("display", "none");
    $("table.Model").css("display", "none");
    $(".selected").each(function () {
        restoreDisplay($(this).text());
    });
    changeCountNum();
}

function restoreDisplay(Val) {
    $("span.BayNum").each(function () {
        if ($(this).text() == Val) {
            $(this).parents("li:first").css("display", "list-item");
            $(this).parents("div:first").find("table.Model").css("display", "table");
        }
    });
    $("td.WorkCell").each(function () {
        if ($(this).text() == Val) {
            $(this).parents("li:first").css("display", "list-item");
            $(this).parents("table.Model:first").css("display", "table");
        }
    });
}

function changeCountNum() {
    var building = 0, loadingComplete = 0;
    building = $("table.Model[style='border: 5px solid rgb(150, 255, 113); display: table;']").length;
    loadingComplete = $("table.Model[style='border: 5px solid rgb(130, 255, 243); display: table;']").length;
    $("span#buildingCount").text("Processing: " + building);
    $("span#loadingCompleteCount").text("LoadingComplete: " + loadingComplete);
}

//滑动显示隐藏的部分
function listenSlide() {

        $("span.ModelTable").each(function (index, current) {
            $(this).mouseenter(function () {
                if(tableSlideTable == true) {
                    var viewWidth = $(this).innerWidth();
                    var sumWidth = 0;
                    $(this).children("table").each(function () {
                        sumWidth += $(this).innerWidth() + 10;
                    });

                    var slideLength = sumWidth - viewWidth;
                    if (slideLength > 0) {
                        if ($(this).attr('style') == undefined) {
                            $(this).animate({left: '-=' + slideLength + 'px'}, slideLength * 2);
                        }
                        else {
                            var currentPos = parseInt($(this).attr('style').replace(/[^\d|\.]/ig, ""));
                            if (currentPos >= (slideLength - 20)) {
                                $(this).animate({left: '0px'}, currentPos * 2);
                            }
                            else {
                                $(this).animate({left: '-=' + (slideLength - currentPos ) + 'px'}, (slideLength - currentPos) * 2);
                            }
                        }
                    }
                }
            });
            $(this).mouseleave(function () {
                if(tableSlideTable == true) {
                    $(this).stop();
                }
            });
        });
}

