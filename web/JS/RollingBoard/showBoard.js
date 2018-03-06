$(document).ready(function () {
    updateData();
    setInterval('updateData()', 1000 * 60 * 5);
});

$(window).resize(function () {
    var height = innerHeight / ($('tr').length + 5);
    $('tr>td>img').each(function () {
        $(this).css('height', height);
    });
    $('tr:first').css('height', (height - 5) * 2);
});

function updateData() {
    $.ajax({
        dataType: 'json',
        url: '/getFullBayLine.form',
        success: function (data) {
            $('table#displayBoard').find('tr').remove();
            //Update:使用Table实现
            $('table#displayBoard').append(
                '<tr id="Nav">' +
                '   <th id="LineRunningStatus">Line Running Status</th>' +
                '   <th id="Bay">Bay#</th>' +
                '   <th id="WorkCell">WorkCell</th>' +
                '   <th id="ProcessingModel">Processing Model</th>' +
                '   <th id="NextModel">Next Model</th>' +
                '   <th id="RunningStatus">Running Status</th>' +
                '   <th id="LotsReady">Lots Ready</th>' +
                '   <th id="TimeToUse">Lots Ready Time</th>' +
                '</tr>'
            );
            $.each(data, function (index, lineData) {
                writeData(lineData);
            });
            changeHeight(data);
        }
    });
}

//检查数据是否为空
function notEmpty(arr) {
    for (let i = 0; i < arr.length; ++i) {
        if (arr[i] != undefined) {
            return true;
        }
    }
    return false;
}

//找到最后一个非空值得索引
function findLastIndex(arr) {
    for (let i = arr.length - 1; i >= 0; --i) {
        if (arr[i] != undefined)
            return i;
    }
}

//获取数组真实长度
function len(arr) {
    let count = 0;
    for (let i = arr.length - 1; i >= 0; --i) {
        if (arr[i] != undefined)
            count += 1;
    }
    return count;
}
//获取URL里的参数
function GetRequest() {

    var url = location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = (strs[i].split("=")[1]);
        }
    }
    return theRequest;
}

