<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="CSS/SCM/fontawesome-all.min.css" type="text/css" rel="stylesheet">
<div>
    <i id="restore-card" class="fas fa-times-circle w3-xlarge w3-display-middle"></i>
    <form action="/uploadfile.form" enctype="multipart/form-data" method="post" style="width: 80%;margin:0 auto">
        <table style="margin: 0 auto;
    margin-top: 58px;">
            <tr>
                <td>请选择文件：</td>
                <td><input type="file" name="file"></td>
            </tr>
            <tr>
                <td>开始上传</td>
                <td><input type="submit" value="上传"></td>
            </tr>
        </table>
    </form>
</div>