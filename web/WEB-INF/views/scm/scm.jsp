<%--
  Created by IntelliJ IDEA.
  User: leehaoze
  Date: 2018/3/5
  Time: 下午7:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>scm</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <link href="CSS/SCM/Style.css" type="text/css" rel="stylesheet">
    <link href="CSS/SCM/w3.css" type="text/css" rel="stylesheet">
    <link href="CSS/SCM/animate.css" type="text/css" rel="stylesheet">
    <link href="CSS/SCM/fontawesome-all.min.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="JS/Public/183.js"></script>
    <script type="text/javascript" src="JS/SCM/scm.control.js"></script>
</head>
<body>

<div id="loading">
    <div id="loading-center">
        <div id="loading-center-absolute">
            <div class="object" id="object_one"></div>
            <div class="object" id="object_two"></div>
            <div class="object" id="object_three"></div>
            <div class="object" id="object_four"></div>
            <div class="object" id="object_five"></div>
            <div class="object" id="object_six"></div>
            <div class="object" id="object_seven"></div>
            <div class="object" id="object_eight"></div>
            <div class="object" id="object_big"></div>
        </div>
    </div>
</div>

<div id="particles-js"></div>

<div id="top-menu">
    <button class="w3-button w3-teal w3-large w3-left" onclick="side_menu_control.openLeftMenu()" style="background-color:transparent!important;">&#9776;</button>
    <div id="left-menu" class="w3-sidebar w3-bar-block w3-card-4 w3-animate-left w3-blue-gray">
        <button onclick="side_menu_control.closeMenu()" class="w3-bar-item w3-button w3-large">Close &times;</button>
        <div class="w3-bar-item">Platform</div>
        <div id="by-module" class="w3-dropdown-hover">
            <button class="w3-button w3-blue-gray">By Module<i class="fa fa-caret-down"></i></button>
            <div class="w3-dropdown-content w3-bar-block">
                <%--<div class="w3-bar-item">planning</div>--%>
            </div>
        </div>
        <div id="by-tag" class="w3-dropdown-hover">
            <button class="w3-button w3-blue-gray">By Functions(tags)<i class="fa fa-caret-down"></i> </button>
            <div class="w3-dropdown-content w3-bar-block">

            </div>
        </div>
        <div id="applications" class="w3-dropdown-hover">
            <button class="w3-button w3-blue-gray">Applications<i class="fa fa-caret-down"></i> </button>
            <div class="w3-dropdown-content w3-bar-block">

            </div>
        </div>
        <div id="reference" class="w3-dropdown-hover">
            <button class="w3-button w3-blue-gray">Reference<i class="fa fa-caret-down"></i> </button>
            <div class="w3-dropdown-content w3-bar-block">

            </div>
        </div>
        <div class="w3-dropdown-hover">
            <button class="w3-button w3-blue-gray">Sort by<i class="fa fa-caret-down"></i> </button>
            <div class="w3-dropdown-content w3-bar-block">
                <button class="w3-button" onclick="side_menu_control.sortByfrequent()">Most Frequent</button>
                <button class="w3-button" onclick="side_menu_control.sortByA_Z()">A-Z</button>
            </div>
        </div>
    </div>
</div>

<div id="header-background" class="w3-card-4"></div>
<div id="header">
    <div id="info">
        <div id="name">SCM PLATFORM</div>
        <div id="description"></div>
    </div>
    <div id="nav">
        <span class="category active">All Modules</span>
    </div>
</div>
<div id="relative-menu">
    <div id="search">
        <i class="fas fa-search w3-xlarge"></i>
        <input class="w3-input w3-animate-input" type="text" style="border-bottom: 2px solid #ccc;">
        <i id="cancel-search" class="far fa-times-circle w3-xlarge"></i>
    </div>
    <div id="filter-menu">
        <span>All</span>
        <span>A</span>
        <span>B</span>
        <span>C</span>
        <span>D</span>
        <span>E</span>
        <span>F</span>
        <span>G</span>
        <span>H</span>
        <span>I</span>
        <span>J</span>
        <span>K</span>
        <span>L</span>
        <span>M</span>
        <span>N</span>
        <span>O</span>
        <span>P</span>
        <span>Q</span>
        <span>R</span>
        <span>S</span>
        <span>T</span>
        <span>U</span>
        <span>V</span>
        <span>W</span>
        <span>X</span>
        <span>Y</span>
        <span>Z</span>
    </div>
    <span id="down-button" class="w3-display-middle"><i
            class="fas fa-chevron-circle-down w3-xlarge w3-display-middle"></i>
</span>
</div>

<div id="display-area">
</div>

<div id="footer">
    <div>
        <div class="w3-hide-medium w3-third" style="border: 0 solid white;border-right-width: 5px;">
            <div id="footer-content">
                <img src="/IMG/SCM/jabil-logo-white.svg" alt="JABIL" class="jabillogo">
                <div id="footer-des">
                    <p>Jabil’s engineering, manufacturing and intelligent supply chain solutions help you capitalize on
                        accelerated change and achieve digital business advantage.</p>
                </div>
            </div>
        </div>
        <div class="w3-rest">
            <div id="footer-link" style="height: 100%;width: 80%;display: flex;flex-flow: column wrap;justify-content: space-between;align-items: left;"></div>
        </div>
    </div>
</div>

</body>
<script type="text/javascript" src="JS/SCM/particles.js"></script>
<script type="text/javascript" src="JS/SCM/app.js"></script>
</html>
