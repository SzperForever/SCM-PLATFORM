$(function () {
    content_control.run();
    $('#particles-js').css({
        'position': 'absolute'
    });


    $.ajax({
        dataType: 'json',
        url: '/getTag.form',
        success: function (data) {
            cache['tag'] = data;
            removeLoading();
        }
    })

    // document.getElementsByClassName('particles-js-canvas-el')[0].setAttribute("height",$('body').height());
    // document.getElementsByClassName('particles-js-canvas-el')[0].setAttribute("width",$('body').width());
});

//用来储存一些临时数据，比如预加载的AJAX数据
var cache = {};

//内容控制，用来加载数据
var content_control = {
    run: function () {
        this.loadNameAndDescription();
        this.loadCategory();
        this.loadURL();
        this.bind_menu_event();
        this.loadLink();
    },
    //加载名称和描述
    loadNameAndDescription: function () {
        $.ajax({
            dataType: 'json',
            url: '/getConf.form',
            success: function (data) {
                $('#name').text(data[0]['name']);
                $('#description').text(data[0]['description']);
                removeLoading()
            }
        })
    },
    //加载背景图,收起菜单
    loadBackground: function () {
        $.ajax({
            dataType: 'text',
            url: '/getBackground.form',
            success: function (data) {
                var header = $('#header');
                var height = header.outerHeight(),
                    width = header.width(),
                    top = header.position()['top'],
                    margin_left = (header.outerWidth(true) - width) / 2;
                $('#header-background').css({
                    'position': 'absolute',
                    'height': height,
                    'width': width,
                    // 'margin-top': '8vh',
                    'margin-left': margin_left,
                    'background': 'url("' + data + '") no-repeat  center',
                    'background-size': 'cover'
                });
                setTimeout(function () {
                    //收起菜单
                    content_control.collapse_menu();
                    removeLoading();
                }, 100);
            }
        })
    },
    loadCategory: function () {
        $.ajax({
            dataType: 'json',
            url: '/getCategory.form',
            success: function (data) {
                //缓存以备侧边菜单使用
                cache['category'] = data;
                $.each(data, function (index, val) {
                    var content = '<span class="category">' + val + '</span>';
                    $('#nav').append(content);
                });
                //加载完毕目录之后再加载背景图，防止出现高度不统一
                content_control.loadBackground();
                removeLoading();
            }
        });

    },
    //加载URL卡片
    loadURL: function () {
        $.ajax({
            dataType: 'json',
            url: '/getURL.form',
            success: function (data) {
                cache['URL'] = data;
                $.each(data, function (index, val) {
                    var content =
                        '<div class="w3-card-4">' +
                        '   <span class="module-pic"><img src="' + val['picPath'] + '"></span>' +
                        '   <span class="module-tag">' + val['tagID'] + '</span>' +
                        '   <h3><a href="' + val['link'] + '" target="_blank">' + val['title'] + '</a></h3>' +
                        '   <p class="description">' + val['description'] + '</p>' +
                        '   <span class="data">' + val['description'] + '</span>';

                    if (val['category'].length > 1) {
                        content += '<span class="more-category">';
                        for (var i = 0; i < val['category'].length; ++i) {
                            content += '   <span class="module-type category">' + val['category'][i] + '</span>'
                        }
                        content += '</span><button class="more-category-button"">...</button>';
                    }
                    else {
                        content += '   <span class="module-type category">' + val['category'][0] + '</span>'
                    }

                    content +=
                        '   <span class="category-id">' + val['category_id'] + '</span>' +
                        '</div>';


                    $('#display-area').append(content);
                    if (val['picPath'] === 'null' || val['picPath'] == null) {
                        var module_pic = $('#display-area>div:last>span:first');
                        module_pic.empty();
                        module_pic.text(val['title'].slice(0, 1));
                    }
                });
                //绑定更多按钮
                $('.more-category-button').each(function () {
                    $(this).click(function () {
                        category_select.display_category($(this));
                    })
                });

                $('#display-area>div').each(function () {
                    var p = $(this).find('p');
                    var area_width = p.width() - 100;
                    var char_num = area_width / 9.7;
                    p.text($(this).find('span.data').text());
                    if ($(this).find('span.data').text().length > char_num * 3) {
                        p.empty();
                        p.append($(this).find('span.data').text().slice(0, char_num * 3 - 17) + '&nbsp<span class="read-more">Read more</span>');
                        urlCard_control.bindReadMoreListener($(this));
                    }
                });
                $('#blank_div').each(function () {
                    $(this).remove();
                });
                if ($('#display-area>div').size() % 4 != 0) {
                    for (var i = 0; i < 10 % 4; ++i) {
                        $('#display-area').append('<span id="blank_div" style="width: 22%; height: 22vh;"></span>')
                    }
                }

                content_control.windows_resize_control();
                //绑定点击事件
                setTimeout(function () {
                    category_select.listen_click();
                    content_control.listen_leanPortal_click();
                    removeLoading();
                }, 200);
                //告知发生变化
                filter_menu_control.onChange(true);
            }
        });
    },
    loadLink: function () {
        $.ajax({
            dataType: 'json',
            url: '/getLinks.form',
            success: function (data) {
                $.each(data, function (index, val) {
                    var content = '<a href="' + val['url'] + '" class="footer-link" target="_blank">' + val['name'] + '</a>';
                    $('#footer-link').append(content);
                });
                removeLoading();
            }
        })
    },
    // 收起菜单栏
    collapse_menu: function () {
        var relative_menu = $('#relative-menu');
        var distance = relative_menu.height() - $('#down-button').height();
        relative_menu.css({
            'transform': 'translateY(-' + distance + 'px)'
        });
        $('#down-button>i').css({
            'transform': ' rotate(0)'
        });
        $('#display-area').css({
            'transform': 'translateY(-' + distance + 'px)'
        });
        removeLoading();
    },
    bind_menu_event: function () {
        //点击下拉按钮
        $('#down-button').click(function () {
            if (cache['menu-status'] == null || cache['menu-status'] == 'collapse') {
                $('#relative-menu').css({
                    'transform': 'translateY(0)'
                });
                $('#down-button>i').css({
                    'transform': ' rotate(180deg)'
                });
                $('#display-area').css({
                    'transform': 'translateY(0)'
                });
                cache['menu-status'] = 'down';
            }
            else {
                content_control.collapse_menu();
                cache['menu-status'] = 'collapse';
            }
        });
        //点击搜索栏
        $('#search>input').click(function () {
            $('#cancel-search').css('display', 'block');
            if ($('.category:first').attr('class').indexOf("active") == -1) {
                $('.category:first').click();
            }
        });
        //响应搜索
        $('#search>input').on('input propertychange', function () {
            var text = $(this).val().toLowerCase();
            $('#display-area>div').each(function () {
                var content = $(this).find('h3 a').text();
                // contain
                if (content.toLowerCase().indexOf(text) == -1 && content != "") {
                    $(this).css({
                        'display': 'none'
                    })
                }
                else {
                    $(this).css({
                        'display': 'block'
                    })
                }
            });
            filter_menu_control.onChange(false);
        });
        //鼠标在取消按钮上
        $('#cancel-search').hover(function () {
            $(this).removeClass('far');
            $(this).addClass('fas');
        }, function () {
            $(this).addClass('far');
            $(this).removeClass('fas');
        });
        //消除已输入的字
        $('#cancel-search').click(function () {
            $('#search>input').val("");
            filter_menu_control.onChange(false);
        });
        //相应筛选菜单点击事件
        filter_menu_control.listen_click();
        removeLoading();
    },
    listen_leanPortal_click : function () {
        var lean_portal;
        $('#display-area>div').each(function () {
            if($(this).find('h3 a').text() == 'lean portal'){
                lean_portal = $(this);
            }
        });
        lean_portal.find('h3 a').attr('onclick','return false');
        lean_portal.click(function () {
            //隐藏其他卡片
            $('#display-area>div').each(function () {
                if($(this).find('h3 a').text() != 'lean portal'){
                    $(this).css('display','none');
                }
            });
            filter_menu_control.onChange(true);
            lean_portal.css({
                'width': '100%',
                'height': '60vh',
                'transition': 'all 1s'
            });
            lean_portal.children().each(function(){$(this).fadeOut('200ms')});
            lean_portal.append('<div id="lean-portal"></div>');
            var page = $('#lean-portal');
            page.load()
        })
    },
    windows_resize_control: function () {
        $(window).resize(function () {
            var count = 0;
            $('#display-area>div').each(function () {
                var p = $(this).find('p');
                var area_width = p.width() - 100;
                var char_num = area_width / 9.7;
                p.text($(this).find('span.data').text());
                if ($(this).find('span.data').text().length > char_num * 3) {
                    p.empty();
                    p.append($(this).find('span.data').text().slice(0, char_num * 3 - 17) + '&nbsp<span class="read-more">Read more</span>');
                    urlCard_control.bindReadMoreListener($(this));
                }
                if ($(this).css('display') != 'none') {
                    count++;
                }
            });
            $('#blank_div').each(function () {
                $(this).remove();
            });
            if (count % 4 != 0) {
                for (var i = 0; i < 10 % 4; ++i) {
                    $('#display-area').append('<span id="blank_div" style="width: 22%; height: 22vh;"></span>')
                }
            }

            //控制遮罩
            var header = $('#header');
            var height = header.outerHeight(),
                width = header.width(),
                top = header.position()['top'],
                margin_left = (header.outerWidth(true) - width) / 2;
            $('#header-background').css({
                'position': 'absolute',
                'height': height,
                'width': width,
                // 'margin-top': '5vh',
                'margin-left': margin_left,
            });

        });
    }
};

