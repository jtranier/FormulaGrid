<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title></title>
</head>
<body>
    <code>
        <g:each in="${track.bitmap}" var="trackLine">
            <g:each in="${trackLine}" var="trackPoint">
                ${trackPoint.character}
            </g:each>
            <br />
        </g:each>
    </code>
</body>
</html>