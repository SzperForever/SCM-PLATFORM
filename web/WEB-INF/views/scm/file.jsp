<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="CSS/SCM/fontawesome-all.min.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="JS/Public/jquery.form.js"></script>
<div>
    <div style="width:20%;margin: 0 auto;font-size: x-large">Lean Portal</div>
    <button onclick="content_control.restore_card()" style="cursor: pointer"><i id="restore-card" class="fas fa-times-circle w3-xlarge w3-display-topright"></i></button>
    <form id="upload-form" action="/uploadfile.form" enctype="multipart/form-data" method="post" style="width: 80%;margin:0 auto">
        <table style="margin: 0 auto;
    margin-top: 58px;">
            <tr>
                <td>Choose file：</td>
                <td><input type="file" name="file" value="Choose file"></td>
            </tr>
            <tr style="display: none">
                <td>Choose type：</td>
                <td><input id="id" type="text" name="type"/></td>
            </tr>
            <tr>
                <td>Choose type:</td>
                <td><select id="select" class="w3-select" name="type">
                    <option value="" disabled selected>Choose your option</option>
                </select></td>
            </tr>
            <tr>
                <td>Upload</td>
                <td><input type="submit" value="Upload" onclick="upload_control.pre_handle()"></td>
            </tr>
        </table>
    </form>
</div>
<script>
    var upload_control = {
        load_type : function () {
            $.ajax({
                dataType : 'json',
                url : '/getTypes.form',
                success: function (data) {
                    $.each(data,function (index, value) {
                        $('#select').append('<option value="'+index+'">'+value['typeName']+'</option>')
                    });
                }
            })
        },
        pre_handle : function () {
            $('#id').val( $('#select').val());
            upload_control.upload();
        }
        ,
        upload : function () {
            $('#upload-form').ajaxForm(function () {
                alert("Upload Success!");
                $('upload-form').clearForm();
            });
        },

    };
    $(function () {
        upload_control.load_type();
    });
</script>