var filter_menu_control = {
    onChange: function (first) {
        $('#filter-menu>span').each(function () {
            $(this).removeClass('active');
        });
        var span_all = $('#filter-menu>span:nth-child(1)');
        span_all.addClass('active');
        $('#display-area>div').each(function () {
            if ($(this).css('display') != 'none') {
                $('#filter-menu > span:nth-child(' + ($(this).find('h3 a').text().slice(0, 1).toLowerCase().charCodeAt() - 95) + ')').addClass("active");
            }
            else {
                span_all.removeClass('active');
            }
        });

        if (first) {
            //绑定不允许点击属性
            $('#filter-menu>span').each(function () {
                if ($(this).attr('class') == undefined) {
                    $(this).addClass('disable');
                }
            });
        }
    },
    listen_click: function () {
        $('#filter-menu>span').each(function () {
            $(this).click(function () {
                if ($(this).attr('class').indexOf("disable") == -1) {
                    //未激活
                    if ($(this).attr('class') == undefined || $(this).attr('class').indexOf('active') == -1) {
                        $(this).addClass('active');
                        var text = $(this).text();
                        $('#display-area>div').each(function () {
                            if (text == 'All' || $(this).find('h3 a').text().slice(0, 1).toLowerCase() == text.toLowerCase()) {
                                $(this).css('display', 'block');
                            }
                        });
                    } else {
                        $(this).removeClass('active');
                        var text = $(this).text();
                        $('#display-area>div').each(function () {
                            if (text == 'All' || $(this).find('h3 a').text().slice(0, 1).toLowerCase() == text.toLowerCase()) {
                                $(this).css('display', 'none');
                            }
                        });
                    }

                    filter_menu_control.onChange(false);
                    filter_menu_control.check_all_status();
                }
            })
        })
    },
    check_all_status: function () {
        var span_all = $('#filter-menu>span:nth-child(1)');
        span_all.addClass('active');
        $('#display-area>div').each(function () {
            if ($(this).css('display') == 'none') {
                span_all.removeClass('active');
                return;
            }
        })
    }
};

