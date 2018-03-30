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
<div id="top-bar" >
    <div id="container">
        <div id="logo" class="w3-bar-item">
            <span></span>
        </div>
        <div id="search-box" class="w3-bar-item">
            <input class="w3-input" placeholder="Find a document" type="text">
            <i class="fas fa-search w3-large"></i>
        </div>
        <div class="popupmenu document-list bright" id="searchresults" style="display: none;">
            <div class="results"><p class="empty">no document found</p></div>
        </div>
        <div class="instance">
            <div id="snippet-instanceSelect-"><span class="instance-name">@&nbsp;&nbsp;Jabil</span>
            </div>
        </div>
        <div style="display: none;" id="snippet--top">
            <div class="topmenu">
                <div id="usermenu-btns-desktop">
                    <a title="My Profile" class="tooltip my-profile" href="/profile/detail/?folder=1">
                        <img src="https://jabil.teamguru.com/data/jabil.teamguru.com/images/thumb/1_ec932b5837.jpg?1516956493"
                             width="30" height="30" alt="">
                        <span class="name">Johnson Jia</span>
                    </a>
                    <a title="Home - My Dashboard" class="tooltip my-dashboard" href="/dashboard/?folder=1">Home</a>
                    <a title="Recently opened documents" class="recent_button tooltip my-recents" href="#"
                       onclick="return false;">Recents</a>
                    <div id="notification-icon-wrapper" class="react-component-wrapper react-component-container"><span
                            data-reactroot="" class="tooltip" title=""><a href="#"
                                                                          class="notification_button tooltip"><span
                            class="icon icon-notifications"></span></a></span></div>
                    <a href="/imessage/?folder=1" class="tooltip" title="Messages">
                        <span class="icon icon-mail"></span>
                    </a>
                    <a title="Settings" class="setting_button tooltip settings" href="#" onclick="return false;"><span
                            class="icon icon-settings"></span></a>
                    <a title="Logout" class="tooltip logout" href="/auth/logout/"><span class="icon icon-logout"></span></a>
                </div>
                <div class="cleaner"></div>

            </div>
        </div>
    </div>
</div>

<div id="full-page-container">
    <div id="page" class="content-container">
        <div id="left">
            <div id="asyncJobNotification" class="react-component-container"><!-- react-empty: 1 --></div>
            <div id="favorite">
                <span>FAVORITES</span>
            </div>
            <div id="by-category">
                <span>By category</span>
                <ul>

                </ul>
            </div>
            <div id="by-tag">
                <span>By Tag</span>
                <ul>

                </ul>
            </div>
        </div>
        <div id="center">
            <div id="search-box-2">
                <input class="w3-input" placeholder="Find a document" type="text" style="width: 20%">
                <button class="w3-button">Show Filters</button>
            </div>
            <table id="data-number" class="w3-table-all w3-hoverable">
                <tr>
                    <th>Title</th>
                    <th>Type</th>
                    <th>number</th>
                    <th>description</th>
                    <th>file path</th>
                    <th>category</th>
                </tr>
            </table>
        </div>
    </div>
</div>

</body>
</html>
