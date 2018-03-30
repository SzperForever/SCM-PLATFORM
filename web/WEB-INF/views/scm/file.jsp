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
                <td>请选择文件：</td>
                <td><input type="file" name="file"></td>
            </tr>
            <tr>
                <td>文件类型：</td>
                <td><input type="text" name="type"/></td>
            </tr>
            <tr>
                <td>开始上传</td>
                <td><input type="submit" value="上传" onclick="upload()"></td>
            </tr>
        </table>
    </form>
</div>