<table>
    <g:each in="${0..track.height - 1}" var="y">
        <tr>
            <g:each in="${0..track.width - 1}" var="x">
                <td><code>${track.map.bitmap[x][y].asciRepresentation}</code></td>
            </g:each>
        </tr>
    </g:each>
</table>
