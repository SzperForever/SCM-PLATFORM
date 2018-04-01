<%--
  Created by IntelliJ IDEA.
  User: leehaoze
  Date: 2018/3/27
  Time: 下午4:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>procedure document</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <link href="CSS/SCM/w3.css" type="text/css" rel="stylesheet">
    <link href="CSS/SCM/procedure-documentStyle.css" type="text/css" rel="stylesheet">
    <link href="CSS/SCM/fontawesome-all.min.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="JS/Public/183.js"></script>
    <script type="text/javascript" src="JS/SCM/procedure.js"></script>
</head>
<body>
<div id="top-bar">
    <div id="top-bar-container">
        <div id="logo" class="w3-bar-item">
            <span></span>
        </div>
        <div id="search-box" class="w3-bar-item">
            <input class="w3-input" placeholder="Find a document" type="text">
            <i class="fas fa-search w3-large"></i>
        </div>
            <div id="search-box-result" style="display: none"></div>
        <div class="instance">
            <div id="snippet-instanceSelect-"><span class="instance-name">@&nbsp;&nbsp;Jabil</span>
            </div>
        </div>
    </div>
</div>

<div id="full-page-container">
    <div id="page" class="content-container">
        <div id="left" class="w3-sidenav">
            <div class="top-line"></div>
            <div id="favorite">
                <span>Favorites</span>
            </div>
            <div id="by-category">
                <span style="padding: 5px 0px 5px 0px;display: block;color: #666;position:relative;">
                    By Category
                    <i class="right-caret fas fa-caret-right" style="right: -10px;transform: rotate(-90deg);transition: transform 800ms;" onclick="content_control.collapse_category()"></i>
                </span>
                <ul id="category-ul">

                </ul>
            </div>
            <div id="by-tag">
                <span style="padding: 5px 0px 5px 0px;display: block;color: #666;position:relative;">
                    By Tag
                    <i class="right-caret fas fa-caret-right" style="right: -10px;transform: rotate(-90deg);transition: transform 800ms;" onclick="content_control.collapse_tag()"></i>
                </span>
                <ul id="tag-ul">

                </ul>
            </div>
            <div id="blank-cover">

            </div>
        </div>
        <div id="center">
            <div id="top-path">
                <span><i class="fas fa-home"></i></span>
                <span>Home</span>
                >
                <span>Procedure</span>
            </div>

            <div id="blank-line"></div>

            <div style="margin-bottom: 6px;">

                <div id="bar-name"><h1 style="color: #002a3f;">Documents</h1></div>

                <div id="document-filter">
                    <input type="search" name="document-tag">
                    <i class="fas fa-search w3-large"></i>
                    <button class="w3-button">Show Filters</button>
                    <span id="active-filters">
                    <p>Active filters:</p>
                    <span>Today<i class="fas fa-times"></i></span>
                </span>
                    <div id="search-result">Found : 1</div>
                </div>

            </div>

            <div id="filter-select">
                <div class="filter-item">
                    <h3>Status</h3>
                    <ul>
                        <li>Active</li>
                    </ul>
                </div>
                <div class="filter-item">
                    <h3>Document Type</h3>
                    <ul>
                        <li>Document</li>
                    </ul>
                </div>
                <div class="filter-item">
                    <h3>Created</h3>
                    <ul>
                        <li>Today</li>
                        <li>This week</li>
                        <li>This month</li>
                        <li>This year</li>
                        <li>Custom Range</li>
                    </ul>
                </div>
                <div class="filter-item">
                    <h3>Status</h3>
                    <ul>
                        <li>Active</li>
                    </ul>
                </div>
            </div>

            <div id="result-table-container">
                <table id="result-table" class="w3-table-all">
                    <tr style="color: #1a4473;">
                        <th>Title</th>
                        <th>Description</th>
                        <th>Category</th>
                        <th>Type</th>
                        <th>File number</th>
                        <th>FilePath</th>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>
