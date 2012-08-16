<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="bootstrap"/>
    <title><g:message code="formulagrid.Race"/></title>
    <r:require modules="bootstrap"/>
</head>

<body>
<div class="row">
    <div class="span12" style="text-align: center;">
        <g:render template="/track/track" model="${[track: race.track]}"/>
    </div>
</div>

<hr />

<div class="row">
    <div class="span2 offset5">
        <g:render template="/car/control"/>
    </div>
</div>

</body>
</html>