$(function () {
    content_control.run();
});

let content_control = {
    run: function () {
        this.loadDescription();
        this.loadCategory();
        this.loadURL();
    },
    loadDescription: function () {
        $.ajax({
            dataType: 'text',
            url: '/getDescription.form',
            success: function (data) {
                $('#description').text(data);
            }
        })
    },
    loadCategory: function () {
        $.ajax({
            dataType: 'json',
            url: '/getCategory.form',
            success: function (data) {
                $.each(data, function (index, val) {
                    let content = '<span class="category">' + val + '</span>';
                    $('#nav').append(content);
                });

            }
        });

    },
    loadURL: function () {
        $.ajax({
            dataType: 'json',
            url: '/getURL.form',
            success: function (data) {
                $.each(data, function (index, val) {
                    let content =
                        '<div>' +
                        '   <span class="module-pic"><img src="' + val['picPath'] + '"></span>' +
                        '   <h3><a href="' + val['link'] + '">' + val['title'] + '</a></h3>' +
                        '   <p class="description">' + val['description'] + '</p>' +
                        '   <span class="data">' + val['description'] + '</span>' +
                        '   <span class="module-type category">' + val['category'] + '</span>' +
                        '</div>';
                    $('#display-area').append(content);
                    if (val['picPath'] === 'null') {
                        let module_pic = $('#display-area>div:last>span:first');
                        module_pic.empty();
                        module_pic.text(val['title'].slice(0, 1));
                    }
                });
                content_control.windows_resize_control();
                //绑定点击事件
                category_select.listen_click();
            }
        });
    },
    windows_resize_control: function () {
        $(window).resize(function () {
            $('#display-area>div').each(function () {
                let p = $(this).find('p');
                let area_width = p.width() - 100;
                let char_num = area_width / 9.7;
                p.text($(this).find('span.data').text());
                if ($(this).find('span.data').text().length > char_num * 3) {
                    p.empty();
                    p.append($(this).find('span.data').text().slice(0, char_num * 3 - 17) + '&nbsp<span class="read-more">Read more</span>');
                    urlCard_control.bindReadMoreListener($(this));
                }
            });
            $('#blank_div').remove();
            if ($('#display-area>div').size() % 3 != 0) {
                $('#display-area').append('<span id="blank_div" style="width: 30%; height: 24vh;"></span>')
            }
        });
    },
};

let category_select = {
    run: function () {
        this.listen_click();
    },
    listen_click: function () {
        $(".category").click(function () {

            $('.category').removeClass('active');

            let category_name = $(this).text();

            $('#nav>span').each(function () {
                if ($(this).text() == category_name) {
                    $(this).addClass('active');
                }
            });


            let all = false;
            let module_div = $('#display-area>div');
            if (category_name == "All Modules") {
                all = true;
            }
            module_div.css('display', 'block');
            module_div.addClass('disappear');
            setTimeout(function () {
                $('#display-area>div').css('display', 'none');
                $('#display-area>div').each(function () {
                    if ($(this).find('.module-type').text() == category_name || all) {
                        $(this).css('display', 'block');
                        $(this).addClass('show');
                    }
                })
            }, 700);
            setTimeout(function () {
                $('#display-area>div').each(function () {
                    $(this).removeClass('disappear');
                    $(this).removeClass('show');
                })
            }, 1000)
        })
    }
};

let urlCard_control = {
    run: function () {

    },
    bindReadMoreListener: function (object) {
        object.find('.read-more').click(function () {
            object.find('p').text(object.find('span.data').text());
            let pre_height = object.outerHeight();
            object.css('height', pre_height);
            object.css('height','auto');
            let target_height = object.outerHeight();
            object.css('height', pre_height);
            console.log(target_height, object.height());
            object.animate({
                height: target_height + 'px'
            }, 200);
        })
    }
};