//向表格中添加数据
function writeData(lineData) {
    $('table#displayBoard').append('<tr class="container-li"></tr>');
    //产线状态
    let bayStatus;
    if (lineData['building_'].length == 0) {
        bayStatus = '<td class="free"><img src="/IMG/RollingBoard/free.png" height="14%"></td>'
    }
    else {
        bayStatus = '<td class="work"><img src="/IMG/RollingBoard/working.gif" height="14%"></td>'
    }
    //产线号
    let bayNum = '<td class="bayNum">' + lineData['bayNum_'] + '</td>';
    //处于building状态的信息
    let buildingContent;
    if (lineData['building_'].length > 0) {
        buildingContent = '<td class="workCell">' + lineData['building_'][0]['workcell'] + '</td><td class="modelName">' + lineData['building_'][0]['modelName'] + '</td>';
        // buildingContent = '<span class="building"><span class="workCell"><span>' + lineData['building_'][0]['workcell'] + '</span></span><span class="modelName"><span>' + lineData['building_'][0]['modelName'] + '</span></span></span>';
    }
    else {
        buildingContent = '<td class="noPlan" style="text-align: right">No&nbsp</td><td class="noPlan" style="text-align: left">Plan</td>';
        // buildingContent = '<span class="building" style="justify-content: center; align-items:center"><span>No Plan</span></span>';
    }
    //处于loadingComplete状态的信息

    let loadingCompleteContent;
    if (lineData['loadingComplete_'].length > 0) {
        loadingCompleteContent = '<td class="modelName">' + lineData['loadingComplete_'][0]['modelName'] + '</td>';
    }
    else {
        loadingCompleteContent = '<td class="noPlan">No Plan</td>';
    }

    let status;
    let currentTime = new Date().getTime();
    if (lineData['building_'].length != 0 && currentTime < lineData['building_'][0]["planBuildTime"]) {
        status = '<td class="pre">Pre</td>'
    }
    else if (lineData['building_'].length != 0 && currentTime > lineData['building_'][0]["planCompleteTime"]) {
        status = '<td class="delay">Delay</td>'
    }
    else if (lineData['building_'].length == 0 && lineData['loadingComplete_'].length == 0) {
        status = '<td class="noPlan">No Plan</td>'
    }
    else {
        status = '<td class="onTime">On time</td>'
    }


    let timeToUse;
    currentTime = new Date().getTime();

    let Request = GetRequest();

    //当处于Building状态的OrderID 与 LoadingCompleted状态最后一个的OrderID一样时,去除最后一个
    while (lineData['building_'].length != 0 && lineData['loadingComplete_'].length != 0 && notEmpty(lineData['loadingComplete_'])) {
        let match = false;
        let index = lineData['loadingComplete_'].length - 1;
        while (lineData['loadingComplete_'][index] == undefined) {
            index--;
        }
        let orderID = lineData['loadingComplete_'][index]['orderID'];
        for (let i = 0; i < lineData['building_'].length; ++i) {
            if (orderID == lineData['building_'][i]['orderID']) {
                match = true;
                delete lineData['loadingComplete_'][index];
                break;
            }
        }
        if (match == false) {
            break;
        }
    }

    //去除OrderID相同的一个Product
    for(let i = 0; i < lineData['loadingComplete_'].length; ++i){
        if(lineData['loadingComplete_'][i] == undefined) continue;
        for(let j = i + 1; j < lineData['loadingComplete_'].length; ++j){
            if(lineData['loadingComplete_'][i] != undefined && lineData['loadingComplete_'][j] != undefined){
                if(lineData['loadingComplete_'][i]['orderID'] == lineData['loadingComplete_'][j]['orderID']){
                    delete lineData['loadingComplete_'][i];
                    break;
                }
            }
        }
    }


    let count = '<td class="count">' + len(lineData['loadingComplete_']) + '</td>';

    if (lineData['loadingComplete_'].length != 0 && notEmpty(lineData['loadingComplete_']) && currentTime < lineData['loadingComplete_'][findLastIndex(lineData['loadingComplete_'])]['planBuildTime']) {
        let result = Math.abs((lineData['loadingComplete_'][findLastIndex(lineData['loadingComplete_'])]['planBuildTime'] - currentTime) / 1000);
        let hour = Math.floor(result / 3600);
        let min = Math.floor(result % 3600 / 60);
        if (('' + min).length == 1) {
            timeToUse = '<td class="">' + hour + '.0' + min + '</td>'
        }
        else {
            timeToUse = '<td class="timeToUse">' + hour + '.' + min + '</td>'
        }
    }
    //如果 Delay
    else if (lineData['loadingComplete_'].length != 0 && notEmpty(lineData['loadingComplete_'])) {
        let result = Math.abs((lineData['loadingComplete_'][findLastIndex(lineData['loadingComplete_'])]['planBuildTime'] - currentTime) / 1000);
        let hour = Math.floor(result / 3600);
        let min = Math.floor(result % 3600 / 60);
        if (('' + min).length == 1) {
            timeToUse = '<td class="delay">-' + hour + '.0' + min + '</td>'
        }
        else {
            timeToUse = '<td class="delay">-' + hour + '.' + min + '</td>'
        }
        if(Request['mode']!=undefined)
            status = '<td class="delay">Delay</td>'
    }
    else {
        timeToUse = '<td class="timeToUse">0</td>'
    }

    if (Request['mode'] != undefined) {
        $('.container-li:last').append(bayStatus, bayNum, buildingContent, loadingCompleteContent, status, count, timeToUse);
    }
    else {
        $('th#TimeToUse').remove();
        $('th#LineRunningStatus').css('width', '16.7vw');
        $('th#Bay').css('width', '13.7vw');
        $('th#WorkCell').css('width', '13.7vw');
        $('th#ProcessingModel').css('width', '13.7vw');
        $('th#NextModel').css('width', '13.7vw');
        $('th#RunningStatus').css('width', '13.7vw');
        $('th#LotsReady').css('width', '13.7vw');
        $('.container-li:last').append(bayStatus, bayNum, buildingContent, loadingCompleteContent, status, count);
    }


    $('tr').css('background', '#42b6ee');
    $('tr:first').css('background', '#ffffff')
}


function changeHeight(lineData) {
    var height = innerHeight / (lineData.length + 6);
    $('tr>td>img').each(function () {
        $(this).css('height', height);
    });
    $('tr:first').css('height', (height - 5) * 2);
}
