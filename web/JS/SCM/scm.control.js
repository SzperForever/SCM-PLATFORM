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
                //绑定点击事件
                category_select.listen_click();
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
                        '   <p class="descri">' + val['description'] + '</p>' +
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
            let category_name = $(this).text();
            if(category_name=="All Modules"){
                $('#display-area>div').css('display', 'block');
            }else {
                $('#display-area>div').css('display', 'none');
                $('#display-area>div').each(function () {
                    if ($(this).find('.module-type').text() == category_name) {
                        console.log($(this).find('.module-type').text());
                        $(this).css('display', 'block');
                    }
                })
            }
        })
    }

};