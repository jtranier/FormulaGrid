<table>
    <g:set var="bitmap" value="${race.asciiRepresentation}" />
    <g:each in="${0..race.track.height - 1}" var="y">
        <tr>
            <g:each in="${0..race.track.width - 1}" var="x">
                <td><code>${bitmap[x][y]}</code></td>
            </g:each>
        </tr>
    </g:each>
</table>