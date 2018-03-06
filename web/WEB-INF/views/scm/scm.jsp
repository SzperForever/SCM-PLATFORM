<%--
  Created by IntelliJ IDEA.
  User: leehaoze
  Date: 2018/3/5
  Time: 下午7:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>scm</title>
    <link href="CSS/SCM/Style.css" type="text/css" rel="stylesheet">
</head>
<body>
<div id="header">
    <div id="info">
        <div id="name">SCM PLATFORM</div>
        <div id="description">This is SCM PLATFORM.This platform collects commonly used links in the work, Excel macros, FTP files, to facilitate daily use, improve work efficiency</div>
    </div>
    <div id="nav">
        <span class="type">All Modules</span>
        <span class="type">Planning</span>
        <span class="type">Purchasing</span>
        <span class="type">InventoryControl</span>
        <span class="type">MRO</span>
    </div>
</div>

<div id="display-area">
    <div>
        <span class="module-pic">A</span>
        <h3>Name A</h3>
        <p>Test for short description</p>
        <%--这里的type和上边nav用一样的class--%>
        <span class="module-type type">Planning</span>
    </div>
    <div>
        <span class="module-pic">B</span>
        <h3>Name B</h3>
        <p>This is a description. Test for a long description to prevent text overflow</p>
        <%--这里的type和上边nav用一样的class--%>
        <span class="module-type type">Planning</span>
    </div>
    <div>
        <span class="module-pic">C</span>
        <h3>Name C</h3>
        <p>This is a description. Test for a very long description.Here has one solution to solve this problem : shorten this text or this text will overflow</p>
        <%--这里的type和上边nav用一样的class--%>
        <span class="module-type type">Planning</span>
    </div>
    <div>
        <span class="module-pic">D</span>
        <h3>Name D</h3>
        <p>This is a description</p>
        <%--这里的type和上边nav用一样的class--%>
        <span class="module-type type">Planning</span>
    </div>
    <div>
        <span class="module-pic">E</span>
        <h3>Name E</h3>
        <p>This is a description</p>
        <%--这里的type和上边nav用一样的class--%>
        <span class="type">Planning</span>
    </div>
    <div>
        <span class="module-pic">F</span>
        <h3>Name F</h3>
        <p>This is a description</p>
        <%--这里的type和上边nav用一样的class--%>
        <span class="type">Planning</span>
    </div>
    <div>
        <span class="module-pic">G</span>
        <h3>Name G</h3>
        <p>This is a description</p>
        <%--这里的type和上边nav用一样的class--%>
        <span class="type">Planning</span>
    </div>
    <div>
        <span class="module-pic">H</span>
        <h3>Name H</h3>
        <p>This is a description</p>
        <%--这里的type和上边nav用一样的class--%>
        <span class="type">Planning</span>
    </div>
</div>


</body>
</html>
