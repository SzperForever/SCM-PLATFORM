$(function () {
    content_control.run();
    search_control.listen_input();
});


var cache = {};

var content_control = {
    run: function () {
        this.load_category();
        this.load_table_data();
        this.load_type();
    },
    load_category: function () {
        $.ajax({
            dataType: 'json',
            url: '/getCategory.form',
            success: function (data) {
                cache['category'] = data;
                $.each(data, function (index, val) {
                    var icon = ['<i class="far fa-compass"></i>', '<i class="fas fa-shopping-cart "></i>', '<i class="fas fa-box"></i>', '<i class="fas fa-wrench"></i>'];
                    var content =
                        '<li class="category-li">' +
                        '   <span>' +
                        '       <span class="icon">' + icon[index] + '</span>' +
                        '       <span class="category-id" style="display: none">' + val['id'] + '</span>' +
                        val['name'] +
                        '   </span>' +
                        '   <i class="right-caret fas fa-caret-right "></i>' +
                        '</li>';
                    $('#by-category>ul').append(content);
                });
                content_control.draw_color();
            }
        });
    },
    load_type: function () {
        $.ajax({
            dataType: 'json',
            url: '/getTypes.form',
            success: function (data) {
                cache['types'] = data;
                $.each(data, function (index, val) {
                    var content =
                        '<li class="tag-li">' +
                        '   <span>' +
                        '       <span class="icon"><i class="fas fa-tags"></i></span>' +
                        '       <span class="tag-id" style="display: none">' + val['id'] + '</span>'
                        + val['typeName'] +
                        '   </span>' +
                        '   <i class="right-caret fas fa-caret-right" style="z-index: 31!important;"></i>' +
                        '</li>';
                    //加入z-index使得tag菜单可以遮挡category菜单
                    $('#tag-ul').append(content);
                });
                content_control.draw_color();
                $('#blank-cover').css({
                    'height':$('#by-tag').height(),
                    'width' : $('#by-tag').width()+30,
                    'background' : '#f6f6f6'
                })
            }

        });
    },
    load_table_data: function () {
        $.ajax({
            dataType: 'json',
            url: '/getProdures.form',
            success: function (data) {
                cache['table_data'] = data;
                $.each(data, function (index, val) {
                    var content =
                        '<tr>' +
                        '<td><a href="' + val['filePath'] + '" style="text-decoration: none;">' + val['title'] + '</a></td>' +
                        '<td>' + val['description'] + '</td>' +
                        '<td>' + val['category'] + '</td>' +
                        '<td>' + val['type'] + '</td>' +
                        '<td>' + val['number'] + '</td>' +
                        '<td>' + val['filePath'] + '</td>' +
                        '<td>Star</td>' +
                        '</tr>';
                    $('#result-table').append(content);
                })
            }
        })
    },
    draw_color: function () {
        //(29,168,252) (40,175,83) (252,189,30) (228,73,31) (164,30,114) (29,168,252) ()
        var color = ['rgba(0,134,215)', 'rgba(17,142,54)', 'rgba(215,155,0)', 'rgba(192,44,0)', 'rgba(131,0,83)', 'rgba(0,134,215)'];
        var color_change = ['rgba(29,168,252)','rgba(40,175,83)','rgba(252,189,30)','rgba(228,73,31) ','rgba(164,30,114)','rgba(29,168,252)'];
        $('.icon').each(function (index) {
            var icon = $(this);
            icon.css('background', color[index % color.length]);
            icon.parent().parent().hover(function () {
                icon.css('background', color_change[index % color.length]);
            },function () {
                icon.css('background', color[index % color.length]);
            })
        });
    },
    collapse_category : function () {
        if(cache['category_status'] == undefined || cache['category_status'] == "down"){
            //up content
            cache['category_status'] = 'up';
            $('#by-tag').addClass('up');
            if(cache['tag_status'] == "up"){
                $('#blank-cover').addClass('upTwo');
            }
            $('#by-category>span>i').css('transform','rotate(90deg)');
        }
        else{
            $('#by-tag').removeClass('up');
            $('#blank-cover').removeClass('upTwo');
            cache['category_status'] = 'down';
            $('#by-category>span>i').css('transform','rotate(-90deg)');
        }
    },
    collapse_tag : function () {
        if(cache['tag_status'] == undefined || cache['tag_status'] == "down"){
            //up content
            if(cache['category_status'] == "up"){
                $('#blank-cover').addClass('upTwo');
            }
            else{
                $('#blank-cover').addClass('upOne');
            }
            cache['tag_status'] = 'up';
            $('#by-tag>span>i').css('transform','rotate(90deg)');
        }
        else{
            $('#blank-cover').removeClass('upOne');
            $('#blank-cover').removeClass('upTwo');
            cache['tag_status'] = 'down';
            $('#by-tag>span>i').css('transform','rotate(-90deg)');
        }
    }
};

var search_control = {
    listen_input : function () {
        $('#search-box>input').on('input propertychange', function () {
            var content = $(this).val().toLowerCase();
            if(content == ""){
                $('#search-box-result').fadeOut("200");
            }
            else {
                $('#search-box-result').empty();
                var found = false;
                $.each(cache['table_data'], function (index, val) {
                    if (val['title'].toLowerCase().indexOf(content) != -1) {
                        found = true;
                        $('#search-box-result').css('display', 'block');
                        $('#search-box-result').append('<div class="search-box-item"><a href="' + val['filePath'] + '">' + val['title'] + '</a></div>')
                    }
                })
                if(!found){
                    $('#search-box-result').css('display', 'block');
                    $('#search-box-result').append('<div class="search-box-item" style="    padding: 12px 0 12px 8px;\n' +
                        '    border-left: solid 1px #ccc;\n' +
                        '    border-right: solid 1px #ccc;\n' +
                        '    margin: 0;    color: #888;\n' +
                        '    font-style: italic;">no document found</div>')
                }
            }
        })
    }
};