$(function () {
    content_control.run();
    search_control.listen_input();
    search_control.listen_search();
});


var cache = {};

var content_control = {
    run: function () {
        this.load_category();
        this.load_table_data();
        this.load_type();
        cache['filter_height'] = $('#filter-select').outerHeight();
        $('#filter-select').css({
            'height': '0',
            'overflow': 'hidden',
            'padding': '0'
        });
        this.filter_select();
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
                category_control.listen_click();
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
                    'height': $('#by-tag').height(),
                    'width': $('#by-tag').width() + 30,
                    'background': '#f6f6f6'
                });
                category_control.listen_click();
            }

        });
    },
    load_table_data: function () {
        var count = 0;
        $.ajax({
            dataType: 'json',
            url: '/getProdures.form',
            success: function (data) {
                cache['table_data'] = data;
                $.each(data, function (index, val) {
                    var content =
                        '<tr class="Procedure Document">' +
                        '<td><a href="' + val['filePath'] + '" style="text-decoration: none;">' + val['title'] + '</a></td>' +
                        '<td>' + val['description'] + '</td>' +
                        '<td>' + val['category'] + '</td>' +
                        '<td>' + val['type'] + '</td>' +
                        '<td>' + val['number'] + '</td>' +
                        '<td>' + val['filePath'] + '</td>' +
                        '<td><i class="far fa-star"></i></td>' +
                        '<td class="category-id" style="display: none">'+val['category_id']+'</td>'+
                        '<td class="type-id" style="display: none">'+val['type_id']+'</td>'+
                        '</tr>';
                    $('#result-table').append(content);
                })
            }
        });
        $.ajax({
            dataType:"json",
            url:'/getProLibs.form',
            success:function (data) {
                cache['table_data_fromLib'] = data;
                $.each(data, function (index, val) {
                var content =
                    '<tr class="Project Library">' +
                    '<td><a href="' + val['filePath'] + '" style="text-decoration: none;">' + val['fileName'] + '</a></td>' +
                    '<td>File from ProjectLib</td>' +
                    '<td>' + val['typeName'] + '</td>' +
                    '<td>ProjectLib</td>' +
                    '<td>' + val['id'] + '</td>' +
                    '<td>' + val['filePath'] + '</td>' +
                    '<td><i class="far fa-star"></i></td>' +
                    '<td class="category-id" style="display: none">'+val['category_id']+'</td>'+
                    '</tr>';
                    $('#result-table').append(content);
                });
                $('#search-result').text("Found : " + (cache['table_data'].length + cache['table_data_fromLib'].length));
            }
        })
    },
    draw_color: function () {
        //(29,168,252) (40,175,83) (252,189,30) (228,73,31) (164,30,114) (29,168,252) ()
        var color = ['rgba(0,134,215,1)', 'rgba(17,142,54,1)', 'rgba(215,155,0,1)', 'rgba(192,44,0,1)', 'rgba(131,0,83,1)', 'rgba(0,134,215,1)'];
        var color_change = ['rgba(29,168,252,1)', 'rgba(40,175,83,1)', 'rgba(252,189,30,1)', 'rgba(228,73,31,1) ', 'rgba(164,30,114,1)', 'rgba(29,168,252,1)'];
        $('.icon').each(function (index) {
            var icon = $(this);
            icon.css('background', color[index % color.length]);
            icon.parent().parent().hover(function () {
                icon.css('background', color_change[index % color.length]);
                if($(this).css('background').indexOf('rgb(255, 255, 255)') == -1) {
                    $(this).css('background', '#ebebeb');
                }
            }, function () {
                icon.css('background', color[index % color.length]);
                if($(this).css('background').indexOf('rgb(255, 255, 255)') == -1) {
                    $(this).css('background', '#f6f6f6');
                }
            })
        });
    },
    collapse_category: function () {
        if (cache['category_status'] == undefined || cache['category_status'] == "down") {
            //up content
            cache['category_status'] = 'up';
            $('#by-tag').addClass('up');
            if (cache['tag_status'] == "up") {
                $('#blank-cover').addClass('upTwo');
            }
            $('#by-category>span>i').css('transform', 'rotate(90deg)');
        }
        else {
            $('#by-tag').removeClass('up');
            $('#blank-cover').removeClass('upTwo');
            cache['category_status'] = 'down';
            $('#by-category>span>i').css('transform', 'rotate(-90deg)');
        }
    },
    collapse_tag: function () {
        if (cache['tag_status'] == undefined || cache['tag_status'] == "down") {
            //up content
            if (cache['category_status'] == "up") {
                $('#blank-cover').addClass('upTwo');
            }
            else {
                $('#blank-cover').addClass('upOne');
            }
            cache['tag_status'] = 'up';
            $('#by-tag>span>i').css('transform', 'rotate(90deg)');
        }
        else {
            $('#blank-cover').removeClass('upOne');
            $('#blank-cover').removeClass('upTwo');
            cache['tag_status'] = 'down';
            $('#by-tag>span>i').css('transform', 'rotate(-90deg)');
        }
    },
    collapse_filter_select: function () {
        if (cache['filter_status'] == undefined || cache['filter_status'] == "hidden") {
            $('#filter-select').css({
                'height': cache['filter_height'],
                'padding': '10px 10px 20px 10px'
            });
            cache['filter_status'] = "show";
            $('#document-filter>button').text("Hide Filters");
        }
        else {
            $('#filter-select').css({
                'height': '0',
                'overflow': 'hidden',
                'padding': '0'
            });
            cache['filter_status'] = 'hidden';
            $('#document-filter>button').text("Show Filters");
        }
    },
    filter_select: function () {
        $('.filter-item>ul>li').each(function () {
            $(this).click(function () {
                var h3 = $(this).parent().parent().find('h3').text(), name = $(this).text();
                if($(this).css('color') == "rgb(0, 145, 220)"){
                    content_control.remove_filter(h3,name);
                }
                else {
                    $(this).css({
                        'color': '#0091dc'
                    });
                    $(this).append(
                        '<i class="fas fa-times" onclick=\"content_control.remove_filter(\'' + h3 + '\',\'' + name + '\')\"></i>'
                    );
                    content_control.add_filter(h3,name);
                }
                if(name == "Project Library" || name == "Procedure Document")
                    content_control.excute_filter();
            });
        })
    },
    add_filter: function (h3, name) {
        h3 = h3.trim();
        name = name.trim();
        $('#document-filte>span>p').css('display','inline');
        $('#document-filter').append(
            '<span class="active-filter">' +
            '   <span class="filter-name">' + name + '<i class="fas fa-times" onclick=\"content_control.remove_filter(\'' + h3 + '\',\'' + name + '\')\"></i></span>' +
            '   <span class="h3" style="display: none">'+ h3 +'</span>'+
            '</span>'
        )
    },
    remove_filter: function (h3, name) {
        h3 = h3.trim();
        name = name.trim();
        $('.active-filter').each(function () {
            if ($(this).find('.h3').text() == h3 && $(this).find('.filter-name').text() == name){
                $(this).remove();
            }
        });
        $('.filter-item').each(function () {
            if($(this).find('h3').text() == h3){
                $(this).find('li').each(function () {
                    if($(this).text() == name){
                        $(this).css('color','black');
                        $(this).find('i').remove();
                    }
                })
            }
        });
        if($('.active-filter').length == '0'){
            $('#document-filter>span>p').css('display','none');
        }
        content_control.excute_filter();
    },
    excute_filter : function () {
        // $('#result-table>tbody>tr').each(function () {
        //     if($(this).find('th').length == '0'){
        //         $(this).fadeOut("200");
        //     }
        // });
        $('.active-filter').each(function () {
            var filter_name = $(this).find('span.filter-name').text();
            console.log(filter_name);
            $('#result-table>tbody>tr').each(function () {
                if($(this).find('th').length == '0'){
                    //不符合条件隐藏
                    if($(this).attr("class").indexOf(filter_name) == -1){
                        $(this).fadeOut("200");
                    }
                }
            });
        });
        //如果筛选器为空，则显示所有
        if($('.active-filter').length == '0'){
            $('#result-table>tbody>tr').each(function () {
                $(this).fadeIn("200");
            })
        }
        setTimeout(content_control.setCount(),210);
    },
    setCount : function () {
        var count = 0;
        $('#result-table>tbody>tr').each(function () {
            if($(this).css('display') != 'none'){
                count++;
            }
        });
        $('#search-result').text("Found : " + (count-1));
    },
    show_all:function () {
        $('#result-table>tbody>tr').each(function () {
            $(this).fadeIn("200");
        })
        content_control.setCount();
    }
};

