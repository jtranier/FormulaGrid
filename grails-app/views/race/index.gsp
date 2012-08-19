<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title></title>
</head>
<body>
    <g:render template="/track/track" model="${[track: race.track]}" />
    <g:render template="/car/control" />
</body>
</html>