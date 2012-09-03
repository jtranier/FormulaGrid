<%@ page import="grails.converters.JSON" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="formulagrid"/>
    <title><g:message code="formulagrid.Race"/></title>

    <r:require modules="bootstrap,race"/>
</head>

<body>

<hr/>

<g:render template="rules" />
<div class="row">
    <div class="span2 offset10">
        <a href="#modalRules" role="button" class="" data-toggle="modal">
            Règles du jeu <i class="icon-question-sign"></i>
        </a>
        <p></p>
    </div>
</div>

<div class="row">
    <div id="svg" class="span10 offset1">
    </div>
</div>

<hr/>

<div class="row">
    <div class="span2 offset5">
        <g:render template="/car/control"/>
    </div>
</div>

<script type="text/javascript">
    var xMax = ${race.track.width};
    var yMax = ${race.track.height};
    var width = d3.select("#svg").property("clientWidth");
    var height = Math.round(width * (yMax / xMax));

    var padding = 10;
    var strokeWidth = 5;
    var rounded = 10;

    var xScale = d3.scale.linear()
            .domain([0, xMax])
            .range(["0", (width - 2 * padding)]);

    var yScale = d3.scale.linear()
            .domain([0, yMax])
            .range(["0", (height - 2 * padding)]);

    var svg = d3.select("#svg").
            append("svg")
            .attr("width", width)
            .attr("height", height)
            .append("g")
            .attr("transform", "translate(" + padding + "," + padding + ")");


    drawTrackBox("white");
    drawTrackGrid();
    drawAllObstacle(${race.track.map.allObstacleAsList as JSON});
    drawTrackBox("none");

    var color;
    var path;
    <g:each in="${race.allPlayer}" var="player">
    <g:if test="${player.name == 'John'}">
    color = "red";
    </g:if>
    <g:else>
    color = "blue";
    </g:else>

    <g:def var="car" value="${player.car}" />
    path = [];
    <g:each in="${car.trajectory}" var="trajectoryPoint">
    path.push({x: ${trajectoryPoint.from.x}, y: ${trajectoryPoint.from.y}});
    </g:each>

    <g:if test="${car.trajectory}">
    path.push({x: ${car.trajectory.last().to.x}, y: ${car.trajectory.last().to.y}});
    drawTrajectory(path, color);
    </g:if>

    <g:each in="${car.trajectory}" var="trajectoryPoint">
    drawCar(${trajectoryPoint.from.x}, ${trajectoryPoint.from.y}, .25, color);
    </g:each>

    drawCar(${car.position.x}, ${car.position.y}, .5, color);
    </g:each>

</script>

</body>
</html>