var category_select = {
    run: function () {
        this.listen_click();
    },
    listen_click: function () {
        $(".category").click(function () {
            category_select.switch_card($(this).text(), "Modules")
        })
    },
    switch_card: function (name, by_what) {
        name = name.trim();
        if (name == "All") {
            name = "All Modules";
        }
        //移除所有分类的激活状态
        $('.category').removeClass('active');
        var category_name = name;
        //给对应的分类添加激活状态
        if (by_what == 'Modules') {
            $('#nav>span').each(function () {
                if ($(this).text() == category_name) {
                    $(this).addClass('active');
                }
            });
        }
        else {
            $('#nav>span:first').addClass('active');
        }
        //点击all时 显示所有的模块
        var all = false;
        var module_div = $('#display-area>div');
        if (category_name == "All Modules") {
            all = true;
        }
        // module_div.css('display', 'block');
        //添加消失动画
        module_div.addClass('disappear');
        //延时调用函数，使得可以播放完消失动画
        setTimeout(function () {
            $('#display-area>div').css('display', 'none');
            $('.temp_blank_div').each(function () {
                $(this).remove();
            });
            var count = 0;
            $('#display-area>div').each(function () {
                if ($(this).find('.module-type').text() == category_name || all || $(this).find('.module-tag').text().indexOf(category_name) != -1) {
                    $(this).css('display', 'block');
                    $(this).addClass('show');
                    count += 1;
                }
            });

            $('#blank_div').each(function () {
                $(this).remove();
            });
            if (count % 4 != 0) {
                for (var i = 0; i < 10 % 4; ++i) {
                    $('#display-area').append('<span id="blank_div" style="width: 22%; height: 22vh;"></span>')
                }
            }

            //告知筛选菜单 元素发生变化
            filter_menu_control.onChange();

        }, 550);
        setTimeout(function () {
            $('#display-area>div').each(function () {
                $(this).removeClass('disappear');
                $(this).removeClass('show');
            })
        }, 1000);
    },
    display_category: function (element) {
        if (element.text() == "...") {
            element.prev().css({'display': 'flex'});
            // element.prev().addClass('more-category-expand');
            element.text("X");
        }
        else {
            // element.prev().removeClass('more-category-expand');
            element.prev().css('display', 'none');
            element.text("...");
        }

    }
};