var search_control = {
    listen_input: function () {
        $('#search-box>input').on('input propertychange', function () {
            var content = $(this).val().toLowerCase();
            if (content == "") {
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
                });
                if (!found) {
                    $('#search-box-result').css('display', 'block');
                    $('#search-box-result').append('<div class="search-box-item" style="    padding: 12px 0 12px 8px;\n' +
                        '    border-left: solid 1px #ccc;\n' +
                        '    border-right: solid 1px #ccc;\n' +
                        '    margin: 0;    color: #888;\n' +
                        '    font-style: italic;">no document found</div>')
                }
            }
        })
    },
    listen_search : function () {
        $('#document-filter>input').on('input propertychange', function () {
            var content = $(this).val().toLowerCase();
            var count = 0;
            $('#result-table>tbody>tr').each(function () {
                if($(this).find('th').length == '0') {
                    $(this).stop();
                    $(this).fadeOut("300");
                    if ($(this).find('td:first').text().toLowerCase().indexOf(content) != -1) {
                        $(this).fadeIn("300");
                        ++count;
                    }
                }
            });
            setTimeout(content_control.setCount(),310);

        })
    }
};

var category_control = {
    //点击侧面菜单
    listen_click : function () {
        $('#category-ul>li').each(function () {
            $(this).click(function () {
                if($(this).css('background').indexOf("rgb(255, 255, 255)") != -1){
                    content_control.show_all();
                    //保持筛选器的筛选
                    content_control.excute_filter();
                    $(this).css('background','transparent');
                    return ;
                }
                $('.tag-li').each(function () {
                    $(this).css('background','transparent')
                });
                $('.category-li').each(function () {
                    $(this).css('background','transparent')
                });
                var id = $(this).find('.category-id').text();
                console.log(id);
                var count = 0;
                $('#result-table>tbody>tr').each(function () {
                    if($(this).find('th').length == '0') {
                        $(this).stop();
                        if ($(this).find('td.category-id').text().toLowerCase().indexOf(id) == -1) {
                            $(this).fadeOut("300");
                            ++count;
                        }
                    }
                });
                $('#search-result').text("Found : " + count);
                $(this).css('background','white');
            })
        });

        $('#tag-ul>li').each(function () {
            $(this).click(function () {
                if($(this).css('background').indexOf("rgb(255, 255, 255)") != -1){
                    content_control.show_all();
                    content_control.excute_filter();
                    $(this).css('background','transparent');
                    return ;
                }
                $('.tag-li').each(function () {
                    $(this).css('background','transparent')
                });
                $('.category-li').each(function () {
                    $(this).css('background','transparent')
                });
                var id = $(this).find('.tag-id').text();
                var count = 0;
                $('#result-table>tbody>tr').each(function () {
                    if($(this).find('th').length == '0') {
                        $(this).stop();
                        if ($(this).find('td.type-id').text().toLowerCase().indexOf(id) == -1) {
                            $(this).fadeOut("300");
                            ++count;
                        }
                    }
                });
                $('#search-result').text("Found : " + count);
                $(this).css('background','white');
            })
        })

    }
};