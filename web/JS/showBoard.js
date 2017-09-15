$(document).ready(function () {
    updateData();
    setInterval('updateData()',1000 * 60 * 5);
});


function updateData() {
    $.ajax({
        dataType: 'json',
        url: '/getFullBayLine.form',
        success: function (data) {
            $('ul#displayBoard').children('li').remove();
            $('ul#displayBoard').append('<li id="Nav">' +
                '                <span id="LineRunningStatus"><span>Line Running</span><span>Status</span></span>' +
                '                <span id="blank">&nbsp</span>' +
                '                <span id="Bay">Bay#</span>' +
                '                <span id="WorkCell">WorkCell</span>' +
                '                <span id="ProcessingModel">Processing Model</span>' +
                '                <span id="NextModel">Next Model</span>' +
                '                <span id="RunningStatus">Running Status</span>' +
                '                <span>Lots Ready</span>' +
                '            </li>');
            $.each(data, function (index, lineData) {
                writeData(lineData);
            });
            changeHeight(data);
        }
    });
}

function writeData(lineData) {
    $('ul#displayBoard').append('<li class="container-li"><span class="modelLine"></span></li>');

    let bayStatus;
    if(lineData['building_'].length == 0){
        bayStatus = '<span class="free"><img src="/IMG/tmpdir--17_8_31_8_44_29.png" height="100%"></span>'
    }
    else{
        bayStatus = '<span class="work"><img src="/IMG/tmpdir--17_8_31_8_45_09.gif" height="100%"></span>'
    }

    let bayNum = '<span class="bayNum"><span>' + lineData['bayNum_'] +'</span></span>';

    let buildingContent;
    if(lineData['building_'].length > 0){
        buildingContent = '<span class="building"><span class="workCell"><span>' + lineData['building_'][0]['workcell'] + '</span></span><span class="modelName"><span>' + lineData['building_'][0]['modelName'] + '</span></span></span>';
    }
    else{
        buildingContent = '<span class="building" style="justify-content: center; align-items:center"><span>No Plan</span></span>';
    }

    let loadingCompleteContent;
    if(lineData['loadingComplete_'].length > 0){
        loadingCompleteContent = '<span class="loadingComplete">' + '<span class="modelName" style="justify-content: center; flex: 0 0 100%;"><span>' + lineData['loadingComplete_'][0]['modelName'] + '</span></span></span>';
    }
    else{
        loadingCompleteContent = '<span class="loadingComplete" style="justify-content: center ; align-items:center"><span>No Plan</span></span>';
    }

    let status;
    let currentTime = new Date().getTime();
    if (lineData['building_'].length != 0 && currentTime < lineData['building_'][0]["planBuildTime"]) {
        status = '<span class="pre"><span>Ahead of Schedule</span></span>'
    }
    else if (lineData['building_'].length != 0 &&  currentTime > lineData['building_'][0]["planCompleteTime"]) {
        status = '<span class="delay"><span>Delay</span></span>'
    }
    else if(lineData['building_'].length == 0 && lineData['loadingComplete_'].length == 0){
        status = '<span class="noPlan"><span>No Plan</span></span>'
    }
    else{
        status = '<span class="onTime"><span>On time</span></span>'
    }

    let count = '<span class="count"><span>' + lineData['loadingComplete_'].length +'</span></span>';

    $('.modelLine:last').append(bayStatus,bayNum,buildingContent,loadingCompleteContent,status,count);

    $('li').css('background','#42b6ee');
    $('li:first').css('background','#ffffff')
}

function changeHeight(lineData) {
    var height = innerHeight / (lineData.length + 5);
    $('li').each(function () {
        $(this).css('height',height);
    });
    $('li:first').css('height',height * 2);
}
