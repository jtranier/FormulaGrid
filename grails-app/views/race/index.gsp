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

<g:render template="rules"/>
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
    new Race(${race as JSON}, "svg", '<g:createLink action="jsonMakeAMove" />');
</script>

</body>
</html>