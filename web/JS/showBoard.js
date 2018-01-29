$(document).ready(function () {
    updateData();
    setInterval('updateData()', 1000 * 60 * 5);
});

$( window ).resize(function() {
    var height = innerHeight / ($('tr').length + 5);
    console.log(height);
    $('tr>td>img').each(function () {
        $(this).css('height', height);
    });
    $('tr:first').css('height', (height - 5)* 2);
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
                '   <th id="TimeToUse">Time To Use</th>' +
                '</tr>'
            );
            $.each(data, function (index, lineData) {
                writeData(lineData);
            });
            changeHeight(data);
        }
    });
}


//向表格中添加数据
function writeData(lineData) {
    $('table#displayBoard').append('<tr class="container-li"></tr>');
    //产线状态
    let bayStatus;
    if (lineData['building_'].length == 0) {
        bayStatus = '<td class="free"><img src="/IMG/tmpdir--17_8_31_8_44_29.png" height="14%"></td>'
    }
    else {
        bayStatus = '<td class="work"><img src="/IMG/tmpdir--17_8_31_8_45_09.gif" height="14%"></td>'
    }
    //产线号
    let bayNum = '<td class="bayNum">' + lineData['bayNum_'] + '</td>'
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
        status = '<td class="pre">Ahead Of Schedule</td>'
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

    let count = '<td class="count">' + lineData['loadingComplete_'].length + '</td>';

    let timeToUse;
    if(lineData['loadingComplete_'].length != 0 && currentTime < lineData['loadingComplete_'][0]['planCompleteTime']){
        let result =  (lineData['loadingComplete_'][0]['planCompleteTime'] - currentTime) / 1000;
        let hour = Math.floor(result / 3600);
        let min = Math.floor(result % 3600 / 60);
        timeToUse = '<td class="timeToUse">'+ hour + ' h ' + min + ' m ' +'</td>'
    }
    else if(lineData['loadingComplete_'].length != 0){
        let result =  (lineData['loadingComplete_'][0]['planCompleteTime'] - currentTime) / 1000;
        let hour = Math.floor(result / 3600);
        let min = Math.floor(result % 3600 / 60);
        timeToUse = '<td class="delay">Delay : '+ hour + ' h ' + min + ' m ' +'</td>'
    }
    else{
        timeToUse = '<td class="timeToUse">No Plan</td>'
    }

    $('.container-li:last').append(bayStatus, bayNum, buildingContent, loadingCompleteContent, status, count, timeToUse);

    $('tr').css('background', '#42b6ee');
    $('tr:first').css('background', '#ffffff')
}


function changeHeight(lineData) {
    var height = innerHeight / (lineData.length + 6);
    console.log('--',height);
    $('tr>td>img').each(function () {
        $(this).css('height', height);
    });
    $('tr:first').css('height', (height - 5)* 2);
}