var urlCard_control = {
    run: function () {

    },
    bindReadMoreListener: function (object) {
        object.find('.read-more').click(function () {
            object.find('p').text(object.find('span.data').text());
            var pre_height = object.outerHeight();
            object.css('height', pre_height);
            object.css('height', 'auto');
            var target_height = object.outerHeight();
            object.css('height', pre_height);
            console.log(target_height, object.height());
            object.animate({
                height: target_height + 'px'
            }, 200);
        })
    }
};

var count = 0;

function removeLoading() {
    count++;
    if (count == 8) {
        setTimeout(function () {
            $('#loading').fadeOut(600);
        }, 600);
        side_menu_control.run();
    }
}

var side_menu_control = {
    run: function () {
        this.loadContent();
    },
    loadContent: function () {
        //load module
        var by_module = $('#by-module');
        var module_data = cache['category'];
        var functionName = 'category_select.switch_card("All")';
        var content = '<button class="w3-button w3-bar-item w3-blue-gray" onclick=' + functionName + '>All Modules</div>';
        by_module.append(content);
        $.each(module_data, function (index, val) {
            var functionName = 'category_select.switch_card("' + val + '","Modules")';
            var content = '<button class="w3-button w3-bar-item w3-blue-gray" onclick=' + functionName + '>' + val + '</div>';
            by_module.append(content);
        });
        //load tags
        var URL_data = cache['tag'];
        var by_tag = $('#by-tag');
        $.each(URL_data, function (index, val) {
            var functionName = 'category_select.switch_card(\'' + val['id'] + '\',\'Tags\')';
            var content = '<button class="w3-button w3-bar-item w3-blue-gray" onclick=\"' + functionName + '\">' + val['name'] + '</div>';
            by_tag.append(content);
        });
    },
    closeMenu: function () {

        $('#left-menu').css('display', 'none');
        $('#main-container').removeClass();
        $('#main-container').addClass('animate-left');
    },
    openLeftMenu: function () {
        $('#left-menu').css('display', 'block');
        $('#main-container').removeClass();
        $('#main-container').addClass('animate-right');
    },
    sortByFrequent: function () {

    },
    sortByA_Z: function () {

    }
};
