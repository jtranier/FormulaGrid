<table>
    <g:set var="bitmap" value="${track.map.asciiRepresentation}" />
    <g:each in="${0..track.height - 1}" var="y">
        <tr>
            <g:each in="${0..track.width - 1}" var="x">
                <td><code>${bitmap[x][y]}</code></td>
            </g:each>
        </tr>
    </g:each>
</table>
