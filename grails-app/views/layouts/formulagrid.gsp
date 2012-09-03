<%@ page import="org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title><g:layoutTitle default="${meta(name: 'app.name')}"/></title>
    <meta name="description" content="">
    <meta name="author" content="">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    <!--[if lt IE 9]>
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">

    %{--TODO Trouver ces images--}%
    %{--<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">--}%
    %{--<link rel="apple-touch-icon" sizes="72x72" href="${resource(dir: 'images', file: 'apple-touch-icon-72x72.png')}">--}%
    %{--<link rel="apple-touch-icon" sizes="114x114"--}%
          %{--href="${resource(dir: 'images', file: 'apple-touch-icon-114x114.png')}">--}%

    <g:layoutHead/>
    <r:layoutResources/>
</head>

<body>

<nav class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">

            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>

            <a class="brand" href="${createLink(uri: '/')}">Formula Grid</a>

            <div class="nav-collapse">
                <ul class="nav">
                    <li<%=request.forwardURI == "${createLink(uri: '/')}" ? ' class="active"' : ''%>><a
                            href="${createLink(uri: '/')}">Accueil</a></li>

                    <li <%=controllerName == 'race' ? 'class="active"' : '' %>>
                        <g:link controller="race">Course</g:link>
                    </li>
                    <li <%=controllerName == 'history' ? 'class="active"' : '' %>>
                        <g:link controller="history">Historique</g:link>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</nav>

<hr />

<div class="container">
    <g:layoutBody/>

    <footer class="footer">
        %{-- TODO Internationaliser --}%
        <p class="pull-right"><a href="#">Back to top</a></p>
        %{-- TODO License--}%
        <p></p>
    </footer>
</div>

<r:layoutResources/>

</body>
</html>